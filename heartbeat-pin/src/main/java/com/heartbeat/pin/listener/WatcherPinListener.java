package com.heartbeat.pin.listener;

import com.heartbeat.log.Logger;
import com.heartbeat.pin.Pin;
import com.heartbeat.pin.PinChangeListener;
import com.sun.nio.file.SensitivityWatchEventModifier;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * PinListener implementation with WatchService. It is a native listener with recourse friendly implementation.
 * Speed changes according to the OS you ar using.
 * For OSX response time is 2 sec.
 * LINUX ? sec.
 * WINDOWS ? sec.
 */
public class WatcherPinListener extends Thread {
    private static final String TAG = WatcherPinListener.class.getName();
    private final Pin pin;
    private final WatchService service;
    private final WatchKey key;
    private PinChangeListener changeListener;
    private boolean closed = false;


    public WatcherPinListener(Pin pin) throws IOException {
        super();
        this.pin = pin;
        service = FileSystems.getDefault().newWatchService();

        try {
            key = pin.getPath().toPath().getParent().register(service, new WatchEvent.Kind[]{ENTRY_MODIFY}, SensitivityWatchEventModifier.HIGH);

        } catch (IOException e) {
            Logger.error(TAG, "Error at registering pin %s", e, pin.getCode());
            throw e;
        }
    }

    public void run() {
        Logger.info(TAG, "Modified %s STARTED", pin.getCode());
        while (!closed) {
            WatchKey key = null;
            try {
                key = service.take();
            } catch (InterruptedException e) {
            }
            if (this.key.equals(key)) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (!kind.equals(ENTRY_MODIFY) || !event.context().toString().equals(pin.getPath().getName()))
                        continue;
                    onChange(pin);
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    private void onChange(Pin pin) {
        if (changeListener != null)
            changeListener.onChange(pin);
    }


    public void close() {
        try {
            closed = true;
            service.close();
        } catch (IOException e) {
            Logger.error(TAG, "Error at closing listener for pin %s", e, pin.getCode());
        }
    }

    public void setChangeListener(PinChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public PinChangeListener getChangeListener() {
        return this.changeListener;
    }
}
