package fr.iglee42.evolvedmekanism.mixins.mekmm;

import com.jerry.mekmm.common.integration.MoreMachineHooks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//MoreMachine's Evolved Mekanism compat builds factories for the Overclocked to Creative factory tiers, which this fork doesn't have, so it crashes on load.
//We keep the evolvedmekanism mod id for other addons, so tell MoreMachine it isn't installed instead
@SuppressWarnings("unused")
@Mixin(value = MoreMachineHooks.class, remap = false)
public class MoreMachineHooksMixin {

    @Shadow @Final @Mutable
    public MoreMachineHooks.IntegrationInfo evolvedMekanism;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void evolvedmekanism$disableEvolvedMekanismCompat(CallbackInfo ci) {
        evolvedMekanism = new MoreMachineHooks.IntegrationInfo("evolvedmekanism", false);
    }
}
