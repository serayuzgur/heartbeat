package com.heartbeat.pin.command;

/**
 * An exception class which is thrown on invalid pin commands.
 * For ex. writing to the pin while it's mode IN
 * or reading the pin while it's mode OUT
 */
public class PinCommandException extends Exception {
    public PinCommandException(String message) {
        super(message);
    }

    public PinCommandException(Throwable throwable) {
        super(throwable);
    }
}
