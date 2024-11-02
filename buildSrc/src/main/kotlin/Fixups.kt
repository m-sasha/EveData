/**
 * Fixes the eve data, adds missing attributes, effects and modifiers and corrects others.
 */


/**
 * The name and id of a fixup category.
 */
internal data class FixupCategory(val id: Int, val name: String){


    /**
     * Creates an actual [Category] from this [FixupCategory]
     */
    fun toCategory() = Category(
        id = id,
        name = name
    )


}


/**
 * The extra categories we add.
 */
internal object FixupCategories{


    /**
     * The fixup category for Warfare Buffs.
     */
    val warfareBuff = FixupCategory(1_000_000, "(Fixup) Warfare Buff")


    /**
     * The category for Tactical Modes.
     */
    val tacticalMode = FixupCategory(1_000_001, "(Fixup) Tactical Mode")


}


/**
 * The name and id of a fixup group.
 */
internal data class FixupGroup(val id: Int, val name: String){


    /**
     * Creates an actual [Group] from this [FixupGroup], which will be in the given category.
     */
    fun toGroup(category: Category) = Group(
        id = id,
        name = name,
        categoryId = category.id
    )


}


/**
 * The extra groups we add.
 */
internal object FixupGroups{


    /**
     * The prefix we prepend to all fixup groups.
     */
    private const val PREFIX = "Fix "


    /**
     * Creates and returns a new fixup group.
     */
    private fun fixupGroup(id: Int, name: String) = FixupGroup(id, PREFIX + name)


    /**
     * The group into which the warfare buffs type will go.
     */
    val warfareBuffs = fixupGroup(1_000_000, "Warfare Buffs")


}


/**
 * The name and id of a fixup type.
 */
internal data class FixupType(val id: Int, val name: String){


    /**
     * Creates an actual [Type] from this [FixupType], which will be in the given group.
     */
    fun toType(
        group: Group,
        volume: Double = 1.0  // Everything must have a volume
    ) = Type(
        id = id,
        name = name,
        groupId = group.id,
        metaGroupId = null,
        capacity = null,
        mass = null,
        radius = null,
        volume = volume,
        variationParentTypeID = null,
        marketGroupId = null,
        raceId = null,
        iconId = null,
        description = null,
        traits = null,
        published = false
    )


}


/**
 * The extra types we add.
 */
internal object FixupTypes{


    /**
     * The prefix we prepend to all fixup types.
     */
    private const val PREFIX = "Fix "


    /**
     * Creates and returns a new fixup type.
     */
    private fun fixupType(id: Int, name: String) = FixupType(id, PREFIX + name)


    /**
     * The warfare buffs type.
     */
    val warfareBuffs = fixupType(1_000_000, "WarfareBuffsType")


}


/**
 * The name and id of a fixup attribute.
 */
internal data class FixupAttr(val id: Int, val name: String) {

    fun toAttribute(stackable: Boolean, highIsGood: Boolean?, unitId: Int?) = DogmaAttribute(
        id = id,
        name = name,
        displayName = null,
        stackable = stackable,
        highIsGood = highIsGood,
        unitId = unitId
    )

    fun withValue(value: Double) = DogmaAttributeValue(
        attributeId = id,
        value = value
    )

}


/**
 * The extra attributes we add.
 */
internal object FixupAttrs {


    /**
     * The prefix we prepend to all fixup attributes.
     */
    private const val PREFIX = "fix_"


    /**
     * The id of the next fixup attribute.
     */
    private var nextId = 1_000_000


    /**
     * The attributes we've created, mapped by name.
     *
     * This cache is needed not for performance, but because [fixup] can be called multiple times in the same JVM, and
     * we don't want to create attributes with different ids each time.
     */
    private val attrsByName = mutableMapOf<String, FixupAttr>()


    /**
     * Creates and returns a new fixup attribute.
     */
    fun fixupAttr(name: String) = attrsByName.getOrPut(name) {
        FixupAttr(nextId++, PREFIX + name)
    }


    val propulsionModuleSpeedFactor = fixupAttr("propulsionModuleSpeedFactor")
    val propulsionModuleThrust = fixupAttr("propulsionModuleThrust")

    val weaponDamage = listOf(
        fixupAttr("emWeaponDamage"),
        fixupAttr("thermalWeaponDamage"),
        fixupAttr("kineticWeaponDamage"),
        fixupAttr("explosiveWeaponDamage")
    )

    val droneDamage = listOf(
        fixupAttr("emDroneDamage"),
        fixupAttr("thermalDroneDamage"),
        fixupAttr("kineticDroneDamage"),
        fixupAttr("explosiveDroneDamage")
    )

    val canOnlyFitOnCapitals = fixupAttr("canOnlyFitOnCapitals")

    val durationAttributeId = fixupAttr("durationAttributeId")
    val rangeAttributeId = fixupAttr("rangeAttributeId")
    val falloffAttributeId = fixupAttr("falloffAttributeId")
    val trackingSpeedAttributeId = fixupAttr("trackingSpeedAttributeId")

    val warfareShieldResonanceBonus = fixupAttr("warfareShieldResonanceBonus")
    val warfareShieldBoostersBonus = fixupAttr("warfareShieldBoostersBonus")
    val warfareShieldHitpointsBonus = fixupAttr("warfareShieldHitpointsBonus")
    val warfareArmorResonanceBonus = fixupAttr("warfareArmorResonanceBonus")
    val warfareArmorRepairersBonus = fixupAttr("warfareArmorRepairersBonus")
    val warfareArmorHitpointsBonus = fixupAttr("warfareArmorHitpointsBonus")
    val warfareScanResolutionBonus = fixupAttr("warfareScanResolutionBonus")
    val warfareElectronicSuperiorityBonus = fixupAttr("warfareElectronicSuperiorityBonus")
    val warfareSensorStrengthBonus = fixupAttr("warfareSensorStrengthBonus")
    val warfareEwarResistance = fixupAttr("warfareEwarResistance")
    val warfareSignatureRadiusBonus = fixupAttr("warfareSignatureRadiusBonus")
    val warfareTackleRangeBonus = fixupAttr("warfareTackleRangeBonus")
    val warfareSpeedFactorBonus = fixupAttr("warfareSpeedFactorBonus")
    val warfareTargetingRangeBonus = fixupAttr("warfareTargetingRangeBonus")
    val warfareAgilityBonus = fixupAttr("warfareAgilityBonus")

    val spoolupCycles = fixupAttr("spoolupCycles")
    val spoolupMultiplierBonus = fixupAttr("spoolupDamageMultiplierBonus")

    val tacticalDestroyerShipId = fixupAttr("tacticalDestroyerShipId")
    val hasTacticalModes = fixupAttr("hasTacticalModes")
    val tacticalModeTypeKindId = fixupAttr("tacticalModeTypeKindId")

    val securityStatusEffectMin = fixupAttr("securityStatusEffectMin")
    val securityStatusEffectMax = fixupAttr("securityStatusEffectMax")
}


/**
 * The name and id of a fixup effect.
 */
internal data class FixupEffect(
    val id: Int,
    val name: String,
){


    fun toEffect(
        category: Int,
        isOffensive: Boolean = false,
        isAssistive: Boolean = false,
        conditionAttributeId: Int? = null,
        conditionAttributeValue: Int? = null,
        modifiers: List<DogmaModifier>,
    ) = DogmaEffect(
        id = id,
        name = name,
        isOffensive = isOffensive,
        isAssistive = isAssistive,
        category = category,
        durationAttributeId = null,
        rangeAttributeId = null,
        falloffAttributeId = null,
        trackingSpeedAttributeId = null,
        conditionAttributeId = conditionAttributeId,
        conditionAttributeValue = conditionAttributeValue,
        modifiers = modifiers
    )


    fun toEffectRef() = DogmaEffectRef(id)


}


/**
 * The extra effects we add.
 */
internal object FixupEffects {


    /**
     * The prefix we prepend to all fixup effects.
     */
    private const val PREFIX = "fix_"


    /**
     * The id of the next fixup effect.
     */
    private var nextId = 1_000_000


    /**
     * The effects we've created, mapped by name.
     *
     * This cache is needed not for performance, but because [fixup] can be called multiple times in the same JVM, and
     * we don't want to create effects with different ids each time.
     */
    private val effectsByName = mutableMapOf<String, FixupEffect>()


    /**
     * Creates and returns a new fixup effect.
     */
    fun fixupEffect(name: String) = effectsByName.getOrPut(name) {
        FixupEffect(nextId++, PREFIX + name)
    }


    val weaponDamage = fixupEffect("weaponDamage")
    val chargelessWeaponDamage = fixupEffect("chargelessWeaponDamage")  // Smartbombs and civilian guns
    val droneDamage = fixupEffect("droneDamage")
    val turretDamageMultiplier = fixupEffect("turretDamageMultiplier")
    val missileDamageMultiplier = fixupEffect("missileDamageMultiplier")
    val droneDamageMultiplier = fixupEffect("droneDamageMultiplier")

    val warfareShieldResonanceBonus = fixupEffect("warfareShieldResonanceBonus")
    val warfareShieldBoostersBonus = fixupEffect("warfareShieldHitpointsBonus")
    val warfareShieldHitpointsBonus = fixupEffect("warfareShieldHitpointsBonus")
    val warfareArmorResonanceBonus = fixupEffect("warfareArmorResonanceBonus")
    val warfareArmorRepairersBonus = fixupEffect("warfareArmorRepairersBonus")
    val warfareArmorHitpointsBonus = fixupEffect("warfareArmorHitpointsBonus")
    val warfareScanResolutionBonus = fixupEffect("warfareScanResolutionBonus")
    val warfareElectronicSuperiorityBonus = fixupEffect("warfareElectronicSuperiorityBonus")
    val warfareSensorStrengthBonus = fixupEffect("warfareSensorStrengthBonus")
    val warfareEwarResistance = fixupEffect("warfareDampDisruptionResistance")
    val warfareSignatureRadiusBonus = fixupEffect("warfareSignatureRadiusBonus")
    val warfareTackleRangeBonus = fixupEffect("warfareTackleRangeBonus")
    val warfareSpeedFactorBonus = fixupEffect("warfareSpeedFactorBonus")
    val warfareTargetingRangeBonus = fixupEffect("warfareTargetingRangeBonus")
    val warfareAgilityBonus = fixupEffect("warfareAgilityBonus")

    val computeSpoolupMultiplierBonus = fixupEffect("computeSpoolupMultiplierBonus")
    val applySpoolupDamageMultiplierBonus = fixupEffect("applySpoolupDamageMultiplierBonus")
    val applySpoolupRepairAmountMultiplierBonus = fixupEffect("applySpoolupRepairAmountMultiplierBonus")

    val capInverseSecStatusEffect = fixupEffect("capSecStatusEffect")
}


/**
 * Returns a [DogmaModifier] with the given parameters. Takes types (e.g. [DogmaAttribute]s) rather than ids.
 */
private fun DogmaModifier(
    domain: String,
    func: String,
    modifiedAttribute: DogmaAttribute,
    modifyingAttribute: DogmaAttribute,
    attenuatingAttribute: DogmaAttribute? = null,
    operation: Int?,
    group: Group? = null,
    skillType: Type? = null,
) = DogmaModifier(
    domain = domain,
    func = func,
    modifiedAttributeId = modifiedAttribute.id,
    modifyingAttributeId = modifyingAttribute.id,
    attenuatingAttributeId = attenuatingAttribute?.id,
    operation = operation,
    groupId = group?.id,
    skillTypeId = skillType?.id
)


/**
 * Converts a name with spaces into a camel-case identifier.
 * For example, "Cruise Missile Specialization" is converted to "cruiseMissileSpecialization"
 */
private fun String.toCamelCaseIdentifier() =
    splitToSequence(' ')
        .map { it.replaceFirstChar(Char::uppercaseChar) }
        .joinToString(separator = "")
        .replaceFirstChar(Char::lowercaseChar)


/**
 * The underlying components of [EveData], in mutable form.
 */
private class MutableEveData(eveData: EveData) {

    val metaGroups = eveData.metaGroups.toMutableList()
    val marketGroups = eveData.marketGroups.toMutableList()
    val races = eveData.races.toMutableList()
    val categories = eveData.categories.toMutableList()
    val groups = eveData.groups.toMutableList()
    val types = eveData.types.toMutableList()
    val dogmaTypes = eveData.dogmaTypes.toMutableList()
    val dogmaEffects = eveData.dogmaEffects.toMutableList()
    val dogmaAttributes = eveData.dogmaAttributes.toMutableList()
    val mutaplasmids = eveData.mutaplasmids.toMutableList()
    val abyssalNameToParentTypeID = eveData.abyssalNameToReplacementTypeId.toMutableMap()
    val icons = eveData.icons.toMutableList()

    val knownMetaGroups = eveData.knownMetaGroups
    val knownCategories = eveData.knownCategories
    val knownGroups = eveData.knownGroups
    val knownTypes = eveData.knownTypes
    val knownAttributes = eveData.knownAttributes
    val knownEffects = eveData.knownEffects
    val knownMarketGroups = eveData.knownMarketGroups

    val baseEveData = eveData


    /**
     * Returns an [EveData] with all the fixups.
     */
    fun toEveData() = EveData(
        metaGroups = metaGroups,
        marketGroups = marketGroups,
        races = races,
        categories = categories,
        groups = groups,
        types = types,
        dogmaTypes = dogmaTypes,
        dogmaEffects = dogmaEffects,
        dogmaAttributes = dogmaAttributes,
        mutaplasmids = mutaplasmids,
        abyssalNameToReplacementTypeId = abyssalNameToParentTypeID,
        icons = icons
    )


    /**
     * Adds an effect.
     */
    fun addEffect(effect: DogmaEffect) {
        dogmaEffects.add(effect)
    }


    /**
     * Replaces the given effect.
     */
    fun replaceEffect(effect: DogmaEffect, replacer: (DogmaEffect) -> DogmaEffect) {
        val index = dogmaEffects.indexOfFirst { it.id == effect.id }
        if (index < 0)
            throw IllegalStateException("No effect $effect found")
        dogmaEffects[index] = replacer(dogmaEffects[index])
    }


    /**
     * Replaces the given dogma type.
     */
    fun replaceDogmaType(dogmaType: DogmaType, replacer: (DogmaType) -> DogmaType) {
        val index = dogmaTypes.indexOfFirst { it.id == dogmaType.id }
        if (index < 0)
            throw IllegalStateException("No dogma type $dogmaType found")
        dogmaTypes[index] = replacer(dogmaTypes[index])
    }


    /**
     * Replaces the given type.
     */
    fun replaceType(type: Type, replacer: (Type) -> Type) {
        val index = types.indexOfFirst { it.id == type.id }
        if (index < 0)
            throw IllegalStateException("No type with id ${type.id} (${type.name} found")
        types[index] = replacer(types[index])
    }


    /**
     * Adds an attribute.
     */
    fun addAttribute(attribute: DogmaAttribute){
        dogmaAttributes.add(attribute)
    }


    /**
     * Returns all the [Type]s in the given group.
     */
    fun typesInGroup(group: Group): List<Type> {
        return types.filter { it.groupId == group.id }
    }


    /**
     * Returns all the [Type]s and their respective [DogmaType]s in the given group.
     * Only returns those [Type]s that have a [DogmaType].
     */
    fun typesAndDogmaTypesInGroup(group: Group): List<Pair<Type, DogmaType>> {
        return typesInGroup(group)
            .mapNotNull { type ->
                val dogmaType = dogmaType(type.id) ?: return@mapNotNull null
                Pair(type, dogmaType)
            }
    }


    /**
     * Returns all the [DogmaType]s in the given group.
     */
    fun dogmaTypesInGroup(group: Group): List<DogmaType> {
        return typesAndDogmaTypesInGroup(group).map { it.second }
    }


    /**
     * Returns all the [Type]s and their respective [DogmaType]s in the given categories.
     * Only returns those [Type]s that have a [DogmaType].
     */
    @Suppress("UNCHECKED_CAST")
    fun typesAndDogmaIn(categories: Collection<Category>): List<Pair<Type, DogmaType>> {
        return typesAndOptionalDogmaIn(categories).mapNotNull {
            if (it.second == null)
                null
            else
                it as Pair<Type, DogmaType>
        }
    }


    /**
     * Returns all the [Type]s and their respective [DogmaType]s in the given categories.
     * Only returns those [Type]s that have a [DogmaType].
     */
    fun typesAndDogmaIn(vararg categories: Category) = typesAndDogmaIn(categories.toList())


    /**
     * Returns all the [Type]s and their respective optional [DogmaType]s in the given categories.
     */
    fun typesAndOptionalDogmaIn(categories: Collection<Category>): List<Pair<Type, DogmaType?>> {
        val categoryIds = categories.map { it.id }.toSet()
        val groupIds = groups.filter { it.categoryId in categoryIds }.map { it.id }.toSet()
        val dogmaTypeById = dogmaTypes.associateBy { it.id }
        return types
            .filter { it.groupId in groupIds }
            .map { type -> type to dogmaTypeById[type.id] }
    }


    /**
     * Returns all the [DogmaType]s in the given categories.
     */
    fun dogmaTypesIn(vararg categories: Category): List<DogmaType> =
        typesAndDogmaIn(categories.toList()).map { it.second }


    /**
     * Returns all the [Type]s and their respective optional [DogmaType]s in the given categories.
     */
    fun typesAndOptionalDogmaIn(vararg categories: Category) = typesAndOptionalDogmaIn(categories.toList())


    /**
     * Returns the [Type] with the given name.
     */
    fun type(name: String): Type = types.find { it.name == name } ?:
        throw IllegalStateException("No '$name' type found")


    /**
     * Returns the [Type] with the given id.
     */
    fun type(id: Int): Type = types.find { it.id == id } ?:
        throw IllegalStateException("No type with id '$id' found")


    /**
     * Returns the [DogmaType] of the given id.
     */
    fun dogmaType(id: Int): DogmaType? = dogmaTypes.find { it.id == id }


    /**
     * Returns the [DogmaType] of the type with the given name.
     */
    fun dogmaType(name: String) = dogmaType(type(name))!!


    /**
     * Returns the [DogmaType] of the given [Type].
     */
    fun dogmaType(type: Type): DogmaType? = dogmaType(type.id)


    /**
     * Returns the [DogmaEffect] with the given name.
     */
    fun dogmaEffect(name: String): DogmaEffect = dogmaEffects.first { it.name == name }


    /**
     * Returns the [Group] of the given [Type].
     */
    fun groupOf(type: Type): Group? = groups.find { it.id == type.groupId }


    /**
     * Returns the [Category] of the given [Group].
     */
    fun categoryOf(group: Group): Category? = categories.find { it.id == group.categoryId }


    /**
     * Returns the [Category] of the given [Type].
     */
    fun categoryOf(type: Type): Category? = groupOf(type)?.let { categoryOf(it) }


}


/**
 * Replaces a [DogmaAttributeValue] in the given [DogmaType].
 */
