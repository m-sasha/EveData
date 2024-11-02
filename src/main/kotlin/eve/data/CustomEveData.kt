/**
 * A DSL for creating custom versions of eve data.
 */
package eve.data


/**
 * The builder of custom [EveData].
 * This class is not thread safe.
 */
class CustomEveDataBuilder(baseData: EveData) {


    /**
     * The attributes in the base [EveData].
     */
    val baseAttributes = baseData.attributes


    /**
     * The effects in the base [EveData].
     */
    val baseEffects = baseData.effects


    private val metaGroupList = baseData.metaGroups.toMutableList()
    private val marketGroupList = baseData.marketGroups.toMutableList()
    private val raceList = baseData.races.toMutableList()
    private val attributeList = baseData.attributes.toMutableList()
    private val effectList = baseData.effects.toMutableList()
    private val categoriesById = baseData.categories.associateByTo(mutableMapOf()) { it.id }
    private val groupsById = baseData.groups.associateByTo(mutableMapOf()) { it.id }
    private val characterTypeBuilder = CharacterTypeBuilder(baseData.characterType)
    private val warfareBuffsTypeBuilder = WarfareBuffsTypeBuilder(baseData.warfareBuffsType)
    private val skills = baseData.skillTypes.toMutableList()
    private val ships = baseData.shipTypes.toMutableList()
    private val modules = baseData.moduleTypes.toMutableList()
    private val charges = baseData.chargeTypes.toMutableList()
    private val drones = baseData.droneTypes.toMutableList()
    private val implants = baseData.implantTypes.toMutableList()
    private val boosters = baseData.boosterTypes.toMutableList()
    private val subsystems = baseData.subsystemTypes.toMutableList()
    private val tacticalModes = baseData.tacticalModeTypes.toMutableList()
    private val environments = baseData.environmentTypes.toMutableList()
    private val materials = baseData.materialTypes.toMutableList()
    private val miscTypes = baseData.miscItemTypes.toMutableList()
    private val mutaplasmids = baseData.mutaplasmids.toMutableList()
    private val abyssalItemReplacementIds = baseData.abyssalItemReplacementIds.toMutableMap()


    /**
     * A cached [Attributes] value based on the updated list of attributes ([attributeList]).
     * Whenever the list of attributes changes, this is cleared.
     */
    private var _attributes: Attributes? = null


    /**
     * Returns an [Attributes] instance based on the current [attributeList].
     */
    internal val attributes: Attributes
        get(){
            var attrs = _attributes
            if (attrs == null){
                attrs = Attributes(attributeList)
                _attributes = attrs
            }
            return attrs
        }


    /**
     * A cached [Effects] value based on the updated list of effects ([effectList]).
     * Whenever the list of effects changes, this is cleared.
     */
    private var _effects: Effects? = null


    /**
     * Returns an [Effects] instance based on the current [effectList]
     */
    internal val effects: Effects
        get(){
            var effects = _effects
            if (effects == null){
                effects = Effects(effectList)
                _effects = effects
            }
            return effects
        }


    /**
     * The next attribute id we can freely use in the custom [EveData].
     */
    private var nextAvailableAttributeId = attributeList.maxOf { it.id } + 1


    /**
     * The next effect id we can freely use in the custom [EveData].
     */
    private var nextAvailableEffectId = effectList.maxOf { it.id } + 1


    /**
     * The next category id we can freely use in the custom [EveData].
     */
    private var nextAvailableCategoryId = (categoriesById.keys.maxOrNull() ?: 0) + 1


    /**
     * The next group id we can freely use in the custom [EveData].
     */
    private var nextAvailableGroupId = (groupsById.keys.maxOrNull() ?: 0) + 1


    /**
     * The next item id we can freely use in the custom [EveData].
     */
    private var nextAvailableItemId = listOf(skills, ships, modules, charges, drones, implants, subsystems)
        .flatten()
        .plus(baseData.characterType)
        .plus(baseData.warfareBuffsType)
        .maxOf { it.itemId } + 1


    /**
     * The next market group id we can freely use in the custom [EveData].
     */
    private var nextAvailableMarketGroupId = marketGroupList.maxOf { it.id } + 1


