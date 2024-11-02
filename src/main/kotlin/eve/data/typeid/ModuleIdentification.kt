/**
 * Methods for identifying (non-rig) modules.
 */

package eve.data.typeid

import eve.data.EveData
import eve.data.ModuleType
import eve.data.SensorType


/**
 * Returns whether the module is a capacitor booster.
 */
context(EveData)
fun ModuleType.isCapacitorBooster() = group == groups.capacitorBooster


/**
 * Returns whether the module is an ancillary armor repairer.
 */
context(EveData)
fun ModuleType.isAncillaryArmorRepairer() = group == groups.ancillaryArmorRepairer


/**
 * Returns whether the module is an ancillary remote armor repairer.
 */
context(EveData)
fun ModuleType.isAncillaryRemoteArmorRepairer() = group == groups.ancillaryRemoteArmorRepairer


/**
 * Returns whether the module is a mutadaptive remote armor repairer.
 */
context(EveData)
fun ModuleType.isMutadaptiveRemoteArmorRepairer() = group == groups.mutadaptiveRemoteArmorRepairer


/**
 * Returns whether the module is an ancillary shield booster.
 */
context(EveData)
fun ModuleType.isAncillaryShieldBooster() = group == groups.ancillaryShieldBooster


/**
 * Returns whether the module is an ancillary remote shield booster.
 */
context(EveData)
fun ModuleType.isAncillaryRemoteShieldBooster() = group == groups.ancillaryRemoteShieldBooster


/**
 * Returns whether the module is a bomb launcher.
 */
context(EveData)
fun ModuleType.isBombLauncher() = group == groups.bombLauncher


/**
 * Returns whether the module is an interdiction sphere launcher.
 */
context(EveData)
fun ModuleType.isInterdictionSphereLauncher() = group == groups.interdictionSphereLauncher


/**
 * Returns whether the module is a laser turret.
 */
context(EveData)
fun ModuleType.isEnergyWeapon() = group == groups.energyWeapon


/**
 * Returns whether the module is a hybrid turret.
 */
context(EveData)
fun ModuleType.isHybridWeapon() = group == groups.hybridWeapon


/**
 * Returns whether the module is a projectile turret.
 */
context(EveData)
fun ModuleType.isProjectileWeapon() = group == groups.projectileWeapon


/**
 * Returns whether the module is a turret.
 * Note that Vorton projectors are not considered to be turrets.
 */
context(EveData)
fun ModuleType.isTurret() = group in groups.allTurrets


/**
 * Returns whether the module is a Vorton projector.
 */
context(EveData)
fun ModuleType.isVortonProjector() = group == groups.vortonProjector


/**
 * Returns whether the module is a turret or a Vorton projector.
 */
context(EveData)
fun ModuleType.isTurretOrVortonProjector() = isTurret() || isVortonProjector()


/**
 * Returns whether the module is a Precursor (Triglavian) weapon.
 */
context(EveData)
fun ModuleType.isEntropicDisintegrator() = group == groups.precursorWeapon


/**
 * Returns whether the module is a scan probe launcher.
 */
context(EveData)
fun ModuleType.isScanProbeLauncher() = group == groups.scanProbeLauncher


/**
 * Returns whether the module is a missile launcher (except defenders).
 */
context(EveData)
fun ModuleType.isMissileLauncher() = group in groups.allMissileLaunchers


/**
 * Returns whether the module is a defender missile launcher.
 */
context(EveData)
fun ModuleType.isDefenderMissileLauncher() = group == groups.defenderLaunchers


/**
 * Returns whether the module is an overdrive.
 */
context(EveData)
fun ModuleType.isOverdrive() = group == groups.overdriveInjectorSystem


/**
 * Returns whether the module is a drone damage amplifier.
 */
context(EveData)
fun ModuleType.isDroneDamageAmplifier() = group == groups.droneDamageAmplifier


/**
 * Returns whether the module is a drone link augmentor.
 */
context(EveData)
fun ModuleType.isDroneLinkAugmentor() = group == groups.droneLinkAugmentor


/**
 * Returns whether the module is a drone navigation computer.
 */
context(EveData)
fun ModuleType.isDroneNavigationComputer() = group == groups.droneNavigationComputer


/**
 * Returns whether the module is an omnidirectional tracking link.
 */
context(EveData)
fun ModuleType.isOmnidirectionalTrackingLink() = group == groups.omnidirectionalTrackingLink


