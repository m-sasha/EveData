package eve.data


/**
 * An item type category, e.g. "Modules".
 * This is the most generic (top-level) grouping of [EveItemType]s.
 */
class TypeCategory(


    /**
     * The id of the category.
     */
    val id: Int,


    /**
     * The name of the category.
     */
    val name: String


)


/**
 * Groups all item type categories and provides some of them via explicit properties.
 */
class TypeCategories internal constructor(
    categories: Collection<TypeCategory>
): Collection<TypeCategory> by categories{


    /**
     * Categories mapped by id.
     */
    private val byId: Map<Int, TypeCategory> = categories.associateBy { it.id }


    /**
     * Categories mapped by name.
     */
    private val byName: Map<String, TypeCategory> = categories.associateBy { it.name }


    /**
     * Returns the [TypeCategory] with the given id, assuming it exists.
     */
    operator fun get(id: Int) = byId[id] ?: throw BadEveDataException("No category with id $id found")


    /**
     * Returns the [TypeCategory] with the given name, assuming it exists.
     */
    operator fun get(name: String) = byName[name] ?: throw BadEveDataException("No category '$name' found")


    val deployable = get("Deployable")


}
