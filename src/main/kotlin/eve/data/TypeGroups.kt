package eve.data


/**
 * An item type group.
 */
class TypeGroup(


    /**
     * The id of the group.
     */
    val id: Int,


    /**
     * The category this group belongs to.
     */
    val category: TypeCategory,


    /**
     * The name of this group.
     */
    val name: String


)


/**
 * Groups all item type groups and provides some of them via explicit properties.
 */
@Suppress("Unused")
class TypeGroups internal constructor(groups: Collection<TypeGroup>): Collection<TypeGroup> by groups {


    /**
     * Groups mapped by id.
     */
    private val byId: Map<Int, TypeGroup> = groups.associateBy{ it.id }


    /**
     * Groups mapped by name.
     */
    private val byName: Map<String, TypeGroup> = groups.associateBy { it.name }


    /**
     * Returns the [TypeGroup] with the given id, assuming it exists.
     */
    operator fun get(id: Int) = byId[id] ?: throw BadEveDataException("No group with id $id found")


    /**
     * Returns the [TypeGroup] with the given name, assuming it exists.
     */
    operator fun get(name: String) = byName[name] ?: throw BadEveDataException("No group '$name' found")


    val capacitorBooster = get("Capacitor Booster")
    val ancillaryArmorRepairer = get("Ancillary Armor Repairer")
    val ancillaryRemoteArmorRepairer = get("Ancillary Remote Armor Repairer")
    val ancillaryShieldBooster = get("Ancillary Shield Booster")
    val ancillaryRemoteShieldBooster = get("Ancillary Remote Shield Booster")

    val bombLauncher = get("Missile Launcher Bomb")
    val interdictionSphereLauncher = get("Interdiction Sphere Launcher")
    val scanProbeLauncher = get("Scan Probe Launcher")
    val breacherPodLauncher = get("Breacher Pod Launchers")

    val energyWeapon = get("Energy Weapon")
    val hybridWeapon = get("Hybrid Weapon")
    val projectileWeapon = get("Projectile Weapon")
    val precursorWeapon = get("Precursor Weapon")  // aka Entropic Disintegrator

    val allTurrets = setOf(
        energyWeapon,
        hybridWeapon,
        projectileWeapon,
        precursorWeapon,
    )

    val defenderLaunchers = get("Missile Launcher Defender")

    val allMissileLaunchers = setOf(
        get("Missile Launcher Rocket"),
        get("Missile Launcher Light"),
        get("Missile Launcher Rapid Light"),
        get("Missile Launcher Heavy Assault"),
        get("Missile Launcher Heavy"),
        get("Missile Launcher Rapid Heavy"),
        get("Missile Launcher Cruise"),
        get("Missile Launcher Torpedo"),
        get("Missile Launcher Rapid Torpedo"),
        get("Missile Launcher XL Torpedo"),
        get("Missile Launcher XL Cruise"),
    )

    val vortonProjector = get("Vorton Projector")

    val overdriveInjectorSystem = get("Overdrive Injector System")

    val droneDamageAmplifier = get("Drone Damage Modules")
    val droneLinkAugmentor = get("Drone Control Range Module")
    val droneNavigationComputer = get("Drone Navigation Computer")
    val omnidirectionalTrackingLink = get("Drone Tracking Modules")
    val omnidirectionalTrackingEnhancer = get("Drone Tracking Enhancer")

