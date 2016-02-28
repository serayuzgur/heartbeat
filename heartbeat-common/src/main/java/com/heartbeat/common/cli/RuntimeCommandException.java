package com.heartbeat.common.cli;

/**
 * An exception class for system commands
 */
public class RuntimeCommandException extends Exception {
    public RuntimeCommandException(String message) {
        super(message);
    }

    public RuntimeCommandException(Throwable throwable) {
        super(throwable);
    }
}
