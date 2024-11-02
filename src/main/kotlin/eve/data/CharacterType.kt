package eve.data

import androidx.compose.runtime.Immutable


/**
 * An Eve character type.
 */
@Immutable
class CharacterType internal constructor(


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
)