package com.heartbeat.pin;

import org.junit.Test;

import java.io.File;

public class OperationTest {

    @Test
    public void testCommand() throws Exception {
        Pin a = new Pin("408", new File("path"));
        assert Operation.GET_MODE.command(a).equals("cat /sys/class/gpio/gpio408/direction");
        assert Operation.SET_MODE_IN.command(a).equals("echo in > /sys/class/gpio/gpio408/direction");
        assert Operation.SET_MODE_OUT.command(a).equals("echo out > /sys/class/gpio/gpio408/direction");
        assert Operation.ENABLE.command(a).equals("echo 408 | sudo tee /sys/class/gpio/export");
        assert Operation.DISABLE.command(a).equals("echo 408 | sudo tee /sys/class/gpio/unexport");
        assert Operation.READ.command(a).equals("cat /sys/class/gpio/gpio408/value");
        assert Operation.WRITE_HIGH.command(a).equals("echo 1 > /sys/class/gpio/gpio408/value");
        assert Operation.WRITE_LOW.command(a).equals("echo 0 > /sys/class/gpio/gpio408/value");

    }
}