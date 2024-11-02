package eve.data

import androidx.compose.runtime.Immutable


/**
 * A synthetic type for a single item through which the command burst effects are applied.
 */
@Immutable
class WarfareBuffsType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData


) : EveItemType(
    attributes = attributes,
    typeData = typeData
)