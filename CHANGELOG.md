# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [UNRELEASED]

### Added

- Added support for debugging cyclic wisps with [HexDebug](https://modrinth.com/mod/hexdebug)!
- Added Gatekeeper's Purification to check whether a gate currently has a valid destination.

### Changed

- Increased minimum dependency versions:
  - Fabric Loader: `0.16`
  - Fabric Language Kotlin: `1.10.18+kotlin.1.9.22`

### Fixed

- Fixed internal error when an entity-anchored gate can't find its entity, by Dominik-Pawelec in [#204](https://github.com/FallingColors/Hexal/pull/204).

## `0.3.1` - 2025-10-30

### Changed

- Updated zh_cn translations, by ChuijkYahus in [#179](https://github.com/FallingColors/Hexal/pull/179).
- Disabled the everbook size limit in singleplayer, by beholderface in [#185](https://github.com/FallingColors/Hexal/pull/185).

### Fixed

- Fixed several bugs with gates, by Robotgiggle in [#184](https://github.com/FallingColors/Hexal/pull/184):
  - Removed arbitrary 32k block range limit.
  - Fixed gates not working for entities with passengers.
  - Fixed a crash caused by teleport-related changes in Hex Casting 0.11.3.
  - Improved behaviour when teleporting stacks of entities with gates.
- Development: Removed `hexal.serialization-hooks` dependency on Fabric.

## `0.3.0` - 2025-10-14

### Added

- Added a pattern called Preview Craft which returns a list of Item Stack iotas representing what Craft would have returned for the same inputs.

### Changed

- Updated to Minecraft 1.20.1!
- Moved Hexal into the Falling Colors organization.
- Published Hexal to https://maven.hexxy.media.
- Added entity iotas to the Everbook blacklist.
- Changed Stacking Distillation II to also accept Item Stack iotas as input.
- Sorter's Purification can now convert motes to Item Stack iotas.
- Disabled several unnecessary log messages.
- Wisps now initialise their ravenmind to the ravenmind of their caster when they are made.
- Polished the Everbook, by beholderface in [#173](https://github.com/FallingColors/Hexal/pull/173):
  - Everbook data is now compressed before sending it to the server to minimize packet size related issues.
  - Added a 10-second timer (configurable) to save the Everbook to disk, rather than only saving on logout.
  - Macros now play a single context-appropriate sound when drawn, rather than playing the sounds of potentially hundreds of patterns at once. 
- Added a configurable server-side Everbook size limit, by beholderface in [#177](https://github.com/FallingColors/Hexal/pull/177).
- Updated zh_cn translations, by ChuijkYahus in [#115](https://github.com/FallingColors/Hexal/pull/115), [#132](https://github.com/FallingColors/Hexal/pull/132), and [#170](https://github.com/FallingColors/Hexal/pull/170).

### Fixed

- Fixed weird behaviour when casting Break Block on a Mote Nexus.
- Fixed several bugs related to bound wisps.
- Fixed a crash when setting the bound wisp to null.
- Fixed Place Block II not working in survival mode.
- Fixed a crash when saving a wisp in a Create schematic.
- Fixed several typos in Hexal's notebook content.
- Fixed a server crash with Phase Block, caused by an issue in Lib39.
- Fixed Phase Block not checking for edit permissions.
- Fixed a broken error message when attempting to use Phase Block on an unbreakable block.
- Fixed the Everbook not checking for illegal iotas inside of anything other than lists (eg. bubbles), by kineticneticat in [#159](https://github.com/FallingColors/Hexal/pull/159).

### Removed

- Moved type iotas into MoreIotas.
- Moved Thanatos' Reflection into Hex Casting.

## Previous versions

See https://modrinth.com/mod/hexal/changelog for changelogs from previous versions.