/**
 * Returns whether the module is an omnidirectional tracking enhancer.
 */
context(EveData)
fun ModuleType.isOmnidirectionalTrackingEnhancer() = group == groups.omnidirectionalTrackingEnhancer


/**
 * Returns whether the module is a burst jammer.
 */
context(EveData)
fun ModuleType.isBurstJammer() = group == groups.burstJammer


/**
 * Returns whether the module is an ECM module.
 */
context(EveData)
fun ModuleType.isEcm() = group == groups.ecmModule


/**
 * Returns the sensor type the given ECM module jams best; `null` if it's a multispectral ECM.
 */
context(EveData)
fun ModuleType.ecmSensorType(): SensorType? {
    val strengthBySensorType = SensorType.entries.associateWith { sensorType ->
        attributeValueOrNull(attributes.ecmStrength[sensorType]) ?: 0.0
    }

    val (maxSensorType, maxStrength) = strengthBySensorType.maxBy { (_, strength) -> strength }
    return if (strengthBySensorType.values.all { it == maxStrength })
        null  // Multispectral
    else
        maxSensorType
}


/**
 * Returns whether the module is a signal distortion amplifier.
 */
context(EveData)
fun ModuleType.isSignalDistortionAmplifier() = group == groups.signalDistortionAmplifier


/**
 * Returns whether the module is a remote sensor dampener.
 */
context(EveData)
fun ModuleType.isRemoteSensorDampener() = group == groups.remoteSensorDampener


/**
 * Returns whether the module is a remote sensor booster.
 */
context(EveData)
fun ModuleType.isRemoteSensorBooster() = group == groups.remoteSensorBooster


/**
 * Returns whether the module is a signature radius suppressor.
 */
context(EveData)
fun ModuleType.isSignatureRadiusSuppressor() = group == groups.signatureRadiusSuppressor


/**
 * Returns whether the module is a stasis grappler.
 */
context(EveData)
fun ModuleType.isStasisGrappler() = group == groups.stasisGrappler


/**
 * Returns whether the module is a stasis webifier.
 */
context(EveData)
fun ModuleType.isStasisWebifier() = group == groups.stasisWebifier


/**
 * Returns whether the module is a target painter.
 */
context(EveData)
fun ModuleType.isTargetPainter() = group == groups.targetPainter


/**
 * Returns whether the module is warp scrambler/disruptor.
 */
context(EveData)
fun ModuleType.isWarpScrambler() = group == groups.warpScrambler


/**
 * Returns whether the module is a warp disruption field generator.
 */
context(EveData)
fun ModuleType.isWarpDisruptionFieldGenerator() = group == groups.warpDisruptFieldGenerator


/**
 * Returns whether the module is a weapon disruptor (guidance or tracking).
 */
context(EveData)
fun ModuleType.isWeaponDisruptor() = group == groups.weaponDisruptor


/**
 * Returns whether the module is a tracking disruptor.
 */
context(EveData)
fun ModuleType.isTrackingDisruptor() = isWeaponDisruptor() && hasEffect(effects.trackingDisruptor)


/**
 * Returns whether the module is a guidance disruptor.
 */
context(EveData)
fun ModuleType.isGuidanceDisruptor() = isWeaponDisruptor() && hasEffect(effects.guidanceDisruptor)


/**
 * Returns whether the module is an auto targeting system.
 */
context(EveData)
fun ModuleType.isAutoTargetingSystem() = group == groups.autoTargetingSystem


/**
 * Returns whether the module is a passive targeting system.
 */
context(EveData)
fun ModuleType.isPassiveTargetingSystem() = group == groups.passiveTargetingSystem


/**
 * Returns whether the module is a coprocessor.
 */
context(EveData)
fun ModuleType.isCoProcessor() = group == groups.coProcessor


/**
 * Returns whether the module is a cloaking device.
 */
context(EveData)
fun ModuleType.isCloakingDevice() = group == groups.cloakingDevice


/**
 * Returns whether the module is a sensor booster.
 */
context(EveData)
fun ModuleType.isSensorBooster() = group == groups.sensorBooster


/**
 * Returns whether the module is a signal amplifier.
 */
context(EveData)
fun ModuleType.isSignalAmplifier() = group == groups.signalAmplifier


/**
 * Returns whether the module is a tractor beam.
 */
context(EveData)
fun ModuleType.isTractorBeam() = group == groups.tractorBeam


