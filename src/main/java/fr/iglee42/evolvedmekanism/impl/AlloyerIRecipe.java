package fr.iglee42.evolvedmekanism.impl;

import fr.iglee42.evolvedmekanism.recipes.AlloyerRecipe;
import fr.iglee42.evolvedmekanism.registries.EMBlocks;
import fr.iglee42.evolvedmekanism.registries.EMRecipeSerializers;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

@NothingNullByDefault
public class AlloyerIRecipe extends AlloyerRecipe {

    public AlloyerIRecipe(ItemStackIngredient mainInput, ItemStackIngredient extraInput, ItemStackIngredient secondExtraInput, ItemStack output) {
        super(mainInput, extraInput,secondExtraInput, output);
    }

    @Override
    public RecipeType<AlloyerRecipe> getType() {
        return EMRecipeType.ALLOYING.get();
    }

    @Override
    public RecipeSerializer<AlloyerIRecipe> getSerializer() {
        return EMRecipeSerializers.ALLOYER.get();
    }

    @Override
    public String getGroup() {
        return EMBlocks.ALLOYER.getName();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(EMBlocks.ALLOYER);
    }
}
