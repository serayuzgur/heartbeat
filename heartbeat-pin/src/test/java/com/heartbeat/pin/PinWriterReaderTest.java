package com.heartbeat.pin;

import com.heartbeat.log.Logger;
import com.heartbeat.pin.command.PinCommandException;
import com.heartbeat.pin.listener.PollerPinListener;
import com.heartbeat.pin.listener.WatcherPinListener;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PinWriterReaderTest {
    private static final String TAG = PinWriterReaderTest.class.getName();
    File temp;

    private class WriterThread extends Thread {
        private final Pin pin;

        public WriterThread(Pin pin) {
            this.pin = pin;
        }

        public void run() {
            PinWriter writer = null;
            try {
                writer = new PinWriter(pin);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                boolean written = i % 2 == 0;
                try {
                    writer.write(written);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Logger.info(TAG, "Written: %b", written);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writer.close();
        }
    }

    @Before
    public void setUp() throws Exception {
        temp = File.createTempFile("temp-file-name", ".tmp");
    }

    @After
    public void tearDown() throws Exception {
        if (temp != null)
            temp.delete();
    }


    @org.junit.Test
    public void testWriteWithReader() throws Exception {
        Pin pin = new Pin("P1", temp, Pin.Mode.OUT, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.OUT;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });

        PinWriter writer = new PinWriter(pin);
        PinReader reader = new PinReader(pin);
        for (int i = 0; i < 5; i++) {
            boolean written = i % 2 == 0;
            writer.write(written);
            boolean read = reader.read();
            Logger.info(TAG, "Written: %b Read: %b ", written, read);
            assert written == read;
        }
        writer.close();
        reader.close();

    }

    @org.junit.Test
    public void testWriteWithPollListener() throws Exception {
        final Pin pin = new Pin("P1", temp, Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });

        pin.enable();
        Logger.setLevel(Logger.Level.DEBUG);

        PollerPinListener listener = new PollerPinListener(pin);
        listener.start();
        Thread a = new WriterThread(pin);
        a.start();
        listener.join(1000);
        a.join();
        listener.close();
    }

    @org.junit.Test
    public void testWriteWithWatchListener() throws Exception {
        final Pin pin = new Pin("P1", temp, Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });

        Logger.setLevel(Logger.Level.DEBUG);

        WatcherPinListener listener = new WatcherPinListener(pin);
        listener.start();
        Thread a = new WriterThread(pin);
        a.start();
        listener.join(1000);
        a.join();
        listener.close();
    }


    @org.junit.Test
    public void testWriteWithPollerListenerAndReader() throws Exception {
        final Pin pin = new Pin("P1", temp, Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });


        PollerPinListener listener = new PollerPinListener(pin);
        listener.setChangeListener(new PinChangeListener() {
            PinReader reader = new PinReader(pin);

            @Override
            public void onChange(Pin pin) {
                try {
                    Logger.info(TAG, "Read: %b ", reader.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listener.start();
        Thread a = new WriterThread(pin);
        a.start();
        listener.join(1000);
        a.join();
        listener.close();
    }

    @org.junit.Test
    public void testWriteWithWatcherListenerAndReader() throws Exception {
        final Pin pin = new Pin("P1", temp, Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });


        WatcherPinListener listener = new WatcherPinListener(pin);
        listener.setChangeListener(new PinChangeListener() {
            PinReader reader = new PinReader(pin);

            @Override
            public void onChange(Pin pin) {
                try {
                    Logger.info(TAG, "Read: %b ", reader.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listener.start();
        Thread a = new WriterThread(pin);
        a.start();
        listener.join(1000);
        a.join();
        listener.close();
    }


}