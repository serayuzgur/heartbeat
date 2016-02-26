package com.heartbeat.pin;

import static java.lang.String.format;

/**
 * Enum which holds pin operations and their native command templates for them.
 */
public enum Operation {
    GET_MODE("cat /sys/class/gpio/gpio%s/direction"),//in/out
    SET_MODE_IN("echo in > /sys/class/gpio/gpio%s/direction"),
    SET_MODE_OUT("echo out > /sys/class/gpio/gpio%s/direction"),
    ENABLE("echo %s | sudo tee /sys/class/gpio/export"),
    DISABLE("echo %s | sudo tee /sys/class/gpio/unexport"),
    READ("cat /sys/class/gpio/gpio%s/value"),
    WRITE_HIGH("echo 1 > /sys/class/gpio/gpio%s/value"),
    WRITE_LOW("echo 0 > /sys/class/gpio/gpio%s/value");

    private final String command;

    Operation(String command) {
        this.command = command;
    }

    /**
     * Returns sys command template of the operation.
     *
     * @return
     */
    public String command() {
        return command;
    }

    /**
     * Returns sys command of the operation for the given Pin.
     *
     * @return
     */
    public String command(Pin pin) {
        return format(command(), pin.getCode());
    }
}
