package fr.iglee42.evolvedmekanism.mixins.tiles;

import fr.iglee42.evolvedmekanism.utils.EMDataHandlerUtils;
import mekanism.common.attachments.containers.ContainerType;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = ContainerType.class, remap = false)
public class ContainerTypeMixin {

    @Redirect(method = "read",at = @At(value = "INVOKE", target = "Lmekanism/api/DataHandlerUtils;readContents(Lnet/minecraft/core/HolderLookup$Provider;Ljava/util/List;Lnet/minecraft/nbt/ListTag;Ljava/lang/String;)V"))
    private void em$loadInventoryWithIntSlots(HolderLookup.Provider provider, List<? extends INBTSerializable<CompoundTag>> containers, ListTag storedContainers, String key){
        EMDataHandlerUtils.readContents(provider, containers, storedContainers, key);
    }

    @Redirect(method = "save",at = @At(value = "INVOKE", target = "Lmekanism/api/DataHandlerUtils;writeContents(Lnet/minecraft/core/HolderLookup$Provider;Ljava/util/List;Ljava/lang/String;)Lnet/minecraft/nbt/ListTag;"))
    private ListTag em$writeInventoryWithIntSlots(HolderLookup.Provider provider, List<? extends INBTSerializable<CompoundTag>> containers, String key){
        return EMDataHandlerUtils.writeContents(provider, containers, key);
    }

}
