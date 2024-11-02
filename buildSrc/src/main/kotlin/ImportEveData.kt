@file:Suppress("DuplicatedCode")

import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.SafeConstructor
import org.yaml.snakeyaml.nodes.CollectionNode
import org.yaml.snakeyaml.nodes.MappingNode
import org.yaml.snakeyaml.nodes.SequenceNode
import java.io.*
import java.net.URL
import java.nio.channels.Channels
import java.nio.file.Files
import java.util.*
import java.util.zip.ZipFile


/**
 * Wraps an output stream into a [DataOutputStream].
 */
fun OutputStream.data() = DataOutputStream(this)


/**
 * Runs the given code, printing the given message before.
 */
internal fun <T> runPrintingMessage(
    message: String,
    block: () -> T
): T {
    println(message)
    return block()
}


/**
 * Imports the EVE Static Data Dump from the given file.
 */
@Suppress("UNUSED_VARIABLE", "unused")
fun importSde(


    /**
     * The SDE file.
     */
    sdeFile: File,


    /**
     * The directory of the EVE client cache, e.g. `~/Library/Application Support/EVE Online/SharedCache`.
     *
     * This is where market group icons are taken from. The item type icons are downloaded on-the-fly from
     * `https://images.evetech.net/types/${iconId}/icon`.
     */
    eveCacheDir: File,


    /**
     * The path to where the output `eve_data.dat` file should be written.
     */
    outputFile: File,


    /**
     * The root directory where the icons should be written.
     */
    iconsOutputDir: File


) {
    val originalEveData = EveData.load(sdeFile)
    val eveData = runPrintingMessage("Fixing Eve data") {
        fixup(originalEveData)
    }
    runPrintingMessage("Validating Eve data") {
        validate(eveData)
    }

    class TypeInfo(
        val type: Type,
        val group: Group,
        val attributes: Map<String, DogmaAttributeValue>,
        val effects: List<DogmaEffect>
    ) {
        val name = type.name
        override fun toString() = name
    }

    fun Collection<Type>.itemTree() = this
        .mapNotNull {
            val dogmaType = eveData.dogmaTypeById[it.id] ?: return@mapNotNull null
            val group = eveData.group(it.groupId)
            TypeInfo(
                type = it,
                group = group,
                attributes = dogmaType.attributeValues.associateBy { attr -> eveData.dogmaAttribute(attr.attributeId).name },
                effects = dogmaType.effectRefs.map { effectRef -> eveData.dogmaEffect(effectRef.effectId) }
            )
        }
        .associateBy { it.name }


    val categories = eveData.knownCategories
    val shipTree = eveData.typesIn(categories.ship).itemTree()
    val skillTree = eveData.typesIn(categories.skill).itemTree()
    val moduleTree = eveData.typesIn(categories.module).itemTree()
    val chargeTree = eveData.typesIn(categories.charge).itemTree()
    val droneTree = eveData.typesIn(categories.drone).itemTree()
    val implantTree = eveData.typesIn(categories.implant).filter {
        eveData.dogmaType(it).hasAttribute(eveData.knownAttributes.implantness)
    }.itemTree()
    val boosterTree = eveData.typesIn(categories.implant).filter {
        eveData.dogmaType(it).hasAttribute(eveData.knownAttributes.boosterness)
    }.itemTree()
    val warfareBuffs = eveData.typesIn(categories.warfareBuffs).itemTree()
    val tacticalModeTree = eveData.typesIn(categories.tacticalMode).itemTree()
    val subsystemTree = eveData.typesIn(categories.subsystem).itemTree()
    val envTree = eveData.relevantEnvironmentTypes().itemTree()
    val allTree = eveData.types.itemTree()

    val modifierOperationToEffects = mutableMapOf<Int?, MutableSet<DogmaEffect>>()
    eveData.dogmaEffects.forEach { effect ->
        effect.modifiers.forEach { modifier ->
            modifierOperationToEffects.putIfAbsent(modifier.operation, mutableSetOf())
            modifierOperationToEffects.getValue(modifier.operation).add(effect)
        }
    }

    val effectToTypeInfo = mutableMapOf<String, MutableSet<TypeInfo>>()
    for (dogmaType in eveData.dogmaTypes) {
        val type = eveData.typeById[dogmaType.id] ?: continue
        val group = eveData.group(type.groupId)
        val effects = dogmaType.effectRefs.map { effectRef -> eveData.dogmaEffect(effectRef.effectId) }
        val typeInfo = TypeInfo(
            type = type,
            group = group,
            attributes = dogmaType.attributeValues.associateBy { attr -> eveData.dogmaAttribute(attr.attributeId).name },
            effects = effects
        )

        effects.forEach {
            effectToTypeInfo.putIfAbsent(it.name, mutableSetOf())
            effectToTypeInfo.getValue(it.name).add(typeInfo)
        }
    }

    val attributeToTypeInfo = mutableMapOf<Int, MutableSet<TypeInfo>>()
    for (dogmaType in eveData.dogmaTypes) {
        val type = eveData.typeById[dogmaType.id] ?: continue
        val group = eveData.group(type.groupId)
        val effects = dogmaType.effectRefs.map { effectRef -> eveData.dogmaEffect(effectRef.effectId) }
        val typeInfo = TypeInfo(
            type = type,
            group = group,
            attributes = dogmaType.attributeValues.associateBy { attr -> eveData.dogmaAttribute(attr.attributeId).name },
            effects = effects
        )

        dogmaType.attributeValues.forEach { attrValue ->
            attributeToTypeInfo.putIfAbsent(attrValue.attributeId, mutableSetOf())
            attributeToTypeInfo.getValue(attrValue.attributeId).add(typeInfo)
        }
    }

    fun effectsAffectingAttribute(attrId: Int): Set<DogmaEffect> {
        val result = mutableSetOf<DogmaEffect>()
        for (effect in eveData.dogmaEffects) {
            if (effect.modifiers.any { (it.modifiedAttributeId == attrId) || (it.modifyingAttributeId == attrId) })
                result.add(effect)
        }
        return result
    }

    data class EffectAndModules(
        val effect: DogmaEffect,
        val modules: List<TypeInfo>
    ) {
        override fun toString() = effect.name
    }

    val modulesByEffect = eveData.dogmaEffects.associateWith { effect ->
        moduleTree.values.filter { typeInfo -> typeInfo.effects.any { it.id == effect.id } }
    }
    val effectsByCategory = mutableMapOf<Int, MutableList<EffectAndModules>>()
    for (effect in eveData.dogmaEffects)
        effectsByCategory.computeIfAbsent(effect.category) { mutableListOf() }
            .add(EffectAndModules(effect, modulesByEffect.getOrElse(effect, ::emptyList)))

    fun Iterable<TypeInfo>.funcToEffects(): Map<String, Collection<DogmaEffect>> {
        return this.flatMap { it.effects.flatMap { effect -> effect.modifiers.map { mod -> mod.func to effect } } }
            .groupBy(
                keySelector = { it.first }, valueTransform = { it.second }
            ).mapValues { it.value.toSet().toList() }
    }

    val funcToModuleEffects = moduleTree.values.funcToEffects()
    val funcToSkillEffects = skillTree.values.funcToEffects()
    val funcToShipEffects = shipTree.values.funcToEffects()

    val funcDomainPairsToEffects = allTree.values.flatMap {
        it.effects.flatMap { effect ->
            effect.modifiers.map { mod -> (mod.func to mod.domain) to effect }
        }
    }.groupBy(
        keySelector = { it.first },
        valueTransform = { it.second }
    ).mapValues { it.value.toSet().toList() }

    outputFile.parentFile.mkdirs()
    outputFile.outputStream().buffered().data().use {
        it.apply {
            writeMetaGroups(eveData)
            writeMarketGroups(eveData)
            writeRaces(eveData)
            writeAttributes(eveData)
            writeEffects(eveData)
            writeCategories(eveData)
            writeGroups(eveData)
            writeCharacter(eveData)
            writeWarfareBuffs(eveData)
            writeSkills(eveData)
            writeShips(eveData)
            writeModules(eveData)
            writeCharges(eveData)
            writeDrones(eveData)
            writeImplants(eveData)
            writeBoosters(eveData)
            writeSubsystems(eveData)
            writeTacticalModes(eveData)
            writeEnvironments(eveData)
            writeMaterials(eveData)
            writeMiscTypes(eveData)
            writeMutaplasmids(eveData)
            writeAbyssalItemReplacements(eveData)
        }
    }

    updateItemTypeIcons(eveData, targetDir = File(iconsOutputDir, "items"))
    updateMarketIcons(eveData, eveCacheDir = eveCacheDir, targetDir = File(iconsOutputDir, "market"))
}


/**
 * Updates the icons for item types.
 */
internal fun updateItemTypeIcons(eveData: EveData, targetDir: File) = with (eveData) {
    println("Checking item type icons...")

    val typeByIconId = types.associateBy { it.iconId }
    val iconIdByFilename = typeByIconId.keys.filterNotNull().associateBy {
        "$it.png"
    }

    val existingFiles = targetDir.listFiles()?.filter { it.name.endsWith(".png") } ?: emptyList()
    for (file in existingFiles) {
        if (file.name !in iconIdByFilename.keys) {
//            println("${file.name} can be deleted")
            println("Deleting item type icon ${file.name}")
            file.delete()
        }
    }

    val fileNames = existingFiles.mapTo(mutableSetOf()){ it.name }
    for ((fileName, iconId) in iconIdByFilename) {
        if (fileName in fileNames)
            continue

        val typeId = fileName.substringBefore(".").toInt()
        val item = typeByIconId[typeId]!!
        println("Icon $fileName for \"${item.name}\" is missing; downloading")
        downloadFile(
            url = URL("https://images.evetech.net/types/${iconId}/icon"),
            outputFile = File(targetDir, fileName)
        )
    }

    // Load all icons and group their names by their content
    val iconIdsByFileContent = mutableMapOf<IconFileContent, MutableList<Int>>()
    for ((iconFileName, iconId) in iconIdByFilename) {
        val file = File(targetDir, iconFileName)
        if (!file.exists())
            continue
        val fileContent = IconFileContent(file.readBytes())
        iconIdsByFileContent.getOrPut(fileContent, ::mutableListOf).add(iconId)
    }
    if (iconIdsByFileContent.values.any { it.size > 1 }) {
        println("Detected duplicate icons; add these remappings to RemappedIcons and re-run this task:")
        for (fileList in iconIdsByFileContent.values) {
            if (fileList.size <= 1)
                continue

            // Pick
            fileList.sort()
            val target = fileList.first()
            for (source in fileList.slice(1..fileList.lastIndex)) {
                println("$source to $target,")
            }
        }
    }

    println("Done.")
}


/**
 * Updates the icons for market types.
 */
