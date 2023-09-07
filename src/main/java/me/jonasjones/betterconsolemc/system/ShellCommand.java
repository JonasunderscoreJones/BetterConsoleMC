package me.jonasjones.betterconsolemc.system;

import com.mojang.brigadier.context.CommandContext;
import me.jonasjones.betterconsolemc.BetterConsoleMC;
import me.jonasjones.betterconsolemc.command.GameCommandHandler;
import me.jonasjones.betterconsolemc.modconfig.ModConfigs;
import me.jonasjones.betterconsolemc.util.CommandPreRegistry;
import net.minecraft.server.command.ServerCommandSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommand {
    public static String execute(CommandPreRegistry command, CommandContext<ServerCommandSource> context) {

        StringBuilder output = new StringBuilder();

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (BetterConsoleMC.ISWINDOWS) {
            processBuilder.command("cmd.exe", "/c", command.getCommand());
        } else {
            processBuilder.command("bash", "-c", command.getCommand());
        }

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (ModConfigs.LOG_CMD_OUTPUT) {
                    GameCommandHandler.returnCommandOutput(command.getCommand(), line, context);
                }
                //get the ingame command name


                output.append("[").append(command.getIngamecommand()).append("]").append(line).append("\n");
            }

            // Read buffered reader line by line and append to output with prefix of command before each line

            int exitCode = process.waitFor();
            BetterConsoleMC.LOGGER.info("Exited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
