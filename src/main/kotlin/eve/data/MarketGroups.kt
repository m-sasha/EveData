package eve.data

/**
 * A market group, e.g. "Standard Frigates" or "Signal Amplifiers" etc.
 */
class MarketGroup(


    /**
     * The id of the market group.
     */
    val id: Int,


    /**
     * The name of the market group.
     */
    val name: String,


    /**
     * The id of the icon for this market group; `null` if none.
     */
    val iconId: Int?,


    /**
     * The id of the parent market group; `null` if this is a tol-level market group.
     */
    val parentGroupId: Int?


) {


    override fun toString(): String {
        return "MarketGroup($name, id=$id, parentGroupId=$parentGroupId)"
    }


}


/**
 * Groups all market groups, and provides some of them via explicit properties.
 */
class MarketGroups internal constructor(
    marketGroups: Collection<MarketGroup>
): Collection<MarketGroup> by marketGroups {


    /**
     * Market groups mapped by id.
     */
    private val byId: Map<Int, MarketGroup> = marketGroups.associateBy { it.id }


    /**
     * Returns the market group with the given id, assuming it exists.
     */
    operator fun get(id: Int) = byId[id] ?: throw BadEveDataException("No market group with id $id found")


    /**
     * Maps each market group id to the list of its children, sorted alphabetically by name.
     *
     * The `null` market group it is mapped to the top-level market groups.
     */
    private val childrenByParentId: Map<Int?, List<MarketGroup>> = marketGroups
        .groupBy { it.parentGroupId }
        .mapValues { (_, children) -> children.sortedBy { it.name } }


    /**
     * Returns the children of the given market group; `null` if none.
     */
    fun childrenOf(marketGroup: MarketGroup): List<MarketGroup>? = childrenByParentId[marketGroup.id]


    /**
     * Returns whether this market group is an ancestor of [marketGroup].
     */
    fun MarketGroup.isAncestorOf(marketGroup: MarketGroup): Boolean {
        var group = marketGroup
        while (true) {
            val parentGroupId = group.parentGroupId ?: break
            if (parentGroupId == this.id)
                return true
            group = get(parentGroupId)
        }

        return false
    }


    /**
     * Returns whether the given item type is in the given market group (i.e. either [marketGroup] is the item's market
     * group or its ancestor).
     */
    fun EveItemType.isInGroup(marketGroup: MarketGroup): Boolean {
        val itemGroup = this.marketGroup ?: return false
        return (marketGroup == itemGroup) || marketGroup.isAncestorOf(itemGroup)
    }


    val ships = get(4)
    val skills = get(150)
    val ammoAndCharges = get(11)
    val deployableStructures = get(404)
    val drones = get(157)
    val filaments = get(2456)
    val implants = get(27)
    val boosters = get(977)
    val rigs = get(1111)
    val shipEquipment = get(9)
    val subsystems = get(1112)
    val attributeEnhancers = get(532)
    val skillHardwiring = get(531)
    val droneUpgrades = get(938)
    val afterburners = get(542)
    val microwarpdrives = get(131)
    val damageControls = get(615)
    val propulsion = get(52)
    val warpDisruptors = get(1935)
    val warpScramblers = get(1936)
    val weaponDisruptors = get(680)
    val armorPlates = get(133)
    val remoteArmoreRepairers = get(537)
    val mediumMutadaptiveRemoteArmorRepairers = get(2529)
    val armorHardeners = get(535)
    val armorResistanceCoatings = get(540)
    val energizedArmorResistanceMembranes = get(541)
    val shieldHardeners = get(553)
    val shieldResistanceAmplifiers = get(550)
    val armorRepairers = get(134)
    val hullRepaiers = get(538)
    val remoteArmorRepairers = get(537)
    val remoteHullRepairers = get(1018)
    val capacitorBatteries = get(664)
    val capacitorBoosters = get(668)
    val energyNeutralizers = get(661)
    val energyNosferatu = get(662)
    val remoteCapacitorTransmitters = get(663)
    val remoteShieldBoosters = get(128)
    val shieldBoosters = get(552)
    val shieldExtenders = get(551)
    val smartBombs = get(141)
    val condenserPacks = get(2728)
    val exoticPlasmaCharges = get(2462)
    val frequencyCrystals = get(101)
    val hybridCharges = get(100)
    val projectileAmmo = get(99)
    val shieldRigs = get(965)
    val armorRigs = get(956)
    val scripts = get(1094)

}