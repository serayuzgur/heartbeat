package com.heartbeat.pin.command;

import com.heartbeat.pin.Pin;

import java.io.File;

/**
 * An interface for pin commands
 */
public interface PinCommand {

    /**
     * Gets the mode of given pin from system.
     *
     * @param pin
     */
    Pin.Mode getMode(Pin pin) throws PinCommandException;

    /**
     * Gets the mode and the pin instance and sets to the system.
     *
     * @param pin
     */
    void setMode(Pin pin, Pin.Mode mode) throws PinCommandException;

    /**
     * Enables the given pin from system.
     *
     * @param pin
     */
    void enable(Pin pin) throws PinCommandException;

    /**
     * Disable the given pin from system.
     *
     * @param pin
     */
    void disable(Pin pin) throws PinCommandException;

    /**
     * Reads the given film from system.
     *
     * @param pin
     * @return
     */
    boolean read(Pin pin) throws PinCommandException;

    /**
     * Writes the given pins value from the system.
     *
     * @param pin
     * @param value
     * @return
     */
    void write(Pin pin, boolean value) throws PinCommandException;


    /**
     * Returns the path of the pin.
     *
     * @param pin
     * @return
     */
    File path(Pin pin);
}