private fun DogmaType.replaceAttributeValue(attributeValue: DogmaAttributeValue): DogmaType {
    return copy(
        attributeValues = attributeValues
            .map { if (it.attributeId == attributeValue.attributeId) attributeValue else it },
    )
}


/**
 * Returns whether the given [DogmaType] has at least one of the given [DogmaAttribute]s.
 */
private fun DogmaType.hasAnyAttribute(attributes: Iterable<DogmaAttribute>) = attributes.any { hasAttribute(it) }



/**
 * Returns a [DogmaEffect] like the given one, but with the added list of [DogmaModifier]s.
 */
private fun DogmaEffect.withAddedModifiers(modifiers: List<DogmaModifier>) =
    copy(modifiers = this.modifiers.plus(modifiers))


/**
 * Returns a [DogmaEffect] like the given one, but with the added list of [DogmaModifier]s.
 */
private fun DogmaEffect.withAddedModifiers(vararg modifiers: DogmaModifier) = withAddedModifiers(modifiers.asList())


/**
 * Returns whether the given [DogmaType] has at least one of the given [DogmaEffect]s.
 */
private fun DogmaType.hasAnyEffect(effects: Iterable<DogmaEffect>) = effects.any{ hasEffect(it) }


/**
 * Returns a [DogmaType] with the given effect refs added.
 */
private fun DogmaType.withAddedEffectRefs(effectRefs: List<DogmaEffectRef>) =
    copy(effectRefs = this.effectRefs + effectRefs)


/**
 * Returns a [DogmaType] with the given effect refs added.
 */
private fun DogmaType.withAddedEffectRefs(vararg effectRefs: DogmaEffectRef) = withAddedEffectRefs(effectRefs.asList())


/**
 * Returns a [DogmaType] like the given one, but with the given list of [DogmaAttributeValue]s.
 */
private fun DogmaType.withAttributeValues(attributeValues: List<DogmaAttributeValue>) =
    copy(attributeValues = attributeValues)


/**
 * Returns a [DogmaType] like the given one, but adds the given list of [DogmaAttributeValue]s.
 */
private fun DogmaType.withAddedAttributeValues(attributeValues: Collection<DogmaAttributeValue>) =
    withAttributeValues(this.attributeValues + attributeValues)


/**
 * Returns a [DogmaType] like the given one, but adds the given [DogmaAttributeValue]s.
 */
private fun DogmaType.withAddedAttributeValues(vararg attributeValues: DogmaAttributeValue) =
    withAddedAttributeValues(attributeValues.asList())


/**
 * Returns a [DogmaType] like the given one, but removes the [DogmaAttributeValue] corresponding to the given
 * [DogmaAttribute]s.
 */
private fun DogmaType.withRemovedAttributeValues(attributeValues: Collection<DogmaAttribute>): DogmaType{
    val attributeIds = attributeValues.map { it.id }.toSet()
    return withAttributeValues(this.attributeValues.filter { it.attributeId !in attributeIds })
}


/**
 * Returns a [DogmaType] like the given one, but removes the [DogmaAttributeValue] corresponding to the given
 * [DogmaAttribute]s.
 */
private fun DogmaType.withRemovedAttributeValues(vararg attributeValues: DogmaAttribute) =
    withRemovedAttributeValues(attributeValues.asList())


/**
 * Returns a new [DogmaAttributeValue] with the given value for the given [DogmaAttribute].
 */
private fun DogmaAttribute.withValue(value: Double) = DogmaAttributeValue(
    attributeId = this.id,
    value = value,
)


/**
 * Removes all types and their corresponding dogma types that match the given filter.
 */
private fun MutableEveData.removeTypesAndCorrespondingDogma(
    vararg categories: Category,
    matcher: (Type, DogmaType?) -> Boolean
) {
    removeTypesAndCorrespondingDogma(
        subset = typesAndOptionalDogmaIn(*categories),
        matcher = matcher
    )
}


/**
 * Removes all types and their corresponding dogma types that match the given filter.
 */
private fun MutableEveData.removeTypesAndCorrespondingDogma(
    subset: Iterable<Pair<Type, DogmaType?>>,
    matcher: (Type, DogmaType?) -> Boolean
) {
    val typesToRemove = mutableSetOf<Type>()
    val dogmaTypesToRemove = mutableSetOf<DogmaType>()
    for ((item, dogmaItem) in subset) {
        if (matcher(item, dogmaItem)) {
            typesToRemove.add(item)
            if (dogmaItem != null)
                dogmaTypesToRemove.add(dogmaItem)
        }
    }

    types.removeAll(typesToRemove)
    dogmaTypes.removeAll(dogmaTypesToRemove)
}


/**
 * The search terms by which we identify wormhole environments.
 */
private val WormholeEnvironmentSearchTerms = listOf(
    "Pulsar Effects",
    "Black Hole Effects",
    "Cataclysmic Variable Effects",
    "Magnetar Effects",
    "Wolf Rayet Effects",
    "Red Giant Effects",
)


/**
 * The search terms by which we identify metaliminal storm environments.
 */
private val MetaliminalStormSearchTerms = listOf(
    "Metaliminal Electrical Storm",
    "Metaliminal Exotic Matter Storm",
    "Metaliminal Gamma Ray Storm",
    "Metaliminal Plasma Firestorm",
)


/**
 * The ids of abyssal "Dark Matter Field" weather types.
 */
private val AbyssalDarkMatterFieldWeatherIds = listOf(47378, 47379, 47380)  // darkness_weather_1/2/3


/**
 * The ids of abyssal "Exotic Particle Storm" weather types.
 */
private val AbyssalExoticParticleStormWeatherIds = listOf(47384, 47385, 47386)  // caustic_toxin_weather_1/2/3


/**
 * The ids of abyssal "Plasma Firestorm" weather types.
 */
private val AbyssalPlasmaFirestormWeatherIds = listOf(47390, 47391, 47392)  // infernal_weather_1/2/3


/**
 * The ids of abyssal "Electrical Storm" weather types.
 */
private val AbyssalElectricalStormWeatherIds = listOf(47381, 47382, 47383)  // electric_storm_weather_1/2/3


/**
 * The ids of abyssal "Gamma-Ray Afterglow" weather types.
 */
private val AbyssalGammaRayAfterglowWeatherIds = listOf(47387, 47388, 47389)  // xenon_gas_weather_1/2/3


/**
 * The ids of all abyssal weather types.
 */
private val AbyssalWeatherEnvironmentIds = buildSet {
    addAll(AbyssalDarkMatterFieldWeatherIds)
    addAll(AbyssalExoticParticleStormWeatherIds)
    addAll(AbyssalPlasmaFirestormWeatherIds)
    addAll(AbyssalElectricalStormWeatherIds)
    addAll(AbyssalGammaRayAfterglowWeatherIds)
}


/**
 * Returns the environment types of the given [EveData] that are relevant to us.
 */
internal fun EveData.relevantEnvironmentTypes(): List<Type> {
    val effectBeaconEnvs = typesIn(knownGroups.effectBeacon)

    return buildList {

        fun Collection<Type>.addIfNameContains(substring: String) {
            for (type in this@addIfNameContains) {
                if (type.name.contains(substring))
                    add(type)
            }
        }

        // Wormholes
        WormholeEnvironmentSearchTerms.forEach {
            effectBeaconEnvs.addIfNameContains(it)
        }

        // Metaliminal storms
        MetaliminalStormSearchTerms.forEach {
            effectBeaconEnvs.addIfNameContains(it)
        }

        // Abyssal weather
        addAll(types.filter { it.id in AbyssalWeatherEnvironmentIds })

        /*
        // Sansha Incursion
        effectBeaconEnvs.addIfNameContains("Sansha Incursion")  // Has effect; no modifiers

        // Turnur Aftermath
        effectBeaconEnvs.addIfNameContains("Turnur Aftermath")
         */
    }
}


/**
 * Fixes a bunch of problems and adds missing stuff.
 */
internal fun fixup(eveData: EveData): EveData {
    return with(MutableEveData(eveData)) {
        fixAbyssalWeatherTypes()
        removeUselessTypes()
        fixAttributeNames()
        fixDisplayedAttributeNames()
        fixMetaGroupId()
        fixTacticalModesGroupCategory()
        fixVariationParentTypeId()
        handleAbyssalItems()  // Must be called after fixMetaGroupId
        removeUselessModules()  // Must be called after fixTacticalModesGroupCategory
        removeUselessImplants()
        removeUselessBoosters()
        addImplantVariationParentTypeId()
        addBoosterVariationParentTypeId()
        addTacticalModeToDestroyerAssociation()
        addTacticalModeKindIdAttribute()
        fixCovertOpsCloakingDevicesVariationParentTypeId()
        fixAncillaryRepairerVariationParentTypeId()
        fixShipAttributeValues()
        fixDroneAttributeValues()
        fixModuleAttributeValues()
        fixChargeAttributeValues()
        fixSkillAttributeValues()
        fixCapitalOnlyModules()
        fixPropModulesSpeedBonusEffects()
        fixSpeedLimit()
        fixEntosisLinkSensorStrengthBonus()
        fixMissileAndDroneSkillDamageBonusEffects()
        fixMissileSpecializationSkillRofBonusEffects()
        fixMissileBonusEffects()
        fixWeaponModuleDamage()
        fixDroneDamage()
        fixSpoolupModules()
        fixProjectileCapacitorNeed()
        fixNosferatuCapacitorNeed()
        fixMetaAttributes()
        fixAttributeUnits()
        addAttributeRanges()
        fixResistanceKillers()
        fixEmergencyHullEnergizerEffect()
        fixMaxActiveDronesAttribute()
        fixSmallAsbZeroChargeSize()
        fixZeroMaxGroupActive()
        fixMercoxitMiningLaserBonusEffect()
        fixCommandBursts()
        fixReactiveArmorHardener()
        fixBoosterMissileExplosionCloudPenaltyFixedEffect()
        fixSlotModifierEffect()
        fixHardpointModifierEffect()
        fixSecurityStatusEffects()
        removeSentryDronesSpeed()
        removeProbeSpeed()
        removeWhitespaceFromAttributeAndEffectNames()
        fixRemoteEffects()
        fixMutaplasmids()
        assignItemTypeIconIds()
        removeUselessMarketGroups()
        fixMarketGroupNames()
        fixTriglavianShipLevels()
        fixMarketIconIds()
        addEnvironmentIcons()
        addEnvironmentVariationParentTypes()

        toEveData()
    }
}


/**
 * Removes some types we're not interested in.
 */
private fun MutableEveData.removeUselessTypes() {
    val allTypesAndDogma = types.map { type ->
        val dogmaType = dogmaType(type)
        Pair(type, dogmaType)
    }

    val extraAllowedItemNames = setOf(
        "Civilian Gatling Autocannon",
        "Civilian Gatling Pulse Laser",
        "Civilian Gatling Railgun",
        "Civilian Light Electron Blaster",
        "Civilian Relic Analyzer",
    )

    removeTypesAndCorrespondingDogma(subset = allTypesAndDogma) { type, _ ->
        when {
            type.published -> false
            type.groupId == knownGroups.shipModifiers.id -> false
            type.id == EveData.CHARACTER_TYPE_ID -> false
            type.name in extraAllowedItemNames -> false
            else -> true
        }
    }
}


/**
 * Fixes some badly named attributes.
 */
private fun MutableEveData.fixAttributeNames() {
    val renamings = listOf(
        "modeThermicResistancePostDiv" to "modeThermalResistancePostDiv"
    )

    for ((name, newName) in renamings){
        val index = dogmaAttributes.indexOfFirst { it.name == name }
        if (index == -1)
            println("Can't find attribute `$name` to rename")

        dogmaAttributes[index] = dogmaAttributes[index].copy(
            name = newName
        )
    }
}


/**
 * Fixes displayed attribute names.
 *
 * Most of the display names are capitalized in every word, but some aren't. This fixes that.
 */
private fun MutableEveData.fixDisplayedAttributeNames() {
    for (index in dogmaAttributes.indices) {
        val attr = dogmaAttributes[index]
        val displayName = attr.displayName ?: continue
        dogmaAttributes[index] = attr.copy(
            displayName = displayName
                .split(' ')
                .joinToString(" ") {
                    if (it == "of")
                        it
                    else
                        it.replaceFirstChar(Char::titlecaseChar)
                }
        )
    }
}


/**
 * Items with a `null` [Type.variationParentTypeID] are the "parent" of their variation group.
 * To make it easier to group items into variation groups, we assign them their own id as their
 * [Type.variationParentTypeID].
 */
private fun MutableEveData.fixVariationParentTypeId() {
    val categories = setOf(knownCategories.module, knownCategories.drone)
    for ((index, type) in types.withIndex()){
        val category = categoryOf(type)
        if (category !in categories)
            continue

        if (type.variationParentTypeID == null){
            types[index] = type.copy(
                variationParentTypeID = type.id
            )
        }
    }
}


/**
 * Adds [Type.variationParentTypeID] to implants, grouping implants that have the same effect but of different
 * magnitude, e.g.
 * - Eifyr and Co. 'Rogue' Navigation NN-602
 * - Eifyr and Co. 'Rogue' Navigation NN-603
 * etc.
 */
private fun MutableEveData.addImplantVariationParentTypeId(){

    class ImplantInfo(
        val typeIndex: Int,
        val type: Type,
        val slot: Int
    )

    // Separately group learning implants that have one of these substrings in their name
    val learningImplantSubstrings = listOf(
        "Ocular Filter",
        "Memory Augmentation",
        "Neural Boost",
        "Cybernetic Subprocessor",
        "Social Adaptation Chip"
    )

    // There's no easy robust way, so what we do here is take advantage of the fact that similar implants
    // share a prefix up to the last "word" in their name.
    val implantness = knownAttributes.implantness
    val implantVariationGroups = types.withIndex()
        .mapNotNull { (index, type) ->
            val dogmaType = dogmaType(type.id) ?: return@mapNotNull null
            val slot = dogmaType.getAttributeValue(implantness)?.toInt() ?: return@mapNotNull null
            ImplantInfo(
                typeIndex = index,
                type = type,
                slot = slot
            )
        }
        .groupBy { info ->
            val name = info.type.name
            val learningImplantSubstring = learningImplantSubstrings.find { name.contains(it) }
            val commonSubstring = name.substring(
                startIndex = name.indexOf("-grade") + 1,  // For pirate implants
                endIndex = name.lastIndexOf(' ')
            )
            Pair(
                first = info.slot,  // Make sure we don't mix slots
                second = learningImplantSubstring ?: commonSubstring
            )
        }
        .values
        .associateBy { implantInfos ->
            implantInfos.minByOrNull { it.type.id }!!  // We only care about consistency, not which one is the parent
        }

    for ((parentImplantInfo, implantInfos) in implantVariationGroups.entries){
        val parentImplantId = parentImplantInfo.type.id
        for (info in implantInfos){
            types[info.typeIndex] = info.type.copy(
                variationParentTypeID = parentImplantId
            )
        }
    }
}


/**
 * Adds [Type.variationParentTypeID] to boosters, grouping boosters that have the same effect but of different
 * magnitude, e.g.
 * - Standard Mindflood Booster
 * - Improved Mindflood Booster
 * etc.
 */
private fun MutableEveData.addBoosterVariationParentTypeId(){

    class BoosterInfo(
        val typeIndex: Int,
        val type: Type,
        val slot: Int
    )

    val classicStrengths = listOf("Synth", "Standard", "Improved", "Strong", "Nugoehuvi Synth")
    val agencyBoosterPrefixes = listOf("Agency 'Hardshell'", "Agency 'Overclocker'", "Agency 'Pyrolancea'")
    val specialBoosterGroup = mapOf(
        "Antipharmakon Aeolis" to "Mindflood Booster",
        "Antipharmakon Kosybo" to "Exile Booster",
        "Antipharmakon Thureo" to "Blue Pill Booster",
        "Antipharmakon Iokira" to "Drop Booster",
    )

    // There's no easy robust way, so what we do here is take advantage of the fact that similar boosters
    // have similar names.
    val boosterness = knownAttributes.boosterness
    val boosterVariationGroups = types.withIndex()
        .mapNotNull { (index, type) ->
            val dogmaType = dogmaType(type.id) ?: return@mapNotNull null
            val slot = dogmaType.getAttributeValue(boosterness)?.toInt() ?: return@mapNotNull null
            BoosterInfo(
                typeIndex = index,
                type = type,
                slot = slot
            )
        }
        .groupBy { info ->
            val name = info.type.name
            val commonSubstring: String?

            val strengthPrefix = classicStrengths.find { name.startsWith(it) }
            val agencyPrefix = agencyBoosterPrefixes.find { name.startsWith(it) }
            val specialGroup = specialBoosterGroup[name]

            commonSubstring = when {
                // Classic boosters, e.g. "Improved Blue Pill Booster"
                strengthPrefix != null -> name.substring(startIndex = strengthPrefix.length + 1)

                // Agency boosters
                agencyPrefix != null -> agencyPrefix

                // Special group belonging
                specialGroup != null -> specialGroup

                else -> null
            }
            Pair(
                first = info.slot,  // Make sure we don't mix slots
                second = commonSubstring ?: name  // If no common string, it's unique
            )
        }
        .values
        .associateBy { boosterInfos ->
            boosterInfos.minByOrNull { it.type.id }!!  // We only care about consistency, not which one is the parent
        }

    for ((parentBoosterInfo, boosterInfos) in boosterVariationGroups.entries){
        val parentBoosterId = parentBoosterInfo.type.id
        for (info in boosterInfos){
            types[info.typeIndex] = info.type.copy(
                variationParentTypeID = parentBoosterId
            )
        }
    }
}


/**
 * Removes useless implants.
 */
private fun MutableEveData.removeUselessImplants(){
    removeTypesAndCorrespondingDogma(knownCategories.implant) { type, _ ->
        type.name == "Genolution 'Auroral' AU-79"
    }
}


/**
 * Removes useless boosters.
 */
private fun MutableEveData.removeUselessBoosters(){
    removeTypesAndCorrespondingDogma(knownCategories.implant) { type, dogmaItem ->
        // Currently we remove all boosters with an expiration date, but maybe in the future we could
        // allow unexpired, or even expired, boosters.
        (dogmaItem?.hasAttribute(knownAttributes.boosterLastInjectionDatetime) == true)
                || (type.marketGroupId == null)
                || type.name.contains("Cerebral Accelerator")
                || (type.name == "Nanoheuristic Clone Mapper")
    }
}


/**
 * The group for Tactical Modes (Ship Modifiers) is in the "Modules" category, which makes no sense.
 * This assigns it to a fixup category "Tactical Modes"
 */
private fun MutableEveData.fixTacticalModesGroupCategory(){
    categories.add(FixupCategories.tacticalMode.toCategory())

    val index = groups.indexOfFirst { it.id == knownGroups.shipModifiers.id }
    if (index < 0)
        throw IllegalStateException("No group ${knownGroups.shipModifiers} found")
    groups[index] = knownGroups.shipModifiers.copy(categoryId = FixupCategories.tacticalMode.id)
}


