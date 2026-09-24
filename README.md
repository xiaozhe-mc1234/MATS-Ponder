> This mod pack is called **Mechanical and Aerospace Technology Studies: A Four-Season Nature Leisure Journey** (简称 M&ATSASNLJ)—「机械航空科技学：四季自然休闲之旅」。

# MOD-PACK Ponder

A Ponder addon for **Create** on NeoForge 1.21.1. It adds Ponder scenes to blocks from
**Applied Energistics 2**, **Supplementaries** and **Amendments**.

Mod ID: `cufmponder` — Version 1.0.0 — License: MIT

## What it does

Create ships a Ponder system (the in-game tutorial you open with the Ponder key). Its Ponder index
only covers Create's own blocks, so blocks added by other mods have no tutorial at all. This addon
registers its own Ponder plugin and fills that gap: every AE2, Supplementaries and Amendments block
that had no scene gets one.

Hover a supported block in the inventory or in JEI and press the Ponder key (default `W`).

## Scene coverage

| Source | Scenes | Notes |
| --- | --- | --- |
| Applied Energistics 2 | 102 | 7 hand-written (ME Controller, ME Drive, Energy Acceptor, Charger, Inscriber, Quantum Ring, Autocrafting) + 95 generated |
| Supplementaries | 254 | 2 hand-written (Spring Launcher, Rope and Pulley) + 252 generated |
| Amendments | 31 | generated |
| **Total** | **387** | 1177 language entries in `en_us` and `zh_cn` |

Three Ponder categories are registered, so the Ponder UI shows a side button for each supported mod
that lists every Ponderable block of that mod.

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.248 or newer
- Java 21
- Create 6.0.0 or newer (required, provides the Ponder runtime)

AE2, Supplementaries and Amendments are optional. Blocks are resolved by registry name at runtime and
scenes whose mod is missing are skipped, so those mods can be added or removed at any time.

## Installation

1. Install NeoForge 21.1.248+ for Minecraft 1.21.1.
2. Put `cufmponder-1.0.0.jar` and Create into the `mods` folder.
3. Optionally add AE2, Supplementaries and Amendments.

## Building

    gradlew.bat build      (Windows)
    ./gradlew build        (Linux, macOS)

The jar is written to `build/libs/cufmponder-1.0.0.jar`.

## Project layout

    src/main/java/com/cufm/ponder/                 PonderMod, CufmPonderPlugin, Ae2Scenes, SuppScenes, AutoScenes
    src/main/resources/assets/cufmponder/ponder/   387 scene structures (.nbt)
    src/main/resources/assets/cufmponder/lang/     en_us.json, zh_cn.json
    src/main/templates/META-INF/neoforge.mods.toml mod metadata, placeholders expanded at build time

## License

MIT, see [LICENSE](LICENSE).

The repository skeleton (Gradle wrapper, build script, CI workflow) is taken from the NeoForged MDK
for 1.21.1 (ModDevGradle); see [TEMPLATE_LICENSE.txt](TEMPLATE_LICENSE.txt).
