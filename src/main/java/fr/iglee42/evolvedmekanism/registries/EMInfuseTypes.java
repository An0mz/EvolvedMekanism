package fr.iglee42.evolvedmekanism.registries;

import fr.iglee42.evolvedmekanism.EvolvedMekanism;
import mekanism.api.chemical.Chemical;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;

@SuppressWarnings("unused")
public class EMInfuseTypes {

    public static final ChemicalDeferredRegister INFUSE_TYPES = new ChemicalDeferredRegister(EvolvedMekanism.MODID);

    public static final DeferredChemical<Chemical> URANIUM = INFUSE_TYPES.registerInfuse("uranium",0x9BE199);
    public static final DeferredChemical<Chemical> BETTER_GOLD = INFUSE_TYPES.register("better_gold",EvolvedMekanism.rl("infuse_type/better_gold"),0xF9C918);
    public static final DeferredChemical<Chemical> PLASLITHERITE = INFUSE_TYPES.register("plaslitherite",EvolvedMekanism.rl("infuse_type/plaslitherite"),0x666667);
}
