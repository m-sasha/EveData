package eve.data


/**
 * The base class for all eve item types.
 * An instance of this class represents the item type, e.g. "Raven", "Large Shield Booster", "Hammerhead II".
 */
abstract class EveItemType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying data of this item type.
     */
    internal val typeData: EveItemTypeData,


    /**
     * The mutation, if this is a mutated type.
     */
    val mutation: Mutation? = null,


): HasAttributeValues {


    init {
        @Suppress("LeakingThis")
        if (mutation != null)
            mutation.itemType = this
    }


    /**
     * The item type's id.
     */
    val itemId: Int by typeData::itemId


    /**
     * The item type's group.
     */
    val group: TypeGroup by typeData::group


    /**
     * The item type's group ID.
     */
    val groupId: Int by group::id


    /**
     * The name of the item.
     */
    val name: String by typeData::name


    /**
     * The description of the item.
     */
    val description: String? by typeData::description


    /**
     * The item's volume, in m^3.
     */
    val volume: Double by typeData::volume


    /**
     * The item type's [MarketGroup]; if `null`, this item type does not appear in the market.
     */
    val marketGroup: MarketGroup? by typeData::marketGroup


    /**
     * The type's meta group id.
     */
    val metaGroupId: Int? by attributes.metaGroupId.orNull


    /**
     * The type's [Race], or `null` if it's not a racial item type.
     */
    val race: Race? by typeData::race


    /**
     * The id of the icon for this item type; `null` if none.
     */
    val iconId: Int? by typeData::iconId


    /**
     * Whether the icon image is a rendering of the item, rather than an actual icon.
     */
    val isIconRendering: Boolean by typeData::isIconRendering


    /**
     * The type's [Traits].
     */
    val traits: Traits? by typeData::traits


    /**
     * The item's attribute values.
     */
    override val attributeValues: AttributeValues by typeData::attributeValues


    /**
     * The references to the item's effects.
     */
    val effectReferences: EffectReferences by typeData::effectReferences


    /**
     * The skill requirements for this item.
     */
    val requiredSkills: List<SkillRequirement> by lazy {
        attributes.skillRequirements.mapNotNull { skillRequirementOrNull(it.skillId, it.level) }
    }


    /**
     * Returns a [SkillRequirement] for the given skill id and skill level attributes, or `null` if there is no skill
     * requirement with the given skill id attribute for this skill.
     */
    private fun skillRequirementOrNull(
        skillIdAttribute: Attribute<Int>,
        levelAttribute: Attribute<Int>
    ): SkillRequirement? {
        val skillId = attributeValueOrNull(skillIdAttribute) ?: return null
        val level = attributeValue(levelAttribute)
        return SkillRequirement(skillId, level)
    }


    /**
     * Returns whether the given skill is required for this item.
     */
    fun isSkillRequired(skillId: Int) = requiredSkills.any { it.skillId == skillId }


    /**
     * Returns the level of the given skill required for this item, or `null` if not required.
     */
    fun requiredSkillLevel(skillId: Int) = requiredSkills.find { it.skillId == skillId }?.level


    /**
     * The item's tech level, e.g. the `2` in "Tech 2".
     */
    val techLevel: Int? by attributes.techLevel.orNull


    /**
     * The item's meta-level.
     */
    val metaLevel: Int? by attributes.metaLevel.orNull


    /**
     * Returns whether this type has the given attribute.
     */
    fun hasAttribute(attribute: Attribute<*>): Boolean = attributeValues.has(attribute)


    /**
     * Returns the value of the given attribute of this type; throws an exception if it has no such attribute.
     */
    fun <T: Any> attributeValue(attribute: Attribute<T>): T = attributeValues.get(attribute)


    /**
     * Returns the value of the given attribute of this type, or `null` if it has no such attribute.
     */
    fun <T: Any> attributeValueOrNull(attribute: Attribute<T>): T? = attributeValues.getOrNull(attribute)


    /**
     * Returns whether this type has the given effect.
     */
    fun hasEffect(effect: Effect) = effectReferences.any { it == effect.id }


    override fun toString() =
        if (mutation == null)
            "${typeData.typeName}($name, id=$itemId)"
        else
            "${typeData.typeName}(${mutation.baseType.name} ($name), id=$itemId)"


}


/**
 * The data underlying an [EveItemType].
 */
data class EveItemTypeData(
    val itemId: Int,
    val group: TypeGroup,
    val name: String,
    val description: String?,
    val volume: Double,
    val marketGroup: MarketGroup?,
    val race: Race?,
    val iconId: Int?,
    val isIconRendering: Boolean,
    val traits: Traits? = null,
    val attributeValues: AttributeValues,
    val effectReferences: EffectReferences,
    val typeName: String
) {


    /**
     * Returns the [EveItemTypeData] for a mutated item.
     */
    context(EveData)
    fun mutated(mutation: Mutation): EveItemTypeData {
        val mutatedAttributeValues = AttributeValues(
            buildList {
                for (attrValue in attributeValues) {
                    val mutatedValue = mutation.mutatedAttributeValue(attrValue.attributeId)
                    add(AttributeValue(attrValue.attributeId, mutatedValue ?: attrValue.value))
                }
            }
        )

        // Set the metagroup to abyssal
        mutatedAttributeValues.set(attributes.metaGroupId, metaGroups.abyssal.id)

        return copy(
            name = mutation.name,
            attributeValues = mutatedAttributeValues
        )
    }


}


