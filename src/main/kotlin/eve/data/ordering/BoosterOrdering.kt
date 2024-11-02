package eve.data.ordering

import eve.data.BoosterType


/**
 * The order of strengths of classic boosters.
 */
private val CLASSIC_BOOSTER_STRENGTH_ORDER = listOf("Nugoehuvi Synth", "Synth", "Standard", "Improved", "Strong")


/**
 * A [Comparator] of classic boosters by their "strength":
 * 1. Nugoehuvi Synth
 * 2. Synth
 * 3. Standard
 * 4. Improved
 * 5. Strong
 */
val CLASSIC_BOOSTER_COMPARATOR: Comparator<BoosterType> = compareBy{ boosterType ->
    val name = boosterType.name
    CLASSIC_BOOSTER_STRENGTH_ORDER.indexOfFirst { name.startsWith(it) }
}


/**
 * The order of strengths of Agency boosters.
 */
private val AGENCY_BOOSTER_STRENGTH_ORDER = listOf("I", "II", "III", "IV")


/**
 * A [Comparator] of Agency boosters by their "strength":
 * 1. +3% "Dose I" boosters.
 * 2. +5% "Dose II" boosters.
 * 3. +7% "Dose III" boosters.
 * 4. +9% "Dose IV" boosters .
 */
val AGENCY_BOOSTER_COMPARATOR: Comparator<BoosterType> = compareBy { boosterType ->
    val name = boosterType.name
    if (!name.startsWith("Agency") || !name.contains("Dose"))  // Make sure it's an Agency booster
        return@compareBy -1

    val strength = name.substringAfterLast(" ", missingDelimiterValue = "")
    AGENCY_BOOSTER_STRENGTH_ORDER.indexOf(strength)
}
