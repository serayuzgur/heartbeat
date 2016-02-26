package com.heartbeat.pin;

import java.io.File;

/**
 * A information class for Pin
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
    private String code;
    private File path;

    public Pin() {
    }

    public Pin(String code, File path) {
        this.code = code;
        this.path = path;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;

        Pin pin = (Pin) o;

        if (!code.equals(pin.code)) return false;
        return path.equals(pin.path);

    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pin{");
        sb.append("code='").append(code).append('\'');
        sb.append(", path=").append(path);
        sb.append('}');
        return sb.toString();
    }
}