/**
 * Returns whether the module is a micro auxiliary power core.
 */
context(EveData)
fun ModuleType.isMicroAuxiliarPowerCore() = group == groups.auxiliaryPowerCore


/**
 * Returns whether the module is a cap battery.
 */
context(EveData)
fun ModuleType.isCapBattery() = group == groups.capacitorBattery


/**
 * Returns whether the module is a capacitor flux coil.
 */
context(EveData)
fun ModuleType.isCapacitorFluxCoil() = group == groups.capacitorFluxCoil


/**
 * Returns whether the module is a capacitor power relay.
 */
context(EveData)
fun ModuleType.isCapacitorPowerRelay() = group == groups.capacitorPowerRelay


/**
 * Returns whether the module is a cap recharger.
 */
context(EveData)
fun ModuleType.isCapRecharger() = group == groups.capRecharger


/**
 * Returns whether the module is an energy neutralizer.
 */
context(EveData)
fun ModuleType.isEnergyNeutralizer() = group == groups.energyNeutralizer


/**
 * Returns whether the module is an energy nosferatu.
 */
context(EveData)
fun ModuleType.isEnergyNosferatu() = group == groups.energyNosferatu


/**
 * Returns whether the module is a power diagnostic system.
 */
context(EveData)
fun ModuleType.isPowerDiagnosticSystem() = group == groups.powerDiagnosticSystem


/**
 * Returns whether the module is a reactor control system.
 */
context(EveData)
fun ModuleType.isReactorControlUnit() = group == groups.reactorControlUnit


/**
 * Returns whether the module is a remote capacitor transmitter.
 */
context(EveData)
fun ModuleType.isRemoteCapacitorTransmitter() = group == groups.remoteCapacitorTransmitter


/**
 * Returns whether the module is a cynosural field generator.
 */
context(EveData)
fun ModuleType.isCynosuralFieldGenerator() = group == groups.cynosuralFieldGenerator


/**
 * Returns whether the module is a (non-covert) jump portal generator.
 */
context(EveData)
fun ModuleType.isJumpPortalGenerator() = group == groups.jumpPortalGenerator


/**
 * Returns whether the module is a covert jump portal generator.
 */
context(EveData)
fun ModuleType.isCovertJumpPortalGenerator() = group == groups.covertJumpPortalGenerator


/**
 * Returns whether the module is a gas cloud harvester.
 */
context(EveData)
fun ModuleType.isGasCloudHarvester() = group == groups.gasCloudHarvester


/**
 * Returns whether the module is a gas cloud scoop.
 */
context(EveData)
fun ModuleType.isGasCloudScoop() = group == groups.gasCloudScoop


/**
 * Returns whether the module is a strip miner.
 */
context(EveData)
fun ModuleType.isStripMiner() = group == groups.stripMiner


/**
 * Returns whether the module is a mining laser.
 */
context(EveData)
fun ModuleType.isMiningLaser() = group == groups.miningLaser


/**
 * Returns whether the module is a frequency modulated mining laser.
 */
context(EveData)
fun ModuleType.isFrequencyMiningLaser() = group == groups.frequencyMiningLaser


/**
 * Returns whether the module is a mining module.
 */
context(EveData)
fun ModuleType.isMiningModule() =
    isGasCloudHarvester() ||
            isGasCloudScoop() ||
            isStripMiner() ||
            isMiningLaser() ||
            isFrequencyMiningLaser()


/**
 * Returns whether the module is a mining upgrade module.
 */
context(EveData)
fun ModuleType.isMiningUpgrade() = group == groups.miningUpgrade


/**
 * Returns whether the module is a salvager.
 */
context(EveData)
fun ModuleType.isSalvager() = group == groups.salvager



/**
 * Returns whether the module is an armor hardener.
 */
context(EveData)
fun ModuleType.isArmorHardener() = group == groups.armorHardener


/**
 * Returns whether the module is a reactive armor hardener.
 */
context(EveData)
fun ModuleType.isReactiveArmorHardener() = group == groups.armorResistanceShiftHardener


/**
 * Returns whether the module is an armor plate.
 */
context(EveData)
fun ModuleType.isArmorPlate() = group == groups.armorPlate


/**
 * Returns whether the module is a local armor repairer.
 */
context(EveData)
fun ModuleType.isArmorRepairer(includingAncillary: Boolean) =
    (group == groups.armorRepairer) || (includingAncillary && isAncillaryArmorRepairer())


