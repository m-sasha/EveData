/**
 * Methods for identifying drones..
 */

package eve.data.typeid

import eve.data.DroneType
import eve.data.EveData


/**
 * Returns whether the given drone type is a stasis webifying drone.
 */
context(EveData)
fun DroneType.isStasisWebifyingDrone() =
    group == groups.stasisWebifyingDrone


/**
 * Returns whether the given drone type is an energy neutralizing drone.
 */
context(EveData)
fun DroneType.isEnergyNeutralizerDrone() =
    group == groups.energyNeutralizerDrone


/**
 * The ids of the tracking disrupting drones.
 */
private val TRACKING_DISRUPTING_DRONE_IDS = intArrayOf(23727, 23725, 23510)


/**
 * Returns whether the given drone is a tracking disruption drone.
 */
context(EveData)
fun DroneType.isTrackingDisruptingDrone() =
    itemId in TRACKING_DISRUPTING_DRONE_IDS


/**
 * The ids of the target painting drones.
 */
private val TARGET_PAINTING_DRONE_IDS = intArrayOf(23723, 23721, 23512)


/**
 * Returns whether the given drone is a target painting drone.
 */
context(EveData)
fun DroneType.isTargetPaintingDrone() =
    itemId in TARGET_PAINTING_DRONE_IDS


/**
 * The ids of the sensor dampening drones.
 */
private val SENSOR_DAMPENING_DRONE_IDS = intArrayOf(23715, 23713, 23506)


/**
 * Returns whether the given drone is a sensor dampening drone.
 */
context(EveData)
fun DroneType.isSensorDampeningDrone() =
    itemId in SENSOR_DAMPENING_DRONE_IDS


/**
 * The ids of the ECM drones.
 */
private val ECM_DRONE_IDS = intArrayOf(23707, 23705, 23473)


/**
 * Returns whether the given drone is an ECM drone.
 */
context(EveData)
fun DroneType.isEcmDrone() =
    itemId in ECM_DRONE_IDS


/**
 * The variation parent type ids of shield boosting drones.
 */
private val SHIELD_MAINTENANCE_BOT_VARIATION_PARENT_TYPE_IDS = intArrayOf(23719, 23717, 22765)


/**
 * Returns whether the given drone is an armor repairing drone.
 */
context(EveData)
fun DroneType.isShieldMaintenanceBot() =
    variationParentTypeId in SHIELD_MAINTENANCE_BOT_VARIATION_PARENT_TYPE_IDS


/**
 * The ids of armor repairing drones.
 */
private val ARMOR_MAINTENANCE_BOT_VARIATION_PARENT_TYPE_IDS = intArrayOf(23711, 23709, 23523)


/**
 * Returns whether the given drone is an armor repairing drone.
 */
context(EveData)
fun DroneType.isArmorMaintenanceBot() =
    variationParentTypeId in ARMOR_MAINTENANCE_BOT_VARIATION_PARENT_TYPE_IDS


/**
 * The ids of hull repairing drones.
 */
private val HULL_MAINTENANCE_BOT_VARIATION_PARENT_TYPE_IDS = intArrayOf(33706, 33704, 33671)


/**
 * Returns whether the given drone is a hull repairing drone.
 */
context(EveData)
fun DroneType.isHullMaintenanceBot() =
    variationParentTypeId in HULL_MAINTENANCE_BOT_VARIATION_PARENT_TYPE_IDS


/**
 * Returns whether the given drone is a mining drone.
 */
context(EveData)
fun DroneType.isMiningDrone() =
    group == groups.miningDrone


/**
 * Returns whether the given drone is a combat drone.
 */
context(EveData)
fun DroneType.isCombatDrone() =
    group == groups.combatDrone
