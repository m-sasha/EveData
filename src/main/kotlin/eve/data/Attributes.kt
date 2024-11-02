package eve.data

import androidx.compose.runtime.Immutable
import eve.data.AttributeType.Companion.BOOLEAN
import eve.data.AttributeType.Companion.DOUBLE
import eve.data.AttributeType.Companion.INT
import eve.data.AttributeType.Companion.ITEM_SIZE
import eve.data.utils.ValueByEnum
import eve.data.utils.valueByEnum
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


/**
 * The units of attribute values.
 */
enum class AttributeUnit(
    val suffix: String? = null,
    val normalSuffix: String? = suffix,
    val rawToNormalValue: (Double) -> Double = { it },
    val normalToRawValue: (Double) -> Double = { it },
    val normalizingInverts: Boolean = false,  // Whether a < b implies rawToNormalValue(a) > rawToNormalValue(b)
    val formatNormalValue: (Double) -> String = DefaultValueFormat,
    val formatRawValueWithUnits: (Double) -> String = DefaultValueFormatWithUnits(rawToNormalValue, normalSuffix)
) {

    RangeMeters(
        suffix = "m",
        normalSuffix = "km",
        rawToNormalValue = DivideByThousand,
        normalToRawValue = MultiplyByThousand,
        formatRawValueWithUnits = { it.asDistance(withUnits = true) }
    ),
    Mass(
        suffix = "kg",
        normalSuffix = "t",
        rawToNormalValue = DivideByThousand,
        normalToRawValue = MultiplyByThousand,
        formatRawValueWithUnits = { it.asShipMass(withUnits = true) }
    ),
    TimeSec(
        suffix = "sec",
        formatRawValueWithUnits = { (it*1000).millisAsTimeSec(withUnits = true) }
    ),
    VolumeM3("m³"),
    VelocityMetersPerSec(
        suffix = "m/s",
        formatRawValueWithUnits = { it.asSpeed(withUnits = true) }
    ),
    MaxVelocityMetersPerSec(
        suffix = "m/s",
        formatRawValueWithUnits = { it.asSpeed(withUnits = true) }
    ),
    DurationMs(
        suffix = "ms.",
        normalSuffix = "sec",
        rawToNormalValue = DivideByThousand,
        normalToRawValue = MultiplyByThousand,
        formatRawValueWithUnits = { it.millisAsTimeSec(withUnits = true) }
    ),
    ScanResolutionMm("mm"),
    Multiplier1(
        suffix = "x",
    ),
    BonusPercentAdditive1("%"),
    CpuTf("tf"),
    PowerMw("MW"),
    Resonance(
        suffix = null,
        normalSuffix = "%",
        rawToNormalValue = NormalizeInvertedPercentage,
        normalToRawValue = DenormalizeInvertedPercentage,
        normalizingInverts = true
    ),
    Multiplier2(
        suffix = "x",
        normalSuffix = "%",
        rawToNormalValue = FactorToAddedPercentage,
        normalToRawValue = AddedPercentageToFactor
    ),
    Multiplier3(  // speedMultiplier attribute (which is used as rate-of-fire bonus on e.g. Heat Sinks
        suffix = "x",
        normalSuffix = "%",
        rawToNormalValue = NormalizeInvertedPercentage,
        normalToRawValue = DenormalizeInvertedPercentage,
        normalizingInverts = true
    ),
    Hitpoints(
        suffix = "HP",
        formatRawValueWithUnits = { it.asHitPoints(withUnits = true) }
    ),
    CapacitorGj(
        suffix = "GJ",
        formatRawValueWithUnits = { it.asCapacitorEnergy(withUnits = true) }
    ),
    GroupId(null),
    TypeId(null),
    SizeCategory(
        suffix = null,
        formatRawValueWithUnits = {
            when (ItemSize.fromCode(it.toInt())) {
                ItemSize.CAPITAL -> "Capital"
                ItemSize.LARGE -> "Large"
                ItemSize.MEDIUM -> "Medium"
                ItemSize.SMALL -> "Small"
                else -> it.toInt().toString()
            }
        }
    ),
    AttributeId(null),
    Unitless(null),
    BonusPercentAdditive2("%"),
    SlotAmount(null),
    BonusPercentAdditive3("%"),
    ThrustNewtons(
        suffix = "N",
        normalSuffix = "MN",
        rawToNormalValue = DivideByMillion,
        normalToRawValue = MultiplyByMillion,
        formatRawValueWithUnits = { it.asThrust(withUnits = true) }
    ),
    JumpRangeLy("ly"),
    AdditiveFraction(
        suffix = "x",
        normalSuffix = "%",
        rawToNormalValue = ValueToPercentage,
        normalToRawValue = PercentageToValue,
    ),
    Bandwidth(
        suffix = "mbit/s",
        formatRawValueWithUnits = { it.asDroneBandwidth(withUnits = true) }
    ),
    Isk("ISK"),
    ScanRangeAu("AU"),
    SlotNumber(null),
    BooleanFlag(
        suffix = null,
        formatRawValueWithUnits = { if (it == 0.0) "no" else "yes" }
    ),
    FuelUnits("units"),
    BonusIntegerAdditive(
        suffix = null,
        formatRawValueWithUnits = { "+${it.toInt()}" }
    ),
    Level(null),
    HardpointAmount(null),
    Gender(null),
    DateTime(null),
    WarpSpeedAuPerSec(
        suffix = "AU/s",
        formatRawValueWithUnits = { it.asWarpSpeed(withUnits = true) }
    ),
    WasteMultiplier("x");

}


/**
 * Divides the value by 1000.
 *
 * Used for normalizing time (milliseconds to seconds), distance (meters to kilometers), speed (m/s to km/s).
 */
private val DivideByThousand: (Double) -> Double = { it / 1000 }


/**
 * Multiplies the value by 1000.
 *
 * Used for denormalizing time (seconds to milliseconds), distance (kilometers to meters), speed (km/s to m/s).
 */
private val MultiplyByThousand: (Double) -> Double = { it * 1000 }


/**
 * Divides the value by 1,000,000.
 *
 * Used for normalizing thrust (N to MN).
 */
private val DivideByMillion: (Double) -> Double = { it / 1_000_000 }


/**
 * Multiplies the value by 1,000,000.
 *
 * Used for denormalizing thrust (MN to N).
 */
private val MultiplyByMillion: (Double) -> Double = { it * 1_000_000 }


/**
 * Converts a value to a percentage.
 */
private val ValueToPercentage: (Double) -> Double = { it * 100 }


/**
 * Converts a percentage to a regular value.
 */
private val PercentageToValue: (Double) -> Double = { it / 100 }


/**
 * Converts a multiplicative factor to a percentage increase.
 *
 * Used for normalizing multiplicative factors, such as bonus to damage.
 */
