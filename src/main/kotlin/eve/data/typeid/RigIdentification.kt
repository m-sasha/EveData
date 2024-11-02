/**
 * Methods for identifying rigs.
 */

package eve.data.typeid

import eve.data.EveData
import eve.data.ModuleType


/**
 * Returns whether the given rig is an armor rig.
 */
context(EveData)
fun ModuleType.isArmorRig() = with(marketGroups) { isInGroup(armorRigs) }


/**
 * Returns whether the given rig is a shield rig.
 */
context(EveData)
fun ModuleType.isShieldRig() = with(marketGroups) { isInGroup(shieldRigs) }


/**
 * The variation parent type ids of Higgs Anchor rigs.
 */
private val HIGGS_ANCHOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(34266, 34268, 34306, 34308)


/**
 * Returns whether the given module is a Higgs Anchor rig.
 */
context(EveData)
fun ModuleType.isHiggsAnchor() =
    variationParentTypeId in HIGGS_ANCHOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Auxiliary Nano Pump rigs.
 */
private val AUXILIARY_NANO_PUMP_VARIATION_PARENT_TYPE_IDS = intArrayOf(25896, 27064, 31045, 31047)


/**
 * Returns whether the given module is an Auxiliary Nano Pump rig.
 */
context(EveData)
fun ModuleType.isAuxiliaryNanoPump() =
    variationParentTypeId in AUXILIARY_NANO_PUMP_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of EM Armor Reinforcer rigs.
 */
private val EM_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25736, 30997, 30999, 31001)


/**
 * Returns whether the given module is an EM Armor Reinforcer rig.
 */
context(EveData)
fun ModuleType.isEmArmorReinforcer() =
    variationParentTypeId in EM_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Explosive Armor Reinforcer rigs.
 */
private val EXPLOSIVE_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25888, 31009, 31011, 31013)


/**
 * Returns whether the given module is an Explosive Armor Reinforcer rig.
 */
context(EveData)
fun ModuleType.isExplosiveArmorReinforcer() =
    variationParentTypeId in EXPLOSIVE_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Kinetic Armor Reinforcer rigs.
 */
private val KINETIC_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25890, 31021, 31023, 31025)


/**
 * Returns whether the given module is a Kinetic Armor Reinforcer rig.
 */
context(EveData)
fun ModuleType.isKineticArmorReinforcer() =
    variationParentTypeId in KINETIC_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Nanobot Accelerator rigs.
 */
private val NANOBOT_ACCELERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25898, 31067, 31063, 31065)


/**
 * Returns whether the given module is a Nanobot Accelerator rig.
 */
context(EveData)
fun ModuleType.isNanobotAccelerator() =
    variationParentTypeId in NANOBOT_ACCELERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Remote Repair Augmentor rigs.
 */
private val REMOTE_REPAIR_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25900, 27068, 31073, 31075)


/**
 * Returns whether the given module is a Remote Repair Augmentor rig.
 */
context(EveData)
fun ModuleType.isRemoteRepairAugmentor() =
    variationParentTypeId in REMOTE_REPAIR_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Thermal Armor Reinforcer rigs.
 */
private val THERMAL_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25892, 31033, 31035, 31037)


/**
 * Returns whether the given module is a Thermal Armor Reinforcer rig.
 */
context(EveData)
fun ModuleType.isThermalArmorReinforcer() =
    variationParentTypeId in THERMAL_ARMOR_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Transverse Bulkhead rigs.
 */
private val TRANSVERSE_BULKHEAD_VARIATION_PARENT_TYPE_IDS = intArrayOf(33890, 33894, 33898, 33902)


/**
 * Returns whether the given module is a Transverse Bulkhead rig.
 */
context(EveData)
fun ModuleType.isTransverseBulkhead() =
    variationParentTypeId in TRANSVERSE_BULKHEAD_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Trimark Armor Pump rigs.
 */
private val TRIMARK_ARMOR_PUMP_VARIATION_PARENT_TYPE_IDS = intArrayOf(25894, 30987, 30993, 31055)


/**
 * Returns whether the given module is a Trimark Armor Pump rig.
 */
context(EveData)
fun ModuleType.isTrimarkArmorPump() =
    variationParentTypeId in TRIMARK_ARMOR_PUMP_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Ancillary Current Router rigs.
 */
