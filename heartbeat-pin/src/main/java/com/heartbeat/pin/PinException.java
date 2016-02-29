package com.heartbeat.pin;

/**
 * Generic Pin exception.
 */
public class PinException extends Exception {
    public PinException(String message) {
        super(message);
    }

    public PinException() {
    }

    public PinException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinException(Throwable cause) {
        super(cause);
    }

    public PinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
