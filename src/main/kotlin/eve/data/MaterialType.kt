package eve.data

import androidx.compose.runtime.Immutable


/**
 * A type of material, e.g. "Liquid Ozone".
 */
@Immutable
class MaterialType internal constructor(


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