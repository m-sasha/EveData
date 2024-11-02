/**
 * Methods for identifying charges.
 */

package eve.data.typeid

import eve.data.ChargeType
import eve.data.EveData


/**
 * The id of the Active Shielding Charge.
 */
const val ACTIVE_SHIELDING_CHARGE_ID = 42694


/**
 * Returns whether the given charge is an Active Shielding Charge.
 */
context(EveData)
fun ChargeType.isActiveShieldingCharge() = itemId == ACTIVE_SHIELDING_CHARGE_ID


/**
 * The id of the Shield Harmonizing Charge.
 */
const val SHIELD_HARMONIZING_CHARGE_ID = 42695


/**
 * Returns whether the given charge is a Shield Harmonizing Charge.
 */
context(EveData)
fun ChargeType.isShieldHarmonizingCharge() = itemId == SHIELD_HARMONIZING_CHARGE_ID


/**
 * The id of the Shield Extension Charge.
 */
const val SHIELD_EXTENSION_CHARGE_ID = 42696


/**
 * Returns whether the given charge is a Shield Extension Charge.
 */
context(EveData)
fun ChargeType.isShieldExtensionCharge() = itemId == SHIELD_EXTENSION_CHARGE_ID


/**
 * The group id for Shield Command Burst Charges.
 */
private const val SHIELD_COMMAND_BURST_CHARGES_GROUP_ID = 1769


/**
 * Returns whether the given charge is a Shield Command Burst Charge.
 */
context(EveData)
fun ChargeType.isShieldCommandBurstCharge() = groupId == SHIELD_COMMAND_BURST_CHARGES_GROUP_ID


/**
 * The id of the Armor Energizing Charge.
 */
const val ARMOR_ENERGIZING_CHARGE_ID = 42832


/**
 * Returns whether the given charge is an Armor Energizing Charge.
 */
context(EveData)
fun ChargeType.isArmorEnergizingCharge() = itemId == ARMOR_ENERGIZING_CHARGE_ID


/**
 * The id of the Rapid Repair Charge.
 */
const val RAPID_REPAIR_CHARGE_ID = 42833


/**
 * Returns whether the given charge is a Rapid Repair Charge.
 */
context(EveData)
fun ChargeType.isRapidRepairCharge() = itemId == RAPID_REPAIR_CHARGE_ID


/**
 * The id of the Armor Reinforcement Charge.
 */
const val ARMOR_REINFORCEMENT_CHARGE_ID = 42834


/**
 * Returns whether the given charge is an Armor Reinforcement Charge.
 */
context(EveData)
fun ChargeType.isArmorReinforcementCharge() = itemId == ARMOR_REINFORCEMENT_CHARGE_ID


/**
 * The group id for Armor Command Burst Charge.
 */
private const val ARMOR_COMMAND_BURST_CHARGES_GROUP_ID = 1774


/**
 * Returns whether the given charge is an Armor Command Burst Charge.
 */
context(EveData)
fun ChargeType.isArmorCommandBurstCharge() = groupId == ARMOR_COMMAND_BURST_CHARGES_GROUP_ID


/**
 * The id of the Sensor Optimization Charge.
 */
const val SENSOR_OPTIMIZATION_CHARGE_ID = 42835


/**
 * Returns whether the given charge is a Sensor Optimization Charge.
 */
context(EveData)
fun ChargeType.isSensorOptimizationCharge() = itemId == SENSOR_OPTIMIZATION_CHARGE_ID


/**
 * The id of the Electronic Superiority Charge.
 */
const val ELECTRONIC_SUPERIORITY_CHARGE_ID = 42836


/**
 * Returns whether the given charge is an Electronic Superiority Charge.
 */
context(EveData)
fun ChargeType.isElectronicSuperiorityCharge() = itemId == ELECTRONIC_SUPERIORITY_CHARGE_ID


/**
 * The id of the Electronic Hardening Charge.
 */
const val ELECTRONIC_HARDENING_CHARGE_ID = 42837


/**
 * Returns whether the given charge is an Electronic Hardening Charge.
 */
context(EveData)
fun ChargeType.isElectronicHardeningCharge() = itemId == ELECTRONIC_HARDENING_CHARGE_ID


/**
 * The group id for Information Command Burst Charges.
 */
private const val INFORMATION_COMMAND_BURST_CHARGES_GROUP_ID = 1773



/**
 * Returns whether the given charge is an Information Command Burst Charge.
 */
