package fr.iglee42.evolvedmekanism.mixins.extras;

import com.google.common.collect.Table;
import com.jerry.mekextras.api.tier.IAdvancedTier;
import com.jerry.mekextras.common.block.attribute.ExtraAttributeTier;
import com.jerry.mekextras.common.block.prefab.BlockExtraFactoryMachine.BlockExtraFactory;
import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import com.jerry.mekextras.common.registries.ExtraBlockTypes;
import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.tile.factory.TileEntityExtraFactory;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.interfaces.EMInputRecipeCache;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import mekanism.common.attachments.containers.ContainerType;
import mekanism.common.attachments.containers.item.ItemSlotsBuilder;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.registration.impl.BlockRegistryObject;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BiFunction;
import java.util.function.Supplier;

//Registers mekanism_extras:<tier>_alloying_factory blocks, exactly like Extras registers its other factories
@Mixin(value = ExtraBlocks.class, remap = false)
public class ExtraBlocksMixin {

    @Shadow @Final private static Table<ExtraFactoryTier, FactoryType, BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory>> FACTORIES;

    @Shadow
    private static <TILE extends TileEntityExtraFactory<?>> BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory> registerFactory(ExtraFactory<TILE> type) {
        throw new AssertionError();
    }

    @Shadow
    private static <BLOCK extends Block, ITEM extends BlockItem> BlockRegistryObject<BLOCK, ITEM> registerTieredBlock(IAdvancedTier tier, String suffix,
          Supplier<? extends BLOCK> blockSupplier, BiFunction<BLOCK, Item.Properties, ITEM> itemCreator) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void evolvedmekanism$registerAlloyingFactories(CallbackInfo ci) {
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            FACTORIES.put(tier, EMFactoryType.ALLOYING, registerFactory(ExtraBlockTypes.getAdvancedFactory(tier, EMFactoryType.ALLOYING)));
        }
    }

    //registerFactory switches over FactoryType to set up the item's containers, which can't handle ALLOYING
    @Inject(method = "registerFactory", at = @At("HEAD"), cancellable = true)
    private static <TILE extends TileEntityExtraFactory<?>> void evolvedmekanism$registerAlloyingFactory(ExtraFactory<TILE> type, CallbackInfoReturnable<BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory>> cir) {
        if (type.getFactoryType() != EMFactoryType.ALLOYING) {
            return;
        }
        ExtraFactoryTier tier = (ExtraFactoryTier) type.get(ExtraAttributeTier.class).tier();
        BlockRegistryObject<BlockExtraFactory<?>, ItemBlockExtraFactory> factory = registerTieredBlock(tier, "_" + type.getFactoryType().getRegistryNameComponent() + "_factory", () -> new BlockExtraFactory<>(type), ItemBlockExtraFactory::new);
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
