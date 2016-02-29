package com.heartbeat.pin.command;

import com.heartbeat.pin.PinException;

/**
 * An exception class which is thrown on invalid pin commands.
 * For ex. writing to the pin while it's mode IN
 * or reading the pin while it's mode OUT
 */
public class PinCommandException extends PinException {

    public PinCommandException(String message) {
        super(message);
    }

    public PinCommandException() {
    }

    public PinCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinCommandException(Throwable cause) {
        super(cause);
    }

    public PinCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
