package com.heartbeat.common.cli;

/**
 * A class for checked Argument exceptions
 */
public class ArgumentException extends Exception {
    public ArgumentException(String message) {
        super(message);
    }
}
