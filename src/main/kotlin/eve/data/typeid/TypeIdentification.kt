/**
 * Methods for identifying eve item types.
 */

package eve.data.typeid

import eve.data.EveData
import eve.data.EveItemType


/**
 * The names of empire navy factions.
 */
private val EMPIRE_NAVY_FACTION_NAMES = listOf(
    "Federation Navy",
    "Caldari Navy",
    "Imperial Navy",
    "Republic Fleet",
    "Khanid Navy",
)


/**
 * Returns whether the item is an empire navy faction item.
 */
context(EveData)
val EveItemType.isEmpireNavyFactionItem: Boolean
    get() {
        // Unfortunately, the SDE doesn't differentiate between the different factions,
        // so we have no choice but to use names.
        return (metaGroupId == metaGroups.faction.id) &&
                EMPIRE_NAVY_FACTION_NAMES.any { name.contains(it) }
    }


/**
 * The names of (non-elite) pirate factions.
 */
private val LOW_TIER_PIRATE_FACTION_NAMES = listOf(
    "Sansha",
    "Blood",
    "Serpentis",
    "Guristas",
    "Arch Angel",
    "Legion"  // Auto-targeting missiles
)


/**
 * Returns whether the item is a low-tier (non-elite) pirate faction item.
 */
context(EveData)
val EveItemType.isLowTierPirateFactionItem: Boolean
    get() {
        // Unfortunately, the SDE doesn't differentiate between the different factions,
        // so we have no choice but to use names.
        return (metaGroupId == metaGroups.faction.id) &&
                LOW_TIER_PIRATE_FACTION_NAMES.any { name.contains(it) } &&
                ELITE_PIRATE_FACTION_NAMES.none { name.contains(it) }
    }


/**
 * The names of elite pirate factions.
 * Note that what we actually want to identify here are very rare and expensive items, so we include sentient drones
 * and CONCORD here.
 */
private val ELITE_PIRATE_FACTION_NAMES = listOf(
    "True Sansha",
    "Dark Blood",
    "Shadow Serpentis",
    "Dread Guristas",
    "Guardian",
    "Domination",
    "Sentient",
    "CONCORD"
)


/**
 * whether the item is an elite pirate faction item (or sentient drone, or CONCORD).
 */
context(EveData)
val EveItemType.isElitePirateFactionItem: Boolean
    get() {
        // Unfortunately, the SDE doesn't differentiate between the different factions,
        // so we have no choice but to use names.
        return (metaGroupId == metaGroups.faction.id) &&
                ELITE_PIRATE_FACTION_NAMES.any { name.contains(it) }
    }


/**
 * Whether the given item is a Tech 1 item.
 */
context(EveData)
val EveItemType.isTech1Item: Boolean
    get() = metaGroupId == metaGroups.tech1.id


/**
 * Whether the given item is a Tech 2 item.
 */
context(EveData)
val EveItemType.isTech2Item: Boolean
    get() = metaGroupId == metaGroups.tech2.id