/**
 * Returns whether the module is a local armor repairer, including ancillary.
 */
context(EveData)
fun ModuleType.isArmorRepairerInclAncillary() = isArmorRepairer(includingAncillary = true)


/**
 * Returns whether the module has a bonus to armor HP.
 */
context(EveData)
private fun ModuleType.hasBonusToArmorHp() =
    attributeValueOrNull(attributes.armorHPMultiplier).let { armorHpMultiplier ->
        (armorHpMultiplier != null) && (armorHpMultiplier != 1.0)
    }


/**
 * Returns whether the module is an armor resistance coating.
 */
context(EveData)
fun ModuleType.isArmorResistanceCoating() =
    (group == groups.armorCoating) && !hasBonusToArmorHp()


/**
 * Returns whether the module is a layered armor coating.
 */
context(EveData)
fun ModuleType.isLayeredArmorCoating() =
    (group == groups.armorCoating) && hasBonusToArmorHp()


/**
 * Returns whether the module is a damage control (including assault damage controls).
 */
context(EveData)
fun ModuleType.isDamageControl() = group == groups.damageControl


/**
 * Returns whether the module is an assault damage control.
 */
context(EveData)
fun ModuleType.isAssaultDamageControl() =
    (group == groups.damageControl) && hasEffect(effects.moduleBonusAssaultDamageControl)


/**
 * Returns whether the module is a Capital Emergency Hull Energizer.
 */
context(EveData)
fun ModuleType.isCapitalEmergencyHullEnergizer() =
    (group == groups.damageControl) && hasEffect(effects.emergencyHullEnergizer)


/**
 * Returns whether the module is an energized armor resistance membrane.
 */
context(EveData)
fun ModuleType.isEnergizedArmorResistanceMembrane() =
    (group == groups.energizedArmorMembrane) && !hasBonusToArmorHp()


/**
 * Returns whether the module is a layered energized armor membrane.
 */
context(EveData)
fun ModuleType.isLayeredEnergizedArmorMembrane() =
    (group == groups.energizedArmorMembrane) && hasBonusToArmorHp()


/**
 * Returns whether the module is a structure repairer.
 */
context(EveData)
fun ModuleType.isStructureRepairer() = group == groups.hullRepairer


/**
 * Returns whether the module is an expanded cargohold.
 */
context(EveData)
fun ModuleType.isExpandedCargohold() = group == groups.expandedCargohold


/**
 * Returns whether the module is a nanofiber internal structure.
 */
context(EveData)
fun ModuleType.isNanofiberInternalStructure() = group == groups.nanofiberInternalStructure


/**
 * Returns whether the module is a reinforced bulkhead.
 */
context(EveData)
fun ModuleType.isReinforcedBulkhead() = group == groups.reinforcedBulkhead


/**
 * Returns whether the module is a remote armor repairer.
 */
context(EveData)
fun ModuleType.isRemoteArmorRepairer(includeAncillary: Boolean, includeMutadaptive: Boolean) =
    (group == groups.remoteArmorRepairer) ||
            (includeAncillary && isAncillaryRemoteArmorRepairer()) ||
            (includeMutadaptive && isMutadaptiveRemoteArmorRepairer())


/**
 * Returns whether the module is a remote armor repairer, including ancillary.
 */
context(EveData)
fun ModuleType.isRemoteArmorRepairerInclAncillaryAndMutadaptive() =
    isRemoteArmorRepairer(includeAncillary = true, includeMutadaptive = true)


/**
 * Returns whether the module is a remote structure repairer.
 */
context(EveData)
fun ModuleType.isRemoteStructureRepairer() = group == groups.remoteHullRepairer


/**
 * Returns whether the module is a propulsion module (Afterburner of Microwarpdrive).
 */
context(EveData)
fun ModuleType.isPropulsionModule() = group == groups.propulsionModule


/**
 * Returns whether the module is a micro jump drive.
 */
context(EveData)
fun ModuleType.isMicroJumpDrive() = group == groups.microJumpDrive


/**
 * Returns whether the module is a micro jump field generator.
 */
context(EveData)
fun ModuleType.isMicroJumpFieldGenerator() = group == groups.microJumpFieldGenerator


/**
 * Returns whether the module is an inertial stabilizer.
 */
context(EveData)
fun ModuleType.isInertialStabilizer() = group == groups.inertialStabilizer


/**
 * Returns whether the module is an interdiction nullifier.
 */