/**
 * The tactical mode types have no "connection" to their respective tactical destroyers.
 * This adds a `tacticalDestroyerShipId` attribute to each tactical mode to specify the type id of the tactical
 * destroyer the mode is for.
 * It also adds a `hasTacticalModes` flag attribute to each tactical destroyer.
 */
private fun MutableEveData.addTacticalModeToDestroyerAssociation(){
    addAttribute(FixupAttrs.tacticalDestroyerShipId.toAttribute(stackable = true, highIsGood = null, unitId = Units.TypeId))
    addAttribute(FixupAttrs.hasTacticalModes.toAttribute(stackable = true, highIsGood = null, unitId = Units.BooleanFlag))

    val tacticalDestroyerByName = typesInGroup(knownGroups.tacticalDestroyers).associateBy { it.name }

    // Add `tacticalDestroyerShipId` to each tactical mode
    for ((mode, dogmaMode) in typesAndDogmaTypesInGroup(knownGroups.shipModifiers)){
        val shipName = mode.name.split(' ').first()  // Sigh
        val ship = tacticalDestroyerByName[shipName] ?:
            throw IllegalStateException("Tactical mode $mode doesn't match any tactical destroyer")

        replaceDogmaType(dogmaMode){
            it.withAddedAttributeValues(
                FixupAttrs.tacticalDestroyerShipId.withValue(ship.id.toDouble())
            )
        }
    }

    // Add `hasTacticalModes` to each tactical destroyer
    for ((_, dogmaDestroyer) in typesAndDogmaTypesInGroup(knownGroups.tacticalDestroyers)){
        replaceDogmaType(dogmaDestroyer){
            it.withAddedAttributeValues(
                FixupAttrs.hasTacticalModes.withValue(1.0)
            )
        }
    }
}


/**
 * Adds a fixup `tacticalModeTypeKindId` attribute to each tactical mode that specifies its kind:
 * - 1: Defense Mode
 * - 2: Sharpshooter Mode
 * - 3: Propulsion Mode
 */
private fun MutableEveData.addTacticalModeKindIdAttribute(){
    addAttribute(FixupAttrs.tacticalModeTypeKindId.toAttribute(stackable = true, highIsGood = null, unitId = Units.Unitless))

    val stringIdToKindId = listOf(
        "Defense" to 1,
        "Sharpshooter" to 2,
        "Propulsion" to 3
    )
    // Add `tacticalModeTypeKindId` to each tactical mode
    for ((mode, dogmaMode) in typesAndDogmaTypesInGroup(knownGroups.shipModifiers)){
        val kindId = stringIdToKindId.find { (stringId, _) -> mode.name.contains(stringId) }?.second
            ?: error("Tactical mode $mode with unrecognized name")

        replaceDogmaType(dogmaMode){
            it.withAddedAttributeValues(
                FixupAttrs.tacticalModeTypeKindId.withValue(kindId.toDouble())
            )
        }
    }
}


/**
 * Separates covert-ops cloaking devices into their separate variation group.
 */
private fun MutableEveData.fixCovertOpsCloakingDevicesVariationParentTypeId(){
    val covertOpsCloak = type("Covert Ops Cloaking Device II")
    val covertOpsShipGroup = knownGroups.covertOpsShips
    for ((index, type) in types.withIndex()){
        if (type.variationParentTypeID != covertOpsCloak.variationParentTypeID)
            continue

        val dogmaType = dogmaType(type) ?: continue
        val isCovertOpsCloak = knownAttributes.canFitShipGroups.any {
            dogmaType.getAttributeValue(it)?.toInt() == covertOpsShipGroup.id
        }
        if (isCovertOpsCloak){
            types[index] = type.copy(
                variationParentTypeID = covertOpsCloak.id
            )
        }
    }
}


/**
 * Puts Ancillary Armor Repairers (including remote) and Ancillary Shield Boosters (including remote) into the variation
 *
 */
private fun MutableEveData.fixAncillaryRepairerVariationParentTypeId(){
    val moduleSuffixes = listOf(
        "Ancillary Armor Repairer" to "Armor Repairer I",
        "Ancillary Shield Booster" to "Shield Booster I",
        "Ancillary Remote Armor Repairer" to "Remote Armor Repairer I",
        "Ancillary Remote Shield Booster" to "Remote Shield Booster I"
    )
    val sizes = listOf("Small", "Medium", "Large", "X-Large", "Capital")

    val allFixes = sizes.flatMap { size -> moduleSuffixes.map { Triple(size, it.first, it.second) } }
    for ((size, fixSuffix, targetSuffix) in allFixes){
        val moduleToFixIndex = types.indexOfFirst { it.name == "$size $fixSuffix" }
        if (moduleToFixIndex < 0)  // Skip X-Large AARs, which don't exist
            continue

        val moduleToFix = types[moduleToFixIndex]
        val targetModule = type("$size $targetSuffix")

        types[moduleToFixIndex] = moduleToFix.copy(
            variationParentTypeID = targetModule.variationParentTypeID
        )
    }
}


/**
 * Fixes the metaGroupID attribute on all the types that need it.
 */
private fun MutableEveData.fixMetaGroupId(){
    val categoriesThatNeedMetaGroup = with(knownCategories){ listOf(ship, module, charge, drone) }
    val metaGroupIdAttribute = knownAttributes.metaGroupId

    for ((type, dogmaType) in typesAndDogmaIn(categoriesThatNeedMetaGroup)){
        val addedAttributeValues = ArrayList<DogmaAttributeValue>(1)

        // Some types have their metaGroupID in the Type rather that in the DogmaType
        if (type.metaGroupId != null)
            addedAttributeValues.add(metaGroupIdAttribute.withValue(type.metaGroupId.toDouble()))
        else if (!dogmaType.hasAttribute(metaGroupIdAttribute) && (categoryOf(type) in categoriesThatNeedMetaGroup)){
            // Other items don't have it at all
            addedAttributeValues.add(metaGroupIdAttribute.withValue(knownMetaGroups.tech1.id.toDouble()))
        }

        if (addedAttributeValues.isNotEmpty()){
            replaceDogmaType(dogmaType){
                it.withAddedAttributeValues(addedAttributeValues)
            }
        }
    }
}


/**
 * Fixes ship attributes.
 */
private fun MutableEveData.fixShipAttributeValues(){
    for ((ship, dogmaShip) in typesAndDogmaIn(knownCategories.ship)){
        val existingAttributeIds = dogmaShip.attributeValues.map { it.attributeId }.toSet()
        val fixedAttributeValues = ArrayList<DogmaAttributeValue>(dogmaShip.attributeValues.size + 4)
        fixedAttributeValues.addAll(dogmaShip.attributeValues)

        fun addIfMissing(attributeValue: DogmaAttributeValue){
            if (attributeValue.attributeId !in existingAttributeIds)
                fixedAttributeValues.add(attributeValue)
        }

        with(knownAttributes) {
            fixedAttributeValues.apply {
                removeIf { it.attributeId == wrongRigSlots.id }

                // For some reason capacity and mass are in the Type rather than the DogmaType
                add(capacity.withValue(ship.capacity ?: 0.0))
                add(mass.withValue(ship.mass!!))
                add(radius.withValue(ship.radius!!))

                // These are not present in some ships, but we don't want to deal with that, so we insert a reasonable default
                addIfMissing(calibration.withValue(0.0))
                addIfMissing(turretHardpoints.withValue(0.0))
                addIfMissing(launcherHardpoints.withValue(0.0))
                addIfMissing(highSlots.withValue(0.0))
                addIfMissing(medSlots.withValue(0.0))
                addIfMissing(lowSlots.withValue(0.0))
                addIfMissing(rigSlots.withValue(0.0))
                addIfMissing(scanResolution.withValue(0.0))
                addIfMissing(droneCapacity.withValue(0.0))
                addIfMissing(droneBandwidth.withValue(0.0))
                addIfMissing(sensorDampenerResistance.withValue(1.0))
                addIfMissing(targetPainterResistance.withValue(1.0))
                addIfMissing(ecmResistance.withValue(1.0))
                addIfMissing(stasisWebifierResistance.withValue(1.0))
                addIfMissing(remoteRepairImpedance.withValue(1.0))
                addIfMissing(remoteAssistanceImpedance.withValue(1.0))

                // These are needed to implement prop modules' speed boost. See fixPropModulesSpeedBonusEffects
                add(FixupAttrs.propulsionModuleSpeedFactor.withValue(1.0))
                add(FixupAttrs.propulsionModuleThrust.withValue(0.0))
            }
        }

        replaceDogmaType(dogmaShip){
            it.withAttributeValues(fixedAttributeValues)
        }
    }
}


/**
 * Fixes drone attributes.
 */
private fun MutableEveData.fixDroneAttributeValues(){
    for ((drone, dogmaDrone) in typesAndDogmaIn(knownCategories.drone)){
        val fixedAttributeValues = ArrayList<DogmaAttributeValue>(dogmaDrone.attributeValues.size + 1)
        fixedAttributeValues.addAll(dogmaDrone.attributeValues)

        with(knownAttributes) {
            fixedAttributeValues.apply {
                // For some reason mass is in the Type rather than the DogmaType
                drone.mass?.let { add(mass.withValue(it)) }
            }
        }

        replaceDogmaType(dogmaDrone){
            it.withAttributeValues(fixedAttributeValues)
        }
    }
}


/**
 * Fixes module attributes.
 */
private fun MutableEveData.fixModuleAttributeValues(){
    for ((module, dogmaModule) in typesAndDogmaIn(knownCategories.module)){
        val addedAttributeValues = ArrayList<DogmaAttributeValue>(1)

        // For some reason capacity is in the Type rather than the DogmaType
        if (module.capacity != null)
            addedAttributeValues.add(knownAttributes.capacity.withValue(module.capacity))

        if (addedAttributeValues.isNotEmpty()){
            replaceDogmaType(dogmaModule){
                it.withAddedAttributeValues(addedAttributeValues)
            }
        }
    }
}


/**
 * Fixes charge attributes.
 */
private fun MutableEveData.fixChargeAttributeValues(){
    for ((charge, dogmaCharge) in typesAndDogmaIn(knownCategories.charge)){
        val addedAttributeValues = ArrayList<DogmaAttributeValue>(1)

        // For some reason mass (for missiles) is in the Type rather than the DogmaType
        if (charge.mass != null)
            addedAttributeValues.add(knownAttributes.mass.withValue(charge.mass))

        if (addedAttributeValues.isNotEmpty()){
            replaceDogmaType(dogmaCharge){
                it.withAddedAttributeValues(addedAttributeValues)
            }
        }
    }
}


/**
 * Fixes skill attributes.
 */
private fun MutableEveData.fixSkillAttributeValues() {
    val skillLevel = knownAttributes.skillLevel

    for (dogmaSkill in dogmaTypesIn(knownCategories.skill)){
        val addedAttributeValues = ArrayList<DogmaAttributeValue>(1)

        // Some skills are missing the 'skillLevel' attribute, so we add it
        if (dogmaSkill.attributeValues.none { it.attributeId == skillLevel.id })
            addedAttributeValues.add(skillLevel.withValue(0.0))

        if (addedAttributeValues.isNotEmpty()){
            replaceDogmaType(dogmaSkill){
                it.withAddedAttributeValues(addedAttributeValues)
            }
        }
    }
}


/**
 * Removes modules we don't care about.
 */
private fun MutableEveData.removeUselessModules(){
    fun DogmaType?.isUseless(): Boolean{
        // The SDE includes weird Drifter modules like "Lux Kontos", which don't have a slot effect
        return (this == null) || moduleSlotType(knownEffects, this) == null
    }

    removeTypesAndCorrespondingDogma(knownCategories.module) { _, dogmaModule ->
        dogmaModule.isUseless()
    }
}


/**
 * Most (all?) capital-sized modules can only be fitted on capital-class ships. So it says in their description.
 * In the SDE, however, nothing marks them as such.
 * This adds a special attribute to them that marks them as only being fittable on capitals.
 */
private fun MutableEveData.fixCapitalOnlyModules(){
    addAttribute(FixupAttrs.canOnlyFitOnCapitals.toAttribute(stackable = true, highIsGood = null, unitId = Units.BooleanFlag))

    for ((module, dogmaModule) in typesAndDogmaIn(knownCategories.module)){
        // Unfortunately, I don't see a better way to detect these modules.
        if (module.description?.contains("May only be fitted to capital class ships") != true)
            continue

        replaceDogmaType(dogmaModule){
            it.withAddedAttributeValues(
                listOf(FixupAttrs.canOnlyFitOnCapitals.withValue(1.0))
            )
        }
    }
}



/**
 * Fixes the `moduleBonusMicrowarpdrive` and `moduleBonusAfterburner` effects, adding the necessary modifiers to them.
 * For some reason, the SDE does not specify any modifiers for these effects.
 */
private fun MutableEveData.fixPropModulesSpeedBonusEffects(){
    // We implement 3 things here:
    // 1. MWD signature bloom
    // 2. MWD and AB mass increase
    // 3. MWD and AB speed boost
    //
    // Signature bloom and mass increase are straightforward: just multiply/add the module's relevant (modifying)
    // attribute to the ship's relevant (modified) attributes.
    //
    // Speed boost is trickier. There are two attributes in the module itself that affect the increase in maximum
    // velocity:
    // 1. `speedFactor`, which is interpreted as "Maximum Velocity Bonus" and is a percentage increase, e.g. 510% base
    //    for "50MN Microwarpdrive II".
    // 2. `speedBoostFactor`, which is interpreted as "Thrust". It is divided by the mass to obtain a factor that is
    //    then multiplied by the `speedFactor`.
    // The full formula for maximum velocity, taken from https://wiki.eveuniversity.org/Propulsion_equipment, is:
    // maxVelocityWithPropModule = maxVelocity * (1 + maxVelocityBonus * (thrust/mass))
    // To implement this, we
    // 1. Add two new (fix) attributes, `propulsionModuleSpeedFactor` and `propulsionModuleThrust`, to each ship
    //    (see fixShipAttributeValues). `propulsionModuleSpeedFactor` receives a default value of 1.0 and
    //    `propulsionModuleThrust` receives a default value of 0.0
    // 2. Add modifiers to the prop modules effects that change these ship attributes:
    //    - The prop module's `speedFactor` multiplies the ship's `propulsionModuleSpeedFactor`
    //    - The prop module's `speedBoostFactor` (thrust) adds to the ship's `propulsionModuleThrust`
    // The final max velocity calculation is then done by applying the above formula to the ship's attribute values.

    val propulsionModuleSpeedFactor =
        FixupAttrs.propulsionModuleSpeedFactor.toAttribute(stackable = true, highIsGood = true, unitId = Units.Multiplier1)
    val propulsionModuleThrust =
        FixupAttrs.propulsionModuleThrust.toAttribute(stackable = true, highIsGood = true, unitId = Units.ThrustNewtons)

    val microwarpdriveBonusEffect = knownEffects.moduleBonusMicrowarpdrive
    val afterburnerBonusEffect = knownEffects.moduleBonusAfterburner

    addAttribute(propulsionModuleSpeedFactor)
    addAttribute(propulsionModuleThrust)
    replaceEffect(microwarpdriveBonusEffect) {
        it.withAddedModifiers(
            propModuleSignatureRadiusIncreaseModifier(knownAttributes.signatureRadiusBonus),
            propModuleMassAdditionIncreaseModifier(),
            propModuleSpeedFactorModifier(propulsionModuleSpeedFactor),
            propModuleThrustModifier(propulsionModuleThrust),
        )
    }
    replaceEffect(afterburnerBonusEffect) {
        it.withAddedModifiers(
            propModuleMassAdditionIncreaseModifier(),
            propModuleSpeedFactorModifier(propulsionModuleSpeedFactor),
            propModuleThrustModifier(propulsionModuleThrust),
        )
    }

    // Fix Micro Jump Drives and Micro Jump Field Generators signature bloom too
    replaceEffect(knownEffects.microJumpDrive){
        it.withAddedModifiers(
            propModuleSignatureRadiusIncreaseModifier(knownAttributes.signatureRadiusBonusPercent)
        )
    }
    replaceEffect(knownEffects.microJumpPortalDrive){
        it.withAddedModifiers(
            propModuleSignatureRadiusIncreaseModifier(knownAttributes.signatureRadiusBonusPercent)
        )
    }
}


/**
 * Returns a [DogmaModifier] that will increase the ship's mass for Microwarpdrives and Afterburners.
 */
private fun MutableEveData.propModuleMassAdditionIncreaseModifier(): DogmaModifier{
    return DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = knownAttributes.mass,
        modifyingAttribute = knownAttributes.massAddition,
        operation = ModifierOperation.add,
    )
}


/**
 * Returns a [DogmaModifier] that will increase the ship's `speedFactor` for Microwarpdrives and Afterburners.
 */
private fun MutableEveData.propModuleSpeedFactorModifier(propulsionModuleSpeedFactor: DogmaAttribute): DogmaModifier{
    return DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = propulsionModuleSpeedFactor,
        modifyingAttribute = knownAttributes.speedFactor,
        // This is a made-up modifier operation we added especially for speedFactor because although it's supposed to
        // be a multiplicative factor, its value is in percent (but only in propulsion modules; in a stasis webifier,
        // the value is a coefficient, as it should be).
        // For example, for a 10MN Afterburner II, its base value is 135, and it implies a 35% (or x1.35) boost to
        // propulsionModuleSpeedFactor. But there is no operation to multiply by percent, only to add a percent or to
        // multiply by a value.
        // Historical note: initially, we divided all propulsion module speedFactor values by 100, and used
        // postMultiply. But this becomes problematic once we need to display, import and export attribute values, such
        // as with mutated attributes.
        operation = ModifierOperation.multiplyPercent,
    )
}


/**
 * Returns a [DogmaModifier] that will increase the ship's thrust for Microwarpdrives and Afterburners.
 */
private fun MutableEveData.propModuleThrustModifier(propulsionModuleThrust: DogmaAttribute): DogmaModifier{
    // The ship's propulsionModuleThrust default value is 0, and we add to it.
    // This zeroes the whole prop module speed boost percentage factor when no prop modules are active.
    return DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = propulsionModuleThrust,
        modifyingAttribute = knownAttributes.speedBoostFactor,
        operation = ModifierOperation.add,
    )
}


/**
 * Returns a [DogmaModifier] that will increase the ship's signature radius for Microwarpdrives, Micro Jump Drives and
 * Micro Jump Field Generators.
 */
private fun MutableEveData.propModuleSignatureRadiusIncreaseModifier(
    bonusAttribute: DogmaAttribute
): DogmaModifier{
    return DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = knownAttributes.signatureRadius,
        modifyingAttribute = bonusAttribute,
        operation = ModifierOperation.addPercent,
    )
}


