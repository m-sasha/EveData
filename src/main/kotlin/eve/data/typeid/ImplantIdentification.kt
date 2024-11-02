/**
 * Methods for identifying implants.
 */

package eve.data.typeid

import eve.data.EveData
import eve.data.ImplantType


/**
 * Returns whether the given implant is a mindlink.
 */
context(EveData)
fun ImplantType.isMindlinkImplant() =
    group == groups.mindlinks


/**
 * The variation parent type id of "Repair Systems" implants.
 */
private const val REPAIR_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3291


/**
 * Returns whether the given implant is a "Repair Systems" implant.
 */
context(EveData)
fun ImplantType.isRepairSystemsImplant() =
    variationParentTypeId == REPAIR_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Numon Family Heirloom" implant.
 */
private const val NUMON_FAMILY_HEIRLOOM_IMPLANT_TYPE_ID = 20358


/**
 * Returns whether the given implant is a "Numon Family Heirloom" implant.
 */
context(EveData)
fun ImplantType.isNumonFamilyHeirloomImplant() =
    itemId == NUMON_FAMILY_HEIRLOOM_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Remote Armor Repair Systems" implants.
 */
private const val REMOTE_ARMOR_REPAIR_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3414


/**
 * Returns whether the given implant is a "Remote Armor Repair Systems" implant.
 */
context(EveData)
fun ImplantType.isRemoteArmorRepairSystemsImplant() =
    variationParentTypeId == REMOTE_ARMOR_REPAIR_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Mechanic" implants.
 */
private const val MECHANIC_IMPLANT_VARIATION_PARENT_TYPE_ID = 3471


/**
 * Returns whether the given implant is a "Mechanic" implant.
 */
context(EveData)
fun ImplantType.isMechanicImplant() =
    variationParentTypeId == MECHANIC_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Repair Proficiency" implants.
 */
private const val REPAIR_PROFICIENCY_IMPLANT_VARIATION_PARENT_TYPE_ID = 3476


/**
 * Returns whether the given implant is a "Repair Proficiency" implant.
 */
context(EveData)
fun ImplantType.isRepairProficiencyImplant() =
    variationParentTypeId == REPAIR_PROFICIENCY_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Hull Upgrades" implants.
 */
private const val HULL_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3479


/**
 * Returns whether the given implant is a "Hull Upgrades" implant.
 */
context(EveData)
fun ImplantType.isHullUpgradesImplant() =
    variationParentTypeId == HULL_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Imperial Navy Modified 'Noble' Implant" implant.
 */
private const val IMPERIAL_NAVY_NOBLE_IMPLANT_TYPE_ID = 32254


/**
 * Returns whether the given implant is an "Imperial Navy Modified 'Noble' Implant" implant.
 */
context(EveData)
fun ImplantType.isImperialNavyNobleImplant() =
    itemId == IMPERIAL_NAVY_NOBLE_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Drone Navigation" implants.
 */
private const val DRONE_NAVIGATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 54534


/**
 * Returns whether the given implant is a "Drone Navigation" implant.
 */
context(EveData)
fun ImplantType.isDroneNavigationImplant() =
    variationParentTypeId == DRONE_NAVIGATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Overmind 'Goliath' Drone Tuner T25-10S" implant.
 */
private const val GOLIATH_DRONE_TUNER_IMPLANT_TYPE_ID = 48746


/**
 * Returns whether the given implant is the "Overmind 'Goliath' Drone Tuner T25-10S" implant.
 */
context(EveData)
fun ImplantType.isGoliathDroneTunerImplant() =
    itemId == GOLIATH_DRONE_TUNER_IMPLANT_TYPE_ID


/**
 * The type id of the "Overmind 'Hawkmoth' Drone Tuner S10-25T" implant.
 */
private const val HAWKMOTH_DRONE_TUNER_IMPLANT_TYPE_ID = 48747


/**
 * Returns whether the given implant is the "Overmind 'Hawkmoth' Drone Tuner S10-25T" implant.
 */
context(EveData)
fun ImplantType.isHawkmothDroneTunerImplant() =
    itemId == HAWKMOTH_DRONE_TUNER_IMPLANT_TYPE_ID


