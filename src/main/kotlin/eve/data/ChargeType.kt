package eve.data

import androidx.compose.runtime.Immutable


/**
 * The type of charge or script, e.g. "Republic Fleet Fusion M" or "Targeting Range Script".
 */
@Immutable
class ChargeType internal constructor(


    /**
     * The context [Attributes].
     */
    val attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData


) : EveItemType(
    attributes = attributes,
    typeData = typeData
) {


    /**
     * The size of this charge; `null` if irrelevant.
     * Modules may limit the size of the charges loaded into them. For example "Heavy Pulse Laser II" can only load
     * medium-sized crystals.
     */
    val chargeSize: ItemSize? by attributes.chargeSize.orNull


    /**
     * Returns the damage of the given type done by this charge, or `null` if irrelevant.
     */
    fun damageOfType(damageType: DamageType): Double? = damageOfType(attributes, damageType)


    /**
     * The total damage done by this charge, or `null` if irrelevant.
     */
    val totalDamage: Double? = totalDamage(attributes)


    /**
     * The capacitor boost granted by this cap booster; `null` if it's not a cap booster.
     */
    val capacitorBonus: Double? by attributes.capacitorBonus.orNull


    /**
     * The damage type which this charge deals most of; `null` if this charge does not deal damage.
     */
    val primaryDamageType: DamageType? = primaryDamageType(attributes)


    /**
     * The bonus to the max range bonus of e.g. tracking computers by the optimal range scripts (in percent).
     */
    val maxRangeBonusBonus: Double? by attributes.maxRangeBonusBonus.orNull


    /**
     * The bonus to the missile velocity bonus of e.g. missile guidance computers by the missile range scripts.
     */
    val missileVelocityBonusBonus: Double? by attributes.missileVelocityBonusBonus.orNull


    /**
     * The bonus to the targeting range bonus of e.g. sensor boosters by the targeting range scripts.
     */
    val maxTargetRangeBonusBonus: Double? by attributes.maxTargetRangeBonusBonus.orNull


    /**
     * Whether this crystal gets damaged when used; `null` if it's not a crystal.
     */
    val crystalGetsDamaged: Boolean? by attributes.crystalsGetDamaged.orNull


    /**
     * The crystal's volatility chance; `null` if it's not a crystal.
     */
    val crystalVolatilityChance: Double? by attributes.crystalVolatilityChance.orNull


    /**
     * The crystal's volatility damage; `null` if it's not a crystal.
     */
    val crystalVolatilityDamage: Double? by attributes.crystalVolatilityDamage


}