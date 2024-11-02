package eve.data

import androidx.compose.runtime.Immutable


/**
 * Item traits; a description of their effects/bonuses.
 */
@Immutable
class Traits(


    /**
     * The per-skill level bonuses, mapped by skill id.
     */
    val perSkill: Map<Int, List<Bonus>>,


    /**
     * The role bonuses.
     */
    val role: List<Bonus>,


    /**
     * Additional, miscellaneous bonuses.
     */
    val misc: List<Bonus>


)


/**
 * A single trait/bonus/effect description.
 */
@Immutable
class Bonus(


    /**
     * The description of the trait/bonus/effect.
     */
    val text: String,


    /**
     * The bonus value.
     */
    val value: BonusValue?,


)


/**
 * The value of the bonus/effect.
 */
@Immutable
class BonusValue(


    /**
     * The amount of the bonus/effect; e.g. "5" for "5% bonus to resistances".
     *
     * This is a number, represented as a string.
     */
    val amount: String,


    /**
     * The unit of the amount.
     */
    val unit: BonusUnit


)


/**
 * The units for the [Bonus.value].
 */
enum class BonusUnit {


    /**
     * Meters; e.g. the bonus to drone control range for the Ishtar.
     */
    METERS,


    /**
     * Kilograms, e.g. the added mass of an Entosis Link.
     */
    KILOGRAMS,


    /**
     * The affected property is multiplied by a factor; e.g. the penalty to Entosis Link duration on dreads.
     */
    MULTIPLY,


    /**
     * The affected property is multiplied by a percentage; e.g. the bonus to armor resistances on the Vengeance.
     *
     * Nearly all bonuses use this unit.
     */
    PERCENT,


    /**
     * A unitless number; e.g. the bonus to ship warp core strength on Titans.
     */
    NUMBER;


}