/**
 * The type id of the "Creodron 'Bumblebee' Drone Tuner T10-5D" implant.
 */
private const val BUMBLEBEE_DRONE_TUNER_IMPLANT_TYPE_ID = 48149


/**
 * Returns whether the given implant is the "Creodron 'Bumblebee' Drone Tuner T10-5D" implant.
 */
context(EveData)
fun ImplantType.isBumblebeeDroneTunerImplant() =
    itemId == BUMBLEBEE_DRONE_TUNER_IMPLANT_TYPE_ID


/**
 * The type id of the "Creodron 'Yellowjacket' Drone Tuner D5-10T" implant.
 */
private const val YELLOWJACKET_DRONE_TUNER_IMPLANT_TYPE_ID = 48148


/**
 * Returns whether the given implant is the "Creodron 'Yellowjacket' Drone Tuner D5-10T" implant.
 */
context(EveData)
fun ImplantType.isYellowjacketDroneTunerImplant() =
    itemId == YELLOWJACKET_DRONE_TUNER_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Drone Sharpshooting" implants.
 */
private const val DRONE_SHARPSHOOTING_IMPLANT_VARIATION_PARENT_TYPE_ID = 54537


/**
 * Returns whether the given implant is a "Drone Sharpshooting" implant.
 */
context(EveData)
fun ImplantType.isDroneSharpshootingImplant() =
    variationParentTypeId == DRONE_SHARPSHOOTING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Repair Drone Operation" implants.
 */
private const val REPAIR_DRONE_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 54540


/**
 * Returns whether the given implant is a "Repair Drone Operation" implant.
 */
context(EveData)
fun ImplantType.isRepairDroneOperationImplant() =
    variationParentTypeId == REPAIR_DRONE_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Drone Durability" implants.
 */
private const val DRONE_DURABILITY_IMPLANT_VARIATION_PARENT_TYPE_ID = 54543


/**
 * Returns whether the given implant is a "Drone Durability" implant.
 */
context(EveData)
fun ImplantType.isDroneDurabilityImplant() =
    variationParentTypeId == DRONE_DURABILITY_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Stasis Webifier" implants.
 */
private const val STASIS_WEBIFIER_IMPLANT_VARIATION_PARENT_TYPE_ID = 47261


/**
 * Returns whether the given implant is a "Stasis Webifier" implant.
 */
context(EveData)
fun ImplantType.isStasisWebifierImplant() =
    variationParentTypeId == STASIS_WEBIFIER_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Entanglement Optimizer" implants.
 */
private const val ENTANGLEMENT_OPTIMIZER_IMPLANT_VARIATION_PARENT_TYPE_ID = 50053


/**
 * Returns whether the given implant is an "Entanglement Optimizer" implant.
 */
context(EveData)
fun ImplantType.isEntanglementOptimizerImplant() =
    variationParentTypeId == ENTANGLEMENT_OPTIMIZER_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Propulsion Jamming" implants.
 */
private const val PROPULSION_JAMMING_IMPLANT_VARIATION_PARENT_TYPE_ID = 3277


/**
 * Returns whether the given implant is a "Propulsion Jamming" implant.
 */
context(EveData)
fun ImplantType.isPropulsionJammingImplant() =
    variationParentTypeId == PROPULSION_JAMMING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Electronic Warfare" implants.
 */
private const val ELECTRONIC_WARFARE_IMPLANT_VARIATION_PARENT_TYPE_ID = 3271


/**
 * Returns whether the given implant is an "Electronic Warfare" implant.
 */
context(EveData)
fun ImplantType.isElectronicWarfareImplant() =
    variationParentTypeId == ELECTRONIC_WARFARE_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Sensor Linking" implants.
 */
private const val SENSOR_LINKING_IMPLANT_VARIATION_PARENT_TYPE_ID = 3280


/**
 * Returns whether the given implant is a "Sensor Linking" implant.
 */
context(EveData)
fun ImplantType.isSensorLinkingImplant() =
    variationParentTypeId == SENSOR_LINKING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Target Painting" implants.
 */
