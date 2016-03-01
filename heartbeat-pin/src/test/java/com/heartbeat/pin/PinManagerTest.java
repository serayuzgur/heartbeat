package com.heartbeat.pin;

import com.heartbeat.pin.mapping.PinCode;
import org.junit.Test;


public class PinManagerTest {
    private enum TestPinCode implements PinCode {
        TEST_PIN;

        @Override
        public String getCode() {
            return "TestP0";
        }

        @Override
        public String getHwCode() {
            return "P10";
        }
    }

    @Test
    public void testGetInstance() throws Exception {
        PinManager pm = PinManager.getInstance();
        assert pm != null;

    }

    @Test
    public void testCreatePin() throws Exception {
        try {
            Pin pin = PinManager.getInstance().createPin(TestPinCode.TEST_PIN, Pin.Mode.IN);
        } catch (PinException e) {
            assert e.getMessage().startsWith("Pin code is not suitable with this board");
        }

//        assert pin.getMode() == Pin.Mode.IN;
//        assert pin.getCode().equals("A");
//
//        Pin pin2 = PinManager.getInstance().createPin("A", Pin.Mode.IN);
//        assert pin2.equals(pin);
//
//        try {
//            Pin pin3 = PinManager.getInstance().createPin("A", Pin.Mode.OUT);
//            assert false;
//        } catch (PinException e) {
//            assert e.getMessage().contains("already created");
//        }
//

    }

    @Test
    public void testGetPin() throws Exception {
        try {
            Pin pin = PinManager.getInstance().getPin("XIO_P0");
            assert pin != null;
        } catch (PinException e) {
            assert e.getMessage().equals("Pin is not created. Please call 'createPin' first.");
        }
    }

}