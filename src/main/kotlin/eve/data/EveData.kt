package eve.data

import eve.data.AttributeModifier.*
import eve.data.Effect.Category
import eve.data.Effect.Condition
import eve.data.utils.UnsyncByteArrayInputStream
import eve.data.utils.ValueByEnum
import eve.data.utils.valueByEnum
import java.io.DataInputStream
import java.io.InputStream


/**
 * Wraps an input stream in a [DataInputStream].
 */
fun InputStream.data(): DataInputStream =
    if (this is DataInputStream) this else DataInputStream(this)


/**
 * Reads and returns an optional id of some entity.
 */
private fun DataInputStream.readOptionalId(): Int?{
    val id = readInt()
    return if (id == 0) null else id  // When there is no id, the value written is 0, as actual ids can't be 0.
}


/**
 * Reads and returns an id of some entity.
 */
private fun DataInputStream.readId(): Int = readInt()


/**
 * Reads a string.
 */
private fun DataInputStream.readString(): String = readUTF()


/**
 * Reads an optional string.
 */
private fun DataInputStream.readOptionalString(): String? {
    return if (readBoolean())
        readString()
    else
        null
}


/**
 * Reads a module's slot type.
 */
private fun DataInputStream.readModuleSlotType() = when (val value = readUnsignedByte()){
    1 -> ModuleSlotType.HIGH
    2 -> ModuleSlotType.MEDIUM
    3 -> ModuleSlotType.LOW
    4 -> ModuleSlotType.RIG
    else -> throw BadEveDataException("Unknown module slot type value: $value")
}


/**
 * Loads a list of items using the given function to load individual items.
 */
private inline fun <T> DataInputStream.readList(itemReader: DataInputStream.() -> T): List<T>{
    val count = readInt()
    val result = List(count) {
        itemReader.invoke(this)
    }
    return result
}


/**
 * Reads a single [MetaGroup].
 */
private fun DataInputStream.readMetaGroup() =
    MetaGroup(
        id = readId(),
        name = readString()
    )


/**
 * Reads a single [MarketGroup].
 */
private fun DataInputStream.readMarketGroup() =
    MarketGroup(
        id = readId(),
        name = readString(),
        iconId = readOptionalId(),
        parentGroupId = readOptionalId()
    )


/**
 * Reads a single [Race].
 */
private fun DataInputStream.readRace() =
    Race(
        id = readId(),
        name = readString()
    )


/**
 * Maps attribute unit ids to [AttributeUnit]s.
 */
private val AttributeUnitsById = mapOf(
    1 to AttributeUnit.RangeMeters,
    2 to AttributeUnit.Mass,
    3 to AttributeUnit.TimeSec,
    9 to AttributeUnit.VolumeM3,
    10 to AttributeUnit.VelocityMetersPerSec,
    11 to AttributeUnit.MaxVelocityMetersPerSec,
    101 to AttributeUnit.DurationMs,
    102 to AttributeUnit.ScanResolutionMm,
    104 to AttributeUnit.Multiplier1,
    105 to AttributeUnit.BonusPercentAdditive1,
    106 to AttributeUnit.CpuTf,
    107 to AttributeUnit.PowerMw,
    108 to AttributeUnit.Resonance,
    109 to AttributeUnit.Multiplier2,
    111 to AttributeUnit.Multiplier3,
    113 to AttributeUnit.Hitpoints,
    114 to AttributeUnit.CapacitorGj,
    115 to AttributeUnit.GroupId,
    116 to AttributeUnit.TypeId,
    117 to AttributeUnit.SizeCategory,
    119 to AttributeUnit.AttributeId,
    120 to AttributeUnit.Unitless,
    121 to AttributeUnit.BonusPercentAdditive2,
    122 to AttributeUnit.SlotAmount,
    124 to AttributeUnit.BonusPercentAdditive3,
    125 to AttributeUnit.ThrustNewtons,
    126 to AttributeUnit.JumpRangeLy,
    127 to AttributeUnit.AdditiveFraction,
    128 to AttributeUnit.Bandwidth,
    133 to AttributeUnit.Isk,
    135 to AttributeUnit.ScanRangeAu,
    136 to AttributeUnit.SlotNumber,
    137 to AttributeUnit.BooleanFlag,
    138 to AttributeUnit.FuelUnits,
    139 to AttributeUnit.BonusIntegerAdditive,
    140 to AttributeUnit.Level,
    141 to AttributeUnit.HardpointAmount,
    142 to AttributeUnit.Gender,
    143 to AttributeUnit.DateTime,
    144 to AttributeUnit.WarpSpeedAuPerSec,
    205 to AttributeUnit.WasteMultiplier,
)

/**
 * Reads a single dogma [Attribute].
 */
private fun DataInputStream.readAttribute(): Attribute<Double> {
    val id = readId()
    val name = readString()
    val displayName = readOptionalString()
    val unit = AttributeUnitsById[readUnsignedByte()]
    val flags = readUnsignedByte()
    val range = if (flags.isBitSet(3)) {
        val min = readDouble()
        val max = readDouble()
        min .. max
    } else null
    return Attribute.double(
        id = id,
        name = name,
        displayName = displayName,
        unit = unit,
        isStackingPenalized = flags.isBitSet(0),
        highIsGood = if (flags.isBitSet(1)) flags.isBitSet(2) else null,
        propertyRange = range
    )
}


/**
 * Reads an [Effect.Category].
 */
private fun DataInputStream.readEffectCategory(): Category = when (readUnsignedByte()){
    0 -> Category.ALWAYS
    1 -> Category.ACTIVE
    2 -> Category.PROJECTED
    4 -> Category.ONLINE
    5 -> Category.OVERLOADED
    else -> Category.UNHANDLED
}


/**
 * Reads an [Effect.Condition].
 */
private fun DataInputStream.readEffectCondition(): Condition?{
    if (readByte().toInt() == 0)
        return null

    return Condition(
        attributeId = readId(),
        attributeValue = readInt()
    )
}


/**
 * Reads a single dogma [Effect].
 */
