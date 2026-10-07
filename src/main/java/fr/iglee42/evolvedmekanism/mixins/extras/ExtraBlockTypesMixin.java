package fr.iglee42.evolvedmekanism.mixins.extras;

import com.jerry.mekextras.common.content.blocktype.ExtraFactory;
import com.jerry.mekextras.common.registries.ExtraBlockTypes;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import fr.iglee42.evolvedmekanism.compat.extras.ExtrasAlloyingFactories;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.common.content.blocktype.FactoryType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Supplier;

//Extras builds a block type for every factory type, but its builder switches over FactoryType and can't handle ALLOYING, so build the alloying ones ourselves
@Mixin(value = ExtraBlockTypes.class, remap = false)
public class ExtraBlockTypesMixin {

    @SuppressWarnings("rawtypes")
    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/jerry/mekextras/common/content/blocktype/ExtraFactory$ExtraFactoryBuilder;createFactory(Ljava/util/function/Supplier;Lmekanism/common/content/blocktype/FactoryType;Lcom/jerry/mekextras/common/tier/ExtraFactoryTier;)Lcom/jerry/mekextras/common/content/blocktype/ExtraFactory$ExtraFactoryBuilder;"))
    private static ExtraFactory.ExtraFactoryBuilder evolvedmekanism$createAlloyingFactories(Supplier<?> tileEntityRegistrar, FactoryType type, ExtraFactoryTier tier) {
        if (type == EMFactoryType.ALLOYING) {
            return ExtrasAlloyingFactories.createFactory(tier);
        }
        return ExtraFactory.ExtraFactoryBuilder.createFactory(tileEntityRegistrar, type, tier);
    }
}
