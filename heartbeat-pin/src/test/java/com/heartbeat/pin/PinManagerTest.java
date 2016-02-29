package com.heartbeat.pin;

import org.junit.Test;

/**
 * Created by serayuzgur on 29/02/16.
 */
public class PinManagerTest {

    @Test
    public void testGetInstance() throws Exception {
        PinManager pm = PinManager.getInstance();
        assert pm != null;

    }

    @Test
    public void testCreatePin() throws Exception {
        Pin pin = PinManager.getInstance().createPin("A", Pin.Mode.IN);
        assert pin.getMode() == Pin.Mode.IN;
        assert pin.getCode().equals("A");

        Pin pin2 = PinManager.getInstance().createPin("A", Pin.Mode.IN);
        assert pin2.equals(pin);

        try {
            Pin pin3 = PinManager.getInstance().createPin("A", Pin.Mode.OUT);
            assert false;
        } catch (PinException e) {
            assert e.getMessage().contains("already created");
        }


    }

    @Test
    public void testGetPin() throws Exception {

    }
}