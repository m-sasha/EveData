package eve.data


/**
 * An EVE race (e.g. Minmatar).
 */
class Race(


    /**
     * The id of the race.
     */
    val id: Int,


    /**
     * The name of the race.
     */
    val name: String


) {


    override fun toString() = "Race($name, id=$id)"


}


/**
 * Returns the [Race] corresponding to the given sensor type.
 */
context(EveData)
val SensorType.race: Race
    get() = when (this) {
        SensorType.RADAR -> races.amarr
        SensorType.MAGNETOMETRIC -> races.gallente
        SensorType.GRAVIMETRIC -> races.caldari
        SensorType.LADAR -> races.minmatar
    }


/**
 * Returns the racial damage type of the given race.
 */
context(EveData)
val Race.damageType: DamageType?
    get() = when (this) {
        races.caldari -> DamageType.KINETIC
        races.minmatar -> DamageType.EXPLOSIVE
        races.amarr -> DamageType.EM
        races.gallente -> DamageType.THERMAL
        else -> null
    }


/**
 * Groups all races, and provides some of them via explicit properties.
 */
class Races(races: Collection<Race>): Collection<Race> by races {


    /**
     * [Race]s mapped by id.
     */
    private val byId: Map<Int, Race> = races.associateBy{ it.id }


    /**
     * [Race]s mapped by name.
     */
    private val byName: Map<String, Race> = races.associateBy{ it.name }


    /**
     * Returns the [Race] with the given id, assuming it exists.
     */
    operator fun get(id: Int) = byId[id] ?: throw BadEveDataException("No race with id $id found")


    /**
     * Returns the [Race] with the given name, assuming it exists.
     */
    operator fun get(name: String) = byName[name] ?: throw BadEveDataException("No '$name' race found")


    val caldari = get("Caldari")
    val minmatar = get("Minmatar")
    val amarr = get("Amarr")
    val gallente = get("Gallente")
    val triglavian = get("Triglavian")
    val ore = get("ORE")
    val jove = get("Jove")
    val pirate = get("Pirate")


}