context(EveData)
fun ChargeType.isInformationCommandBurstCharge() =
    groupId == INFORMATION_COMMAND_BURST_CHARGES_GROUP_ID


/**
 * The id of the Evasive Maneuvers Charge.
 */
const val EVASIVE_MANEUVERS_CHARGE_ID = 42838


/**
 * Returns whether the given charge is an Evasive Maneuvers Charge.
 */
context(EveData)
fun ChargeType.isEvasiveManeuversCharge() = itemId == EVASIVE_MANEUVERS_CHARGE_ID


/**
 * The id of the Interdiction Maneuvers Charge.
 */
const val INTERDICTION_MANEUVERS_CHARGE_ID = 42839


/**
 * Returns whether the given charge is an Interdiction Maneuvers Charge.
 */
context(EveData)
fun ChargeType.isInterdictionManeuversCharge() = itemId == INTERDICTION_MANEUVERS_CHARGE_ID


/**
 * The id of the Rapid Deployment Charge.
 */
const val RAPID_DEPLOYMENT_CHARGE_ID = 42840


/**
 * Returns whether the given charge is a Rapid Deployment Charge.
 */
context(EveData)
fun ChargeType.isRapidDeploymentCharge() = itemId == RAPID_DEPLOYMENT_CHARGE_ID


/**
 * The group id for Skirmish Command Burst Charges.
 */
private const val SKIRMISH_COMMAND_BURST_CHARGES_GROUP_ID = 1772


/**
 * Returns whether the given charge is a Skirmish Command Burst Charge.
 */
context(EveData)
fun ChargeType.isSkirmishCommandBurstCharge() = groupId == SKIRMISH_COMMAND_BURST_CHARGES_GROUP_ID


/**
 * The group id for Mining Command Burst Charges.
 */
private const val MINING_COMMAND_BURST_CHARGES_GROUP_ID = 1771


/**
 * Returns whether the given charge is a Mining Command Burst Charge.
 */
context(EveData)
fun ChargeType.isMiningCommandBurstCharge() = groupId == MINING_COMMAND_BURST_CHARGES_GROUP_ID

/**
 * Returns whether the given charge is a Command Burst Charge.
 */
context(EveData)
fun ChargeType.isCommandBurstCharge() =
    isShieldCommandBurstCharge() ||
            isArmorCommandBurstCharge() ||
            isInformationCommandBurstCharge() ||
            isSkirmishCommandBurstCharge() ||
            isMiningCommandBurstCharge()


/**
 * Returns whether the given charge is an auto-targeting missile.
 */
context(EveData)
fun ChargeType.isAutoTargetingMissile() = group in groups.allAutoTargetingMissiles


/**
 * Returns whether the given charge is a javelin missile.
 */
context(EveData)
fun ChargeType.isJavelinMissile() =
    (group in groups.allAdvancedMissiles) && name.contains("Javelin")


/**
 * Returns whether the given charge is a rage missile.
 */
context(EveData)
fun ChargeType.isRageMissile() =
    (group in groups.allAdvancedMissiles) && name.contains("Rage")


/**
 * Returns whether the given charge is a precision missile.
 */
context(EveData)
fun ChargeType.isPrecisionMissile() =
    (group in groups.allAdvancedMissiles) && name.contains("Precision")


/**
 * Returns whether the given charge is a fury missile.
 */
context(EveData)
fun ChargeType.isFuryMissile() =
    (group in groups.allAdvancedMissiles) && name.contains("Fury")


/**
 * Returns whether the given charge is a missile.
 */
context(EveData)
fun ChargeType.isMissile() =
    (group in groups.allMissiles)
            || (group in groups.allAdvancedMissiles)
            || isAutoTargetingMissile()
            || isDefenderMissile()


/**
 * The id of Nanite Repair Paste.
 */
private const val NANITE_REPAIR_PASTE_ID = 28668


/**
 * Returns whether the given charge is Nanite Repair Paste.
 */
context(EveData)
fun ChargeType.isNaniteRepairPaste() = itemId == NANITE_REPAIR_PASTE_ID


/**
 * Returns the nanite repoair paste type.
 */
context(EveData)
fun EveData.naniteRepairPaste() = chargeType(NANITE_REPAIR_PASTE_ID)


/**
 * The group id of cap booster charges.
 */
private const val CAP_BOOSTER_CHARGE_GROUP_ID = 87


/**
 * Returns whether the given charge is a cap booster.
 */
