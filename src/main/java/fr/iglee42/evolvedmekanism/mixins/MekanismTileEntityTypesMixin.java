package fr.iglee42.evolvedmekanism.mixins;

import com.google.common.collect.Table;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMTileEntityTypes;
import fr.iglee42.evolvedmekanism.tiles.factory.TileEntityAlloyingFactory;
import mekanism.common.block.prefab.BlockFactoryMachine.BlockFactory;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.item.block.machine.ItemBlockFactory;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.registries.MekanismTileEntityTypes;
import mekanism.common.tier.FactoryTier;
import mekanism.common.tile.base.TileEntityMekanism;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.util.EnumUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(value = MekanismTileEntityTypes.class,remap = false)
public class MekanismTileEntityTypesMixin {


    @Shadow @Final private static Table<FactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityFactory<?>>> FACTORIES;

    @Inject(method = "<clinit>",at = @At("TAIL"))
    private static void evolveddraconic$addNewFactories(CallbackInfo ci){
        for (FactoryTier tier : EnumUtils.FACTORY_TIERS) {
            BlockRegistryObject<BlockFactory<?>, ItemBlockFactory> block = MekanismBlocks.getFactory(tier, EMFactoryType.ALLOYING);
            FACTORIES.put(tier, EMFactoryType.ALLOYING, EMTileEntityTypes.TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new TileEntityAlloyingFactory(block, pos, state))
                    .clientTicker(TileEntityMekanism::tickClient)
                    .serverTicker(TileEntityMekanism::tickServer)
                    .withSimple(Capabilities.CONFIG_CARD)
                    .build());
        }
    }

}
