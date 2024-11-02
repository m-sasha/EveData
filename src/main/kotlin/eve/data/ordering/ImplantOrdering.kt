package eve.data.ordering

import eve.data.ImplantType


/**
 * A [Comparator] of implants by the implant's number, e.g. the "604" from "Zainou `Gypsy` CPU Management EE-604".
 * Doesn't order implants that don't have a number
 */
val IMPLANT_NUMBERS_COMPARATOR: Comparator<ImplantType> = compareBy{ implantType ->
    val name = implantType.name
    if (name.last().isDigit())
        name.substring(name.indexOfLast { !it.isDigit() } + 1).toIntOrNull()
    else
        null
}


/**
 * The order of learning implants.
 */
private val LEARNING_IMPLANT_NAMES_ORDER = listOf(
    // The +1 implant starts with "Limited" but so does the +2 ("- Beta").
    "- Beta",
    "- Basic",
    "- Standard",
    "- Improved"
)


/**
 * A [Comparator] of learning implants in this order:
 * 1. +1 implants (e.g. "Limited Cybernetic Subprocessor")
 * 2. +2 implants (e.g. "Limited Cybernetic Subprocessor - Beta")
 * 3. +3 implants (e.g. "Cybernetic Subprocessor - Basic")
 * 4. +4 implants (e.g. "Cybernetic Subprocessor - Standard")
 * 5. +5 implants (e.g. "Cybernetic Subprocessor - Improved")
 * Doesn't order other implants that don't have a number
 */
val LEARNING_IMPLANTS_COMPARATOR: Comparator<ImplantType> = compareBy { implantType ->
    val name = implantType.name
    LEARNING_IMPLANT_NAMES_ORDER.indexOfFirst { name.endsWith(it) }.let {
        if (it >= 0)
            it
        else if (name.startsWith("Limited") && !name.endsWith("- Beta"))
            0
        else
            null
    }
}


/**
 * The order of pirate implants of the same slot.
 */
private val SAME_SLOT_PIRATE_IMPLANT_NAMES_ORDER = listOf("Low-grade", "Mid-grade", "High-grade")


/**
 * A [Comparator] of pirate implants of the same slot: low-, mid- and high-grade.
 *
 * Doesn't order other implants.
 */
val SAME_SLOT_PIRATE_IMPLANTS_COMPARATOR: Comparator<ImplantType> = compareBy { implantType ->
    val name = implantType.name
    SAME_SLOT_PIRATE_IMPLANT_NAMES_ORDER.indexOfFirst { name.startsWith(it) }.let {
        if (it >= 0) it else null
    }
}


/**
 * The order of pirate implants of the same type.
 */
private val SAME_TYPE_PIRATE_IMPLANTS_NAME_ORDER = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Omega")


/**
 * A [Comparator] of pirate implants of the same type:
 * 1. Alpha
 * 2. Beta
 * 3. Gamma
 * 4. Delta
 * 5. Epsilon
 * 6. Omega
 *
 * Doesn't order other implants.
 */
val SAME_TYPE_PIRATE_IMPLANTS_COMPARATOR: Comparator<ImplantType> = compareBy{ implantType ->
    val name = implantType.name
    SAME_TYPE_PIRATE_IMPLANTS_NAME_ORDER.indexOfFirst { name.endsWith(it) }.let {
        if (it >= 0) it else null
    }
}
