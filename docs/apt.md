# Antimatter Protomolecular Transmutator (APT)

The APT is a multiblock that combines an item with a chemical to make a new item. It is how you make Better Gold and the Supercharging Element MK2.

## Blocks

| Block | Recipe |
|---|---|
| **APT Casing** | SPS Casing + 50 mB Antimatter in the Antiprotonic Nucleosynthesizer |
| **APT Port** | 4 APT Casings around a Quantum Control Circuit (crafting table) |
| **Supercharging Element MK1** | Superheating Element in the middle, 4 Singular Alloys in the corners, 4 Refined Redstone Ingots on the sides (crafting table) |
| **Supercharging Element MK2** | Supercharging Element MK1 + 250 mB Antimatter in the APT |

## Building it

The APT is a **7 × 5 × 7** shell (7 wide, 5 tall, 7 deep) with rounded corners, a lot like the SPS but shorter.

Legend: `C` = APT Casing only · `P` = APT Casing **or** APT Port · `.` = not part of the structure, leave it empty or put anything there

**Bottom layer and top layer** (seen from above):

```
. . C C C . .
. C P P P C .
C P P P P P C
C P P P P P C
C P P P P P C
. C P P P C .
. . C C C . .
```

**Each of the 4 side walls** (seen from the side, top row = top layer):

```
. . C C C . .
. C P P P C .
C P P P P P C
. C P P P C .
. . C C C . .
```

That comes to 52 casing-only spots and 86 spots that take a casing or a port, so you need at least **138 APT Casings and Ports** in total.

The inside (5 × 3 × 5) must be air, except for Supercharging Elements on the lowest inside layer (see below).

Ports can't go on the `C` spots. The structure forms automatically when it is complete; right-click any casing or port to open the GUI.

## Using it

Ports are how you get things in and out. Every port gives access to the whole APT:

- **Energy** goes into the shared energy buffer (10 MJ by default).
- **Chemicals** go into the input tank (5,000 mB by default). It only accepts chemicals that some APT recipe uses.
- **Items** go into the input slot. It only accepts items that some APT recipe uses.
- Finished items sit in the output slot. Pull them out of any port with a pipe or transporter.

Sneak + right-click a port to switch it between input and output mode. A comparator next to a port reads how full the input tank is.

While a recipe runs, the APT uses **100,000 J per tick** (by default). It pauses when it runs out of energy or the output slot is full.

### Recipe time

```
time (ticks) = 200 × (chemical amount ÷ 100) ÷ (1 + speed bonus)
```

So with no Supercharging Elements a 100 mB recipe takes 200 ticks (10 s) and a 900 mB recipe takes 1,800 ticks (90 s).

### Default recipes

| Item | Chemical | Result | Base time |
|---|---|---|---|
| Gold Ingot | 100 mB Spent Nuclear Waste | Better Gold Ingot | 10 s |
| Gold Dust | 100 mB Spent Nuclear Waste | Better Gold Dust | 10 s |
| Gold Block | 900 mB Spent Nuclear Waste | Better Gold Block | 90 s |
| Supercharging Element MK1 | 250 mB Antimatter | Supercharging Element MK2 | 25 s |

## Supercharging Elements

Supercharging Elements make the APT faster. Place them **inside** the APT, on the **lowest inside layer** (the layer just above the bottom casing). That layer is 5 × 5, so it fits up to 25 elements. Anywhere else and the APT won't form ("invalid Supercharging Element arrangement").

| Element | Speed bonus (default) |
|---|---|
| MK1 | +25% each |
| MK2 | +250% each |

Bonuses add up, and you can mix MK1 and MK2. You don't need to fill the layer; even one element helps. Some examples for a 100 mB recipe:

| Elements | Speed bonus | Time |
|---|---|---|
| none | 0% | 200 ticks (10 s) |
| 4 × MK1 | +100% | 100 ticks (5 s) |
| 25 × MK1 | +625% | 27 ticks |
| 1 × MK2 | +250% | 57 ticks |
| 25 × MK2 | +6,250% | 3 ticks |

The elements are counted when the APT forms. If you add or remove one, the APT re-forms and picks up the change.

## Computer support

Ports work with ComputerCraft through Mekanism's computer integration:

- `getInput`, `getInputCapacity`, `getInputNeeded`, `getInputFilledPercentage`: the input tank
- `getInputSlot`, `getOutputSlot`: the item slots
- `getSuperchargers`: the total speed bonus (1.0 = +100%)
- `getMode` / `setMode(boolean)`: the port mode (`true` = output, `false` = input)

Mekanism's usual multiblock methods (energy, formed state and so on) work too.

## Config

The APT settings are in `serverconfig/EvolvedMekanism/general.toml` in your world folder (server config, synced to clients), under `[general.apt]`.

| Option | Default | What it does |
|---|---|---|
| `inputPerAntimatter` | 5000 | Input tank size in mB |
| `defaultDuration` | 200 | Base recipe time in ticks for 100 mB of chemical |
| `energyStorage` | 10000000 | Energy buffer in Joules |
| `energyPerInput` | 100000 | Energy used per tick while running, in Joules |
| `mk1Percent` | 25 | Speed bonus per MK1 element, in percent |
| `mk2Percent` | 250 | Speed bonus per MK2 element, in percent |
