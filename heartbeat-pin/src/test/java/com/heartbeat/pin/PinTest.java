package com.heartbeat.pin;

import com.heartbeat.common.board.OperatingSystem;
import com.heartbeat.pin.command.PinCommandException;
import org.junit.Test;

import java.io.File;

public class PinTest {


    @Test
    public void testSettersGetters() throws Exception {
        Pin a = new Pin("P1", new File("/sys/class/gpio/gpio/P1/value"), Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        assert a.getCode().equals("P1");
        assert a.getPath().equals(new File("/sys/class/gpio/gpio/P1/value"));
        assert a.getMode().equals(Pin.Mode.IN);

    }

    @Test
    public void testEquals() throws Exception {
        Pin a = new Pin("P1", Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        Pin b = new Pin("P1", Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });

        assert !a.equals(null);
        assert a.equals(b);
    }

    @Test
    public void testHashCode() throws Exception {
        Pin a = new Pin("P1", new File("/sys/class/gpio/gpio/P1/value"), new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        Pin b = new Pin("P1", new File("/sys/class/gpio/gpio/P1/value"), Pin.Mode.IN, new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        assert a.hashCode() == b.hashCode();

    }

    @Test
    public void testToString() throws Exception {
        Pin a = new Pin("P1", new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.IN;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        String expectedStr = "Pin{code='P1', path=/sample/P1, mode=IN, enabled=true}";
        if (OperatingSystem.getType() == OperatingSystem.Type.WINDOWS) {
            expectedStr = expectedStr.replace('/', '\\');
        }
        assert expectedStr.equals(a.toString());
    }

    @Test
    public void testEnable() throws Exception {
        Pin a = new Pin("P1", new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.OUT;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        a.write(false);

        a.disable();
        try {
            a.write(false);
            assert false;
        } catch (PinCommandException e) {
            assert true;
        }
    }

    @Test
    public void testMode() throws Exception {
        Pin a = new Pin("P1", new TestPinCommand() {
            @Override
            public Pin.Mode getMode(Pin pin) throws PinCommandException {
                return Pin.Mode.OUT;
            }

            @Override
            public boolean read(Pin pin) throws PinCommandException {
                return true;
            }
        });
        a.write(false);
        try {
            a.read();
            assert false;
        } catch (PinCommandException e) {
            assert true;
        }
        a.setMode(Pin.Mode.IN);
        a.read();
        try {
            a.write(false);
            assert false;
        } catch (PinCommandException e) {
            assert true;
        }
    }
}