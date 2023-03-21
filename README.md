# BetterConsoleMC

<a href="https://github.com/J-onasJones/BetterConsoleMC/blob/master/LICENSE"><img src="https://img.shields.io/github/license/J-onasJones/McWebserver?style=flat&color=900c3f" alt="License"></a>
<a href="https://discord.gg/V2EsuUVmWh"><img src="https://img.shields.io/discord/702180921234817135?color=5865f2&label=Discord&style=flat" alt="Discord"></a>
<a href="https://modrinth.com/mod/betterconsolemc"><img src="https://img.shields.io/modrinth/dt/betterconsolemc?logo=modrinth&label=&style=flat&color=242629&labelColor=00AF5C&logoColor=white" alt="Modrinth"></a>
<a href="https://modrinth.com/mod/betterconsolemc"><img src="https://img.shields.io/modrinth/game-versions/betterconsolemc?logo=modrinth&color=242629&labelColor=00AF5C&logoColor=white"></a>

<center><img src="https://jonasjones.me/uploads/mod-badges/support-fabric.png" width="250px"><img src="https://jonasjones.me/uploads/mod-badges/support-quilt.png" width="250px"></center>

### About the mod
This mod allows for simple ingame command creation to allow for system command execution trough a player on the server.

This mod can be looked at as version 2.0 of [ConsoleMC](https://github.com/J-onasJones/ConsoleMC) which while being powerful enough, has a big security flaw whe used on big and/or public servers in a way that everyone (that is OP) can run any command on the MC server's host system, giving anyone access to the entire machine and allowing for bad actions to be taken by people who shouldn't have access to it.

A detailed documentation with examples is available at [this repo's wiki](https://github.com/J-onasJones/BetterConsoleMC/wiki)

### Fabric/Quilt support goes back to 1.17 Pre-release 1
Forge support isn't part of any of my plans.

### Warning
If used wrongly this mod can be a security risk for your server and all devices connected to the network that your server is in.

Beware that anyone who has write access to the MC server's files is able to edit the config files and therefore the commands at any time.

**Use at your own risk!**

<img src="https://jonasjones.me/uploads/mod-badges/fabric-api.png" width="250px">
<img src="https://jonasjones.me/uploads/mod-badges/available-modrinth.png" width="250px">

### Setup

1. Download the correct version of the mod **including the fabric api if you're using fabric** into your mods folder.
2. Restart Your Minecraft Server and let the mod create the config file. The webserver will be offline by default.
3. In the config file, enable the webserver and adjust all settings if needed.
4. Edit the command config file and add your desired commands!
5. Restart your Minecraft server and You're good to go!

# KNOWN ISSUES
- It is only possible to define a single command at this point - subject to change once I figure out how to do things
- Execution Timeout and Execution block Timeout have no effect on the command at this point.
- The command mode has no effect on the command feedback itself. in any case, the output will be sent in chat
- the console is spamed with debug messages
- threads are not closed after a task exits
- and many more....
