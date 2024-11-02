package eve.data.utils

import androidx.compose.runtime.Immutable


/**
 * An efficient, immutable mapping from each value of an enum type to some other type.
 */
@Immutable
class ValueByEnum<K: Enum<K>, out V>(valueByOrdinal: Array<V>){


    /**
     * The values, mapped by the enum ordinals.
     */
    private val valueByOrdinal = valueByOrdinal.copyOf()


    /**
     * The values, in the order of their enum keys.
     */
    val values: List<V> by lazy { valueByOrdinal.toList() }


    /**
     * Returns the value corresponding to the given enum value.
     */
    operator fun get(key: K): V = valueByOrdinal[key.ordinal]


}


/**
 * Returns a [ValueByEnum] created by invoking the given function for each enum value, sequentially, in the order of
 * definition of the enum values.
 */
inline fun <reified K: Enum<K>, reified V> valueByEnum(value: (K) -> V): ValueByEnum<K, V>{
    val enumValues = enumValues<K>()
    val valueByOrdinal = Array(enumValues.size){ value(enumValues[it]) }
    return ValueByEnum(valueByOrdinal)
}


/**
 * Maps the values using the given transform function that only takes the value.
 */
inline fun <reified K: Enum<K>, reified V, reified R>
        ValueByEnum<in K, V>.mapValues(transform: (V) -> R): ValueByEnum<K, R> {
    return valueByEnum { key -> transform(this[key]) }
}


/**
 * Returns the [ValueByEnum] as a [Map].
 */
@Suppress("unused")
inline fun <reified K: Enum<K>, V> ValueByEnum<K, V>.asMap(): Map<K, V>{
    return enumValues<K>().associateWith { get(it) }
}


/**
 * Invokes the given function on each key and value.
 */
inline fun <reified K: Enum<K>, V> ValueByEnum<K, V>.forEach(block: (K, V) -> Unit){
    for (key in enumValues<K>())
        block(key, this[key])
}