private fun DataInputStream.readEffect() =
    Effect(
        id = readId(),
        name = readString(),
        category = readEffectCategory(),
        flags = EffectFlags(readUnsignedByte()),
        condition = readEffectCondition(),
        modifiers = readList(DataInputStream::readModifier),
    )


/**
 * Reads an [AttributeModifier.Operation].
 */
private fun DataInputStream.readModifierOperation() = when (val operation = readInt()){
    0 -> Operation.PRE_MULTIPLY
    2 -> Operation.ADD
    3 -> Operation.SUBTRACT
    4 -> Operation.POST_MULTIPLY
    5 -> Operation.POST_DIVIDE
    6 -> Operation.ADD_PERCENT
    7 -> Operation.SET
    9 -> Operation.UNHANDLED
    100 -> Operation.SET_MAX_ABS
    101 -> Operation.MULTIPLY_PERCENT
    102 -> Operation.COERCE_AT_LEAST
    103 -> Operation.COERCE_AT_MOST
    -255, -1 -> Operation.UNHANDLED
    else -> throw BadEveDataException("Unknown modifier operation: $operation")
}


/**
 * Reads an [AttributeModifier.AffectedItemType].
 */
private fun DataInputStream.readModifierAffectedItemType(): AffectedItemType = when (val value = read()) {
    0 -> AffectedItemType.NONE
    1 -> AffectedItemType.SELF
    2 -> AffectedItemType.SHIP
    3 -> AffectedItemType.CHARACTER
    4 -> AffectedItemType.MODULES
    5 -> AffectedItemType.IMPLANTS_BOOSTERS
    6 -> AffectedItemType.LAUNCHABLES
    7 -> AffectedItemType.LAUNCHER
    100 -> AffectedItemType.WARFARE_BUFFS
    else -> throw BadEveDataException("Unknown modifier affected item type: $value")
}


/**
 * Reads an [AttributeModifier.AffectedItemFilter].
 */
private fun DataInputStream.readModifierAffectedItemFilter(): AffectedItemFilter = when (val value = read()){
    1 -> AffectedItemFilter.ALL
    2 -> AffectedItemFilter.MATCH_REQUIRED_SKILL
    3 -> AffectedItemFilter.MATCH_GROUP
    else -> throw BadEveDataException("Unknown modifier affected item filter: $value")
}


/**
 * Reads a single dogma [AttributeModifier].
 */
private fun DataInputStream.readModifier() =
    AttributeModifier(
        modifiedAttributeId = readOptionalId(),
        modifyingAttributeId = readOptionalId(),
        attenuatingAttributeId = readOptionalId(),
        affectedItemType = readModifierAffectedItemType(),
        affectedItemFilter = readModifierAffectedItemFilter(),
        operation = readModifierOperation(),
        groupId = readOptionalId(),
        skillTypeId = readOptionalId(),
    )


/**
 * Reads a single dogma [AttributeValue].
 */
private fun DataInputStream.readAttributeValue() =
    AttributeValue(
        attributeId = readId(),
        value = readDouble()
    )


/**
 * Reads a list of effect references.
 */
private fun DataInputStream.readEffectReferences(): EffectReferences{
    val count = readInt()
    val effectIds = IntArray(count){
        readId()
    }
    return EffectReferences(effectIds)
}


/**
 * Reads a single [TypeCategory].
 */
private fun DataInputStream.readCategory() =
    TypeCategory(
        id = readId(),
        name = readString()
    )


/**
 * Reads a single [TypeGroup].
 */
private fun DataInputStream.readGroup(categories: TypeCategories) =
    TypeGroup(
        id = readId(),
        category = categories[readId()],
        name = readString()
    )


/**
 * Reads a single [Traits] object.
 */
private fun DataInputStream.readTraits(): Traits {

    fun DataInputStream.readUnit() = when (val unitId = read()) {
        1 -> BonusUnit.METERS
        2 -> BonusUnit.KILOGRAMS
        3 -> BonusUnit.MULTIPLY
        4 -> BonusUnit.PERCENT
        5 -> BonusUnit.NUMBER
        else -> throw BadEveDataException("Unknown unit id: $unitId")
    }

    fun DataInputStream.readBonus(): Bonus {
        val text = readString()
        val value = if (readBoolean()) {
            BonusValue(
                amount = readString(),
                unit = readUnit()
            )
        }
        else null
        return Bonus(
            text = text,
            value = value
        )
    }

    val perSkill = readList {
        val skillId = readId()
        val bonuses = readList(DataInputStream::readBonus)
        skillId to bonuses
    }.toMap()
    val role = readList(DataInputStream::readBonus)
    val misc = readList(DataInputStream::readBonus)

    return Traits(
        perSkill = perSkill,
        role = role,
        misc = misc
    )
}


/**
 * The context required to read an item type.
 */
private class ReadContext(
    val attributes: Attributes,
    val categories: TypeCategories,
    val groups: TypeGroups,
    val marketGroups: MarketGroups,
    val races: Races,
)


/**
 * Reads an [EveItemTypeData].
 */
private fun DataInputStream.readItemData(
    typeName: String,
    context: ReadContext,
    hasDogma: Boolean = true,
    hasTraits: Boolean = false,
    isIconRendering: (TypeGroup) -> Boolean = { false }
): EveItemTypeData{
    val itemId = readId()
    val group = context.groups[readId()]
    val name = readString()
    val description = readOptionalString()
    val volume = readDouble()
    val marketGroup = readOptionalId()?.let { context.marketGroups[it] }
    val race = readOptionalId()?.let { context.races[it] }
    val iconId = readOptionalId()
    val attributeValues = AttributeValues(
        if (hasDogma) readList(DataInputStream::readAttributeValue) else emptyList()
    )
    val effectReferences = if (hasDogma) readEffectReferences() else EffectReferences.EMPTY
    val traits = if (hasTraits) readTraits() else null

    return EveItemTypeData(
        itemId = itemId,
        group = group,
        name = name,
        description = description,
        volume = volume,
        marketGroup = marketGroup,
        race = race,
        iconId = iconId,
        isIconRendering = isIconRendering(group),
        traits = traits,
        attributeValues = attributeValues,
        effectReferences = effectReferences,
        typeName = typeName,
    )
}


/**
 * Reads the character type.
 */
