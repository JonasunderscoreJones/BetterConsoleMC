package me.jonasjones.betterconsolemc.cmdconfig;

import me.jonasjones.betterconsolemc.BetterConsoleMC;
import me.jonasjones.betterconsolemc.modconfig.ModConfigProvider;
import me.jonasjones.betterconsolemc.modconfig.SimpleConfig;

import static me.jonasjones.betterconsolemc.BetterConsoleMC.ISWINDOWS;

public class CmdConfigs {
    public static SimpleConfig CMDCONFIG;
    private static ModConfigProvider cmdconfigs;

    public static void registerConfigs() {
        cmdconfigs = new ModConfigProvider();
        createConfigs();

        CMDCONFIG = SimpleConfig.of(BetterConsoleMC.MODID + "-commands_config").provider(cmdconfigs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        cmdconfigs.addSingleLineComment("This is the command configuration file for BetterConsoleMC");
        if (ISWINDOWS) {
            cmdconfigs.addSingleLineComment("This message is there to remind you that the mod is running on a WINODWS machine");
        } else {
            cmdconfigs.addSingleLineComment("This message is there to remind you that the mod is running on a UNIX-LIKE machine");
        }
        cmdconfigs.addSingleLineComment("The general syntax of defining a command goes like this:");
        cmdconfigs.addSingleLineComment("[Command Mode] [Permissione Level] [Execution Timeout] [Execution Block Timeout] [Broadcast to OP] [Ingame Command name] [command to execute]");
        cmdconfigs.addSingleLineComment("Documentation and examples are available at the wiki of the repository");
    }

    private static void assignConfigs() {

        SimpleConfig.LOGGER.info("All " + cmdconfigs.getConfigsList().size() + " have been set properly");
    }
}