context(EveData)
fun ModuleType.isInterdictionNullifier() = group == groups.interdictionNullifier


/**
 * Returns whether the module is a jump drive economizer.
 */
context(EveData)
fun ModuleType.isJumpDriveEconomizer() = group == groups.jumpDriveEconomizer


/**
 * Returns whether the module is a warp accelerator.
 */
context(EveData)
fun ModuleType.isWarpAccelerator() = group == groups.warpAccelerator


/**
 * Returns whether the module is a warp core stabilizer.
 */
context(EveData)
fun ModuleType.isWarpCoreStabilizer() = group == groups.warpCoreStabilizer


/**
 * Returns whether the module is a data miner.
 */
context(EveData)
fun ModuleType.isDataMiner() = group == groups.dataMiner


/**
 * Returns whether the module is a cargo scanner.
 */
context(EveData)
fun ModuleType.isCargoScanner() = group == groups.cargoScanner


/**
 * Returns whether the module is an entosis link.
 */
context(EveData)
fun ModuleType.isEntosisLink() = group == groups.entosisLink


/**
 * Returns whether the module is a scanning time upgrade module (Scan Acquisition Array).
 */
context(EveData)
fun ModuleType.isScanningTimeUpgrade() = group == groups.scanningUpgradeTime


/**
 * Returns whether the module is a scanning deviation upgrade module (Scan Pinpointing Array).
 */
context(EveData)
fun ModuleType.isScanningDeviationUpgrade() =
    (group == groups.scanningUpgrade) && hasAttribute(attributes.maxScanDeviationModifierModule)


/**
 * Returns whether the module is a scanning strength upgrade module (Scan Rangefinding Array).
 */
context(EveData)
fun ModuleType.isScanningStrengthUpgrade() =
    (group == groups.scanningUpgrade) && hasAttribute(attributes.scanStrengthBonusModule)


/**
 * Returns whether the module is a ship scanner.
 */
context(EveData)
fun ModuleType.isShipScanner() = group == groups.shipScanner


/**
 * Returns whether the module is a survey probe launcher.
 */
context(EveData)
fun ModuleType.isSurveyProbeLauncher() = group == groups.surveyProbeLauncher


/**
 * Returns whether the module is a survey scanner.
 */
context(EveData)
fun ModuleType.isSurveyScanner() = group == groups.surveyScanner


/**
 * Returns whether the module is a remote shield booster.
 */
context(EveData)
fun ModuleType.isRemoteShieldBooster(includingAncillary: Boolean) =
    (group == groups.remoteShieldBooster) ||
            (includingAncillary && isAncillaryRemoteShieldBooster())


/**
 * Returns whether the module is a remote shield booster.
 */
context(EveData)
fun ModuleType.isRemoteShieldBoosterInclAncillary() =
    isRemoteShieldBooster( includingAncillary = true)


/**
 * Returns whether the module is a (local) shield booster.
 */
context(EveData)
fun ModuleType.isShieldBooster(includingAncillary: Boolean) =
    (group == groups.shieldBooster) || (includingAncillary && isAncillaryShieldBooster())


/**
 * Returns whether the module is a (local) shield booster, including ancillary.
 */
context(EveData)
fun ModuleType.isShieldBoosterInclAncillary() = isShieldBooster(includingAncillary = true)


/**
 * Returns whether the module is a shield boost amplifier.
 */
context(EveData)
fun ModuleType.isShieldBoostAmplifier() = group == groups.shieldBoostAmplifier


/**
 * Returns whether the module is a shield extender.
 */
context(EveData)
fun ModuleType.isShieldExtender() = group == groups.shieldExtender


/**
 * Returns whether the module is a shield flux coil.
 */
context(EveData)
fun ModuleType.isShieldFluxCoil() = group == groups.shieldFluxCoil


/**
 * Returns whether the module is a shield hardener.
 */
context(EveData)
fun ModuleType.isShieldHardener() = group == groups.shieldHardener


/**
 * Returns whether the module is a flex shield hardener.
 */
context(EveData)
fun ModuleType.isFlexShieldHardener() = group == groups.flexShieldHardener


/**
 * Returns whether the module is a shield power relay.
 */
context(EveData)
fun ModuleType.isShieldPowerRelay() = group == groups.shieldPowerRelay


/**
 * Returns whether the module is a shield recharger.
 */