private val FactorToAddedPercentage: (Double) -> Double = { (it - 1.0) * 100 }


/**
 * Converts a percentage increase to a multiplicative factor.
 *
 * Used for denormalizing multiplicative factors, such as bonus to damage.
 */
private val AddedPercentageToFactor: (Double) -> Double = { (1.0 + it / 100) }


/**
 * Normalizes inverted percentage attributes, such as resonance.
 */
private val NormalizeInvertedPercentage: (Double) -> Double = { (1.0 - it) * 100 }


/**
 * Denormalizes inverted percentage attributes, such as resonance.
 */
private val DenormalizeInvertedPercentage: (Double) -> Double = { (1.0 - it/100) }


/**
 * The default value-formatting function.
 */
private val DefaultValueFormat: (Double) -> String = { it.toDecimalWithSignificantDigitsAtMost(4) }


/**
 * Returns the default value-with-units formatting function.
 */
private fun DefaultValueFormatWithUnits(
    rawToNormalValue: (Double) -> Double,
    normalSuffix: String?
): (Double) -> String =
    {
        val normalValue = rawToNormalValue(it)
        DefaultValueFormat(normalValue) + (if (normalSuffix == null) "" else "$NNBSP$normalSuffix")
    }


/**
 * Formats a normal value of the given attribute as a string without units.
 */
fun Double.normalAttributeValueToString(attribute: Attribute<*>) =
    (attribute.unit?.formatNormalValue ?: DefaultValueFormat).invoke(this)


/**
 * Formats a raw value of the given attribute as a string with units.
 *
 * This is the most suitable format for presenting the value to the user.
 */
fun Double.rawAttributeValueToStringWithUnits(attribute: Attribute<*>) =
    (attribute.unit?.formatRawValueWithUnits ?: DefaultValueFormat).invoke(this)


/**
 * An attribute of some entity, e.g. "armorHP" (of a ship).
 */
@Immutable
class Attribute<T: Any> internal constructor(


    /**
     * The attribute's id.
     */
    val id: Int,


    /**
     * The attribute's name.
     */
    val name: String,


    /**
     * The attributes name that is suitable to be displayed to the user.
     */
    val displayName: String?,


    /**
     * The attribute's unit.
     */
    val unit: AttributeUnit?,


    /**
     * Whether this attribute is stacking-penalized.
     */
    val isStackingPenalized: Boolean,


    /**
     * Whether a higher attribute value (if applicable) is better; `null` if irrelevant.
     */
    val highIsGood: Boolean?,


    /**
     * An optional range to which the [Double] value of any property corresponding to this attribute is restricted.
     */
    val propertyRange: ClosedRange<Double>?,


    /**
     * A converter between the [Double] attribute values to the attribute's actual type.
     *
     * This is needed because the SDE specifies all attribute values as doubles, and does not appear to specify the
     * attribute's actual type. The only way to determine the type of the attribute is to "know" it (e.g. that
     * "techLevel" is an integer attribute). We therefore first create all the attributes as [Double] attributes, and
     * then convert the ones we "know" to their correct type (the named attribute properties in [Attributes]).
     */
    private val type: AttributeType<T>


) : ReadOnlyProperty<HasAttributeValues, T> {


    /**
     * Implementation of [ReadOnlyProperty] for obtaining the value of this attribute in the context of the given
     * [HasAttributeValues] object.
     * This allows using [Attribute]s as delegates for (Kotlin) properties of types implementing [HasAttributeValues].
     */
    override operator fun getValue(thisRef: HasAttributeValues, property: KProperty<*>): T {
        return thisRef.attributeValues.get(this)
    }


    /**
     * A [ReadOnlyProperty] for an attribute whose [AttributeValue] is not necessarily present in the
     * [HasAttributeValues] object. In such a case, it will return `null`.
     */
    val orNull: ReadOnlyProperty<HasAttributeValues, T?> by lazy {
        ReadOnlyProperty { it, _ ->
            it.attributeValues.getOrNull(this@Attribute)
        }
    }


    /**
     * Returns a [ReadOnlyProperty] for an attribute whose [AttributeValue] is not necessarily present in the
     * [HasAttributeValues] object. In such a case, it will return the given default value.
     */
    fun orDefault(defaultValue: T): ReadOnlyProperty<HasAttributeValues, T> =
        ReadOnlyProperty{ it, _ ->
            it.attributeValues.getOrDefault(this@Attribute, defaultValue)
        }


    /**
     * Converts the [Double] value to the type of this attribute.
     */
    fun valueFromDouble(value: Double) = type.fromDouble(value)


    /**
     * Converts the value of the type of this attribute to a [Double] value.
     */
    fun valueToDouble(value: T) = type.toDouble(value)


    override fun toString(): String {
        return "Attribute($name, id=$id)"
    }


    companion object{


        /**
         * Creates a [Double] attribute with the given id, name, display name and stacking-penalized flag.
         */
        fun double(
            id: Int,
            name: String,
            displayName: String?,
            unit: AttributeUnit?,
            isStackingPenalized: Boolean,
            highIsGood: Boolean?,
            propertyRange: ClosedRange<Double>?
        ): Attribute<Double> =
            Attribute(
                id = id,
                name = name,
                displayName = displayName,
                unit = unit,
                isStackingPenalized = isStackingPenalized,
                type = DOUBLE,
                highIsGood = highIsGood,
                propertyRange = propertyRange,
            )


    }


}


/**
 * The runtime representation of the type of an [Attribute], allowing to convert between the type and its underlying
 * [Double] value representation.
 */
internal interface AttributeType<T>{


    /**
     * Converts a [Double] value to the type.
     */
    fun fromDouble(value: Double): T


    /**
     * Convert a type value to [Double].
     */
    fun toDouble(value: T): Double


    /**
     * Whether a higher [Double] value corresponds to a lower [T] value; `null` if irrelevant.
     */
    val isInvertedRelativeToDouble: Boolean?


