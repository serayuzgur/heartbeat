package com.heartbeat.pin;

import java.io.File;

/**
 * A information class for Pin
 * Holds code forex. "P13_8" and path "/sys/class/gpio/gpio/P13_8/value
 */
public class Pin {
    private String code;
    private File path;

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
