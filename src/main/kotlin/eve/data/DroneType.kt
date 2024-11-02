package eve.data

import androidx.compose.runtime.Immutable


/**
 * The type of drone, e.g. "Acolyte II".
 */
@Immutable
class DroneType internal constructor(


    /**
     * The context [Attributes].
     */
    val attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


    /**
     * The id of the drone type that is the parent of the variation group of this drone type.
     * If this drone type is the parent itself, then this is its id.
     */
    variationParentTypeId: Int,


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
     * The base [DroneType], if this is a mutated drone; `this` otherwise.
     */
    val baseType: DroneType
        get() = (super.mutation?.baseType as DroneType?) ?: this


    /**
     * Returns a mutated version of this drone type.
     */
    context(EveData)
    fun mutated(
        mutaplasmid: Mutaplasmid,
        name: String,
        valueByAttribute: Map<Attribute<*>, Double>
    ): DroneType {
        if (this.mutation != null)
            throw IllegalStateException("Cannot mutate an already mutated type; mutate the base type instead")
        if (this.itemId !in mutaplasmid.targetTypeIds)
            throw IllegalStateException("Mutaplasmid $mutaplasmid can't mutate $this")

        val mutation = Mutation(this, mutaplasmid, name, attributes, valueByAttribute)
        return DroneType(
            attributes = attributes,
            typeData = typeData.mutated(mutation),
            variationParentTypeId = variationParentTypeId,
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
     * The bandwidth required to launch this drone type.
     */
    val bandwidthUsed: Int by attributes.droneBandwidthUsed


    /**
     * The attribute specifying this drone's activation duration.
     */
    override val durationAttribute: Attribute<Double>
        get() = super.durationAttribute!!  // Drones always have a duration


    /**
     * The attribute specifying this drone type's optimal range.
     * Note that this is not the optimal range itself, but the attribute specifying the optimal range.
     */
    override val optimalRangeAttribute: Attribute<Double>
        get() = super.optimalRangeAttribute!!  // Drones always have an optimal range


    /**
     * The damage type which this drone deals most of; `null` if this drone does not deal damage.
     */
    val primaryDamageType: DamageType? = primaryDamageType(attributes)


}