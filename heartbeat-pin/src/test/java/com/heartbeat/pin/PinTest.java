package com.heartbeat.pin;

import org.junit.Test;

import java.io.File;

public class PinTest {

    @Test
    public void testSettersGetters() throws Exception {
        Pin a = new Pin();
        a.setCode("P1");
        assert a.getCode().equals("P1");
        a.setPath(new File("/sys/class/gpio/gpio/P1/value"));
        assert a.getPath().equals(new File("/sys/class/gpio/gpio/P1/value"));
    }

    @Test
    public void testEquals() throws Exception {
        Pin a = new Pin();
        a.setCode("P1");
        a.setPath(new File("/sys/class/gpio/gpio/P1/value"));
        Pin b = new Pin();
        b.setCode("P1");
        b.setPath(new File("/sys/class/gpio/gpio/P1/value"));

        assert !a.equals(null);
        assert a.equals(b);
    }

    @Test
    public void testHashCode() throws Exception {
        Pin a = new Pin();
        a.setCode("P1");
        a.setPath(new File("/sys/class/gpio/gpio/P1/value"));
        Pin b = new Pin();
        b.setCode("P1");
        b.setPath(new File("/sys/class/gpio/gpio/P1/value"));
        assert a.hashCode() == b.hashCode();

    }

    @Test
    public void testToString() throws Exception {
        Pin a = new Pin();
        a.setCode("P1");
        a.setPath(new File("P1PATH"));
        assert "Pin{code='P1', path=P1PATH}".equals(a.toString());

    }
}