private const val TARGET_PAINTING_IMPLANT_VARIATION_PARENT_TYPE_ID = 3288


/**
 * Returns whether the given implant is a "Target Painting" implant.
 */
context(EveData)
fun ImplantType.isTargetPaintingImplant() =
    variationParentTypeId == TARGET_PAINTING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Weapon Disruption" implants.
 */
private const val WEAPON_DISRUPTION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3283


/**
 * Returns whether the given implant is a "Weapon Disruption" implant.
 */
context(EveData)
fun ImplantType.isWeaponDisruptionImplant() =
    variationParentTypeId == WEAPON_DISRUPTION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Capacitor Systems Operation" implants.
 */
private const val CAPACITOR_SYSTEMS_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3240


/**
 * Returns whether the given implant is a "Capacitor Systems Operation" implant.
 */
context(EveData)
fun ImplantType.isCapacitorSystemsOperationImplant() =
    variationParentTypeId == CAPACITOR_SYSTEMS_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Power Grid Management" implants.
 */
private const val POWER_GRID_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID = 3256


/**
 * Returns whether the given implant is a "Power Grid Management" implant.
 */
context(EveData)
fun ImplantType.isPowerGridManagementImplant() =
    variationParentTypeId == POWER_GRID_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "CPU Management" implants.
 */
private const val CPU_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID = 3265


/**
 * Returns whether the given implant is a "CPU Management" implant.
 */
context(EveData)
fun ImplantType.isCpuManagementImplant() =
    variationParentTypeId == CPU_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Electronics Upgrades" implants.
 */
private const val ELECTRONICS_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3262


/**
 * Returns whether the given implant is an "Electronics Upgrades" implant.
 */
context(EveData)
fun ImplantType.isElectronicsUpgradesImplant() =
    variationParentTypeId == ELECTRONICS_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Capacitor Emission Systems" implants.
 */
private const val CAPACITOR_EMISSION_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3247


/**
 * Returns whether the given implant is a "Capacitor Emission Systems" implant.
 */
context(EveData)
fun ImplantType.isCapacitorEmissionSystemsImplant() =
    variationParentTypeId == CAPACITOR_EMISSION_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Energy Grid Upgrades" implants.
 */
private const val ENERGY_GRID_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3253


/**
 * Returns whether the given implant is an "Energy Grid Upgrades" implant.
 */
context(EveData)
fun ImplantType.isEnergyGridUpgradesImplant() =
    variationParentTypeId == ENERGY_GRID_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Energy Pulse Weapons" implants.
 */
private const val ENERGY_PULSE_WEAPONS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3250


/**
 * Returns whether the given implant is an "Energy Pulse Weapons" implant.
 */
context(EveData)
fun ImplantType.isEnergyPulseWeaponsImplant() =
    variationParentTypeId == ENERGY_PULSE_WEAPONS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Capacitor Management" implants.
 */
private const val CAPACITOR_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID = 3237


/**
 * Returns whether the given implant is a "Capacitor Management" implant.
 */
context(EveData)
fun ImplantType.isCapacitorManagementImplant() =
    variationParentTypeId == CAPACITOR_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Small Projectile Turret" implants.
 */
private const val SMALL_PROJECTILE_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3199


/**
 * Returns whether the given implant is a "Small Projectile Turret" implant.
 */
context(EveData)
fun ImplantType.isSmallProjectileTurretImplant() =
    variationParentTypeId == SMALL_PROJECTILE_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Small Energy Turret" implants.
 */
private const val SMALL_ENERGY_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3202


/**
 * Returns whether the given implant is a "Small Energy Turret" implant.
 */
context(EveData)
fun ImplantType.isSmallEnergyTurretImplant() =
    variationParentTypeId == SMALL_ENERGY_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Small Hybrid Turret" implants.
 */
private const val SMALL_HYBRID_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3225


/**
 * Returns whether the given implant is a "Small Hybrid Turret" implant.
 */
context(EveData)
fun ImplantType.isSmallHybridTurretImplant() =
    variationParentTypeId == SMALL_HYBRID_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Motion Prediction" implants.
 */
private const val MOTION_PREDICTION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3190


