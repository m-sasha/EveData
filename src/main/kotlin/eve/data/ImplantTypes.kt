package eve.data


/**
 * Groups implant-related information in [EveData].
 */
class ImplantTypes(implantTypes: List<ImplantType>): Iterable<ImplantType> by implantTypes {


    /**
     * Maps implant types by their item id.
     */
    private val byId = implantTypes.associateByItemId()


    /**
     * Returns the implant type with the given id.
     */
    operator fun get(id: Int): ImplantType = byId[id]
        ?: throw IllegalArgumentException("No implant type with id $id is known")


    /**
     * Returns the implant type with the given id or `null` if no such implant type exists.
     */
    fun getOrNull(id: Int): ImplantType? = byId[id]


    /**
     * Maps implant types by their name.
     */
    private val byName by lazy { implantTypes.associateByName() }


    /**
     * Returns the implant type with the given name or `null` if no such implant type exists.
     */
    fun getOrNull(name: String): ImplantType? = byName[name]


    /**
     * Maps implant variation groups by their [ImplantType.variationParentTypeId].
     */
    private val implantTypesByVariationParentTypeId = implantTypes.groupBy { it.variationParentTypeId }


    /**
     * Returns the implant types with the given variation parent type id.
     */
    fun byVariationParentTypeId(id: Int): Collection<ImplantType> {
        return implantTypesByVariationParentTypeId[id]
            ?: throw IllegalArgumentException("No implant types with variation parent type id $id are known")
    }


    /**
     * The number of implant slots.
     */
    val slotCount = implantTypes.maxOf { it.slotIndex } + 1


    /**
     * The pirate implant sets.
     */
    val pirateSets: List<PirateImplantSet> = run {
        val bySetName = mutableMapOf<String, MutableMap<ImplantGrade, MutableList<ImplantType>>>()
        for (implantType in implantTypes) {
            val parts = implantType.name.split(' ')
            if (parts.size != 3)
                continue

            val gradeName = parts[0]
            val grade = when {
                gradeName.startsWith(ImplantGrade.LOW.displayName) -> ImplantGrade.LOW
                gradeName.startsWith(ImplantGrade.MID.displayName) -> ImplantGrade.MID
                gradeName.startsWith(ImplantGrade.HIGH.displayName) -> ImplantGrade.HIGH
                else -> continue
            }

            val setName = parts[1]

            val byGrade = bySetName.getOrPut(setName, ::mutableMapOf)
            val implants = byGrade.getOrPut(grade) { ArrayList(6) }
            implants.add(implantType)
        }

        buildList {
            for ((setName, byGrade) in bySetName) {
                for ((setGrade, types) in byGrade) {
                    add(PirateImplantSet(setName, setGrade, types))
                }
            }
        }
    }


}


/**
 * The pirate implant grades.
 */
enum class ImplantGrade(val displayName: String) {
    LOW("Low-grade"),
    MID("Mid-grade"),
    HIGH("High-grade")
}


/**
 * A pirate implant set.
 */
class PirateImplantSet(


    /**
     * The name of the set, excluding the grade; e.g. "Snake"
     */
    val name: String,


    /**
     * The grade of the set.
     */
    val grade: ImplantGrade,


    /**
     * The implants in the set.
     */
    val implantTypes: List<ImplantType>


) {


    /**
     * The name of the set as it should be displayed; e.g. "High-grade Snake Set".
     */
    val displayName = "${grade.displayName} $name Set"


    override fun toString() = displayName


}
