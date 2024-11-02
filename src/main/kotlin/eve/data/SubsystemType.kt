package eve.data

import androidx.compose.runtime.Immutable


/**
 * The type of strategic cruiser subsystem, e.g. "Proteus Defensive - Covert Reconfiguration".
 */
@Immutable
class SubsystemType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData


) : EveItemType(
    attributes = attributes,
    typeData = typeData
) {


    /**
     * The id of the ship this subsystem is for.
     */
    val shipId: Int by attributes.fitsToShipTypeId


    /**
     * The "subsystem slot" of this subsystem.
     */
    private val subsystemSlot: Int by attributes.subSystemSlot


    /**
     * The kind of this subsystem.
     */
    val kind: Kind = Kind.valueBySubsystemSlot.getValue(subsystemSlot)


    /**
     * The amount of high module slots added by this subsystem type.
     */
    val highSlotsAdded: Int by attributes.highSlotModifier.orDefault(0)


    /**
     * The amount of medium module slots added by this subsystem type.
     */
    val medSlotsAdded: Int by attributes.medSlotModifier.orDefault(0)


    /**
     * The amount of low module slots added by this subsystem type.
     */
    val lowSlotsAdded: Int by attributes.lowSlotModifier.orDefault(0)


    /**
     * The amount of turret hardpoints added by this subsystem type.
     */
    @Suppress("unused")
    val turretHardpointsAdded: Int by attributes.turretHardPointModifier.orDefault(0)


    /**
     * The amount of missile hardpoints added by this subsystem type.
     */
    @Suppress("unused")
    val launcherHardpointsAdded: Int by attributes.launcherHardPointModifier.orDefault(0)


    /**
     * The kinds of subsystems, in their standard order.
     */
    enum class Kind(


        /**
         * The "slot" of this subsystem kind.
         */
        val subsystemSlot: Int,


        /**
         * The name of this subsystem kind.
         */
        val displayName: String


    ) {


        CORE(125, "Core"),
        DEFENSIVE(126, "Defensive"),
        OFFENSIVE(127, "Offensive"),
        PROPULSION(128, "Propulsion");


        companion object{


            /**
             * The [Kind] values by their subsystem slot.
             */
            internal val valueBySubsystemSlot = Kind.entries.associateBy { it.subsystemSlot }


        }


    }


}