package eve.data.typeid

import eve.data.EnvironmentType
import eve.data.EveData


/**
 * The variation parent type id of "Pulsar" wormhole environments.
 */
private const val PULSAR_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 30844


/**
 * Returns whether the given environment is a "Pulsar" wormhole.
 */
context(EveData)
fun EnvironmentType.isPulsarWormhole() =
    variationParentTypeId == PULSAR_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Black Hole" wormhole environments.
 */
private const val BLACK_HOLE_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 30845


/**
 * Returns whether the given environment is a "Black Hole" wormhole.
 */
context(EveData)
fun EnvironmentType.isBlackHoleWormhole() =
    variationParentTypeId == BLACK_HOLE_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Cataclysmic Variable" wormhole environments.
 */
private const val CATACLYSMIC_VARIABLE_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 30846


/**
 * Returns whether the given environment is a "Cataclysmic Variable" wormhole.
 */
context(EveData)
fun EnvironmentType.isCataclysmicVariableWormhole() =
    variationParentTypeId == CATACLYSMIC_VARIABLE_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Magnetar" wormhole environments.
 */
private const val MAGNETAR_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 30847


/**
 * Returns whether the given environment is a "Magnetar" wormhole.
 */
context(EveData)
fun EnvironmentType.isMagnetarWormhole() =
    variationParentTypeId == MAGNETAR_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Red Giant wormhole environments.
 */
private const val RED_GIANT_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 30848


/**
 * Returns whether the given environment is a "Red Giant" wormhole.
 */
context(EveData)
fun EnvironmentType.isRedGiantWormhole() =
    variationParentTypeId == RED_GIANT_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Wolf Rayet" wormhole environments.
 */
private const val WOLF_RAYET_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 30849


/**
 * Returns whether the given environment is a "Wolf Rayet" wormhole.
 */
context(EveData)
fun EnvironmentType.isWolfRayetWormhole() =
    variationParentTypeId == WOLF_RAYET_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type ids of all wormhole environments.
 */
private val WormholeEnvironmentsVariationParentTypeIds = setOf(
    PULSAR_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    BLACK_HOLE_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    CATACLYSMIC_VARIABLE_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    MAGNETAR_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    RED_GIANT_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    WOLF_RAYET_WORMHOLE_ENVIRONMENT_VARIATION_PARENT_TYPE_ID
)


/**
 * Returns whether the given environment is a wormhole.
 */
context(EveData)
fun EnvironmentType.isWormhole() = variationParentTypeId in WormholeEnvironmentsVariationParentTypeIds


/**
 * The variation parent type id of "Gamma Ray" metaliminal storm environments.
 */
private const val GAMMA_RAY_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 56060


/**
 * Returns whether the given environment is a "Gamma Ray" metaliminal storm.
 */
context(EveData)
fun EnvironmentType.isGammaRayMetaliminalStorm() =
    variationParentTypeId == GAMMA_RAY_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Electrical" metaliminal storm environments.
 */
private const val ELECTRICAL_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 56064


/**
 * Returns whether the given environment is a "Electrical" metaliminal storm.
 */
context(EveData)
fun EnvironmentType.isElectricalMetaliminalStorm() =
    variationParentTypeId == ELECTRICAL_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Plasma Firestorm" metaliminal storm environments.
 */
private const val PLASMA_FIRESTORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 56062


/**
 * Returns whether the given environment is a "Plasma Firestorm" metaliminal storm.
 */
context(EveData)
fun EnvironmentType.isPlasmaMetaliminalStorm() =
    variationParentTypeId == PLASMA_FIRESTORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Exotic Matter" metaliminal storm environments.
 */
private const val EXOTIC_MATTER_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 56058


/**
 * Returns whether the given environment is an "Exotic Matter" metaliminal storm.
 */
context(EveData)
fun EnvironmentType.isExoticMatterMetaliminalStorm() =
    variationParentTypeId == EXOTIC_MATTER_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type ids of all metaliminal storm environments.
 */
private val MetaliminalStormEnvironmentsVariationParentTypeIds = setOf(
    GAMMA_RAY_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    ELECTRICAL_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    PLASMA_FIRESTORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    EXOTIC_MATTER_METALIMINAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID
)


/**
 * Returns whether the given environment is a metaliminal storm.
 */
context(EveData)
fun EnvironmentType.isMetaliminalStorm() = variationParentTypeId in MetaliminalStormEnvironmentsVariationParentTypeIds


/**
 * The variation parent type id of abyssal "Electrical" storm environments.
 */
private const val ABYSSAL_ELECTRICAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 47381


/**
 * Returns whether the given environment is an abyssal "Electrical" storm.
 */
context(EveData)
fun EnvironmentType.isAbyssalElectricalStorm() =
    variationParentTypeId == ABYSSAL_ELECTRICAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of abyssal "Exotic Particle" storm environments.
 */
private const val ABYSSAL_EXOTIC_PARTICLE_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 47384


/**
 * Returns whether the given environment is an abyssal "Exotic Particle" storm.
 */
context(EveData)
fun EnvironmentType.isAbyssalExoticParticleStorm() =
    variationParentTypeId == ABYSSAL_EXOTIC_PARTICLE_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of abyssal "Plasma Firestorm" environments.
 */
private const val ABYSSAL_PLASMA_FIRESTORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 47390


/**
 * Returns whether the given environment is an abyssal "Plasma Firestorm".
 */
context(EveData)
fun EnvironmentType.isAbyssalPlasmaFirestorm() =
    variationParentTypeId == ABYSSAL_PLASMA_FIRESTORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Abyssal Gamma Ray Afterglow Storm" environments.
 */
private const val ABYSSAL_GAMMA_RAY_AFTERGLOW_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 47387


/**
 * Returns whether the given environment is a "Abyssal Gamma Ray Afterglow Storm".
 */
context(EveData)
fun EnvironmentType.isAbyssalGammaRayAfterglowStorm() =
    variationParentTypeId == ABYSSAL_GAMMA_RAY_AFTERGLOW_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of abyssal "Dark Matter Field" storm environments.
 */
private const val ABYSSAL_DARK_MATTER_FIELD_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID = 47378


/**
 * Returns whether the given environment is an abyssal "Dark Matter Field" storm.
 */
context(EveData)
fun EnvironmentType.isAbyssalDarkMatterFieldStorm() =
    variationParentTypeId == ABYSSAL_DARK_MATTER_FIELD_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type ids of all abyssal storm environments.
 */
private val AbyssalStormEnvironmentsVariationParentTypeIds = setOf(
    ABYSSAL_ELECTRICAL_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    ABYSSAL_PLASMA_FIRESTORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    ABYSSAL_GAMMA_RAY_AFTERGLOW_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    ABYSSAL_EXOTIC_PARTICLE_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID,
    ABYSSAL_DARK_MATTER_FIELD_STORM_ENVIRONMENT_VARIATION_PARENT_TYPE_ID
)


/**
 * Returns whether the given environment is an abyssal storm.
 */
context(EveData)
fun EnvironmentType.isAbyssalStorm() = variationParentTypeId in AbyssalStormEnvironmentsVariationParentTypeIds
