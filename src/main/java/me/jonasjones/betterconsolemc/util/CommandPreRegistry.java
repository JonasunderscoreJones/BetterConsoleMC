package me.jonasjones.betterconsolemc.util;

import java.util.List;

public class CommandPreRegistry { //TODO: Add timeout before command can be ran again (unixtimestamp)
    private Constants.CmdMode commandmode;
    private String command;
    private int permissionLevel;
    private int execTimeout;
    private String ingamecommand;
    private int execRerunTimeout;
    private boolean broadcastToOp;

    public void init(Constants.CmdMode commandmode, String command, int permissionLevel, int execTimeout, String ingamecommand, int execRerunTimeout, boolean broadcastToOp) {
        this.commandmode = commandmode;
        this.command = command;
        this.permissionLevel = permissionLevel;
        this.execTimeout = execTimeout;
        this.ingamecommand = ingamecommand;
        this.execRerunTimeout = execRerunTimeout;
        this.broadcastToOp = broadcastToOp;
    }

    public Constants.CmdMode getCommandmode() {
        return this.commandmode;
    }

    public String getCommand() {
        return this.command;
    }

    public int getPermissionLevel() {
        return this.permissionLevel;
    }

    public int getExecTimeout() {
        return this.execTimeout;
    }

    public String getIngamecommand() {
        return this.ingamecommand;
    }
    public int getExecRerunTimeout() {
        return this.execRerunTimeout;
    }

    public boolean doBroadcastToOp() {
        return this.broadcastToOp;
    }
}
