package me.jonasjones.betterconsolemc.cmdconfig;

import me.jonasjones.betterconsolemc.BetterConsoleMC;
import me.jonasjones.betterconsolemc.exceptions.BroadcastToOpNotBoolException;
import me.jonasjones.betterconsolemc.exceptions.CommandModeException;
import me.jonasjones.betterconsolemc.exceptions.ExecTimeoutException;
import me.jonasjones.betterconsolemc.exceptions.PermissionLevelException;
import me.jonasjones.betterconsolemc.util.CommandPreRegistry;
import me.jonasjones.betterconsolemc.util.Constants.CmdMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CmdConfigHandler {
    public static List<CommandPreRegistry> FULLREG;

    public static void getCommands(String path) throws IOException {
        List<CommandPreRegistry> commands = new ArrayList<CommandPreRegistry>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int linecount = 1;
        String linecontent = reader.readLine();
        while (linecontent != null) {
            if (!linecontent.startsWith("#") || linecontent.equals("\n")) {
                try {
                    commands.add(preRegisterCommands(linecontent));
                } catch (Exception e) {
                    BetterConsoleMC.LOGGER.error("ERROR WHILE PREREGISTERING COMMAND IN CONFIG FILE IN LINE " + linecount);
                    BetterConsoleMC.LOGGER.error(String.valueOf(e));
                }
            }
            linecontent = reader.readLine();
            linecount++;
        }
        FULLREG = commands;
    }

    public static CommandPreRegistry preRegisterCommands(String configCommandDef) throws Exception {
        CommandPreRegistry preRegisteredCommand = new CommandPreRegistry();
        CmdMode commandMode = getCommandMode(configCommandDef);
        int permissionLevel = getPermissionLevel(configCommandDef);
        int execTimeout = getExecTimeout(configCommandDef);
        int execRerunTimeout = getExecRerunTiemout(configCommandDef);
        boolean broadcastToOP = doBroadcastToOP(configCommandDef);
        String ingameCommand = getIngameCommand(configCommandDef);
        String command = getCommand(configCommandDef);

        preRegisteredCommand.init(commandMode, command, permissionLevel, execTimeout, ingameCommand, execRerunTimeout, broadcastToOP);

        return preRegisteredCommand;
    }

    private static CmdMode getCommandMode(String configCommandDef) throws Exception {
        if (configCommandDef.startsWith("SIMPLE ")) {
            return CmdMode.SIMPLE;
        } else if (configCommandDef.startsWith("RETURN ")) {
            return CmdMode.RETURN;
        } else {
            throw new CommandModeException(getSplitCommandPartByIndex(configCommandDef, 0));
        }
    }

    private static int getPermissionLevel(String configCommandDef) throws PermissionLevelException {
        if (-1 < Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 1))) {
            return Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 1));
        } else {
            throw new PermissionLevelException(Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 1)));
        }
    }

    private static int getExecTimeout(String configCommandDef) throws ExecTimeoutException {
        if (-1 < Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 2))) {
            return Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 2));
        } else {
            throw new ExecTimeoutException(Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 2)));
        }
    }

    private static String getIngameCommand(String configCommandDef) {
        return getSplitCommandPartByIndex(configCommandDef, 5);
    }

    private static int getExecRerunTiemout(String configCommandDef) {
        return Integer.parseInt(getSplitCommandPartByIndex(configCommandDef, 3));
    }

    private static String getBroadcastToOP(String configCommandDef) throws BroadcastToOpNotBoolException {
        return getSplitCommandPartByIndex(configCommandDef, 4);
    }

    private static boolean doBroadcastToOP(String configCommandDef) throws BroadcastToOpNotBoolException {
        if (getSplitCommandPartByIndex(configCommandDef, 4).equals("true")) {
            return true;
        } else if (getSplitCommandPartByIndex(configCommandDef, 5).equals("false")) {
            return false;
        } else {
            throw new BroadcastToOpNotBoolException();
        }
    }

    private static String getCommand(String configCommandDef) throws Exception {
        return configCommandDef.replaceFirst(getCommandMode(configCommandDef).name() + " " + getPermissionLevel(configCommandDef) + " " + getExecTimeout(configCommandDef) + " " + getExecRerunTiemout(configCommandDef) + " " + getBroadcastToOP(configCommandDef) + " " + getIngameCommand(configCommandDef) + " ", "")
                .replaceFirst("\"", "")
                .replaceAll("\"$", "");
    }

    private static String getSplitCommandPartByIndex(String configCommandDef, int index) {
        return List.of(configCommandDef.split(" ")).get(index);
    }
}
