package com.heartbeat.pin.listener;

/**
 * An Interface for Pin listening. Support two different king of approach.
 *
 * @see PollerPinListener
 * @see WatcherPinListener
 */
public interface PinListener {

    /**
     * Starts the Pin Listener
     */
    void run();

    /**
     * Stops the Pin Listener
     */
    void close();
}