    companion object{


        /**
         * The [AttributeType] for floating-point attributes.
         */
        internal val DOUBLE = object: AttributeType<Double> {
            override fun fromDouble(value: Double) = value
            override fun toDouble(value: Double) = value
            override val isInvertedRelativeToDouble: Boolean
                get() = false
        }


        /**
         * The [AttributeType] for integer attributes.
         */
        internal val INT = object: AttributeType<Int> {

            override fun fromDouble(value: Double) =
                if (value % 1 != 0.0)
                    throw IllegalArgumentException("Value expected to be a whole number; actually: $value")
                else
                    value.toInt()

            override fun toDouble(value: Int) = value.toDouble()

            override val isInvertedRelativeToDouble: Boolean
                get() = false

        }


        /**
         * The [AttributeType] for boolean attributes.
         */
        internal val BOOLEAN = object: AttributeType<Boolean> {

            override fun fromDouble(value: Double): Boolean = when (value){
                0.0 -> false
                1.0 -> true
                else -> throw IllegalArgumentException("Value expected to be either 0 or 1; actually: $value")
            }

            override fun toDouble(value: Boolean): Double =
                if (value) 1.0 else 0.0

            override val isInvertedRelativeToDouble: Boolean?
                get() = null

        }


        /**
         * The [AttributeType] for [ItemSize] attributes.
         */
        internal val ITEM_SIZE = object: AttributeType<ItemSize> {

            override fun fromDouble(value: Double) =
                ItemSize.fromCode(INT.fromDouble(value)) ?: throw BadEveDataException("Unknown item size code: $value")

            override fun toDouble(value: ItemSize) = value.code.toDouble()

            override val isInvertedRelativeToDouble: Boolean?
                get() = null

        }


    }


}


/**
 * Groups all dogma attributes, and provides some of them via explicit properties.
 */