private val ANCILLARY_CURRENT_ROUTER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25956, 31358, 31360, 31362)


/**
 * Returns whether the given module is an Ancillary Current Router rig.
 */
context(EveData)
fun ModuleType.isAncillaryCurrentRouter() =
    variationParentTypeId in ANCILLARY_CURRENT_ROUTER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Capacitor Control Circuit rigs.
 */
private val CAPACITOR_CONTROL_CIRCUIT_VARIATION_PARENT_TYPE_IDS = intArrayOf(25948, 31370, 31372, 31374)


/**
 * Returns whether the given module is a Capacitor Control Circuit rig.
 */
context(EveData)
fun ModuleType.isCapacitorControlCircuit() =
    variationParentTypeId in CAPACITOR_CONTROL_CIRCUIT_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Command Processor rigs.
 */
private val COMMAND_PROCESSOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(43894, 43896, 43898, 43900)


/**
 * Returns whether the given module is a Command Processor rig.
 */
context(EveData)
fun ModuleType.isCommandProcessor() =
    variationParentTypeId in COMMAND_PROCESSOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Egress Port Maximizer rigs.
 */
private val EGRESS_PORT_MAXIMIZER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25950, 31382, 31384, 31386)


/**
 * Returns whether the given module is an Egress Port Maximizer rig.
 */
context(EveData)
fun ModuleType.isEgressPortMaximizer() =
    variationParentTypeId in EGRESS_PORT_MAXIMIZER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Liquid Cooled Electronics rigs.
 */
private val LIQUID_COOLED_ELECTRONICS_VARIATION_PARENT_TYPE_IDS = intArrayOf(25934, 31226, 31228, 31230)


/**
 * Returns whether the given module is a Liquid Cooled Electronics rig.
 */
context(EveData)
fun ModuleType.isLiquidCooledElectronics() =
    variationParentTypeId in LIQUID_COOLED_ELECTRONICS_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Powergrid Subroutine Maximizer rigs.
 */
private val POWERGRID_SUBROUTINE_MAXIMIZER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25952, 31394, 31396, 31398)


/**
 * Returns whether the given module is a Powergrid Subroutine Maximizer rig.
 */
context(EveData)
fun ModuleType.isPowergridSubroutineMaximizer() =
    variationParentTypeId in POWERGRID_SUBROUTINE_MAXIMIZER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Processor Overclocking Unit rigs.
 */
private val PROCESSOR_OVERCLOCKING_UNIT_VARIATION_PARENT_TYPE_IDS = intArrayOf(4395, 4397, 26929, 33303)


/**
 * Returns whether the given module is a Processor Overclocking Unit rig.
 */
context(EveData)
fun ModuleType.isProcessorOverclockingUnit() =
    variationParentTypeId in PROCESSOR_OVERCLOCKING_UNIT_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Semiconductor Memory Cell rigs.
 */
private val SEMICONDUCTOR_MEMORY_CELL_VARIATION_PARENT_TYPE_IDS = intArrayOf(25954, 31406, 31408, 31410)


/**
 * Returns whether the given module is a Semiconductor Memory Cell rig.
 */
context(EveData)
fun ModuleType.isSemiconductorMemoryCell() =
    variationParentTypeId in SEMICONDUCTOR_MEMORY_CELL_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Drone Control Range Augmentor rigs.
 */
private val DRONE_CONTROL_RANGE_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25908, 32025, 32027, 33277)


/**
 * Returns whether the given module is a Drone Control Range Augmentor rig.
 */
context(EveData)
fun ModuleType.isDroneControlRangeAugmentor() =
    variationParentTypeId in DRONE_CONTROL_RANGE_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Drone Durability Enhancer rigs.
 */
private val DRONE_DURABILITY_ENHANCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25916, 32033, 32035, 33281)


/**
 * Returns whether the given module is a Drone Durability Enhancer rig.
 */
context(EveData)
fun ModuleType.isDroneDurabilityEnhancer() =
    variationParentTypeId in DRONE_DURABILITY_ENHANCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Drone Mining Augmentor rigs.
 */
private val DRONE_MINING_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25918, 32041, 32043, 33285)


/**
 * Returns whether the given module is a Drone Mining Augmentor rig.
 */
context(EveData)
fun ModuleType.isDroneMiningAugmentor() =
    variationParentTypeId in DRONE_MINING_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Drone Repair Augmentor rigs.
 */
