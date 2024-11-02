package eve.data

import androidx.compose.runtime.Immutable


/**
 * The type of module, e.g. "10MN Afterburner II".
 */
@Immutable
class ModuleType internal constructor(


    /**
     * The context [Attributes].
     */
    private val attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


    /**
     * The id of the [ModuleType] that is the parent of the variation group of this module.
     * If this module is the parent itself, then this is its id.
     */
    variationParentTypeId: Int,


    /**
     * The module's slot type.
     */
    val slotType: ModuleSlotType,


    /**
     * A set of flags specifying some of a module's boolean properties.
     */
    private val flags: ModuleFlags,


    /**
     * The module's mutation, if any.
     */
    mutation: Mutation? = null


) : ModuleOrDroneType(
    attributes = attributes,
    typeData = typeData,
    variationParentTypeId = variationParentTypeId,
    mutation = mutation
), HasVariationParent {


    /**
     * The base [ModuleType], if this is a mutated module; `this` otherwise.
     */
    val baseType: ModuleType
        get() = (super.mutation?.baseType as ModuleType?) ?: this


    /**
     * Returns a mutated version of this module type.
     */
    context(EveData)
    fun mutated(
        mutaplasmid: Mutaplasmid,
        name: String,
        valueByAttribute: Map<Attribute<*>, Double>
    ): ModuleType {
        if (this.mutation != null)
            throw IllegalStateException("Cannot mutate an already mutated type; mutate the base type instead")
        if (this.itemId !in mutaplasmid.targetTypeIds)
            throw IllegalStateException("Mutaplasmid $mutaplasmid can't mutate $this")

        val mutation = Mutation(this, mutaplasmid, name, attributes, valueByAttribute)
        return ModuleType(
            attributes = attributes,
            typeData = typeData.mutated(mutation),
            variationParentTypeId = variationParentTypeId,
            slotType = slotType,
            flags = flags,
            mutation = mutation
        )
    }


    /**
     * This is a version of [mutated] that is needed because currently the compiler creates an incorrect function
     * reference for `ModuleType::mutated` because of the context receiver.
     */
    @JvmName("mutatedWithEveData")
    fun mutated(
        eveData: EveData,
        mutaplasmid: Mutaplasmid,
        name: String,
        valueByAttribute: Map<Attribute<*>, Double>
    ) = with(eveData) {
        mutated(mutaplasmid, name, valueByAttribute)
    }


    /**
     * Whether this module can be activated (non-passive).
     */
    val isActivable: Boolean
        get() = flags.isActivable


    /**
     * Whether this module can be overloaded (overheated).
     */
    val isOverloadable: Boolean
        get() = flags.isOverloadable


    /**
     * Whether this module has projected effects.
     */
    val isProjected: Boolean
        get() = flags.isProjected


    /**
     * Whether this module is a rig.
     */
    val isRig: Boolean
        get() = slotType == ModuleSlotType.RIG


    /**
     * Whether this module type is a turret.
     */
    val takesTurretHardpoint: Boolean
        get() = flags.takesTurretHardpoint


    /**
     * Whether this module type is a missile launcher.
     * This doesn't include modules like Defender Launcher or Bomb Launchers.
     */
    val takesLauncherHardpoint: Boolean
        get() = flags.takesLauncherHardpoint


    /**
     * The amount of power used by this module; `null` if this module uses no power (e.g. it's a rig).
     */
    val powerNeed: Double? by attributes.power.orNull


    /**
     * The amount of CPU used by this module; `null` if this module uses no CPU (e.g. it's a rig)
     */
    val cpuNeed: Double? by attributes.cpu.orNull


    /**
     * The amount of capacitor (in GJ) used by this module for one activation; `null` if this module is not active, or
     * maybe doesn't require capacitor.
     */
    val capacitorNeed: Double? by attributes.capacitorNeed.orNull


    /**
     * The calibration used by this rig; `null` if this is not a rig.
     */
    val calibrationNeed: Int? by attributes.calibrationNeed.orNull


    /**
     * The amount of modules of this module type's group ID that can be fitted into one ship.
     * A `null` value means there is no limit.
     */
    val maxGroupFitted: Int? by attributes.maxGroupFitted.orNull


    /**
     * If this module is a rig, its rig size; otherwise `null`.
     */
    val rigSize: ItemSize? by attributes.rigSize.orNull


    /**
     * The group ids of charges that can be loaded into this module.
     */
    val chargeGroupIds: List<Int> = attributes.chargeGroups.mapNotNull { attributeValues.getOrNull(it) }


    /**
     * The size of the charges (via [ChargeType.chargeSize]) that can be loaded into this module; `null` if either not
     * relevant or the charge size is not limited.
     * Note that size here refers not to volume, but to an enumerated list of sizes. For example, frigate sized
     * lasers, and the corresponding "S" crystals have charge size of 1, while "M" crystals have charge size of 2.
     */
    val chargeSize: ItemSize? by attributes.chargeSize.orNull


    /**
     * The module's volume capacity for charges, in m^3.
     * This will be non-null whenever [chargeGroupIds] is not empty (validated by eve data validations).
     */
    val chargeCapacity: Double? by attributes.capacity.orNull


    /**
     * The number of charges consumed per activation.
     */
    val chargesConsumed: Int? by attributes.chargeRate.orNull


    /**
     * Returns whether this module can be loaded with charges.
     */
    val canLoadCharges: Boolean
        get() = chargeGroupIds.isNotEmpty()


    /**
     * Returns whether this module type can load the given charge type.
     */
    fun canLoadCharge(chargeType: ChargeType): Boolean{
        // Check group id match
        if (!chargeGroupIds.contains(chargeType.groupId))
            return false

        // Check charge size match
        val chargeSize = this.chargeSize
        if ((chargeSize != null) && (chargeSize != chargeType.chargeSize))
            return false

        // Check that capacity is greater than charge volume
        val chargeCapacity = this.chargeCapacity
            ?: throw BadEveDataException("Module $this has non-empty chargeGroupIds, but no charge capacity")
        return chargeType.volume <= chargeCapacity
    }


    /**
     * The ship type group ids this module type can be fitted on; `null` if there is no restriction.
     */
    val canFitShipGroupIds: Set<Int>? by lazy{
        attributes.canFitShipGroups.mapNotNull { attributeValueOrNull(it) }.toSet().ifEmpty { null }
    }


    /**
     * The ship type ids this module type can be fitted on; `null` if there is no restriction.
     */
    val canFitShipTypes: Set<Int>? by lazy{
        attributes.canFitShipTypes.mapNotNull { attributeValueOrNull(it) }.toSet().ifEmpty { null }
    }


    /**
     * Whether this module can only be fitted on capital-class ships.
     */
    val canOnlyFitOnCapitals by attributes.canOnlyFitOnCapitals.orDefault(false)


}