    /**
     * Reserves and returns an available attribute id.
     */
    private fun reserveAvailableAttributeId() = nextAvailableAttributeId++


    /**
     * Reserves and returns the next available effect id.
     */
    private fun reserveAvailableEffectId() = nextAvailableEffectId++


    /**
     * Reserves and returns an available category id.
     */
    private fun reserveAvailableCategoryId() = nextAvailableCategoryId++


    /**
     * Reserves and returns an available group id.
     */
    private fun reserveAvailableGroupId() = nextAvailableGroupId++


    /**
     * Reserves and returns an available item id.
     */
    fun reserveAvailableItemId() = nextAvailableItemId++


    /**
     * Reserves and returns an available market group id.
     */
    @Suppress("unused")
    fun reserveAvailableMarketGroupId() = nextAvailableMarketGroupId++


    /**
     * Adds an attribute and returns it.
     */
    fun attribute(
        id: Int? = null,
        name: String,
        displayName: String? = null,
        unit: AttributeUnit? = null,
        isStackingPenalized: Boolean,
        highIsGood: Boolean? = null,
        propertyRange: ClosedRange<Double>? = null,
    ) = Attribute.double(
        id = id ?: reserveAvailableAttributeId(),
        name = name,
        displayName = displayName,
        unit = unit,
        isStackingPenalized = isStackingPenalized,
        highIsGood = highIsGood,
        propertyRange = propertyRange
    ).also {
        attributeList.add(it)
        _attributes = null
    }


    /**
     * Adds an effect and returns it.
     */
    fun effect(
        id: Int? = null,
        name: String,
        category: Effect.Category,
        flags: EffectFlags = EffectFlags(),
        condition: Effect.Condition? = null,
        builder: EffectBuilder.() -> Unit
    ) = EffectBuilder(
        id = id ?: reserveAvailableEffectId(),
        name = name,
        category = category,
        flags = flags,
        condition = condition
    ).apply(builder).build().also {
        effectList.add(it)
    }


    /**
     * Adds a type category and returns it.
     */
    fun category(
        id: Int?,
        name: String
    ) = TypeCategory(
        id = id ?: reserveAvailableCategoryId(),
        name = name
    ).also {
        categoriesById[it.id] = it
    }


    /**
     * Adds a type group and returns it.
     */
    fun group(
        category: TypeCategory,
        name: String
    ) = TypeGroup(
        id = reserveAvailableGroupId(),
        category = category,
        name = name
    ).also{
        groupsById[it.id] = it
    }


    /**
     * Modifies the [CharacterType].
     * Repeated calls to this method are cumulative, since there is only one [CharacterType] to build.
     */
    fun characterType(
        editor: CharacterTypeBuilder.() -> Unit
    ) = characterTypeBuilder.apply(editor)


    /**
     * Modifies the [WarfareBuffsType].
     * Repeated calls to this method are cumulative, since there is only one [WarfareBuffsType] to build.
     */
    fun warfareBuffsType(
        editor: WarfareBuffsTypeBuilder.() -> Unit
    ) = warfareBuffsTypeBuilder.apply(editor)