private val DRONE_REPAIR_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25910, 32049, 32051, 33289)


/**
 * Returns whether the given module is a Drone Repair Augmentor rig.
 */
context(EveData)
fun ModuleType.isDroneRepairAugmentor() =
    variationParentTypeId in DRONE_REPAIR_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Drone Scope Chip rigs.
 */
private val DRONE_SCOPE_CHIP_VARIATION_PARENT_TYPE_IDS = intArrayOf(25912, 32069, 32071, 33293)


/**
 * Returns whether the given module is a Drone Scope Chip rig.
 */
context(EveData)
fun ModuleType.isDroneScopeChip() =
    variationParentTypeId in DRONE_SCOPE_CHIP_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Drone Speed Augmentor rigs.
 */
private val DRONE_SPEED_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25914, 32057, 32059, 33297)


/**
 * Returns whether the given module is a Drone Speed Augmentor rig.
 */
context(EveData)
fun ModuleType.isDroneSpeedAugmentor() =
    variationParentTypeId in DRONE_SPEED_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Sentry Damage Augmentor rigs.
 */
private val SENTRY_DAMAGE_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25920, 32081, 32083, 33307)


/**
 * Returns whether the given module is a Sentry Damage Augmentor rig.
 */
context(EveData)
fun ModuleType.isSentryDamageAugmentor() =
    variationParentTypeId in SENTRY_DAMAGE_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Stasis Drone Augmentor rigs.
 */
private val STASIS_DRONE_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25924, 32089, 32091, 33311)


/**
 * Returns whether the given module is a Stasis Drone Augmentor rig.
 */
context(EveData)
fun ModuleType.isStasisDroneAugmentor() =
    variationParentTypeId in STASIS_DRONE_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Inverted Signal Field Projector rigs.
 */
private val INVERTED_SIGNAL_FIELD_PROJECTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26110, 31262, 31264, 31266)


/**
 * Returns whether the given module is an Inverted Signal Field Projector rig.
 */
context(EveData)
fun ModuleType.isInvertedSignalFieldProjector() =
    variationParentTypeId in INVERTED_SIGNAL_FIELD_PROJECTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Particle Dispersion Augmentor rigs.
 */
private val PARTICLE_DISPERSION_AUGMENTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26106, 31286, 31288, 31290)


/**
 * Returns whether the given module is a Particle Dispersion Augmentor rig.
 */
context(EveData)
fun ModuleType.isParticleDispersionAugmentor() =
    variationParentTypeId in PARTICLE_DISPERSION_AUGMENTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Particle Dispersion Projector rigs.
 */
private val PARTICLE_DISPERSION_PROJECTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26108, 31298, 31300, 31302)


/**
 * Returns whether the given module is a Particle Dispersion Projector rig.
 */
context(EveData)
fun ModuleType.isParticleDispersionProjector() =
    variationParentTypeId in PARTICLE_DISPERSION_PROJECTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Signal Disruption Amplifier rigs.
 */
private val SIGNAL_DISRUPTION_AMPLIFIER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25928, 31250, 31252, 31254)


/**
 * Returns whether the given module is a Signal Disruption Amplifier rig.
 */
context(EveData)
fun ModuleType.isSignalDisruptionAmplifier() =
    variationParentTypeId in SIGNAL_DISRUPTION_AMPLIFIER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Targeting Systems Stabilizer rigs.
 */
private val TARGETING_SYSTEMS_STABILIZER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26096, 31334, 31336, 31338)


/**
 * Returns whether the given module is a Targeting Systems Stabilizer rig.
 */
context(EveData)
fun ModuleType.isTargetingSystemsStabilizer() =
    variationParentTypeId in TARGETING_SYSTEMS_STABILIZER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Tracking Diagnostic Subroutines rigs.
 */
private val TRACKING_DIAGNOSTIC_SUBROUTINES_VARIATION_PARENT_TYPE_IDS = intArrayOf(26112, 31346, 31348, 31350)


/**
 * Returns whether the given module is a Tracking Diagnostic Subroutines rig.
 */
context(EveData)
fun ModuleType.isTrackingDiagnosticSubroutines() =
    variationParentTypeId in TRACKING_DIAGNOSTIC_SUBROUTINES_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Algid Energy Administrations Unit rigs.
 */
