package eve.data

import androidx.compose.runtime.Immutable

/**
 * The type of booster, e.g. "Strong Mindflood Booster".
 */
@Immutable
class BoosterType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


    /**
     * The id of the [BoosterType] that is the parent of the variation group of this booster type.
     * If this booster is the parent itself, then this is its id.
     */
    override val variationParentTypeId: Int,


    /**
     * The (1-based) slot of this booster.
     * This should only be used to display the slot, not for indexing.
     */
    val slot: Int,


    /**
     * The booster's side effects.
     */
    val sideEffects: List<SideEffect>


): EveItemType(
    attributes = attributes,
    typeData = typeData
), HasVariationParent {


    /**
     * The 0-based index of the slot of this booster.
     */
    val slotIndex = slot-1


    /**
     * The side effect penalty of using a booster.
     */
    class SideEffect(


        /**
         * The affected attribute.
         */
        val penalizedAttribute: Attribute<*>,


        /**
         * The affecting attribute.
         */
        val penalizingAttribute: Attribute<*>,


    ) {

        override fun toString(): String = "SideEffect[${penalizedAttribute.name}]"

    }


}