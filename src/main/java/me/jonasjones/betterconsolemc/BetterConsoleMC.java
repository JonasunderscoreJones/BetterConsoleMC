package me.jonasjones.betterconsolemc;

import me.jonasjones.betterconsolemc.cmdconfig.CmdConfigHandler;
import me.jonasjones.betterconsolemc.cmdconfig.CmdConfigs;
import me.jonasjones.betterconsolemc.modconfig.ModConfigs;
import me.jonasjones.betterconsolemc.util.CommandPreRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

import static me.jonasjones.betterconsolemc.command.GameCommandHandler.registerCommands;

public class BetterConsoleMC implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static String MODID = "betterconsolemc";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static boolean ISWINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    public static Path CONFIGDIR = FabricLoader.getInstance().getConfigDir();
    public static CommandPreRegistry COMMANDPREREGISTRY;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        ModConfigs.registerConfigs();
        CmdConfigs.registerConfigs();
        LOGGER.info("BetterConsoleMC initialized!");
        try {
            CmdConfigHandler.getCommands(String.valueOf(CONFIGDIR.resolve(BetterConsoleMC.MODID + "-commands_config.properties")));
            registerCommands();
        } catch (Exception e) {
            ModConfigs.IS_ENABLED = false;
            LOGGER.error("SYNTAX ERROR(S) IN COMMAND CONFIG FILE OF BETTERCONSOLEMC");
            LOGGER.error("For security reasons, the mod has beeen disabled and all commands are nonfunctional");
        }
    }
}