/**
 * Returns whether the given implant is a "Motion Prediction" implant.
 */
context(EveData)
fun ImplantType.isMotionPredictionTurretImplant() =
    variationParentTypeId == MOTION_PREDICTION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Ogdin's Eye Coordination Enhancer" implant.
 */
private const val OGDINS_EYE_COORDINATION_IMPLANT_TYPE_ID = 20443


/**
 * Returns whether the given implant is an "Ogdin's Eye Coordination Enhancer" implant.
 */
context(EveData)
fun ImplantType.isOgdinsEyeCoordinationEnhancerImplant() =
    itemId == OGDINS_EYE_COORDINATION_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Controlled Bursts" implants.
 */
private const val CONTROLLED_BURSTS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3203


/**
 * Returns whether the given implant is a "Controlled Bursts" implant.
 */
context(EveData)
fun ImplantType.isControlledBurstsImplant() =
    variationParentTypeId == CONTROLLED_BURSTS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Trajectory Analysis" implants.
 */
private const val TRAJECTORY_ANALYSIS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3220


/**
 * Returns whether the given implant is a "Trajectory Analysis" implant.
 */
context(EveData)
fun ImplantType.isTrajectoryAnalysisImplant() =
    variationParentTypeId == TRAJECTORY_ANALYSIS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Medium Projectile Turret" implants.
 */
private const val MEDIUM_PROJECTILE_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3185


/**
 * Returns whether the given implant is a "Medium Projectile Turret" implant.
 */
context(EveData)
fun ImplantType.isMediumProjectileTurretImplant() =
    variationParentTypeId == MEDIUM_PROJECTILE_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Medium Energy Turret" implants.
 */
private const val MEDIUM_ENERGY_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3206


/**
 * Returns whether the given implant is a "Medium Energy Turret" implant.
 */
context(EveData)
fun ImplantType.isMediumEnergyTurretImplant() =
    variationParentTypeId == MEDIUM_ENERGY_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Medium Hybrid Turret" implants.
 */
private const val MEDIUM_HYBRID_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3231


/**
 * Returns whether the given implant is a "Medium Hybrid Turret" implant.
 */
context(EveData)
fun ImplantType.isMediumHybridTurretImplant() =
    variationParentTypeId == MEDIUM_HYBRID_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Surgical Strike" implants.
 */
private const val SURGICAL_STRIKE_IMPLANT_VARIATION_PARENT_TYPE_ID = 3193


/**
 * Returns whether the given implant is a "Surgical Strike" implant.
 */
context(EveData)
fun ImplantType.isSurgicalStrikeImplant() =
    variationParentTypeId == SURGICAL_STRIKE_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Gunnery" implants.
 */
private const val GUNNERY_IMPLANT_VARIATION_PARENT_TYPE_ID = 3204


/**
 * Returns whether the given implant is a "Gunnery" implant.
 */
context(EveData)
fun ImplantType.isGunneryImplant() =
    variationParentTypeId == GUNNERY_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Pashan's Turret Customization Mindlink" implant.
 */
private const val PASHANS_TURRET_CUSTOMIZATION_MINDLINK_IMPLANT_TYPE_ID = 25868


/**
 * Returns whether the given implant is a "Pashan's Turret Customization Mindlink" implant.
 */
context(EveData)
fun ImplantType.isPashansTurretCustomizationMindlinkImplant() =
    itemId == PASHANS_TURRET_CUSTOMIZATION_MINDLINK_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Turret Sharpshooter" implants.
 */
private const val TURRET_SHARPSHOOTER_IMPLANT_VARIATION_PARENT_TYPE_ID = 3217


/**
 * Returns whether the given implant is a "Turret Sharpshooter" implant.
 */
context(EveData)
fun ImplantType.isTurretSharpshooterImplant() =
    variationParentTypeId == TURRET_SHARPSHOOTER_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Large Projectile Turret" implants.
 */
private const val LARGE_PROJECTILE_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3196


/**
 * Returns whether the given implant is a "Large Projectile Turret" implant.
 */
context(EveData)
fun ImplantType.isLargeProjectileTurretImplant() =
    variationParentTypeId == LARGE_PROJECTILE_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Large Energy Turret" implants.
 */
