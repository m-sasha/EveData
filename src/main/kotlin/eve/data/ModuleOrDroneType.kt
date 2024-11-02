package eve.data

import androidx.compose.runtime.Immutable


/**
 * The base class for [ModuleType] and [DroneType], as they share quite a lot of attributes.
 */
@Immutable
sealed class ModuleOrDroneType(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


    /**
     * The id of the [ModuleType] or [DroneType] that is the parent of the variation group of this module or drone.
     * If this module or drone is the parent itself, then this is its id.
     */
    val variationParentTypeId: Int,


    /**
     * The item's mutation, if any.
     */
    mutation: Mutation? = null,


): EveItemType(
    attributes = attributes,
    typeData = typeData,
    mutation = mutation
) {


    /**
     * Returns whether the given module or drone is a variant of this one.
     */
    fun isVariant(anotherType: ModuleType) = variationParentTypeId == anotherType.variationParentTypeId


    /**
     * The attribute specifying this module or drone type's activation duration; `null` if this module or drone is
     * either not activable, or it does not have an activation cycle (cloaking devices, for example).
     * Note that this is not the duration itself, but the attribute specifying the duration.
     */
    open val durationAttribute: Attribute<Double>? by attributes.durationAttribute.orNull


    /**
     * The attribute specifying this module or drone type's optimal range; `null` if irrelevant.
     * Note that this is not the optimal range itself, but the attribute specifying the optimal range.
     */
    open val optimalRangeAttribute: Attribute<Double>? by attributes.optimalRangeAttribute.orNull


    /**
     * The attribute specifying this module or drone type's falloff range; `null` if irrelevant.
     * Note that this is not the falloff range itself, but the attribute specifying the falloff range.
     */
    val falloffRangeAttribute: Attribute<Double>? by attributes.falloffRangeAttribute.orNull


    /**
     * The attribute specifying this module or drone type's tracking speed; `null` if irrelevant.
     * Note that this is not the tracking speed itself, but the attribute specifying the tracking speed.
     */
    val trackingSpeedAttribute: Attribute<Double>? by attributes.trackingSpeedAttribute.orNull


}