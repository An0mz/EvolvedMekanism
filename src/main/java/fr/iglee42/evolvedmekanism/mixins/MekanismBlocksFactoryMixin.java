package fr.iglee42.evolvedmekanism.mixins;

import fr.iglee42.evolvedmekanism.interfaces.EMInputRecipeCache;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import mekanism.api.tier.ITier;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.block.attribute.AttributeTier;
import mekanism.common.block.prefab.BlockFactoryMachine.BlockFactory;
import mekanism.common.content.blocktype.Factory;
import mekanism.common.item.block.machine.ItemBlockFactory;
import mekanism.common.registration.impl.BlockRegistryObject;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tier.FactoryTier;
import mekanism.common.tile.factory.TileEntityFactory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

//registerFactory switches over FactoryType to set up the item's containers, which can't handle ALLOYING
@Mixin(value = MekanismBlocks.class, remap = false)
public class MekanismBlocksFactoryMixin {

    @Shadow
    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(ITier tier, String suffix,
          Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        throw new AssertionError();
    }

    @Inject(method = "registerFactory", at = @At("HEAD"), cancellable = true)
    private static <TILE extends TileEntityFactory<?>> void evolvedmekanism$registerAlloyingFactory(Factory<TILE> type, CallbackInfoReturnable<BlockRegistryObject<BlockFactory<?>, ItemBlockFactory>> cir) {
        if (type.getFactoryType() != EMFactoryType.ALLOYING) {
            return;
        }
        FactoryTier tier = (FactoryTier) type.get(AttributeTier.class).tier();
        BlockRegistryObject<BlockFactory<?>, ItemBlockFactory> factory = registerTieredBlock(tier, "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockFactory<>(type), ItemBlockFactory::new);
        factory.forItemHolder(holder -> holder.addAttachmentOnlyContainers(ContainerType.ITEM, () -> ItemSlotsBuilder.builder()
              .addBasicFactorySlots(tier.processes, s -> EMRecipeType.ALLOYING.getInputCache().containsInputA(null, s))
              .addInput(EMRecipeType.ALLOYING, EMInputRecipeCache.TripleItem::containsInputB)
              .addInput(EMRecipeType.ALLOYING, EMInputRecipeCache.TripleItem::containsInputC)
              .addEnergy()
              .build()
        ));
        cir.setReturnValue(factory);
    }
}
