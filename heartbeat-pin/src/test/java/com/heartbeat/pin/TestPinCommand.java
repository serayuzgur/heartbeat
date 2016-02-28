package com.heartbeat.pin;

import com.heartbeat.pin.command.PinCommand;
import com.heartbeat.pin.command.PinCommandException;

import java.io.File;


public abstract class TestPinCommand implements PinCommand {

    @Override
    public void setMode(Pin pin, Pin.Mode mode) throws PinCommandException {

    }

    @Override
    public void enable(Pin pin) throws PinCommandException {

    }

    @Override
    public void disable(Pin pin) throws PinCommandException {

    }

    @Override
    public void write(Pin pin, boolean value) throws PinCommandException {

    }

    @Override
    public File path(Pin pin) {
        return new File("/sample/" + pin.getCode());
    }


}