/**
 * Entosis links impose a fixed limit on the ship speed by setting a ship "speedLimit" attribute. However, by default
 * ships do not have this attribute, which makes sense, as there is no speed limit by default. Unfortunately, the
 * fitting engine does not support affecting a property whose attribute is not present in the first place.
 * As a workaround, we add the "speedLimit" attribute to all ships with a default value of -1.0, which we interpret as
 * "no limit".
 */
private fun MutableEveData.fixSpeedLimit(){
    val speedLimitAttributeValue = knownAttributes.speedLimit.withValue(-1.0)
    for (dogmaShip in dogmaTypesIn(knownCategories.ship)){
        replaceDogmaType(dogmaShip){
            it.copy(
                attributeValues = it.attributeValues + speedLimitAttributeValue
            )
        }
    }
}


/**
 * Entosis links are supposed to give a bonus to sensor strength. Unfortunately, they do not specify the effect needed
 * to actually make it happen. However, an effect that does just that already exists (although it doesn't appear to be
 * used anywhere). We add this effect to entosis links.
 */
private fun MutableEveData.fixEntosisLinkSensorStrengthBonus(){
    val sensorStrengthBonusEffect = knownEffects.scanStrengthBonusPercentActivate
    for (dogmaEntosisLink in dogmaTypesInGroup(knownGroups.entosisLink)){
        replaceDogmaType(dogmaEntosisLink){
            it.copy(
                effectRefs = it.effectRefs + DogmaEffectRef(effectId = sensorStrengthBonusEffect.id)
            )
        }
    }
}


/**
 * Fix the (non-specialization) missile skills and (all) drone skills that grant a damage bonus.
 * For some reason, the SDE does not specify the effects needed for these skills to actually apply their damage bonus.
 * Amusingly, the XL missile skills, and the "Drone Interfacing" skill do have the right effects.
 */
private fun MutableEveData.fixMissileAndDroneSkillDamageBonusEffects(){
    val missileSkillsToFix = listOf(
        "Light Missiles",
        "Rockets",
        "Heavy Missiles",
        "Heavy Assault Missiles",
        "Cruise Missiles",
        "Torpedoes",
        "Auto-Targeting Missiles"
    )
    val droneSkillsToFix = listOf(
        "Light Drone Operation",
        "Medium Drone Operation",
        "Heavy Drone Operation",
        "Sentry Drone Interfacing",
        "Amarr Drone Specialization",
        "Caldari Drone Specialization",
        "Gallente Drone Specialization",
        "Minmatar Drone Specialization"
    )
    val skillsToFix = missileSkillsToFix.map { it to true } + droneSkillsToFix.map { it to false }

    val skillByName = typesAndDogmaIn(knownCategories.skill).associateBy { it.first.name }
    for ((skillName, isMissileSkill) in skillsToFix){
        val (skill, dogmaSkill) = skillByName.getValue(skillName)

        // Add the effect
        val effectName = skillName.toCamelCaseIdentifier() + "SkillDamageBonus"
        val fixupEffect = FixupEffects.fixupEffect(effectName)
        addEffect(
            if (isMissileSkill){
                // For missile skills we apply the effect on all 4 damage attributes, instead of simply boosting the
                // "damage multiplier" attribute because the "damage multiplier" attribute for missiles is on the
                // character and is therefore common to all missiles.
                fixupEffect.toEffect(
                    category = EffectCategory.always,
                    modifiers = knownAttributes.damage.map { damageAttribute ->
                        DogmaModifier(
                            domain = ModifierDomain.charID,
                            func = ModifierFunc.OwnerRequiredSkillModifier,
                            modifiedAttributeId = damageAttribute.id,
                            modifyingAttributeId = knownAttributes.damageMultiplierBonus.id,
                            operation = ModifierOperation.addPercent,
                            skillTypeId = skill.id
                        )
                    }
                )
            }
            else{
                // For drone skills, we boost the "damageMultiplier" attribute.
                // Note that although that attribute is stacking penalized, the FittingEngine does not penalize skill
                // effects (see stackingPenaltyGroup function), so it's OK for us to do this.
                fixupEffect.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        DogmaModifier(
                            domain = ModifierDomain.charID,
                            func = ModifierFunc.OwnerRequiredSkillModifier,
                            modifiedAttribute = knownAttributes.damageMultiplier,
                            modifyingAttribute = knownAttributes.damageMultiplierBonus,
                            operation = ModifierOperation.addPercent,
                            skillType = skill
                        )
                    )
                )
            }
        )

        // Associate the effect reference to the skill
        replaceDogmaType(dogmaSkill){
            it.withAddedEffectRefs(fixupEffect.toEffectRef())
        }
    }
}


/**
 * Fix the missile specialization skills (granting a rate-of-fire bonus), adding the necessary effects to them.
 * For some reason, the SDE does not specify the effects needed for these skills to actually work.
 * Amusingly, the XL missile skills do have the right effects/modifiers.
 */
private fun MutableEveData.fixMissileSpecializationSkillRofBonusEffects(){
    // To each of the skills we add an effect that modifies the duration attribute of the modules that require the skill
    val skillsToFix = listOf(
        "Light Missile Specialization",
        "Rocket Specialization",
        "Heavy Missile Specialization",
        "Heavy Assault Missile Specialization",
        "Cruise Missile Specialization",
        "Torpedo Specialization",
    )

    val skillByName = typesAndDogmaIn(knownCategories.skill).associateBy { it.first.name }
    for (skillName in skillsToFix){
        val (skill, dogmaSkill) = skillByName.getValue(skillName)

        // Add the effect
        val effectName = skillName.toCamelCaseIdentifier() + "SkillRofBonus"
        val fixupEffect = FixupEffects.fixupEffect(effectName)
        addEffect(
            fixupEffect.toEffect(
                category = EffectCategory.always,
                modifiers = listOf(
                    DogmaModifier(
                        domain = ModifierDomain.shipID,
                        func = ModifierFunc.LocationRequiredSkillModifier,
                        modifiedAttribute = knownAttributes.speed,
                        modifyingAttribute = knownAttributes.rofBonus,
                        operation = ModifierOperation.addPercent,
                        skillType = skill
                    )
                )
            )
        )

        // Associate the effect reference to the skill
        replaceDogmaType(dogmaSkill){
            it.withAddedEffectRefs(fixupEffect.toEffectRef())
        }
    }
}


/**
 * Some per-skill level ship bonuses to missile damage, like the Thermal+Kinetic bonus on the Caiman or all the missile
 * damage bonuses on the Jaguar, have the wrong domain and func specified (shipID and LocationRequiredSkillModifier).
 *
 * Effects that bonus missile flight time, speed, explosion radius and speed (on Missile Guidance Enhancers and
 * Missile Guidance Computers) have the same problem.
 *
 * This function fixes that.
 */
private fun MutableEveData.fixMissileBonusEffects(){
    val modifiedAttributeIds = with(knownAttributes){
        damage + listOf(maxVelocity, flightTime, explosionRadius, explosionVelocity)
    }.map { it.id }

    // Only fix modifiers where the skillTypeId is a missile skill
    val missileSkillIds = typesAndDogmaIn(knownCategories.skill)
        .filter { it.first.groupId == knownGroups.missileSkills.id }
        .map { it.first.id }
        .toSet()

    fun isWrongModifier(modifier: DogmaModifier) =
        (modifier.modifiedAttributeId in modifiedAttributeIds) &&
                (modifier.domain == ModifierDomain.shipID) &&
                (modifier.func == ModifierFunc.LocationRequiredSkillModifier) &&
                (modifier.modifyingAttributeId != null) &&
                (modifier.skillTypeId in missileSkillIds)

    for (effect in dogmaEffects){
        if (effect.modifiers.none { isWrongModifier(it) })
            continue

        replaceEffect(effect){
            val modifiers = effect.modifiers.map {
                if (isWrongModifier(it)){
                    it.copy(
                        domain = ModifierDomain.charID,
                        func = ModifierFunc.OwnerRequiredSkillModifier,
                    )
                }
                else
                    it
            }

//            println("Fixed domain/func of (hopefully) missile effect ${effect.name}")

            effect.copy(
                modifiers = modifiers
            )
        }
    }
}


/**
 * Fixes, or rather creates, the mechanism for weapon modules (including smartbombs) to have the correct damage
 * properties.
 * What we do here is:
 * 1. To each weapon module, we add 4 "weaponDamage" attributes, one for each damage type. Their base value is 0.
 * 2. To each charge we add a "weaponDamage" effect that, when the module is active, add the charge's own damage
 *    attributes to the module's "weaponDamage" attributes.
 * 3.1 For turrets, the mechanism for increasing their damage is via their "damageMultiplier" attribute (Gyrostabs, for
 *     example, increase it). So for them, we also add a "turretDamageMultiplier" effect that multiplies the
 *     "weaponDamage" attributes by the value of the "damageMultiplier" attribute.
 * 3.2 For missile/bomb launchers, the mechanism for increasing their damage is via a "missileDamageMultiplier"
 *     attribute in the CharacterType, which unfortunately doesn't have a corresponding effect to multiply the charge's
 *     damage attributes by it. We add such an effect.
 * 3.3 Smartbombs actually do not require fixing. They do not have charges, nor a mechanism for increasing their volley
 *     damage. So their regular "damage" attributes already perfectly describe the damage they do.
 *     However, we want all damage-dealing modules to use the same attribute to specify damage, so we "fix" smartbombs
 *     too by adding an effect which adds the value of their regular damage attributes to the fix "weaponDamage"
 *     attributes.

 * Altogether, each weapon module will have, per damage type, a property whose value is the volley damage of the
 * module in that damage type, and that property will be 0 when the module is not active.
 *
 * Note that use of [EffectCategory.active] on a charge probably does not exist in the original SDE. It required a
 * special addition to the fitting engine.
 */
private fun MutableEveData.fixWeaponModuleDamage(){
    val weaponAttributes = FixupAttrs.weaponDamage.map {
        it.toAttribute(stackable = false, highIsGood = true, unitId = Units.Hitpoints)
    }
    for (attr in weaponAttributes)
        addAttribute(attr)

    // Add the weaponDamage effect that adds the charge damage attributes to the module's weaponDamage attributes
    addEffect(
        FixupEffects.weaponDamage.toEffect(
            category = EffectCategory.active,  // Only when the module is active
            modifiers = knownAttributes.damage
                .zip(weaponAttributes)
                .map{
                    DogmaModifier(
                        domain = ModifierDomain.otherID,
                        func = ModifierFunc.ItemModifier,
                        modifiedAttribute = it.second,
                        modifyingAttribute = it.first,
                        operation = ModifierOperation.add,
                    )
                }
        )
    )

    // Add the effect that adds the chargeless weapon's damage attributes to its weaponDamage attributes
    addEffect(
        FixupEffects.chargelessWeaponDamage.toEffect(
            category = EffectCategory.active,  // Only when the module is active
            modifiers = knownAttributes.damage
                .zip(weaponAttributes)
                .map{
                    DogmaModifier(
                        domain = ModifierDomain.itemID,
                        func = ModifierFunc.ItemModifier,
                        modifiedAttribute = it.second,
                        modifyingAttribute = it.first,
                        operation = ModifierOperation.add,
                    )
                }
        )
    )

    // Add the turretDamageMultiplier effect, which multiplies the turret's weaponDamage attributes by the module's
    // "damageMultiplier" attribute.
    addEffect(
        FixupEffects.turretDamageMultiplier.toEffect(
            category = EffectCategory.always,
            modifiers = FixupAttrs.weaponDamage.map{ damageAttribute ->
                DogmaModifier(
                    domain = ModifierDomain.itemID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttributeId = damageAttribute.id,
                    modifyingAttributeId = knownAttributes.damageMultiplier.id,
                    operation = ModifierOperation.postMultiply
                )
            }
        )
    )

    // Add the missileDamageMultiplier effect, which multiplies the missile/bomb damage attributes by the character's
    // "missileDamageMultiplier" attribute.
    val missileLauncherOperationSkill = knownTypes.missileLauncherOperation
    addEffect(
        FixupEffects.missileDamageMultiplier.toEffect(
            category = EffectCategory.always,
            modifiers = knownAttributes.damage.map{ damageAttribute ->
                DogmaModifier(
                    domain = ModifierDomain.charID,
                    func = ModifierFunc.OwnerRequiredSkillModifier,
                    modifiedAttribute = damageAttribute,
                    modifyingAttribute = knownAttributes.missileDamageMultiplier,
                    operation = ModifierOperation.postMultiply,
                    skillType = missileLauncherOperationSkill
                )
            }
        )
    )

    // These are (empty) effects that appear to mark the various weapon systems
    // - useMissiles is attached to missile and bomb launchers (and probe launchers, unfortunately)
    // - targetAttack is attached to lasers and drones (wtf?)
    // - projectileFired is attached to projectile and hybrid guns
    // - chainLightning is attached to Vorton projectors
    // - empWave is attached to smartbombs
    val weaponEffects = listOf(
        knownEffects.useMissiles,
        knownEffects.targetAttack,
        knownEffects.projectileFired,
        knownEffects.chainLightning,
        knownEffects.targetDisintegratorAttack,
        knownEffects.empWave
    )
    val excludedGroupIds = listOf(
        knownGroups.scanProbeLauncher.id,
        knownGroups.interdictionSphereLauncher.id,
        knownGroups.surveyProbeLauncher.id
    )
    val weaponDamageAttributeValues = FixupAttrs.weaponDamage.map { it.withValue(0.0) }

    // Attach the attributes and effect to the weapon modules
    for ((module, dogmaModule) in typesAndDogmaIn(knownCategories.module)) {
        if (!dogmaModule.hasAnyEffect(weaponEffects))
            continue
        val hasCharges = dogmaModule.hasAnyAttribute(knownAttributes.chargeGroups)
        if (module.groupId in excludedGroupIds)
            continue

        val hasDamageMultiplier = dogmaModule.hasAttribute(knownAttributes.damageMultiplier)

        replaceDogmaType(dogmaModule) {
            it.copy(
                attributeValues = it.attributeValues + weaponDamageAttributeValues,
                effectRefs = buildList {
                    addAll(it.effectRefs)
                    if (!hasCharges)
                        add(FixupEffects.chargelessWeaponDamage.toEffectRef())
                    if (hasDamageMultiplier)
                        add(FixupEffects.turretDamageMultiplier.toEffectRef())
                }
            )
        }
    }

    // Attach the missileDamageMultiplier to the CharacterType
    val dogmaChar = dogmaType(EveData.CHARACTER_TYPE_ID)!!
    replaceDogmaType(dogmaChar){
        it.withAddedEffectRefs(FixupEffects.missileDamageMultiplier.toEffectRef())
    }


    // Attach the weaponDamage effect to the charges
    for (dogmaCharge in dogmaTypesIn(knownCategories.charge)){
        if (!dogmaCharge.hasAnyAttribute(knownAttributes.damage))
            continue

        replaceDogmaType(dogmaCharge){
            it.withAddedEffectRefs(FixupEffects.weaponDamage.toEffectRef())
        }
    }
}


/**
 * Drones have the same issue as weapon modules, as described in [fixWeaponModuleDamage].
 * The difference is that with drones, there is no module to which the "weaponDamage" attributes can be added.
 * So instead, we simply add "droneDamage" attributes directly to the drone, and the "droneDamage" effect sets them to
 * the values of the drone's regular "damage" attributes.
 */
private fun MutableEveData.fixDroneDamage(){
    val droneAttributes = FixupAttrs.droneDamage.map {
        it.toAttribute(stackable = false, highIsGood = true, unitId = Units.Hitpoints)
    }
    for (attr in droneAttributes)
        addAttribute(attr)

    // Add the droneDamage effect that adds the drone's damage attributes to the drone's droneDamage attributes
    addEffect(
        FixupEffects.droneDamage.toEffect(
            category = EffectCategory.active,  // Only when the drone is active
            modifiers = knownAttributes.damage
                .zip(droneAttributes)
                .map{
                    DogmaModifier(
                        domain = ModifierDomain.itemID,
                        func = ModifierFunc.ItemModifier,
                        modifiedAttribute = it.second,
                        modifyingAttribute = it.first,
                        operation = ModifierOperation.add,
                    )
                }
        )
    )

    // Add the droneDamageMultiplier effect, which multiplies the drone's damage attributes by the drone's
    // "damageMultiplier" attribute.
    addEffect(
        FixupEffects.droneDamageMultiplier.toEffect(
            category = EffectCategory.always,
            modifiers = knownAttributes.damage.map{ damageAttribute ->
                DogmaModifier(
                    domain = ModifierDomain.itemID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttribute = damageAttribute,
                    modifyingAttribute = knownAttributes.damageMultiplier,
                    operation = ModifierOperation.postMultiply,
                )
            }
        )
    )

    // Attach the attributes and effects to the drones
    val damageDroneEffect = knownEffects.targetAttack  // Only damage drones have this
    val droneDamageAttributeValues = FixupAttrs.droneDamage.map { it.withValue(0.0) }
    for (dogmaDrone in dogmaTypesIn(knownCategories.drone)){
        if (!dogmaDrone.hasEffect(damageDroneEffect))
            continue

        replaceDogmaType(dogmaDrone){
            it.copy(
                attributeValues = it.attributeValues + droneDamageAttributeValues,
                effectRefs = it.effectRefs +
                        FixupEffects.droneDamage.toEffectRef() +
                        FixupEffects.droneDamageMultiplier.toEffectRef()
            )
        }
    }
}


/**
 * Fixes the (triglavian) modules that have spool-up.
 *
 * To implement spoolup effects, we add these attributes:
 * - `spoolupCycles`: the number of cycles it has spooled up for. The default value is set to 0, and the user is
 *   expected to change it in order to see the damage values after the number of cycles he's interested in.
 * - `maxSpoolupCycles`: the maximum number of spoolup cycles for the module.
 * - `spoolupMultiplierBonus`: the bonus to the target attribute (e.g. damage, repair amount) based on the
 *   number of spoolup cycles.

 * and an effect:
 * - `computeSpoolupMultiplierBonus`: multiplies `spoolupMultiplierBonus` by `spoolupCycles`.
 *
 * A triglavian module can then add the value of `spoolupMultiplierBonus` to the target attribute.
 */
