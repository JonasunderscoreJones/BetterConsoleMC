package me.jonasjones.betterconsolemc.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.jonasjones.betterconsolemc.BetterConsoleMC;
import me.jonasjones.betterconsolemc.cmdconfig.CmdConfigHandler;
import me.jonasjones.betterconsolemc.system.ShellCommand;
import me.jonasjones.betterconsolemc.util.CommandPreRegistry;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.MessageArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static me.jonasjones.betterconsolemc.BetterConsoleMC.COMMANDPREREGISTRY;

public class GameCommandHandler {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {

        serverCommandSourceCommandDispatcher.register((CommandManager.literal(COMMANDPREREGISTRY.getIngamecommand()).requires(source -> source.hasPermissionLevel(COMMANDPREREGISTRY.getPermissionLevel()))
                .executes((context -> {
                    context.getSource().sendFeedback(runCommand(COMMANDPREREGISTRY, context), COMMANDPREREGISTRY.doBroadcastToOp());
                    return 1;
                })))
        );
        }
    public static Text runCommand(CommandPreRegistry commandPreRegistry, CommandContext<ServerCommandSource> context) {
        new Thread(() -> {ShellCommand.execute(commandPreRegistry, context);}).start();
        return Text.of("Return");
    }
    public static void returnCommandOutput(String cmd, String commandFeedback, CommandContext<ServerCommandSource> context) {
        String consoleLog = "  [" + cmd + "]: " + commandFeedback;
        BetterConsoleMC.LOGGER.info(consoleLog);
    }
}
