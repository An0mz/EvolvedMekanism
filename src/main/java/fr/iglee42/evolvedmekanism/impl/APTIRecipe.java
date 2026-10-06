package fr.iglee42.evolvedmekanism.impl;

import fr.iglee42.evolvedmekanism.registries.EMItems;
import fr.iglee42.evolvedmekanism.registries.EMRecipeSerializers;
import fr.iglee42.evolvedmekanism.registries.EMRecipeType;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.api.recipes.basic.BasicItemStackChemicalToItemStackRecipe;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;

@NothingNullByDefault
public class APTIRecipe extends BasicItemStackChemicalToItemStackRecipe {

    public APTIRecipe(ItemStackIngredient itemInput, ChemicalStackIngredient chemicalInput, ItemStack output, boolean perTickUsage) {
        super(itemInput, chemicalInput, output, perTickUsage, EMRecipeType.APT.get());
    }

    @Override
    public RecipeSerializer<APTIRecipe> getSerializer() {
        return EMRecipeSerializers.APT.get();
    }

    @Override
    public String getGroup() {
        return EMItems.BETTER_GOLD_INGOT.getName();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(EMItems.BETTER_GOLD_INGOT.get());
    }
}
