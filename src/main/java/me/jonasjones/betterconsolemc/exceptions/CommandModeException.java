package me.jonasjones.betterconsolemc.exceptions;

public class CommandModeException extends Exception {
    public CommandModeException(String error) {
        super("Command Mode '" + error + "' is invalid!");
    }
}