private fun DataInputStream.readCharacterType(context: ReadContext): CharacterType {
    return CharacterType(
        attributes = context.attributes,
        typeData = readItemData("CharacterType", context)
    )
}


/**
 * Reads the warfare buffs type.
 */
private fun DataInputStream.readWarfareBuffsType(context: ReadContext): WarfareBuffsType {
    return WarfareBuffsType(
        attributes = context.attributes,
        typeData = readItemData("WarfareBuffs", context)
    )
}


/**
 * Reads a single [ShipType].
 */
private fun DataInputStream.readShipType(context: ReadContext): ShipType {
    return ShipType(
        attributes = context.attributes,
        typeData = readItemData("ShipType", context, hasTraits = true, isIconRendering = { true })
    )
}


/**
 * Reads a single [ModuleType].
 */
private fun DataInputStream.readModuleType(context: ReadContext): ModuleType {
    val itemData = readItemData("ModuleType", context)
    val variationParentTypeId = readId()
    val slotType = readModuleSlotType()
    val flags = ModuleFlags(readUnsignedByte())

    return ModuleType(
        attributes = context.attributes,
        typeData = itemData,
        slotType = slotType,
        flags = flags,
        variationParentTypeId = variationParentTypeId
    )
}


/**
 * Reads a single [ChargeType].
 */
private fun DataInputStream.readChargeType(context: ReadContext): ChargeType {
    return ChargeType(
        attributes = context.attributes,
        typeData = readItemData("ChargeType", context)
    )
}


/**
 * Reads a single [SkillType].
 */
private fun DataInputStream.readSkillType(context: ReadContext): SkillType {
    return SkillType(
        attributes = context.attributes,
        typeData = readItemData("SkillType", context)
    )
}


/**
 * Reads a single [DroneType].
 */
private fun DataInputStream.readDroneType(context: ReadContext): DroneType {
    return DroneType(
        attributes = context.attributes,
        typeData = readItemData("DroneType", context, isIconRendering = { true }),
        variationParentTypeId = readId()
    )
}


/**
 * Reads a single [ImplantType].
 */
private fun DataInputStream.readImplantType(context: ReadContext): ImplantType {
    val itemData = readItemData("ImplantType", context)
    val variationParentTypeId = readId()
    val slot = readByte().toInt()
    return ImplantType(
        attributes = context.attributes,
        typeData = itemData,
        variationParentTypeId = variationParentTypeId,
        slot = slot
    )
}


/**
 * Reads a single [BoosterType].
 */
private fun DataInputStream.readBoosterType(context: ReadContext): BoosterType {
    val itemData = readItemData("BoosterType", context)
    val variationParentTypeId = readId()
    val slot = readByte().toInt()
    val sideEffects = readList {
        BoosterType.SideEffect(
            penalizedAttribute = context.attributes[readId()],
            penalizingAttribute = context.attributes[readId()]
        )
    }

    return BoosterType(
        attributes = context.attributes,
        typeData = itemData,
        variationParentTypeId = variationParentTypeId,
        slot = slot,
        sideEffects = sideEffects
    )
}


/**
 * Reads a single [SubsystemType].
 */
private fun DataInputStream.readSubsystemType(context: ReadContext): SubsystemType {
    return SubsystemType(
        attributes = context.attributes,
        typeData = readItemData("SubsystemType", context)
    )
}


/**
 * Reads a single [TacticalModeType].
 */
private fun DataInputStream.readTacticalModeType(context: ReadContext): TacticalModeType {
    return TacticalModeType(
        attributes = context.attributes,
        typeData = readItemData("TacticalModeType", context)
    )
}


/**
 * Reads a single [EnvironmentType].
 */
private fun DataInputStream.readEnvironmentType(context: ReadContext): EnvironmentType {
    val itemData = readItemData(
        typeName = "EnvironmentType",
        context = context,
        isIconRendering = { true }
    )
    val variationParentTypeId = readId()

    return EnvironmentType(
        attributes = context.attributes,
        typeData = itemData,
        variationParentTypeId = variationParentTypeId
    )
}


/**
 * Reads a single [MaterialType].
 */
private fun DataInputStream.readMaterialType(context: ReadContext): MaterialType {
    return MaterialType(
        attributes = context.attributes,
        typeData = readItemData("MaterialType", context, hasDogma = false)
    )
}


/**
 * Reads a single [MiscItemType].
 */
private fun DataInputStream.readMiscItemType(context: ReadContext): MiscItemType {
    return MiscItemType(
        attributes = context.attributes,
        typeData = readItemData(
            typeName = "MiscItemType",
            context = context,
            isIconRendering = { group -> group.category == context.categories.deployable }
        )
    )
}


/**
 * Reads a single [Mutaplasmid].
 */
private fun DataInputStream.readMutaplasmid(@Suppress("UNUSED_PARAMETER") context: ReadContext): Mutaplasmid {

    fun DataInputStream.readMutation(): AttributeMutation {
        val attributeId = readId()
        val min = readDouble()
        val max = readDouble()
        val highIsGood = readUnsignedByte()
        return AttributeMutation(
            attributeId = attributeId,
            range = min .. max,
            highIsGood = when (highIsGood) {
                0 -> null
                1 -> true
                2 -> false
                else -> throw BadEveDataException("Unexpected `highIsGood` value: $highIsGood")
            }
        )
    }

    return Mutaplasmid(
        id = readId(),
        name = readString(),
        targetTypeIds = readList(DataInputStream::readId),
        attributeMutations = readList(DataInputStream::readMutation).associateBy { it.attributeId}
    )
}


/**
 * Reads the mapping from abyssal item names to their "replacement" type ids.
 */
private fun DataInputStream.readAbyssalItemReplacements(): Map<String, Int> {
    return readList {
        val itemName = readString()
        val replacementTypeId = readInt()
        Pair(itemName, replacementTypeId)
    }.toMap()
}


/**
 * The contents of the eve data file.
 */
