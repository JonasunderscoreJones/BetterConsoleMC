package me.jonasjones.betterconsolemc.exceptions;

public class ExecTimeoutException extends Exception {
    public ExecTimeoutException(int error) {
        super("Execute Timeout '" + error + "' is invalid!");
    }
}
