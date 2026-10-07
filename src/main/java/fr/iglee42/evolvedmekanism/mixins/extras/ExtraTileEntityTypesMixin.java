package fr.iglee42.evolvedmekanism.mixins.extras;

import com.google.common.collect.Table;
import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactory;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.registries.ExtraTileEntityTypes;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.compat.extras.TileEntityExtraAlloyingFactory;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registration.impl.TileEntityTypeRegistryObject;
import mekanism.common.tile.base.TileEntityMekanism;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ExtraTileEntityTypes.class, remap = false)
public class ExtraTileEntityTypesMixin {

    @Shadow @Final private static Table<ExtraFactoryTier, FactoryType, TileEntityTypeRegistryObject<? extends TileEntityExtraFactory<?>>> FACTORIES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void evolvedmekanism$registerAlloyingFactories(CallbackInfo ci) {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory> block = ExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING);
            FACTORIES.put(tier, EMFactoryType.ALLOYING, ExtraTileEntityTypes.EXTRA_TILE_ENTITY_TYPES.mekBuilder(block, (pos, state) -> new TileEntityExtraAlloyingFactory(block, pos, state))
                    .clientTicker(TileEntityMekanism::tickClient)
                    .serverTicker(TileEntityMekanism::tickServer)
                    .withSimple(Capabilities.CONFIG_CARD)
                    .build());
        }
    }
}
