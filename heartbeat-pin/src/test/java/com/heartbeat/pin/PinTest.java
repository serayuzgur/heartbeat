package com.heartbeat.pin;

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
    }

    @Test
    public void testEquals() throws Exception {
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

        assert !a.equals(null);
        assert a.equals(b);
    }

    @Test
    public void testHashCode() throws Exception {
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
        assert "Pin{code='P1', path=/sys/class/gpio/gpio/P1/value, mode=IN, enabled=true}".equals(a.toString());

    }
}