package fr.iglee42.evolvedmekanism.config;

import java.util.HashMap;
import java.util.Map;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import mekanism.common.config.IMekanismConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.event.config.ModConfigEvent;

public class EMConfig {

    private EMConfig() {
    }

    private static final Map<IConfigSpec, IMekanismConfig> KNOWN_CONFIGS = new HashMap<>();

    public static final EMGeneralConfig general = new EMGeneralConfig();

    public static void registerConfigs(ModContainer modContainer) {
        EMConfigHelper.registerConfig(KNOWN_CONFIGS, modContainer, general);
    }

    public static void onConfigLoad(ModConfigEvent configEvent) {
        EMConfigHelper.onConfigLoad(configEvent, EvolvedMekanism.MODID, KNOWN_CONFIGS);
    }
}