private val ALGID_ENERGY_ADMINISTRATIONS_UNIT_VARIATION_PARENT_TYPE_IDS = intArrayOf(25976, 31418, 31420, 31422)


/**
 * Returns whether the given module is an Algid Energy Administrations Unit rig.
 */
context(EveData)
fun ModuleType.isAlgidEnergyAdministrationsUnit() =
    variationParentTypeId in ALGID_ENERGY_ADMINISTRATIONS_UNIT_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Energy Ambit Extension rigs.
 */
private val ENERGY_AMBIT_EXTENSION_VARIATION_PARENT_TYPE_IDS = intArrayOf(25970, 31430, 31432, 31434)


/**
 * Returns whether the given module is an Energy Ambit Extension rig.
 */
context(EveData)
fun ModuleType.isEnergyAmbitExtension() =
    variationParentTypeId in ENERGY_AMBIT_EXTENSION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Energy Burst Aerator rigs.
 */
private val ENERGY_BURST_AERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25978, 31442, 31444, 31446)


/**
 * Returns whether the given module is an Energy Burst Aerator rig.
 */
context(EveData)
fun ModuleType.isEnergyBurstAerator() =
    variationParentTypeId in ENERGY_BURST_AERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Energy Collision Accelerator rigs.
 */
private val ENERGY_COLLISION_ACCELERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25980, 31454, 31456, 31458)


/**
 * Returns whether the given module is an Energy Collision Accelerator rig.
 */
context(EveData)
fun ModuleType.isEnergyCollisionAccelerator() =
    variationParentTypeId in ENERGY_COLLISION_ACCELERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Energy Discharge Elutriation rigs.
 */
private val ENERGY_DISCHARGE_ELUTRIATION_VARIATION_PARENT_TYPE_IDS = intArrayOf(25968, 31466, 31468, 31470)


/**
 * Returns whether the given module is an Energy Discharge Elutriation rig.
 */
context(EveData)
fun ModuleType.isEnergyDischargeElutriation() =
    variationParentTypeId in ENERGY_DISCHARGE_ELUTRIATION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Energy Locus Coordinator rigs.
 */
private val ENERGY_LOCUS_COORDINATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(25972, 31478, 31480, 31482)


/**
 * Returns whether the given module is an Energy Locus Coordinator rig.
 */
context(EveData)
fun ModuleType.isEnergyLocusCoordinator() =
    variationParentTypeId in ENERGY_LOCUS_COORDINATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Energy Metastasis Adjuster rigs.
 */
private val ENERGY_METASTASIS_ADJUSTER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25974, 31490, 31492, 31494)


/**
 * Returns whether the given module is an Energy Metastasis Adjuster rig.
 */
context(EveData)
fun ModuleType.isEnergyMetastasisAdjuster() =
    variationParentTypeId in ENERGY_METASTASIS_ADJUSTER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Algid Hybrid Administrations Unit rigs.
 */
private val ALGID_HYBRID_ADMINISTRATIONS_UNIT_VARIATION_PARENT_TYPE_IDS = intArrayOf(26004, 31502, 31504, 31506)


/**
 * Returns whether the given module is an Algid Hybrid Administrations Unit rig.
 */
context(EveData)
fun ModuleType.isAlgidHybridAdministrationsUnit() =
    variationParentTypeId in ALGID_HYBRID_ADMINISTRATIONS_UNIT_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hybrid Ambit Extension rigs.
 */
private val HYBRID_AMBIT_EXTENSION_VARIATION_PARENT_TYPE_IDS = intArrayOf(25998, 31514, 31516, 31518)


/**
 * Returns whether the given module is a Hybrid Ambit Extension rig.
 */
context(EveData)
fun ModuleType.isHybridAmbitExtension() =
    variationParentTypeId in HYBRID_AMBIT_EXTENSION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hybrid Burst Aerator rigs.
 */
private val HYBRID_BURST_AERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26006, 31526, 31528, 31530)


/**
 * Returns whether the given module is a Hybrid Burst Aerator rig.
 */
context(EveData)
fun ModuleType.isHybridBurstAerator() =
    variationParentTypeId in HYBRID_BURST_AERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hybrid Collision Accelerator rigs.
 */
private val HYBRID_COLLISION_ACCELERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26008, 31538, 31540, 31542)