private const val LARGE_ENERGY_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3205


/**
 * Returns whether the given implant is a "Large Energy Turret" implant.
 */
context(EveData)
fun ImplantType.isLargeEnergyTurretImplant() =
    variationParentTypeId == LARGE_ENERGY_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Pashan's Turret Handling Mindlink" implant.
 */
private const val PASHANS_TURRET_HANDLING_MINDLINK_IMPLANT_TYPE_ID = 25867


/**
 * Returns whether the given implant is a "Pashan's Turret Handling Mindlink" implant.
 */
context(EveData)
fun ImplantType.isPashansTurretHandlingMindlinkImplant() =
    itemId == PASHANS_TURRET_HANDLING_MINDLINK_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Large Hybrid Turret" implants.
 */
private const val LARGE_HYBRID_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID = 3222


/**
 * Returns whether the given implant is a "Large Hybrid Turret" implant.
 */
context(EveData)
fun ImplantType.isLargeHybridTurretImplant() =
    variationParentTypeId == LARGE_HYBRID_TURRET_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Weapon Upgrades" implants.
 */
private const val WEAPON_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3228


/**
 * Returns whether the given implant is a "Weapon Upgrades" implant.
 */
context(EveData)
fun ImplantType.isWeaponUpgradesImplant() =
    variationParentTypeId == WEAPON_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Xl Torpedo Sharpshooter" implants.
 */
private const val XL_TORPEDO_SHARPSHOOTER_IMPLANT_VARIATION_PARENT_TYPE_ID = 3149


/**
 * Returns whether the given implant is a "Xl Torpedo Sharpshooter" implant.
 */
context(EveData)
fun ImplantType.isXlTorpedoSharpshooterImplant() =
    variationParentTypeId == XL_TORPEDO_SHARPSHOOTER_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Launcher CPU Efficiency" implants.
 */
private const val LAUNCHER_CPU_EFFICIENCY_IMPLANT_VARIATION_PARENT_TYPE_ID = 3144


/**
 * Returns whether the given implant is a "Launcher CPU Efficiency" implant.
 */
context(EveData)
fun ImplantType.isLauncherCpuEfficiencyImplant() =
    variationParentTypeId == LAUNCHER_CPU_EFFICIENCY_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Cruise Missiles" implants.
 */
private const val CRUISE_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3181


/**
 * Returns whether the given implant is a "Cruise Missiles" implant.
 */
context(EveData)
fun ImplantType.isCruiseMissilesImplant() =
    variationParentTypeId == CRUISE_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Torpedoes" implants.
 */
private const val TORPEDOES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3176


/**
 * Returns whether the given implant is a "Torpedoes" implant.
 */
context(EveData)
fun ImplantType.isTorpedoesImplant() =
    variationParentTypeId == TORPEDOES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Missile Bombardment" implants.
 */
private const val MISSILE_BOMBARDMENT_IMPLANT_VARIATION_PARENT_TYPE_ID = 3128


/**
 * Returns whether the given implant is a "Missile Bombardment" implant.
 */
context(EveData)
fun ImplantType.isMissileBombardmentImplant() =
    variationParentTypeId == MISSILE_BOMBARDMENT_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Missile Projection" implants.
 */
private const val MISSILE_PROJECTION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3133


/**
 * Returns whether the given implant is a "Missile Projection" implant.
 */
context(EveData)
fun ImplantType.isMissileProjectionImplant() =
    variationParentTypeId == MISSILE_PROJECTION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Heavy Assault Missiles" implants.
 */
private const val HEAVY_ASSAULT_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3157


/**
 * Returns whether the given implant is a "Heavy Assault Missiles" implant.
 */
context(EveData)
fun ImplantType.isHeavyAssaultMissilesImplant() =
    variationParentTypeId == HEAVY_ASSAULT_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Heavy Missiles" implants.
 */
private const val HEAVY_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3165


/**
 * Returns whether the given implant is a "Heavy Missiles" implant.
 */
context(EveData)
fun ImplantType.isHeavyMissilesImplant() =
    variationParentTypeId == HEAVY_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Guided Missile Precision" implants.
 */
