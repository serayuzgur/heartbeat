package com.heartbeat.pin;

import com.heartbeat.pin.command.PinCommand;
import com.heartbeat.pin.command.PinCommandException;

import java.io.File;

/**
 * A  class for Pin. Stores necessary data and some pin lifecycle methods like
 * <ul>
 * <li>enable</li>
 * <li>disable</li>
 * <li>write</li>
 * <li>read</li>
 * <li>setMode</li>
 * </ul>
 * Holds code forex. "408" and path "/sys/class/gpio/gpio408
 */
public class Pin {
    /*  Pins
 *
 *  X10-P0: 408
 *  X10-P1: 409
 *  X10-P2: 410
 *  X10-P3: 411
 *  X10-P4: 412
 *  X10-P5: 413
 *  X10-P6: 414
 *  X10-P7: 415
 */
    private final String code;
    private final File path;
    protected Mode mode;
    private final PinCommand command;


    private boolean enabled;

    protected Pin(String code, Mode mode, PinCommand command) throws PinCommandException {
        this.code = code;
        this.path = command.path(this);
        this.enabled = false;
        this.command = command;
        enable();
        setMode(mode);
    }

    protected Pin(String code, File path, Mode mode, PinCommand command) throws PinCommandException {
        this.code = code;
        this.path = path;
        this.enabled = false;
        this.command = command;
        enable();
        setMode(mode);
    }

    protected Pin(String code, PinCommand command) throws PinCommandException {
        this.code = code;
        this.path = command.path(this);
        this.enabled = false;
        this.command = command;
        enable();
        readMode();
    }


    protected Pin(String code, File path, PinCommand command) throws PinCommandException {
        this.code = code;
        this.path = path;
        this.enabled = false;
        this.command = command;
        enable();
        readMode();
    }

    public String getCode() {
        return code;
    }


    public File getPath() {
        return path;
    }

    private void readMode() throws PinCommandException {
        checkEnabled();
        this.mode = command.getMode(this);
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) throws PinCommandException {
        checkEnabled();
        command.setMode(this, mode);
        this.mode = mode;
    }

    public void write(boolean value) throws PinCommandException {
        checkEnabled();
        if (Mode.IN.equals(mode)) {
            throw new PinCommandException("Pin value can't be written pin mode: IN");
        } else {
            command.write(this, value);
        }
    }

    public boolean read() throws PinCommandException {
        checkEnabled();
        if (Mode.OUT.equals(mode)) {
            throw new PinCommandException("Pin value can't be read pin mode: OUT");
        } else {
            return command.read(this);
        }
    }

    public void enable() throws PinCommandException {
        command.enable(this);
        this.enabled = true;
    }

    public void disable() throws PinCommandException {
        command.disable(this);
        this.enabled = false;
    }

    private void checkEnabled() throws PinCommandException {
        if (!enabled)
            throw new PinCommandException("Pin is Disabled :" + code);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;

        Pin pin = (Pin) o;

        if (code != null ? !code.equals(pin.code) : pin.code != null) return false;
        if (path != null ? !path.equals(pin.path) : pin.path != null) return false;
        return mode == pin.mode;

    }


    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pin{");
        sb.append("code='").append(code).append('\'');
        sb.append(", path=").append(path);
        sb.append(", mode=").append(mode);
        sb.append('}');
        return sb.toString();
    }

    public enum Mode {
        IN,
        OUT
    }

}