class Attributes internal constructor(
    attributes: Iterable<Attribute<Double>>
): Iterable<Attribute<Double>> by attributes{


    /**
     * Attributes mapped by their name.
     */
    private val byName: MutableMap<String, Attribute<*>> = attributes.associateByTo(mutableMapOf()){ it.name }


    /**
     * Attributes mapped by their id.
     */
    private val byId: MutableMap<Int, Attribute<*>> = attributes.associateByTo(mutableMapOf()){ it.id }


    /**
     * The amount of attributes.
     */
    val size: Int
        get() = byId.size


    /**
     * Replaces the [DOUBLE] [Attribute] with the given name with an identical one of the given type and returns it.
     * The attribute must exist.
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T: Any> update(name: String, type: AttributeType<T>): Attribute<T> {
        val attribute = byName[name] ?: error("No attribute named `$name` found")
        val doubleAttribute = attribute as Attribute<Double>
        if (type == DOUBLE)
            return doubleAttribute as Attribute<T>

        return Attribute(
            id = doubleAttribute.id,
            name = doubleAttribute.name,
            displayName = doubleAttribute.displayName,
            unit = doubleAttribute.unit,
            isStackingPenalized = doubleAttribute.isStackingPenalized,
            highIsGood = when(val highIsGood = doubleAttribute.highIsGood) {
                null -> null
                type.isInvertedRelativeToDouble -> !highIsGood
                else -> highIsGood
            },
            propertyRange = doubleAttribute.propertyRange,
            type = type,
        ).also {
            byName[name] = it
            byId[doubleAttribute.id] = it
        }
    }


    /**
     * Like [update], but returns `null` if the attribute does not exist.
     */
    private fun <T: Any> updateIfExists(name: String, type: AttributeType<T>): Attribute<T>? {
        return if (byName.containsKey(name)) update(name, type) else null
    }


    /**
     * Updates all attributes (like [update]) that have the name
     * ```
     * nameFormat.format(index)
     * ```
     * where `index` goes from 1 until there is no attribute with that name.
     * This allows batch-updating attributes like "chargeGroup1", "chargeGroup2" etc., and also makes the code
     * future-proof when new indices are added.
     */
    private fun <T: Any> updateIndexed(nameFormat: String, type: AttributeType<T>): List<Attribute<T>>{
        return generateSequence(1){ it + 1 }
            .map { index -> nameFormat.format(Locale.ROOT, index) }
            .map { name ->
                updateIfExists(name, type)
            }
            .takeWhile { it != null }
            .filterNotNull()
            .toList()
    }



    /**
     * Updates the attributes that have the name
     * ```
     * nameFormat.format(damageType.id)
     * ```
     * where `damageType` iterates over the values of [DamageType].
     * This allows batch-updating per-damage-type attributes.
     */
    private fun <T: Any> updateWithDamageType(


        /**
         * The name format, in the sense of [String.format]
         */
        nameFormat: String,


        /**
         * Whether to capitalize the first letter of the damage type name.
         */
        capitalizeFirstLetter: Boolean,


        /**
         * The updated attribute type.
         */
        type: AttributeType<T>


    ): ValueByEnum<DamageType, Attribute<T>> {
        return valueByEnum{ damageType ->
            val id = damageType.id
            val name = if (capitalizeFirstLetter) id.replaceFirstChar(Char::uppercaseChar) else id
            val attributeName = nameFormat.format(Locale.ROOT, name)
            update(attributeName, type)
        }
    }


    /**
     * Updates the attributes that have the name
     * ```
     * nameFormat.format(sensorType.id)
     * ```
     * where `sensorType` iterates over the values of [SensorType].
     * This allows batch-updating per-sensor-type attributes.
     */
    @Suppress("SameParameterValue")
    private fun <T: Any> updateWithSensorType(


        /**
         * The name format, in the sense of [String.format]
         */
        nameFormat: String,


        /**
         * Whether to capitalize the first letter of the sensor type name.
         */
        capitalizeFirstLetter: Boolean,


        /**
         * The updated attribute type.
         */
        type: AttributeType<T>


    ): ValueByEnum<SensorType, Attribute<T>>{
        return valueByEnum { sensorType ->
            val id = sensorType.id
            val name = if (capitalizeFirstLetter) id.replaceFirstChar(Char::uppercaseChar) else id
            val attributeName = nameFormat.format(Locale.ROOT, name)
            update(attributeName, type)
        }
    }


    /**
     * Updates all subsystem bonus attributes (like [update]) that have the name
     * ```
     * namePrefix + index
     * ```
     * where `index` goes from 1 until there is no attribute with that name.
     */
    private fun updateSubsystemBonuses(namePrefix: String): List<Attribute<Double>>{
        return generateSequence(1){ it + 1 }
            .map { index -> namePrefix + (if (index == 1) "" else "$index") }
            .map { name ->
                updateIfExists(name, DOUBLE)
            }
            .takeWhile { it != null }
            .filterNotNull()
            .toList()
    }


    /**
     * Returns an [AttributeType] for attributes whose values are other attributes.
     */
    private fun <T: Any> attribute(): AttributeType<Attribute<T>> = object : AttributeType<Attribute<T>> {

        @Suppress("UNCHECKED_CAST")
        override fun fromDouble(value: Double) = get(INT.fromDouble(value)) as Attribute<T>

        override fun toDouble(value: Attribute<T>) = INT.toDouble(value.id)

        override val isInvertedRelativeToDouble: Boolean?
            get() = null

    }


    /**
     * Returns the attribute with the given id, assuming it exists.
     */
    operator fun get(id: Int): Attribute<*> = byId[id]!!


    /**
     * Returns the attribute with the given id, or `null` if it doesn't exist.
     */
    fun getOrNull(id: Int): Attribute<*>? = byId[id]


    /**
     * Returns the attribute with the given name, or `null` if it doesn't exist.
     */
    operator fun get(name: String): Attribute<*>? = byName[name]


    val techLevel = update("techLevel", INT)
    val metaLevel = update("metaLevelOld", INT)
    val metaGroupId = update("metaGroupID", INT)

    val perceptionBonus = update("perceptionBonus", INT)
    val memoryBonus = update("memoryBonus", INT)
    val willpowerBonus = update("willpowerBonus", INT)
    val intelligenceBonus = update("intelligenceBonus", INT)
    val charismaBonus = update("charismaBonus", INT)

    val signatureRadius = update("signatureRadius", DOUBLE)
    val radius = update("radius", DOUBLE)  // Ship ball radius
    val capacity = update("capacity", DOUBLE)  // Cargo or module capacity, in m^3
    val isCapitalSize = update("isCapitalSize", BOOLEAN)
    val highSlots = update("hiSlots", INT)
    val medSlots = update("medSlots", INT)
    val lowSlots = update("lowSlots", INT)
    val rigSlots = update("rigSlots", INT)
    val cpuOutput = update("cpuOutput", DOUBLE)
    val powerOutput = update("powerOutput", DOUBLE)
    val calibration = update("upgradeCapacity", INT)
    val turretHardpoints = update("turretSlotsLeft", INT)
    val launcherHardpoints = update("launcherSlotsLeft", INT)
    val rigSize = update("rigSize", ITEM_SIZE)
    val droneCapacity = update("droneCapacity", INT)
    val droneBandwidth = update("droneBandwidth", DOUBLE)  // This can be a fraction due to Warp Core Stabilizers
    val droneBandwidthUsed = update("droneBandwidthUsed", INT)  // Drone attribute
    val droneControlRange = update("droneControlDistance", DOUBLE)
    val warpCapacitorNeed = update("warpCapacitorNeed", DOUBLE)  // Ship attribute
    val warpSpeedMultiplier = update("warpSpeedMultiplier", DOUBLE)  // Ship attribute

    val structureHp = update("hp", DOUBLE)
    val structureResonance = updateWithDamageType("%sDamageResonance", capitalizeFirstLetter = false, DOUBLE)  // Ship attribute
    val structureResonanceBonus = updateWithDamageType("hull%sDamageResonance", capitalizeFirstLetter = true, DOUBLE)  // Module bonus attribute

    val armorHp = update("armorHP", DOUBLE)
    val armorResonance = updateWithDamageType("armor%sDamageResonance", capitalizeFirstLetter = true, DOUBLE)

    val shieldHp = update("shieldCapacity", DOUBLE)
    val shieldRechargeTime = update("shieldRechargeRate", DOUBLE)  // In milliseconds
    val shieldResonance = updateWithDamageType("shield%sDamageResonance", capitalizeFirstLetter = true, DOUBLE)

    val capacitorCapacity = update("capacitorCapacity", DOUBLE)
    val capacitorRechargeTime = update("rechargeRate", DOUBLE)  // In milliseconds

    val targetingRange = update("maxTargetRange", DOUBLE)
    val maxLockedTargets = update("maxLockedTargets", INT)
    val scanResolution = update("scanResolution", DOUBLE)

    val sensorStrength = updateWithSensorType("scan%sStrength", true, DOUBLE)
    val ecmStrength = updateWithSensorType("scan%sStrengthBonus", true, DOUBLE) // Jam strength of ECM modules/bombs
    val ewTargetJam = update("ewTargetJam", DOUBLE)  // The presence of this attribute means the module or drone jams for a period of time
    val ecmJamDuration = update("ecmJamDuration", DOUBLE)  // The duration of a successful jam

    val mass = update("mass", DOUBLE)
    val inertiaModifier = update("agility", DOUBLE)
    val maxVelocity = update("maxVelocity", DOUBLE)  // Ship, drone and missile/bomb attribute
    val propulsionModuleSpeedFactor = update("fix_propulsionModuleSpeedFactor", DOUBLE)
    val propulsionModuleThrust = update("fix_propulsionModuleThrust", DOUBLE)
    val speedLimit = update("speedLimit", DOUBLE)  // Modifying module and the modified ship attr (by e.g. Entosis Links)
    val speedFactor = update("speedFactor", DOUBLE)  // Webifier and propulsion module attribute

    val energyWarfareResistance = update("energyWarfareResistance", DOUBLE)
    val weaponDisruptionResistance = update("weaponDisruptionResistance", DOUBLE)
    val sensorDampenerResistance = update("sensorDampenerResistance", DOUBLE)
    val targetPainterResistance = update("targetPainterResistance", DOUBLE)
    val ecmResistance = update("ECMResistance", DOUBLE)
    val remoteRepairImpedance = update("remoteRepairImpedance", DOUBLE)  // Ship attribute; effectiveness of remote repair
    val remoteAssistanceImpedance = update("remoteAssistanceImpedance", DOUBLE)  // Ship attribute; effectiveness of RTCs and RSBs

    val power = update("power", DOUBLE)  // Module power usage
    val cpu = update("cpu", DOUBLE) // Module CPU usage
    val calibrationNeed = update("upgradeCost", INT)  // Rig calibration usage
    val capacitorNeed = update("capacitorNeed", DOUBLE)  // Module capacitor need per activation

    val damage = updateWithDamageType("%sDamage", capitalizeFirstLetter = false, DOUBLE)  // Damage done by ammo/drones/smartbombs
    val weaponDamage = updateWithDamageType("fix_%sWeaponDamage", capitalizeFirstLetter = false, DOUBLE)  // Damage done by weapon modules
    val droneDamage = updateWithDamageType("fix_%sDroneDamage", capitalizeFirstLetter = false, DOUBLE)  // Damage done by drones
    val damageMultiplier = update("damageMultiplier", DOUBLE)  // Weapon and drone attribute, and also weapon damage modules (e.g. Heat Sink)
    val droneDamageBonus = update("droneDamageBonus", DOUBLE)  // Drone damage amplifiers and Triage Module
    val missileDamageMultiplierBonus = update("missileDamageMultiplierBonus", DOUBLE)  // BCS damage bonus

    val speedMultiplier = update("speedMultiplier", DOUBLE)  // Rate of fire bonus

    val weaponRangeMultiplier = update("weaponRangeMultiplier", DOUBLE)  // Module attribute

    val durationAttribute = update("fix_durationAttributeId", attribute<Double>())  // Module/drone attribute specifying its duration
    val optimalRangeAttribute = update("fix_rangeAttributeId", attribute<Double>())  // Module/drone attribute specifying optimal range
    val falloffRangeAttribute = update("fix_falloffAttributeId", attribute<Double>())  // Module/drone attribute specifying falloff
    val trackingSpeedAttribute = update("fix_trackingSpeedAttributeId", attribute<Double>())  // Module/drone attribute specifying tracking speed
    val moduleReactivationDelay = update("moduleReactivationDelay", DOUBLE)  // Module attribute

    val reloadTime = update("reloadTime", DOUBLE)  // The time to reload, in milliseconds

    val speed = update("speed", DOUBLE)  // Typically used as the activation duration attribute
    val duration = update("duration", DOUBLE)  // Sometimes used as the activation duration attribute, but also as the scan time for scan probe launchers
    val maxRange = update("maxRange", DOUBLE)  // Typically used as the optimal range attribute
    val falloff = update("falloff", DOUBLE)  // Typically used as the falloff attribute
    val trackingSpeed = update("trackingSpeed", DOUBLE)  // Typically used as the tracking speed attribute
    val signatureResolution = update("optimalSigRadius", DOUBLE)  // The signature resolution of e.g. turrets
    val missileFlightTime = update("explosionDelay", DOUBLE)  // Missile and bomb (Charge) attribute
    val explosionRange = update("explosionRange", DOUBLE)  // Bomb (charge) attribute

    val optimalRangeBonus = update("maxRangeBonus", DOUBLE)  // Tracking computers/enhancers/disruptors
    val falloffBonus = update("falloffBonus", DOUBLE)  // Tracking computers/enhancers/disruptors
    val trackingSpeedBonus = update("trackingSpeedBonus", DOUBLE)  // Tracking computers/enhancers/disruptors
    val missileVelocityBonus = update("missileVelocityBonus", DOUBLE)  // Guidance computers/enhancers/disruptors
    val missileFlightTimeBonus = update("explosionDelayBonus", DOUBLE)  // Guidance computers/enhancers/disruptors
    val explosionVelocityBonus = update("aoeVelocityBonus", DOUBLE)  // Guidance computers/enhancers/disruptors
    val explosionRadiusBonus = update("aoeCloudSizeBonus", DOUBLE)  // Guidance computers/enhancers/disruptors
    val aoeCloudSize = update("aoeCloudSize", DOUBLE)  // Missile and bomb explosion radius
    val aoeVelocity = update("aoeVelocity", DOUBLE)  // Missile explosion velocity
    val aoeDamageReductionFactor = update("aoeDamageReductionFactor", DOUBLE)  // Missile drf
    val missileDamageMultiplier = update("missileDamageMultiplier", DOUBLE)  // Character attribute!

    val targetingRangeBonus = update("maxTargetRangeBonus", DOUBLE)  // Remote/self sensor booster and remote sensor dampener
    val scanResolutionBonus = update("scanResolutionBonus", DOUBLE)  // Remote/self sensor booster and remote sensor dampener
    val sensorStrengthBonus = updateWithSensorType("scan%sStrengthPercent", true, DOUBLE) // Remote/self sensor booster
    val signatureRadiusBonus = update("signatureRadiusBonus", DOUBLE)  // Target painter
    val warpDisruptionStrength = update("warpScrambleStrength", INT)  // Warp disruptor/scrambler and WCS

    val maxTractorVelocity = update("maxTractorVelocity", DOUBLE)  // Tractor beam

    val shieldBoost = update("shieldBonus", DOUBLE)  // Shield Booster attribute
    val armorRepairAmount = update("armorDamageAmount", DOUBLE)  // Armor repairer (including drones)
    val structureDamageAmount = update("structureDamageAmount", DOUBLE)  // Hull repairer
    val energyNeutralizerAmount = update("energyNeutralizerAmount", DOUBLE)  // Neuts, EV-drones and Void bomb
    val energyTransferAmount = update("powerTransferAmount", DOUBLE)  // Nosferatus, remote capacitor transmitters

    val chargedArmorDamageMultiplier = update("chargedArmorDamageMultiplier", DOUBLE)  // Ancillary armor repairers
    val chargeRate = update("chargeRate", INT)  // The amount of charges consumed per activation

    val armorHPMultiplier = update("armorHPMultiplier", DOUBLE)  // Bonus to armor HP
    val signatureRadiusAdd = update("signatureRadiusAdd", DOUBLE)  // Shield extenders

    val charisma = update("charisma", INT)
    val intelligence = update("intelligence", INT)
    val memory = update("memory", INT)
    val perception = update("perception", INT)
    val willpower = update("willpower", INT)

    val skillLevel = update("skillLevel", INT)
    val primaryAttribute = update("primaryAttribute", attribute<Int>())
    val secondaryAttribute = update("secondaryAttribute", attribute<Int>())
    val skillTimeConstant = update("skillTimeConstant", INT)

    val skillRequirements: List<SkillRequirementAttributes> = updateIndexed("requiredSkill%d", INT)
        .zip(updateIndexed("requiredSkill%dLevel", INT))
        .map { SkillRequirementAttributes(it.first, it.second) }

    // Charge groups are module attributes that specify the groupId of a charge that can be loaded into the module
    val chargeGroups: List<Attribute<Int>> = updateIndexed("chargeGroup%d", INT)

    val chargeSize = update("chargeSize", ITEM_SIZE)  // The size of a charge; an attribute of modules and charges
    val maxGroupFitted = update("maxGroupFitted", INT)  // Max. number of modules of the group that can be fitted onto the ship
    val maxGroupOnline = update("maxGroupOnline", INT)  // Max. number of modules of the group that can be online at the same time
    val maxGroupActive = update("maxGroupActive", INT)  // Max. number of modules of the group that can be active at the same time

    // Modules with these attributes limit the ships they can be fitted onto by the ship type's groupId
    val canFitShipGroups: List<Attribute<Int>> = updateIndexed("canFitShipGroup%02d", INT)

    // Modules with these attributes limit the ships they can be fitted onto by the ship type's id
    val canFitShipTypes: List<Attribute<Int>> = updateIndexed("canFitShipType%d", INT)

    // Marks modules that can only be fit on capital-class ships
    val canOnlyFitOnCapitals = update("fix_canOnlyFitOnCapitals", BOOLEAN)

    // The capacitor increase provided by Cap Booster charges
    val capacitorBonus = update("capacitorBonus", DOUBLE)

    val probeCanScanShips = update("probeCanScanShips", BOOLEAN)  // True for combat probes
    val scanStrengthBonus = update("scanStrengthBonus", DOUBLE)  // Probe launcher attribute
    val baseMaxScanDeviation = update("baseMaxScanDeviation", DOUBLE)  // Scan probe attribute
    val baseScanRange = update("baseScanRange", DOUBLE)  // Scan probe attribute
    val baseScanSensorStrength = update("baseSensorStrength", DOUBLE)  // Scan probe attribute
    val maxScanDeviationModifierModule = update("maxScanDeviationModifierModule", DOUBLE)  // Scan Pinpointing Array
    val scanStrengthBonusModule = update("scanStrengthBonusModule", DOUBLE)  // Scan Rangefinding Array

    // Warp disruption probes, mobile warp disruptors and warp disruption field generators
    val warpScrambleRange = update("warpScrambleRange", DOUBLE)
    val doomsdayAOERange = update("doomsdayAOERange", DOUBLE)  // Webification probes and, I guess, doomsdays

    val maxActiveDrones = update("maxActiveDrones", INT)  // Character attribute
    val entityCruiseSpeed = update("entityCruiseSpeed", DOUBLE)  // Drone orbit speed
    val entityFlyRange = update("entityFlyRange", DOUBLE)  // Drone orbit range

    val consumptionQuantity = update("consumptionQuantity", DOUBLE)  // Amount of fuel (e.g. liquid ozone) consumed
    val consumptionTypeId = update("consumptionType", INT)  // The id of the type of fuel consumed
    val jumpDriveConsumptionAmount = update("jumpDriveConsumptionAmount", DOUBLE)  // Ship attribute

    val miningAmount = update("miningAmount", DOUBLE)
    val miningResidueProbability = update("miningWasteProbability", DOUBLE)  // Percentage
    val miningResidueVolumeMultiplier = update("miningWastedVolumeMultiplier", DOUBLE)

    val accessDifficultyBonus = update("accessDifficultyBonus", DOUBLE)  // Salvager, relic/data analyzer, salvage tackle rigs
    val virusCoherence = update("virusCoherence", DOUBLE)  // Relic/data analyzer
    val virusElementSlots = update("virusElementSlots", INT)  // Relic/data analyzer
    val virusStrength = update("virusStrength", DOUBLE)  // Relic/data analyzer

    val mjfgRadius = update("mjfgRadius", DOUBLE)  // Micro Jump Field Generator radius
    val mjdShipJumpCap = update("mjdShipJumpCap", INT)  // Micro Jump Field Generator cap on ships jumped

    val decloakTargetingDelay = update("cloakingTargetingDelay", DOUBLE)  // Cloaking Devices
    val specializationAsteroidYieldMultiplier = update("specializationAsteroidYieldMultiplier", DOUBLE)  // Mercoxit crystals

    // Reactive armor hardener
    val resistanceShiftAmount = update("resistanceShiftAmount", DOUBLE)

    // Triglavian modules
    val spoolupCycles = update("fix_spoolupCycles", DOUBLE)
    val damageMultiplierBonusPerCycle = update("damageMultiplierBonusPerCycle", DOUBLE)  // bonus per cycle
    val repairMultiplierBonusPerCycle = update("repairMultiplierBonusPerCycle", DOUBLE)  // bonus per cycle
    val damageMultiplierBonusMax = update("damageMultiplierBonusMax", DOUBLE)  // multiplier bonus at max spools
    val repairMultiplierBonusMax = update("repairMultiplierBonusMax", DOUBLE)  // multiplier bonus at max spools

    // Cloaks
    val stabilizeCloakDuration = update("stabilizeCloakDuration", DOUBLE)

    // Armor plate
    val massAddition = update("massAddition", DOUBLE)

    // Boosters (not exclusively)
    val shieldBoostMultiplier = update("shieldBoostMultiplier", DOUBLE)
    val armorDamageAmountBonus = update("armorDamageAmountBonus", DOUBLE)
    val capacitorCapacityBonus = update("capacitorCapacityBonus", DOUBLE)
    val rangeSkillBonus = update("rangeSkillBonus", DOUBLE)
    val aoeCloudSizeBonus = update("aoeCloudSizeBonus", DOUBLE)
    val velocityBonus = update("velocityBonus", DOUBLE)
    val damageMultiplierBonus = update("damageMultiplierBonus", DOUBLE)

    val tacticalDestroyerShipId = update("fix_tacticalDestroyerShipId", INT)
    val hasTacticalModes = update("fix_hasTacticalModes", BOOLEAN)
    val tacticalModeTypeKindId = update("fix_tacticalModeTypeKindId", INT)

    // Tech3 cruiser subsystems
    val highSlotModifier = update("hiSlotModifier", INT)
    val medSlotModifier = update("medSlotModifier", INT)
    val lowSlotModifier = update("lowSlotModifier", INT)
    val turretHardPointModifier = update("turretHardPointModifier", INT)
    val launcherHardPointModifier = update("launcherHardPointModifier", INT)

    val fitsToShipTypeId = update("fitsToShipType", INT)
    val subSystemSlot = update("subSystemSlot", INT)
    val maxSubSystems = update("maxSubSystems", INT)
    val powerEngineeringOutputBonus = update("powerEngineeringOutputBonus", DOUBLE)
    val cpuOutputBonus2 = update("cpuOutputBonus2", DOUBLE)
    val maxLockedTargetsBonus = update("maxLockedTargetsBonus", INT)
    val armorHPBonusAdd = update("armorHPBonusAdd", DOUBLE)
    val structureHPBonusAdd = update("structureHPBonusAdd", DOUBLE)
    val subsystemBonusMassAddition = update("subsystemBonusMassAddition", DOUBLE)
    val subsystemEnergyNeutFittingReduction = update("subsystemEnergyNeutFittingReduction", DOUBLE)
    val subsystemMMissileFittingReduction = update("subsystemMMissileFittingReduction", DOUBLE)
    val subsystemMETFittingReduction = update("subsystemMETFittingReduction", DOUBLE)
    val subsystemMHTFittingReduction = update("subsystemMHTFittingReduction", DOUBLE)
    val subsystemMPTFittingReduction = update("subsystemMPTFittingReduction", DOUBLE)
    val subsystemMRSBFittingReduction = update("subsystemMRSBFittingReduction", DOUBLE)
    val subsystemCommandBurstFittingReduction = update("subsystemCommandBurstFittingReduction", DOUBLE)
    val subsystemMRARFittingReduction = update("subsystemMRARFittingReduction", DOUBLE)
    val cloakingCpuNeedBonus = update("cloakingCpuNeedBonus", DOUBLE)
    val covertOpsAndReconOpsCloakModuleDelay = update("covertOpsAndReconOpsCloakModuleDelay", DOUBLE)
    val cargoCapacityAdd = update("cargoCapacityAdd", DOUBLE)
    val remoteArmorRepairerOptimalBonus = update("remoteArmorRepairerOptimalBonus", DOUBLE)
    val remoteArmorRepairerFalloffBonus = update("remoteArmorRepairerFalloffBonus", DOUBLE)
    val remoteShieldBoosterFalloffBonus = update("remoteShieldBoosterFalloffBonus", DOUBLE)
    val roleBonusCommandBurstAoERange = update("roleBonusCommandBurstAoERange", DOUBLE)
    val agilityBonusAdd = update("agilityBonusAdd", DOUBLE)
    val shipBonusRole1 = update("shipBonusRole1", DOUBLE)
    val shipBonusRole2 = update("shipBonusRole2", DOUBLE)

    /**
     * Groups the attributes affected by subsystem skills.
     */
    inner class Subsystems{
        val bonusAmarrCore: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusAmarrCore")
        val bonusCaldariCore: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusCaldariCore")
        val bonusMinmatarCore: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusMinmatarCore")
        val bonusGallenteCore: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusGallenteCore")
        val bonusAmarrDefensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusAmarrDefensive")
        val bonusCaldariDefensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusCaldariDefensive")
        val bonusMinmatarDefensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusMinmatarDefensive")
        val bonusGallenteDefensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusGallenteDefensive")
        val bonusAmarrOffensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusAmarrOffensive")
        val bonusCaldariOffensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusCaldariOffensive")
        val bonusMinmatarOffensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusMinmatarOffensive")
        val bonusGallenteOffensive: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusGallenteOffensive")
        val bonusAmarrPropulsion: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusAmarrPropulsion")
        val bonusCaldariPropulsion: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusCaldariPropulsion")
        val bonusMinmatarPropulsion: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusMinmatarPropulsion")
        val bonusGallentePropulsion: List<Attribute<Double>> = updateSubsystemBonuses("subsystemBonusGallentePropulsion")
    }

    /**
     * The attributes related to subsystems.
     */
    val subsystems = Subsystems()

    // CONCORD ships (Marshal, Enforcer, Pacifier)
    val pilotSecurityStatus = update("pilotSecurityStatus", DOUBLE)

    // Scripts
    val maxRangeBonusBonus = update("maxRangeBonusBonus", DOUBLE)
    val missileVelocityBonusBonus = update("missileVelocityBonusBonus", DOUBLE)
    val maxTargetRangeBonusBonus = update("maxTargetRangeBonusBonus", DOUBLE)


    /**
     * Groups the warfare buffs attributes.
     */
    inner class Warfare{
        val shieldResonanceBonus = update("fix_warfareShieldResonanceBonus", DOUBLE)
        val shieldBoostersBonus = update("fix_warfareShieldBoostersBonus", DOUBLE)
        val shieldHitpointsBonus = update("fix_warfareShieldHitpointsBonus", DOUBLE)
        val armorResonanceBonus = update("fix_warfareArmorResonanceBonus", DOUBLE)
        val armorRepairersBonus = update("fix_warfareArmorRepairersBonus", DOUBLE)
        val armorHitpointsBonus = update("fix_warfareArmorHitpointsBonus", DOUBLE)
        val scanResolutionBonus = update("fix_warfareScanResolutionBonus", DOUBLE)
        val electronicSuperiorityBonus = update("fix_warfareElectronicSuperiorityBonus", DOUBLE)
        val sensorStrengthBonus = update("fix_warfareSensorStrengthBonus", DOUBLE)
        val ewarResistance = update("fix_warfareEwarResistance", DOUBLE)
        val signatureRadiusBonus = update("fix_warfareSignatureRadiusBonus", DOUBLE)
        val tackleRangeBonus = update("fix_warfareTackleRangeBonus", DOUBLE)
        val speedFactorBonus = update("fix_warfareSpeedFactorBonus", DOUBLE)
        val targetingRangeBonus = update("fix_warfareTargetingRangeBonus", DOUBLE)
        val agilityBonus = update("fix_warfareAgilityBonus", DOUBLE)

        @Suppress("unused")
        val mindlinkBonus = update("mindlinkBonus", DOUBLE)
        val buffDuration = update("buffDuration", DOUBLE)
    }


    /**
     *  Warfare buffs attributes.
     */
    val warfare = Warfare()


    inner class TacticalMode{
        val resonanceBonusDiv = updateWithDamageType("mode%sResistancePostDiv", capitalizeFirstLetter = true, DOUBLE)
        val sensorStrengthBonusDiv = updateWithSensorType("mode%sStrengthPostDiv", capitalizeFirstLetter = true, DOUBLE)
        val signatureRadiusBonusDiv = update("modeSignatureRadiusPostDiv", DOUBLE)
        val agilityBonusDiv = update("modeAgilityPostDiv", DOUBLE)
        val velocityBonusDiv = update("modeVelocityPostDiv", DOUBLE)
        val mwdVelocityPostDiv = update("modeMWDVelocityPostDiv", DOUBLE)  // Hecate only
        val maxRangeBonusDiv = update("modeMaxRangePostDiv", DOUBLE)
        val targetRangeBonusDiv = update("modeMaxTargetRangePostDiv", DOUBLE)
        val damageBonusDiv = update("modeDamageBonusPostDiv", DOUBLE)
        val ewarResistanceBonusDiv = update("modeEwarResistancePostDiv", DOUBLE)
        val mwdSigPenaltyBonusDiv = update("modeMWDSigPenaltyPostDiv", DOUBLE)
        val mwdCapBonusDiv = update("modeMWDCapPostDiv", DOUBLE)
        val trackingBonusDiv = update("modeTrackingPostDiv", DOUBLE)
        val armorRepDurationBonusDiv = update("modeArmorRepDurationPostDiv", DOUBLE)
    }


    /**
     * Tactical mode effect attributes.
     */
    val tacticalMode = TacticalMode()

    // (Laser) Crystal attributes
    val crystalVolatilityChance = update("crystalVolatilityChance", DOUBLE)
    val crystalVolatilityDamage = update("crystalVolatilityDamage", DOUBLE)
    val crystalsGetDamaged = update("crystalsGetDamaged", BOOLEAN)

    val boosterDuration = update("boosterDuration", DOUBLE)
    val boosterSideEffectChance = updateIndexed("boosterEffectChance%d", DOUBLE)

    // Environmental effect attributes (but probably not just)
    val shieldHitpointsMultiplier = update("shieldCapacityMultiplier", DOUBLE)
    val signatureRadiusMultiplier = update("signatureRadiusMultiplier", DOUBLE)
    val shieldResistanceBonus = updateWithDamageType("shield%sDamageResistanceBonus", capitalizeFirstLetter = true, DOUBLE)
    val armorResistanceBonus = updateWithDamageType("armor%sDamageResistanceBonus", capitalizeFirstLetter = true, DOUBLE)
    val rechargeRateMultiplier = update("rechargeRateMultiplier", DOUBLE)
    val energyWarfareStrengthMultiplier = update("energyWarfareStrengthMultiplier", DOUBLE)
    val agilityMultiplier = update("agilityMultiplier", DOUBLE)
    val maxTargetRangeMultiplier = update("maxTargetRangeMultiplier", DOUBLE)
    val missileVelocityMultiplier = update("missileVelocityMultiplier", DOUBLE)
    val maxVelocityMultiplier = update("maxVelocityMultiplier", DOUBLE)
    val explosionVelocityMultiplier = update("aoeVelocityMultiplier", DOUBLE)
    val explosionRadiusMultiplier = update("aoeCloudSizeMultiplier", DOUBLE)
    val stasisWebStrengthMultiplier = update("stasisWebStrengthMultiplier", DOUBLE)
    val armorRepairAmountMultiplier = update("armorDamageAmountMultiplier", DOUBLE)
    val armorRemoteRepairAmountMultiplier = update("armorDamageAmountMultiplierRemote", DOUBLE)
    val shieldBoostAmountMultiplier = update("shieldBonusMultiplier", DOUBLE)
    val shieldRemoteBoostAmountMultiplier = update("shieldBonusMultiplierRemote", DOUBLE)
    val capacitorCapacityMultiplier = update("capacitorCapacityMultiplierSystem", DOUBLE)
    val energyTransferAmountMultiplier = update("energyTransferAmountBonus", DOUBLE)
    val trackingSpeedMultiplier = update("trackingSpeedMultiplier", DOUBLE)
    val damageMultiplierMultiplier = update("damageMultiplierMultiplier", DOUBLE)
    val targetPainterEffectivenessMultiplier = update("targetPainterStrengthMultiplier", DOUBLE)
    val heatDamageMultiplier = update("heatDamageMultiplier", DOUBLE)
    val overloadBonusMultiplier = update("overloadBonusMultiplier", DOUBLE)
    val smartbombRangeMultiplier = update("empFieldRangeMultiplier", DOUBLE)
    val bombEffectivenessMultiplier = update("smartbombDamageMultiplier", DOUBLE)
    val smallWeaponDamageMultiplier = update("smallWeaponDamageMultiplier", DOUBLE)
    val virusCoherenceBonus = update("virusCoherenceBonus", DOUBLE)
    val scanProbeStrengthBonus = update("scanProbeStrengthBonus", DOUBLE)
    val disallowCloaking = update("disallowCloaking", BOOLEAN)
    val miningDurationMultiplier = update("miningDurationMultiplier", DOUBLE)
    val warpSpeedBonus = update("warpSpeedBonus", DOUBLE)
    val shieldBoosterDurationBonus = update("shieldBoosterDurationBonus", DOUBLE)
    val armorRepairerDurationBonus = update("armorRepairDurationBonus", DOUBLE)
    val maxVelocityBonus = update("implantBonusVelocity", DOUBLE)
    val capRechargeBonus = update("capRechargeBonus", DOUBLE)
    val armorHpBonus = update("armorHpBonus", DOUBLE)
    val shieldHpBonus = update("shieldHpBonus", DOUBLE)
}


