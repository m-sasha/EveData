/**
 * Eve data validations.
 * These are conditions the app assumes hold, and they currently do hold, so there's no need to fix them.
 */


/**
 * Checks whether the [EveData] is valid, asserting all the expectations we have of it.
 * Throws a [InvalidSdeException] if invalid; returns normally if valid.
 */
internal fun validate(eveData: EveData) = with(eveData) {
    validateModulesWithChargesHaveCapacity()
    validateRigsHaveRigSize()
    validateChargeSizes()
    validateAllTypesHaveVolume()
    validateCapacitorBoosterCharges()
    validateAncillaryArmorRepairerCharges()
    validateAllDronesHaveDurationAndRange()
    validateBaseWarpSpeedIsUnused()
    validateShipAttributesPresent()
    validateModulesDoNotAffectImplants()
    validateBoostersHaveDuration()
}


/**
 * Returns all the [Type]s and their respective optional [DogmaType]s in the given groups.
 */
private fun EveData.typesAndDogmaInGroups(groups: Iterable<Group>): Sequence<Pair<Type, DogmaType>> {
    val groupIds = groups.map { it.id }.toSet()
    return types.asSequence()
        .filter { it.groupId in groupIds }
        .map { type -> type to dogmaType(type) }
}


/**
 * Returns all the [Type]s and their respective optional [DogmaType]s in the given groups.
 */
private fun EveData.typesAndDogmaInGroups(vararg groups: Group): Sequence<Pair<Type, DogmaType>> =
    typesAndDogmaInGroups(groups.toList())


/**
 * Returns all the [Type]s and their respective optional [DogmaType]s in the given categories.
 */
private fun EveData.typesAndDogmaInCategories(categories: Iterable<Category>): Sequence<Pair<Type, DogmaType>> {
    val categoryIds = categories.map { it.id }.toSet()
    val groups = groups.filter { it.categoryId in categoryIds }
    return typesAndDogmaInGroups(groups)
}


/**
 * Returns all the [Type]s and their respective optional [DogmaType]s in the given categories.
 */
private fun EveData.typesAndDogmaInCategories(vararg categories: Category): Sequence<Pair<Type, DogmaType>> =
    typesAndDogmaInCategories(categories.toList())


/**
 * Validates that all modules that have chargeGroupIds also have a capacity.
 */
private fun EveData.validateModulesWithChargesHaveCapacity(){
    val chargeGroupsAttributeIds = knownAttributes.chargeGroups.map { it.id }
    val capacityAttribute = knownAttributes.capacity
    for ((module, dogmaModule) in typesAndDogmaInCategories(knownCategories.module)){
        val canBeLoadedWithCharges =
            dogmaModule.attributeValues.any { chargeGroupsAttributeIds.contains(it.attributeId) }
        val hasCapacity = dogmaModule.hasAttribute(capacityAttribute)
        if (canBeLoadedWithCharges && !hasCapacity)
            throw InvalidSdeException("Module $module has a chargeGroupN attribute but no capacity")
    }
}


/**
 * Validates that all rigs that a rigSize attribute.
 */
private fun EveData.validateRigsHaveRigSize(){
    fun DogmaType.isRig() = effectRefs.any { it.effectId == knownEffects.rigSlot.id }
    val rigSizes = setOf(1.0, 2.0, 3.0, 4.0)

    for ((module, dogmaModule) in typesAndDogmaInCategories(knownCategories.module)){
        if (!dogmaModule.isRig())
            continue

        val rigSize = dogmaModule.getAttributeValue(knownAttributes.rigSize)
        if (rigSize !in rigSizes)
            throw InvalidSdeException("$module has invalid rig size: $rigSize")
    }
}


/**
 * Validates that all "chargeSize" attribute values are in the expected range.
 */
private fun EveData.validateChargeSizes(){
    val chargeSizes = setOf(1.0, 2.0, 3.0, 4.0)
    for ((module, dogmaModule) in typesAndDogmaInCategories(knownCategories.all - knownCategories.material)){
        val chargeSize = dogmaModule.getAttributeValue(knownAttributes.chargeSize)
        if ((chargeSize != null) && (chargeSize !in chargeSizes))
            throw InvalidSdeException("$module has invalid charge size: $chargeSize")
    }
}


/**
 * Validate that all types of interest have a volume property.
 */
private fun EveData.validateAllTypesHaveVolume(){
    for (type in typesInCategories(knownCategories.all)){
        if (type.volume == null)
            throw InvalidSdeException("$type has no volume property")
    }
}


/**
 * Returns all the charge types that can be loaded into the given [Type].
 */
private fun EveData.charges(type: Type): Sequence<Pair<Type, DogmaType>>{
    val dogmaType = dogmaType(type)
    val chargeGroups = knownAttributes.chargeGroups
        .mapNotNull { dogmaType.getAttributeValue(it) }
        .map { group(it.toInt()) }
    return typesAndDogmaInGroups(chargeGroups)
}


