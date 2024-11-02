/**
 * A collection of utilities for converting Eve property values to strings.
 */

package eve.data

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import kotlin.math.*


/**
 * Narrow, non-breaking space.
 */
const val NNBSP = "\u202F"


/**
 * The string for m^3.
 *
 * Note that the value below consists of two characters ('m' and `\u00B3`), instead of the single ㎥ (U+33A5) character.
 * This is on purpose, because that single character causes `Text` to measure taller than normal text, which causes
 * misalignment.
 */
const val METER_CUBED = "m³"


/**
 * Returns a representation of a [Double] value as a string, with the given number of digits after the decimal point.
 */
fun Double.toDecimalWithPrecision(precision: Int): String = when {
    this.isNaN() -> "NaN"
    this.isInfinite() -> if (this < 0) "-Inf" else "+Inf"
    precision == 0 -> this.roundToInt().toString()
    else -> BigDecimal(this).setScale(precision, RoundingMode.HALF_UP).toPlainString()
}


/**
 * Returns a representation of a [Double] value as a string, with the number of digits equal to at most the given value.
 * Trailing zeroes after the decimal point are removed.
 */
fun Double.toDecimalWithPrecisionAtMost(precision: Int): String = when {
    this.isNaN() -> "NaN"
    this.isInfinite() -> if (this < 0) "-Inf" else "+Inf"
    precision == 0 -> this.roundToInt().toString()
    else -> BigDecimal(this).setScale(precision, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString()
}



/**
 * Converts a [Double] to a string that is rounded to the nearest integer if the (absolute) value is above [threshold],
 * or shows at most one decimal point if it is below.
 */
fun Double.toDecimalWithTenthsIfBelow(threshold: Int): String =
    if (this.absoluteValue >= threshold)
        roundToLong().toString()
    else
        this.toDecimalWithPrecisionAtMost(1)


/**
 * Converts a [Double] to a string with at most the given number of significant digits.
 */
fun Double.toDecimalWithSignificantDigitsAtMost(digits: Int): String = when {
    this.isNaN() -> "NaN"
    this.isInfinite() -> if (this < 0) "-Inf" else "+Inf"
    else -> BigDecimal(this).round(MathContext(digits)).stripTrailingZeros().toPlainString()
}


/**
 * If [withUnits] is `true`, returns [number] with [units] appended; otherwise returns just [number].
 * If [withSign] is `true` and [number] does not start with a '-' character, prepends a '+' sign to it.
 */
fun withUnitsAndSign(
    number: String,
    units: String,
    withUnits: Boolean,
    withSign: Boolean,
    unitsDelimiter: String = NNBSP
): String{
    val numberWithSign = addSign(number, withSign)
    return if (withUnits) "$numberWithSign$unitsDelimiter$units" else numberWithSign
}


/**
 * If [withSign] is `true` and [number] does not start with a '-' character, prepends a '+' sign to it.
 */
fun addSign(number: String, withSign: Boolean): String{
    return if (withSign && !number.startsWith('-')) "+$number" else number
}


/**
 * Converts the given [Int] value to a string, optionally prefixing it with a sign.
 */
fun Int.toString(withSign: Boolean): String{
    return addSign(toString(), withSign = withSign)
}


/**
 * Converts a [Double] value on the 0-1 scale to a percentage string with the given precision.
 */
fun Double.fractionAsPercentage(precision: Int, withSign: Boolean = false): String =
    addSign("${(100*this).toDecimalWithPrecision(precision)}%", withSign)


/**
 * Converts a [Double] value on the 0-1 scale to a percentage string with at most the given precision.
 */
fun Double.fractionAsPercentageWithPrecisionAtMost(precision: Int, withSign: Boolean = false): String =
    addSign("${(100*this).toDecimalWithPrecisionAtMost(precision)}%", withSign)


/**
 * Converts a [Double] value on the 0-1 scale to a percentage string.
 * If the resulting percentage value is below [threshold], tenths will be shown.
 */
fun Double.fractionAsPercentageWithTenthsIfBelow(threshold: Int = 10, withSign: Boolean = false): String =
    addSign("${(100*this).toDecimalWithTenthsIfBelow(threshold)}%", withSign)


/**
 * Converts a [Double] value on the 0-100 scale to a percentage string with the given precision.
 */
fun Double.asPercentage(precision: Int, withSign: Boolean = false): String =
    addSign("${this.toDecimalWithPrecision(precision)}%", withSign)


/**
 * Converts a [Double] value on the 0-100 scale to a percentage string with at most the given precision.
 */
fun Double.asPercentageWithPrecisionAtMost(precision: Int, withSign: Boolean = false): String =
    addSign("${this.toDecimalWithPrecisionAtMost(precision)}%", withSign)


/**
 * Converts a [Double] value on the 0-100 scale to a percentage string.
 * If the value is below [threshold], tenths will be shown.
 */
fun Double.asPercentageWithTenthsIfBelow(threshold: Int = 10, withSign: Boolean = false): String =
    addSign("${this.toDecimalWithTenthsIfBelow(threshold)}%", withSign)


/**
 * Converts a [Double] value to a string representing CPU in teraflops.
 * This can be used to display the CPU output of a ship.
 */
fun Double.asCpu(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(this.toDecimalWithPrecisionAtMost(2), "tf", withUnits, withSign)


/**
 * Shortens the given value by using SI units.
 */
private fun Double.compactSiWithSignificantDigitsAtMost(digits: Int) = when {
    this.absoluteValue > 1_000_000 -> "${(this/1_000_000).toDecimalWithSignificantDigitsAtMost(digits)}m"
    this.absoluteValue > 100_000 -> "${(this/1000).toDecimalWithSignificantDigitsAtMost(digits)}k"
    this.absoluteValue > 10_000 -> "${(this/1000).toDecimalWithSignificantDigitsAtMost(digits)}k"
    this.absoluteValue > 1_000 -> "${(this/1000).toDecimalWithSignificantDigitsAtMost(digits)}k"
    else -> this.toDecimalWithPrecisionAtMost(2)
}


/**
 * Converts a [Double] value to a string representing power in megawatts.
 * This can be used to display the powergrid output of a ship.
 */
fun Double.asPower(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(this.compactSiWithSignificantDigitsAtMost(3), "MW", withUnits, withSign)


/**
 * Converts an [Int] value to a string representing calibration.
 */
fun Int.asCalibration(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(toString(), units = "calibration", withUnits, withSign)


/**
 * Converts a [Double] value to a string representing energy in gigajoules.
 * This can be used to display the ship's capacitor capacity.
 */
fun Double.asCapacitorEnergy(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(this.toDecimalWithTenthsIfBelow(10), "GJ", withUnits, withSign)


/**
 * Converts a [Double] value to a string representing energy in gigajoules per second.
 * This can be used to display the cap usage of a module.
 */
fun Double.asCapacitorEnergyPerSecond(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(this.toDecimalWithTenthsIfBelow(10), "GJ/sec", withUnits, withSign)


/**
 * Converts a [Double] value to a string representing hitpoints, or effective hitpoints.
 */
fun Double.asHitPoints(ehp: Boolean = false, withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(
        number = when {
            this > 100_000_000 -> "${(this/1_000_000).roundToInt()}m"
            this > 10_000_000 -> "${(this/1_000_000).toDecimalWithPrecision(1)}m"
            this > 1_000_000 -> "${(this/1_000_000).toDecimalWithPrecision(2)}m"
            this > 100_000 -> "${(this/1000).roundToInt()}k"
            this > 10_000 -> "${(this/1000).toDecimalWithPrecisionAtMost(1)}k"
            this > 1_000 -> "${(this/1000).toDecimalWithPrecisionAtMost(2)}k"
            else -> this.roundToInt().toString()
        },
        units = if (ehp) "EHP" else "HP",
        withUnits = withUnits,
        withSign = withSign
    )


/**
 * Converts a [Double] value to a string representing (possibly effective) hitpoints-per-second.
 */
fun Double.asHitpointsPerSecond(
    ehp: Boolean = false,
    withUnits: Boolean = true,
    withSign: Boolean = false,
    rounding: Boolean = true,
) =
    withUnitsAndSign(
        number = when{
            rounding && (this > 1000) -> "${(this/1000).toDecimalWithPrecisionAtMost(2)}k"
            else -> this.roundToInt().toString()
        },
        units = if (ehp) "EHP/s" else "HP/s",
        withUnits = withUnits,
        withSign = withSign
    )


/**
 * Converts a [Double] value in meters/sec to a string representing speed in m/s or km/s.
 * This can be used to display a ship's speed.
 */
fun Double.asSpeed(withUnits: Boolean = true, withSign: Boolean = false, kmsThreshold: Int = 2000): String {
    return if ((this.absoluteValue < kmsThreshold) || !withUnits)
        withUnitsAndSign(this.toDecimalWithTenthsIfBelow(10), "m/s", withUnits, withSign)
    else
        withUnitsAndSign((this/1000).toDecimalWithPrecision(1), "km/s", true, withSign)
}


/**
 * Converts a [Double] value to a string representing a factor by which something else is multiplied.
 */
fun Double.asMultiplicationFactor(withUnits: Boolean = true, withSign: Boolean = false): String {
    val precision = -log10(this.absoluteValue).toInt() + 2
    return withUnitsAndSign(
        number = this.toDecimalWithPrecisionAtMost(precision),
        units = "x",
        withUnits,
        withSign,
        unitsDelimiter = ""
    )
}


/**
 * Converts a [Double] value representing time in milliseconds to a string representing that time in hours, minutes and
 * seconds.
 */
fun Double.millisAsTime(showZeroRemainder: Boolean = false): String {
    var timeSec = this/1000

    val hours = (timeSec / (60*60)).toInt()
    timeSec -= hours * 60*60

    val minutes = (timeSec / 60).toInt()
    timeSec -= minutes * 60

    val seconds = timeSec.roundToInt()

    return when {
        hours != 0 -> {
            if (!showZeroRemainder && (seconds == 0)) {
                if (minutes == 0)
                    "${hours}h"
                else
                    "${hours}h${minutes}m"
            }
            else
                "${hours}h${minutes}m${seconds}s"
        }
        minutes != 0 -> {
            if (!showZeroRemainder && (seconds == 0))
                "${minutes}m"
            else
                "${minutes}m${seconds}s"
        }
        else -> "${seconds}s"
    }
}


/**
 * Converts a [Double] value representing time in milliseconds to a string representing that time in seconds.
 * This can be used to display shield or capacitor recharge times.
 */
fun Double.millisAsTimeSec(withUnits: Boolean = true, withSign: Boolean = false, units: String = "sec") =
    withUnitsAndSign((this/1000).toDecimalWithTenthsIfBelow(10), units, withUnits, withSign)


/**
 * Converts a [Double] value representing align time in milliseconds to a string representing that time in seconds.
 */
fun Double.asAlignTime(withUnits: Boolean = true, withSign: Boolean = false, units: String = "sec"): String {
    val seconds = this / 1000
    return withUnitsAndSign(
        number = seconds.toDecimalWithPrecision(if (seconds < 10) 2 else 1),
        units = units,
        withUnits = withUnits,
        withSign = withSign
    )
}


/**
 * Converts a [Double] value to a string representing sensor strength.
 */
fun Double.asSensorStrength(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(toDecimalWithTenthsIfBelow(50), "pts", withUnits, withSign)


/**
 * Converts a [Double] value to a scan resolution, in mm.
 */
fun Double.asScanResolution(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(toDecimalWithTenthsIfBelow(10), "mm", withUnits, withSign)


/**
 * Converts a [Double] value in meters to a distance or range, in meters or kilometers.
 * This can be used to display targeting range, or drone control range.
 */
fun Double.asDistance(withUnits: Boolean = true, withSign: Boolean = false, kmThreshold: Int = 1000): String{
    return if (this.absoluteValue < kmThreshold)
        withUnitsAndSign(this.toDecimalWithTenthsIfBelow(10), "m", withUnits, withSign)
    else
        withUnitsAndSign((this/1000.0).toDecimalWithTenthsIfBelow(10), "km", withUnits, withSign)
}


/**
 * Converts a [Double] value in meters to a distance or range, in meters or kilometers, with the given precision.
 * This can be used to display targeting range, or drone control range.
 */
fun Double.asDistanceWithPrecision(precision: Int, withUnits: Boolean = true, withSign: Boolean = false): String{
    return if (this.absoluteValue < 1000)
        withUnitsAndSign(this.toDecimalWithPrecision(precision), "m", withUnits, withSign)
    else
        withUnitsAndSign((this/1000.0).toDecimalWithPrecision(precision), "km", withUnits, withSign)
}


/**
 * Converts a [Double] value in AU to a distance or range, in AU.
 */
fun Double.asDistanceAu(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign((this).toDecimalWithPrecisionAtMost(3), "AU", withUnits, withSign)


/**
 * Converts a [Double] value to a signature radius, in meters.
 */
fun Double.asSignatureRadius(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(toDecimalWithTenthsIfBelow(100), "m", withUnits, withSign)


/**
 * Converts a [Double] value to a ship mass, in tonnes.
 */
fun Double.asShipMass(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign((this.roundToInt()/1000).toString(), "t", withUnits, withSign)


/**
 * Converts an [Int] value to a drone capacity, in m^3.
 */
fun Int.asDroneCapacity(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(this.toString(), METER_CUBED, withUnits, withSign)


/**
 * Converts a [Double] value to a drone bandwidth, in mbit/s.
 */
fun Double.asDroneBandwidth(withUnits: Boolean = true, withSign: Boolean = false): String {
    val roundedDown = this.toInt()
    val roundedDownToMultipleOf5 = roundedDown - roundedDown%5
    return withUnitsAndSign(roundedDownToMultipleOf5.toString(), "mbit/s", withUnits, withSign)
}


/**
 * Converts an [Int] value to a drone bandwidth, in mbit/s.
 */
@Suppress("unused")
fun Int.asDroneBandwidth(withUnits: Boolean = true, withSign: Boolean = false): String {
    return withUnitsAndSign(this.toString(), "mbit/s", withUnits, withSign)
}


/**
 * Converts a [Double] value to hitpoints damaged.
 */
fun Double.asDamage(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(
        number = this.toDecimalWithTenthsIfBelow(100),
        units = "dmg",
        withUnits = withUnits,
        withSign = withSign,
    )


/**
 * Converts a [Double] value to hitpoints damaged per second.
 */
fun Double.asDps(withUnits: Boolean = true, withSign: Boolean = false) =
    withUnitsAndSign(
        number = this.toDecimalWithTenthsIfBelow(100),
        units = "dps",
        withUnits = withUnits,
        withSign = withSign
    )


/**
 * Converts an optimal and falloff range (both in meters) to a string.
 */
fun asRange(optimal: Double, falloff: Double?, withUnits: Boolean = true) =
    withUnitsAndSign(
        number = buildString {
            append((optimal/1000.0).toDecimalWithTenthsIfBelow(10))
            if ((falloff != null) && (falloff != 0.0)){
                append("+")
                append((falloff/1000.0).toDecimalWithTenthsIfBelow(10))
            }
        },
        units = "km",
        withUnits = withUnits,
        withSign = false
    )


/**
 * Converts an [Int] value to warp disruption strength.
 */
fun Int.asWarpDisruptionStrength(withUnits: Boolean = true) =
    withUnitsAndSign(this.toString(), "pts", withUnits = withUnits, withSign = false)


/**
 * Converts a [Double] value to tracking speed.
 */
fun Double.asTrackingSpeed(withSign: Boolean = false): String = addSign(
    number = toDecimalWithSignificantDigitsAtMost(4),
    withSign = withSign
)


/**
 * Converts a [Double] value to a string displaying the resist factor.
 */
fun Double.asResistFactor() = this.asMultiplicationFactor(withUnits = true, withSign = false)


/**
 * Converts a [Double] value specifying resonance, to a percentage specifying resistance.
 */
fun Double.resonanceAsResistancePercentage(precision: Int) = (1-this).fractionAsPercentage(precision)


/**
 * Converts a [Double] value specifying resonance, to a percentage specifying resistance, with the given maximum
 * precision.
 */
fun Double.resonanceAsResistancePercentageWithPrecisionAtMost(precision: Int) =
    (1-this).fractionAsPercentageWithPrecisionAtMost(precision)


/**
 * Converts an [Int] value into a string representing some kind of units.
 */
fun Int.asUnits(withUnits: Boolean = true, withSign: Boolean = false): String{
    val units = if (this == 1) "unit" else "units"
    return withUnitsAndSign(this.toString(), units, withUnits = withUnits, withSign = withSign)
}


/**
 * Converts a [Double] value into a string representing some kind of units.
 * The value is rounded to the nearest integer.
 */
fun Double.asUnits(withUnits: Boolean = true, withSign: Boolean = false) =
    ceil(this).roundToInt().asUnits(withUnits = withUnits, withSign = withSign)


/**
 * Converts a [Double] value to a string representing volume in m^3.
 * This can be used to display mining amount, cargo space etc.
 */
fun Double.asVolume(withUnits: Boolean = true, withSign: Boolean = false): String{
    return withUnitsAndSign(
        number = compactSiWithSignificantDigitsAtMost(2),
        units = METER_CUBED,
        withUnits = withUnits,
        withSign = withSign
    )
}


/**
 * Converts a [Double] value into a string representing volume (in m^3) per hour.
 * This can be used to display mining speed.
 */
fun Double.asVolumePerHour(withUnits: Boolean = true, withSign: Boolean = false): String{
    return withUnitsAndSign(
        number = asVolume(withUnits = false, withSign = false),
        units = "$METER_CUBED/h",
        withUnits = withUnits,
        withSign = withSign
    )
}


/**
 * Converts a [Double] value into a string representing thrust in Newtons.
 */
fun Double.asThrust(withUnits: Boolean = true, withSign: Boolean = false): String{
    return withUnitsAndSign(
        number = (this/1_000_000).toDecimalWithPrecisionAtMost(1),
        units = "MN",
        withUnits = withUnits,
        withSign = withSign
    )
}


/**
 * Converts a [Double] value into a string representing warp speed in AU/s.
 */
fun Double.asWarpSpeed(withUnits: Boolean = true, withSign: Boolean = false): String{
    return withUnitsAndSign(
        number = this.toDecimalWithPrecisionAtMost(2),
        units = "AU/s",
        withUnits = withUnits,
        withSign = withSign
    )
}


/**
 * Converts a [Double] value into an integer string, representing a number of some items.
 */
fun Double.asIntNumber(withSign: Boolean = false): String{
    return addSign(
        number = this.roundToInt().toString(),
        withSign = withSign
    )
}


/**
 * Returns a string representing the mined and wasted amounts from the given [MiningInfo].
 */
fun MiningInfo.asMinedAndWastedAmounts(): String = buildString {
    val residuePerHour = residuePerHour
    if ((residuePerHour == null) || (residuePerHour == 0.0)){
        append(minedPerHour.asVolumePerHour(withSign = false, withUnits = true))
    }
    else{
        append(minedPerHour.asVolumePerHour(withSign = false, withUnits = false))
        append("+")
        append(residuePerHour.asVolumePerHour(withSign = false, withUnits = true))
    }
}


/**
 * Returns a string suitable to represent the given amount of spoolup cycles.
 */
fun Double.asSpoolupCycles() = this.toDecimalWithPrecisionAtMost(2)