context(EveData)
fun ModuleType.isShieldRecharger() = group == groups.shieldRecharger


/**
 * Returns whether the module is a shield resistance amplifier.
 */
context(EveData)
fun ModuleType.isShieldResistanceAmplifier() = group == groups.shieldResistanceAmplifier


/**
 * Returns whether the module is a smartbomb.
 */
context(EveData)
fun ModuleType.isSmartbomb() = group == groups.smartbomb


/**
 * Returns whether the module is a ballistic control system.
 */
context(EveData)
fun ModuleType.isBallisticControlSystem() = group == groups.ballisticControlSystem


/**
 * Returns whether the module is a gyrostabilizer.
 */
context(EveData)
fun ModuleType.isGyrostabilizer() = group == groups.gyrostabilizer


/**
 * Returns whether the module is a heat sink.
 */
context(EveData)
fun ModuleType.isHeatSink() = group == groups.heatSink


/**
 * Returns whether the module is a magnetic field stabilizer.
 */
context(EveData)
fun ModuleType.isMagneticFieldStabilizer() = group == groups.magneticFieldStabilizer


/**
 * Returns whether the module is a Vorton tuning system.
 */
context(EveData)
fun ModuleType.isVortonTuningSystem() = group == groups.vortonProjectorUpgrade


/**
 * Returns whether the module is an entropic radiation sink.
 */
context(EveData)
fun ModuleType.isEntropicRadiationSink() = group == groups.entropicRadiationSink


/**
 * Returns whether the module is a missile guidance computer.
 */
context(EveData)
fun ModuleType.isMissileGuidanceComputer() = group == groups.missileGuidanceComputer


/**
 * Returns whether the module is a missile guidance enhancer.
 */
context(EveData)
fun ModuleType.isMissileGuidanceEnhancer() = group == groups.missileGuidanceEnhancer


/**
 * Returns whether the module is a tracking computer.
 */
context(EveData)
fun ModuleType.isTrackingComputer() = group == groups.trackingComputer


/**
 * Returns whether the module is a tracking enhancer.
 */
context(EveData)
fun ModuleType.isTrackingEnhancer() = group == groups.trackingEnhancer


/**
 * Returns whether the module is a remote tracking computer.
 */
context(EveData)
fun ModuleType.isRemoteTrackingComputer() = group == groups.remoteTrackingComputer


/**
 * Returns whether the module is a bastion module.
 */
context(EveData)
fun ModuleType.isBastionModule() = variationParentTypeId == 33400


/**
 * Returns whether the module is a siege module.
 */
context(EveData)
fun ModuleType.isSiegeModule() = variationParentTypeId == 20280


/**
 * Returns whether the module is a triage module.
 */
context(EveData)
fun ModuleType.isTriageModule() = variationParentTypeId == 27951


/**
 * Returns whether the module is a command burst.
 */
context(EveData)
fun ModuleType.isCommandBurst() = group == groups.commandBurst


/**
 * The variation parent type id of Armor Command Burst modules.
 */
private const val ARMOR_COMMAND_BURST_VARIATION_PARENT_TYPE_ID = 42526


/**
 * Returns whether the given module is an Armor Command Burst.
 */
context(EveData)
fun ModuleType.isArmorCommandBurst() =
    variationParentTypeId == ARMOR_COMMAND_BURST_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of Information Command Burst modules.
 */
private const val INFORMATION_COMMAND_BURST_VARIATION_PARENT_TYPE_ID = 42527


/**
 * Returns whether the given module is an Information Command Burst.
 */
context(EveData)
fun ModuleType.isInformationCommandBurst() =
    variationParentTypeId == INFORMATION_COMMAND_BURST_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of Shield Command Burst modules.
 */
private const val SHIELD_COMMAND_BURST_VARIATION_PARENT_TYPE_ID = 42529


/**
 * Returns whether the given module is a Shield Command Burst.
 */
context(EveData)
fun ModuleType.isShieldCommandBurst() =
    variationParentTypeId == SHIELD_COMMAND_BURST_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of Skirmish Command Burst modules.
 */
private const val SKIRMISH_COMMAND_BURST_VARIATION_PARENT_TYPE_ID = 42530


/**
 * Returns whether the given module is a Skirmish Command Burst.
 */
context(EveData)
fun ModuleType.isSkirmishCommandBurst() =
    variationParentTypeId == SKIRMISH_COMMAND_BURST_VARIATION_PARENT_TYPE_ID
