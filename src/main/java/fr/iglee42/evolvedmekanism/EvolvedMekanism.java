package fr.iglee42.evolvedmekanism;

import fr.iglee42.evolvedmekanism.interfaces.InitializableEnum;
import fr.iglee42.evolvedmekanism.registries.*;
import mekanism.api.text.EnumColor;
import net.minecraft.network.chat.Component;

import fr.iglee42.evolvedmekanism.config.EMConfig;
import fr.iglee42.evolvedmekanism.multiblock.EMBuilders;
import fr.iglee42.evolvedmekanism.multiblock.apt.APTCache;
import fr.iglee42.evolvedmekanism.multiblock.apt.APTMultiblockData;
import fr.iglee42.evolvedmekanism.multiblock.apt.APTValidator;
import fr.iglee42.evolvedmekanism.network.EMPacketHandler;
import fr.iglee42.evolvedmekanism.tiers.EMBaseTier;
import mekanism.api.tier.AlloyTier;
import mekanism.api.tier.BaseTier;
import mekanism.common.MekanismLang;
import mekanism.common.command.builders.BuildCommand;
import mekanism.common.lib.Version;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registries.MekanismItems;
import mekanism.common.util.MekanismUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@SuppressWarnings("unused")
@Mod(EvolvedMekanism.MODID)
public class EvolvedMekanism {

    public static final String MODID = "evolvedmekanism";
    public static final String MOD_NAME = "EvolvedMekanism";

    public static EvolvedMekanism instance;

    private final EMPacketHandler packetHandler;

    public final Version versionNumber;

    public static final MultiblockManager<APTMultiblockData> aptManager = new MultiblockManager<>("apt", APTCache::new,
            APTValidator::new);

    public EvolvedMekanism(IEventBus modEventBus, ModContainer modContainer) {
        instance = this;

        EMConfig.registerConfigs(modContainer);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(EMConfig::onConfigLoad);

        initEnums();

        EMBlocks.BLOCKS.register(modEventBus);
        EMItems.ITEMS.register(modEventBus);
        EMCreativeTabs.CREATIVE_TABS.register(modEventBus);
        EMTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        EMInfuseTypes.INFUSE_TYPES.register(modEventBus);
        EMContainerTypes.CONTAINER_TYPES.register(modEventBus);
        EMRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        EMParticleTypes.PARTICLES.register(modEventBus);

        versionNumber = new Version(modContainer);
        packetHandler = new EMPacketHandler(modEventBus);
    }

    public static EMPacketHandler packetHandler() {
        return instance.packetHandler;
    }

    @SuppressWarnings("DataFlowIssue")
    private void initEnums() {
        MekanismLang ignoredLType = MekanismLang.MEKANISM;
        ((InitializableEnum)(Object)BaseTier.BASIC).evolvedmekanism$initNewValues();
        ((InitializableEnum)(Object)AlloyTier.INFUSED).evolvedmekanism$initNewValues();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            EMTags.init();
            BuildCommand.register("apt", EvolvedMekanismLang.APT, new EMBuilders.APTBuilder());
        });
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static ResourceLocation getResource(MekanismUtils.ResourceType type, String name) {
        return rl(type.getPrefix() + name);
    }

    public static boolean isEvolvedMekanismTier(BaseTier tier) {
        return tier.equals(EMBaseTier.OVERCLOCKED) || tier.equals(EMBaseTier.QUANTUM) || tier.equals(EMBaseTier.DENSE)
                || tier.equals(EMBaseTier.MULTIVERSAL) || tier.equals(BaseTier.CREATIVE);
    }

    public static ItemRegistryObject<Item> getCircuitByTier(BaseTier tier) {
        if (tier.equals(BaseTier.BASIC))
            return MekanismItems.BASIC_CONTROL_CIRCUIT;
        if (tier.equals(BaseTier.ADVANCED))
            return MekanismItems.ADVANCED_CONTROL_CIRCUIT;
        if (tier.equals(BaseTier.ELITE))
            return MekanismItems.ELITE_CONTROL_CIRCUIT;
        if (tier.equals(BaseTier.ULTIMATE))
            return MekanismItems.ULTIMATE_CONTROL_CIRCUIT;
        if (tier.equals(EMBaseTier.OVERCLOCKED))
            return EMItems.OVERCLOCKED_CONTROL_CIRCUIT;
        if (tier.equals(EMBaseTier.QUANTUM))
            return EMItems.QUANTUM_CONTROL_CIRCUIT;
        if (tier.equals(EMBaseTier.DENSE))
            return EMItems.DENSE_CONTROL_CIRCUIT;
        if (tier.equals(EMBaseTier.MULTIVERSAL))
            return EMItems.MULTIVERSAL_CONTROL_CIRCUIT;
        if (tier.equals(BaseTier.CREATIVE))
            return EMItems.CREATIVE_CONTROL_CIRCUIT;
        return null;
    }

    public static Component logFormat(Object message) {
        return logFormat(EnumColor.GRAY,message);
    }

    public static Component logFormat(EnumColor messageColor, Object message) {
        return MekanismLang.LOG_FORMAT.translateColored(EnumColor.DARK_GREEN, EvolvedMekanismLang.MEKANISM_EVOLVED, messageColor, message);
    }
}