private const val GUIDED_MISSILE_PRECISION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3125


/**
 * Returns whether the given implant is a "Guided Missile Precision" implant.
 */
context(EveData)
fun ImplantType.isGuidedMissilePrecisionImplant() =
    variationParentTypeId == GUIDED_MISSILE_PRECISION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Defender Missiles" implants.
 */
private const val DEFENDER_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3152


/**
 * Returns whether the given implant is a "Defender Missiles" implant.
 */
context(EveData)
fun ImplantType.isDefenderMissilesImplant() =
    variationParentTypeId == DEFENDER_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Target Navigation Prediction" implants.
 */
private const val TARGET_NAVIGATION_PREDICTION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3141


/**
 * Returns whether the given implant is a "Target Navigation Prediction" implant.
 */
context(EveData)
fun ImplantType.isTargetNavigationPredictionImplant() =
    variationParentTypeId == TARGET_NAVIGATION_PREDICTION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Light Missiles" implants.
 */
private const val LIGHT_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3168


/**
 * Returns whether the given implant is a "Light Missiles" implant.
 */
context(EveData)
fun ImplantType.isLightMissilesImplant() =
    variationParentTypeId == LIGHT_MISSILES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Rockets" implants.
 */
private const val ROCKETS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3173


/**
 * Returns whether the given implant is a "Rockets" implant.
 */
context(EveData)
fun ImplantType.isRocketsImplant() =
    variationParentTypeId == ROCKETS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Whelan Machorin's Ballistic Smartlink" implant.
 */
private const val WHELAN_MACHORINS_BALLISTIC_SMARTLINK_IMPLANT_TYPE_ID = 20371


/**
 * Returns whether the given implant is the "Whelan Machorin's Ballistic Smartlink" implant.
 */
context(EveData)
fun ImplantType.isWhelanMachorinsBallisticSmartlinkImplant() =
    itemId == WHELAN_MACHORINS_BALLISTIC_SMARTLINK_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Rapid Launch" implants.
 */
private const val RAPID_LAUNCH_IMPLANT_VARIATION_PARENT_TYPE_ID = 3136


/**
 * Returns whether the given implant is a "Rapid Launch" implant.
 */
context(EveData)
fun ImplantType.isRapidLaunchImplant() =
    variationParentTypeId == RAPID_LAUNCH_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Auto Targeting Explosion Radius" implants.
 */
private const val AUTO_TARGETING_EXPLOSION_RADIUS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3160


/**
 * Returns whether the given implant is an "Auto Targeting Explosion Radius" implant.
 */
context(EveData)
fun ImplantType.isAutoTargetingExplosionRadiusImplant() =
    variationParentTypeId == AUTO_TARGETING_EXPLOSION_RADIUS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Acceleration Control" implants.
 */
private const val ACCELERATION_CONTROL_IMPLANT_VARIATION_PARENT_TYPE_ID = 3120


/**
 * Returns whether the given implant is an "Acceleration Control" implant.
 */
context(EveData)
fun ImplantType.isAccelerationControlImplant() =
    variationParentTypeId == ACCELERATION_CONTROL_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Zor's Custom Navigation Hyper-Link" implant.
 */
private const val ZORS_CUSTOM_NAVIGATION_HYPERLINK_IMPLANT_TYPE_ID = 24663


/**
 * Returns whether the given implant is the "Zor's Custom Navigation Hyper-Link" implant.
 */
context(EveData)
fun ImplantType.isZorsCustomNavigationHyperLinkImplant() =
    itemId == ZORS_CUSTOM_NAVIGATION_HYPERLINK_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Afterburner" implants.
 */
private const val AFTERBURNER_IMPLANT_VARIATION_PARENT_TYPE_ID = 3104


/**
 * Returns whether the given implant is an "Afterburner" implant.
 */
context(EveData)
fun ImplantType.isAfterburnerImplant() =
    variationParentTypeId == AFTERBURNER_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Zor's Custom Navigation Link" implant.
 */
private const val ZORS_CUSTOM_NAVIGATION_LINK_IMPLANT_TYPE_ID = 19500