internal fun updateMarketIcons(
    eveData: EveData,
    eveCacheDir: File,
    targetDir: File
) = with (eveData) {
    println("Checking market group icons...")

    if (!targetDir.isDirectory && !targetDir.mkdirs())
        error("Unable to create directory $targetDir")

    fun iconFilename(iconId: Int) = "${iconId}.png"

    val resourceIndexFilename = File(eveCacheDir, "index_tranquility.txt").useLines { lines ->
        val line = lines.find { it.contains("resfileindex.txt") }!!
        line.split(",")[1]
    }

    val eveResourcesDir = File(eveCacheDir, "ResFiles")
    val filenameByResourceUrl = File(eveResourcesDir, resourceIndexFilename).useLines { lines ->
        lines.associate {
            val (resourceUrl, filename) = it.split(",")
            resourceUrl to filename
        }
    }

    for (marketGroup in marketGroups) {
        val iconId = marketGroup.iconId ?: continue
        val targetIconFile = File(targetDir, iconFilename(iconId))
        if (targetIconFile.isFile)
            continue

        val resourceUrl = eveData.iconInfoById[iconId]?.iconFile?.lowercase()
            ?: error("No icon info specified for icon $iconId (market group $marketGroup")

        println("Missing icon $iconId for market group $marketGroup; copying from EVE cache")
        val sourceFilename = filenameByResourceUrl[resourceUrl] ?: error("Icon $resourceUrl missing in EVE cache")
        val sourceIconFile = File(eveResourcesDir, sourceFilename)
        if (!sourceIconFile.isFile) {
            println("Market icon $iconId (file $sourceIconFile) for market group $marketGroup doesn't exist; " +
                    "either load it in-game, or add it to MissingMarketIconIds, and re-run this task")
            continue
        }

        Files.copy(sourceIconFile.toPath(), targetIconFile.toPath())
    }

    val neededIconFilenames = marketGroups.mapNotNullTo(mutableSetOf()) { marketGroup ->
        marketGroup.iconId?.let { iconFilename(it) }
    }
    val existingFiles = targetDir.listFiles()?.filter { it.name.endsWith(".png") } ?: emptyList()
    for (file in existingFiles) {
        if (file.name !in neededIconFilenames) {
//            println("${file.name} can be deleted")
            println("Deleting market group icon ${file.name}")
            file.delete()
        }
    }

    println("Done.")
}


/**
 * Wraps the contents of an icon file.
 */
private class IconFileContent(val data: ByteArray) {

    override fun equals(other: Any?): Boolean {
        if (other !is IconFileContent)
            return false

        return data.contentEquals(other.data)
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }

}


/**
 * Downloads the content of the given URL into the given file.
 */
private fun downloadFile(url: URL, outputFile: File) {
    url.openStream().use {
        Channels.newChannel(it).use { rbc ->
            FileOutputStream(outputFile).use { fos ->
                fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
            }
        }
    }
}


/**
 * Writes a list of items into the given output stream using the given function to write each.
 */
private inline fun <T> DataOutputStream.writeList(items: Collection<T>, itemWriter: DataOutputStream.(T) -> Unit) {
    writeInt(items.size)
    items.forEach {
        itemWriter.invoke(this, it)
    }
}


/**
 * Writes a list of items into the given output stream using the given function to write each.
 * Also, prints the number of items in the list and a random element from it.
 */
private inline fun <T> DataOutputStream.writeListPrintStats(
    items: Collection<T>,
    typeName: String,
    itemToString: (T) -> String,
    crossinline itemWriter: DataOutputStream.(T) -> Unit
) {
    runPrintingMessage("Writing ${items.size} $typeName (e.g. ${itemToString(items.random())})..."){
        writeList(items, itemWriter)
    }
}


/**
 * Writes a list of [NamedEntity] items into the given output stream using the given function to write each.
 * Also, prints the number of items in the list and the name of a random element from it.
 */
private inline fun <T: NamedEntity> DataOutputStream.writeListPrintStats(
    items: Collection<T>,
    typeName: String,
    crossinline itemWriter: DataOutputStream.(T) -> Unit
) {
    writeListPrintStats(items, typeName, itemToString = { it.name }, itemWriter)
}


/**
 * Writes an optional id.
 * If the value is null, 0 is written, as actual ids can't be 0.
 */
private fun DataOutputStream.writeOptionalId(id: Int?) = writeInt(id ?: 0)


/**
 * Writes an  id.
 */
private fun DataOutputStream.writeId(id: Int) = writeInt(id)


/**
 * Writes a string.
 */
private fun DataOutputStream.writeString(s: String) = writeUTF(s)


/**
 * Writes an optional string.
 */
private fun DataOutputStream.writeOptionalString(s: String?) {
    writeBoolean(s != null)
    if (s != null)
        writeString(s)
}


/**
 * Writes the [MetaGroup]s.
 */
private fun DataOutputStream.writeMetaGroups(eveData: EveData){
    writeListPrintStats(eveData.metaGroups, "meta groups"){ metaGroup ->
        writeId(metaGroup.id)
        writeString(metaGroup.name)
    }
}


/**
 * Writes the [MarketGroup]s.
 */
private fun DataOutputStream.writeMarketGroups(eveData: EveData){
    writeListPrintStats(eveData.marketGroups, "market groups"){ marketGroup ->
        writeId(marketGroup.id)
        writeString(marketGroup.name)
        writeOptionalId(marketGroup.iconId)
        writeOptionalId(marketGroup.parentGroupId)
    }
}


/**
 * Writes the [Race]s.
 */
private fun DataOutputStream.writeRaces(eveData: EveData){
    writeListPrintStats(eveData.races, "races"){ race ->
        writeId(race.id)
        writeString(race.name)
    }
}


/**
 * Writes the [DogmaAttribute]s.
 */
private fun DataOutputStream.writeAttributes(eveData: EveData){
    writeListPrintStats(eveData.dogmaAttributes, "dogma attributes") { attribute ->
        writeId(attribute.id)
        writeString(attribute.name)
        writeOptionalString(attribute.displayName)
        writeByte(attribute.unitId ?: 0)
        writeByte(flagsToByte(
            flag0 = !attribute.stackable,
            flag1 = attribute.highIsGood != null,
            flag2 = attribute.highIsGood ?: false,
            flag3 = attribute.range != null
        ))
        attribute.range?.let {
            writeDouble(it.start)
            writeDouble(it.endInclusive)
        }
    }
}


/**
 * The types of affected items.
 */
enum class AffectedItemsType(val value: Byte){
    NONE(0), // No affected items, either because we don't care about this effect, or we're just not implementing it yet
    AFFECTING_ITEM(1),  // The affected item is the affecting item itself
    SHIP(2),  // The affected item is the ship
    CHARACTER(3),  // The affected item is the character
    MODULES(4),  // The affected items are things fitted to the ship (modules, rigs, but not drones, probes)
    IMPLANTS_BOOSTERS(5),  // The affected items are things "fitted" to the character (implants, boosters)
    LAUNCHABLES(6),  // The affected items are charges and drones
    LAUNCHER(7),  // The affected item is the module into which the charge is loaded
    WARFARE_BUFFS(100)  // The affected item is the synthetic Warfare Buffs item
}


/**
 * An additional filter on the affected items.
 */
enum class AffectedItemsFilter(val value: Byte){
    ALL(1),  // No filter - all the items are affected
    MATCH_REQUIRED_SKILL(2),  // Item must require the skillTypeId of the modifier
    MATCH_GROUP(3),  // Item's groupId must match the modifier's groupId
}


/**
 * Returns the type of items affected by the given modifier and the type of filter on them.
 *
 * - `func=ItemModifier, domain=itemID`:
 *   The affecting item itself, e.g. skill bonus per skill level.
 *
 * - `func=ItemModifier, domain=shipID`:
 *   The ship, e.g. Mechanics skill increasing ship structure.
 *
 * - `func=ItemModifier, domain=charID`:
 *   The character, e.g.
 *     - Drones skill increasing max controlled drones
 *     - BCS module increasing `missileDMGBonus` attribute (not sure why it's a character attribute).
 *
 * - `func=LocationModifier, domain=shipID`:
 *   All fitted modules, e.g.
 *     - Red Giant has `systemOverloadRange` effect, which affects all modules that have the `overloadRangeBonus`, which
 *       includes all modules whose overload increases their range (warp disruptors, warp scramblers, stasis webifiers,
 *       grapplers).
 *     - Subsystems reducing heat damage to all modules.
 *
 * - `func=LocationRequiredSkillModifier, domain=shipID`:
 *   Fitted modules that require the `skillTypeId` of the modifier, e.g.
 *     - Rapid Launch skill affecting all modules requiring the Missile Launcher Operation skill.
 *     - Thalia bonus to Remote Armor Repairer amount
 *
 * - `func=LocationRequiredSkillModifier, domain=charID`:
 *   Fitted implants or boosters that require the `skillTypeId` of the modifier, e.g.
 *     - Asklepian implants set bonus affecting implants requiring the Cybernetics skill.
 *   Pirate implant set bonuses appear to be the only case here. Not sure why this is used instead of just using
 *   `LocationGroupModifier` for all implant set bonuses.
 *
 * - `func=LocationGroupModifier, domain=shipID`:
 *   Fitted modules that have the `groupId` of the modifier, e.g.
 *     - Electronic Warfare skill reducing capacitor need for ewar modules.
 *     - Augoror bonus to Remote Armor Repairer faloff.
 *
 * - `func=LocationGroupModifier, domain=charID`:
 *   Fitted implants or boosters that require the groupId of the modifier, e.g.
 *     - Talisman implants set bonus affecting implants within the group.
 *     - Biology skill increasing attribute booster duration.
 *
 * - `func=OwnerRequiredSkillModifier, domain=charID`:
 *   Non-module items fitted to the ship (missiles, drones, fighters, probes, bombs) that require the `skillTypeId` of
 *   the modifier, e.g.
 *     - Crow's bonus to missile max velocity per Caldari Frigate skill level (`shipMissileVelocityCF`) affects all such
 *       items that require the Missile Launcher Operation skill (which, contrary to Crow's description, includes all
 *       missiles, not just light missiles and rockets).
 *     - Armageddon's bonus to drone damage per Amarr Battleship skill level (`shipBonusDroneDamageMultiplierAB`)
 *       affects all such items that require the Drones skill (which is all drones).
 *     - Manticore's bonus to kinetic bomb damage per Covert Ops skill level (`eliteBonusCoverOpsBombKineticDmg1`)
 *       affects all such items that require the Bomb Deployment skill (which is all bombs).
 */
private fun DogmaModifier.affectedItemsAndFilter(): Pair<AffectedItemsType, AffectedItemsFilter>{
    val unhandled = AffectedItemsType.NONE to AffectedItemsFilter.ALL
    return when (func){
        ModifierFunc.ItemModifier -> when (domain){
            ModifierDomain.itemID -> AffectedItemsType.AFFECTING_ITEM to AffectedItemsFilter.ALL
            ModifierDomain.shipID -> AffectedItemsType.SHIP to AffectedItemsFilter.ALL
            ModifierDomain.charID -> AffectedItemsType.CHARACTER to AffectedItemsFilter.ALL
            ModifierDomain.warfareBuffsID -> AffectedItemsType.WARFARE_BUFFS to AffectedItemsFilter.ALL
            ModifierDomain.otherID -> AffectedItemsType.LAUNCHER to AffectedItemsFilter.ALL
            else -> unhandled
        }
        ModifierFunc.LocationModifier -> when (domain){
            ModifierDomain.shipID -> AffectedItemsType.MODULES to AffectedItemsFilter.ALL
            else -> unhandled
        }
        ModifierFunc.LocationRequiredSkillModifier -> when (domain){
            ModifierDomain.shipID -> AffectedItemsType.MODULES to AffectedItemsFilter.MATCH_REQUIRED_SKILL
            ModifierDomain.charID -> AffectedItemsType.IMPLANTS_BOOSTERS to AffectedItemsFilter.MATCH_REQUIRED_SKILL
            else -> unhandled
        }
        ModifierFunc.LocationGroupModifier -> when (domain){
            ModifierDomain.shipID -> AffectedItemsType.MODULES to AffectedItemsFilter.MATCH_GROUP
            ModifierDomain.charID -> AffectedItemsType.IMPLANTS_BOOSTERS to AffectedItemsFilter.MATCH_GROUP
            else -> unhandled
        }
        ModifierFunc.OwnerRequiredSkillModifier -> when (domain){
            ModifierDomain.charID -> AffectedItemsType.LAUNCHABLES to AffectedItemsFilter.MATCH_REQUIRED_SKILL
            else -> unhandled
        }
        else -> unhandled
    }
}


