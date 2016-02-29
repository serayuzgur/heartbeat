package com.heartbeat.pin.command.system;

import com.heartbeat.pin.Pin;
import com.heartbeat.pin.command.PinCommandException;
import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder
public class ChipSystemPinCommandTest {
    private Pin getPin() throws PinCommandException {
        final Pin pin = new Pin("P1", Pin.Mode.IN, new ChipSystemPinCommand());
        return pin;
    }

    @Test
    public void testGetMode() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            cmd.getMode(getPin());
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }

    @Test
    public void testSetMode() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            cmd.setMode(getPin(), Pin.Mode.IN);
            assert true;
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }

    @Test
    public void testEnable() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            cmd.enable(getPin());
            assert true;
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }

    @Test
    public void testDisable() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            cmd.disable(getPin());
            assert true;
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }

    @Test
    public void testRead() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            cmd.read(getPin());
            assert true;
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }

    @Test
    public void testWrite() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            cmd.write(getPin(), false);
            assert true;
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }

    @Test
    public void testPath() throws Exception {
        try {
            ChipSystemPinCommand cmd = new ChipSystemPinCommand();
            assert cmd.path(getPin()).equals("/sys/class/gpio/gpioP1");
        } catch (PinCommandException e) {
            System.err.println(e.getMessage());
            assert e.getMessage().contains("sudo:");
        }
    }
}