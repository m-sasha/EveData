package eve.data

import eve.data.Effect.Category.ALWAYS
import eve.data.Effect.Category.PROJECTED

/**
 * An effect is a named grouping of modifiers and some shared metadata.
 * Ship bonuses, skills, for example, are implemented as effects.
 */
class Effect internal constructor(
    val id: Int,
    val name: String,
    val category: Category,
    private val flags: EffectFlags,
    val condition: Condition?,
    val modifiers: List<AttributeModifier>
) {


    /**
     * Returns whether this is a projected effect.
     */
    val isProjected: Boolean
        get() = category == PROJECTED


    /**
     * Returns whether this effect is in general offensive (bad for the target).
     */
    val isOffensive: Boolean
        get() = flags.isOffensive


    /**
     * Whether the effect is in general assistive (good for the target).
     */
    val isAssistive: Boolean
        get() = flags.isAssistive


    override fun toString() = "Effect($name, id=$id, ${modifiers.size} modifiers)"


    /**
     * The effect category.
     * For modules this specifies when (and on whom) the effect is active.
     * All non-module effects seem to all have the [ALWAYS] category.
     */
    enum class Category {


        /**
         * The effect is always applied.
         */
        ALWAYS,


        /**
         * The effect is only applied when the module is active.
         */
        ACTIVE,


        /**
         * An effect projected on other entities (typically ships).
         */
        PROJECTED,


        /**
         * An effect applied when the module is merely online.
         */
        ONLINE,


        /**
         * An effect applied when the module is overheated.
         */
        OVERLOADED,


        /**
         * All other categories (which we don't care about) are mapped to this.
         */
        UNHANDLED


    }


    /**
     * Specifies an extra condition for the effect to be active.
     */
    data class Condition(


        /**
         * The id of the condition attribute.
         */
        val attributeId: Int,


        /**
         * The value the condition attribute must have in order for the condition to hold.
         */
        val attributeValue: Int


    )



}


/**
 * Groups all dogma effects.
 */
class Effects internal constructor(effects: Iterable<Effect>) : Iterable<Effect> by effects{


    /**
     * Effects mapped by [Effect.name].
     */
    private val byName = effects.associateBy{ it.name }


    /**
     * Effects mapped by [Effect.id].
     */
    private val byId = effects.associateBy{ it.id }


    /**
     * Returns the effect with the given id.
     * The effect must exist.
     */
    operator fun get(id: Int) = byId[id] ?: throw BadEveDataException("No effect with id $id found")


    /**
     * Returns the effect with the given name.
     * The effect must exist.
     */
    operator fun get(name: String) = byName[name] ?: throw BadEveDataException("No effect with name '$name' found")


    val adaptiveArmorHardener = get("adaptiveArmorHardener")  // Reactive Armor Hardener
    val moduleBonusAssaultDamageControl = get("moduleBonusAssaultDamageControl")  // Assault Damage Control
    val emergencyHullEnergizer = get("emergencyHullEnergizer")  // Capital Emergency Hull Energizer
    val powerBooster = get("powerBooster")  // Capacitor Booster
    val trackingDisruptor = get("shipModuleTrackingDisruptor")  // Tracking Disruptor
    val guidanceDisruptor = get("shipModuleGuidanceDisruptor")  // Guidance Disruptor


}


/**
 * A list of references to effects, by their id.
 */
@JvmInline
value class EffectReferences(private val effectIds: IntArray): Iterable<Int>{

    override fun iterator() = effectIds.iterator()

    companion object{

        /**
         * An empty [EffectReferences] instance.
         */
        val EMPTY = EffectReferences(IntArray(0))

    }

}


/**
 * Converts a collection of effect ids to an [EffectReferences] instance.
 */
fun Collection<Int>.toEffectReferences() = EffectReferences(toIntArray())


/**
 * A modifier of an attribute, based on some other attribute.
 * A modifier is always part of some [Effect].
 */
