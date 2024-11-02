package eve.data

import androidx.compose.runtime.Immutable

/**
 * The type of an implant, e.g. "Zainou 'Gypsy' CPU Management EE-603".
 */
@Immutable
class ImplantType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


    /**
     * The id of the [ImplantType] that is the parent of the variation group of this implant type.
     * If this implant is the parent itself, then this is its id.
     */
    override val variationParentTypeId: Int,


    /**
     * The (1-based) slot of this implant.
     * This should only be used to display the slot, not for indexing.
     */
    val slot: Int


): EveItemType(
    attributes = attributes,
    typeData = typeData
), HasVariationParent {


    /**
     * The 0-based index of the slot of this implant.
     */
    val slotIndex = slot-1


    /**
     * The bonus to perception provided by this implant.
     */
    val perceptionBonus: Int? by attributes.perceptionBonus.orNull


    /**
     * The bonus to memory provided by this implant.
     */
    val memoryBonus: Int? by attributes.memoryBonus.orNull


    /**
     * The bonus to willpower provided by this implant.
     */
    val willpowerBonus: Int? by attributes.willpowerBonus.orNull


    /**
     * The bonus to intelligence provided by this implant.
     */
    val intelligenceBonus: Int? by attributes.intelligenceBonus.orNull


    /**
     * The bonus to charisma provided by this implant.
     */
    val charismaBonus: Int? by attributes.charismaBonus.orNull


    /**
     * Returns the maximal bonus to a skill learning attribute provided by this implant; `null` if none.
     */
    val skillLearningBonus: Int? by lazy {
        listOf(
            perceptionBonus,
            memoryBonus,
            willpowerBonus,
            intelligenceBonus,
            charismaBonus
        ).firstOrNull { (it != null) && (it != 0) }
    }


}