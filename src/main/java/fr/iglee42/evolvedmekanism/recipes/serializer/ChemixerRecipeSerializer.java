package fr.iglee42.evolvedmekanism.recipes.serializer;

import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.iglee42.evolvedmekanism.recipes.ChemixerRecipe;
import mekanism.api.SerializationConstants;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class ChemixerRecipeSerializer<RECIPE extends ChemixerRecipe> implements RecipeSerializer<RECIPE> {

    private final MapCodec<RECIPE> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, RECIPE> streamCodec;

    public ChemixerRecipeSerializer(Function4<ItemStackIngredient, ItemStackIngredient, ChemicalStackIngredient, ItemStack, RECIPE> factory) {
        this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStackIngredient.CODEC.fieldOf(SerializationConstants.MAIN_INPUT).forGetter(ChemixerRecipe::getInputMain),
                ItemStackIngredient.CODEC.fieldOf(SerializationConstants.EXTRA_INPUT).forGetter(ChemixerRecipe::getInputExtra),
                ChemicalStackIngredient.CODEC.fieldOf(SerializationConstants.CHEMICAL_INPUT).forGetter(ChemixerRecipe::getInputGas),
                ItemStack.CODEC.fieldOf(SerializationConstants.OUTPUT).forGetter(ChemixerRecipe::getOutputRaw)
        ).apply(instance, factory));
        this.streamCodec = StreamCodec.composite(
                ItemStackIngredient.STREAM_CODEC, ChemixerRecipe::getInputMain,
                ItemStackIngredient.STREAM_CODEC, ChemixerRecipe::getInputExtra,
                ChemicalStackIngredient.STREAM_CODEC, ChemixerRecipe::getInputGas,
                ItemStack.STREAM_CODEC, ChemixerRecipe::getOutputRaw,
                factory
        );
    }

    @NotNull
    @Override
    public MapCodec<RECIPE> codec() {
        return codec;
    }

    @NotNull
    @Override
    public StreamCodec<RegistryFriendlyByteBuf, RECIPE> streamCodec() {
        return streamCodec;
    }
}
