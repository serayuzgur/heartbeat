package com.heartbeat.pin.listener;

import com.heartbeat.log.Logger;
import com.heartbeat.pin.Pin;
import com.heartbeat.pin.PinChangeListener;

import java.io.IOException;

/**
 * PinListener implementation with polling lastModified of the pin.
 * It is high speed but resource consuming.
 */
public class PollerPinListener extends Thread {
    private static final String TAG = PollerPinListener.class.getName();
    private final Pin pin;
    private long lastModified;
    private boolean closed = false;
    private PinChangeListener changeListener;


    public PollerPinListener(Pin pin) throws IOException {
        super();
        this.pin = pin;
        lastModified = pin.getPath().lastModified();
        setPriority(Thread.MAX_PRIORITY);

    }

    public void run() {
        Logger.info(TAG, "Modified %s STARTED", pin.getCode());
        while (!closed) {
            long lastModified = pin.getPath().lastModified();
            if (this.lastModified != lastModified) {
                Logger.debug(TAG, "Modified %s", pin.getCode());
                this.lastModified = lastModified;
                onChange(pin);
            }
            try {
                sleep(20);
            } catch (InterruptedException e) {
                Logger.debug(TAG, "Waked %s", pin.getCode());
            }

        }

    }

    private void onChange(Pin pin) {
        if (changeListener != null)
            changeListener.onChange(pin);
    }

    public void close() {
        this.closed = true;
        this.interrupt();

    }

    public PinChangeListener getChangeListener() {
        return changeListener;
    }

    public void setChangeListener(PinChangeListener changeListener) {
        this.changeListener = changeListener;
    }
}
