package fr.iglee42.evolvedmekanism.client;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import fr.iglee42.evolvedmekanism.client.gui.*;
import fr.iglee42.evolvedmekanism.client.renderers.RenderAPT;
import fr.iglee42.evolvedmekanism.client.renderers.datas.MultipleCustomRenderData;
import fr.iglee42.evolvedmekanism.particles.RisingBubbleParticle;
import fr.iglee42.evolvedmekanism.registries.*;
import mekanism.client.ClientRegistrationUtil;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;

@EventBusSubscriber(modid = EvolvedMekanism.MODID, value = Dist.CLIENT)
public class ClientRegistration {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(EMParticleTypes.RISING_BUBBLE.get(),
                RisingBubbleParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //Register entity rendering handlers

        event.registerBlockEntityRenderer(EMTileEntityTypes.APT_CASING.get(), RenderAPT::new);
        event.registerBlockEntityRenderer(EMTileEntityTypes.APT_PORT.get(), RenderAPT::new);
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registerContainers(RegisterMenuScreensEvent event) {
        ClientRegistrationUtil.registerScreen(event, EMContainerTypes.ALLOYER, GuiAlloyer::new);
        ClientRegistrationUtil.registerScreen(event, EMContainerTypes.CHEMIXER, GuiChemixer::new);
        ClientRegistrationUtil.registerScreen(event, EMContainerTypes.APT, GuiAPT::new);
    }

    @SubscribeEvent
    public static void onStitch(TextureAtlasStitchedEvent event) {
        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            return;
        }
        CustomModelRenderer.resetCachedModels();
        MultipleCustomRenderData.clearCaches();
    }
}