class EveData internal constructor(
    val metaGroups: MetaGroups,
    val marketGroups: MarketGroups,
    val races: Races,
    val attributes: Attributes,
    val effects: Effects,
    val categories: TypeCategories,
    val groups: TypeGroups,
    val characterType: CharacterType,
    val warfareBuffsType: WarfareBuffsType,
    val skillTypes: List<SkillType>,
    val shipTypes: List<ShipType>,
    val moduleTypes: List<ModuleType>,
    val chargeTypes: List<ChargeType>,
    val droneTypes: List<DroneType>,
    implantTypes: List<ImplantType>,
    val boosterTypes: List<BoosterType>,
    val subsystemTypes: List<SubsystemType>,
    val tacticalModeTypes: List<TacticalModeType>,
    val environmentTypes: List<EnvironmentType>,
    val materialTypes: List<MaterialType>,
    val miscItemTypes: List<MiscItemType>,
    val mutaplasmids: List<Mutaplasmid>,
    val abyssalItemReplacementIds: Map<String, Int>
) {


    /**
     * All item types that can be placed into the cargohold.
     */
    val cargoItemTypes = listOf(
        skillTypes,
        shipTypes,
        moduleTypes,
        chargeTypes,
        droneTypes,
        implantTypes,
        boosterTypes,
        subsystemTypes,
        materialTypes,
        miscItemTypes
    ).flatten()


    /**
     * Maps all types that can be placed into the cargohold by their ids.
     */
    val cargoItemTypeById: Map<Int, EveItemType> = cargoItemTypes.associateByItemId()


    /**
     * Returns the cargo item with the given id.
     */
    fun cargoItemType(id: Int): EveItemType = cargoItemTypeById[id] 
        ?: throw IllegalArgumentException("No cargo item type with id $id is known")


    /**
     * Returns the cargo item type with the given id or `null` if no such cargo item type exists.
     */
    fun cargoItemTypeOrNull(id: Int): EveItemType? = cargoItemTypeById[id]


    /**
     * Maps all cargo item types by their names.
     */
    val cargoItemTypeByName: Map<String, EveItemType> by lazy { cargoItemTypes.associateByName() }


    /**
     * Returns the cargo item type with the given name.
     */
    fun cargoItemType(name: String): EveItemType = cargoItemTypeByName[name]
        ?: throw IllegalArgumentException("No cargo item type with name $name is known")


    /**
     * Returns the cargo item type with the given name or `null` if no such cargo item type exists.
     */
    fun cargoItemTypeOrNull(name: String): EveItemType? = cargoItemTypeByName[name]


    /**
     * Maps skill types by their ids.
     */
    val skillTypeById: Map<Int, SkillType> = skillTypes.associateByItemId()


    /**
     * Returns the skill type with the given id.
     */
    fun skillType(id: Int): SkillType = skillTypeById[id]
        ?: throw IllegalArgumentException("No skill type with id $id is known")


    /**
     * Returns the skill type with the given id or `null` if no skill type with the given id exists.
     */
    fun skillTypeOrNull(id: Int): SkillType? = skillTypeById[id]


    /**
     * Maps ship types by their item id.
     */
    val shipTypeById: Map<Int, ShipType> = shipTypes.associateByItemId()


    /**
     * Returns the ship type with the given id.
     */
    fun shipType(id: Int): ShipType = shipTypeById[id]
        ?: throw IllegalArgumentException("No ship type with id $id is known")


    /**
     * Returns the ship type with the given id or `null` if no such ship type exists.
     */
    fun shipTypeOrNull(id: Int): ShipType? = shipTypeById[id]


    /**
     * Maps ship types by their names.
     */
    val shipTypeByName: Map<String, ShipType> = shipTypes.associateByName()


    /**
     * Returns the ship type with the given name.
     */
    fun shipType(name: String): ShipType = shipTypeByName[name]
        ?: throw IllegalArgumentException("No ship type with name $name is known")


    /**
     * Returns the ship type with the given name or `null` if no such ship type exists.
     */
    fun shipTypeOrNull(name: String): ShipType? = shipTypeByName[name]


    /**
     * Maps modules types by their item id.
     */
    val moduleTypeById: Map<Int, ModuleType> = moduleTypes.associateByItemId()


    /**
     * Returns the module type with the given id.
     */
    fun moduleType(id: Int): ModuleType = moduleTypeById[id]
        ?: throw IllegalArgumentException("No module type with id $id is known")


    /**
     * Returns the module type with the given id or `null` if no such module type exists.
     */
    fun moduleTypeOrNull(id: Int): ModuleType? = moduleTypeById[id]


    /**
     * Maps modules types by their names.
     */
    val moduleTypeByName: Map<String, ModuleType> = moduleTypes.associateByName()


    /**
     * Returns the module type with the given name.
     */
    fun moduleType(name: String): ModuleType = moduleTypeByName[name]
        ?: throw IllegalArgumentException("No module type with name $name is known")


    /**
     * Returns the module type with the given name or `null` if no such module type exists.
     */
    fun moduleTypeOrNull(name: String): ModuleType? = moduleTypeByName[name]


    /**
     * Maps module variation groups by their [ModuleType.variationParentTypeId].
     */
    private val moduleTypesByVariationParentTypeId: Map<Int, Collection<ModuleType>> =
        moduleTypes.groupBy { it.variationParentTypeId }


    /**
     * Returns the module types with the given variation parent type id.
     */
    fun moduleTypesByVariationParentTypeId(id: Int): Collection<ModuleType> {
        return moduleTypesByVariationParentTypeId[id]
            ?: throw IllegalArgumentException("No module types with variation parent type id $id are known")
    }


    /**
     * Maps charge types by their item id.
     */
    val chargeTypeById: Map<Int, ChargeType> = chargeTypes.associateByItemId()


    /**
     * Returns the charge type with the given id.
     */
    fun chargeType(id: Int): ChargeType = chargeTypeById[id]
        ?: throw IllegalArgumentException("No charge type with id $id is known")


    /**
     * Returns the charge type with the given id or `null` if no such charge type exists.
     */
    fun chargeTypeOrNull(id: Int): ChargeType? = chargeTypeById[id]


    /**
     * Maps charge types by their name.
     */
    val chargeTypeByName: Map<String, ChargeType> = chargeTypes.associateByName()


    /**
     * Returns the charge type with the given name.
     */
    fun chargeType(name: String): ChargeType = chargeTypeByName[name]
        ?: throw IllegalArgumentException("No charge type with name $name is known")


    /**
     * Returns the charge type with the given name or `null` if no such charge type exists.
     */
    fun chargeTypeOrNull(name: String): ChargeType? = chargeTypeByName[name]


    /**
     * Maps charge type groups by their group id.
     */
    private val chargeTypesByGroupId: Map<Int, Collection<ChargeType>> = chargeTypes.groupBy { it.groupId }


    /**
     * Returns the charge types that can be loaded into the given module type.
     */
    fun chargesForModule(moduleType: ModuleType): Collection<ChargeType> {
        return moduleType.chargeGroupIds
            .flatMap { groupId ->
                chargeTypesByGroupId[groupId] ?: throw BadEveDataException("No charge group id $groupId found")
            }
            .filter {
                moduleType.canLoadCharge(it)
            }
    }


    /**
     * Maps drone types by their item id.
     */
    val droneTypeById: Map<Int, DroneType> = droneTypes.associateByItemId()


    /**
     * Returns the drone type with the given id.
     */
    fun droneType(id: Int): DroneType = droneTypeById[id]
        ?: throw IllegalArgumentException("No drone type with id $id is known")


    /**
     * Returns the drone type with the given id or `null` if no such drone type exists.
     */
    fun droneTypeOrNull(id: Int): DroneType? = droneTypeById[id]
    

    /**
     * Maps drone types by their name.
     */
    val droneTypeByName: Map<String, DroneType> by lazy { droneTypes.associateByName() }


    /**
     * Returns the drone type with the given name.
     */
    fun droneType(name: String): DroneType = droneTypeByName[name]
        ?: throw IllegalArgumentException("No drone type with name $name is known")


    /**
     * Returns the drone type with the given name or `null` if no such drone type exists.
     */
    fun droneTypeOrNull(name: String): DroneType? = droneTypeByName[name]


    /**
     * Maps drone types by their market group.
     */
    private val droneTypesByMarketGroup: Map<MarketGroup, Collection<DroneType>> = droneTypes
        .filter { it.marketGroup != null }
        .groupBy { it.marketGroup!! }


    /**
     * Returns the drone types with the given market group.
     */
    fun droneTypesByMarketGroup(marketGroup: MarketGroup): Collection<DroneType> {
        return droneTypesByMarketGroup[marketGroup]
            ?: throw IllegalArgumentException("No drones with market ground $marketGroup are known")
    }


    /**
     * Maps drone variation groups by their [DroneType.variationParentTypeId].
     */
    private val droneTypesByVariationParentTypeId: Map<Int, Collection<DroneType>> =
        droneTypes.groupBy { it.variationParentTypeId }


    /**
     * Returns the drone types with the given variation parent type id.
     */
    fun droneTypesByVariationParentTypeId(id: Int): Collection<DroneType> {
        return droneTypesByVariationParentTypeId[id]
            ?: throw IllegalArgumentException("No drone types with variation parent type id $id are known")
    }


    /**
     * Implant related information.
     */
    val implantTypes: ImplantTypes = ImplantTypes(implantTypes)
    
    
    /**
     * Maps booster types by their item id.
     */
    val boosterTypeById: Map<Int, BoosterType> = boosterTypes.associateByItemId()


    /**
     * Returns the booster type with the given id.
     */
    fun boosterType(id: Int): BoosterType = boosterTypeById[id]
        ?: throw IllegalArgumentException("No booster type with id $id is known")


    /**
     * Returns the booster type with the given id or `null` if no such booster type exists.
     */
    fun boosterTypeOrNull(id: Int): BoosterType? = boosterTypeById[id]
    
    
    /**
     * Maps booster types by their name.
     */
    val boosterTypeByName: Map<String, BoosterType> by lazy{ boosterTypes.associateByName() }


    /**
     * Returns the booster type with the given name.
     */
    fun boosterType(name: String): BoosterType = boosterTypeByName[name]
        ?: throw IllegalArgumentException("No booster type with name $name is known")


    /**
     * Returns the booster type with the given name or `null` if no such booster type exists.
     */
    fun boosterTypeOrNull(name: String): BoosterType? = boosterTypeByName[name]


    /**
     * Maps booster variation groups by their [BoosterType.variationParentTypeId].
     */
    private val boosterTypesByVariationParentTypeId: Map<Int, Collection<BoosterType>> =
        boosterTypes.groupBy { it.variationParentTypeId }


    /**
     * Returns the booster types with the given variation parent type id.
     */
    fun boosterTypesByVariationParentTypeId(id: Int): Collection<BoosterType> {
        return boosterTypesByVariationParentTypeId[id]
            ?: throw IllegalArgumentException("No booster types with variation parent type id $id are known")
    }
    
    
    /**
     * The number of booster slots.
     */
    val boosterSlotCount = boosterTypes.maxOf { it.slotIndex } + 1


    /**
     * Maps material types by their item id.
     */
    val materialTypeById: Map<Int, MaterialType> = materialTypes.associateByItemId()


    /**
     * Returns the material type with the given id.
     */
    fun materialType(id: Int): MaterialType = materialTypeById[id]
        ?: throw IllegalArgumentException("No material type with id $id is known")


    /**
     * Returns the material type with the given id or `null` if no such material type exists.
     */
    @Suppress("unused")
    fun materialTypeOrNull(id: Int): MaterialType? = materialTypeById[id]


    /**
     * Maps tactical mode types by their item id.
     */
    val tacticalModeTypeById: Map<Int, TacticalModeType> = tacticalModeTypes.associateByItemId()


    /**
     * Returns the tactical mode type with the given id.
     */
    @Suppress("unused")
    fun tacticalModeType(id: Int): TacticalModeType = tacticalModeTypeById[id]
        ?: throw IllegalArgumentException("No tactical mode type with id $id is known")


    /**
     * Returns the tactical mode type with the given id or `null` if no such tactical mode type exists.
     */
    fun tacticalModeTypeOrNull(id: Int): TacticalModeType? = tacticalModeTypeById[id]


    /**
     * Maps tactical mode types by the type of the ship they're used with.
     */
    val tacticalModeTypesByShipType: Map<ShipType, ValueByEnum<TacticalModeType.Kind, TacticalModeType>> =
        tacticalModeTypes
            .groupBy { shipTypeById[it.shipId]!! }
            .mapValues { (shipType, tacticalModes) ->
                valueByEnum { type ->
                    tacticalModes.find { it.kind == type } ?: error("Missing $type tactical mode for $shipType")
                }
            }


    /**
     * Returns the tactical mode types for the given ship type.
     */
    fun tacticalModeTypes(shipType: ShipType): ValueByEnum<TacticalModeType.Kind, TacticalModeType> {
        return tacticalModeTypesByShipType[shipType]
            ?: throw IllegalArgumentException("The ship type $shipType does not use tactical modes")
    }


    /**
     * Returns the tactical mode types for the given ship type or `null` if the ship type does not use tactical modes.
     */
    fun tacticalModeTypesOrNull(shipType: ShipType): ValueByEnum<TacticalModeType.Kind, TacticalModeType>? {
        return tacticalModeTypesByShipType[shipType]
    }


    /**
     * Maps subsystem types by their item id.
     */
    val subsystemTypeById: Map<Int, SubsystemType> = subsystemTypes.associateByItemId()


    /**
     * Returns the subsystem type with the given id.
     */
    fun subsystemType(id: Int): SubsystemType = subsystemTypeById[id]
        ?: throw IllegalArgumentException("No subsystem type with id $id is known")


    /**
     * Returns the subsystem type with the given id or `null` if no such subsystem type exists.
     */
    fun subsystemTypeOrNull(id: Int): SubsystemType? = subsystemTypeById[id]


    /**
     * Maps subsystem types by their name.
     */
    val subsystemTypeByName: Map<String, SubsystemType> by lazy { subsystemTypes.associateByName() }


    /**
     * Returns the subsystem type with the given name.
     */
    fun subsystemType(name: String): SubsystemType = subsystemTypeByName[name]
        ?: throw IllegalArgumentException("No subsystem type with name $name is known")


    /**
     * Returns the subsystem type with the given name or `null` if no such subsystem type exists.
     */
    fun subsystemTypeOrNull(name: String): SubsystemType? = subsystemTypeByName[name]


    /**
     * Maps subsystem types by the type of the ship they're used with.
     */
    val subsystemTypeByShipType: Map<ShipType, ValueByEnum<SubsystemType.Kind, List<SubsystemType>>> =
        subsystemTypes
            .groupBy { shipTypeById[it.shipId]!! }
            .mapValues { (_, subsystemTypes) ->
                valueByEnum { type ->
                    subsystemTypes.filter { it.kind == type }
                }
            }


    /**
     * Returns the subsystem types for the given ship type.
     */
    fun subsystemTypes(shipType: ShipType): ValueByEnum<SubsystemType.Kind, List<SubsystemType>> {
        return subsystemTypeByShipType[shipType]
            ?: throw IllegalArgumentException("The ship type $shipType does not use subsystems")
    }


    /**
     * Returns the subsystem types for the given ship type, or `null` if the ship type does not have subsystems.
     */
    fun subsystemTypeOrNull(shipType: ShipType): ValueByEnum<SubsystemType.Kind, List<SubsystemType>>? {
        return subsystemTypeByShipType[shipType]
    }


    /**
     * Maps environment types by their item id.
     */
    val environmentTypeById: Map<Int, EnvironmentType> = environmentTypes.associateByItemId()


    /**
     * Returns the environment type with the given id.
     */
    fun environmentType(id: Int): EnvironmentType = environmentTypeById[id]
        ?: throw IllegalArgumentException("No environment type with id $id is known")


    /**
     * Returns the environment type with the given id or `null` if no such booster type exists.
     */
    fun environmentTypeOrNull(id: Int): EnvironmentType? = environmentTypeById[id]


    /**
     * Maps environment types by their name.
     */
    val environmentTypeByName: Map<String, EnvironmentType> by lazy { environmentTypes.associateByName() }


    /**
     * Returns the environment type with the given name.
     */
    @Suppress("unused")
    fun environmentType(name: String): EnvironmentType = environmentTypeByName[name]
        ?: throw IllegalArgumentException("No environment type with name $name is known")


    /**
     * Returns the environment type with the given name or `null` if no such environment type exists.
     */
    fun environmentTypeOrNull(name: String): EnvironmentType? = environmentTypeByName[name]


    /**
     * Maps environment variation groups by their [EnvironmentType.variationParentTypeId].
     */
    private val environmentTypesByVariationParentTypeId: Map<Int, Collection<EnvironmentType>> =
        environmentTypes.groupBy { it.variationParentTypeId }


    /**
     * Returns the environment types with the given variation parent type id.
     */
    fun environmentTypesByVariationParentTypeId(id: Int): Collection<EnvironmentType> {
        return environmentTypesByVariationParentTypeId[id]
            ?: throw IllegalArgumentException("No environment types with variation parent type id $id are known")
    }


    /**
     * The abyssal item replacements.
     */
    private val abyssalItemReplacement: Map<String, ModuleOrDroneType> =
        abyssalItemReplacementIds.mapValues { (_, typeId) ->
            (moduleTypeById[typeId] ?: droneTypeById[typeId]) ?: error("Missing abyssal replacement type id $typeId")
        }


    /**
     * Returns the module or drone the abyssal item with the given name should be replaced with; `null` if no
     * replacement is known.
     */
    fun abyssalItemReplacement(name: String): ModuleOrDroneType? {
        return abyssalItemReplacement[name]
    }


    /**
     * The command burst module.
     */
    private val commandBurst = moduleType("Information Command Burst I")


    /**
     * Returns whether the given ship type can fit command bursts.
     */
    fun canFitCommandBursts(shipType: ShipType) = shipType.canFit(commandBurst)


    /**
     * Returns whether the given item type has any offensive effects.
     */
    fun isOffensive(itemType: EveItemType) = itemType.effectReferences.any {
        effects[it].isOffensive
    }


    /**
     * Returns whether the given item type has any assistive effects.
     */
    fun isAssistive(itemType: EveItemType) = itemType.effectReferences.any {
        effects[it].isAssistive
    }


    /**
     * A ship type suitable to be used in auxiliary fits.
     * It should have all the basic attributes, but with no special bonuses.
     */
    val auxiliaryShipType: ShipType by lazy {
        val attributeValues = listOf(
            attributes.mass to 10_000.0,
            attributes.highSlots to 8.0,
            attributes.medSlots to 8.0,
            attributes.lowSlots to 8.0,
            attributes.rigSlots to 3.0,
            attributes.shieldHp to 1.0,
            attributes.armorHp to 1.0,
            attributes.structureHp to 1.0,
            attributes.maxVelocity to 1.0,
            attributes.powerOutput to 1.0,
            attributes.cpuOutput to 1.0,
            attributes.calibration to 1.0,
            attributes.shieldRechargeTime to 1.0,
            attributes.capacitorCapacity to 1.0,
            attributes.capacitorRechargeTime to 1.0,
            attributes.launcherHardpoints to 8.0,
            attributes.turretHardpoints to 8.0,
            attributes.targetingRange to 1.0,
            attributes.maxLockedTargets to 1.0,
            attributes.droneCapacity to 100_000.0,
            attributes.droneBandwidth to 100_000.0,
            attributes.techLevel to 1.0,
            attributes.signatureRadius to 1.0,
            attributes.capacity to 1.0,
            attributes.inertiaModifier to 0.3,
            attributes.speedLimit to -1.0,
            attributes.propulsionModuleSpeedFactor to 1.0,
            attributes.propulsionModuleThrust to 0.0,
            attributes.isCapitalSize to 0.0,
            attributes.sensorStrength[SensorType.RADAR] to 1.0,
            attributes.sensorStrength[SensorType.GRAVIMETRIC] to 0.0,
            attributes.sensorStrength[SensorType.MAGNETOMETRIC] to 0.0,
            attributes.sensorStrength[SensorType.LADAR] to 0.0,
            attributes.shieldResonance[DamageType.EM] to 0.5,
            attributes.shieldResonance[DamageType.THERMAL] to 0.5,
            attributes.shieldResonance[DamageType.KINETIC] to 0.5,
            attributes.shieldResonance[DamageType.EXPLOSIVE] to 0.5,
            attributes.armorResonance[DamageType.EM] to 0.5,
            attributes.armorResonance[DamageType.THERMAL] to 0.5,
            attributes.armorResonance[DamageType.KINETIC] to 0.5,
            attributes.armorResonance[DamageType.EXPLOSIVE] to 0.5,
            attributes.structureResonance[DamageType.EM] to 0.5,
            attributes.structureResonance[DamageType.THERMAL] to 0.5,
            attributes.structureResonance[DamageType.KINETIC] to 0.5,
            attributes.structureResonance[DamageType.EXPLOSIVE] to 0.5,
        )
        ShipType(
            attributes = attributes,
            typeData = EveItemTypeData(
                itemId = -1,
                group = groups.battleship,
                name = "Auxiliary Ship",
                description = null,
                volume = 1.0,
                marketGroup = null,
                race = null,
                iconId = null,
                isIconRendering = true,
                attributeValues = AttributeValues(
                    attributeValues.map { (attribute, value) ->
                        AttributeValue(attribute.id, value)
                    }
                ),
                effectReferences = EffectReferences(IntArray(0)),
                typeName = "Auxiliary Ship",
            )
        )
    }


    /**
     * Maps mutaplasmids by their item id.
     */
    val mutaplasmidById: Map<Int, Mutaplasmid> = mutaplasmids.associateBy { it.id }


    /**
     * Returns the mutaplasmid with the given id.
     */
    fun mutaplasmid(id: Int): Mutaplasmid = mutaplasmidById[id]
        ?: throw IllegalArgumentException("No mutaplasmid with id $id is known")


    /**
     * Maps mutaplasmids by their name.
     */
    val mutaplasmidByName: Map<String, Mutaplasmid> by lazy { mutaplasmids.associateBy { it.name } }


    /**
     * Returns the mutaplasmid with the given name, or null if no such mutaplasmid exists.
     */
    fun mutaplasmidOrNull(name: String): Mutaplasmid? = mutaplasmidByName[name]


    /**
     * For each item type id, the mutaplasmids that can mutate the type.
     */
    val mutaplasmidsByTargetItemId: Map<Int, List<Mutaplasmid>> =
        mutableMapOf<Int, MutableList<Mutaplasmid>>()
            .also {
                for (mutaplasmid in mutaplasmids) {
                    for (targetItemType in mutaplasmid.targetTypeIds) {
                        it.getOrPut(targetItemType) { mutableListOf() }.add(mutaplasmid)
                    }
                }
            }
            .also {
                for (mutaplasmids in it.values) {
                    mutaplasmids.sort()
                }
            }


    /**
     * Returns the mutaplasmids that can mutate the given item type.
     */
    fun mutaplasmidsMutating(itemType: EveItemType): List<Mutaplasmid> {
        return mutaplasmidsByTargetItemId[itemType.itemId] ?: emptyList()
    }


    /**
     * Maps each [MarketGroup] to the item types in it.
     *
     * Note that this is computed lazily, as it takes a bit of time.
     */
    val itemTypesByMarketGroup: Map<MarketGroup, List<EveItemType>> by lazy {
        val itemLists = listOf(
            shipTypes,
            skillTypes,
            moduleTypes,
            chargeTypes,
            droneTypes,
            implantTypes,
            boosterTypes,
            subsystemTypes,
            materialTypes,
            miscItemTypes
        )
        buildMap<MarketGroup, MutableList<EveItemType>> {
            for (items in itemLists) {
                for (item in items) {
                    val marketGroup = item.marketGroup ?: continue
                    val list = getOrPut(marketGroup) { ArrayList(10) }
                    list.add(item)
                }
            }
        }
    }


    companion object {


        /**
         * Loads and returns the standard (built-in) eve data.
         * The data is written by `ImportEveData.kt`.
         */
        fun loadStandard(): EveData = EveData::class.java.getResourceAsStream("/eve_data.dat")
            .use {
                it!!.readAllBytes()
            }.let {
                load(UnsyncByteArrayInputStream(it))
            }


        /**
         * Loads and returns the eve data from the given input stream.
         * The data is written by `ImportEveData.kt`.
         */
        private fun load(inputStream: InputStream) = inputStream.data().let { input ->
            val metaGroups = MetaGroups(input.readList(DataInputStream::readMetaGroup))
            val marketGroups = MarketGroups(input.readList(DataInputStream::readMarketGroup))
            val races = Races(input.readList(DataInputStream::readRace))

            val attributes = Attributes(input.readList(DataInputStream::readAttribute))
            val effects = Effects(input.readList(DataInputStream::readEffect))

            val categories = TypeCategories(input.readList(DataInputStream::readCategory))
            val groups = TypeGroups(input.readList{
                readGroup(categories)
            })

            val readContext = ReadContext(
                attributes = attributes,
                categories = categories,
                groups = groups,
                marketGroups = marketGroups,
                races = races,
            )

            fun <T> DataInputStream.readListWithContext(itemReader: DataInputStream.(ReadContext) -> T) =
                readList { itemReader(this, readContext) }

            EveData(
                metaGroups = metaGroups,
                marketGroups = marketGroups,
                races = races,
                attributes = attributes,
                effects = effects,
                categories = categories,
                groups = groups,
                characterType = input.readCharacterType(readContext),
                warfareBuffsType = input.readWarfareBuffsType(readContext),
                skillTypes = input.readListWithContext(DataInputStream::readSkillType),
                shipTypes = input.readListWithContext(DataInputStream::readShipType),
                moduleTypes = input.readListWithContext(DataInputStream::readModuleType),
                chargeTypes = input.readListWithContext(DataInputStream::readChargeType),
                droneTypes = input.readListWithContext(DataInputStream::readDroneType),
                implantTypes = input.readListWithContext(DataInputStream::readImplantType),
                boosterTypes = input.readListWithContext(DataInputStream::readBoosterType),
                subsystemTypes = input.readListWithContext(DataInputStream::readSubsystemType),
                tacticalModeTypes = input.readListWithContext(DataInputStream::readTacticalModeType),
                environmentTypes = input.readListWithContext(DataInputStream::readEnvironmentType),
                materialTypes = input.readListWithContext(DataInputStream::readMaterialType),
                miscItemTypes = input.readListWithContext(DataInputStream::readMiscItemType),
                mutaplasmids = input.readListWithContext(DataInputStream::readMutaplasmid),
                abyssalItemReplacementIds = input.readAbyssalItemReplacements()
            ).also {
                if (input.read() != -1)
                    throw BadEveDataException("Bytes remaining after reading Eve data")
            }
        }


    }


}


