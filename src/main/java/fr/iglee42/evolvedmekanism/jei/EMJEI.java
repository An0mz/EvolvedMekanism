package fr.iglee42.evolvedmekanism.jei;

import java.util.Collection;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.compat.extras.ExtrasJEI;
import net.neoforged.fml.ModList;
import fr.iglee42.evolvedmekanism.jei.categories.APTRecipeCategory;
import fr.iglee42.evolvedmekanism.jei.categories.AlloyerRecipeCategory;
import fr.iglee42.evolvedmekanism.jei.categories.ChemixerRecipeCategory;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMItems;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import mekanism.client.recipe_viewer.jei.CatalystRegistryHelper;
import mekanism.client.recipe_viewer.jei.MekanismSubtypeInterpreter;
import mekanism.client.recipe_viewer.jei.RecipeRegistryHelper;
import mekanism.common.capabilities.Capabilities;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class EMJEI implements IModPlugin {

    private static final ISubtypeInterpreter<ItemStack> MEKANISM_DATA_INTERPRETER = new MekanismSubtypeInterpreter();

    @NotNull
    @Override
    public ResourceLocation getPluginUid() {
        return EvolvedMekanism.rl("jei_plugin");
    }

    public static void registerItemSubtypes(ISubtypeRegistration registry, Collection<? extends Holder<Item>> items) {
        for (Holder<Item> item : items) {
            //Handle items
            ItemStack stack = new ItemStack(item);
            if (Capabilities.STRICT_ENERGY.hasCapability(stack) || Capabilities.CHEMICAL.hasCapability(stack) || Capabilities.FLUID.hasCapability(stack)) {
                registry.registerSubtypeInterpreter(stack.getItem(), MEKANISM_DATA_INTERPRETER);
            }
        }
    }

    @Override
    public void registerItemSubtypes(@NotNull ISubtypeRegistration registry) {
        registerItemSubtypes(registry, EMItems.ITEMS.getEntries());
        registerItemSubtypes(registry, EMBlocks.BLOCKS.getSecondaryEntries());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new AlloyerRecipeCategory(guiHelper, JEIRecipeTypes.ALLOYING));
        registry.addRecipeCategories(new ChemixerRecipeCategory(guiHelper, JEIRecipeTypes.CHEMIXING));
        registry.addRecipeCategories(new APTRecipeCategory(guiHelper, JEIRecipeTypes.APT));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registry) {
        RecipeRegistryHelper.register(registry, JEIRecipeTypes.ALLOYING, EMRecipeType.ALLOYING);
        RecipeRegistryHelper.register(registry, JEIRecipeTypes.CHEMIXING, EMRecipeType.CHEMIXING);
        RecipeRegistryHelper.register(registry, JEIRecipeTypes.APT, EMRecipeType.APT);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registry) {
        CatalystRegistryHelper.register(registry, JEIRecipeTypes.ALLOYING);
        if (ModList.get().isLoaded("mekanism_extras")) {
            ExtrasJEI.registerCatalysts(registry);
        }
        CatalystRegistryHelper.register(registry, JEIRecipeTypes.CHEMIXING);
        CatalystRegistryHelper.register(registry, JEIRecipeTypes.APT);
    }
}