private fun MutableEveData.fixSpoolupModules(){
    addAttribute(FixupAttrs.spoolupCycles.toAttribute(stackable = false, highIsGood = null, unitId = Units.Unitless))
//    addAttribute(FixupAttrs.maxSpoolupCycles.toAttribute(stackable = false, highIsGood = true, unitId = Units.Unitless))
    addAttribute(FixupAttrs.spoolupMultiplierBonus.toAttribute(stackable = false, highIsGood = true, unitId = Units.Multiplier1))

    addEffect(
        FixupEffects.computeSpoolupMultiplierBonus.toEffect(
            category = EffectCategory.always,
            modifiers = listOf(
                DogmaModifier(
                    domain = ModifierDomain.itemID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttributeId = FixupAttrs.spoolupMultiplierBonus.id,
                    modifyingAttributeId = FixupAttrs.spoolupCycles.id,
                    operation = ModifierOperation.preMultiply
                )
            )
        )
    )

    fixSpoolupModule(
        typeName = "Entropic disintegrator",
        applySpoolupMultiplierBonusEffect = FixupEffects.applySpoolupDamageMultiplierBonus,
        targetAttribute = knownAttributes.damageMultiplier,
        moduleMarkerEffect = knownEffects.targetDisintegratorAttack,
        multiplierBonusPerCycle = knownAttributes.damageMultiplierBonusPerCycle,
        multiplierBonusMax = knownAttributes.damageMultiplierBonusMax,
    )
    fixSpoolupModule(
        typeName = "Mutadaptive repairer",
        applySpoolupMultiplierBonusEffect = FixupEffects.applySpoolupRepairAmountMultiplierBonus,
        targetAttribute = knownAttributes.armorDamageAmount,
        moduleMarkerEffect = knownEffects.mutadaptiveArmorRepairer,
        multiplierBonusPerCycle = knownAttributes.repairMultiplierBonusPerCycle,
        multiplierBonusMax = knownAttributes.repairMultiplierBonusMax
    )
}


/**
 * Fixes a set of modules that has a per-spoolup cycle bonus, such as Entropic disintegrators and Mutadaptive repairers.
 *
 * To each module we add:
 * - `spoolupCycles` with a value of 0.
 * - `maxSpoolupCycles` with the maximum number of spoolup cycles.
 * - `spoolupMultiplierBonus` with the value of the modules per-cycle bonus, in percent.
 *
 * We also add an effect that boosts the module's target attribute (damage or repair amount) by the value of
 * `spoolupMultiplierBonus`.
 */
private fun MutableEveData.fixSpoolupModule(


    /**
     * The name of the module type to display when an error occurs.
     */
    typeName: String,


    /**
     * The target attribute boosted per-cycle.
     */
    targetAttribute: DogmaAttribute,


    /**
     * The attribute holding the bonus increase per cycle.
     */
    multiplierBonusPerCycle: DogmaAttribute,


    /**
     * The attribute holding the maximum bonus.
     */
    multiplierBonusMax: DogmaAttribute,


    /**
     * The fixup effect to add to the module, which applies the spoolup multiplier to the target attribute.
     */
    applySpoolupMultiplierBonusEffect: FixupEffect,


    /**
     * The effect we use to detect the modules to fix.
     */
    moduleMarkerEffect: DogmaEffect,


){
    addEffect(
        applySpoolupMultiplierBonusEffect.toEffect(
            category = EffectCategory.always,
            modifiers = listOf(
                DogmaModifier(
                    domain = ModifierDomain.itemID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttributeId = targetAttribute.id,
                    modifyingAttributeId = FixupAttrs.spoolupMultiplierBonus.id,
                    operation = ModifierOperation.addPercent
                )
            )
        )
    )

    // Attach the attributes and effect to the weapon modules
    for ((module, dogmaModule) in typesAndDogmaIn(knownCategories.module)){
        if (!dogmaModule.hasEffect(moduleMarkerEffect))
            continue

        val multiplierBonusPerCycleValue = dogmaModule.getAttributeValue(multiplierBonusPerCycle)
        if (multiplierBonusPerCycleValue == null)
            throw IllegalStateException("$typeName ($module) is missing a ${multiplierBonusPerCycle.name} attribute")

        val multiplierBonusMaxValue = dogmaModule.getAttributeValue(multiplierBonusMax)
        if (multiplierBonusMaxValue == null)
            throw IllegalStateException("$typeName ($module) is missing a ${multiplierBonusMax.name} attribute")

        val spoolupDamageMultiplierBonusValue = multiplierBonusPerCycleValue * 100  // We want it in percent

        replaceDogmaType(dogmaModule){
            it.copy(
                attributeValues = it.attributeValues +
                        FixupAttrs.spoolupCycles.withValue(0.0) +
                        FixupAttrs.spoolupMultiplierBonus.withValue(spoolupDamageMultiplierBonusValue),
                effectRefs = it.effectRefs +
                        FixupEffects.computeSpoolupMultiplierBonus.toEffectRef() +
                        applySpoolupMultiplierBonusEffect.toEffectRef()
            )
        }
    }
}


/**
 * Implements a single meta-attribute, as explained in [fixMetaAttributes].
 */
private fun MutableEveData.fixMetaAttribute(
    fixupMetaAttribute: FixupAttr,
    metaAttributeId: (DogmaEffect) -> Int?,
    metaAttributeName: String
){
    // Add the meta attribute
    dogmaAttributes.add(fixupMetaAttribute.toAttribute(stackable = true, highIsGood = null, unitId = Units.AttributeId))

    // Add the attribute to each module/drone that needs it
    for (dogmaItem in dogmaTypesIn(knownCategories.module, knownCategories.drone)){
        val metaAttributeIds = dogmaItem.effectRefs
            .mapNotNull { effectRef -> dogmaEffects.find { it.id == effectRef.effectId } }
            .mapNotNull { metaAttributeId(it) }
            .filter { attributeId -> dogmaItem.attributeValues.any { it.attributeId == attributeId } }
            .distinct()
        if (metaAttributeIds.isEmpty())
            continue
        if (metaAttributeIds.size > 1)
            throw IllegalStateException("Item with id ${dogmaItem.id} has more than one $metaAttributeName")
        val attributeId = metaAttributeIds.first()

        replaceDogmaType(dogmaItem){
            it.withAddedAttributeValues(fixupMetaAttribute.withValue(attributeId.toDouble()))
        }
    }
}


/**
 * Projectile weapons don't need capacitor, but they nevertheless have a "capacitorNeed" attribute, which can be
 * affected, and that gets in the way of correctly displaying applied effects.
 * This removes the "capacitorNeed" attribute from projectile weapons.
 */
private fun MutableEveData.fixProjectileCapacitorNeed(){
    for ((projectileWeapon, dogmaProjectileWeapon) in typesAndDogmaTypesInGroup(knownGroups.projectileWeapon)){
        val capacitorNeedValue = dogmaProjectileWeapon.getAttributeValue(knownAttributes.capacitorNeed)
        if (capacitorNeedValue != 0.0)
            throw IllegalStateException("Projectile weapon ${projectileWeapon.name} has non-zero capacitor need")

        replaceDogmaType(dogmaProjectileWeapon){
            it.withRemovedAttributeValues(knownAttributes.capacitorNeed)
        }
    }
}


/**
 * Nosferatus don't need capacitor, but they nevertheless have a "capacitorNeed" attribute (with a value of 0), which
 * makes it error-prone to detect them as nosferatus.
 * This removes the "capacitorNeed" attribute from nosferatus.
 */
private fun MutableEveData.fixNosferatuCapacitorNeed(){
    for ((module, dogmaModule) in typesAndDogmaTypesInGroup(knownGroups.energyNosferatu)){
        val capacitorNeedValue = dogmaModule.getAttributeValue(knownAttributes.capacitorNeed)
        if (capacitorNeedValue != 0.0)
            throw IllegalStateException("Nosferatu ${module.name} has non-zero capacitor need")

        replaceDogmaType(dogmaModule){
            it.withRemovedAttributeValues(knownAttributes.capacitorNeed)
        }
    }
}


/**
 * Implements the [FixupAttrs.durationAttributeId], [FixupAttrs.rangeAttributeId], [FixupAttrs.falloffAttributeId] and
 * [FixupAttrs.trackingSpeedAttributeId] fix meta-attributes.
 * In the SDE, different modules (and drones) have different attributes representing their cycle time, optimal range,
 * falloff and tracking speed. The actual attribute used is (strangely) specified by an attribute of one of the item's
 * effects.
 * Instead of looking through the list of a module's effects in the app, we add a fixup attribute to the item itself
 * whose value is the id of the attribute that holds the actual value.
 */
private fun MutableEveData.fixMetaAttributes(){
    fixMetaAttribute(FixupAttrs.durationAttributeId, DogmaEffect::durationAttributeId, "durationAttributeId")
    fixMetaAttribute(FixupAttrs.rangeAttributeId, DogmaEffect::rangeAttributeId, "rangeAttributeId")
    fixMetaAttribute(FixupAttrs.falloffAttributeId, DogmaEffect::falloffAttributeId, "falloffAttributeId")
    fixMetaAttribute(FixupAttrs.trackingSpeedAttributeId, DogmaEffect::trackingSpeedAttributeId, "trackingSpeedAttributeId")
}



/**
 * Fixes the units of some attributes.
 *
 * We want to use the unit in order to correctly format the attribute values, so we set the unit according to how we
 * want that attribute to be displayed. For example, we want to display damageMultiplierBonus as, e.g. "+20%", but in
 * the SDE it has the same unit as inertiaModifier, which we want to display as "0.52x".
 */
private fun MutableEveData.fixAttributeUnits() {
    val attributeToUnitId = with(knownAttributes) {
        mapOf(
            damageMultiplier to UnitIds.MultiplierDisplayedAsAddedPercentage
        )
    }

    for ((i, attribute) in dogmaAttributes.withIndex()) {
        val unitId = attributeToUnitId[attribute]
        if (unitId != null) {
            dogmaAttributes[i] = attribute.copy(
                unitId = unitId
            )
        }
    }
}


/**
 * Assigns a range to select attributes.
 *
 * The values of some attributes, have an intrinsic range that can be exceeded by certain effects/operations. This
 * function assigns the correct ranges to such attributes.
 * For example, resonances (1-resistances) must be in the range [0-1], but effects that increase the resonance can cause
 * them to go outside this range, just due to the nature of the operation. For example, certain wormhole environment
 * effects can increase the resonance (reduce the resistance) by a certan factor. But if the base resosnance is high
 * enough already, it will end up above 1. The range is needed to clip the resonance.
 *
 * Note that not all attributes with intrinsic ranges get assigned one here; only the ones with a known effect that can
 * cause them to exceed the range.
 */
private fun MutableEveData.addAttributeRanges() {
    val resonanceAttributes = with(knownAttributes) {
        shieldResonance + armorResonance + structureResonance
    }.toSet()

    for ((i, attribute) in dogmaAttributes.withIndex()) {
        if (attribute in resonanceAttributes) {
            dogmaAttributes[i] = attribute.copy(
                range = 0.0..1.0
            )
        }
    }
}


/**
 * Fixes the values of the "resistanceKiller" attribute.
 * This is used to set the resistances to 0 when a polarized module is activated.
 * For some reason, there are two effects that do this:
 * - "resistanceKillerHullAll" which (correctly) sets the value of all hull resonances to 1.0, the value of the
 *   "resistanceKillerHull" attribute.
 * - "resistanceKillerShieldArmorAll" which (incorrectly) sets the value of all shield and armor resonances to 100.0,
 *   the value of the "resistanceKiller" attribute.
 * Why is this split into two effects, and why is one correct while the other is incorrect?
 * Only the Norse gods know.
 */
private fun MutableEveData.fixResistanceKillers(){
    val resistanceKiller = knownAttributes.resistanceKiller
    for (dogmaModule in dogmaTypesIn(knownCategories.module)){
        val resistanceKillerAttributeValue =
            dogmaModule.attributeValues.find { it.attributeId == resistanceKiller.id } ?: continue

        val resistanceKillerValue = resistanceKillerAttributeValue.value
        if (resistanceKillerValue > 1.0){
            replaceDogmaType(dogmaModule){
                it.replaceAttributeValue(
                    resistanceKiller.withValue(resistanceKillerValue/100.0)
                )
            }
        }
    }
}


/**
 * Fixes the Capital Emergency Hull Energizer group of modules.
 * For some reason, their effect, "emergencyHullEnergizer" doesn't have any modifiers to actually do anything.
 */
private fun MutableEveData.fixEmergencyHullEnergizerEffect() {
    replaceEffect(knownEffects.emergencyHullEnergizer){ effect ->
        effect.withAddedModifiers(
            knownAttributes.hullDamageResonance.zip(knownAttributes.structureResonance).map {
                DogmaModifier(
                    domain = ModifierDomain.shipID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttribute = it.second,
                    modifyingAttribute = it.first,
                    operation = ModifierOperation.preMultiply,
                )
            }
        )
    }
}


/**
 * The CharacterType is supposed to have the "maxActiveDrones" attribute, but for some reason it does not.
 * This function adds it.
 */
private fun MutableEveData.fixMaxActiveDronesAttribute() {
    val dogmaChar = dogmaType(EveData.CHARACTER_TYPE_ID)!!
    replaceDogmaType(dogmaChar){
        it.withAddedAttributeValues(knownAttributes.maxActiveDrones.withValue(0.0))
    }
}


/**
 * Small ASB and Small remote ASB have a "chargeSize" attribute with value of 0, which doesn't appear anywhere else, and
 * is anyway not used to determine the cap boosters that can be loaded into them (in fact, any charge that fits by
 * volume can be loaded into them).
 * This removes this attribute from them.
 */
private fun MutableEveData.fixSmallAsbZeroChargeSize(){
    val boosters = dogmaTypesInGroup(knownGroups.ancillaryShieldBooster) +
            dogmaTypesInGroup(knownGroups.ancillaryRemoteShieldBooster)
    for (dogmaType in boosters){
        val chargeSize = dogmaType.attributeValues.find { it.attributeId == knownAttributes.chargeSize.id }
        if (chargeSize?.value == 0.0){
            replaceDogmaType(dogmaType){
                it.copy(
                    attributeValues = it.attributeValues - chargeSize
                )
            }
        }
    }
}


/**
 * Some modules have a "maxGroupActive" value of 0 (Gas Cloud Scoops, for example), which was probably intended to mean
 * no restriction on number of active modules in group.
 * For simplicity, we remove this attribute from those modules.
 */
private fun MutableEveData.fixZeroMaxGroupActive(){
    val maxGroupActiveAttributeId = knownAttributes.maxGroupActive.id
    for (dogmaModule in dogmaTypesIn(knownCategories.module)){
        val maxGroupActiveValue = dogmaModule.attributeValues.find { it.attributeId == maxGroupActiveAttributeId }
        if ((maxGroupActiveValue != null) && (maxGroupActiveValue.value == 0.0)){
            replaceDogmaType(dogmaModule){
                it.copy(
                    attributeValues = it.attributeValues - maxGroupActiveValue
                )
            }
        }
    }
}


/**
 * The effect bonus to Mercoxit mining lasers by Mercoxit Mining Crystal Optimization (a rig) mistakenly has
 * `domain=shipID, func=LocationRequiredSkillModifier`. It should be `domain=charID, func=OwnerRequiredSkillModifier`
 * to affect charges.
 * This fixes it.
 */
private fun MutableEveData.fixMercoxitMiningLaserBonusEffect(){
    replaceEffect(knownEffects.mercoxitCrystalBonus){
        val modifier = it.modifiers.first().copy(
            domain = ModifierDomain.charID,
            func = ModifierFunc.OwnerRequiredSkillModifier
        )
        it.copy(
            modifiers = listOf(modifier)
        )
    }
}


/**
 * The SDE does specify the command burst effects. The existing mechanism in the SDE is as follows:
 * 1. Each command burst module has a list of "warfareBuff${N}Value" (N is currently 4) attributes, specifying the
 *    strength of the effect.
 * 2. The command burst charges all have the same "chargeBonusWarfareCharge" effect which:
 *    A. Copies all the charge's "warfareBuff${N}ID" attributes to the command burst module.
 *    B. Multiplies each of the command burst module's "warfareBuff${N}Value" attributes by the charge's corresponding
 *       "warfareBuff${N}Multiplier" attribute.
 * The values of the "warfareBuff${N}ID" attribute are 10 through 25, and they specify the type of the effect. The
 * effects themselves do not appear to be specified anywhere. We define them ourselves in [WarfareBuffs].
 *
 * The mechanism for applying the buff effects is as follows:
 * 1. We add a new item type, "WarfareBuffsType" which the fitting engine takes into consideration, similarly to the
 *    "CharacterType". The "WarfareBuffsType" has an effect and a corresponding "value" attribute for each
 *    [WarfareBuff]. By default, the effect's value attribute is 0, so the effect does nothing.
 * 2. Each command burst module is given effects which, when the "warfareBuff${N}ID" attribute has the correct buff
 *    id value, copy the value of the command burst's "warfareBuff${N}Value" attribute the corresponding value
 *    attribute of the "WarfareBuffsType".
 * 3. The value is actually copied only when the abs(value) > abs(target), which causes only the command burst with the
 *    largest bonus apply.
 */