    /**
     * Builds, adds and returns a new skill type.
     */
    fun skillType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        builder: SkillTypeBuilder.() -> Unit
    ): SkillType = SkillTypeBuilder(
        itemId = itemId ?: reserveAvailableItemId(),
        group = group,
        volume = volume,
        marketGroup = marketGroup,
        name = name
    ).apply(builder).build().also{
        skills.add(it)
    }


    /**
     * Builds, adds and returns a new ship type.
     */
    fun shipType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        race: Race? = null,
        builder: ShipTypeBuilder.() -> Unit
    ): ShipType = ShipTypeBuilder(
        itemId = itemId ?: reserveAvailableItemId(),
        group = group,
        volume = volume,
        marketGroup = marketGroup,
        race = race,
        name = name
    ).apply(builder).build().also{
        ships.add(it)
    }


    /**
     * Builds, adds and returns a new module type.
     */
    fun moduleType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        slotType: ModuleSlotType,
        flags: ModuleFlags,
        variationParentTypeId: Int? = null,
        builder: ModuleTypeBuilder.() -> Unit
    ): ModuleType{
        val moduleItemId = itemId ?: reserveAvailableItemId()
        val moduleTypeBuilder = ModuleTypeBuilder(
            itemId = moduleItemId,
            group = group,
            name = name,
            volume = volume,
            marketGroup = marketGroup,
            slotType = slotType,
            flags = flags,
            variationParentTypeId = variationParentTypeId ?: moduleItemId
        )
        return moduleTypeBuilder.apply(builder).build().also{
            modules.add(it)
        }
    }


    /**
     * Builds, adds and returns a new charge type.
     */
    fun chargeType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        builder: ChargeTypeBuilder.() -> Unit
    ) = ChargeTypeBuilder(
        itemId = itemId ?: reserveAvailableItemId(),
        group = group,
        name = name,
        volume = volume,
        marketGroup = marketGroup,
    ).apply(builder).build().also{
        charges.add(it)
    }


    /**
     * Builds, adds and returns a new drone type.
     */
    fun droneType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        variationParentTypeId: Int? = null,
        builder: DroneTypeBuilder.() -> Unit
    ): DroneType{
        val droneItemId = itemId ?: reserveAvailableItemId()
        val droneTypeBuilder = DroneTypeBuilder(
            itemId = droneItemId,
            group = group,
            name = name,
            volume = volume,
            marketGroup = marketGroup,
            variationParentTypeId = variationParentTypeId ?: droneItemId
        )
        return droneTypeBuilder.apply(builder).build().also{
            drones.add(it)
        }
    }


    /**
     * Builds, adds and returns a new implant type.
     */
    fun implantType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
        variationParentTypeId: Int? = null,
        slot: Int,
        builder: ImplantTypeBuilder.() -> Unit
    ): ImplantType {
        val implantItemId = itemId ?: reserveAvailableItemId()
        val implantTypeBuilder = ImplantTypeBuilder(
            itemId = implantItemId,
            group = group,
            name = name,
            marketGroup = marketGroup,
            variationParentTypeId = variationParentTypeId ?: implantItemId,
            slot = slot,
        )
        return implantTypeBuilder.apply(builder).build().also{
            implants.add(it)
        }
    }


    /**
     * Builds, adds and returns a new booster type.
     */
    fun boosterType(
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
        variationParentTypeId: Int? = null,
        slot: Int,
        sideEffects: List<BoosterType.SideEffect>,
        builder: BoosterTypeBuilder.() -> Unit
    ): BoosterType {
        val boosterItemId = itemId ?: reserveAvailableItemId()
        val boosterTypeBuilder = BoosterTypeBuilder(
            itemId = boosterItemId,
            group = group,
            name = name,
            marketGroup = marketGroup,
            variationParentTypeId = variationParentTypeId ?: boosterItemId,
            slot = slot,
            sideEffects = sideEffects
        )
        return boosterTypeBuilder.apply(builder).build().also{
            boosters.add(it)
        }
    }


    /**
     * Builds, adds and returns a new tactical mode type for the given ship type.
     */
    fun tacticalModeType(
        shipType: ShipType,
        kind: TacticalModeType.Kind,
        itemId: Int? = null,
        group: TypeGroup,
        marketGroup: MarketGroup? = null,
        builder: TacticalModeTypeBuilder.() -> Unit
    ): TacticalModeType {
        val tacticalModeTypeId = itemId ?: reserveAvailableItemId()
        val tacticalModeTypeBuilder = TacticalModeTypeBuilder(
            itemId = tacticalModeTypeId,
            group = group,
            name = "${shipType.name} ${kind.name} Mode",
            marketGroup = marketGroup,
        )

        return tacticalModeTypeBuilder.apply {
            attributeValue(attributes.tacticalDestroyerShipId, shipType.itemId)
            attributeValue(attributes.tacticalModeTypeKindId, kind.kindId)
            builder()
        }.build().also {
            tacticalModes.add(it)
        }
    }


    /**
     * Builds, adds and returns a new subsystem type for the given ship type.
     */
    fun subsystemType(
        shipType: ShipType,
        kind: SubsystemType.Kind,
        itemId: Int? = null,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
        builder: SubsystemTypeBuilder.() -> Unit
    ): SubsystemType {
        val subsystemTypeId = itemId ?: reserveAvailableItemId()
        val subsystemTypeBuilder = SubsystemTypeBuilder(
            itemId = subsystemTypeId,
            group = group,
            name = "${shipType.name} ${kind.name} - $name",
            marketGroup = marketGroup,
        )

        return subsystemTypeBuilder.apply {
            attributeValue(attributes.fitsToShipTypeId, shipType.itemId)
            attributeValue(attributes.subSystemSlot, kind.subsystemSlot)
            builder()
        }.build().also {
            subsystems.add(it)
        }
    }


    /**
     * Builds an [EveData] instance.
     */
    fun build(): EveData{
        val attributes = Attributes(attributeList)
        return EveData(
            metaGroups = MetaGroups(metaGroupList),
            marketGroups = MarketGroups(marketGroupList),
            races = Races(raceList),
            attributes = attributes,
            effects = Effects(effectList),
            categories = TypeCategories(categoriesById.values),
            groups = TypeGroups(groupsById.values),
            characterType = characterTypeBuilder.build(),
            warfareBuffsType = warfareBuffsTypeBuilder.build(),
            skillTypes = skills,
            shipTypes = ships,
            moduleTypes = modules,
            chargeTypes = charges,
            droneTypes = drones,
            implantTypes = implants,
            boosterTypes = boosters,
            subsystemTypes = subsystems,
            tacticalModeTypes = tacticalModes,
            environmentTypes = environments,
            materialTypes = materials,
            miscItemTypes = miscTypes,
            mutaplasmids = mutaplasmids,
            abyssalItemReplacementIds = abyssalItemReplacementIds
        )
    }


    /**
     * The [Effect] builder.
     */
    class EffectBuilder(
        private val id: Int,
        private val name: String,
        private val category: Effect.Category,
        private val flags: EffectFlags = EffectFlags(),
        private val condition: Effect.Condition? = null
    ) {


        /**
         * The modifiers added to the [Effect] being built.
         */
        private val modifiers = mutableListOf<AttributeModifier>()


        /**
         * Adds a modifier to the effect.
         */
        fun modifier(
            modifiedAttributeId: Int?,
            modifyingAttributeId: Int?,
            attenuatingAttributeId: Int? = null,
            affectedItemType: AttributeModifier.AffectedItemType,
            affectedItemFilter: AttributeModifier.AffectedItemFilter,
            operation: AttributeModifier.Operation,
            groupId: Int? = null,
            skillTypeId: Int? = null,
        ) {
            modifiers.add(
                AttributeModifier(
                    modifiedAttributeId = modifiedAttributeId,
                    modifyingAttributeId = modifyingAttributeId,
                    attenuatingAttributeId = attenuatingAttributeId,
                    affectedItemType = affectedItemType,
                    affectedItemFilter = affectedItemFilter,
                    operation = operation,
                    groupId = groupId,
                    skillTypeId = skillTypeId
                )
            )
        }


        /**
         * Builds the effect
         */
        fun build() = Effect(
            id = id,
            name = name,
            category = category,
            flags = flags,
            modifiers = modifiers,
            condition =  condition,
        )


    }


    /**
     * The base class of [EveItemType] builders.
     */
    abstract inner class EveItemBuilder<T: EveItemType>(


        /**
         * The item on which the built item will be based. The list of attributes and effects will be initialized with
         * the attributes and effects of this item.
         */
        baseItem: T? = null,


        /**
         * The id of the item to build.
         */
        private val itemId: Int,


        /**
         * The group of the item type to build.
         */
        private val group: TypeGroup,


        /**
         * The name of the item to build.
         */
        private val name: String,


        /**
         * The description of the item to build.
         */
        private val description: String? = null,


        /**
        * The volume of the item to build.
         */
        private val volume: Double,


        /**
         * The market group of the item to build.
         */
        private val marketGroup: MarketGroup?,


        /**
         * The race of the item to build.
         */
        private val race: Race? = null,


        /**
         * The id of the icon for the item.
         */
        private val iconId: Int? = null,


        /**
         * Whether the icon is a rendering of the item.
         */
        private val isIconRendering: Boolean = false,


        /**
        * The name of the type of the item (e.g. "Ship", "Module").
         */
        private val typeName: String,


        /**
         * A function that creates the actual item.
         */
        private val ctor: (Attributes, EveItemTypeData) -> T


    ) {


        /**
         * The [AttributeValue]s of the item being built, mapped by their id.
         */
        private val attributeValueById = baseItem?.attributeValues?.associateBy { it.attributeId }?.toMutableMap()
            ?: mutableMapOf()


        /**
         * The ids of the effects of the item being built.
         */
        private val effectIds = baseItem?.effectReferences?.toMutableSet() ?: mutableSetOf()


        /**
         * Adds or replaces an [AttributeValue] of the given attribute, with the given value.
         */
        fun <T: Any> attributeValue(attribute: Attribute<T>, value: T){
            attributeValueById[attribute.id] = AttributeValue(
                attributeId = attribute.id,
                value = attribute.valueToDouble(value)
            )
        }


        /**
         * Adds a reference to the given effect.
         */
        fun effectReference(effect: Effect){
            effectIds.add(effect.id)
        }


        /**
         * Adds a skill requirement to this item.
         */
        fun requiresSkill(
            skillRequirementIndex: Int,
            skillId: Int,
            skillLevel: Int
        ){
            val requiredSkillAttributes = baseAttributes.skillRequirements[skillRequirementIndex]
            attributeValue(requiredSkillAttributes.skillId, skillId)
            attributeValue(requiredSkillAttributes.level, skillLevel)
        }


        /**
         * Builds the item.
         */
        fun build() = ctor(
            attributes,
            EveItemTypeData(
                itemId = itemId,
                group = group,
                name = name,
                description = description,
                volume = volume,
                marketGroup = marketGroup,
                race = race,
                iconId = iconId,
                isIconRendering = isIconRendering,
                attributeValues = AttributeValues(attributeValueById.values),
                effectReferences = effectIds.toEffectReferences(),
                typeName = typeName
            )
        )


    }


    /**
     * The [CharacterType] builder.
     */
    inner class CharacterTypeBuilder(
        baseCharacter: CharacterType
    ): EveItemBuilder<CharacterType>(
        baseItem = baseCharacter,
        itemId = baseCharacter.itemId,
        group = baseCharacter.group,
        name = baseCharacter.name,
        volume = baseCharacter.volume,
        marketGroup = baseCharacter.marketGroup,
        typeName = "CharacterType",
        ctor = ::CharacterType
    )


    /**
     * The [WarfareBuffsType] builder.
     */
    inner class WarfareBuffsTypeBuilder(
        baseWarfareBuffs: WarfareBuffsType
    ): EveItemBuilder<WarfareBuffsType>(
        baseItem = baseWarfareBuffs,
        itemId = baseWarfareBuffs.itemId,
        group = baseWarfareBuffs.group,
        name = baseWarfareBuffs.name,
        volume = baseWarfareBuffs.volume,
        marketGroup = baseWarfareBuffs.marketGroup,
        typeName = "WarfareBuffs",
        ctor = ::WarfareBuffsType
    )


    /**
     * The [SkillType] builder.
     */
    inner class SkillTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
    ): EveItemBuilder<SkillType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = volume,
        marketGroup = marketGroup,
        typeName = "SkillType",
        ctor = ::SkillType
    )


    /**
     * The [ShipType] builder.
     */
    inner class ShipTypeBuilder(
        baseShip: ShipType? = null,
        itemId: Int,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        race: Race? = null,
    ): EveItemBuilder<ShipType>(
        baseItem = baseShip,
        itemId = itemId,
        group = group,
        name = name,
        volume = volume,
        marketGroup = marketGroup,
        race = race,
        typeName = "ShipType",
        ctor = ::ShipType
    )


    /**
     * The [ModuleType] builder.
     */
    inner class ModuleTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        slotType: ModuleSlotType,
        flags: ModuleFlags,
        variationParentTypeId: Int
    ): EveItemBuilder<ModuleType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = volume,
        marketGroup = marketGroup,
        typeName = "ModuleType",
        ctor = { attributes, typeData ->
            ModuleType(attributes, typeData, variationParentTypeId, slotType, flags)
        }
    )


    /**
     * The [ChargeType] builder.
     */
    inner class ChargeTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
    ): EveItemBuilder<ChargeType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = volume,
        marketGroup = marketGroup,
        typeName = "ChargeType",
        ctor = ::ChargeType
    )


    /**
     * The [DroneType] builder.
     */
    inner class DroneTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        volume: Double,
        marketGroup: MarketGroup? = null,
        variationParentTypeId: Int
    ): EveItemBuilder<DroneType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = volume,
        marketGroup = marketGroup,
        typeName = "DroneType",
        ctor = { attributes, typeData ->
            DroneType(attributes, typeData, variationParentTypeId)
        }
    )


    /**
     * The [ImplantType] builder.
     */
    inner class ImplantTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
        variationParentTypeId: Int,
        slot: Int
    ): EveItemBuilder<ImplantType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = 1.0,
        marketGroup = marketGroup,
        typeName = "ImplantType",
        ctor = { attributes, typeData ->
            ImplantType(attributes, typeData, variationParentTypeId, slot)
        }
    )


    /**
     * The [BoosterType] builder.
     */
    inner class BoosterTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
        variationParentTypeId: Int,
        slot: Int,
        sideEffects: List<BoosterType.SideEffect>
    ): EveItemBuilder<BoosterType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = 1.0,
        marketGroup = marketGroup,
        typeName = "BoosterType",
        ctor = { attributes, typeData ->
            BoosterType(attributes, typeData, variationParentTypeId, slot, sideEffects)
        }
    )


    /**
     * The [TacticalModeType] builder.
     */
    inner class TacticalModeTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
    ): EveItemBuilder<TacticalModeType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = 1.0,
        marketGroup = marketGroup,
        typeName = "TacticalModeType",
        ctor = { attributes, typeData ->
            TacticalModeType(attributes, typeData)
        }
    )


    /**
     * The [SubsystemType] builder.
     */
    inner class SubsystemTypeBuilder(
        itemId: Int,
        group: TypeGroup,
        name: String,
        marketGroup: MarketGroup? = null,
    ): EveItemBuilder<SubsystemType>(
        itemId = itemId,
        group = group,
        name = name,
        volume = 5.0,
        marketGroup = marketGroup,
        typeName = "SubsystemType",
        ctor = { attributes, typeData ->
            SubsystemType(attributes, typeData)
        }
    )


}


