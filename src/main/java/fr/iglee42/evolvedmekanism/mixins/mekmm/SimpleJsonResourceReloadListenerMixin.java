package fr.iglee42.evolvedmekanism.mixins.mekmm;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

//MoreMachine still ships recipes and loot tables for the Evolved Mekanism factories we stop it from registering, so drop them before they get parsed and spam the log
@SuppressWarnings("unused")
@Mixin(value = SimpleJsonResourceReloadListener.class, remap = false)
public class SimpleJsonResourceReloadListenerMixin {

    @Unique
    private static final List<String> evolvedmekanism$MEKMM_TIERS = List.of("overclocked", "quantum", "dense", "multiversal", "creative");

    @Inject(method = "scanDirectory", at = @At("TAIL"))
    private static void evolvedmekanism$removeMekmmEvolvedData(ResourceManager resourceManager, String name, Gson gson, Map<ResourceLocation, JsonElement> output, CallbackInfo ci) {
        if (name.equals("recipe")) {
            output.keySet().removeIf(id -> id.getNamespace().equals("mekmm") && id.getPath().startsWith("compat/evolvedmekanism/"));
        } else if (name.equals("loot_table")) {
            output.keySet().removeIf(id -> id.getNamespace().equals("mekmm") && evolvedmekanism$isEvolvedFactory(id.getPath()));
        }
    }

    @Unique
    private static boolean evolvedmekanism$isEvolvedFactory(String path) {
        if (!path.startsWith("blocks/") || !path.endsWith("_factory")) return false;
        String block = path.substring("blocks/".length());
        for (String tier : evolvedmekanism$MEKMM_TIERS) {
            if (block.startsWith(tier + "_")) return true;
        }
        return false;
    }
}
