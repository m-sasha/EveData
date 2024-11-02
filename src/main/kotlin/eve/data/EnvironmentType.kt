package eve.data

import androidx.compose.runtime.Immutable


/**
 * An environment effect, e.g. wormwhole, abyssal weather etc.
 */
@Immutable
class EnvironmentType internal constructor(


    /**
     * The context [Attributes].
     */
    val attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


    /**
     * The id of the [EnvironmentType] that is the parent of the variation group of this environment type.
     * If this booster is the parent itself, then this is its id.
     */
    override val variationParentTypeId: Int,


): EveItemType(
    attributes = attributes,
    typeData = typeData
), HasVariationParent