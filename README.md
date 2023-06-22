# VoidTeleport

Minecraft Spigot plugin for preventing players from falling into the void on hub / lobby servers.
Supports bukkit, spigot, and paper builds from minecraft version `1.13` to the latest available server version.
Other forks have not been tested, but due to the plugin's simplicity it will most likely work out of the box.

## Usage

This plugin is very simple.
Firstly, a server administrator uses the `voidteleport` command to setup the spawn location.
Then, all players who fall into the void (height under a configurable threshold), are automatically teleported there.
Configuration is persisted in a `config.yml` file under `plugins/VoidTeleport/`.

### Commands

`/voidteleport`, or `/vtp`: manage the plugin.
Usage: `/<command> [reload|set|test]`.
  - `reload`: reloads the configuration.
  - `set`: sets the spawn location to your current location and orientation.
  - `test`: test the spawn location by teleporting you to it.

### Configuration

The following configuration entries are used:

- `spawn`: configured spawn world, location, and orientation for players.
- `void_height`: height below which players are considered to be in the void.

Remember to use `/vtp reload` after making configuration changes.
Possible config options are documented more in depth in the config file itself.

### Permission nodes

- `voidteleport.manage`: full access to the `/vtp` command and subcommands. Given to server operators by default.

## License

This project is released under the MIT license:

```
VoidTeleport, minecraft Spigot plugin for preventing players from falling into the void on hub / lobby servers.
Copyright © 2023 Antonio de Haro

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the "Software"),
to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