private fun MutableEveData.fixCommandBursts() {
    val buffIds = WarfareBuffs.byBuffId.keys
    val buffs = WarfareBuffs.byBuffId.values

    // Add the Command Bursts group, type and dogma type
    val warfareBuffsCategory = FixupCategories.warfareBuff.toCategory()
    val warfareBuffsGroup = FixupGroups.warfareBuffs.toGroup(warfareBuffsCategory)
    val warfareBuffsType = FixupTypes.warfareBuffs.toType(warfareBuffsGroup)
    val warfareBuffsDogmaType = createWarfareBuffsDogmaType()
    categories.add(warfareBuffsCategory)
    groups.add(warfareBuffsGroup)
    types.add(warfareBuffsType)
    dogmaTypes.add(warfareBuffsDogmaType)

    // Add all the attributes and effects required by the buffs
    for (buff in buffs) {
        // highIsGood should not actually be `null` here, but it's not used right now anyway
        addAttribute(buff.valueAttribute.toAttribute(stackable = true, highIsGood = null, unitId = Units.BonusPercentAdditive1))
        for (effect in buff.effects(this))
            addEffect(effect)
    }

    // For each buff id, find the "warfareBuff${N}ID" attributes it is placed into by the charges.
    val commandBurstCharges = listOf(
        knownGroups.shieldCommandBurstCharges,
        knownGroups.armorCommandBurstCharges,
        knownGroups.skirmishCommandBurstCharges,
        knownGroups.informationCommandBurstCharges,
        knownGroups.miningForemanBurstCharges
    ).flatMap { dogmaTypesInGroup(it) }
    val warfareBuffIdAttributesByBuffId = buffIds.associateWith { buffId ->
        commandBurstCharges.flatMap { charge ->
            knownAttributes.warfareBuffIds.filter { buffIdAttr ->
                charge.getAttributeValue(buffIdAttr)?.toInt() == buffId
            }
        }
    }

    // Add the activateWarfareBuffEffects, which set the buff effect's value attribute
    val activateWarfareEffectsByBuffId = buffIds.associateWith { mutableListOf<FixupEffect>() }
    for ((buffId, warfareBuffIdAttributes) in warfareBuffIdAttributesByBuffId) {
        val buff = WarfareBuffs.byBuffId.getValue(buffId)
        for (warfareBuffIdAttribute in warfareBuffIdAttributes) {
            val slotIndex = knownAttributes.warfareBuffIds.indexOf(warfareBuffIdAttribute)
            val fixupEffect = FixupEffects.fixupEffect("activateWarfare${buff.name}Buff${slotIndex}Effects")
            activateWarfareEffectsByBuffId.getValue(buffId).add(fixupEffect)
            addEffect(
                fixupEffect.toEffect(
                    category = EffectCategory.active,
                    conditionAttributeId = warfareBuffIdAttribute.id,
                    conditionAttributeValue = buff.buffId,
                    modifiers = listOf(
                        DogmaModifier(
                            domain = ModifierDomain.warfareBuffsID,
                            func = ModifierFunc.ItemModifier,
                            modifiedAttributeId = buff.valueAttribute.id,
                            modifyingAttributeId = knownAttributes.warfareBuffValues[slotIndex].id,
                            operation = ModifierOperation.setMaxAbsValue,
                        )
                    )
                )
            )
        }
    }

    // Add all the activateWarfareBuffEffects to the command burst modules
    for ((module, dogmaModule) in typesAndDogmaTypesInGroup(knownGroups.commandBursts)){
        when (module.variationParentTypeID){
            knownTypes.shieldCommandBurst.id -> {
                fixCommandBurstModuleWarfareBuffEffects(
                    dogmaModule = dogmaModule,
                    activateWarfareEffectsByBuffId = activateWarfareEffectsByBuffId,
                    WarfareBuffs.SHIELD_RESISTANCES,
                    WarfareBuffs.SHIELD_BOOSTERS,
                    WarfareBuffs.SHIELD_HITPOINTS
                )
            }
            knownTypes.armorCommandBurst.id -> {
                fixCommandBurstModuleWarfareBuffEffects(
                    dogmaModule = dogmaModule,
                    activateWarfareEffectsByBuffId = activateWarfareEffectsByBuffId,
                    WarfareBuffs.ARMOR_RESISTANCES,
                    WarfareBuffs.ARMOR_REPAIRERS,
                    WarfareBuffs.ARMOR_HITPOINTS
                )
            }
            knownTypes.skirmishCommandBurst.id -> {
                fixCommandBurstModuleWarfareBuffEffects(
                    dogmaModule = dogmaModule,
                    activateWarfareEffectsByBuffId = activateWarfareEffectsByBuffId,
                    WarfareBuffs.SIGNATURE_RADIUS,
                    WarfareBuffs.TACKLE_RANGE,
                    WarfareBuffs.PROP_MOD_SPEED_FACTOR,
                    WarfareBuffs.AGILITY
                )
            }
            knownTypes.informationCommandBurst.id -> {
                fixCommandBurstModuleWarfareBuffEffects(
                    dogmaModule = dogmaModule,
                    activateWarfareEffectsByBuffId = activateWarfareEffectsByBuffId,
                    WarfareBuffs.SCAN_RESOLUTION,
                    WarfareBuffs.ELECTRONIC_SUPERIORITY,
                    WarfareBuffs.SENSOR_STRENGTH,
                    WarfareBuffs.EWAR_RESISTANCE,
                    WarfareBuffs.TARGETING_RANGE
                )
            }
            knownTypes.miningForemanBurst.id -> {}
        }
    }
}


/**
 * Creates the [DogmaType] for the "Warfare Buffs" type through which we apply the warfare buffs.
 */
private fun MutableEveData.createWarfareBuffsDogmaType(): DogmaType{
    val warfareBuffs = WarfareBuffs.byBuffId.values
    return DogmaType(
        id = FixupTypes.warfareBuffs.id,
        attributeValues = warfareBuffs.map { it.valueAttribute.withValue(0.0) },
        effectRefs = warfareBuffs.flatMap { buff ->
            buff.effects(this).map { DogmaEffectRef(it.id) }
        }
    )
}


/**
 * Adds the effect that activates the warfare buff to the command burst module.
 */
private fun MutableEveData.fixCommandBurstModuleWarfareBuffEffects(
    dogmaModule: DogmaType,
    activateWarfareEffectsByBuffId: Map<Int, Collection<FixupEffect>>,
    vararg warfareBuffs: WarfareBuff,
){
    replaceDogmaType(dogmaModule){ item ->
        item.copy(
            attributeValues = item.attributeValues +
                    knownAttributes.warfareBuffIds.map { it.withValue(0.0) },
            effectRefs = item.effectRefs +
                    warfareBuffs.flatMap { buff ->
                        activateWarfareEffectsByBuffId.getValue(buff.buffId).map { it.toEffectRef() }
                    }
        )
    }
}


/**
 * Encapsulates a single command burst effect, e.g. the bonus to shield resistances.
 */
private abstract class WarfareBuff(


    /**
     * The "warfareBuff${N}ID" value that corresponds to this buff.
     */
    val buffId: Int,


    /**
     * A short, camel-case name for this buff.
     */
    val name: String


) {


    /**
     * The buff's attribute, which will receive the value of the "warfareBuff${N}Value" attribute.
     */
    abstract val valueAttribute: FixupAttr


    /**
     * The effects that need to be added to implement this buff.
     */
    abstract fun effects(eveData: MutableEveData): Collection<DogmaEffect>


}


/**
 * The warfare effects.
 */
private object WarfareBuffs{


    /**
     * A modifier boosting the given ship attribute.
     */
    private fun WarfareBuff.shipAttributeModifier(modifiedAttribute: DogmaAttribute) =
        DogmaModifier(
            domain = ModifierDomain.shipID,
            func =  ModifierFunc.ItemModifier,
            modifiedAttributeId = modifiedAttribute.id,
            modifyingAttributeId = valueAttribute.id,
            operation = ModifierOperation.addPercent,
        )


    /**
     * A modifier boosting the given module attribute of modules requiring the given skill.
     */
    private fun WarfareBuff.moduleAttributeModifier(modifiedAttribute: DogmaAttribute, requiredSkill: Type) =
        DogmaModifier(
            domain = ModifierDomain.shipID,
            func = ModifierFunc.LocationModifier,
            modifiedAttributeId = modifiedAttribute.id,
            modifyingAttributeId = valueAttribute.id,
            operation = ModifierOperation.addPercent,
            skillTypeId = requiredSkill.id
        )


    /**
     * A modifier boosting the given module attribute of modules in the given group.
     */
    private fun WarfareBuff.moduleAttributeModifier(modifiedAttribute: DogmaAttribute, group: Group) =
        DogmaModifier(
            domain = ModifierDomain.shipID,
            func = ModifierFunc.LocationModifier,
            modifiedAttributeId = modifiedAttribute.id,
            modifyingAttributeId = valueAttribute.id,
            operation = ModifierOperation.addPercent,
            groupId = group.id
        )


    /**
     * The buff to shield resistances.
     */
    val SHIELD_RESISTANCES = object : WarfareBuff(buffId = 10, "ShieldResonance") {

        override val valueAttribute = FixupAttrs.warfareShieldResonanceBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareShieldResonanceBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = knownAttributes.shieldResonance.map { shipAttributeModifier(it) }
                )
            )
        }

    }


    /**
     * The buff to shield booster (local and remote) duration and capacitor need.
     */
    val SHIELD_BOOSTERS = object : WarfareBuff(buffId = 11, "ShieldBoosters") {

        override val valueAttribute = FixupAttrs.warfareShieldBoostersBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            // The skills required by the affected modules
            val skillTypes = with(knownTypes){
                listOf(shieldOperation, shieldEmissionSystems, capitalShieldEmissionSystems)
            }
            val shieldBoosterDurationModifiers =
                skillTypes.map { skill -> moduleAttributeModifier(knownAttributes.duration, requiredSkill = skill) }
            val shieldBoosterCapNeedModifiers =
                skillTypes.map { skill -> moduleAttributeModifier(knownAttributes.capacitorNeed, requiredSkill = skill) }

            listOf(
                FixupEffects.warfareShieldBoostersBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = shieldBoosterDurationModifiers + shieldBoosterCapNeedModifiers
                )
            )
        }

    }


    /**
     * The buff to shield capacity.
     */
    val SHIELD_HITPOINTS = object : WarfareBuff(buffId = 12, "ShieldHitpoints") {

        override val valueAttribute = FixupAttrs.warfareShieldHitpointsBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareShieldHitpointsBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.shieldHp)
                    )
                )
            )
        }

    }


    /**
     * The buff to armor resistances.
     */
    val ARMOR_RESISTANCES = object : WarfareBuff(buffId = 13, "ArmorResonance") {

        override val valueAttribute = FixupAttrs.warfareArmorResonanceBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareArmorResonanceBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = knownAttributes.armorResonance.map { shipAttributeModifier(it) }
                )
            )
        }

    }


    /**
     * The buff to shield booster (local and remote) duration and capacitor need.
     */
    val ARMOR_REPAIRERS = object : WarfareBuff(buffId = 14, "ArmorRepairers") {

        override val valueAttribute = FixupAttrs.warfareArmorRepairersBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            // The skills required by the affected modules
            val skillTypes = with(knownTypes){
                listOf(repairSystems, remoteArmorRepairSystems, capitalRemoteArmorRepairSystems)
            }
            val armorRepairerDurationModifiers =
                skillTypes.map { skill -> moduleAttributeModifier(knownAttributes.duration, requiredSkill = skill) }
            val armorRepairerCapNeedModifiers =
                skillTypes.map { skill -> moduleAttributeModifier(knownAttributes.capacitorNeed, requiredSkill = skill) }
            listOf(
                FixupEffects.warfareArmorRepairersBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = armorRepairerDurationModifiers + armorRepairerCapNeedModifiers
                )
            )
        }

    }


    /**
     * The buff to armor hitpoints.
     */
    val ARMOR_HITPOINTS = object : WarfareBuff(buffId = 15, "ArmorHitpoints") {

        override val valueAttribute = FixupAttrs.warfareArmorHitpointsBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareArmorHitpointsBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.armorHp)
                    )
                )
            )
        }

    }


    /**
     * The buff to scan resolution.
     */
    val SCAN_RESOLUTION = object : WarfareBuff(buffId = 16, "ScanResolution") {

        override val valueAttribute = FixupAttrs.warfareScanResolutionBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareScanResolutionBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.scanResolution)
                    )
                )
            )
        }

    }


    /**
     * The electronic superiority buff.
     */
    val ELECTRONIC_SUPERIORITY = object : WarfareBuff(buffId = 17, "ElectronicSuperiority") {

        override val valueAttribute = FixupAttrs.warfareElectronicSuperiorityBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            val sensorLinking = knownTypes.sensorLinking
            val targetPainting = knownTypes.targetPainting
            val weaponDisruption = knownTypes.weaponDisruption
            val skillTypes = listOf(sensorLinking, targetPainting, weaponDisruption)

            // Bonuses to optimal and falloff of remote sensor dampeners, target painters, weapon disrutors and ECM
            val allRange = listOf(knownAttributes.maxRange, knownAttributes.falloffEffectiveness)
                .flatMap { attribute ->
                    skillTypes.map { skill -> moduleAttributeModifier(attribute, requiredSkill = skill) } +
                            moduleAttributeModifier(attribute, group = knownGroups.ecm)
                }

            val sensorDampenerEffectiveness = listOf(
                moduleAttributeModifier(knownAttributes.scanResolutionBonus, requiredSkill = sensorLinking),
                moduleAttributeModifier(knownAttributes.maxTargetRangeBonus, requiredSkill = sensorLinking)
            )

            val targetPainterEffectiveness =
                moduleAttributeModifier(knownAttributes.signatureRadiusBonus, requiredSkill = targetPainting)

            val weaponDisruptionAttributes = with(knownAttributes){
                listOf(
                    maxRangeBonus, falloffBonus, trackingSpeedBonus,
                    missileVelocityBonus, explosionDelayBonus, aoeVelocityBonus, aoeCloudSizeBonus
                )
            }
            val weaponDisruptionEffectiveness = weaponDisruptionAttributes.map { attribute ->
                moduleAttributeModifier(attribute, requiredSkill = weaponDisruption)
            }

            val ecmEffectiveness = knownAttributes.scanStrengthBonus.map { attribute ->
                moduleAttributeModifier(attribute, group = knownGroups.ecm)
            }

            listOf(
                FixupEffects.warfareElectronicSuperiorityBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = allRange +
                            sensorDampenerEffectiveness +
                            targetPainterEffectiveness +
                            weaponDisruptionEffectiveness +
                            ecmEffectiveness
                )
            )
        }

    }


    /**
     * The buff to sensor strength.
     */
    val SENSOR_STRENGTH = object : WarfareBuff(buffId = 18, "SensorStrength") {

        override val valueAttribute = FixupAttrs.warfareSensorStrengthBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareSensorStrengthBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = knownAttributes.scanStrength.map { shipAttributeModifier(it) }
                )
            )
        }

    }


    /**
     * The buff to sensor dampener and weapon disruption resistance.
     */
    val EWAR_RESISTANCE = object : WarfareBuff(buffId = 19, "EwarResistance") {

        override val valueAttribute = FixupAttrs.warfareEwarResistance

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareEwarResistance.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.sensorDampenerResistance),
                        shipAttributeModifier(knownAttributes.weaponDisruptionResistance),
                    )
                )
            )
        }

    }


    /**
     * The buff to signature radius.
     */
    val SIGNATURE_RADIUS = object : WarfareBuff(buffId = 20, "SignatureRadius") {

        override val valueAttribute = FixupAttrs.warfareSignatureRadiusBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareSignatureRadiusBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.signatureRadius)
                    )
                )
            )
        }

    }


    /**
     * The buff to warp disruptor, warp scrambler and web range.
     */
    val TACKLE_RANGE = object : WarfareBuff(buffId = 21, "TackleRange") {

        override val valueAttribute = FixupAttrs.warfareTackleRangeBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            val groups = listOf(knownGroups.warpScrambler, knownGroups.stasisWeb)
            listOf(
                FixupEffects.warfareTackleRangeBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = groups.map { moduleAttributeModifier(knownAttributes.maxRange, group = it) }
                )
            )
        }

    }


    /**
     * The buff to propulsion module speed factor.
     */
    val PROP_MOD_SPEED_FACTOR = object : WarfareBuff(buffId = 22, "PropulsionModuleSpeedFactor") {

        override val valueAttribute = FixupAttrs.warfareSpeedFactorBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareSpeedFactorBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        // The description of Rapid Deployment Charge says it affects "thrust potential", which hints to
                        // the speedBoostFactor (Thrust) attribute, but in-game, it modifies the speedFactor (aka
                        // "Maximum Velocity Bonus") attribute.
                        moduleAttributeModifier(knownAttributes.speedFactor, group = knownGroups.propulsionModule)
                    )
                )
            )
        }

    }


    /**
     * The buff to targeting range.
     */
    val TARGETING_RANGE = object : WarfareBuff(buffId = 26, "TargetingRange") {

        override val valueAttribute = FixupAttrs.warfareTargetingRangeBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareTargetingRangeBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.maxTargetRange)
                    )
                )
            )
        }

    }


    /**
     * The buff to ship agility.
     */
    val AGILITY = object : WarfareBuff(buffId = 60, "Agility") {

        override val valueAttribute = FixupAttrs.warfareAgilityBonus

        override fun effects(eveData: MutableEveData) = with(eveData){
            listOf(
                FixupEffects.warfareAgilityBonus.toEffect(
                    category = EffectCategory.always,
                    modifiers = listOf(
                        shipAttributeModifier(knownAttributes.agility)
                    )
                )
            )
        }

    }


    /**
     * Maps all the buffs by their buff id.
     */
    val byBuffId: Map<Int, WarfareBuff> = listOf(
        SHIELD_RESISTANCES,
        SHIELD_BOOSTERS,
        SHIELD_HITPOINTS,
        ARMOR_RESISTANCES,
        ARMOR_REPAIRERS,
        ARMOR_HITPOINTS,
        SCAN_RESOLUTION,
        ELECTRONIC_SUPERIORITY,
        SENSOR_STRENGTH,
        EWAR_RESISTANCE,
        SIGNATURE_RADIUS,
        TACKLE_RANGE,
        PROP_MOD_SPEED_FACTOR,
        TARGETING_RANGE,
        AGILITY
    ).associateBy { it.buffId }


}


/**
 * Fix the Reactive Armor Hardener, by adding to its effect (`adaptiveArmorHardener`) actual modifiers that affect
 * this ship's armor resistances.
 */
private fun MutableEveData.fixReactiveArmorHardener() {
    replaceEffect(knownEffects.adaptiveArmorHardener){ effect ->
        effect.withAddedModifiers(
            knownAttributes.armorResonance.map {
                DogmaModifier(
                    domain = ModifierDomain.shipID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttribute = it,
                    modifyingAttribute = it,
                    operation = ModifierOperation.preMultiply,
                )
            }
        )
    }
}


/**
 * The effect for the penalty on missile explosion radius for boosters (e.g. Exile) has the wrong `skillTypeId`.
 * This fixes it.
 */
private fun MutableEveData.fixBoosterMissileExplosionCloudPenaltyFixedEffect(){
    val missileLauncherOperation = knownTypes.missileLauncherOperation
    replaceEffect(knownEffects.boosterMissileExplosionCloudPenaltyFixed){ effect ->
        val modifiers = effect.modifiers.toMutableList()

        modifiers[0].let {
            if (it.skillTypeId == missileLauncherOperation.id) {
                System.err.println("boosterMissileExplosionCloudPenaltyFixed already has the correct skillTypeId")
                return@replaceEffect effect
            }
            if (it.skillTypeId != 3452) {
                System.err.println(
                    "boosterMissileExplosionCloudPenaltyFixed has an unexpected, but still possibly wrong, skillTypeId"
                )
            }
        }

        modifiers[0] = modifiers[0].copy(
            skillTypeId = missileLauncherOperation.id
        )
        effect.copy(modifiers = modifiers)
    }
}


/**
 * Subsystems have a `slotModifier` effect, but it's empty.
 * This adds the correct modifiers to it.
 */
private fun MutableEveData.fixSlotModifierEffect(){
    replaceEffect(knownEffects.slotModifier){ slotModifierEffect ->
        with(knownAttributes){
            val slotAndModifierAttributes = listOf(
                highSlots to highSlotModifier,
                medSlots to medSlotModifier,
                lowSlots to lowSlotModifier
            )

            val modifiers = slotAndModifierAttributes.map { (slotAttribute, slotModifierAttribute) ->
                DogmaModifier(
                    domain = ModifierDomain.shipID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttribute = slotAttribute,
                    modifyingAttribute = slotModifierAttribute,
                    operation = ModifierOperation.add,
                )
            }

            slotModifierEffect.withAddedModifiers(modifiers)
        }
    }

}


/**
 * Subsystems have a `fixHardpointModifierEffect` effect, but it's empty.
 * This adds the correct modifiers to it.
 */
