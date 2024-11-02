package eve.data

import androidx.compose.runtime.Immutable


/**
 * Encapsulates a single mutaplasmid.
 */
@Immutable
data class Mutaplasmid(


    /**
     * The id of the mutaplasmid.
     */
    val id: Int,


    /**
     * The name of the mutaplasmid.
     */
    val name: String,


    /**
     * The ids of types this mutaplasmid can mutate.
     */
    val targetTypeIds: List<Int>,


    /**
     * The mutations performed by this mutaplasmid, by the id of the attribute.
     */
    val attributeMutations: Map<Int, AttributeMutation>


): Comparable<Mutaplasmid> {


    /**
     * The type of this mutaplasmid.
     */
    val type = MutaplasmidNameSubstringToType.firstOrNull { (substring, _) -> substring in name }?.second
        ?: error("Unknown mutaplasmid name: $name")


    /**
     * The types of mutaplasmids.
     */
    enum class Type {
        Decayed,
        Gravid,
        Unstable,
        Radical,
        ExigentNavigation,
        ExigentFirepower,
        ExigentDurability,
        ExigentProjection,
        ExigentPrecision
    }


    override fun compareTo(other: Mutaplasmid): Int {
        return type.compareTo(other.type)
    }


    override fun toString(): String {
        return "Mutaplasmid($name, id=$id)"
    }


}


/**
 * The mutaplasmid prefixes.
 */
private val MutaplasmidNameSubstringToType = listOf(
    "Decayed" to Mutaplasmid.Type.Decayed,
    "Gravid" to Mutaplasmid.Type.Gravid,
    "Unstable" to Mutaplasmid.Type.Unstable,
    "Radical" to Mutaplasmid.Type.Radical,
    "Navigation" to Mutaplasmid.Type.ExigentNavigation,
    "Firepower" to Mutaplasmid.Type.ExigentFirepower,
    "Durability" to Mutaplasmid.Type.ExigentDurability,
    "Projection" to Mutaplasmid.Type.ExigentProjection,
    "Precision" to Mutaplasmid.Type.ExigentPrecision,
)


/**
 * Encapsulates how the mutaplasmid can modify a single attribute.
 */
data class AttributeMutation(


    /**
     * The id of the modified attribute.
     */
    val attributeId: Int,


    /**
     * The range of values for the factor by which the attribute is multiplied.
     */
    val range: ClosedFloatingPointRange<Double>,


    /**
     * Whether a higher value in the mutated attribute is "better" in the context of this mutation.
     * This property, if not `null`, overrides the attribute's own [Attribute.highIsGood] property.
     */
    val highIsGood: Boolean?


) {


    /**
     * Returns the range of the mutated attribute value, given its base value.
     */
    fun attributeValueRange(baseValue: Double): ClosedFloatingPointRange<Double> {
        return if (baseValue < 0)
            range.endInclusive * baseValue .. range.start * baseValue
        else
            range.start * baseValue .. range.endInclusive * baseValue
    }


    /**
     * Returns whether a higher value is good in this mutation.
     */
    fun highIsGood(attribute: Attribute<*>): Boolean {
        if (attribute.id != attributeId)
            throw IllegalArgumentException("Wrong attribute given")
        return this.highIsGood ?: attribute.highIsGood ?:
            error("Unknown whether attribute $attribute high is good")
    }


}