/**
 * Converts a set of flags to a [Byte].
 */
private fun flagsToByte(
    flag0: Boolean = false,
    flag1: Boolean = false,
    flag2: Boolean = false,
    flag3: Boolean = false,
    flag4: Boolean = false,
    flag5: Boolean = false,
    flag6: Boolean = false,
    flag7: Boolean = false,
): Int {
    fun Boolean.toInt() = if (this) 1 else 0
    return (
            (flag0.toInt() shl 0) or
            (flag1.toInt() shl 1) or
            (flag2.toInt() shl 2) or
            (flag3.toInt() shl 3) or
            (flag4.toInt() shl 4) or
            (flag5.toInt() shl 5) or
            (flag6.toInt() shl 6) or
            (flag7.toInt() shl 7)
    )
}


/**
 * Returns the effect flags (a byte) we write for the given effect.
 */
private fun effectFlags(dogmaEffect: DogmaEffect): Int {
    return with(dogmaEffect) {
        flagsToByte(
            flag0 = isOffensive,
            flag1 = isAssistive
        )
    }
}


/**
 * Writes dogma effects.
 */
private fun DataOutputStream.writeEffects(eveData: EveData) {
    writeListPrintStats(eveData.dogmaEffects, "dogma effects") { effect ->
        writeId(effect.id)
        writeString(effect.name)
        writeByte(effect.category)
        writeByte(effectFlags(effect))
        writeByte(if (effect.conditionAttributeId == null) 0 else 1)
        if (effect.conditionAttributeId != null) {
            writeId(effect.conditionAttributeId)
            writeInt(effect.conditionAttributeValue!!)
        }
        writeList(effect.modifiers) { modifier ->
            writeOptionalId(modifier.modifiedAttributeId)
            writeOptionalId(modifier.modifyingAttributeId)
            writeOptionalId(modifier.attenuatingAttributeId)
            modifier.affectedItemsAndFilter().let { (itemsType, itemsFilter) ->
                writeByte(itemsType.value.toInt())
                writeByte(itemsFilter.value.toInt())
            }
            writeInt(modifier.operation ?: -255)
            writeOptionalId(modifier.groupId)
            writeOptionalId(modifier.skillTypeId)
        }
    }
}


/**
 * Writes the item type categories.
 */
private fun DataOutputStream.writeCategories(eveData: EveData){
    writeListPrintStats(eveData.categories, "categories"){ category ->
        writeId(category.id)
        writeString(category.name)
    }
}


/**
 * Writes the item type groups.
 */
private fun DataOutputStream.writeGroups(eveData: EveData){
    writeListPrintStats(eveData.groups, "groups"){ group ->
        writeId(group.id)
        writeId(group.categoryId)
        writeString(group.name)
    }
}


/**
 * Writes a [DogmaAttributeValue].
 */
private fun DataOutputStream.writeAttributeValue(attrValue: DogmaAttributeValue){
    writeInt(attrValue.attributeId)
    writeDouble(attrValue.value)
}


/**
 * Writes a [DogmaEffectRef]]
 */
private fun DataOutputStream.writeEffectRef(effectRef: DogmaEffectRef){
    writeInt(effectRef.effectId)
}


/**
 * Writes an Eve type's basic data, common among all the types we write.
 */
private fun DataOutputStream.writeTypeData(type: Type, dogmaType: DogmaType?) {
    writeInt(type.id)
    writeInt(type.groupId)
    writeString(type.name)
    writeOptionalString(type.description)
    writeDouble(type.volume!!)  // Verified by Validations
    writeOptionalId(type.marketGroupId)
    writeOptionalId(type.raceId)
    writeOptionalId(type.iconId)
    if (dogmaType != null) {
        writeList(dogmaType.attributeValues, DataOutputStream::writeAttributeValue)
        writeList(dogmaType.effectRefs, DataOutputStream::writeEffectRef)
    }
}


/**
 * Writes the data of all the types in a given category.
 */
private fun DataOutputStream.writeTypeDataInCategory(
    eveData: EveData,
    category: Category,
    noDogma: Boolean = false,
    extraItemDataWriter: (DataOutputStream.(Type, DogmaType?) -> Unit)? = null
){
    with(eveData){
        val types = typesIn(category)
        val typeName = category.name.lowercase(Locale.ROOT).let {
            if (it.endsWith("y"))
                it.substring(0, it.length-1) + "ies"
            else
                it + "s"
        }
        writeListPrintStats(types, typeName){ type ->
            val dogmaType = if (noDogma) null else dogmaType(type)
            writeTypeData(type, dogmaType)
            extraItemDataWriter?.invoke(this@writeTypeDataInCategory, type, dogmaType)
        }
    }
}


/**
 * Writes the character type.
 */
private fun DataOutputStream.writeCharacter(eveData: EveData){
    val character = eveData.typeById[EveData.CHARACTER_TYPE_ID] ?: throw InvalidSdeException("Missing 'Character' type")
    val dogmaCharacter = eveData.dogmaType(character)
    writeTypeData(character, dogmaCharacter)
}


/**
 * Writes the warfare buffs type.
 */
private fun DataOutputStream.writeWarfareBuffs(eveData: EveData){
    val warfareBuffs = eveData.typeById[eveData.knownTypes.warfareBuffs.id]
        ?: throw InvalidSdeException("Missing 'Warfare Buffs' type")
    val dogmaWarfareBuffs = eveData.dogmaType(warfareBuffs)
    writeTypeData(warfareBuffs, dogmaWarfareBuffs)
}


/**
 * The regex for the "showinfo" tags.
 */
private val SHOW_INFO_TAG_REGEX = "<a href=showinfo:\\d+>([^<]*)</a>".toRegex()


/**
 * Strips "showinfo" tags from the given string.
 */
private fun String.stripShowInfoTags() = replace(SHOW_INFO_TAG_REGEX){
    it.groupValues[1]
}


/**
 * Fixes the bonus value string.
 */
private fun String.fixBonusValue() = removeSuffix(".0")


/**
 * Writes a single [Bonus].
 */
private fun DataOutputStream.writeBonus(bonus: Bonus) {
    writeString(bonus.text.stripShowInfoTags())

    writeBoolean(bonus.value != null)
    if (bonus.value != null) {
        writeString(bonus.value.fixBonusValue())
        writeByte(
            when (bonus.unit) {
                BonusUnit.METERS -> 1
                BonusUnit.KILOGRAMS -> 2
                BonusUnit.MULTIPLY -> 3
                BonusUnit.PERCENT -> 4
                BonusUnit.NUMBER -> 5
                BonusUnit.OTHER -> throw InvalidSdeException("Unknown bonus unit")
                null -> throw InvalidSdeException("Bonus unit should not be null when value is not null")
            }
        )
    }
}


/**
 * Writes the ship types.
 */
private fun DataOutputStream.writeShips(eveData: EveData) {

    fun writeBonuses(bonuses: List<Bonus>) {
        writeList(bonuses.sortedBy { it.importance }, DataOutputStream::writeBonus)
    }

    writeTypeDataInCategory(eveData, eveData.knownCategories.ship){ ship, _ ->
        val traits = ship.traits

        val perSkillLevel = traits?.perSkillLevel ?: emptyMap()
        writeList(perSkillLevel.entries){ (skillId, bonuses) ->
            writeId(skillId)
            writeBonuses(bonuses)
        }

        writeBonuses(traits?.role ?: emptyList())
        writeBonuses(traits?.misc ?: emptyList())
    }
}


/**
 * Returns the module flags (a byte) we write for the given module.
 */
private fun moduleFlags(eveData: EveData, dogmaModule: DogmaType): Int {
    val effects = eveData.dogmaEffects(dogmaModule.effectRefs)
    val knownEffects = eveData.knownEffects
    val onlineEffect = knownEffects.online

    val isProjected = effects.any { it.category == EffectCategory.projected }
    val isActivable = isProjected || effects.any {
        (it.category == EffectCategory.active) and (it != onlineEffect) // For some reason even passive modules have the 'online' effect
    }
    val isOverloadable = effects.any { it.category == EffectCategory.overloaded }
    val takesTurretHardpoint = effects.contains(knownEffects.turretFitted)
    val takesLauncherHardpoint = effects.contains(knownEffects.launcherFitted)

    return flagsToByte(
        flag0 = isActivable,
        flag1 = isOverloadable,
        flag2 = isProjected,
        flag3 = takesTurretHardpoint,
        flag4 = takesLauncherHardpoint
    )
}


/**
 * Returns the value we write to indicate the module's slot type; null if the module does not have a slot.
 */
internal fun moduleSlotType(knownEffects: EveData.KnownEffects, dogmaModule: DogmaType): Int?{
    for (effectRef in dogmaModule.effectRefs){
        when (effectRef.effectId){
            knownEffects.highSlot.id -> return 1
            knownEffects.medSlot.id -> return 2
            knownEffects.lowSlot.id -> return 3
            knownEffects.rigSlot.id -> return 4
        }
    }

    return null
}


/**
 * Writes the module types.
 */
private fun DataOutputStream.writeModules(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.module){ module, dogmaModule ->
        dogmaModule!!
        writeInt(module.variationParentTypeID!!)  // Fixups assigns variationParentTypeID to all modules
        writeByte(moduleSlotType(eveData.knownEffects, dogmaModule)!!)  // Fixups filters out modules without a slot
        writeByte(moduleFlags(eveData, dogmaModule))
    }
}


/**
 * Writes the charge types.
 */
private fun DataOutputStream.writeCharges(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.charge)
}


/**
 * Writes the skill types.
 */
private fun DataOutputStream.writeSkills(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.skill)
}


/**
 * Writes the drone types.
 */
private fun DataOutputStream.writeDrones(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.drone){ drone, _ ->
        writeInt(drone.variationParentTypeID!!)  // Fixups assigns variationParentTypeID to all drones
    }
}


/**
 * Writes the implant types.
 */
private fun DataOutputStream.writeImplants(eveData: EveData) {
    with(eveData){
        // The implant category includes boosters
        val implants = typesIn(knownCategories.implant).filter {
            dogmaType(it).hasAttribute(knownAttributes.implantness)
        }
        writeListPrintStats(implants, "implants"){ implant ->
            val dogmaImplant = dogmaType(implant)
            writeTypeData(implant, dogmaImplant)
            writeInt(implant.variationParentTypeID!!)  // Fixups assigns variationParentTypeID to all implants
            writeByte(dogmaImplant.getAttributeValue(knownAttributes.implantness)!!.toInt())  // Implant slot
        }
    }
}


/**
 * The information we write about a booster's side effect.
 */
private class BoosterSideEffect(
    val penalizedAttributeId: Int,
    val penalizingAttributeId: Int
)


/**
 * Returns the list of the booster's side effects.
 */
