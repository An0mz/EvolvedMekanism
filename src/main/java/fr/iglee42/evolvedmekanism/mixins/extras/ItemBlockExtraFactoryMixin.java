package fr.iglee42.evolvedmekanism.mixins.extras;

import com.jerry.mekextras.common.item.block.machine.ItemBlockExtraFactory;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//getSideConfig switches over FactoryType, which can't handle ALLOYING. Alloying factories use the same one as combining factories
@Mixin(value = ItemBlockExtraFactory.class, remap = false)
public class ItemBlockExtraFactoryMixin {

    @Redirect(method = "getSideConfig", at = @At(value = "INVOKE", target = "Lmekanism/common/content/blocktype/FactoryType;ordinal()I"))
    private static int evolvedmekanism$alloyingSideConfig(FactoryType type) {
        return type == EMFactoryType.ALLOYING ? FactoryType.COMBINING.ordinal() : type.ordinal();
    }
}