/**
 * Returns whether the given module is a Hybrid Collision Accelerator rig.
 */
context(EveData)
fun ModuleType.isHybridCollisionAccelerator() =
    variationParentTypeId in HYBRID_COLLISION_ACCELERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hybrid Discharge Elutriation rigs.
 */
private val HYBRID_DISCHARGE_ELUTRIATION_VARIATION_PARENT_TYPE_IDS = intArrayOf(25996, 31550, 31552, 31554)


/**
 * Returns whether the given module is a Hybrid Discharge Elutriation rig.
 */
context(EveData)
fun ModuleType.isHybridDischargeElutriation() =
    variationParentTypeId in HYBRID_DISCHARGE_ELUTRIATION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hybrid Locus Coordinator rigs.
 */
private val HYBRID_LOCUS_COORDINATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26000, 31562, 31564, 31566)


/**
 * Returns whether the given module is a Hybrid Locus Coordinator rig.
 */
context(EveData)
fun ModuleType.isHybridLocusCoordinator() =
    variationParentTypeId in HYBRID_LOCUS_COORDINATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hybrid Metastasis Adjuster rigs.
 */
private val HYBRID_METASTASIS_ADJUSTER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26002, 31574, 31576, 31578)


/**
 * Returns whether the given module is a Hybrid Metastasis Adjuster rig.
 */
context(EveData)
fun ModuleType.isHybridMetastasisAdjuster() =
    variationParentTypeId in HYBRID_METASTASIS_ADJUSTER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Bay Loading Accelerator rigs.
 */
private val BAY_LOADING_ACCELERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26026, 31586, 31588, 31590)


/**
 * Returns whether the given module is a Bay Loading Accelerator rig.
 */
context(EveData)
fun ModuleType.isBayLoadingAccelerator() =
    variationParentTypeId in BAY_LOADING_ACCELERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hydraulic Bay Thrusters rigs.
 */
private val HYDRAULIC_BAY_THRUSTERS_VARIATION_PARENT_TYPE_IDS = intArrayOf(26016, 31598, 31600, 31602)


/**
 * Returns whether the given module is a Hydraulic Bay Thrusters rig.
 */
context(EveData)
fun ModuleType.isHydraulicBayThrusters() =
    variationParentTypeId in HYDRAULIC_BAY_THRUSTERS_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Rocket Fuel Cache Partition rigs.
 */
private val ROCKET_FUEL_CACHE_PARTITION_VARIATION_PARENT_TYPE_IDS = intArrayOf(26022, 31608, 31610, 31612)


/**
 * Returns whether the given module is a Rocket Fuel Cache Partition rig.
 */
context(EveData)
fun ModuleType.isRocketFuelCachePartition() =
    variationParentTypeId in ROCKET_FUEL_CACHE_PARTITION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Warhead Calefaction Catalyst rigs.
 */
private val WARHEAD_CALEFACTION_CATALYST_VARIATION_PARENT_TYPE_IDS = intArrayOf(26030, 31620, 31622, 31624)


/**
 * Returns whether the given module is a Warhead Calefaction Catalyst rig.
 */
context(EveData)
fun ModuleType.isWarheadCalefactionCatalyst() =
    variationParentTypeId in WARHEAD_CALEFACTION_CATALYST_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Warhead Flare Catalyst rigs.
 */
private val WARHEAD_FLARE_CATALYST_VARIATION_PARENT_TYPE_IDS = intArrayOf(26028, 31632, 31634, 31636)


/**
 * Returns whether the given module is a Warhead Flare Catalyst rig.
 */
context(EveData)
fun ModuleType.isWarheadFlareCatalyst() =
    variationParentTypeId in WARHEAD_FLARE_CATALYST_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Warhead Rigor Catalyst rigs.
 */
private val WARHEAD_RIGOR_CATALYST_VARIATION_PARENT_TYPE_IDS = intArrayOf(26020, 31644, 31646, 31648)


/**
 * Returns whether the given module is a Warhead Rigor Catalyst rig.
 */
context(EveData)
fun ModuleType.isWarheadRigorCatalyst() =
    variationParentTypeId in WARHEAD_RIGOR_CATALYST_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Auxiliary Thrusters rigs.
 */