private fun DogmaType.boosterSideEffects(eveData: EveData, typeName: String): List<BoosterSideEffect> {

    val attributeValueById = attributeValues.associateBy { it.attributeId }
    fun DogmaModifier.isPenalty(): Boolean {
        val modifiedAttributeId = this.modifiedAttributeId ?: return false
        val modifiedAttributeHighIsGood = eveData.dogmaAttribute(modifiedAttributeId).highIsGood ?: return false
        val modifyingAttributeValue = attributeValueById[modifyingAttributeId]!!.value
        val modifiedValueIncreases = if (operation == ModifierOperation.addPercent)
            modifyingAttributeValue > 0.0
        else
            throw IllegalStateException("Unexpected booster $typeName operation: $operation")
        return modifiedValueIncreases != modifiedAttributeHighIsGood
    }

    val effects = effectRefs.map { eveData.dogmaEffect(it.effectId) }
    val penaltyEffects = effects.filter { effect -> effect.modifiers.all { it.isPenalty() } }

    return penaltyEffects
        .map { effect ->
            val modifiedAttributeIds = effect.modifiers.mapNotNull { it.modifiedAttributeId }.toSet()
            if (modifiedAttributeIds.size != 1)
                throw IllegalStateException("Booster $typeName, effect $effect has a penalty effect with more than one modified attribute")

            val modifyingAttributeIds = effect.modifiers.mapNotNull { it.modifyingAttributeId }.toSet()
            if (modifyingAttributeIds.size != 1)
                throw IllegalStateException("Booster $typeName, effect $effect has a penalty effect with more than one modifying attribute")

            BoosterSideEffect(
                penalizedAttributeId = modifiedAttributeIds.first(),
                penalizingAttributeId = modifyingAttributeIds.first()
            )
        }
        .sortedBy { it.penalizedAttributeId }
}


/**
 * Writes the booster types.
 */
private fun DataOutputStream.writeBoosters(eveData: EveData) {
    with(eveData){
        // The implant category includes boosters
        val implants = typesIn(knownCategories.implant).filter {
            dogmaType(it).hasAttribute(knownAttributes.boosterness)
        }
        writeListPrintStats(implants, "boosters"){ booster ->
            val dogmaBooster = dogmaType(booster)
            writeTypeData(booster, dogmaBooster)
            writeInt(booster.variationParentTypeID!!)  // Fixups assigns variationParentTypeID to all boosters
            writeByte(dogmaBooster.getAttributeValue(knownAttributes.boosterness)!!.toInt())  // Booster slot
            writeList(dogmaBooster.boosterSideEffects(eveData, booster.name)) {
                writeId(it.penalizedAttributeId)
                writeId(it.penalizingAttributeId)
            }
        }
    }
}


/**
 * Writes the subsystem types.
 */
private fun DataOutputStream.writeSubsystems(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.subsystem)
}


/**
 * Writes the material types.
 */
private fun DataOutputStream.writeMaterials(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.material, noDogma = true)
}


/**
 * Writes miscellaneous types we only need to be able to place them in the cargohold.
 */
private fun DataOutputStream.writeMiscTypes(eveData: EveData) {
    with(eveData) {
        val types = typesIn(knownCategories.deployable) +
            typesIn(knownGroups.jumpFilaments, knownGroups.triglavianSpaceFilaments)
        writeListPrintStats(types, "misc. items") { type ->
            val dogmaType = dogmaType(type.id)
            writeTypeData(type, dogmaType)
        }
    }
}


/**
 * Writes the tactical mode types.
 */
private fun DataOutputStream.writeTacticalModes(eveData: EveData) {
    writeTypeDataInCategory(eveData, eveData.knownCategories.tacticalMode)
}


/**
 * Writes the environments.
 */
private fun DataOutputStream.writeEnvironments(eveData: EveData) = with(eveData) {
    writeListPrintStats(eveData.relevantEnvironmentTypes(), "environments") { type ->
        val dogmaType = dogmaType(type)
        writeTypeData(type, dogmaType)
        writeInt(type.variationParentTypeID!!)  // Fixups assigns variationParentTypeID to all environments
    }
}


/**
 * Writes the mutaplasmids.
 */
private fun DataOutputStream.writeMutaplasmids(eveData: EveData) {
    writeList(eveData.mutaplasmids) { mutaplasmid ->
        val type = eveData.type(mutaplasmid.id)
        writeId(mutaplasmid.id)
        writeString(type.name)
        writeList(mutaplasmid.targetTypeIds, DataOutputStream::writeId)
        writeList(mutaplasmid.mutations) {
            writeId(it.attributeId)
            writeDouble(it.min)
            writeDouble(it.max)
            writeByte(
                when (it.highIsGood) {
                    null -> 0
                    true -> 1
                    false -> 2
                }
            )
        }
    }
}


/**
 * Write the map of abyssal type names to their "parent" types (should be the Tech 1 counterpart).
 *
 * These are the item types that will be used when e.g. importing a module with that name, without mutation info.
 */
private fun DataOutputStream.writeAbyssalItemReplacements(eveData: EveData) {
    writeList(eveData.abyssalNameToReplacementTypeId.entries) { (name, parentTypeId) ->
        writeString(name)
        writeId(parentTypeId)
    }
}


/**
 * The exception we throw when we encounter something unexpected in the SDE.
 */
class InvalidSdeException(description: String) : RuntimeException(description)


/**
 * A mapping from language code to a translation of some text into that language.
 */
private typealias TranslatedText = Map<String, String>


/**
 * Generic YAML node.
 */
internal open class YamlNode(val properties: Map<Any, Any>){


    /**
     * Returns the value of the given property, of the given type.
     * Throws a [InvalidSdeException] if the value is not of the correct type.
     * Note that if type is non-optional type (subtype of [Any]) and the value is missing, a [InvalidSdeException] will
     * be thrown. If the type is an optional type, however, null will be returned.
     */
    private inline fun <reified T> get(key: String): T{
        return when (val value = properties[key]) {
            is T -> value
            null -> throw InvalidSdeException("Missing '${key}' property type on\n$this")
            else -> throw InvalidSdeException("Wrong '${key}' property type ($value) on\n$this")
        }
    }


    /**
     * Returns the child [YamlNode] with the given key, or `null` if it is missing.
     */
    fun childOrNull(key: String) = get<Map<Any, Any>?>(key)?.let(::YamlNode)


    /**
     * Returns the value of the given [Int] property. The property must be present.
     */
    fun int(key: String) = get<Int>(key)


    /**
     * Returns the value of the given [Int] property, or `null` if it is missing.
     */
    fun intOrNull(key: String) = get<Int?>(key)


    /**
     * Returns the value of the given [Double] property. The property must be present.
     */
    fun double(key: String) = get<Double>(key)


    /**
     * Returns the value of the given [Double] property, or `null` if it is missing.
     */
    fun doubleOrNull(key: String) = get<Double?>(key)


    /**
     * Returns the value of the given [Boolean] property. The property must be present.
     */
    fun boolean(key: String) = get<Boolean>(key)


    /**
     * Returns the value of the given [Boolean] property or `null` if it's missing.
     */
    fun booleanOrNull(key: String) = get<Boolean?>(key)


    /**
     * Returns the value of the given [String] property. The property must be present.
     */
    fun string(key: String) = get<String>(key)


    /**
     * Returns the value of the given [Int] or [Double] property. The property must be present.
     */
    fun number(key: String) = get<Number>(key)


    /**
     * Returns the value of the given [Int] or [Double] property, or `null` if it is missing.
     */
    fun numberOrNull(key: String) = get<Number?>(key)


    /**
     * Returns the child list node mapped with the given key. The node must be present.
     */
    fun list(key: String): List<YamlNode> = get<List<Map<Any, Any>>>(key).map(::YamlNode)


    /**
     * Returns the child list node mapped with the given key, or null if the property is missing.
     */
    fun listOrNull(key: String): List<YamlNode>? = get<List<Map<Any, Any>>?>(key)?.map(::YamlNode)


    /**
     * Returns the translations of the property with the given name. The property must be present.
     */
    private fun translatedText(key: String) = get<TranslatedText>(key)


    /**
     * Returns the English translation of the property with the given name. The property must be present.
     */
    fun textEn(key: String) = translatedText(key)["en"]!!


    /**
     * Returns the translations of the property with the given name, or null if the property is missing.
     */
    private fun translatedTextOrNull(key: String) = get<TranslatedText?>(key)


    /**
     * Returns the English translation of the property with the given name, or null if the property or
     * translation are missing.
     */
    fun textEnOrNull(key: String) = translatedTextOrNull(key)?.get("en")


    override fun toString() = properties.toString()


}


/**
 * [DogmaEffect] category values.
 */
@Suppress("unused")
internal object EffectCategory{
    const val always = 0
    const val active = 1
    const val projected = 2
    const val passive = 4
    const val overloaded = 5
}



/**
 * [DogmaModifier] domain values.
 */
@Suppress("unused")
internal object ModifierDomain{
    const val itemID = "itemID"
    const val shipID = "shipID"
    const val charID = "charID"
    const val targetID = "targetID"
    const val warfareBuffsID = "warfareBuffsID"
    const val otherID = "otherID"
}


/**
 * [DogmaModifier] func values.
 */
@Suppress("unused")
internal object ModifierFunc{
    const val ItemModifier = "ItemModifier"
    const val LocationRequiredSkillModifier = "LocationRequiredSkillModifier"
    const val LocationGroupModifier = "LocationGroupModifier"
    const val LocationModifier = "LocationModifier"
    const val OwnerRequiredSkillModifier = "OwnerRequiredSkillModifier"
    const val EffectStopper = "EffectStopper"
}


/**
 * [DogmaModifier] operation values.
 */
@Suppress("unused")
internal object ModifierOperation {
    const val preMultiply = 0
    const val add = 2
    const val subtract = 3
    const val postMultiply = 4
    const val postDivide = 5
    const val addPercent = 6
    const val setValue = 7
    const val setMaxAbsValue = 100  // Added for command bursts; sets only if the new value has a larger absolute value
    // Added for speedFactor of propulsion, whose value is a percentage, but it should be multiplied by, not added
    const val multiplyPercent = 101
    const val coerceAtLeast = 102
    const val coerceAtMost = 103
}


/**
 * Unit ids.
 */
internal object UnitIds {
    const val MultiplierDisplayedAsAddedPercentage = 109
}


/**
 * A node that maps an integer id to some entity.
 * All the files in the 'fsd' directory appear to contain entities like this.
 */
internal class YamlNodeWithId(
    val id: Int,
    properties: Map<Any, Any>
): YamlNode(properties)


private fun simpleToString(typeName: String, name: String?, vararg properties: Pair<String, Any?>) = buildString {
    append(typeName)
    append("(")
    var first = true
    if (name != null) {
        append(name)
        first = false
    }
    properties.forEach {
        val (key, value) = it
        if (value != null){
            if (!first)
                append(", ")
            append(key)
            append("=")
            append(value)
            append("")
            first = false
        }
    }
    append(")")
}


/**
 * Base class for entities that have an integer id.
 */
internal open class EntityWithId(val id: Int){


    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (javaClass != other?.javaClass)
            return false

        other as EntityWithId

        return id == other.id
    }


    override fun hashCode(): Int {
        return id
    }


}


/**
 * Interface for entities that have a `name` [String] property.
 */
internal interface NamedEntity{
    val name: String
}


/**
 * Meta groups, e.g. "Tech I", "Storyline" etc.
 */
internal class MetaGroup(
    id: Int,
    override val name: String,
): EntityWithId(id), NamedEntity{

    constructor(node: YamlNodeWithId): this(
        id = node.id,
        name = node.textEn("nameID")
    )

    override fun toString() = simpleToString(
        typeName = "MetaGroup",
        name = name,
        "id" to id,
    )

}


/**
 * Market groups, e.g. "Standard Frigates" or "Signal Amplifiers"
 */
internal class MarketGroup(
    id: Int,
    override val name: String,
    val iconId: Int?,
    val parentGroupId: Int?
): EntityWithId(id), NamedEntity{


    constructor(node: YamlNodeWithId): this(
        id = node.id,
        name = node.textEn("nameID"),
        iconId = node.intOrNull("iconID"),
        parentGroupId = node.intOrNull("parentGroupID")
    )


    fun copy(
        id: Int = this.id,
        name: String = this.name,
        iconId: Int? = this.iconId,
        parentGroupId: Int? = this.parentGroupId
    ) = MarketGroup(
        id = id,
        name = name,
        iconId = iconId,
        parentGroupId = parentGroupId
    )


    override fun toString() = simpleToString(
        typeName = "MarketGroup",
        name = name,
        "parentGroupId" to parentGroupId
    )

}


