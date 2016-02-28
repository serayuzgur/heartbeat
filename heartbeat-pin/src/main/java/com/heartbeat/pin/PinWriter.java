package com.heartbeat.pin;

import com.heartbeat.log.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * A high performance writer class which opens a {@link FileChannel} to the pin.
 * It uses  {@link ByteBuffer} with the size 1 and reuses it for every write.
 */
public class PinWriter {
    private static final String TAG = PinWriter.class.getName();
    private final Pin pin;
    private ByteBuffer TRUE = ByteBuffer.wrap("1".getBytes());
    private ByteBuffer FALSE = ByteBuffer.wrap("0".getBytes());

    private final FileOutputStream os;
    private final FileChannel fch;

    /**
     * Creates a outputstream and file channel for the pin.
     *
     * @param pin
     * @throws IOException
     */
    public PinWriter(Pin pin) throws IOException {
        this.pin = pin;
        os = new FileOutputStream(pin.getPath());
        fch = os.getChannel();
    }

    /**
     * Writes the value.
     * Writes "1" for true, writes "0" for false.
     *
     * @param value value
     * @throws IOException
     */
    public void write(boolean value) throws IOException {
        fch.write(value ? TRUE : FALSE);
        if (value)
            TRUE.position(0);
        else
            FALSE.position(0);
        fch.position(0);
        os.flush();
    }

    /**
     * Closes reader , releases file channel and output stream.
     */
    public void close() {
        if (fch != null && fch.isOpen()) {
            try {
                fch.close();
            } catch (IOException e) {
                Logger.error(TAG, "Can not close Reader FileChannel %s", e, pin.getCode());
            }
        }
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                Logger.error(TAG, "Can not close Writer OutputStream %s", e, pin.getCode());
            }
        }
    }

}
