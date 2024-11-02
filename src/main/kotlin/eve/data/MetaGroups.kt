package eve.data


/**
 * A meta group, e.g. "Tech I", "Storyline" etc.
 */
class MetaGroup(


    /**
     * The id of the meta group.
     */
    val id: Int,


    /**
     * The name of the meta group.
     */
    val name: String


){


    override fun toString(): String {
        return "MetaGroup($name, id=$id)"
    }


}


/**
 * Groups all meta groups, and provides some of them via explicit properties.
 */
class MetaGroups internal constructor(
    metaGroups: Iterable<MetaGroup>
): Iterable<MetaGroup> by metaGroups{



    /**
     * Meta groups mapped by id.
     */
    private val byId: Map<Int, MetaGroup> = metaGroups.associateBy{ it.id }


    /**
     * Returns the meta group with the given id, assuming it exists.
     */
    operator fun get(id: Int) = byId[id] ?: throw BadEveDataException("No meta group with id $id found")


    /**
     * The "Tech I" meta group.
     */
    val tech1: MetaGroup = get(1)


    /**
     * The "Tech II" meta group.
     */
    val tech2: MetaGroup = get(2)


    /**
     * The "Storyline" meta group.
     */
    val storyline: MetaGroup = get(3)


    /**
     * The "Faction" meta group.
     */
    val faction: MetaGroup = get(4)


    /**
     * The "Officer" meta group.
     */
    val officer: MetaGroup = get(5)


    /**
     * The "Deadspace" meta group.
     */
    val deadspace: MetaGroup = get(6)


    /**
     * The "Tech III" meta group.
     */
    @Suppress("unused")
    val tech3: MetaGroup = get(14)


    /**
     * The "Abyssal" meta group.
     */
    val abyssal: MetaGroup = get(15)


}