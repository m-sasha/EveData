package eve.data

import androidx.compose.runtime.Immutable


/**
 * A type of item we are only interested in because it can be placed into the cargohold, e.g. filaments or deployables.
 */
@Immutable
class MiscItemType internal constructor(


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
     * The maximum number of ships that can just using this filament.
     */
    val maximumShipsJumped: Int? by attributes.mjdShipJumpCap.orNull  // Yes, that's the attribute it uses


}