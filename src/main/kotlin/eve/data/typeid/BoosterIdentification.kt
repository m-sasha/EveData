/**
 * Methods for identifying implants.
 */

package eve.data.typeid

import eve.data.BoosterType
import eve.data.EveData


/**
 * The variation parent type id of "Blue Pill" boosters.
 */
private const val BLUE_PILL_BOOSTER_VARIATION_PARENT_TYPE_ID = 9950


/**
 * Returns whether the given booster is a "Blue Pill" booster.
 * Note that this includes the "Antipharmakon Thureo" booster.
 */
context(EveData)
fun BoosterType.isBluePill() =
    variationParentTypeId == BLUE_PILL_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Exile" boosters.
 */
private const val EXILE_BOOSTER_VARIATION_PARENT_TYPE_ID = 15479


/**
 * Returns whether the given booster is an "Exile" booster.
 * Note that this includes the "Antipharmakon Kosybo" booster.
 */
context(EveData)
fun BoosterType.isExile() =
    variationParentTypeId == EXILE_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Mindflood" boosters.
 */
private const val MINDFLOOD_BOOSTER_VARIATION_PARENT_TYPE_ID = 15463


/**
 * Returns whether the given booster is a "Mindflood" booster.
 * Note that this includes the "Antipharmakon Aeolis" booster
 */
context(EveData)
fun BoosterType.isMindflood() =
    variationParentTypeId == MINDFLOOD_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "X-Instinct" boosters.
 */
private const val X_INSTINCT_BOOSTER_VARIATION_PARENT_TYPE_ID = 15457


/**
 * Returns whether the given booster is an "X-Instinct" booster.
 */
context(EveData)
fun BoosterType.isXInstinct() =
    variationParentTypeId == X_INSTINCT_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Drop" boosters.
 */
private const val DROP_BOOSTER_VARIATION_PARENT_TYPE_ID = 15466


/**
 * Returns whether the given booster is a "Drop" booster.
 * Note that this includes the "Antipharmakon Iokira" booster.
 */
context(EveData)
fun BoosterType.isDrop() =
    variationParentTypeId == DROP_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Frentix" boosters.
 */
private const val FRENTIX_BOOSTER_VARIATION_PARENT_TYPE_ID = 15460


/**
 * Returns whether the given booster is a "Frentix" booster.
 */
context(EveData)
fun BoosterType.isFrentix() =
    variationParentTypeId == FRENTIX_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Sooth Sayer" boosters.
 */
private const val SOOTH_SAYER_BOOSTER_VARIATION_PARENT_TYPE_ID = 10164


/**
 * Returns whether the given booster is a "Sooth Sayer" booster.
 */
context(EveData)
fun BoosterType.isSoothSayer() =
    variationParentTypeId == SOOTH_SAYER_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Crash" boosters.
 */
private const val CRASH_BOOSTER_VARIATION_PARENT_TYPE_ID = 9947


/**
 * Returns whether the given booster is a "Crash" booster.
 */
context(EveData)
fun BoosterType.isCrash() =
    variationParentTypeId == CRASH_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Agency `Hardshell`" boosters.
 */
private const val AGENCY_HARDSHELL_BOOSTER_VARIATION_PARENT_TYPE_ID = 46001


/**
 * Returns whether the given booster is an "Agency `Hardshell`" booster.
 */
context(EveData)
fun BoosterType.isAgencyHardshell() =
    variationParentTypeId == AGENCY_HARDSHELL_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Agency `Overclocker`" boosters.
 */
private const val AGENCY_OVERCLOCKER_BOOSTER_VARIATION_PARENT_TYPE_ID = 46004


/**
 * Returns whether the given booster is an "Agency `Overclocker`" booster.
 */
context(EveData)
fun BoosterType.isAgencyOverclocker() =
    variationParentTypeId == AGENCY_OVERCLOCKER_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The variation parent type id of "Agency Pyrolancea" boosters.
 */
private const val AGENCY_PYROLANCEA_BOOSTER_VARIATION_PARENT_TYPE_ID = 45998


/**
 * Returns whether the given booster is an "Agency Pyrolancea" booster.
 */
context(EveData)
fun BoosterType.isAgencyPyrolancea() =
    variationParentTypeId == AGENCY_PYROLANCEA_BOOSTER_VARIATION_PARENT_TYPE_ID


/**
 * The id of "Quafe Zero Classic" boosters.
 */
private const val QUAFE_ZERO_CLASSIC_BOOSTER_TYPE_ID = 3898


/**
 * Returns whether the given booster is the "Quafe Zero Classic" booster.
 */
context(EveData)
fun BoosterType.isQuafeZeroClassic() =
    itemId == QUAFE_ZERO_CLASSIC_BOOSTER_TYPE_ID



/**
 * The id of "Quafe Zero Green Apple" boosters.
 */
private const val QUAFE_ZERO_GREEN_APPLE_BOOSTER_ID = 60575


/**
 * Returns whether the given booster is a "Quafe Zero Green Apple" booster.
 */
context(EveData)
fun BoosterType.isQuafeZeroGreenApple() =
    variationParentTypeId == QUAFE_ZERO_GREEN_APPLE_BOOSTER_ID



/**
 * The id of "Antipharmakon Toxot" boosters.
 */
private const val ANTIPHARMAKON_TOXOT_BOOSTER_ID = 36911


/**
 * Returns whether the given booster is an "Antipharmakon Toxot" booster.
 */
context(EveData)
fun BoosterType.isAntipharmakonToxot() =
    itemId == ANTIPHARMAKON_TOXOT_BOOSTER_ID


/**
 * The id of "Veilguard" boosters.
 */
private const val VEILGUARD_BOOSTER_ID = 59633


/**
 * Returns whether the given booster is a "Veilguard" booster.
 */
context(EveData)
fun BoosterType.isVeilguard() =
    itemId == VEILGUARD_BOOSTER_ID