/**
 * A pilot or ship race.
 */
internal class Race(
    id: Int,
    override val name: String
): EntityWithId(id), NamedEntity{

    constructor(node: YamlNodeWithId): this(
        id = node.id,
        name = node.textEn("nameID")
    )

    override fun toString() = simpleToString(
        typeName = "Race",
        name = name,
        "id" to id
    )

}


/**
 * The units for the [Bonus.value].
 */
enum class BonusUnit {

    METERS,  // e.g. the bonus to drone control range for the Ishtar
    KILOGRAMS,  // e.g. the added mass of an Entosis Link
    MULTIPLY,  // e.g. the penalty to Entosis Link duration on dreads
    PERCENT, // e.g. the bonus to armor resistances on the Vengeance (nearly all bonuses use this)
    NUMBER, // e.g. the bonus to ship warp core strength on Titans
    OTHER; // Some other unit we are not interested in

    companion object {

        fun decode(unitId: Int) = when (unitId) {
            1 -> METERS
            2 -> KILOGRAMS
            104 -> MULTIPLY
            105 -> PERCENT
            139 -> NUMBER
            else -> OTHER
        }

    }

}


/**
 * Describes a ship bonus.
 */
internal class Bonus(
    val text: String,  // The text of the bonus, e.g. "bonus to whatever"
    val value: String?,  // The value of the bonus, e.g. "5" for a "5% bonus to whatever"
    val unit: BonusUnit?,  // The unit of the bonus value
    val importance: Int  // The "importance" of the bonus, determining the order the bonuses should be displayed
) {

    constructor(node: YamlNode): this(
        text = node.textEn("bonusText"),
        value = node.numberOrNull("bonus")?.toString(),  // Could get an inexact value here due to conversion from string and back
        unit = node.intOrNull("unitID")?.let { BonusUnit.decode(it) },
        importance = node.int("importance")
    )

    override fun toString() = simpleToString(
        typeName = "Bonus",
        name = null,
        "value" to value,
        "unit" to unit,
        "text" to text,
        "importance" to importance
    )

}


/**
 * Describes a ship's traits (bonuses and special abilities).
 *
 * Note that this is just the description, the bonuses themselves are implemented via effects/modifiers.
 */
internal class Traits(
    val perSkillLevel: Map<Int, List<Bonus>>?,
    val role: List<Bonus>?,
    val misc: List<Bonus>?,
) {

    @Suppress("UNCHECKED_CAST")
    constructor(node: YamlNode): this(
        perSkillLevel = node.childOrNull("types")?.let { perSkillBonusNode ->
            perSkillBonusNode.properties.entries.associate { (skillId, bonuses) ->
                (skillId as Int) to (bonuses as List<Map<Any, Any>>).map { Bonus(YamlNode(it)) }
            }
        },
        role = node.listOrNull("roleBonuses")?.map(::Bonus),
        misc = node.listOrNull("miscBonuses")?.map(::Bonus)
    )

}



/**
 * A category of entities, e.g. "Module".
 * This is the most top-level grouping. Each category contains groups.
 *
 * Categories come from `categoryIDs.yaml`.
 */
internal class Category(
    id: Int,
    override val name: String,
) : EntityWithId(id), NamedEntity {


    constructor(node: YamlNodeWithId) : this(
        id = node.id,
        name = node.textEn("name"),
    )


    override fun toString() = simpleToString(
        typeName = "Category",
        name = name,
        "id" to id,
    )

}


/**
 * A group of entities, e.g. "Propulsion Module".
 * This is the mid-level grouping - each group belongs to a category and contains types.
 *
 * Groups come from `groupIDs.yaml`.
 */
internal class Group(
    id: Int,
    override val name: String,
    val categoryId: Int
) : EntityWithId(id), NamedEntity {


    constructor(node: YamlNodeWithId) : this(
        id = node.id,
        name = node.textEn("name"),
        categoryId = node.int("categoryID")
    )


    fun copy(
        id: Int = this.id,
        name: String = this.name,
        categoryId: Int = this.categoryId
    ) = Group(
        id = id,
        name = name,
        categoryId = categoryId
    )


    override fun toString() = simpleToString(
        typeName = "Group",
        name = name,
        "id" to id,
        "categoryId" to categoryId,
    )


}


/**
 * A type of entities, e.g. "1MN Afterburner II".
 * This is the most bottom-level grouping - each type belongs to a group.
 *
 * Types come from `typeIDs.yaml`.
 */
internal class Type(
    id: Int,
    override val name: String,
    val groupId: Int,
    val metaGroupId: Int?,
    val capacity: Double?,  // Ship cargo or module capacity
    val mass: Double?,  // Ship mass
    val radius: Double?,  // Ship ball radius
    val volume: Double?,  // Item volume, in m^3
    val variationParentTypeID: Int?,  // The id of the parent of the variations of this type
    val marketGroupId: Int?,  // The id of this item's most specific market group
    val raceId: Int?,  // The id of the item's race, if any
    val iconId: Int?,  // The id of the item's icon, if any
    val description: String?,
    val traits: Traits?,
    val published: Boolean,
): EntityWithId(id), NamedEntity {


    constructor(node: YamlNodeWithId) : this(
        id = node.id,
        name = node.textEn("name"),
        groupId = node.int("groupID"),
        metaGroupId = node.numberOrNull("metaGroupID")?.toInt(),
        capacity = node.doubleOrNull("capacity"),
        mass = node.doubleOrNull("mass"),
        radius = node.doubleOrNull("radius"),
        volume = node.doubleOrNull("volume"),
        variationParentTypeID = node.intOrNull("variationParentTypeID"),
        marketGroupId = node.intOrNull("marketGroupID"),
        raceId = node.intOrNull("raceID"),
        iconId = null,  // No idea what the iconID in the YAML refers to; we calculate our in fixup
        description = node.textEnOrNull("description"),
        traits = node.childOrNull("traits")?.let { Traits(it) },
        published = node.boolean("published"),
    )


    /**
     * Returns a [Type] that is like this one, but with the given values changed.
     */
    fun copy(
        id: Int = this.id,
        name: String = this.name,
        groupId: Int = this.groupId,
        metaGroupId: Int? = this.metaGroupId,
        capacity: Double? = this.capacity,
        mass: Double? = this.mass,
        radius: Double? = this.radius,
        volume: Double? = this.volume,
        variationParentTypeID: Int? = this.variationParentTypeID,
        marketGroupId: Int? = this.marketGroupId,
        raceId: Int? = this.raceId,
        iconId: Int? = this.iconId,
        description: String? = this.description,
        traits: Traits? = this.traits,
        published: Boolean = this.published,
    ) = Type(
        id = id,
        name = name,
        groupId = groupId,
        metaGroupId = metaGroupId,
        capacity = capacity,
        mass = mass,
        radius = radius,
        volume = volume,
        variationParentTypeID = variationParentTypeID,
        marketGroupId = marketGroupId,
        raceId = raceId,
        iconId = iconId,
        description = description,
        traits = traits,
        published = published,
    )


    override fun toString() = simpleToString(
        typeName = "Type",
        name = name,
        "id" to id,
        "groupId" to groupId,
        "metaGroupId" to metaGroupId,
        "capacity" to capacity,
        "mass" to mass,
        "volume" to volume,
        "variationParentTypeID" to variationParentTypeID,
        "published" to published
    )

}


/**
 * A dogma attribute and its value in a concrete instance.
 */
internal class DogmaAttributeValue(
    val attributeId: Int,
    val value: Double,
){


    constructor(node: YamlNode): this(
        attributeId = node.int("attributeID"),
        value = node.double("value")
    )


    /**
     * Returns a [DogmaAttributeValue] that is like this one, but with the given values changed.
     */
    @Suppress("unused")
    fun copy(
        attributeId: Int = this.attributeId,
        value: Double = this.value,
    ) = DogmaAttributeValue(
        attributeId = attributeId,
        value = value,
    )


    override fun toString() = simpleToString(
        typeName = "AttributeValue",
        name = null,
        "attributeId" to attributeId,
        "value" to value,
    )

}


/**
 * A reference to a dogma effect.
 */
internal class DogmaEffectRef(val effectId: Int){

    constructor(node: YamlNode): this(node.int("effectID"))

    override fun toString() = simpleToString(
        typeName = "EffectRef",
        name = null,
        "effectId" to effectId,
    )

}


/**
 * A Dogma type.
 *
 * Dogma types come from `typeDogma.yaml`.
 */
internal class DogmaType(
    id: Int,
    val attributeValues: List<DogmaAttributeValue>,
    val effectRefs: List<DogmaEffectRef>
): EntityWithId(id){


    constructor(node: YamlNodeWithId) : this(
        id = node.id,
        attributeValues = node.list("dogmaAttributes").map(::DogmaAttributeValue),
        effectRefs = node.list("dogmaEffects").map(::DogmaEffectRef)
    )


    /**
     * Returns whether this [DogmaType] has the given attribute.
     */
    fun hasAttribute(attribute: DogmaAttribute): Boolean = getAttributeValue(attribute) != null


    /**
     * Returns the value of the given attribute in this [DogmaType], or `null` if none.
     */
    fun getAttributeValue(attribute: DogmaAttribute): Double? =
        attributeValues.find { it.attributeId == attribute.id }?.value


    /**
     * Returns whether this [DogmaType] has the given effect.
     */
    fun hasEffect(effect: DogmaEffect) = effectRefs.any { it.effectId == effect.id }


    /**
     * Returns a [DogmaType] that is like this one, but with the given values changed.
     */
    fun copy(
        id: Int = this.id,
        attributeValues: List<DogmaAttributeValue> = this.attributeValues,
        effectRefs: List<DogmaEffectRef> = this.effectRefs
    ) = DogmaType(
        id = id,
        attributeValues = attributeValues,
        effectRefs = effectRefs
    )


    override fun toString() = simpleToString(
        typeName = "DogmaType",
        name = null,
        "id" to id,
    )


}


/**
 * Dogma effects.
 *
 * Dogma effects come from `dogmaEffects.yaml`
 */
