package eve.data

import androidx.compose.runtime.Immutable
import eve.data.utils.ValueByEnum

/**
 * The type of ship, e.g. "Raven State Issue".
 */
@Immutable
class ShipType internal constructor(


    /**
     * The context [Attributes].
     */
    private val attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData


): EveItemType(
    attributes = attributes,
    typeData = typeData
) {


    /**
     * Signature radius, in meters.
     */
    val signatureRadius: Double by attributes.signatureRadius


    /**
     * Cargo capacity, in m^3.
     */
    @Suppress("unused")
    val cargoCapacity: Double by attributes.capacity


    /**
     * Whether this is a capital-class ship.
     */
    val isCapitalSize: Boolean by attributes.isCapitalSize.orDefault(false)


    /**
     * Fitting attributes.
     */
    val fitting = Fitting()


    /**
     * Defensive attributes (shield, armor, etc.)
     */
    val defenses = Defenses()


    /**
     * Capacitor attributes.
     */
    val capacitor = Capacitor()


    /**
     * Targeting attributes (range, sensor strength, etc.)
     */
    val targeting = Targeting()


    /**
     * Propulsion attributes (maximum velocity etc.)
     */
    val propulsion = Propulsion()


    /**
     * Drone attributes (capacity, bandwidth, etc.)
     */
    val drones = Drones()


    /**
     * Whether this ship type has tactical modes.
     */
    val hasTacticalModes: Boolean by attributes.hasTacticalModes.orDefault(false)


    /**
     * Whether this ship type uses subsystems.
     */
    // Note: the value of maxSubSystems is, for some reason, 5 instead of 4.
    val usesSubsystems: Boolean = attributeValues.getOrDefault(attributes.maxSubSystems, 0) > 0


    /**
     * Returns whether the given module type can be fitted onto this ship type.
     *
     * Note that this doesn't include a check for hardpoints, because the amount of hardpoints depends on the fitted
     * subsystems for strategic cruisers.
     */
    fun canFit(moduleType: ModuleType): Boolean {
        if (moduleType.isRig) {
            val shipRigSize = this.fitting.rigSize ?: return false  // The ship can't fit rigs

            if (this.fitting.slots.rig == 0)
                return false  // The ship has no rig slots

            if (shipRigSize != moduleType.rigSize)
                return false  // The rig is of the wrong size for the ship
        }

        // If both are null, there is no fitting restriction.
        // If exactly one is not-null, the group/type of the not-null must match
        // If both are not-null, either group or type must match
        val canFitShipGroupIds = moduleType.canFitShipGroupIds
        val canFitShipTypes = moduleType.canFitShipTypes
        if ((canFitShipGroupIds != null) || (canFitShipTypes != null)) {
            val groupIdMatch = canFitShipGroupIds?.let { this.groupId in it } == true
            val thisMatch = canFitShipTypes?.let { this.itemId in it } == true
            if (!groupIdMatch && !thisMatch)
                return false
        }

        if (moduleType.canOnlyFitOnCapitals && !this.isCapitalSize)
            return false

        return true
    }


    /**
     * Ship fitting properties.
     */
    inner class Fitting: HasAttributeValues by this@ShipType {


        /**
         * Slot layout.
         */
        val slots = Slots()


        /**
         * CPU output of the ship.
         */
        val cpu: Double by attributes.cpuOutput


        /**
         * Power grid output of the ship.
         */
        val power: Double by attributes.powerOutput


        /**
         * Calibration of the ship.
         */
        val calibration: Int by attributes.calibration


        /**
         * Number of turret hardpoints available on the ship.
         *
         * Note that the value for strategic cruisers is 0.
         */
        val turretHardpoints: Int by attributes.turretHardpoints


        /**
         * Number of missile launcher hardpoints available on the ship.
         * Note that the value for strategic cruisers is 0.
         */
        val launcherHardpoints: Int by attributes.launcherHardpoints


        /**
         * Size of rigs that can be fitted to this ship; null if no rigs can be fitted.
         */
        val rigSize: ItemSize? by attributes.rigSize.orNull


        /**
         * The slot layout.
         */
        inner class Slots: HasAttributeValues by this@ShipType {


            /**
             * Number of high slots on the ship.
             */
            val high: Int by attributes.highSlots


            /**
             * Number of medium slots on the ship.
             */
            val med: Int by attributes.medSlots


            /**
             * Number of low slots on the ship.
             */
            val low: Int by attributes.lowSlots


            /**
             * Number of rig slots on the ship.
             */
            val rig: Int by attributes.rigSlots


        }


    }


    /**
     * Resonance (1-resist) values.
     */
    inner class Resonances(
        private val resonanceAttributeByDamageType: ValueByEnum<DamageType, Attribute<Double>>
    ): HasAttributeValues by this@ShipType {


        /**
         * Returns the resonance for the given damage type.
         */
        operator fun get(damageType: DamageType): Double =
            attributeValues.get(resonanceAttributeByDamageType[damageType])


        override fun toString() = DamageType.entries.map { get(it) }.joinToString(separator = ", ") {
            it.resonanceAsResistancePercentage(1)
        }


    }



    /**
     * The properties of a ship's defensive layer (e.g. structure, armor, shield).
     */
    sealed interface Defense {


        /**
         * Total hitpoints of this layer.
         */
        val hp: Double


        /**
         * The resonances of this layer.
         */
        val resonances: Resonances


    }


    /**
     * The properties of a ship's defense layers.
     */
    inner class Defenses: HasAttributeValues by this@ShipType{


        /**
         * Shield properties.
         */
        val shield = Shield()


        /**
         * Armor properties.
         */
        val armor = Armor()


        /**
         * Structure (hull) properties.
         */
        val structure = Structure()


        /**
         * Shield defense.
         */
        inner class Shield: Defense, HasAttributeValues by this@ShipType{


            /**
             * Shield hitpoints.
             */
            override val hp: Double by attributes.shieldHp


            /**
             * Shield resonances.
             */
            override val resonances = Resonances(attributes.shieldResonance)


            /**
             * Recharge rate, in milliseconds.
             */
            @Suppress("unused")
            val rechargeRate: Double by attributes.shieldRechargeTime


        }



        /**
         * Armor defense.
         */
        inner class Armor: Defense, HasAttributeValues by this@ShipType{


            /**
             * Armor hitpoints.
             */
            override val hp: Double by attributes.armorHp


            /**
             * Armor resonances.
             */
            override val resonances = Resonances(attributes.armorResonance)


        }


        /**
         * Structure defense.
         */
        inner class Structure: Defense, HasAttributeValues by this@ShipType{


            /**
             * Structure hitpoints.
             */
            override val hp: Double by attributes.structureHp


            /**
             * Structure resonances.
             */
            override val resonances = Resonances(attributes.structureResonance)


        }


    }


    /**
     * A ship's capacitor properties.
     */
    inner class Capacitor: HasAttributeValues by this@ShipType {


        /**
         * Total capacitor capacity, in GJ,
         */
        val capacity: Double by attributes.capacitorCapacity


        /**
         * Recharge rate, in milliseconds.
         */
        @Suppress("unused")
        val rechargeRate: Double by attributes.capacitorRechargeTime


    }


    /**
     * A ship's targeting properties.
     */
    inner class Targeting: HasAttributeValues by this@ShipType{


        /**
         * Maximum targeting range, in meters.
         */
        @Suppress("unused")
        val targetingRange: Double by attributes.targetingRange


        /**
         * Maximum number of locked targets.
         */
        @Suppress("unused")
        val maxLockedTargets: Int by attributes.maxLockedTargets


        /**
         * Scan resolution, in millimeters.
         */
        @Suppress("unused")
        val scanResolution: Double by attributes.scanResolution


        /**
         * Sensors.
         */
        val sensors: Sensors = Sensors()


        /**
         * Ship sensor strengths.
         */
        inner class Sensors: HasAttributeValues by this@ShipType {


            /**
             * The sensor strength of the given type.
             */
            private fun strengthOfType(sensorType: SensorType): Double =
                attributeValues.get(attributes.sensorStrength[sensorType])


            /**
             * The sensor type of this ship.
             */
            val type: SensorType = SensorType.entries.firstOrNull { strengthOfType(it) > 0 } ?:
                throw IllegalStateException("All sensor strengths are 0 for ${this@ShipType.name}")


            /**
             * The sensor strength of this ship.
             */
            val strength: Double = attributeValues.get(attributes.sensorStrength[type])


        }


    }


    /**
     * The ship's propulsion properties.
     */
    inner class Propulsion: HasAttributeValues by this@ShipType {


        /**
         * Mass, in kg.
         */
        val mass: Double by attributes.mass


        /**
         * Inertia modifier (agility).
         */
        @Suppress("unused")
        val inertiaModifier: Double by attributes.inertiaModifier


        /**
         * Maximum velocity, in m/s.
         */
        val maxVelocity: Double by attributes.maxVelocity


    }


    /**
     * The ship's drone properties.
     */
    inner class Drones: HasAttributeValues by this@ShipType {


        /**
         * The drone bay capacity, in m^3
         */
        val capacity: Int by attributes.droneCapacity


        /**
         * The drone bandwidth, in megabit/s
         */
        val bandwidth: Double by attributes.droneBandwidth


    }


}