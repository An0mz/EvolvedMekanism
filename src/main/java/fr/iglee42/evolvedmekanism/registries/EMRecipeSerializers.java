package fr.iglee42.evolvedmekanism.registries;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.impl.*;
import fr.iglee42.evolvedmekanism.recipes.serializer.AlloyerRecipeSerializer;
import fr.iglee42.evolvedmekanism.recipes.serializer.ChemixerRecipeSerializer;
import mekanism.common.recipe.serializer.MekanismRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EMRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, EvolvedMekanism.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AlloyerIRecipe>> ALLOYER = RECIPE_SERIALIZERS.register("alloying", () -> new AlloyerRecipeSerializer<>(AlloyerIRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ChemixerIRecipe>> CHEMIXER = RECIPE_SERIALIZERS.register("chemixing", () -> new ChemixerRecipeSerializer<>(ChemixerIRecipe::new));
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<APTIRecipe>> APT = RECIPE_SERIALIZERS.register("apt", () -> MekanismRecipeSerializer.itemChemicalToItem(APTIRecipe::new));
}
