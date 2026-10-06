package fr.iglee42.evolvedmekanism.recipes.serializer;

import com.mojang.datafixers.util.Function4;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.iglee42.evolvedmekanism.recipes.AlloyerRecipe;
import fr.iglee42.evolvedmekanism.utils.EMSerializationConstants;
import mekanism.api.SerializationConstants;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class AlloyerRecipeSerializer<RECIPE extends AlloyerRecipe> implements RecipeSerializer<RECIPE> {

    private final MapCodec<RECIPE> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, RECIPE> streamCodec;

    public AlloyerRecipeSerializer(Function4<ItemStackIngredient, ItemStackIngredient, ItemStackIngredient, ItemStack, RECIPE> factory) {
        this.codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ItemStackIngredient.CODEC.fieldOf(SerializationConstants.MAIN_INPUT).forGetter(AlloyerRecipe::getMainInput),
                ItemStackIngredient.CODEC.fieldOf(SerializationConstants.EXTRA_INPUT).forGetter(AlloyerRecipe::getExtraInput),
                ItemStackIngredient.CODEC.fieldOf(EMSerializationConstants.SECOND_EXTRA_INPUT).forGetter(AlloyerRecipe::getTertiaryExtraInput),
                ItemStack.CODEC.fieldOf(SerializationConstants.OUTPUT).forGetter(AlloyerRecipe::getOutputRaw)
        ).apply(instance, factory));
        this.streamCodec = StreamCodec.composite(
                ItemStackIngredient.STREAM_CODEC, AlloyerRecipe::getMainInput,
                ItemStackIngredient.STREAM_CODEC, AlloyerRecipe::getExtraInput,
                ItemStackIngredient.STREAM_CODEC, AlloyerRecipe::getTertiaryExtraInput,
                ItemStack.STREAM_CODEC, AlloyerRecipe::getOutputRaw,
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