private fun MutableEveData.fixHardpointModifierEffect(){
    replaceEffect(knownEffects.hardPointModifier){ hardpointModifierEffect ->
        with(knownAttributes){
            val hardpointAndModifierAttributes = listOf(
                turretHardpoints to turretHardPointModifier,
                launcherHardpoints to launcherHardPointModifier
            )

            val modifiers = hardpointAndModifierAttributes.map { (hardpointAttribute, hardpointModifierAttribute) ->
                DogmaModifier(
                    domain = ModifierDomain.shipID,
                    func = ModifierFunc.ItemModifier,
                    modifiedAttribute = hardpointAttribute,
                    modifyingAttribute = hardpointModifierAttribute,
                    operation = ModifierOperation.add,
                )
            }

            hardpointModifierEffect.withAddedModifiers(modifiers)
        }
    }
}


/**
 * In ships that have bonuses/penalties from security status (e.g. Pacifier, Cobra), the bonuses are capped.
 * There are no effects, however, that specify this capping.
 * This function adds these effects.
 */
private fun MutableEveData.fixSecurityStatusEffects() {
    // The way we implement the capping is by capping the security status attribute.
    // This isn't strictly correct, as we should be capping the final attribute that gives the bonus, but
    // this means we'll need to add as many effects as there are bonuses. Capping the security status works (for now)
    // because for every ship, the cap for all bonuses is the same factor. For example, the Cobra has two bonuses
    // * -3.75x bonus to scramblers and disruptors range, with a cap of 37.5%
    // * -7.5x bonus to webifiers, with a cap of 75%
    // So by capping the inverseCappedSecStatus to be between -10 and 0, we achieve both.
    // If there is ever a ship with several security-status bonuses with caps that are different factors of the
    // security status, we'll need to cap the target attribute instead.

    addAttribute(FixupAttrs.securityStatusEffectMax.toAttribute(stackable = true, highIsGood = null, unitId = null))
    addAttribute(FixupAttrs.securityStatusEffectMin.toAttribute(stackable = true, highIsGood = null, unitId = null))

    addEffect(FixupEffects.capInverseSecStatusEffect.toEffect(
        category = EffectCategory.always,
        modifiers = listOf(
            DogmaModifier(
                domain = ModifierDomain.shipID,
                func = ModifierFunc.ItemModifier,
                modifiedAttributeId = knownAttributes.inverseCappedSecStatus.id,
                modifyingAttributeId = FixupAttrs.securityStatusEffectMin.id,
                operation = ModifierOperation.coerceAtLeast
            ),
            DogmaModifier(
                domain = ModifierDomain.shipID,
                func = ModifierFunc.ItemModifier,
                modifiedAttributeId = knownAttributes.inverseCappedSecStatus.id,
                modifyingAttributeId = FixupAttrs.securityStatusEffectMax.id,
                operation = ModifierOperation.coerceAtMost
            )
        )
    ))

    val secStatusRangeByShipName = listOf(
        "Pacifier" to Pair(-5.0, 0.0),
        "Enforcer" to Pair(-5.0, 0.0),
        "Marshal" to Pair(-5.0, 0.0),
        "Sidewinder" to Pair(-10.0, 0.0),
        "Cobra" to Pair(-10.0, 0.0),
        "Python" to Pair(-7.5, 0.0)
    )

    for ((shipName, secStatusRange) in secStatusRangeByShipName) {
        val (min, max) = secStatusRange
        replaceDogmaType(dogmaType(shipName)) { ship ->
            ship
                .withAddedAttributeValues(
                    FixupAttrs.securityStatusEffectMin.withValue(min),
                    FixupAttrs.securityStatusEffectMax.withValue(max),
                )
                .withAddedEffectRefs(FixupEffects.capInverseSecStatusEffect.toEffectRef())
        }
    }
}


/**
 * For some strange reason, sentry drones have a very small speed attribute.
 * This removes it.
 */
private fun MutableEveData.removeSentryDronesSpeed() {
    val maxVelocityAttribute = knownAttributes.maxVelocity
    for (dogmaDrone in dogmaTypesIn(knownCategories.drone)){
        val maxVelocity = dogmaDrone.attributeValues.find { it.attributeId == maxVelocityAttribute.id }
        if ((maxVelocity != null) && (maxVelocity.value < 2E-5)){
            replaceDogmaType(dogmaDrone){
                it.copy(
                    attributeValues = it.attributeValues - maxVelocity
                )
            }
        }
    }
}


/**
 * Interdiction probes have a "maxVelocity" attribute (with value 0).
 * Scan probes have a "maxVelocity" attribute, with varying values.
 * Survey probes also have a "maxVelocity" attribute, all with a value of 30km/s.
 *
 * This removes those, because we don't want these probes to have a "range".
 */
private fun MutableEveData.removeProbeSpeed() {
    val maxVelocityAttribute = knownAttributes.maxVelocity
    val probes = dogmaTypesInGroup(knownGroups.interdictionProbe) +
            dogmaTypesInGroup(knownGroups.scannerProbe) +
            dogmaTypesInGroup(knownGroups.surveyProbe)
    for (dogmaProbe in probes){
        val maxVelocity = dogmaProbe.attributeValues.find { it.attributeId == maxVelocityAttribute.id }
        if (maxVelocity != null){
            replaceDogmaType(dogmaProbe){
                it.copy(
                    attributeValues = it.attributeValues - maxVelocity
                )
            }
        }
    }
}


/**
 * Removes whitespace from all effect and attribute names.
 */
private fun MutableEveData.removeWhitespaceFromAttributeAndEffectNames() {
    fun <T: NamedEntity> MutableList<T>.fixNames(withName: T.(String) -> T){
        val iterator = listIterator()
        while (iterator.hasNext()){
            val item = iterator.next()
            val nameWithoutWhitespace = item.name.filter { !it.isWhitespace() }
            if (item.name != nameWithoutWhitespace)
                iterator.set(item.withName(nameWithoutWhitespace))
        }
    }

    dogmaAttributes.fixNames { fixedName -> copy(name = fixedName) }
    dogmaEffects.fixNames { fixedName -> copy(name = fixedName) }
}


/**
 * Remove abyssal modules and drones, since it doesn't make sense for them to be in the normal set, and add their tech1
 * counterparts in [MutableEveData.abyssalNameToParentTypeID].
 */
private fun MutableEveData.handleAbyssalItems() {
    removeTypesAndCorrespondingDogma(knownCategories.module, knownCategories.drone) { item, dogmaItem ->
        if (dogmaItem?.getAttributeValue(knownAttributes.metaGroupId) != 15.0)  // 15 is the abyssal meta group
            return@removeTypesAndCorrespondingDogma false

        val parentType = types.find {
            ((it.id == item.variationParentTypeID) && (it != item)) ||
                    (it.name == item.name.replace(oldValue = "Abyssal ", newValue = "")) ||
                    (it.name == item.name.replace(oldValue = "Abyssal ", newValue = "") + " I")
        } ?: if (item.name == "Large Abyssal Armor Plate") type("1600mm Steel Plates I") else null

        if (parentType != null)
            abyssalNameToParentTypeID[item.name] = parentType.variationParentTypeID!!

        return@removeTypesAndCorrespondingDogma true
    }
}


/**
 * Remote effects appear to be missing the actual modifiers that do anything. This implements them.
 */
private fun MutableEveData.fixRemoteEffects() {
    fixStasisWebifierRemoteEffects()
    fixRemoteSensorDampenerRemoteEffects()
    fixTrackingDisruptorRemoteEffects()
    fixGuidanceDisruptorRemoteEffects()
    fixTargetPainterRemoteEffects()
    fixRemoteSensorBoosterRemoteEffects()
    fixRemoteTrackingComputerRemoteEffects()
    fixOffensiveAndAssistiveFlags()
}


/**
 * Implements the remote speed-reduction effect modifier of stasis webifiers, grapplers and web drones.
 */
private fun MutableEveData.fixStasisWebifierRemoteEffects() {
    val webModifier = DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = knownAttributes.maxVelocity,
        modifyingAttribute = knownAttributes.speedFactor,
        attenuatingAttribute = knownAttributes.stasisWebifierResistance,
        operation = ModifierOperation.addPercent
    )

    // Stasis webifiers and grapplers
    val dogmaWeb = dogmaType("Stasis Webifier I")
    if (!dogmaWeb.hasEffect(knownEffects.remoteWebifierFalloff))
        error("Stasis Webifier lacks the remoteWebifierFalloff effect")
    if (!dogmaWeb.hasAttribute(knownAttributes.speedFactor))
        error("Stasis Webifier lacks a speedFactor attribute")
    replaceEffect(knownEffects.remoteWebifierFalloff) {
        it.withAddedModifiers(webModifier)
    }

    // SW drones
    val dogmaSwDrone = dogmaType("Warrior SW-300")
    if (!dogmaSwDrone.hasEffect(knownEffects.remoteWebifierEntity))
        error("Webifier drones lack the remoteWebifierEntity effect")
    if (!dogmaSwDrone.hasAttribute(knownAttributes.speedFactor))
        error("Webifier drones lack a speedFactor attribute")
    replaceEffect(knownEffects.remoteWebifierEntity) {
        it.withAddedModifiers(webModifier)
    }
}


/**
 * Implements the effect modifiers of remote sensor dampeners.
 */
private fun MutableEveData.fixRemoteSensorDampenerRemoteEffects() {
    val scanResModifier = DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = knownAttributes.scanResolution,
        modifyingAttribute = knownAttributes.scanResolutionBonus,
        attenuatingAttribute = knownAttributes.sensorDampenerResistance,
        operation = ModifierOperation.addPercent
    )
    val targetRangeModifier = DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = knownAttributes.maxTargetRange,
        modifyingAttribute = knownAttributes.maxTargetRangeBonus,
        attenuatingAttribute = knownAttributes.sensorDampenerResistance,
        operation = ModifierOperation.addPercent
    )

    val dogmaDampener = dogmaType("Remote Sensor Dampener I")
    if (!dogmaDampener.hasEffect(knownEffects.remoteSensorDampFalloff))
        error("Remote Sensor Dampener lacks the remoteSensorDampFalloff effect")
    if (!dogmaDampener.hasAttribute(knownAttributes.scanResolutionBonus))
        error("Remote Sensor Dampener lacks a scanResolutionBonus attribute")
    if (!dogmaDampener.hasAttribute(knownAttributes.maxTargetRangeBonus))
        error("Remote Sensor Dampener lacks a maxTargetRangeBonus attribute")
    replaceEffect(knownEffects.remoteSensorDampFalloff) {
        it.withAddedModifiers(
            scanResModifier,
            targetRangeModifier
        )
    }

    val dogmaDampenerDrone = dogmaType("Hobgoblin SD-300")
    if (!dogmaDampenerDrone.hasEffect(knownEffects.remoteSensorDampEntity))
        error("Remote Sensor Dampener drone lacks the remoteSensorDampEntity effect")
    if (!dogmaDampenerDrone.hasAttribute(knownAttributes.scanResolutionBonus))
        error("Remote Sensor Dampener drone lacks a scanResolutionBonus attribute")
    if (!dogmaDampenerDrone.hasAttribute(knownAttributes.maxTargetRangeBonus))
        error("Remote Sensor Dampener drone lacks a maxTargetRangeBonus attribute")
    replaceEffect(knownEffects.remoteSensorDampEntity) {
        it.withAddedModifiers(
            scanResModifier,
            targetRangeModifier
        )
    }
}


/**
 * Implements the effect modifiers of tracking disruptors.
 */
private fun MutableEveData.fixTrackingDisruptorRemoteEffects() {
    // Use modifiers from tracking computer (the modifying attributes are just negative in disruptors)
    val weaponDisruptionModifiers = knownEffects.gunneryMaxRangeFalloffTrackingSpeedBonus.modifiers.map {
        it.copy(
            attenuatingAttributeId = knownAttributes.weaponDisruptionResistance.id
        )
    }

    val dogmaTd = dogmaType("Tracking Disruptor I")
    val updatedModuleEffect = knownEffects.shipModuleTrackingDisruptor
    if (!dogmaTd.hasEffect(updatedModuleEffect))
        error("Tracking Disruptor lacks the $updatedModuleEffect effect")
    for (modifier in weaponDisruptionModifiers) {
        if (dogmaTd.attributeValues.none { it.attributeId == modifier.modifyingAttributeId } )
            error("Tracking Disruptor lacks Tracking Computer attribute with id ${modifier.modifyingAttributeId}")
    }
    replaceEffect(updatedModuleEffect) {
        it.withAddedModifiers(weaponDisruptionModifiers)
    }

    val dogmaTdDrone = dogmaType("Acolyte TD-300")
    val updatedDroneEffect = knownEffects.npcEntityWeaponDisruptor
    if (!dogmaTdDrone.hasEffect(updatedDroneEffect))
        error("Tracking Disruptor drone lacks the $updatedDroneEffect effect")
    for (modifier in weaponDisruptionModifiers) {
        if (dogmaTdDrone.attributeValues.none { it.attributeId == modifier.modifyingAttributeId } )
            error("Tracking Disruptor drone lacks Tracking Computer attribute with id ${modifier.modifyingAttributeId}")
    }
    replaceEffect(updatedDroneEffect) {
        it.withAddedModifiers(weaponDisruptionModifiers)
    }
}


/**
 * Implements the effect modifiers of guidance disruptors.
 */
private fun MutableEveData.fixGuidanceDisruptorRemoteEffects() {
    // Use modifiers from guidance computer (the modifying attributes are just negative in disruptors)
    // Note that we can't take it from knownEffects because it's broken, and we've fixed it in fixMissileBonusEffects
    val guidanceComputerEffect = dogmaEffect("missileGuidanceComputerBonus4")
    val guidanceDisruptionModifiers = guidanceComputerEffect.modifiers.map {
        it.copy(
            attenuatingAttributeId = knownAttributes.weaponDisruptionResistance.id
        )
    }

    val dogmaGd = dogmaType("Guidance Disruptor I")
    val updatedEffect = knownEffects.shipModuleGuidanceDisruptor
    if (!dogmaGd.hasEffect(updatedEffect))
        error("Guidance Disruptor lacks the $updatedEffect effect")
    for (modifier in guidanceDisruptionModifiers) {
        if (dogmaGd.attributeValues.none { it.attributeId == modifier.modifyingAttributeId } )
            error("Guidance Disruptor lacks Guidance Computer attribute with id ${modifier.modifyingAttributeId}")
    }
    replaceEffect(updatedEffect) {
        it.withAddedModifiers(guidanceDisruptionModifiers)
    }
}


/**
 * Implements the effect modifiers of target painters.
 */
private fun MutableEveData.fixTargetPainterRemoteEffects() {
    val targetPaintingModifier = DogmaModifier(
        domain = ModifierDomain.shipID,
        func = ModifierFunc.ItemModifier,
        modifiedAttribute = knownAttributes.signatureRadius,
        modifyingAttribute = knownAttributes.signatureRadiusBonus,
        attenuatingAttribute = knownAttributes.targetPainterResistance,
        operation = ModifierOperation.addPercent
    )

    val dogmaTp = dogmaType("Target Painter I")
    if (!dogmaTp.hasEffect(knownEffects.remoteTargetPaintFalloff))
        error("Target Painter lacks the remoteTargetPaintFalloff effect")
    if (!dogmaTp.hasAttribute(knownAttributes.signatureRadiusBonus))
        error("Target Painter lacks a signatureRadiusBonus attribute")
    replaceEffect(knownEffects.remoteTargetPaintFalloff) {
        it.withAddedModifiers(targetPaintingModifier)
    }

    val dogmaTpDrone = dogmaType("Warrior TP-300")
    if (!dogmaTpDrone.hasEffect(knownEffects.remoteTargetPaintEntity))
        error("Target Painter drone lacks the remoteTargetPaintEntity effect")
    if (!dogmaTpDrone.hasAttribute(knownAttributes.signatureRadiusBonus))
        error("Target Painter drone lacks a signatureRadiusBonus attribute")
    replaceEffect(knownEffects.remoteTargetPaintEntity) {
        it.withAddedModifiers(targetPaintingModifier)
    }
}



/**
 * Implements the effect modifiers of remote sensor boosters.
 */
private fun MutableEveData.fixRemoteSensorBoosterRemoteEffects() {
    // Use modifiers from local sensor boosters
    val remoteSensorBoosterModifiers = knownEffects.sensorBoosterActivePercentage.modifiers.map {
        it.copy(
            attenuatingAttributeId = knownAttributes.remoteAssistanceImpedance.id
        )
    }

    val dogmaRsb = dogmaType("Remote Sensor Booster I")
    val updatedEffect = knownEffects.remoteSensorBoostFalloff
    if (!dogmaRsb.hasEffect(updatedEffect))
        error("Remote Sensor Booster lacks the Sensor Booster $updatedEffect effect")
    for (modifier in remoteSensorBoosterModifiers) {
        if (dogmaRsb.attributeValues.none { it.attributeId == modifier.modifyingAttributeId } )
            error("Remote Sensor Booster lacks Sensor Booster attribute with id ${modifier.modifyingAttributeId}")
    }

    replaceEffect(knownEffects.remoteSensorBoostFalloff) {
        it.withAddedModifiers(remoteSensorBoosterModifiers)
    }
}


/**
 * Implements the effect modifiers of remote tracking computer.
 */
private fun MutableEveData.fixRemoteTrackingComputerRemoteEffects() {
    // Use modifiers from tracking computer
    val remoteTrackingComputerModifiers = knownEffects.gunneryMaxRangeFalloffTrackingSpeedBonus.modifiers.map {
        it.copy(
            attenuatingAttributeId = knownAttributes.remoteAssistanceImpedance.id
        )
    }

    val dogmaTc = dogmaType("Remote Tracking Computer I")
    val updatedModuleEffect = knownEffects.shipModuleRemoteTrackingComputer
    if (!dogmaTc.hasEffect(updatedModuleEffect))
        error("Remote Tracking Computer lacks the $updatedModuleEffect effect")
    for (modifier in remoteTrackingComputerModifiers) {
        if (dogmaTc.attributeValues.none { it.attributeId == modifier.modifyingAttributeId } )
            error("Remote Tracking Computer lacks Tracking Computer attribute with id ${modifier.modifyingAttributeId}")
    }
    replaceEffect(updatedModuleEffect) {
        it.withAddedModifiers(remoteTrackingComputerModifiers)
    }
}


/**
 * Some of the damage-dealing modules (turrets, smartbombs, bombs, but strangely not launchers) have an effect marked
 * as offensive, but we don't want to treat them as such because they don't have an actual projected effect.
 * On the other hand we want to treat modules such as neutralizers and jammers as offensive modules, even though they
 * also don't have an actual projected effect.
 * So we simply set the flag of the damage-dealing modules to 0.
 *
 * Additionally, some effects are just marked as offensive and/or defensive for no good reason. We clean those up too.
 */