/**
 * Validates that all the charges that can be loaded into capacitor boosters have a "capacitorBonus" attribute.
 */
private fun EveData.validateCapacitorBoosterCharges(){
    val capacitorBonus = knownAttributes.capacitorBonus
    for ((type, _) in typesAndDogmaInGroups(knownGroups.capacitorBooster)){
        for ((_, dogmaCharge) in charges(type)){
            if (!dogmaCharge.hasAttribute(capacitorBonus))
                throw InvalidSdeException("$type has no $capacitorBonus attribute")
        }
    }
}


/**
 * Validates that Ancillary Armor Repairers (local and remote) have exactly one charge type.
 */
private fun EveData.validateAncillaryArmorRepairerCharges(){
    for (type in typesIn(knownGroups.ancillaryArmorRepairer, knownGroups.ancillaryRemoteArmorRepairer)){
        val chargesIterator = charges(type).iterator()
        if (!chargesIterator.hasNext())
            throw InvalidSdeException("$type (an Ancillary Armor Repairer) can't load any charges")
        chargesIterator.next()
        if (chargesIterator.hasNext())
            throw InvalidSdeException("$type (an Ancillary Armor Repairer) has more than one charge type")
    }
}


/**
 * Validates that all drones have all the meta-attributes for duration and range.
 */
private fun EveData.validateAllDronesHaveDurationAndRange(){
    val durationAttribute = knownAttributes.durationAttributeId
    val rangeAttribute = knownAttributes.durationAttributeId
    for ((drone, dogmaDrone) in typesAndDogmaInCategories(knownCategories.drone)){
        if (!dogmaDrone.hasAttribute(durationAttribute))
            throw InvalidSdeException("$drone has no durationAttributeId attribute")
        if (!dogmaDrone.hasAttribute(rangeAttribute))
            throw InvalidSdeException("$drone has no rangeAttributeId attribute")
    }
}


/**
 * All ships have a "baseWarpSpeed" attribute. However, its value for all ships is 1.0, and it is not used in any
 * effects. All effects on the warp speed affect the "warpSpeedMultiplier" attribute instead.
 * Because of this we simply treat "warpSpeedMultiplier" as the attribute for warp speed.
 */
private fun EveData.validateBaseWarpSpeedIsUnused(){
    val baseWarpSpeedAttribute = knownAttributes.baseWarpSpeed
    for ((ship, dogmaShip) in typesAndDogmaInCategories(knownCategories.ship)){
        val baseWarpSpeed = dogmaShip.getAttributeValue(baseWarpSpeedAttribute)
        if (baseWarpSpeed != 1.0)
            throw InvalidSdeException("$ship has baseWarpSpeed=$baseWarpSpeed")
    }

    for (effect in dogmaEffects){
        for (modifier in effect.modifiers){
            if ((modifier.modifiedAttributeId == baseWarpSpeedAttribute.id) || (modifier.modifyingAttributeId == baseWarpSpeedAttribute.id))
                throw InvalidSdeException("$effect uses baseWarpSpeed")
        }
    }
}


/**
 * Verifies that all ships have the attributes we expect them to have.
 */
private fun EveData.validateShipAttributesPresent(){
    fun verify(ship: Type, dogmaShip: DogmaType, attribute: DogmaAttribute){
        if (!dogmaShip.hasAttribute(attribute))
            throw InvalidSdeException("$ship does not have attribute ${attribute.name}")
    }

    for ((ship, dogmaShip) in typesAndDogmaInCategories(knownCategories.ship)){
        verify(ship, dogmaShip, knownAttributes.energyWarfareResistance)
        verify(ship, dogmaShip, knownAttributes.weaponDisruptionResistance)
        verify(ship, dogmaShip, knownAttributes.warpCapacitorNeed)
    }
}


/**
 * Verifies that no modules have an effect on implants or boosters.
 */
private fun EveData.validateModulesDoNotAffectImplants(){
    val implantFuncs = setOf(
        ModifierFunc.LocationRequiredSkillModifier,
        ModifierFunc.LocationModifier
    )
    for ((module, dogmaModule) in typesAndDogmaInCategories(knownCategories.module)){
        for (effect in dogmaEffects(dogmaModule.effectRefs)){
            if (effect.modifiers.any { (it.domain == ModifierDomain.charID) && (it.func in implantFuncs) }){
                throw InvalidSdeException("$module has an effect on implants and/or boosters")
            }
        }
    }
}


/**
 * Verifies that all boosters have a `boosterDuration` attribute.
 */
private fun EveData.validateBoostersHaveDuration() {
    val boosterness = knownAttributes.boosterness
    val boosterDuration = knownAttributes.boosterDuration
    for ((booster, dogmaBooster) in typesAndDogmaInCategories(knownCategories.implant)){
        if (!dogmaBooster.hasAttribute(boosterness))
            continue

        if (!dogmaBooster.hasAttribute(boosterDuration)){
            throw InvalidSdeException("$booster does not have boosterDuration attribute")
        }
    }
}