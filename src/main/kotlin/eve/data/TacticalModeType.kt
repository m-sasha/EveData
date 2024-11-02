package eve.data

import androidx.compose.runtime.Immutable

/**
 * The item type for a tactical destroyer mode, e.g. "Jackdaw Defense Mode".
 */
@Immutable
class TacticalModeType internal constructor(


    /**
     * The context [Attributes].
     */
    attributes: Attributes,


    /**
     * The underlying [EveItemTypeData].
     */
    typeData: EveItemTypeData,


): EveItemType(
    attributes = attributes,
    typeData = typeData
) {


    /**
     * The id of the ship this tactical mode is for.
     */
    val shipId: Int by attributes.tacticalDestroyerShipId


    /**
     * The id of the kind of this tactical mode type.
     */
    private val kindId: Int by attributes.tacticalModeTypeKindId


    /**
     * The kind of this tactical mode.
     */
    val kind: Kind = Kind.valueByKindId.getValue(kindId)


    /**
     * The kinds of tactical modes, in their standard order.
     */
    enum class Kind(val kindId: Int){


        // The kind id is defined in Fixups
        DEFENSE(1),
        SHARPSHOOTER(2),
        PROPULSION(3);


        companion object{


            /**
             * The [Kind] values by their kind id.
             */
            internal val valueByKindId = entries.associateBy { it.kindId }


        }


    }


}