/**
 * `OR`s `this` with a bit at the given index with the given value.
 */
private fun Int.orBit(index: Int, value: Boolean) = this or ((if (value) 1 else 0) shl index)


/**
 * Returns whether the bit at the given index is 1.
 */
private fun Int.isBitSet(index: Int) = (this and (1 shl index)) != 0


/**
 * The effect flags.
 */
@JvmInline
value class EffectFlags internal constructor(private val flags: Int) {


    /**
     * Creates an [EffectFlags] with the given flags.
     */
    constructor(
        isOffensive: Boolean = false,
        isAssistive: Boolean = false
    ): this(
        0
            .orBit(OFFENSIVE_BIT_INDEX, isOffensive)
            .orBit(ASSISTIVE_BIT_INDEX, isAssistive)
    )


    /**
     * Whether the effect is offensive.
     */
    val isOffensive: Boolean
        get() = flags.isBitSet(OFFENSIVE_BIT_INDEX)


    /**
     * Whether the effect is assistive.
     */
    val isAssistive: Boolean
        get() = flags.isBitSet(ASSISTIVE_BIT_INDEX)


    private companion object{

        private const val OFFENSIVE_BIT_INDEX = 0
        private const val ASSISTIVE_BIT_INDEX = 1

    }


}


/**
 * The module flags.
 */
