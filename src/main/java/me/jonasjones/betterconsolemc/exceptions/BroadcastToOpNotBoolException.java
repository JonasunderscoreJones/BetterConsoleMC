package me.jonasjones.betterconsolemc.exceptions;

public class BroadcastToOpNotBoolException extends Exception {
    public BroadcastToOpNotBoolException() {
        super("Value of BroadcastToOP is not a boolean!");
    }
}
