# Materials

## Resources

| Resource | How to make it | Forms |
|---|---|---|
| **Better Gold** | Gold + Spent Nuclear Waste in the [APT](apt.md) | Ingot, nugget, dust, enriched, block |
| **Plaslitherite** | Netherite + HDPE + Lithium in the [Chemixer](machines.md#chemixer) | Ingot, nugget, dust, enriched, block |
| **Refined Redstone** | Redstone Dust in the Osmium Compressor (uses Osmium) | Ingot, nugget, block |

Better Gold and Plaslitherite work like Mekanism's metals: crush ingots into dust, smelt dust back into ingots, enrich dust in the Enrichment Chamber, and craft nuggets and blocks.

Each also comes as a **chemical** for the Metallurgic Infuser, together with **Uranium**:

| Item | Chemical |
|---|---|
| 1 dust | 10 mB |
| 1 enriched item | 80 mB |

Enriched Uranium comes from enriching Uranium Dust.

## Uranium ores

**Netherrack Uranium Ore** generates in the Nether and **End Stone Uranium Ore** in the End. They drop and process like normal uranium ore.

They use Mekanism's uranium ore settings (in Mekanism's world config), so turning off or changing uranium generation there changes them too.

You can also make them in the Combiner: 8 Raw Uranium + Netherrack or End Stone.

## Alloys

Five new alloy tiers on top of Mekanism's Atomic Alloy:

| Alloy | Recipe |
|---|---|
| **Hypercharged** | Atomic Alloy + 20 mB Uranium in the Metallurgic Infuser |
| **Subatomic** | Hypercharged Alloy + 50 mB Antimatter in the Antiprotonic Nucleosynthesizer |
| **Singular** | Subatomic Alloy + 20 mB Better Gold in the Metallurgic Infuser |
| **Exoversal** | Singular Alloy + 20 mB Plaslitherite in the Metallurgic Infuser |
| **Creative** | Creative only, no recipe |

Every alloy, including Mekanism's Infused, Reinforced and Atomic, has a storage block: 8 alloys around 1 more in the middle make 1 block.

## Circuits

Each circuit is the circuit below it with 4 alloys around it (top, bottom, left, right) in a crafting table:

| Circuit | Made from |
|---|---|
| **Overclocked** | Ultimate Control Circuit + 4 Hypercharged Alloys |
| **Quantum** | Overclocked Circuit + 4 Subatomic Alloys |
| **Dense** | Quantum Circuit + 4 Singular Alloys |
| **Multiversal** | Dense Circuit + 4 Exoversal Alloys |
| **Creative** | Multiversal Circuit + 4 Creative Alloys |

They are tagged `c:circuits/overclocked`, `c:circuits/quantum` and so on, so other mods and modpacks can use them.
