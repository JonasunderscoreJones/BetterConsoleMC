package me.jonasjones.betterconsolemc.system;

import com.mojang.brigadier.context.CommandContext;
import me.jonasjones.betterconsolemc.BetterConsoleMC;
import me.jonasjones.betterconsolemc.command.GameCommandHandler;
import me.jonasjones.betterconsolemc.util.CommandPreRegistry;
import net.minecraft.server.command.ServerCommandSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommand {
    public static int execute(CommandPreRegistry command, CommandContext<ServerCommandSource> context) {

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
                GameCommandHandler.returnCommandOutput(command.getCommand(), line, context);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                BetterConsoleMC.LOGGER.info("Exited with error code : " + exitCode);
            } else {
                BetterConsoleMC.LOGGER.error("Exited with error code : " + exitCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