    val burstJammer = get("Burst Jammer")
    val ecmModule = get("ECM")
    val signalDistortionAmplifier = get("ECM Stabilizer")
    val remoteSensorDampener = get("Sensor Dampener")
    val remoteSensorBooster = get("Remote Sensor Booster")
    val signatureRadiusSuppressor = get("Signature Suppressor")
    val stasisGrappler = get("Stasis Grappler")
    val stasisWebifier = get("Stasis Web")
    val targetPainter = get("Target Painter")
    val warpScrambler = get("Warp Scrambler")
    val warpDisruptFieldGenerator = get("Warp Disrupt Field Generator")
    val weaponDisruptor = get("Weapon Disruptor")
    val autoTargetingSystem = get("Automated Targeting System")
    val passiveTargetingSystem = get("Passive Targeting System")
    val coProcessor = get("CPU Enhancer")
    val cloakingDevice = get("Cloaking Device")
    val sensorBooster = get("Sensor Booster")
    val signalAmplifier = get("Signal Amplifier")
    val tractorBeam = get("Tractor Beam")
    val auxiliaryPowerCore = get("Auxiliary Power Core")
    val capacitorBattery = get("Capacitor Battery")
    val capacitorFluxCoil = get("Capacitor Flux Coil")
    val capacitorPowerRelay = get("Capacitor Power Relay")
    val capRecharger = get("Capacitor Recharger")
    val energyNeutralizer = get("Energy Neutralizer")
    val energyNosferatu = get("Energy Nosferatu")
    val powerDiagnosticSystem = get("Power Diagnostic System")
    val reactorControlUnit = get("Reactor Control Unit")
    val remoteCapacitorTransmitter = get("Remote Capacitor Transmitter")
    val cynosuralFieldGenerator = get("Cynosural Field Generator")
    val jumpPortalGenerator = get("Jump Portal Generator")
    val covertJumpPortalGenerator = get("Covert Jump Portal Generator")
    val gasCloudHarvester = get("Gas Cloud Harvesters")
    val gasCloudScoop = get("Gas Cloud Scoops")
    val stripMiner = get("Strip Miner")
    val miningLaser = get("Mining Laser")
    val frequencyMiningLaser = get("Frequency Mining Laser")
    val miningUpgrade = get("Mining Upgrade")
    val salvager = get("Salvager")
    val armorHardener = get("Armor Hardener")
    val armorResistanceShiftHardener = get("Armor Resistance Shift Hardener")
    val armorPlate = get("Armor Plate")
    val armorRepairer = get("Armor Repair Unit")
    val armorCoating = get("Armor Coating")
    val damageControl = get("Damage Control")
    val energizedArmorMembrane = get("Energized Armor Membrane")
    val hullRepairer = get("Hull Repair Unit")
    val expandedCargohold = get("Expanded Cargohold")
    val nanofiberInternalStructure = get("Nanofiber Internal Structure")
    val reinforcedBulkhead = get("Reinforced Bulkhead")
    val remoteArmorRepairer = get("Remote Armor Repairer")
    val mutadaptiveRemoteArmorRepairer = get("Mutadaptive Remote Armor Repairer")
    val remoteHullRepairer = get("Remote Hull Repairer")
    val propulsionModule = get("Propulsion Module")
    val microJumpDrive = get("Micro Jump Drive")
    val microJumpFieldGenerator = get("Micro Jump Field Generators")
    val inertialStabilizer = get("Inertial Stabilizer")
    val interdictionNullifier = get("Interdiction Nullifier")
    val jumpDriveEconomizer = get("Jump Drive Economizer")
    val warpAccelerator = get("Warp Accelerator")
    val warpCoreStabilizer = get("Warp Core Stabilizer")
    val dataMiner = get("Data Miners")
    val cargoScanner = get("Cargo Scanner")
    val entosisLink = get("Entosis Link")
    val scanningUpgradeTime = get("Scanning Upgrade Time")
    val scanningUpgrade = get("Scanning Upgrade")
    val shipScanner = get("Ship Scanner")
    val surveyProbeLauncher = get("Survey Probe Launcher")
    val surveyScanner = get("Survey Scanner")
    val remoteShieldBooster = get("Remote Shield Booster")
    val shieldBooster = get("Shield Booster")
    val shieldBoostAmplifier = get("Shield Boost Amplifier")
    val shieldExtender = get("Shield Extender")
    val shieldFluxCoil = get("Shield Flux Coil")
    val shieldHardener = get("Shield Hardener")
    val flexShieldHardener = get("Flex Shield Hardener")
    val shieldPowerRelay = get("Shield Power Relay")
    val shieldRecharger = get("Shield Recharger")
    val shieldResistanceAmplifier = get("Shield Resistance Amplifier")
    val smartbomb = get("Smart Bomb")
    val ballisticControlSystem = get("Ballistic Control system")
    val gyrostabilizer = get("Gyrostabilizer")
    val heatSink = get("Heat Sink")
    val magneticFieldStabilizer = get("Magnetic Field Stabilizer")
    val vortonProjectorUpgrade = get("Vorton Projector Upgrade")
    val entropicRadiationSink = get("Entropic Radiation Sink")
    val missileGuidanceComputer = get("Missile Guidance Computer")
    val missileGuidanceEnhancer = get("Missile Guidance Enhancer")
    val trackingComputer = get("Tracking Computer")
    val trackingEnhancer = get("Tracking Enhancer")
    val remoteTrackingComputer = get("Remote Tracking Computer")
    val commandBurst = get("Command Burst")

    val stasisWebifyingDrone = get("Stasis Webifying Drone")
    val energyNeutralizerDrone = get("Energy Neutralizer Drone")
    val miningDrone = get("Mining Drone")
    val combatDrone = get("Combat Drone")

    private val autoTargetingLightMissile = get("Auto-Targeting Light Missile")
    private val autoTargetingHeavyMissile = get("Auto-Targeting Heavy Missile")
    private val autoTargetingCruiseMissile = get("Auto-Targeting Cruise Missile")
    val allAutoTargetingMissiles = setOf(
        autoTargetingLightMissile,
        autoTargetingHeavyMissile,
        autoTargetingCruiseMissile
    )

