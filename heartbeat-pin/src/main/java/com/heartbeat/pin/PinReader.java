package com.heartbeat.pin;

import com.heartbeat.log.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * A high performance reader class which opens a {@link FileChannel} to the pin.
 * It uses  {@link ByteBuffer} with the size 1 and reuses it for every read.
 * Also reads the value and converts to boolean.
 */
public class PinReader {
    private static final String TAG = PinReader.class.getName();
    private final Pin pin;
    private ByteBuffer VALUE = ByteBuffer.wrap("0".getBytes());

    private final FileChannel fch;

    /**
     * Creates a Reader with {@link FileChannel} for the pin.
     *
     * @param pin
     * @throws IOException
     */
    public PinReader(Pin pin) throws IOException {
        this.pin = pin;
        fch = FileChannel.open(pin.getPath().toPath());
    }

    /**
     * Reads the pin and converts its value to boolean
     * If value of pin is "1" returns true if it is "0" returns false.
     *
     * @return
     * @throws IOException
     */
    public boolean read() throws IOException {
        VALUE.position(0);
        fch.read(VALUE);
        fch.position(0);
        return VALUE.get(0) == '1';
    }

    /**
     * Closes reader , releases file channel.
     */
    public void close() {
        if (fch != null && fch.isOpen()) {
            try {
                fch.close();
            } catch (IOException e) {
                Logger.error(TAG, "Can not close Reader %s", e, pin.getCode());
            }
        }
        VALUE.clear();
        VALUE.compact();
    }

}
