package me.jonasjones.betterconsolemc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.jonasjones.betterconsolemc.BetterConsoleMC;
import me.jonasjones.betterconsolemc.cmdconfig.CmdConfigHandler;
import me.jonasjones.betterconsolemc.system.ShellCommand;
import me.jonasjones.betterconsolemc.util.CommandPreRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static me.jonasjones.betterconsolemc.BetterConsoleMC.COMMANDPREREGISTRY;

public class GameCommandHandler {

    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            for (CommandPreRegistry commandPreRegistry : CmdConfigHandler.FULLREG) {
                COMMANDPREREGISTRY = commandPreRegistry;
                register(dispatcher, registryAccess, environment, COMMANDPREREGISTRY);
            }
        });
    }

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment, CommandPreRegistry commandPreRegistry) {

        serverCommandSourceCommandDispatcher.register((CommandManager.literal(commandPreRegistry.getIngamecommand()).requires(source -> source.hasPermissionLevel(commandPreRegistry.getPermissionLevel()))
                .executes((context -> {
                    context.getSource().sendFeedback(() -> runCommand(commandPreRegistry, context), commandPreRegistry.doBroadcastToOp());
                    return 1;
                })))
        );
    }

    public static Text runCommand(CommandPreRegistry commandPreRegistry, CommandContext<ServerCommandSource> context) {
        return Text.of(ShellCommand.execute(commandPreRegistry, context));
    }

    public static void returnCommandOutput(String cmd, String commandFeedback, CommandContext<ServerCommandSource> context) {
        String consoleLog = "  [" + cmd + "]: " + commandFeedback;
        BetterConsoleMC.LOGGER.info(consoleLog);
    }
}