context(EveData)
fun ChargeType.isCapBooster() = groupId == CAP_BOOSTER_CHARGE_GROUP_ID


/**
 * The group id of scanner probes.
 */
private const val SCANNER_PROBE_GROUP_ID = 479


/**
 * Returns whether the given charge is a scanner probe.
 */
context(EveData)
fun ChargeType.isScannerProbe() = groupId == SCANNER_PROBE_GROUP_ID


/**
 * The group id of damage bombs.
 */
private const val DAMAGE_BOMB_GROUP_ID = 90


/**
 * Returns whether the given charge is a damage bomb.
 */
context(EveData)
fun ChargeType.isDamageBomb() = groupId == DAMAGE_BOMB_GROUP_ID


/**
 * The group id of energy bombs.
 */
private const val ENERGY_BOMB_GROUP_ID = 864


/**
 * Returns whether the given charge is an energy bomb.
 */
context(EveData)
fun ChargeType.isEnergyBomb() = groupId == ENERGY_BOMB_GROUP_ID


/**
 * The group id of ECM bombs.
 */
private const val ECM_BOMB_GROUP_ID = 863


/**
 * Returns whether the given charge is an ECM bomb.
 */
context(EveData)
fun ChargeType.isEcmBomb() = groupId == ECM_BOMB_GROUP_ID


/**
 * Returns whether the given charge is a bomb.
 */
context(EveData)
fun ChargeType.isBomb() = isDamageBomb() || isEnergyBomb() || isEcmBomb()


/**
 * The group id of defender missiles.
 */
private const val DEFENDER_MISSILE_GROUP_ID = 88


/**
 * Returns whether the given charge is a Defender Missile.
 */
context(EveData)
fun ChargeType.isDefenderMissile() = groupId == DEFENDER_MISSILE_GROUP_ID


/**
 * The group id of warp disruption scripts.
 */
private const val WARP_DISRUPTION_SCRIPT_GROUP_ID = 908


/**
 * Returns whether the given charge is a Warp Disruption script.
 */
context(EveData)
fun ChargeType.isWarpDisruptionScript() = groupId == WARP_DISRUPTION_SCRIPT_GROUP_ID


/**
 * The group id of interdiction burst probes.
 */
private const val INTERDICTION_BURST_PROBE_GROUP_ID = 4088


/**
 * Returns whether the given charge is an Interdiction Burst Probe (currently only the "Stasis Webification Probe").
 */
context(EveData)
fun ChargeType.isInterdictionBurstProbe() = groupId == INTERDICTION_BURST_PROBE_GROUP_ID


/**
 * The group id of interdiction probes.
 */
private const val INTERDICTION_PROBE_GROUP_ID = 548


/**
 * Returns whether the given charge is an Interdiction Probe (the warp-disrupting ones).
 */
context(EveData)
fun ChargeType.isInterdictionProbe() = groupId == INTERDICTION_PROBE_GROUP_ID


/**
 * Returns whether the given charge is a charge loaded into an "Interdiction Sphere Launcher".
 */
context(EveData)
fun ChargeType.isInterdictionSphereLauncherProbe() =
    isInterdictionBurstProbe() || isInterdictionProbe()


/**
 * Returns whether the given charge is ammo (does damage).
 */
context(EveData)
fun ChargeType.isAmmo() = totalDamage.let { (it != null) && (it > 0) }


/**
 * Returns whether the given charge is projectile ammo.
 */
context(EveData)
fun ChargeType.isProjectileAmmo() = with(groups) {
    group.let {
        (it == projectileAmmo) || (it == advancedAutocannonAmmo) || (it == advancedArtilleryAmmo)
    }
}


/**
 * Returns whether the given charge is a frequency crystal ammo.
 */
context(EveData)
fun ChargeType.isFrequencyCrystalAmmo() = with(groups) {
    group.let {
        (it == frequencyCrystal) || (it == advancedBeamLaserCrystal) || (it == advancedPulseLaserCrystal)
    }
}


/**
 * Returns whether the given charge is a hybrid charge ammo.
 */
context(EveData)
fun ChargeType.isHybridChargeAmmo() = with(groups) {
    group.let {
        (it == hybridCharge) || (it == advancedBlasterCharge) || (it == advancedRailgunCharge)
    }
}


/**
 * Returns whether the given charge is a script.
 */
context(EveData)
fun ChargeType.isScript() = with(marketGroups) {
    isInGroup(scripts)
}
