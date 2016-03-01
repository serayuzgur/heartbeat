package com.heartbeat.pin.mapping;

/**
 * An interface for Pin mappings
 */
public interface PinCode {
    /**
     * Returns the OS code of the pin.
     *
     * @return
     */
    String getCode();

    /**
     * Returns the hardware code of the pin.
     *
     * @return
     */
    String getHwCode();
}
