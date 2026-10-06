package fr.iglee42.evolvedmekanism.mixins.items;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import mekanism.common.item.block.machine.ItemBlockFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//getSideConfig switches over FactoryType, which can't handle ALLOYING. Alloying factories use the same one as combining factories
@Mixin(value = ItemBlockFactory.class, remap = false)
public class ItemBlockFactoryMixin {

    @Redirect(method = "getSideConfig", at = @At(value = "INVOKE", target = "Lmekanism/common/content/blocktype/FactoryType;ordinal()I"))
    private static int evolvedmekanism$alloyingSideConfig(FactoryType type) {
        return type == EMFactoryType.ALLOYING ? FactoryType.COMBINING.ordinal() : type.ordinal();
    }
}