class AttributeModifier(
    val modifiedAttributeId: Int?,
    val modifyingAttributeId: Int?,
    val attenuatingAttributeId: Int?,  // For implementing ewar resistances
    val affectedItemType: AffectedItemType,
    val affectedItemFilter: AffectedItemFilter,
    val operation: Operation,
    val groupId: Int?,
    val skillTypeId: Int?,
){


    /**
     * Returns the [Attribute] specified by [modifiedAttributeId].
     */
    fun modifiedAttribute(eveData: EveData) = modifiedAttributeId?.let { eveData.attributes[it] }


    /**
     * Returns the [Attribute] specified by [modifyingAttributeId].
     */
    fun modifyingAttribute(eveData: EveData) = modifyingAttributeId?.let { eveData.attributes[it] }


    /**
     * Returns the [Attribute] specified by [attenuatingAttributeId].
     */
    fun attenuatingAttribute(eveData: EveData) = attenuatingAttributeId?.let { eveData.attributes[it] }


    override fun toString() = buildString {
        append("AttributeModifier(")
        append("modifiedAttrId=$modifiedAttributeId, ")
        append("modifyingAttrId=$modifyingAttributeId, ")
        if (attenuatingAttributeId != null)
            append("attenuatingAttributeId=$attenuatingAttributeId, ")
        append("affectedItemType=$affectedItemType, ")
        append("affectedItemFilter=$affectedItemFilter, ")
        append("op=$operation, ")
        append("groupId=$groupId, ")
        append("skillTypeId=$skillTypeId")
        append(")")
    }


    /**
     * The modifier's operation, i.e. how the modified value is calculated from the modifying attribute.
     * The order of the operations in the enum determines the order in which they are applied (hence the need for pre-
     * and post- multiply operations).
     */
    enum class Operation{


        /**
         * The modified value is multiplied by the modifying value.
         */
        PRE_MULTIPLY,


        /**
         * The modifying value is added to the modified value.
         */
        ADD,


        /**
         * The modifying value is subtracted from the modified value.
         */
        SUBTRACT,


        /**
         * A percent equal to the modifying value is added to the modified value.
         */
        ADD_PERCENT,


        /**
         * The modified value is multiplied by the modifying value.
         */
        POST_MULTIPLY,


        /**
         * The modified value is multiplied by the modifying value interpreted as a percentage
         * (e.g. by 1.35 if the value is 135)
         */
        MULTIPLY_PERCENT,


        /**
         * The modified value is divided by the modifying value.
         */
        POST_DIVIDE,


        /**
         * The modified value is replaced by the modifying value.
         */
        SET,


        /**
         * The modified value is replaced by the modifying value only if it is greater in its absolute value.
         */
        SET_MAX_ABS,


        /**
         * The modified value is coerced to be at least the modifying value.
         */
        COERCE_AT_LEAST,


        /**
         * The modified value is coerced to be at least the modifying value.
         */
        COERCE_AT_MOST,


        /**
         * All other operations (which we don't care about) are mapped to this.
         */
        UNHANDLED;


    }


    /**
     * The item type whose attribute is being modified.
     */
    enum class AffectedItemType {


        /**
         * The affected item is the affecting item itself.
         */
        SELF,


        /**
         * The affected item is the ship.
         */
        SHIP,


        /**
         * The affected item is the character.
         */
        CHARACTER,


        /**
         * The affected items are things fitted to the ship (modules and rigs, but not drones, probes).
         */
        MODULES,


        /**
         * The affected items are things "fitted" to the character (implants, boosters).
         */
        IMPLANTS_BOOSTERS,


        /**
         * The affected items are charges and drones.
         */
        LAUNCHABLES,


        /**
         * The affected item is the module into which the charge/script is loaded.
         */
        LAUNCHER,


        /**
         * The affected item is the synthetic WarfareBuffs item.
         */
        WARFARE_BUFFS,


        /**
         * No affected items, either because we don't care about this effect, or we're just not implementing it yet.
         */
        NONE


    }


    /**
     * An additional filter on the affected items.
     */
    enum class AffectedItemFilter{


        /**
         * No filter - all the items are affected
         */
        ALL,


        /**
         * Item must require the [skillTypeId] of the modifier.
         */
        MATCH_REQUIRED_SKILL,


        /**
         * Item's groupId must match the modifier's [groupId]
         */
        MATCH_GROUP


    }


}
