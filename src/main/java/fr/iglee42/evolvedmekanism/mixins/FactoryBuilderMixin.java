package fr.iglee42.evolvedmekanism.mixins;

import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.content.blocktype.Factory;
import mekanism.common.content.blocktype.FactoryType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//createFactory switches over FactoryType for the side config, which can't handle ALLOYING. Alloying factories use the same one as combining factories
@Mixin(value = Factory.FactoryBuilder.class, remap = false)
public class FactoryBuilderMixin {

    @Redirect(method = "createFactory", at = @At(value = "INVOKE", target = "Lmekanism/common/content/blocktype/FactoryType;ordinal()I"))
    private static int evolvedmekanism$alloyingSideConfig(FactoryType type) {
        return type == EMFactoryType.ALLOYING ? FactoryType.COMBINING.ordinal() : type.ordinal();
    }
}
