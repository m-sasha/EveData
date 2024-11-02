/**
 * Methods for identifying ships.
 */


package eve.data.typeid

import eve.data.ShipType


/**
 * Returns whether the given ship is a Tengu.
 */
fun ShipType.isTengu() = name == "Tengu"


/**
 * Returns whether the given ship is a Loki.
 */
fun ShipType.isLoki() = name == "Loki"


/**
 * Returns whether the given ship is a Legion.
 */
fun ShipType.isLegion() = name == "Legion"


/**
 * Returns whether the given ship is a Proteus.
 */
fun ShipType.isProteus() = name == "Proteus"


/**
 * Returns whether the given ship is a Jackdaw.
 */
fun ShipType.isJackdaw() = name == "Jackdaw"


/**
 * Returns whether the given ship is a Svipul.
 */
fun ShipType.isSvipul() = name == "Svipul"


/**
 * Returns whether the given ship is a Confessor.
 */
fun ShipType.isConfessor() = name == "Confessor"


/**
 * Returns whether the given ship is a Hecate.
 */
fun ShipType.isHecate() = name == "Hecate"

