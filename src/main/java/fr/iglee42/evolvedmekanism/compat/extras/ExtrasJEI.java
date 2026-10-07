package fr.iglee42.evolvedmekanism.compat.extras;

import com.jerry.mekextras.common.registries.ExtraBlocks;
import com.jerry.mekextras.common.tier.ExtraFactoryTier;
import com.jerry.mekextras.common.util.ExtraEnumUtils;
import fr.iglee42.evolvedmekanism.jei.JEIRecipeTypes;
import fr.iglee42.evolvedmekanism.registries.EMFactoryType;
import mekanism.client.recipe_viewer.jei.CatalystRegistryHelper;
import mekanism.client.recipe_viewer.jei.MekanismJEI;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;

//Kept separate from EMJEI so Mekanism Extras classes are only loaded when it is installed
public class ExtrasJEI {

    public static void registerCatalysts(IRecipeCatalystRegistration registry) {
        List<ItemLike> factories = new ArrayList<>();
        for (ExtraFactoryTier tier : ExtraEnumUtils.EXTRA_FACTORY_TIERS) {
            factories.add(ExtraBlocks.getExtraFactory(tier, EMFactoryType.ALLOYING));
        }
        CatalystRegistryHelper.register(registry, MekanismJEI.genericRecipeType(JEIRecipeTypes.ALLOYING), factories);
    }
}
