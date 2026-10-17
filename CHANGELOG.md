# Changelog

All notable changes to this project will be documented in this file.

The format is based on "Keep a Changelog" and this project follows [Semantic Versioning](https://semver.org/).

## [1.21.1-1.3.2] - 2026-10-17

### Fixed
- **Hundreds of errors in the log with Mekanism: MoreMachine.** MoreMachine still loaded its recipes and loot tables for the Evolved Mekanism factories that 1.3.1 turned off, and they all failed. They are now skipped.

## [1.21.1-1.3.1] - 2026-10-16

### Fixed
- **Crash on startup with Mekanism: MoreMachine.** MoreMachine tried to add factories for Evolved Mekanism tiers this version doesn't have, which broke mod loading. Its Evolved Mekanism support is now turned off.

## [1.21.1-1.3.0] - 2026-10-16

### Fixed
- **Addons made for Evolved Mekanism 1.2+ (like More Thermal Evaporation) failing to load.** The version went back to 1.0.0 with the CurseForge rename, so their version check failed. It now continues from the 1.20.1 versions.

## [1.21.1-1.0.0] - 2026-10-14

First release of Evolved Mekanism APT Only Neo, the NeoForge 1.21.1 port of Evolved Mekanism APT Only.
Needs Mekanism 10.7 and Iglee Lib 1.2.

### Added
- **Everything from the 1.20.1 version, now on NeoForge 1.21.1:** the APT, Better Gold, Plaslitherite, Refined Redstone, Netherrack and End Stone Uranium Ore, alloys and alloy blocks, circuits, the Alloyer, the Chemixer and the Supercharging Elements.
- **Alloying factories** still work with Mekanism 10.7's factory changes and can be upgraded with tier installers.
- **Mekanism Extras support:** Absolute to Infinite alloying factories and the creative upgrade, when Mekanism Extras is installed.
- **Alloyer recipes for other mods** load when those mods are installed: AE2 processors and fluix crystals, and Ender IO alloy.
- **JEI** shows the Alloyer, Chemixer and APT recipes.

### Changed
- **The APT, Alloyer and Chemixer use Mekanism 10.7's chemicals.** Gases, infuse types, pigments and slurries are now all chemicals, like in Mekanism itself.

