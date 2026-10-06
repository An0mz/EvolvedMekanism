package fr.iglee42.evolvedmekanism.config;

import mekanism.common.config.BaseMekanismConfig;
import mekanism.common.config.value.CachedIntValue;
import mekanism.common.config.value.CachedLongValue;
import net.neoforged.fml.config.ModConfig.Type;
import net.neoforged.neoforge.common.ModConfigSpec;

public class EMGeneralConfig extends BaseMekanismConfig {

    private static final String APT_CATEGORY = "apt";

    private final ModConfigSpec configSpec;

    //APT
    public final CachedIntValue aptInputStorage;
    public final CachedIntValue aptDefaultDuration;
    public final CachedLongValue aptEnergyStorage;
    public final CachedLongValue aptEnergyConsumption;
    // Supercharging element percents
    public final CachedIntValue aptMk1Percent;
    public final CachedIntValue aptMk2Percent;

    EMGeneralConfig() {

        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("General Config. This config is synced from server to client.").push("general");
        builder.comment("APT Settings").push(APT_CATEGORY);
        aptInputStorage = CachedIntValue.wrap(this, builder.comment("How much gas (in mB) can the input tank hold.")
                .defineInRange("inputPerAntimatter", 5_000, 1, Integer.MAX_VALUE));
        aptDefaultDuration = CachedIntValue.wrap(this, builder.comment("Duration of a recipe (in ticks). Formula: defaultDuration * (gasInputAmount / 100)")
              .defineInRange("defaultDuration", 200, 1, Integer.MAX_VALUE));
        aptEnergyStorage = CachedLongValue.wrap(this, builder.comment("Amount of energy (in Joules) which can be stored in the APT.")
              .defineInRange("energyStorage", 10_000_000L, 1, Long.MAX_VALUE));
        aptEnergyConsumption = CachedLongValue.wrap(this, builder.comment("Energy needed (in Joules) per tick to process the recipe.")
              .defineInRange("energyPerInput", 100_000L, 0, Long.MAX_VALUE));
        // Contribution per supercharging element in percent (e.g. 25 means +25%)
        aptMk1Percent = CachedIntValue.wrap(this, builder.comment("MK1 contribution in percent (e.g. 25 equals 25% per MK1 element).")
                .defineInRange("mk1Percent", 25, 0, 100000));
        aptMk2Percent = CachedIntValue.wrap(this, builder.comment("MK2 contribution in percent (e.g. 250 equals 250% per MK2 element).")
                .defineInRange("mk2Percent", 250, 0, 100000));
        builder.pop();

        builder.pop();
        configSpec = builder.build();
    }

    @Override
    public String getFileName() {
        return "general";
    }

    @Override
    public String getTranslation() {
        return "General Config";
    }

    @Override
    public ModConfigSpec getConfigSpec() {
        return configSpec;
    }

    @Override
    public Type getConfigType() {
        return Type.SERVER;
    }
}