internal class DogmaEffect(
    id: Int,
    override val name: String,
    val category: Int,
    val isOffensive: Boolean,
    val isAssistive: Boolean,
    val durationAttributeId: Int?,
    val rangeAttributeId: Int?,
    val falloffAttributeId: Int?,
    val trackingSpeedAttributeId: Int?,
    val conditionAttributeId: Int?,
    val conditionAttributeValue: Int?,
    val modifiers: List<DogmaModifier>,
) : EntityWithId(id), NamedEntity {


    init {
        if ((conditionAttributeId != null) != (conditionAttributeValue != null))
            throw IllegalArgumentException("conditionAttributeId and conditionAttributeValue must be either null together or non-null together")
    }


    constructor(node: YamlNodeWithId): this(
        id = node.id,
        name = node.string("effectName"),
        category = node.int("effectCategory"),
        isOffensive = node.boolean("isOffensive"),
        isAssistive = node.boolean("isAssistance"),
        durationAttributeId = node.intOrNull("durationAttributeID"),
        rangeAttributeId = node.intOrNull("rangeAttributeID"),
        falloffAttributeId = node.intOrNull("falloffAttributeID"),
        trackingSpeedAttributeId = node.intOrNull("trackingSpeedAttributeID"),
        conditionAttributeId = null,
        conditionAttributeValue = null,
        modifiers = node.listOrNull("modifierInfo")?.map(::DogmaModifier) ?: emptyList()
    )

    /**
     * Returns a [DogmaEffect] that is like this one, but with the given values changed.
     */
    fun copy(
        id: Int = this.id,
        name: String = this.name,
        category: Int = this.category,
        isOffensive: Boolean = this.isOffensive,
        isAssistive: Boolean = this.isAssistive,
        durationAttributeId: Int? = this.durationAttributeId,
        rangeAttributeId: Int? = this.rangeAttributeId,
        falloffAttributeId: Int? = this.falloffAttributeId,
        trackingSpeedAttributeId: Int? = this.trackingSpeedAttributeId,
        conditionAttributeId: Int? = this.conditionAttributeId,
        conditionAttributeValue: Int? = this.conditionAttributeValue,
        modifiers: List<DogmaModifier> = this.modifiers,
    ) = DogmaEffect(
        id = id,
        name = name,
        category = category,
        isOffensive = isOffensive,
        isAssistive = isAssistive,
        durationAttributeId = durationAttributeId,
        rangeAttributeId = rangeAttributeId,
        falloffAttributeId = falloffAttributeId,
        trackingSpeedAttributeId = trackingSpeedAttributeId,
        conditionAttributeId = conditionAttributeId,
        conditionAttributeValue = conditionAttributeValue,
        modifiers = modifiers,
    )


    override fun toString() = simpleToString(
        typeName = "DogmaEffect",
        name = name,
        "id" to id,
        "mods" to modifiers.joinToString(";"){ "${it.modifyingAttributeId}->${it.modifiedAttributeId}" },
    )

}


/**
 * Dogma attributes.
 *
 * Dogma attributes come from `dogmaAttributes.yaml`
 */
internal class DogmaAttribute(
    id: Int,
    override val name: String,
    val displayName: String?,
    val stackable: Boolean,
    val highIsGood: Boolean?,
    val range: ClosedRange<Double>? = null,
    val unitId: Int?,
) : EntityWithId(id), NamedEntity {

    constructor(node: YamlNodeWithId): this(
        id = node.id,
        name = node.string("name"),
        displayName = node.textEnOrNull("displayNameID"),
        stackable = node.boolean("stackable"),
        highIsGood = node.booleanOrNull("highIsGood"),
        unitId = node.intOrNull("unitID")
    )

    /**
     * Returns a [DogmaAttribute] that is like this one, but with the given values changed.
     */
    fun copy(
        id: Int = this.id,
        name: String = this.name,
        displayName: String? = this.displayName,
        stackable: Boolean = this.stackable,
        highIsGood: Boolean? = this.highIsGood,
        range: ClosedRange<Double>? = this.range,
        unitId: Int? = this.unitId
    ) = DogmaAttribute(
        id = id,
        name = name,
        displayName = displayName,
        stackable = stackable,
        highIsGood = highIsGood,
        range = range,
        unitId = unitId
    )


    override fun toString() = simpleToString(
        typeName = "DogmaAttribute",
        name = name,
        "id" to id,
    )

}


/**
 * Dogma modifiers are part of dogma effects.
 *
 * Dogma modifiers come from `dogmaEffects.yaml`.
 */
internal class DogmaModifier(
    val domain: String,
    val func: String,
    val modifiedAttributeId: Int?,
    val modifyingAttributeId: Int?,
    val attenuatingAttributeId: Int? = null,  // For implementing ewar resistances
    val operation: Int?,
    val groupId: Int? = null,
    val skillTypeId: Int? = null,
) {

    constructor(node: YamlNode): this(
        domain = node.string("domain"),
        func = node.string("func"),
        modifiedAttributeId = node.intOrNull("modifiedAttributeID"),
        modifyingAttributeId = node.intOrNull("modifyingAttributeID"),
        attenuatingAttributeId = null,
        operation = node.intOrNull("operation"),
        groupId = node.intOrNull("groupID"),
        skillTypeId = node.intOrNull("skillTypeID"),
    )

    /**
     * Returns a [DogmaModifier] that is like this one, but with the given values changed.
     */
    fun copy(
        domain: String = this.domain,
        func: String = this.func,
        modifiedAttributeId: Int? = this.modifiedAttributeId,
        modifyingAttributeId: Int? = this.modifyingAttributeId,
        attenuatingAttributeId: Int? = this.attenuatingAttributeId,
        operation: Int? = this.operation,
        groupId: Int? = this.groupId,
        skillTypeId: Int? = this.skillTypeId,
    ) = DogmaModifier(
        domain = domain,
        func = func,
        modifiedAttributeId = modifiedAttributeId,
        modifyingAttributeId = modifyingAttributeId,
        attenuatingAttributeId = attenuatingAttributeId,
        operation = operation,
        groupId = groupId,
        skillTypeId = skillTypeId
    )


    override fun toString() = simpleToString(
        typeName = "DogmaModifier",
        name = null,
        "domain" to domain,
        "func" to func,
        "modifiedAttributeId" to modifiedAttributeId,
        "modifyingAttributeId" to modifyingAttributeId,
        "operation" to operation,
        "groupId" to groupId,
        "skillTypeId" to skillTypeId
    )

}


/**
 * Icon information.
 *
 * This comes from `iconIDs.yaml`.
 */
internal class IconInfo(
    id: Int,
    val iconFile: String
): EntityWithId(id) {


    constructor(node: YamlNodeWithId): this(
        id = node.id,
        iconFile = node.string("iconFile")
    )


    override fun toString() = simpleToString(
        typeName = "Icon",
        name = null,
        "iconFile" to iconFile
    )


}


/**
 * Bundles together all the data loaded from the SDE.
 */