private val AUXILIARY_THRUSTERS_VARIATION_PARENT_TYPE_IDS = intArrayOf(26060, 31105, 31107, 31109)


/**
 * Returns whether the given module is an Auxiliary Thrusters rig.
 */
context(EveData)
fun ModuleType.isAuxiliaryThrusters() =
    variationParentTypeId in AUXILIARY_THRUSTERS_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Cargohold Optimization rigs.
 */
private val CARGOHOLD_OPTIMIZATION_VARIATION_PARENT_TYPE_IDS = intArrayOf(26072, 31117, 31119, 31121)


/**
 * Returns whether the given module is a Cargohold Optimization rig.
 */
context(EveData)
fun ModuleType.isCargoholdOptimization() =
    variationParentTypeId in CARGOHOLD_OPTIMIZATION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Dynamic Fuel Valve rigs.
 */
private val DYNAMIC_FUEL_VALVE_VARIATION_PARENT_TYPE_IDS = intArrayOf(26056, 31129, 31131, 31133)


/**
 * Returns whether the given module is a Dynamic Fuel Valve rig.
 */
context(EveData)
fun ModuleType.isDynamicFuelValve() =
    variationParentTypeId in DYNAMIC_FUEL_VALVE_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Engine Thermal Shielding rigs.
 */
private val ENGINE_THERMAL_SHIELDING_VARIATION_PARENT_TYPE_IDS = intArrayOf(26062, 31141, 31143, 31145)


/**
 * Returns whether the given module is an Engine Thermal Shielding rig.
 */
context(EveData)
fun ModuleType.isEngineThermalShielding() =
    variationParentTypeId in ENGINE_THERMAL_SHIELDING_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Hyperspatial Velocity Optimizer rigs.
 */
private val HYPERSPATIAL_VELOCITY_OPTIMIZER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26068, 31159, 31161, 31163)


/**
 * Returns whether the given module is a Hyperspatial Velocity Optimizer rig.
 */
context(EveData)
fun ModuleType.isHyperspatialVelocityOptimizer() =
    variationParentTypeId in HYPERSPATIAL_VELOCITY_OPTIMIZER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Low Friction Nozzle Joints rigs.
 */
private val LOW_FRICTION_NOZZLE_JOINTS_VARIATION_PARENT_TYPE_IDS = intArrayOf(26058, 31153, 31155, 31157)


/**
 * Returns whether the given module is a Low Friction Nozzle Joints rig.
 */
context(EveData)
fun ModuleType.isLowFrictionNozzleJoints() =
    variationParentTypeId in LOW_FRICTION_NOZZLE_JOINTS_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Polycarbon Engine Housing rigs.
 */
private val POLYCARBON_ENGINE_HOUSING_VARIATION_PARENT_TYPE_IDS = intArrayOf(26070, 31177, 31179, 31181)


/**
 * Returns whether the given module is a Polycarbon Engine Housing rig.
 */
context(EveData)
fun ModuleType.isPolycarbonEngineHousing() =
    variationParentTypeId in POLYCARBON_ENGINE_HOUSING_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Warp Core Optimizer rigs.
 */
private val WARP_CORE_OPTIMIZER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26066, 31189, 31191, 31193)


/**
 * Returns whether the given module is a Warp Core Optimizer rig.
 */
context(EveData)
fun ModuleType.isWarpCoreOptimizer() =
    variationParentTypeId in WARP_CORE_OPTIMIZER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Projectile Ambit Extension rigs.
 */
private val PROJECTILE_AMBIT_EXTENSION_VARIATION_PARENT_TYPE_IDS = intArrayOf(26038, 31656, 31658, 31660)


/**
 * Returns whether the given module is a Projectile Ambit Extension rig.
 */
context(EveData)
fun ModuleType.isProjectileAmbitExtension() =
    variationParentTypeId in PROJECTILE_AMBIT_EXTENSION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Projectile Burst Aerator rigs.
 */
private val PROJECTILE_BURST_AERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26046, 31668, 31670, 31672)


/**
 * Returns whether the given module is a Projectile Burst Aerator rig.
 */
context(EveData)
fun ModuleType.isProjectileBurstAerator() =
    variationParentTypeId in PROJECTILE_BURST_AERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Projectile Collision Accelerator rigs.
 */
private val PROJECTILE_COLLISION_ACCELERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26048, 31680, 31682, 31684)