@JvmInline
value class ModuleFlags internal constructor(private val flags: Int) {


    /**
     * Creates a [ModuleFlags] with the given flags.
     */
    constructor(
        isActivable: Boolean,
        isOverloadable: Boolean,
        isProjected: Boolean,
        isTurret: Boolean,
        isMissileLauncher: Boolean,
    ): this(
        0
            .orBit(ACTIVABLE_BIT_INDEX, isActivable)
            .orBit(OVERLOADABLE_BIT_INDEX, isOverloadable)
            .orBit(PROJECTED_BIT_INDEX, isProjected)
            .orBit(TAKES_TURRET_HARDPOINT_BIT_INDEX, isTurret)
            .orBit(TAKES_LAUNCHER_HARDPOINT_BIT_INDEX, isMissileLauncher)
    )


    /**
     * Whether the module can be active.
     */
    val isActivable: Boolean
        get() = flags.isBitSet(ACTIVABLE_BIT_INDEX)


    /**
     * Whether the module can be overloaded.
     */
    val isOverloadable: Boolean
        get() = flags.isBitSet(OVERLOADABLE_BIT_INDEX)


    /**
     * Whether this module has a projected effect.
     */
    val isProjected: Boolean
        get() = flags.isBitSet(PROJECTED_BIT_INDEX)


    /**
     * Whether this module takes a turret hardpoint.
     */
    val takesTurretHardpoint: Boolean
        get() = flags.isBitSet(TAKES_TURRET_HARDPOINT_BIT_INDEX)


    /**
     * Whether this takes a launcher hardpoint.
     */
    val takesLauncherHardpoint: Boolean
        get() = flags.isBitSet(TAKES_LAUNCHER_HARDPOINT_BIT_INDEX)



    companion object {

        private const val ACTIVABLE_BIT_INDEX = 0
        private const val OVERLOADABLE_BIT_INDEX = 1
        private const val PROJECTED_BIT_INDEX = 2
        private const val TAKES_TURRET_HARDPOINT_BIT_INDEX = 3
        private const val TAKES_LAUNCHER_HARDPOINT_BIT_INDEX = 4

    }


}


/**
 * The exception we throw when we encounter an error or inconsistency in the eve data.
 */
internal class BadEveDataException(message: String): RuntimeException(message)