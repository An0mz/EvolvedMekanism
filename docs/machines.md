# Machines

## Alloyer

Combines three items into one: a main input and two extra inputs. It uses the same energy settings as Mekanism's Combiner.

**Recipe:** Steel Casing in the middle, Energized Smelter on the left, Combiner on the right, Advanced Control Circuits above and below, Basic Alloys in the corners.

**Upgrades:** Speed, Energy, Muffling.

**Side config:** items and energy.

### Recipes

The Alloyer's built-in recipes are for other mods and only load when that mod is installed:

| Mod | Results |
|---|---|
| Applied Energistics 2 | Fluix Crystal, Logic / Calculation / Engineering Processors |
| Ender IO | Conductive Alloy, Energetic Alloy, Dark Steel, End Steel, Industrial Insulation, Red Dye |
| Thermal Series | Signalum, Lumium, Enderium, Hardened Glass |
| Thermal Endergy | Melodium, Prismalium, Stellarium |

Check JEI for the exact inputs. Modpacks can add more with the `evolvedmekanism:alloying` recipe type (`main_input`, `extra_input`, `second_extra_input`, `output`).

## Alloying Factories

Factory versions of the Alloyer, which run several recipes at once. They work like Mekanism's own factories and can be upgraded in place with Tier Installers.

| Factory | Recipe (crafting table) |
|---|---|
| Basic | Alloyer + Basic Alloys, Basic Circuits, Iron Ingots |
| Advanced | Basic factory + Infused Alloys, Advanced Circuits, Osmium Ingots |
| Elite | Advanced factory + Reinforced Alloys, Elite Circuits, Gold Ingots |
| Ultimate | Elite factory + Atomic Alloys, Ultimate Circuits, Diamonds |

With **Mekanism Extras** installed you also get Absolute, Supreme, Cosmic and Infinite Alloying Factories, and the Alloyer and its factories accept the creative upgrade.

## Chemixer

Combines two items with a chemical to make a new item. This is how you make Plaslitherite. It uses the same energy settings as the Combiner.

**Recipe (Mekanism data-keeping craft):** Alloyer in the middle, Osmium Ingots on the left and right, Dense Control Circuits above and below, Singular Alloys in the corners.

**Upgrades:** Speed, Energy, Muffling.

**Side config:** items, chemicals and energy. It also works with computers (as `chemixer`).

### Recipes

| Main input | Extra input | Chemical | Result |
|---|---|---|---|
| Netherite Ingot | 3 HDPE Pellets | 100 mB Lithium | Plaslitherite Ingot |
| Netherite Dust | 3 HDPE Pellets | 100 mB Lithium | Plaslitherite Dust |
| Netherite Block | 3 HDPE Sheets | 900 mB Lithium | Plaslitherite Block |

Modpacks can add more with the `evolvedmekanism:chemixing` recipe type (`main_input`, `extra_input`, `chemical_input`, `output`).