/**
 * Returns whether the given implant is the "Zor's Custom Navigation Link" implant.
 */
context(EveData)
fun ImplantType.isZorsCustomNavigationLinkImplant() =
    itemId == ZORS_CUSTOM_NAVIGATION_LINK_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Navigation" implants.
 */
private const val NAVIGATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3096


/**
 * Returns whether the given implant is a "Navigation" implant.
 */
context(EveData)
fun ImplantType.isNavigationImplant() =
    variationParentTypeId == NAVIGATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The type id of the "Shaqil's Speed Enhancer" implant.
 */
private const val SHAQILS_SPEED_ENHANCER_IMPLANT_TYPE_ID = 24669


/**
 * Returns whether the given implant is the "Shaqil's Speed Enhancer" implant.
 */
context(EveData)
fun ImplantType.isShaqilsSpeedEnhancerImplant() =
    itemId == SHAQILS_SPEED_ENHANCER_IMPLANT_TYPE_ID


/**
 * The variation parent type id of "Warp Drive Operation" implants.
 */
private const val WARP_DRIVE_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3109


/**
 * Returns whether the given implant is a "Warp Drive Operation" implant.
 */
context(EveData)
fun ImplantType.isWarpDriveOperationImplant() =
    variationParentTypeId == WARP_DRIVE_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Warp Drive Speed" implants.
 */
private const val WARP_DRIVE_SPEED_IMPLANT_VARIATION_PARENT_TYPE_ID = 3117


/**
 * Returns whether the given implant is a "Warp Drive Speed" implant.
 */
context(EveData)
fun ImplantType.isWarpDriveSpeedImplant() =
    variationParentTypeId == WARP_DRIVE_SPEED_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Evasive Maneuvering" implants.
 */
private const val EVASIVE_MANEUVERING_IMPLANT_VARIATION_PARENT_TYPE_ID = 3093


/**
 * Returns whether the given implant is an "Evasive Maneuvering" implant.
 */
context(EveData)
fun ImplantType.isEvasiveManeuveringImplant() =
    variationParentTypeId == EVASIVE_MANEUVERING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Fuel Conservation" implants.
 */
private const val FUEL_CONSERVATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3101


/**
 * Returns whether the given implant is a "Fuel Conservation" implant.
 */
context(EveData)
fun ImplantType.isFuelConservationImplant() =
    variationParentTypeId == FUEL_CONSERVATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "High Speed Maneuvering" implants.
 */
private const val HIGH_SPEED_MANEUVERING_IMPLANT_VARIATION_PARENT_TYPE_ID = 3112


/**
 * Returns whether the given implant is a "High Speed Maneuvering" implant.
 */
context(EveData)
fun ImplantType.isHighSpeedManeuveringImplant() =
    variationParentTypeId == HIGH_SPEED_MANEUVERING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Astrometric Pinpointing" implants.
 */
private const val ASTROMETRIC_PINPOINTING_IMPLANT_VARIATION_PARENT_TYPE_ID = 27186


/**
 * Returns whether the given implant is an "Astrometric Pinpointing" implant.
 */
context(EveData)
fun ImplantType.isAstrometricPinpointingImplant() =
    variationParentTypeId == ASTROMETRIC_PINPOINTING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Astrometric Acquisition" implants.
 */
private const val ASTROMETRIC_ACQUISITION_IMPLANT_VARIATION_PARENT_TYPE_ID = 27187


/**
 * Returns whether the given implant is an "Astrometric Acquisition" implant.
 */
context(EveData)
fun ImplantType.isAstrometricAcquisitionImplant() =
    variationParentTypeId == ASTROMETRIC_ACQUISITION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Astrometric Rangefinding" implants.
 */
private const val ASTROMETRIC_RANGEFINDING_IMPLANT_VARIATION_PARENT_TYPE_ID = 27188


/**
 * Returns whether the given implant is an "Astrometric Rangefinding" implant.
 */
context(EveData)
fun ImplantType.isAstrometricRangefindingImplant() =
    variationParentTypeId == ASTROMETRIC_RANGEFINDING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Net Intrusion" implants.
 */
private const val NET_INTRUSION_IMPLANT_VARIATION_PARENT_TYPE_ID = 47028