    private val advancedRocket = get("Advanced Rocket")
    private val advancedLightMissile = get("Advanced Light Missile")
    private val advancedHeavyAssaultMissile = get("Advanced Heavy Assault Missile")
    private val advancedHeavyMissile = get("Advanced Heavy Missile")
    private val advancedTorpedo = get("Advanced Torpedo")
    private val advancedCruiseMissile = get("Advanced Cruise Missile")
    private val advancedXlTorpedo = get("Advanced XL Torpedo")
    private val advancedXlCruiseMissile = get("Advanced XL Cruise Missile")
    val allAdvancedMissiles = setOf(
        advancedRocket,
        advancedLightMissile,
        advancedHeavyAssaultMissile,
        advancedHeavyMissile,
        advancedTorpedo,
        advancedCruiseMissile,
        advancedXlTorpedo,
        advancedXlCruiseMissile,
    )

    private val rocket = get("Rocket")
    private val lightMissile = get("Light Missile")
    private val heavyAssaultMissile = get("Heavy Assault Missile")
    private val heavyMissile = get("Heavy Missile")
    private val torpedo = get("Torpedo")
    private val cruiseMissile = get("Cruise Missile")
    private val xlTorpedo = get("XL Torpedo")
    private val xlCruiseMissile = get("XL Cruise Missile")
    val allMissiles = setOf(
        rocket,
        lightMissile,
        heavyAssaultMissile,
        heavyMissile,
        torpedo,
        cruiseMissile,
        xlTorpedo,
        xlCruiseMissile,
    )

    val defenderMissiles = get("Defender Missiles")

    val projectileAmmo = get("Projectile Ammo")
    val advancedAutocannonAmmo = get("Advanced Autocannon Ammo")
    val advancedArtilleryAmmo = get("Advanced Artillery Ammo")

    val hybridCharge = get("Hybrid Charge")
    val advancedBlasterCharge = get("Advanced Blaster Charge")
    val advancedRailgunCharge = get("Advanced Railgun Charge")

    val frequencyCrystal = get("Frequency Crystal")
    val advancedPulseLaserCrystal = get("Advanced Pulse Laser Crystal")
    val advancedBeamLaserCrystal = get("Advanced Beam Laser Crystal")

    val mindlinks = get("Cyber Leadership")

    // Ship groups
    val corvette = get("Corvette")
    val shuttle = get("Shuttle")
    val frigate = get("Frigate")
    val cruiser = get("Cruiser")
    val hauler = get("Hauler")
    val battleship = get("Battleship")
    val titan = get("Titan")
    val prototypeExplorationShip = get("Prototype Exploration Ship")
    val assaultFrigate = get("Assault Frigate")
    val heavyAssaultCruiser = get("Heavy Assault Cruiser")
    val supercarrier = get("Supercarrier")
    val combatBattlecruiser = get("Combat Battlecruiser")
    val attackBattlecruiser = get("Attack Battlecruiser")
    val covertOps = get("Covert Ops")
    val electronicAttackShip = get("Electronic Attack Ship")
    val interceptor = get("Interceptor")
    val stealthBomber = get("Stealth Bomber")
    val forceReconShip = get("Force Recon Ship")
    val combatReconShip = get("Combat Recon Ship")
    val logistics = get("Logistics")
    val heavyInterdictionCruiser = get("Heavy Interdiction Cruiser")
    val blockadeRunner = get("Blockade Runner")
    val deepSpaceTransport = get("Deep Space Transport")
    val destroyer = get("Destroyer")
    val miningBarge = get("Mining Barge")
    val dreadnought = get("Dreadnought")
    val freighter = get("Freighter")
    val blackOps = get("Black Ops")
    val commandShip = get("Command Ship")
    val interdictor = get("Interdictor")
    val exhumer = get("Exhumer")
    val carrier = get("Carrier")
    val capitalIndustrialShip = get("Capital Industrial Ship")
    val industrialCommandShip = get("Industrial Command Ship")
    val marauder = get("Marauder")
    val jumpFreighter = get("Jump Freighter")
    val strategicCruiser = get("Strategic Cruiser")
    val expeditionFrigate = get("Expedition Frigate")
    val tacticalDestroyer = get("Tactical Destroyer")
    val logisticsFrigate = get("Logistics Frigate")
    val commandDestroyer = get("Command Destroyer")
    val forceAuxiliary = get("Force Auxiliary")
    val flagCruiser = get("Flag Cruiser")
    val lancerDreadnought = get("Lancer Dreadnought")

    val freightContainer = get("Freight Container")
    val secureCargoContainer = get("Secure Cargo Container")
    val auditLogSecureContainer = get("Audit Log Secure Container")
    val cargoContainer = get("Cargo Container")

}
