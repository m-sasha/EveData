package eve.data

import androidx.compose.runtime.Immutable
import eve.data.utils.ValueByEnum
import eve.data.utils.valueByEnum


/**
 * Sizes of eve items, loosely corresponding to ship sizes e.g. rig size ([Attributes.rigSize]) or charge size
 * ([Attributes.chargeSize]).
 */
enum class ItemSize(


    /**
     * The displayed name of this size.
     */
    private val displayName: String,


    /**
     * The code (in the SDE) of this size.
     */
    val code: Int

) {


    /**
     * Typically corresponds to frigate- or destroyer-sized items.
     */
    SMALL("Small", 1),


    /**
     * Typically corresponds to cruiser- or battlecruiser-sized items.
     */
    MEDIUM("Medium", 2),


    /**
     * Typically corresponds to battleship-sized items.
     */
    LARGE("Large", 3),


    /**
     * Typically corresponds to capital-sized items.
     */
    CAPITAL("Capital", 4);


    override fun toString(): String {
        return displayName
    }


    companion object{


        /**
         * Returns the [ItemSize] with the given code.
         */
        fun fromCode(code: Int) = entries.find { it.code == code }


    }


}


/**
 * Ship sensor types.
 */
enum class SensorType(


    /**
     * The id of the sensor type, as used in attribute names.
     */
    val id: String,


    /**
     * The name of the sensor type, as it should be presented to the user.
     */
    val displayName: String,


    /**
     * A shortened name that can be presented to the user when there is little space.
     */
    val shortDisplayName: String


) {


    RADAR(id = "radar", displayName = "Radar", shortDisplayName = "Radar"),
    MAGNETOMETRIC(id = "magnetometric", displayName = "Magnetometric", shortDisplayName = "Magnet."),
    GRAVIMETRIC(id = "gravimetric", displayName = "Gravimetric", shortDisplayName = "Grav."),
    LADAR(id = "ladar", displayName = "Ladar", shortDisplayName = "Ladar");


    override fun toString() = displayName


}


/**
 * A requirement to have trained some skill to a certain level.
 */
@Immutable
data class SkillRequirement(val skillId: Int, val level: Int)


/**
 * Ship module slot types.
 */
enum class ModuleSlotType(val maxSlotCount: Int) {

    HIGH(8),
    MEDIUM(8),
    LOW(8),
    RIG(3);

}


/**
 * Damage types.
 */
enum class DamageType(val id: String){


    EM("em"),
    THERMAL("thermal"),
    KINETIC("kinetic"),
    EXPLOSIVE("explosive");


    /**
     * The displayable name.
     */
    val displayName: String
        get() = if (this == EM) "EM" else id.replaceFirstChar(Char::uppercaseChar)


    /**
     * The displayable name, in lowercase.
     */
    val displayNameLowercase: String
        get() = id


    override fun toString() = displayName


}


/**
 * Returns the value of the damage attribute corresponding to the given damage type of the item; `null` if irrelevant.
 */
internal fun EveItemType.damageOfType(attributes: Attributes, damageType: DamageType): Double? =
    attributeValueOrNull(attributes.damage[damageType])


/**
 * Returns the sum of the values of the damage attributes of the item; `null` if irrelevant.
 */
internal fun EveItemType.totalDamage(attributes: Attributes): Double? {
    val damages = DamageType.entries.map { damageOfType(attributes, it) }
    return if (damages.all { it == null })
        null
    else
        damages.sumOf { it ?: 0.0 }
}


/**
 * Returns the [DamageType] whose corresponding attribute damage value in the item is largest; `null` if the item
 * does not have any damage attribute values.
 */
internal fun EveItemType.primaryDamageType(attributes: Attributes): DamageType? {
    val damageType = DamageType.entries.maxBy{ attributeValueOrNull(attributes.damage[it]) ?: 0.0 }

    return if (attributeValueOrNull(attributes.damage[damageType]) == null)
        null
    else
        damageType
}


/**
 * A damage pattern is the amount of damage of each type.
 */
typealias DamagePattern = ValueByEnum<DamageType, Double>


/**
 * The uniform damage pattern, where there is an equal amount of every damage type.
 */
val UNIFORM_DAMAGE_PATTERN: DamagePattern = valueByEnum { 0.25 }


/**
 * A resonance pattern: the resonance for each damage type.
 */
typealias ResonancePattern = ValueByEnum<DamageType, Double>


/**
 * Combines the mining properties into amount mined per hour and residue generated per hour.
 */
@Immutable
data class MiningInfo(


    /**
     * The amount mined per hour.
     */
    val minedPerHour: Double,


    /**
     * The residue per hour.
     */
    val residuePerHour: Double?


){

    /**
     * Returns the mining info for [count] items.
     */
    operator fun times(count: Int) = MiningInfo(
        minedPerHour = minedPerHour * count,
        residuePerHour = residuePerHour?.let { it * count }
    )

}