/**
 * The interface for [EveItemType]s that have a variation parent.
 */
sealed interface HasVariationParent {


    /**
     * The id of the variation parent, or if the id of the item itself, if it is the parent.
     */
    val variationParentTypeId: Int


}


/**
 * Returns the item's variation parent; `null` if it doesn't have one.
 */
context(EveData)
val EveItemType.variationParentOrNull: EveItemType?
    get() = if (this is HasVariationParent) {
        when (this) {
            is ModuleType -> moduleType(variationParentTypeId)
            is DroneType -> droneType(variationParentTypeId)
            is ImplantType -> implantTypes[variationParentTypeId]
            is BoosterType -> boosterType(variationParentTypeId)
            is EnvironmentType -> environmentType(variationParentTypeId)
        }
    } else null


/**
 * Encapsulates the properties of a mutation applied to an [EveItemType].
 */
class Mutation internal constructor(


    /**
     * The base type.
     */
    val baseType: EveItemType,


    /**
     * The mutating mutaplasmid.
     */
    val mutaplasmid: Mutaplasmid,


    /**
     * The name of the mutated type.
     */
    val name: String,


    /**
     * The context attrubutes.
     */
    attributes: Attributes,


    /**
     * The values of the mutated attributes, mapped by the attribute.
     *
     * Attributes which are mutated by the mutaplasmid but are not in this map will be set to value in the (mutaplasmid)
     * range that is nearest to the base value.
     *
     * Attributes in this map that are not mutated by the mutaplasmid will be ignored.
     */
    valueByAttribute: Map<Attribute<*>, Double>


) {


    /**
     * The mutated attributes, mapped by their id.
     */
    private val mutatedAttributeById: Map<Int, Attribute<*>> =
        mutaplasmid.attributeMutations.keys.associateWith { attrId -> attributes[attrId] }


    /**
     * The attribute's value in the base type, mapped by the attribute.
     */
    val baseValueByAttribute: Map<Attribute<*>, Double> = mutatedAttributeById.values.associateWith { attribute ->
        baseType.attributeValues.getDoubleValue(attribute.id)
    }


    // Validate the factors against the mutaplasmid
    init {
        for (attrMutation in mutaplasmid.attributeMutations.values) {
            val attribute = mutatedAttributeById[attrMutation.attributeId]
            val mutatedAttrValue = valueByAttribute[attribute] ?: continue  // Not mutated

            val baseValue = baseValueByAttribute[attribute]!!
            val attrValueRange = attrMutation.attributeValueRange(baseValue)
            if (mutatedAttrValue !in attrValueRange) {
                throw IllegalArgumentException(
                    "Factor for mutation of attribute $attribute is out of range " +
                            "($mutatedAttrValue, range: $attrValueRange)"
                )
            }
        }
    }


    /**
     * For each mutated attribute, the factor applied to it.
     */
    private val valueByAttribute: MutableMap<Attribute<*>, Double> =
        mutatedAttributeById.values.associateWithTo(mutableMapOf()) {
            valueByAttribute[it] ?: baseValueByAttribute[it]!!
        }


    /**
     * The item type this mutation is applied to.
     */
    internal var itemType: EveItemType? = null


    /**
     * Returns the mutated value of the attribute with the given id; `null` if the attribute is not mutated by this
     * mutation.
     */
    fun mutatedAttributeValue(attributeId: Int): Double? {
        val attribute = mutatedAttributeById[attributeId] ?: return null
        return valueByAttribute[attribute]
    }


    /**
     * Sets the mutated value of the attribute with the given id.
     */
    fun setMutatedAttributeValue(attributeId: Int, value: Double) {
        val attribute = mutatedAttributeById[attributeId]
            ?: throw IllegalArgumentException("Attribute $attributeId is not mutated by mutaplasmid $mutaplasmid")
        val attrMutation = mutaplasmid.attributeMutations[attributeId]!!
        val baseValue = baseValueByAttribute[attribute]!!
        val valueRange = attrMutation.attributeValueRange(baseValue)
        if (value !in valueRange)
            throw IllegalArgumentException(
                "Mutated value of attribute $attribute is out of range ($value, range: ${valueRange})"
            )

        valueByAttribute[attribute] = value
        itemType?.typeData?.attributeValues?.setDoubleValue(attributeId, value)
    }


    /**
     * Returns the mutated attributes and their values.
     */
    fun mutatedAttributesAndValues(): List<Pair<Attribute<*>, Double>> {
        return valueByAttribute.entries.map { (attrId, value) -> Pair(attrId, value) }
    }


}


/**
 * Associates the [EveItemType]'s by their item ids.
 */
fun <T: EveItemType> Iterable<T>.associateByItemId(): Map<Int, T> = this.associateBy { it.itemId }


/**
 * Associates the [EveItemType]'s by their names.
 */
fun <T: EveItemType> Iterable<T>.associateByName(): Map<String, T> = this.associateBy { it.name }
