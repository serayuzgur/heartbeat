package com.heartbeat.pin;

/**
 * Listener Interface for pin value changes
 */
public interface PinChangeListener {

    /**
     * Will be triggered on pin changes.
     * @param pin modified pin
     */
    void onChange(Pin pin);
}
