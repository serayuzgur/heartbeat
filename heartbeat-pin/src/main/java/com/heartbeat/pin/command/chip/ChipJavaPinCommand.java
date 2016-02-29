package com.heartbeat.pin.command.chip;

import com.heartbeat.common.file.FileStreams;
import com.heartbeat.pin.Pin;
import com.heartbeat.pin.command.PinCommand;
import com.heartbeat.pin.command.PinCommandException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import static java.lang.String.format;

/**
 * An implementation of {@link PinCommand} which holds the pure java commands for C.H.I.P.
 */
public class ChipJavaPinCommand implements PinCommand {

    @Override
    public Pin.Mode getMode(Pin pin) throws PinCommandException {
        try {
            String result = FileStreams.readFile(new File(Commands.MODE.command(pin)));
            return Pin.Mode.valueOf(result.toUpperCase(Locale.ENGLISH));
        } catch (IOException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void setMode(Pin pin, Pin.Mode mode) throws PinCommandException {
        try {
            FileStreams.writeFile(new File(Commands.MODE.command(pin)), mode.name().toLowerCase(Locale.ENGLISH));
        } catch (IOException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void enable(Pin pin) throws PinCommandException {
        try {
            FileStreams.writeFile(new File(Commands.ENABLE.command(pin)), pin.getCode());
        } catch (IOException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void disable(Pin pin) throws PinCommandException {
        try {
            FileStreams.writeFile(new File(Commands.DISABLE.command(pin)), pin.getCode());
        } catch (IOException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public boolean read(Pin pin) throws PinCommandException {
        try {
            String result = FileStreams.readFile(new File(Commands.READ.command(pin)));
            return "1".equals(result);
        } catch (IOException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public void write(Pin pin, boolean value) throws PinCommandException {
        try {
            FileStreams.writeFile(new File(Commands.WRITE.command(pin)), value ? "1" : "0");
        } catch (IOException e) {
            throw new PinCommandException(e);
        }
    }

    @Override
    public File path(Pin pin) {
        return new File(Commands.PATH.command(pin));
    }


    /**
     * Template of the paths.
     */
    private enum Commands {
        MODE("/sys/class/gpio/gpio%s/direction"),//in/out
        ENABLE("/sys/class/gpio/export"),
        DISABLE("/sys/class/gpio/unexport"),
        READ("/sys/class/gpio/gpio%s/value"),
        WRITE("/sys/class/gpio/gpio%s/value"), // 1,0
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
