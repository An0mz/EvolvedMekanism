package fr.iglee42.evolvedmekanism.registries;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.multiblock.apt.TileEntityAPTCasing;
import fr.iglee42.evolvedmekanism.multiblock.apt.TileEntityAPTPort;
import fr.iglee42.evolvedmekanism.tiles.TileEntitySuperchargingElement;
import fr.iglee42.evolvedmekanism.tiles.TileEntitySuperchargingElementMk2;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityAlloyer;
import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityChemixer;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.registration.impl.TileEntityTypeDeferredRegister;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;

public class EMTileEntityTypes {

    public static final TileEntityTypeDeferredRegister TILE_ENTITY_TYPES = new TileEntityTypeDeferredRegister(EvolvedMekanism.MODID);

    public static final TileEntityTypeRegistryObject<TileEntityAPTCasing> APT_CASING = TILE_ENTITY_TYPES.mekBuilder(EMBlocks.APT_CASING, TileEntityAPTCasing::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityAPTPort> APT_PORT = TILE_ENTITY_TYPES.mekBuilder(EMBlocks.APT_PORT, TileEntityAPTPort::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIGURABLE)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntitySuperchargingElement> SUPERCHARGING_ELEMENT = TILE_ENTITY_TYPES.mekBuilder(EMBlocks.SUPERCHARGING_ELEMENT, TileEntitySuperchargingElement::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntitySuperchargingElementMk2> SUPERCHARGING_ELEMENT_MK2 = TILE_ENTITY_TYPES.mekBuilder(EMBlocks.SUPERCHARGING_ELEMENT_MK2, TileEntitySuperchargingElementMk2::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .build();

    public static final TileEntityTypeRegistryObject<TileEntityAlloyer> ALLOYER = TILE_ENTITY_TYPES.mekBuilder(EMBlocks.ALLOYER, TileEntityAlloyer::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();
    public static final TileEntityTypeRegistryObject<TileEntityChemixer> CHEMIXER = TILE_ENTITY_TYPES.mekBuilder(EMBlocks.CHEMIXER, TileEntityChemixer::new)
            .clientTicker(TileEntityMekanism::tickClient)
            .serverTicker(TileEntityMekanism::tickServer)
            .withSimple(Capabilities.CONFIG_CARD)
            .build();

}