/**
 * Builds a custom [EveData].
 */
@Suppress("unused")
fun EveData.modified(builder: CustomEveDataBuilder.() -> Unit): EveData {
    return CustomEveDataBuilder(this).apply(builder).build()
}


/**
 * A [ModuleFlags] instance for a passive module.
 */
val ModuleFlags.Companion.PASSIVE: ModuleFlags
    get() = ModuleFlags(
        isActivable = false,
        isOverloadable = false,
        isProjected = false,
        isTurret = false,
        isMissileLauncher = false
    )


/**
 * A [ModuleFlags] instance for an active, non-overloadable module.
 */
val ModuleFlags.Companion.ACTIVE: ModuleFlags
    get() = ModuleFlags(
        isActivable = true,
        isOverloadable = false,
        isProjected = false,
        isTurret = false,
        isMissileLauncher = false
    )


/**
 * A [ModuleFlags] instance for an overloadable, non-projected module.
 */
val ModuleFlags.Companion.OVERLOADABLE: ModuleFlags
    get() = ModuleFlags(
        isActivable = true,
        isOverloadable = true,
        isProjected = false,
        isTurret = false,
        isMissileLauncher = false
    )


/**
 * A [ModuleFlags] instance for a projected module.
 */
val ModuleFlags.Companion.PROJECTED: ModuleFlags
    get() = ModuleFlags(
        isActivable = true,
        isOverloadable = false,
        isProjected = true,
        isTurret = false,
        isMissileLauncher = false
    )


/**
 * A [ModuleFlags] instance for a turret.
 */
val ModuleFlags.Companion.TURRET: ModuleFlags
    get() = ModuleFlags(
        isActivable = true,
        isOverloadable = true,
        isProjected = true,
        isTurret = true,
        isMissileLauncher = false
    )



/**
 * A [ModuleFlags] instance for a missile launcher.
 */
val ModuleFlags.Companion.MISSILE_LAUNCHER: ModuleFlags
    get() = ModuleFlags(
        isActivable = true,
        isOverloadable = true,
        isProjected = true,
        isTurret = false,
        isMissileLauncher = true
    )