@Suppress("MemberVisibilityCanBePrivate")
internal class EveData(
    val metaGroups: List<MetaGroup>,
    val marketGroups: List<MarketGroup>,
    val races: List<Race>,
    val categories: List<Category>,
    val groups: List<Group>,
    val types: List<Type>,
    val dogmaTypes: List<DogmaType>,
    val dogmaEffects: List<DogmaEffect>,
    val dogmaAttributes: List<DogmaAttribute>,
    val mutaplasmids: List<Mutaplasmid>,
    val abyssalNameToReplacementTypeId: Map<String, Int>,
    val icons: List<IconInfo>
) {

    private val metaGroupsByName = metaGroups.associateBy { it.name }
    private val marketGroupById = marketGroups.associateBy { it.id }
    private val categoryByName = categories.associateBy { it.name }
    private val categoryById = categories.associateBy { it.id }
    private val groupById = groups.associateBy { it.id }
    private val groupByName = groups.associateBy { it.name }
    val typeById = types.associateBy { it.id }
    val dogmaTypeById = dogmaTypes.associateBy { it.id }
    private val dogmaEffectById = dogmaEffects.associateBy { it.id }
    private val dogmaEffectByName = dogmaEffects.associateBy { it.name }
    private val dogmaAttributeById = dogmaAttributes.associateBy { it.id }
    private val dogmaAttributeByName = dogmaAttributes.associateBy { it.name }
    val iconInfoById = icons.associateBy { it.id }

    val knownMetaGroups = KnownMetaGroups()
    val knownCategories = KnownCategories()
    val knownGroups = KnownGroups()
    val knownTypes = KnownTypes()
    val knownAttributes = KnownAttributes()
    val knownEffects = KnownEffects()
    val knownMarketGroups = KnownMarketGroups()


    /**
     * Returns the [MetaGroup] with the given name; throws [InvalidSdeException] if none found.
     */
    fun metaGroup(name: String) = metaGroupsByName[name] ?: throw InvalidSdeException("Missing '$name' meta group")


    /**
     * Returns the [Type] with the given id.
     */
    fun type(id: Int) = typeById[id] ?: throw InvalidSdeException("Missing type with id $id")


    /**
     * Returns all the [Type]s in the given categories.
     */
    fun typesInCategories(categories: Collection<Category>): Collection<Type> {
        val categoryIds = categories.map { it.id}.toSet()
        val groupIds = groups.filter { it.categoryId in categoryIds }.map { it.id }.toSet()
        return types.filter { it.groupId in groupIds }
    }


    /**
     * Returns all the [Type]s in the given categories.
     */
    fun typesIn(vararg category: Category): Collection<Type> = typesInCategories(category.toList())


    /**
     * Returns all the [Type]s in the given groups.
     */
    fun typesInGroups(groups: Collection<Group>): Collection<Type> {
        val groupIds = groups.map { it.id }.toSet()
        return types.filter { it.groupId in groupIds }
    }


    /**
     * Returns all the [Type]s in the given group.
     */
    fun typesIn(vararg group: Group): Collection<Type> = typesInGroups(group.toList())


    /**
     * Returns the [Category] with the given name; throws [InvalidSdeException] if none found.
     */
    fun category(name: String) = categoryByName[name] ?: throw InvalidSdeException("Missing '$name' category")


    /**
     * Returns the [Category] with the given id; throws [InvalidSdeException] if none found.
     */
    fun category(id: Int) = categoryById[id] ?: throw InvalidSdeException("Missing category with id $id")


    /**
     * Returns the [Group] with the given id; throws [InvalidSdeException] if none found.
     */
    fun group(id: Int) = groupById[id] ?: throw InvalidSdeException("Missing group with id $id")


    /**
     * Returns the [Group] with the given name; throws [InvalidSdeException] if none found.
     */
    fun group(name: String) = groupByName[name] ?: throw InvalidSdeException("Missing '$name' group")


    /**
     * Returns the [DogmaType] with the given id; throws [InvalidSdeException] if none found.
     */
    fun dogmaType(id: Int) = dogmaTypeById[id] ?: throw InvalidSdeException("Missing dogma type with id $id")


    /**
     * Returns the [DogmaType] corresponding to the given [Type]; throws [InvalidSdeException] if none found.
     */
    fun dogmaType(type: Type) = dogmaType(type.id)


    /**
     * Returns the [DogmaEffect] with the given id; throws [InvalidSdeException] if none found.
     */
    fun dogmaEffect(id: Int) =
        dogmaEffectById[id] ?: throw InvalidSdeException("Missing dogma effect with id $id")


    /**
     * Returns the list of [DogmaEffect]s referenced by the given [DogmaEffectRef]s.
     */
    fun dogmaEffects(refs: Collection<DogmaEffectRef>) = refs.map { dogmaEffect(it.effectId) }


    /**
     * Returns the [DogmaAttribute] with the given id; throws [InvalidSdeException] if none found.
     */
    fun dogmaAttribute(id: Int) =
        dogmaAttributeById[id] ?: throw InvalidSdeException("Missing dogma attribute with id $id")


    /**
     * Returns the [DogmaAttribute] with the given name; throws [InvalidSdeException] if none found.
     */
    fun dogmaAttribute(name: String) =
        dogmaAttributeByName[name] ?: throw InvalidSdeException("Missing '$name' dogma attribute")


    /**
     * Returns the [DogmaEffect] with the given name; throws [InvalidSdeException] if none found.
     */
    fun dogmaEffect(name: String) =
        dogmaEffectByName[name] ?: throw InvalidSdeException("Missing '$name' dogma effect")


    /**
     * Attributes we're referencing directly.
     */
    inner class KnownAttributes{


        @Suppress("SameParameterValue")
        private fun indexedAttributes(nameFormat: String): List<DogmaAttribute>{
            return generateSequence(1){ it + 1 }
                .map { index -> nameFormat.format(index) }
                .map { name -> dogmaAttributeByName[name] }
                .takeWhile { it != null }
                .filterNotNull()
                .toList()
        }


        private fun damageTypeAttributes(nameFormat: String, capitalizeFirstLetter: Boolean): List<DogmaAttribute>{
            return listOf("em", "thermal", "kinetic", "explosive")
                .map { if (capitalizeFirstLetter) it.replaceFirstChar(Char::uppercaseChar) else it }
                .map { nameFormat.format(Locale.ROOT, it) }
                .map { dogmaAttribute(it) }
        }


        private fun sensorTypeAttributes(nameFormat: String): List<DogmaAttribute>{
            return listOf("radar", "magnetometric", "gravimetric", "ladar")
                .map { it.replaceFirstChar(Char::uppercaseChar) }
                .map { nameFormat.format(Locale.ROOT, it) }
                .map { dogmaAttribute(it) }
        }


        val capacity = dogmaAttribute("capacity")
        val mass = dogmaAttribute("mass")
        val radius = dogmaAttribute("radius")
        val metaGroupId = dogmaAttribute("metaGroupID")

        val techLevel = dogmaAttribute("techLevel")
        val metaLevelOld = dogmaAttribute("metaLevelOld")

        val skillLevel = dogmaAttribute("skillLevel")

        val shieldResonance = damageTypeAttributes("shield%sDamageResonance", capitalizeFirstLetter = true)
        val armorResonance = damageTypeAttributes("armor%sDamageResonance", capitalizeFirstLetter = true)
        val structureResonance = damageTypeAttributes("%sDamageResonance", capitalizeFirstLetter = false)
        // These are used in Damage Control modules to specify the bonus to hull resistances
        val hullDamageResonance = damageTypeAttributes("hull%sDamageResonance", capitalizeFirstLetter = true)

        val shieldHp = dogmaAttribute("shieldCapacity")
        val shieldHpBonus = dogmaAttribute("shieldHpBonus")
        val armorHp = dogmaAttribute("armorHP")
        val armorHpBonus = dogmaAttribute("armorHpBonus")
        val duration = dogmaAttribute("duration")
        val capacitorNeed = dogmaAttribute("capacitorNeed")

        val calibration = dogmaAttribute("upgradeCapacity")
        val turretHardpoints = dogmaAttribute("turretSlotsLeft")
        val launcherHardpoints = dogmaAttribute("launcherSlotsLeft")
        val rigSlots = dogmaAttribute("rigSlots")
        val wrongRigSlots = dogmaAttribute("upgradeSlotsLeft")
        val highSlots = dogmaAttribute("hiSlots")
        val medSlots = dogmaAttribute("medSlots")
        val lowSlots = dogmaAttribute("lowSlots")
        val scanResolution = dogmaAttribute("scanResolution")
        val maxTargetRange = dogmaAttribute("maxTargetRange")
        val droneCapacity = dogmaAttribute("droneCapacity")
        val droneBandwidth = dogmaAttribute("droneBandwidth")
        val massAddition = dogmaAttribute("massAddition")
        val energyWarfareResistance = dogmaAttribute("energyWarfareResistance")
        val weaponDisruptionResistance = dogmaAttribute("weaponDisruptionResistance")
        val sensorDampenerResistance = dogmaAttribute("sensorDampenerResistance")
        val targetPainterResistance = dogmaAttribute("targetPainterResistance")
        val ecmResistance = dogmaAttribute("ECMResistance")
        val stasisWebifierResistance = dogmaAttribute("stasisWebifierResistance")
        val remoteRepairImpedance = dogmaAttribute("remoteRepairImpedance")
        val remoteAssistanceImpedance = dogmaAttribute("remoteAssistanceImpedance")
        val signatureRadius = dogmaAttribute("signatureRadius")
        val signatureRadiusBonus = dogmaAttribute("signatureRadiusBonus")
        val speedFactor = dogmaAttribute("speedFactor")
        val agility = dogmaAttribute("agility")
        val speedBoostFactor = dogmaAttribute("speedBoostFactor")
        val signatureRadiusBonusPercent = dogmaAttribute("signatureRadiusBonusPercent")  // MJD and MFJG
        val resistanceKiller = dogmaAttribute("resistanceKiller")

        val maxRange = dogmaAttribute("maxRange")
        val falloff = dogmaAttribute("falloff")
        val falloffEffectiveness = dogmaAttribute("falloffEffectiveness")

        val maxTargetRangeBonus = dogmaAttribute("maxTargetRangeBonus")
        val scanResolutionBonus = dogmaAttribute("scanResolutionBonus")

        val implantVelocityBonus = dogmaAttribute("implantBonusVelocity")  // Used by overdrives, nanofibers etc., in addition to implants

        val maxRangeBonus = dogmaAttribute("maxRangeBonus")
        val falloffBonus = dogmaAttribute("falloffBonus")
        val trackingSpeedBonus = dogmaAttribute("trackingSpeedBonus")
        val missileVelocityBonus = dogmaAttribute("missileVelocityBonus")
        val explosionDelayBonus = dogmaAttribute("explosionDelayBonus")
        val aoeVelocityBonus = dogmaAttribute("aoeVelocityBonus")
        val aoeCloudSizeBonus = dogmaAttribute("aoeCloudSizeBonus")
        val scanStrengthBonus = sensorTypeAttributes("scan%sStrengthBonus")
        val scanStrength = sensorTypeAttributes("scan%sStrength")

        val rigSize = dogmaAttribute("rigSize")

        val canFitShipGroups = indexedAttributes("canFitShipGroup%02d")

        val chargeGroups = indexedAttributes("chargeGroup%d")
        val damage = damageTypeAttributes("%sDamage", capitalizeFirstLetter = false)  // Damage done by ammo
        val damageMultiplier = dogmaAttribute("damageMultiplier")  // Turret/drone attribute
        val missileDamageMultiplier = dogmaAttribute("missileDamageMultiplier")  // Missile/Bomb launcher attribute
        val damageMultiplierBonusPerCycle = dogmaAttribute("damageMultiplierBonusPerCycle")  // Entropic disintegrators
        val damageMultiplierBonusMax = dogmaAttribute("damageMultiplierBonusMax") // Entropic disintegrators
        val repairMultiplierBonusPerCycle = dogmaAttribute("repairMultiplierBonusPerCycle")  // Mutadaptive repairers
        val repairMultiplierBonusMax = dogmaAttribute("repairMultiplierBonusMax")  // Mutadaptive repairers

        val armorDamageAmount = dogmaAttribute("armorDamageAmount")  // Armor repairers (including remote)

        val damageMultiplierBonus = dogmaAttribute("damageMultiplierBonus")
        val rofBonus = dogmaAttribute("rofBonus")

        val speed = dogmaAttribute("speed")  // This is actually a module duration attribute

        // The capacitor increase provided by Cap Booster charges
        val capacitorBonus = dogmaAttribute("capacitorBonus")
        val capRechargeBonus = dogmaAttribute("capRechargeBonus")
        val capRechargeRate = dogmaAttribute("rechargeRate")  // Capacitor recharge rate

        val maxActiveDrones = dogmaAttribute("maxActiveDrones")  // CharacterType attribute

        // The fixup attribute whose value is the id of the item's duration attribute
        val durationAttributeId by lazy { dogmaAttribute(FixupAttrs.durationAttributeId.name) }

        val maxVelocity = dogmaAttribute("maxVelocity")  // Missile, ship, drone attribute
        val speedLimit = dogmaAttribute("speedLimit")  // Ship attribute
        val flightTime = dogmaAttribute("explosionDelay")  // Missile attribute
        val explosionRadius = dogmaAttribute("aoeCloudSize")  // Missile attribute
        val explosionVelocity = dogmaAttribute("aoeVelocity")  // Missile attribute

        val chargeSize = dogmaAttribute("chargeSize")

        val maxGroupActive = dogmaAttribute("maxGroupActive")

        val baseWarpSpeed = dogmaAttribute("baseWarpSpeed")
        val warpCapacitorNeed = dogmaAttribute("warpCapacitorNeed")

        val warfareBuffIds = indexedAttributes("warfareBuff%dID")
        val warfareBuffValues = indexedAttributes("warfareBuff%dValue")

        val implantness = dogmaAttribute("implantness")  // Marks implants, and specifies the implant slot
        val boosterness = dogmaAttribute("boosterness")  // Marks boosters, and specifies the booster slot
        val boosterDuration = dogmaAttribute("boosterDuration")
        val boosterLastInjectionDatetime = dogmaAttribute("boosterLastInjectionDatetime")

        // Subsystems
        val highSlotModifier = dogmaAttribute("hiSlotModifier")
        val medSlotModifier = dogmaAttribute("medSlotModifier")
        val lowSlotModifier = dogmaAttribute("lowSlotModifier")
        val turretHardPointModifier = dogmaAttribute("turretHardPointModifier")
        val launcherHardPointModifier = dogmaAttribute("launcherHardPointModifier")

        // Environment effects
        val structureResistanceBonus = damageTypeAttributes("%sDamageResistanceBonus", capitalizeFirstLetter = false)
        val armorResistanceBonus = damageTypeAttributes("armor%sDamageResistanceBonus", capitalizeFirstLetter = true)
        val shieldResistanceBonus = damageTypeAttributes("shield%sDamageResistanceBonus", capitalizeFirstLetter = true)

        // Ship security status effects
        val inverseCappedSecStatus = dogmaAttribute("inverseCappedSecStatus")
    }


    /**
     * Effects we're referencing directly.
     */
    inner class KnownEffects{
        val highSlot = dogmaEffect("hiPower")
        val medSlot = dogmaEffect("medPower")
        val lowSlot = dogmaEffect("loPower")
        val rigSlot = dogmaEffect("rigSlot")
        val online = dogmaEffect("online")

        val moduleBonusMicrowarpdrive = dogmaEffect("moduleBonusMicrowarpdrive")
        val moduleBonusAfterburner = dogmaEffect("moduleBonusAfterburner")
        val microJumpDrive = dogmaEffect("microJumpDrive")
        val microJumpPortalDrive = dogmaEffect("microJumpPortalDrive")

        val emergencyHullEnergizer = dogmaEffect("emergencyHullEnergizer")

        val useMissiles = dogmaEffect("useMissiles")  // Empty effect that marks missile and bomb launchers
        val targetAttack = dogmaEffect("targetAttack")  // Empty effect that marks energy weapons and drones
        val projectileFired = dogmaEffect("projectileFired") // Empty effect that marks projectile and hybrid turrets
        val chainLightning = dogmaEffect("ChainLightning") // Empty effect that marks Vorton projectors
        val targetDisintegratorAttack = dogmaEffect("targetDisintegratorAttack")  // Empty effect that marks entropic disintegrators
        val mutadaptiveArmorRepairer = dogmaEffect("ShipModuleRemoteArmorMutadaptiveRepairer")  // Empty effect that marks mutadaptive remote repairers
        val empWave = dogmaEffect("empWave")  // An empty effect that appears to mark smartbombs
        val barrage = dogmaEffect("barrage")  // An empty effect on projectile turrets that duplicates projectileFired and doesn't do anything
        val doomsdayBeamDOT = dogmaEffect("doomsdayBeamDOT")  // Doomsdays
        val doomsdaySlash = dogmaEffect("doomsdaySlash")  // Reapers
        val debuffLance = dogmaEffect("debuffLance")  // Lances
        val doomsdayHOG = dogmaEffect("doomsdayHOG")  // Gravitational Transportation Field Oscillator

        val turretFitted = dogmaEffect("turretFitted")  // An empty effect marking a module as taking a turret hardpoint
        val launcherFitted = dogmaEffect("launcherFitted")  // An empty effect marking a module as taking a missile launcher hardpoint

        val sensorBoosterActivePercentage = dogmaEffect("sensorBoosterActivePercentage")  // Sensor boosters
        val gunneryMaxRangeFalloffTrackingSpeedBonus = dogmaEffect("gunneryMaxRangeFalloffTrackingSpeedBonus")  // Tracking Computer

        // Effect that boosts sensor strength based on these attributes:
        // scanGravimetricStrengthPercent, scanLadarStrengthPercent, scanMagnetometricStrengthPercent, scanRadarStrengthPercent
        // The effect doesn't appear to be used anywhere in the SDE, but we're stealing it for the sensor boosting
        // effect by entosis links.
        val scanStrengthBonusPercentActivate = dogmaEffect("scanStrengthBonusPercentActivate")

        val mercoxitCrystalBonus = dogmaEffect("mercoxitCrystalBonus")

        val adaptiveArmorHardener = dogmaEffect("adaptiveArmorHardener")  // Reactive Armor Hardener

        // Missile explosion radius penalty
        val boosterMissileExplosionCloudPenaltyFixed = dogmaEffect("boosterMissileExplosionCloudPenaltyFixed")

        // Subsystems
        val slotModifier = dogmaEffect("slotModifier")
        val hardPointModifier = dogmaEffect("hardPointModifierEffect")

        // Remote effects
        val remoteWebifierFalloff = dogmaEffect("remoteWebifierFalloff")  // Stasis webifiers, grapplers
        val remoteWebifierEntity = dogmaEffect("remoteWebifierEntity")  // SW- drones
        val remoteSensorDampFalloff = dogmaEffect("remoteSensorDampFalloff")  // Remote sensor dampeners
        val remoteSensorDampEntity = dogmaEffect("remoteSensorDampEntity")  // SD- drones
        val remoteTargetPaintFalloff = dogmaEffect("remoteTargetPaintFalloff")  // Target painters
        val remoteTargetPaintEntity = dogmaEffect("remoteTargetPaintEntity")  // TP- drones
        val shipModuleTrackingDisruptor = dogmaEffect("shipModuleTrackingDisruptor")  // Tracking disruptors
        val npcEntityWeaponDisruptor = dogmaEffect("npcEntityWeaponDisruptor")  // TD- drones
        val shipModuleGuidanceDisruptor = dogmaEffect("shipModuleGuidanceDisruptor")  // Guidance disruptors
        val remoteSensorBoostFalloff = dogmaEffect("remoteSensorBoostFalloff")  // Remote sensor boosters
        val shipModuleRemoteTrackingComputer = dogmaEffect("shipModuleRemoteTrackingComputer")  // Remote Tracking Comp

        // Warp Disruption Field Generator modules
        val maxRangeHiddenPreAssignmentWarpScrambleRange = dogmaEffect("maxRangeHiddenPreAssignmentWarpScrambleRange")

        // Core Defense Operation Solidifier rigs
        val shieldBoosterDurationBonusShieldSkills = dogmaEffect("shieldBoosterDurationBonusShieldSkills")

        val moduleBonusBastionModule = dogmaEffect("moduleBonusBastionModule")  // Bastion
    }


    /**
     * Meta groups we're referencing directly.
     */
    inner class KnownMetaGroups{
        val tech1 = metaGroup("Tech I")
    }


    /**
     * Categories we're referencing directly.
     */
    inner class KnownCategories{
        val ship = category("Ship")
        val module = category("Module")
        val charge = category("Charge")
        val skill = category("Skill")
        val drone = category("Drone")
        val implant = category("Implant")  // Includes boosters too :facepalm:
        val subsystem = category("Subsystem")
        val material = category("Material")
        val deployable = category("Deployable")
        val warfareBuffs by lazy { category(FixupCategories.warfareBuff.id) }
        val tacticalMode by lazy { category(FixupCategories.tacticalMode.id) }

        val all: Collection<Category> by lazy{
            setOf(ship, module, charge, skill, drone, implant, subsystem, material, warfareBuffs, tacticalMode)
        }
    }


    /**
     * Groups we're referencing directly.
     */
    inner class KnownGroups{

        val capacitorBooster = group("Capacitor Booster")
        val ancillaryArmorRepairer = group("Ancillary Armor Repairer")
        val ancillaryRemoteArmorRepairer = group("Ancillary Remote Armor Repairer")
        val ancillaryShieldBooster = group("Ancillary Shield Booster")
        val ancillaryRemoteShieldBooster = group("Ancillary Remote Shield Booster")
        val missileSkills = group("Missiles")
        val covertOpsShips = group("Covert Ops")
        val entosisLink = group("Entosis Link")
        val scanProbeLauncher = group("Scan Probe Launcher")
        val interdictionSphereLauncher = group("Interdiction Sphere Launcher")
        val surveyProbeLauncher = group("Survey Probe Launcher")
        val interdictionProbe = group("Interdiction Probe")
        val scannerProbe = group("Scanner Probe")
        val surveyProbe = group("Survey Probe")
        val commandBursts = group("Command Burst")
        val shieldCommandBurstCharges = group("Shield Command Burst Charges")
        val armorCommandBurstCharges = group("Armor Command Burst Charges")
        val skirmishCommandBurstCharges = group("Skirmish Command Burst Charges")
        val informationCommandBurstCharges = group("Information Command Burst Charges")
        val miningForemanBurstCharges = group("Mining Foreman Burst Charges")
        val ecm = group("ECM")
        val warpScrambler = group("Warp Scrambler")
        val stasisWeb = group("Stasis Web")
        val propulsionModule = group("Propulsion Module")
        val projectileWeapon = group("Projectile Weapon")
        val energyNosferatu = group("Energy Nosferatu")
        val shipModifiers = group("Ship Modifiers")  // Tactical modes
        val tacticalDestroyers = group("Tactical Destroyer")
        val jumpFilaments = group("Jump Filaments")
        val triglavianSpaceFilaments = group("Triglavian Space Filaments")

        // Environments
        val effectBeacon = group("Effect Beacon")
        val abyssalEnvironment = group("Abyssal Environment")
    }


    /**
     * [Type]s we're referencing directly.
     */
    inner class KnownTypes{

        val warfareBuffs: Type by lazy{ type(FixupTypes.warfareBuffs.id) }

        val armorCommandBurst = type(42526)
        val shieldCommandBurst = type(42529)
        val informationCommandBurst = type(42527)
        val skirmishCommandBurst = type(42530)
        val miningForemanBurst = type(42528)

        val shieldOperation = type(3416)
        val shieldEmissionSystems = type(3422)
        val capitalShieldEmissionSystems = type(24571)

        val repairSystems = type(3393)
        val remoteArmorRepairSystems = type(16069)
        val capitalRemoteArmorRepairSystems = type(24568)

        val sensorLinking = type(3433)
        val targetPainting = type(19921)
        val weaponDisruption = type(3434)

        val missileLauncherOperation = type(3319)
        val gunnery = type(3300)

    }


    /**
     * Market groups we're referencing directly.
     */
    inner class KnownMarketGroups {

        val stasisGrapplers = marketGroupById[2154]!!
        val interdictionSphereLaunchers = marketGroupById[1937]
        val warpDisruptionFieldGenerators = marketGroupById[1085]
        val warpScramblers = marketGroupById[1936]

    }



    companion object{


        /**
         * The id of the "Character" type.
         */
        const val CHARACTER_TYPE_ID = 1373


        /**
         * Loads the [EveData] from the files in the given (extracted SDE) directory.
         */
        fun load(file: File): EveData = ZipFile(file).use { zipFile ->
            val fsdDirName = "fsd"

            val marketGroups = runPrintingMessage("Loading market groups...") {
                loadEntities(zipFile, "${fsdDirName}/marketGroups.yaml", ::MarketGroup)
            }

            val metaGroups = runPrintingMessage("Loading meta groups...") {
                loadEntities(zipFile, "${fsdDirName}/metaGroups.yaml", ::MetaGroup)
            }

            val races = runPrintingMessage("Loading races...") {
                loadEntities(zipFile, "${fsdDirName}/races.yaml", ::Race)
            }

            val categories = runPrintingMessage("Loading categories...") {
                loadEntities(zipFile, "${fsdDirName}/categories.yaml", ::Category)
            }

            val groups = runPrintingMessage("Loading groups...") {
                loadEntities(zipFile, "${fsdDirName}/groups.yaml", ::Group)
            }

            val types = runPrintingMessage("Loading types...") {
                loadEntities(zipFile, "${fsdDirName}/types.yaml", ::Type)
            }

            val dogmaTypes = runPrintingMessage("Loading dogma types...") {
                loadEntities(zipFile, "${fsdDirName}/typeDogma.yaml", ::DogmaType)
            }

            val dogmaEffects = runPrintingMessage("Loading dogma effects...") {
                loadEntities(zipFile, "${fsdDirName}/dogmaEffects.yaml", ::DogmaEffect)
            }

            val dogmaAttributes = runPrintingMessage("Loading dogma attributes...") {
                loadEntities(zipFile, "${fsdDirName}/dogmaAttributes.yaml", ::DogmaAttribute)
            }

            val icons = runPrintingMessage("Loading icon names...") {
                loadEntities(zipFile, "${fsdDirName}/iconIDs.yaml", ::IconInfo)
            }

            return EveData(
                marketGroups = marketGroups,
                metaGroups = metaGroups,
                races = races,
                categories = categories,
                groups = groups,
                types = types,
                dogmaTypes = dogmaTypes,
                dogmaEffects = dogmaEffects,
                dogmaAttributes = dogmaAttributes,
                mutaplasmids = Mutaplasmids,
                abyssalNameToReplacementTypeId = emptyMap(),
                icons = icons
            )
        }


    }


}