/**
 * Returns whether the given module is a Projectile Collision Accelerator rig.
 */
context(EveData)
fun ModuleType.isProjectileCollisionAccelerator() =
    variationParentTypeId in PROJECTILE_COLLISION_ACCELERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Projectile Locus Coordinator rigs.
 */
private val PROJECTILE_LOCUS_COORDINATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26040, 31692, 31694, 31696)


/**
 * Returns whether the given module is a Projectile Locus Coordinator rig.
 */
context(EveData)
fun ModuleType.isProjectileLocusCoordinator() =
    variationParentTypeId in PROJECTILE_LOCUS_COORDINATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Projectile Metastasis Adjuster rigs.
 */
private val PROJECTILE_METASTASIS_ADJUSTER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26042, 31704, 31706, 31708)


/**
 * Returns whether the given module is a Projectile Metastasis Adjuster rig.
 */
context(EveData)
fun ModuleType.isProjectileMetastasisAdjuster() =
    variationParentTypeId in PROJECTILE_METASTASIS_ADJUSTER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Ice Harvester Accelerator rigs.
 */
private val ICE_HARVESTER_ACCELERATOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(32819)


/**
 * Returns whether the given module is an Ice Harvester Accelerator rig.
 */
context(EveData)
fun ModuleType.isIceHarvesterAccelerator() =
    variationParentTypeId in ICE_HARVESTER_ACCELERATOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Mercoxit Mining Crystal Optimization rigs.
 */
private val MERCOXIT_MINING_CRYSTAL_OPTIMIZATION_VARIATION_PARENT_TYPE_IDS = intArrayOf(32817)


/**
 * Returns whether the given module is a Mercoxit Mining Crystal Optimization rig.
 */
context(EveData)
fun ModuleType.isMercoxitMiningCrystalOptimization() =
    variationParentTypeId in MERCOXIT_MINING_CRYSTAL_OPTIMIZATION_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Salvage Tackle rigs.
 */
private val SALVAGE_TACKLE_VARIATION_PARENT_TYPE_IDS = intArrayOf(25902, 31083, 31085, 31087)


/**
 * Returns whether the given module is a Salvage Tackle rig.
 */
context(EveData)
fun ModuleType.isSalvageTackle() =
    variationParentTypeId in SALVAGE_TACKLE_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Emission Scope Sharpener rigs.
 */
private val EMISSION_SCOPE_SHARPENER_VARIATION_PARENT_TYPE_IDS = intArrayOf(25930, 31201, 31203, 31205)


/**
 * Returns whether the given module is an Emission Scope Sharpener rig.
 */
context(EveData)
fun ModuleType.isEmissionScopeSharpener() =
    variationParentTypeId in EMISSION_SCOPE_SHARPENER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Gravity Capacitor Upgrade rigs.
 */
private val GRAVITY_CAPACITOR_UPGRADE_VARIATION_PARENT_TYPE_IDS = intArrayOf(25936, 31213, 31215, 31217)


/**
 * Returns whether the given module is a Gravity Capacitor Upgrade rig.
 */
context(EveData)
fun ModuleType.isGravityCapacitorUpgrade() =
    variationParentTypeId in GRAVITY_CAPACITOR_UPGRADE_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Memetic Algorithm Bank rigs.
 */
private val MEMETIC_ALGORITHM_BANK_VARIATION_PARENT_TYPE_IDS = intArrayOf(25932, 31238, 31240, 31242)


/**
 * Returns whether the given module is a Memetic Algorithm Bank rig.
 */
context(EveData)
fun ModuleType.isMemeticAlgorithmBank() =
    variationParentTypeId in MEMETIC_ALGORITHM_BANK_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Signal Focusing Kit rigs.
 */
private val SIGNAL_FOCUSING_KIT_VARIATION_PARENT_TYPE_IDS = intArrayOf(26104, 31310, 31312, 31314)


/**
 * Returns whether the given module is a Signal Focusing Kit rig.
 */
context(EveData)
fun ModuleType.isSignalFocusingKit() =
    variationParentTypeId in SIGNAL_FOCUSING_KIT_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Core Defense Capacitor Safeguard rigs.
 */
private val CORE_DEFENSE_CAPACITOR_SAFEGUARD_VARIATION_PARENT_TYPE_IDS = intArrayOf(25906, 31764, 31766, 31768)


