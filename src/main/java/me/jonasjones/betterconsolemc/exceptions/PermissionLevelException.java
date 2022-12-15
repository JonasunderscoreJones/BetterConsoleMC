package me.jonasjones.betterconsolemc.exceptions;

public class PermissionLevelException extends Exception {
    public PermissionLevelException(int error) {
        super("Permission level '" + Integer.toString(error) + "' is invalid!");
    }
}