private fun MutableEveData.fixOffensiveAndAssistiveFlags() {
    val offensiveEffects = with(knownEffects) {
        listOf(
            targetAttack,
            projectileFired,
            chainLightning,
            targetDisintegratorAttack,
            empWave,
            doomsdayBeamDOT,
            doomsdaySlash,
            debuffLance,
            doomsdayHOG,
            barrage,  // Useless effect on projectile turrets
            // WTF?
            mercoxitCrystalBonus,
            maxRangeHiddenPreAssignmentWarpScrambleRange,
            shieldBoosterDurationBonusShieldSkills,
            emergencyHullEnergizer,  // Why?
            moduleBonusBastionModule
        )
        // Check for more with:
        // moduleTree
        //     .filterValues { typeInfo -> typeInfo.effects.any { effect -> effect.isOffensive } }
        //     .filterValues {
        //         listOf(
        //             "Scrambler",
        //             "ECM",
        //             "Tracking Disruptor",
        //             "Neutralizer",
        //             "Nosferatu",
        //             "Webifier",
        //             "Sensor Dampener",
        //             "Warp Disruptor",
        //             "Burst Jammer",
        //             "Painter",
        //             "Grappler",
        //             "Field Generator",
        //             "Guidance Disruptor",
        //             "Burst Projector",
        //         ).none {
        //             name -> it.name.contains(name)
        //         }
        //    }
    }

    val assistiveEffects = with(knownEffects) {
        listOf(
            // Double WTF?
            mercoxitCrystalBonus,
            maxRangeHiddenPreAssignmentWarpScrambleRange,
            shieldBoosterDurationBonusShieldSkills
        )
        // Check for more with
        // moduleTree
        //     .filterValues { typeInfo -> typeInfo.effects.any { effect -> effect.isAssistive } }
        //     .filterValues {
        //         listOf(
        //             "Remote Sensor Booster",
        //             "Remote Shield Booster",
        //             "Remote Tracking Computer",
        //             "Remote Armor Repairer",
        //             "Remote Capacitor Transmitter",
        //             "Remote Hull Repairer"
        //         ).none {
        //             name -> it.name.contains(name)
        //         }
        //     }
    }

    for (effect in offensiveEffects) {
        if (!effect.isOffensive)
            throw IllegalStateException("$effect is already not offensive")
        replaceEffect(effect) {
            it.copy(isOffensive = false)
        }
    }
    for (effect in assistiveEffects) {
        if (!effect.isAssistive)
            throw IllegalStateException("$effect is already not assistive")
        replaceEffect(effect) {
            it.copy(isAssistive = false)
        }
    }
}


/**
 * Fixes the mutaplasmids, removing items which are not in the data (because we removed them).
 * This should be called after all such items have already been removed.
 */
private fun MutableEveData.fixMutaplasmids() {
    val allTypeIds = types.mapTo(mutableSetOf()) { it.id }
    val filteredMutaplasmids = buildList(mutaplasmids.size) {
        for (mutaplasmid in mutaplasmids) {
            val filteredTargetTypeIds = mutaplasmid.targetTypeIds.filter { it in allTypeIds }
            if (filteredTargetTypeIds.isNotEmpty()) {
                add(mutaplasmid.copy(targetTypeIds = filteredTargetTypeIds))
            }
        }
    }

    mutaplasmids.clear()
    mutaplasmids.addAll(filteredMutaplasmids)
}


/**
 * Set the correct icon ids to all relevant item types.
 */
private fun MutableEveData.assignItemTypeIconIds() {
    val lowestSkillId = typesAndOptionalDogmaIn(knownCategories.skill).minOf { (type, _) -> type.id }
    for ((index, type) in types.withIndex()){
        val group = groupOf(type)
        val category = categoryOf(type)
        val iconId =
            when (category) {
                knownCategories.ship -> type.id
                knownCategories.module,
                knownCategories.charge,
                knownCategories.drone,
                knownCategories.implant,  // Includes boosters
                knownCategories.material,
                knownCategories.subsystem,
                knownCategories.deployable -> type.variationParentTypeID ?: type.id
                knownCategories.skill -> lowestSkillId  // All skill books have the same icon
                else -> null
            } ?: when (group) {
                knownGroups.jumpFilaments,
                knownGroups.triglavianSpaceFilaments -> type.variationParentTypeID ?: type.id
                else -> null
            }

        if (iconId != null) {
            types[index] = type.copy(
                iconId = RemappedIcons.getOrDefault(iconId, iconId)
            )
        }
    }
}


/**
 * Removes market groups that have no items.
 */
private fun MutableEveData.removeUselessMarketGroups() {
    val marketGroupById = marketGroups.associateBy { it.id }
    val usefulMarketGroups = mutableSetOf<MarketGroup>()
    for (type in types) {
        type.marketGroupId?.let { marketGroupId ->
            usefulMarketGroups.add(marketGroupById[marketGroupId]!!)
        }
    }

    var addedSomething: Boolean
    do {
        addedSomething = false
        for (marketGroup in usefulMarketGroups.toSet()) {
            marketGroup.parentGroupId?.let { parentId ->
                addedSomething = usefulMarketGroups.add(marketGroupById[parentId]!!) or addedSomething
            }
        }
    } while (addedSomething)

    marketGroups.retainAll(usefulMarketGroups)
}


/**
 * Fix some market group names.
 */
private fun MutableEveData.fixMarketGroupNames() {
    val renamings = mapOf(
        "Booster" to "Boosters",
        "Hull & Armor" to "Armor & Hull"
    )
    for (i in marketGroups.indices) {
        val marketGroup = marketGroups[i]
        val renaming = renamings[marketGroup.name]
        if (renaming != null) {
            marketGroups[i] = marketGroup.copy(name = renaming)
        }
    }
}


/**
 * Fix the tech level and meta level of some Triglavian ships.
 */
private fun MutableEveData.fixTriglavianShipLevels() {
    val typesToFix = listOf(type("Ikitursa"), type("Nergal"), type("Draugur"))
    for (type in typesToFix) {
        replaceDogmaType(dogmaType(type)!!) {
            it.replaceAttributeValue(
                DogmaAttributeValue(knownAttributes.techLevel.id, 2.0)
            ).replaceAttributeValue(
                DogmaAttributeValue(knownAttributes.metaLevelOld.id, 5.0)
            )
        }
    }
}


/**
 * Some market groups have the wrong icon id; this fixed them.
 */
private fun MutableEveData.fixMarketIconIds() {
    val groupToIconId = with(knownMarketGroups) {
        mapOf(
            stasisGrapplers to 21581,
            interdictionSphereLaunchers to 2990,
            warpDisruptionFieldGenerators to 21489,
            warpScramblers to 3433
        )
    }
    for (i in marketGroups.indices) {
        val marketGroup = marketGroups[i]
        val fixedIconId = groupToIconId[marketGroup] ?: continue
        marketGroups[i] = marketGroup.copy(
            iconId = fixedIconId
        )
    }
}


/**
 * The abyssal weather types (e.g. `darkness_weather_1/2/3`):
 * 1. Don't have a good name or description.
 * 2. Don't have any modifiers in their effects.
 * This function fixes that.
 *
 * Note that this function needs to be called before [removeUselessTypes], because that removes the abyssal environment
 * types from which this function copies the names and descriptions.
 */
private fun MutableEveData.fixAbyssalWeatherTypes() {
    val abyssalEnvs = typesInGroup(knownGroups.abyssalEnvironment)

    fun fixTypes(name: String, targetIds: Collection<Int>) {
        val abyssalEnvType = abyssalEnvs.first { it.name == name }
        for (targetId in targetIds) {
            val targetType = type(targetId)
            val targetTypeIndex = targetType.name.substringAfterLast('_')
            replaceType(targetType) {
                it.copy(
                    name = "Abyssal ${abyssalEnvType.name} $targetTypeIndex",
                    description = abyssalEnvType.description,
                    mass = it.mass ?: 1.0,  // Add mass (copied from wormhole effects)
                    volume = it.volume ?: 20.0,  // Add volume (copied from wormhole effects),
                    published = true  // To prevent it from being removed by removeUselessTypes
                )
            }
        }
    }

    fixTypes("Dark Matter Field", AbyssalDarkMatterFieldWeatherIds)
    fixTypes("Exotic Particle Storm", AbyssalExoticParticleStormWeatherIds)
    fixTypes("Plasma Firestorm", AbyssalPlasmaFirestormWeatherIds)
    fixTypes("Electrical Storm", AbyssalElectricalStormWeatherIds)
    fixTypes("Gamma-Ray Afterglow", AbyssalGammaRayAfterglowWeatherIds)

    // The effects here seem to be specified (although the modifiers are missing) via warfareBuff attributes.
    // We don't want to implement them via warfare buffs, however, because that's a different, and incompatible,
    // mechanic. So we convert them to normal attributes and modifiers.
    fun fixDogmaTypes(
        typeIds: Collection<Int>,
        penaltyAttributes: List<DogmaAttribute>,
        bonusAttributes: List<DogmaAttribute>,
    ) {
        val penaltyWarfareBuffValueAttributeId = knownAttributes.warfareBuffValues[0]
        val bonusWarfareBuffValueAttributeId = knownAttributes.warfareBuffValues[1]
        for (typeId in typeIds) {
            val dogmaType = dogmaType(typeId)!!
            replaceDogmaType(dogmaType) { replacedDogmaType ->
                val attributeValues = replacedDogmaType.attributeValues
                val penaltyValue = attributeValues.first { it.attributeId == penaltyWarfareBuffValueAttributeId.id }.value
                val bonusValue = attributeValues.first { it.attributeId == bonusWarfareBuffValueAttributeId.id }.value
                replacedDogmaType.withAttributeValues(
                    buildList {
                        penaltyAttributes.forEach {
                            add(it.withValue(penaltyValue))
                        }
                        bonusAttributes.forEach {
                            add(it.withValue(bonusValue))
                        }
                    }
                )
            }
        }
    }

    // Dark Matter Field
    fixDogmaTypes(
        typeIds = AbyssalDarkMatterFieldWeatherIds,
        penaltyAttributes = listOf(knownAttributes.maxRangeBonus, knownAttributes.falloffBonus),
        bonusAttributes = listOf(knownAttributes.implantVelocityBonus),
    )
    replaceEffect(dogmaEffect("weather_darkness")) {
        it.withAddedModifiers(
            // Turret optimal range penalty
            DogmaModifier(
                domain = ModifierDomain.shipID,
                func = ModifierFunc.LocationRequiredSkillModifier,
                modifiedAttribute = knownAttributes.maxRange,
                modifyingAttribute = knownAttributes.maxRangeBonus,
                operation = ModifierOperation.addPercent,
                skillType = knownTypes.gunnery
            ),

            // Turret falloff penalty
            DogmaModifier(
                domain = ModifierDomain.shipID,
                func = ModifierFunc.LocationRequiredSkillModifier,
                modifiedAttribute = knownAttributes.falloff,
                modifyingAttribute = knownAttributes.falloffBonus,
                operation = ModifierOperation.addPercent,
                skillType = knownTypes.gunnery
            ),

            // Maximum velocity bonus
            DogmaModifier(
                domain = ModifierDomain.shipID,
                func = ModifierFunc.ItemModifier,
                modifiedAttribute = knownAttributes.maxVelocity,
                modifyingAttribute = knownAttributes.implantVelocityBonus,
                operation = ModifierOperation.addPercent,
            ),
        )
    }

    val structureResistanceBonus = knownAttributes.structureResistanceBonus
    val armorResistanceBonus = knownAttributes.armorResistanceBonus
    val shieldResistanceBonus = knownAttributes.shieldResistanceBonus
    fun resistanceBonusAttributes(damageTypeIndex: Int) = listOf(
        structureResistanceBonus[damageTypeIndex],
        armorResistanceBonus[damageTypeIndex],
        shieldResistanceBonus[damageTypeIndex]
    )
    fun resistanceBonusModifiers(damageTypeIndex: Int) = listOf(
        DogmaModifier(
            domain = ModifierDomain.shipID,
            func = ModifierFunc.ItemModifier,
            modifiedAttribute = knownAttributes.structureResonance[damageTypeIndex],
            modifyingAttribute = structureResistanceBonus[damageTypeIndex],
            operation = ModifierOperation.addPercent
        ),
        DogmaModifier(
            domain = ModifierDomain.shipID,
            func = ModifierFunc.ItemModifier,
            modifiedAttribute = knownAttributes.armorResonance[damageTypeIndex],
            modifyingAttribute = armorResistanceBonus[damageTypeIndex],
            operation = ModifierOperation.addPercent
        ),
        DogmaModifier(
            domain = ModifierDomain.shipID,
            func = ModifierFunc.ItemModifier,
            modifiedAttribute = knownAttributes.shieldResonance[damageTypeIndex],
            modifyingAttribute = shieldResistanceBonus[damageTypeIndex],
            operation = ModifierOperation.addPercent
        ),
    )

    // Exotic Particle Storm
    fixDogmaTypes(
        typeIds = AbyssalExoticParticleStormWeatherIds,
        penaltyAttributes = resistanceBonusAttributes(2),  // Kinetic
        bonusAttributes = listOf(knownAttributes.scanResolutionBonus)
    )
    replaceEffect(dogmaEffect("weather_caustic_toxin")) {
        it.withAddedModifiers(
            // Kinetic resistance
            resistanceBonusModifiers(2) +

            // Scan resolution bonus
            DogmaModifier(
                domain = ModifierDomain.shipID,
                func = ModifierFunc.ItemModifier,
                modifiedAttribute = knownAttributes.scanResolution,
                modifyingAttribute = knownAttributes.scanResolutionBonus,
                operation = ModifierOperation.addPercent,
            ),
        )
    }

    // Plasma Firestorm
    fixDogmaTypes(
        typeIds = AbyssalPlasmaFirestormWeatherIds,
        penaltyAttributes = resistanceBonusAttributes(1),  // Thermal
        bonusAttributes = listOf(knownAttributes.armorHpBonus)
    )
    replaceEffect(dogmaEffect("weather_infernal")) {
        it.withAddedModifiers(
            buildList {
                // Thermal resistance
                addAll(resistanceBonusModifiers(1))

                // Armor HP bonus
                add(
                    DogmaModifier(
                        domain = ModifierDomain.shipID,
                        func = ModifierFunc.ItemModifier,
                        modifiedAttribute = knownAttributes.armorHp,
                        modifyingAttribute = knownAttributes.armorHpBonus,
                        operation = ModifierOperation.addPercent,
                    )
                )
            }
        )
    }

    // Electrical Storm
    fixDogmaTypes(
        typeIds = AbyssalElectricalStormWeatherIds,
        penaltyAttributes = resistanceBonusAttributes(0),  // EM
        bonusAttributes = listOf(knownAttributes.capRechargeBonus)
    )
    replaceEffect(dogmaEffect("weather_electric_storm")) {
        it.withAddedModifiers(
            buildList {
                // EM resistance
                addAll(resistanceBonusModifiers(0))

                // Capacitor recharge time bonus
                add(
                    DogmaModifier(
                        domain = ModifierDomain.shipID,
                        func = ModifierFunc.ItemModifier,
                        modifiedAttribute = knownAttributes.capRechargeRate,
                        modifyingAttribute = knownAttributes.capRechargeBonus,
                        operation = ModifierOperation.addPercent,
                    )
                )
            }
        )
    }

    // Gamma-Ray Afterglow
    fixDogmaTypes(
        typeIds = AbyssalGammaRayAfterglowWeatherIds,
        penaltyAttributes = resistanceBonusAttributes(3),  // Explosive
        bonusAttributes = listOf(knownAttributes.shieldHpBonus)
    )
    replaceEffect(dogmaEffect("weather_xenon_gas")) {
        it.withAddedModifiers(
            buildList {
                // Explosive resistance
                addAll(resistanceBonusModifiers(3))

                // Shield HP bonus
                add(
                    DogmaModifier(
                        domain = ModifierDomain.shipID,
                        func = ModifierFunc.ItemModifier,
                        modifiedAttribute = knownAttributes.shieldHp,
                        modifyingAttribute = knownAttributes.shieldHpBonus,
                        operation = ModifierOperation.addPercent,
                    )
                )
            }
        )
    }
}


/**
 * Environments lack icon ids; this adds them.
 */
@Suppress("UnnecessaryVariable")
private fun MutableEveData.addEnvironmentIcons() {
    val wormholeIconId = 32387
    val abyssalExoticIconId = 47862
    val abyssalDarkMatterIconId = 47863
    val abyssalPlasmaIconId = 47864
    val abyssalElectricalIconId = 47865
    val abyssalGammaRayIconId = 47866
    // There don't appear to be separate icons for metaliminal storms
    val metaliminalGammaRayIconId = abyssalGammaRayIconId
    val metaliminalElectricIconId = abyssalElectricalIconId
    val metaliminalPlasmaIconId = abyssalPlasmaIconId
    val metaliminalExoticIconId = abyssalExoticIconId

    for (env in baseEveData.relevantEnvironmentTypes()) {
        if (env.iconId != null)
            continue

        val iconId = when {
            WormholeEnvironmentSearchTerms.any { env.name.contains(it) } -> wormholeIconId
            MetaliminalStormSearchTerms.any { env.name.contains(it) } -> {
                when {
                    env.name.contains("Gamma Ray") -> metaliminalGammaRayIconId
                    env.name.contains("Electric") -> metaliminalElectricIconId
                    env.name.contains("Plasma") -> metaliminalPlasmaIconId
                    env.name.contains("Exotic") -> metaliminalExoticIconId
                    else -> error("Unknown metaliminal storm: ${env.name}")
                }
            }
            AbyssalDarkMatterFieldWeatherIds.contains(env.id) -> abyssalDarkMatterIconId
            AbyssalExoticParticleStormWeatherIds.contains(env.id) -> abyssalExoticIconId
            AbyssalPlasmaFirestormWeatherIds.contains(env.id) -> abyssalPlasmaIconId
            AbyssalElectricalStormWeatherIds.contains(env.id) -> abyssalElectricalIconId
            AbyssalGammaRayAfterglowWeatherIds.contains(env.id) -> abyssalGammaRayIconId
            else -> error("Unknown environment: ${env.name}")
        }

        replaceType(env) {
            it.copy(
                iconId = iconId
            )
        }
    }
}


/**
 * Assigns the correct [Type.variationParentTypeID] to all relevant environments.
 */
private fun MutableEveData.addEnvironmentVariationParentTypes() {
    val wormholeClassRegex = "Class \\d+".toRegex()
    val metaliminalStormStrengthRegex = "(Weak|Strong)".toRegex()
    val abyssalWeatherIndexRegex = "\\d+$".toRegex()
    for (env in baseEveData.relevantEnvironmentTypes()) {
        val parentName = when {
            WormholeEnvironmentSearchTerms.any { env.name.contains(it) } ->
                env.name.replace(wormholeClassRegex, "Class 1")
            MetaliminalStormSearchTerms.any { env.name.contains(it) } ->
                env.name.replace(metaliminalStormStrengthRegex, "Weak")
            AbyssalWeatherEnvironmentIds.contains(env.id) ->
                type(env.id).name.replace(abyssalWeatherIndexRegex, "1")
            else -> error("Unknown environment: ${env.name}")
        }

        val parent = type(parentName)
        replaceType(env) {
            it.copy(
                variationParentTypeID = parent.id
            )
        }
    }
}