/**
 * The [Yaml] instance we use to parse YAML.
 */
private val yaml = Yaml(object: SafeConstructor(LoaderOptions()){

    // Overriding these to avoid the call to newInstance significantly improves the performance, as newInstance
    // with note.type == Object always fails anyway.

    override fun newMap(node: MappingNode): Map<Any?, Any?>? {
        return createDefaultMap(node.value.size)
    }

    override fun newSet(node: CollectionNode<*>): Set<Any?>? {
        return createDefaultSet(node.value.size)
    }

    override fun newList(node: SequenceNode): List<Any?>? {
        return createDefaultList(node.value.size)
    }

})


/**
 * Returns the entries in the given SDE YAML file as a sequence.
 * Each entry is expected to be a number (id) mapped to a YAML node:
 * ```
 * 123:
 *     prop1: ...
 *     prop2: ...
 * ```
 * We do this instead of passing the entire file to the YAML parser because if we do, it will load
 * the entire thing into memory.
 */
private fun nodesWithIds(zipFile: ZipFile, entryName: String): Sequence<YamlNodeWithId> {
    fun String.toYamlNode(): YamlNodeWithId{
        val itemById: Map<Int, Map<Any, Any>> = yaml.load(this)
        val entry = itemById.entries.first()
        return YamlNodeWithId(entry.key, entry.value)
    }

    return sequence {
        val buffer = StringBuilder()
        val entry = zipFile.getEntry(entryName) ?: error("No entry with name $entryName found")
        zipFile.getInputStream(entry).reader(Charsets.UTF_8).useLines { lineSequence ->
            for (line in lineSequence) {
                if (line.isNotEmpty() && line[0].isDigit() && buffer.isNotBlank()) {
                    yield(buffer.toString())
                    buffer.clear()
                }

                buffer.appendLine(line)
            }

            // Don't forget the last node
            if (buffer.isNotBlank())
                yield(buffer.toString())
        }
    }.map { it.toYamlNode() }
}


/**
 * Loads entries from the given SDE YAML file, converting them to entities via the given
 * constructor.
 */
private fun <T> loadEntities(
    zipFile: ZipFile,
    entryName: String,
    constructor: (YamlNodeWithId) -> T,
): List<T> {
    return nodesWithIds(zipFile, entryName)
        .map(constructor)
        .toList()
}
