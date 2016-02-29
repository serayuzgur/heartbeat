package com.heartbeat.pin.command.chip;

import com.heartbeat.common.cli.RuntimeCommandException;
import com.heartbeat.common.cli.RuntimeCommandExec;
import com.heartbeat.pin.Pin;
import com.heartbeat.pin.command.PinCommand;
import com.heartbeat.pin.command.PinCommandException;

import java.io.File;
import java.util.Locale;

import static java.lang.String.format;

/**
 * An implementation of {@link PinCommand} which holds the system commands for C.H.I.P.
 */
public class ChipSystemPinCommand implements PinCommand {


    @Override
    public Pin.Mode getMode(Pin pin) throws PinCommandException {
        try {
            String result = RuntimeCommandExec.exec(Commands.GET_MODE.command(pin));
            return Pin.Mode.valueOf(result.toUpperCase(Locale.ENGLISH));
        } catch (RuntimeCommandException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void setMode(Pin pin, Pin.Mode mode) throws PinCommandException {
        try {
            switch (mode) {
                case IN:
                    RuntimeCommandExec.exec(Commands.SET_MODE_IN.command(pin));
                    break;
                case OUT:
                    RuntimeCommandExec.exec(Commands.SET_MODE_OUT.command(pin));
                    break;
                default:
                    throw new PinCommandException("System command is missing for mode " + mode.name());
            }
            return;
        } catch (RuntimeCommandException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void enable(Pin pin) throws PinCommandException {
        try {
            RuntimeCommandExec.exec(Commands.ENABLE.command(pin));
            return;
        } catch (RuntimeCommandException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void disable(Pin pin) throws PinCommandException {
        try {
            RuntimeCommandExec.exec(Commands.DISABLE.command(pin));
            return;
        } catch (RuntimeCommandException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public boolean read(Pin pin) throws PinCommandException {
        try {
            String result = RuntimeCommandExec.exec(Commands.READ.command(pin));
            switch (result) {
                case "1":
                    return true;
                case "0":
                    return false;
                default:
                    throw new PinCommandException("Pin value is invalid :" + result);
            }

        } catch (RuntimeCommandException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void write(Pin pin, boolean value) throws PinCommandException {
        try {
            if (value)
                RuntimeCommandExec.exec(Commands.WRITE_HIGH.command(pin));
            else
                RuntimeCommandExec.exec(Commands.WRITE_LOW.command(pin));
        } catch (RuntimeCommandException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public File path(Pin pin) {
        return new File(Commands.PATH.command(pin));
    }


    /**
     * Template of the commands.
     */
    private enum Commands {
        GET_MODE("cat /sys/class/gpio/gpio%s/direction"),//in/out
        SET_MODE_IN("echo in > /sys/class/gpio/gpio%s/direction"),
        SET_MODE_OUT("echo out > /sys/class/gpio/gpio%s/direction"),
        ENABLE("echo %s | sudo tee /sys/class/gpio/export"),
        DISABLE("echo %s | sudo tee /sys/class/gpio/unexport"),
        READ("cat /sys/class/gpio/gpio%s/value"),
        WRITE_HIGH("echo 1 > /sys/class/gpio/gpio%s/value"),
        WRITE_LOW("echo 0 > /sys/class/gpio/gpio%s/value"),
        PATH("/sys/class/gpio/gpio%s");

        private final String command;

        Commands(String command) {
            this.command = command;
        }

        /**
         * Returns sys command template of the command.
         *
         * @return
         */
        public String command() {
            return command;
        }

        /**
         * Returns sys command of the command for the given Pin.
         *
         * @return
         */
        public String command(Pin pin) {
            return format(command(), pin.getCode());
        }

    }
}