/**
 * Returns whether the given module is a Core Defense Capacitor Safeguard rig.
 */
context(EveData)
fun ModuleType.isCoreDefenseCapacitorSafeguard() =
    variationParentTypeId in CORE_DEFENSE_CAPACITOR_SAFEGUARD_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Core Defense Charge Economizer rigs.
 */
private val CORE_DEFENSE_CHARGE_ECONOMIZER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26090, 31776, 31778, 31780)


/**
 * Returns whether the given module is a Core Defense Charge Economizer rig.
 */
context(EveData)
fun ModuleType.isCoreDefenseChargeEconomizer() =
    variationParentTypeId in CORE_DEFENSE_CHARGE_ECONOMIZER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Core Defense Field Extender rigs.
 */
private val CORE_DEFENSE_FIELD_EXTENDER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26088, 31788, 31790, 31792)


/**
 * Returns whether the given module is a Core Defense Field Extender rig.
 */
context(EveData)
fun ModuleType.isCoreDefenseFieldExtender() =
    variationParentTypeId in CORE_DEFENSE_FIELD_EXTENDER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Core Defense Field Purger rigs.
 */
private val CORE_DEFENSE_FIELD_PURGER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26084, 31800, 31802, 31804)


/**
 * Returns whether the given module is a Core Defense Field Purger rig.
 */
context(EveData)
fun ModuleType.isCoreDefenseFieldPurger() =
    variationParentTypeId in CORE_DEFENSE_FIELD_PURGER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Core Defense Operational Solidifier rigs.
 */
private val CORE_DEFENSE_OPERATIONAL_SOLIDIFIER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26086, 31816, 31818, 31820)


/**
 * Returns whether the given module is a Core Defense Operational Solidifier rig.
 */
context(EveData)
fun ModuleType.isCoreDefenseOperationalSolidifier() =
    variationParentTypeId in CORE_DEFENSE_OPERATIONAL_SOLIDIFIER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of EM Shield Reinforcer rigs.
 */
private val EM_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26076, 31716, 31718, 31720)


/**
 * Returns whether the given module is an EM Shield Reinforcer rig.
 */
context(EveData)
fun ModuleType.isEmShieldReinforcer() =
    variationParentTypeId in EM_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Explosive Shield Reinforcer rigs.
 */
private val EXPLOSIVE_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26078, 31728, 31730, 31732)


/**
 * Returns whether the given module is an Explosive Shield Reinforcer rig.
 */
context(EveData)
fun ModuleType.isExplosiveShieldReinforcer() =
    variationParentTypeId in EXPLOSIVE_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Kinetic Shield Reinforcer rigs.
 */
private val KINETIC_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26080, 31740, 31742, 31744)


/**
 * Returns whether the given module is a Kinetic Shield Reinforcer rig.
 */
context(EveData)
fun ModuleType.isKineticShieldReinforcer() =
    variationParentTypeId in KINETIC_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Thermal Shield Reinforcer rigs.
 */
private val THERMAL_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26082, 31752, 31754, 31756)


/**
 * Returns whether the given module is a Thermal Shield Reinforcer rig.
 */
context(EveData)
fun ModuleType.isThermalShieldReinforcer() =
    variationParentTypeId in THERMAL_SHIELD_REINFORCER_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Ionic Field Projector rigs.
 */
private val IONIC_FIELD_PROJECTOR_VARIATION_PARENT_TYPE_IDS = intArrayOf(26102, 31274, 31276, 31278)


/**
 * Returns whether the given module is an Ionic Field Projector rig.
 */
context(EveData)
fun ModuleType.isIonicFieldProjector() =
    variationParentTypeId in IONIC_FIELD_PROJECTOR_VARIATION_PARENT_TYPE_IDS


/**
 * The variation parent type ids of Targeting System Subcontroller rigs.
 */
private val TARGETING_SYSTEM_SUBCONTROLLER_VARIATION_PARENT_TYPE_IDS = intArrayOf(26100, 31322, 31324, 31326)


/**
 * Returns whether the given module is a Targeting System Subcontroller rig.
 */
context(EveData)
fun ModuleType.isTargetingSystemSubcontroller() =
    variationParentTypeId in TARGETING_SYSTEM_SUBCONTROLLER_VARIATION_PARENT_TYPE_IDS