/**
 * Returns whether the given implant is a "Net Intrusion" implant.
 */
context(EveData)
fun ImplantType.isNetIntrusionImplant() =
    variationParentTypeId == NET_INTRUSION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Archaeology" implants.
 */
private const val ARCHAEOLOGY_IMPLANT_VARIATION_PARENT_TYPE_ID = 27196


/**
 * Returns whether the given implant is an "Archaeology" implant.
 */
context(EveData)
fun ImplantType.isArchaeologyImplant() =
    variationParentTypeId == ARCHAEOLOGY_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Hacking" implants.
 */
private const val HACKING_IMPLANT_VARIATION_PARENT_TYPE_ID = 27197


/**
 * Returns whether the given implant is a "Hacking" implant.
 */
context(EveData)
fun ImplantType.isHackingImplant() =
    variationParentTypeId == HACKING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Salvaging" implants.
 */
private const val SALVAGING_IMPLANT_VARIATION_PARENT_TYPE_ID = 27198


/**
 * Returns whether the given implant is a "Salvaging" implant.
 */
context(EveData)
fun ImplantType.isSalvagingImplant() =
    variationParentTypeId == SALVAGING_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Environmental Analysis" implants.
 */
private const val ENVIRONMENTAL_ANALYSIS_IMPLANT_VARIATION_PARENT_TYPE_ID = 27260


/**
 * Returns whether the given implant is an "Environmental Analysis" implant.
 */
context(EveData)
fun ImplantType.isEnvironmentalAnalysisImplant() =
    variationParentTypeId == ENVIRONMENTAL_ANALYSIS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Shield Upgrades" implants.
 */
private const val SHIELD_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID = 3077


/**
 * Returns whether the given implant is a "Shield Upgrades" implant.
 */
context(EveData)
fun ImplantType.isShieldUpgradesImplant() =
    variationParentTypeId == SHIELD_UPGRADES_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Sansha Modified Gnome" implants.
 */
private const val SANSHA_MODIFIED_GNOME_IMPLANT_VARIATION_PARENT_TYPE_ID = 32255


/**
 * Returns whether the given implant is a "Sansha Modified Gnome" implant.
 */
context(EveData)
fun ImplantType.isSanshaModifiedGnomeImplant() =
    variationParentTypeId == SANSHA_MODIFIED_GNOME_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Shield Management" implants.
 */
private const val SHIELD_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID = 3080


/**
 * Returns whether the given implant is a "Shield Management" implant.
 */
context(EveData)
fun ImplantType.isShieldManagementImplant() =
    variationParentTypeId == SHIELD_MANAGEMENT_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Shield Emission Systems" implants.
 */
private const val SHIELD_EMISSION_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3085


/**
 * Returns whether the given implant is a "Shield Emission Systems" implant.
 */
context(EveData)
fun ImplantType.isShieldEmissionSystemsImplant() =
    variationParentTypeId == SHIELD_EMISSION_SYSTEMS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Shield Operation" implants.
 */
private const val SHIELD_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID = 3088


/**
 * Returns whether the given implant is a "Shield Operation" implant.
 */
context(EveData)
fun ImplantType.isShieldOperationImplant() =
    variationParentTypeId == SHIELD_OPERATION_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Signature Analysis" implants.
 */
private const val SIGNATURE_ANALYSIS_IMPLANT_VARIATION_PARENT_TYPE_ID = 3268


/**
 * Returns whether the given implant is a "Signature Analysis" implant.
 */
context(EveData)
fun ImplantType.isSignatureAnalysisImplant() =
    variationParentTypeId == SIGNATURE_ANALYSIS_IMPLANT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Long Range Targeting" implants.
 */
private const val LONG_RANGE_TARGETING_IMPLANT_VARIATION_PARENT_TYPE_ID = 3274


/**
 * Returns whether the given implant is a "Long Range Targeting" implant.
 */
context(EveData)
fun ImplantType.isLongRangeTargetingImplant() =
    variationParentTypeId == LONG_RANGE_TARGETING_IMPLANT_VARIATION_PARENT_TYPE_ID