/**
 * The value of some attribute.
 */
class AttributeValue(
    val attributeId: Int,
    val value: Double,
) {

    override fun toString() = "AttributeValue($attributeId=$value)"

}


/**
 * Groups the [AttributeValue]s for some entity and provides convenient access to them.
 */
class AttributeValues private constructor(


    /**
     * Maps [AttributeValue]s by their attribute ids.
     */
    private val valuesByAttributeId: MutableMap<Int, AttributeValue>


): Iterable<AttributeValue> by valuesByAttributeId.values {


    /**
     * Creates an [AttributeValues] instance from the given list of [AttributeValue]s
     */
    constructor(attributeValues: Iterable<AttributeValue>):
            this(attributeValues.associateByTo(mutableMapOf()) { it.attributeId } )


    /**
     * Returns the value of the given attribute.
     * The value must be present.
     */
    fun <T: Any> get(attribute: Attribute<T>): T {
        val attributeValue = valuesByAttributeId[attribute.id]
            ?: throw BadEveDataException("No attribute value for attribute ${attribute.id} found")
        return attribute.valueFromDouble(attributeValue.value)
    }


    /**
     * Returns the value of the given attribute, or `null` if it's not present.
     */
    fun <T: Any> getOrNull(attribute: Attribute<T>): T? {
        return valuesByAttributeId[attribute.id]?.let { attribute.valueFromDouble(it.value) }
    }


    /**
     * Returns the value of the given attribute, or the given default value if it's not present.
     */
    fun <T: Any> getOrDefault(attribute: Attribute<T>, defaultValue: T): T {
        return valuesByAttributeId[attribute.id]?.let { attribute.valueFromDouble(it.value) } ?: defaultValue
    }


    /**
     * Sets the value of the given attribute.
     */
    fun <T: Any> set(attribute: Attribute<T>, value: T) {
        valuesByAttributeId[attribute.id] = AttributeValue(attribute.id, attribute.valueToDouble(value))
    }


    /**
     * Returns the underlying [Double] value of the attribute with the given id.
     */
    fun getDoubleValue(attributeId: Int): Double {
        val attributeValue = valuesByAttributeId[attributeId]
            ?: throw BadEveDataException("No attribute value for attribute $attributeId found")
        return attributeValue.value
    }


    /**
     * Returns the underlying [Double] value of the given attribute.
     */
    fun getDoubleValue(attribute: Attribute<*>): Double {
        val attributeValue = valuesByAttributeId[attribute.id]
            ?: throw BadEveDataException("No attribute value for attribute $attribute found")
        return attributeValue.value
    }


    /**
     * Sets the underlying [Double] value of the attribute with the given id.
     */
    fun setDoubleValue(attributeId: Int, value: Double) {
        valuesByAttributeId[attributeId] = AttributeValue(attributeId, value)
    }


    /**
     * Returns whether the given attribute is present.
     */
    fun has(attribute: Attribute<*>): Boolean {
        return valuesByAttributeId.containsKey(attribute.id)
    }


}


/**
 * The interface for types that have [AttributeValues].
 * Together with [Attribute.getValue], this allows [Attribute]s to be used as delegates of (Kotlin) properties in them:
 * ```
 * class MyEntity(override val attributeValues: AttributeValues){
 *     val techLevel: Int by attributes.techLevel
 * }
 * ```
 */
interface HasAttributeValues {


    /**
     * The attribute values.
     */
    val attributeValues: AttributeValues


}


/**
 * Encapsulates a "requiredSkill" and a "requiredSkillLevel" attribute.
 * Together they represent a requirement for a certain skill to be trained to a certain level.
 */
class SkillRequirementAttributes(


    /**
     * The id of the skill that must be trained.
     */
    val skillId: Attribute<Int>,


    /**
     * The level of the skill that must be trained.
     */
    val level: Attribute<Int>


)

