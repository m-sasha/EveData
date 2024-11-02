package eve.data

import androidx.compose.runtime.Immutable

/**
 * The type of skill, e.g. "Large Missile Launcher Operation".
 */
@Immutable
class SkillType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData


): EveItemType(
    attributes = attributes,
    typeData = typeData
) {


    /**
     * The skill's primary learning attribute, e.g. [Attributes.willpower], [Attributes.perception] etc.
     */
    @Suppress("unused")
    val primaryAttribute: Attribute<Int> by attributes.primaryAttribute


    /**
     * The skill's secondary learning attribute, e.g. [Attributes.charisma], [Attributes.memory] etc.
     */
    @Suppress("unused")
    val secondaryAttribute: Attribute<Int> by attributes.secondaryAttribute


    /**
     * The skill's training time multiplier.
     */
    @Suppress("unused")
    val trainingTimeMultiplier: Int by attributes.skillTimeConstant


}
