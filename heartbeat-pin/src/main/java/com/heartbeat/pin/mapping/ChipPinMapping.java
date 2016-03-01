package com.heartbeat.pin.mapping;

/**
 * Holds Board Pin name mapping as enum.
 */
public enum ChipPinMapping implements PinMapping {
    XIO_P0("408", "U14_13"),
    XIO_P1("409", "U14_14"),
    XIO_P2("410", "U14_15"),
    XIO_P3("411", "U14_16"),
    XIO_P4("412", "U14_17"),
    XIO_P5("413", "U14_18"),
    XIO_P6("414", "U14_19"),
    XIO_P7("415", "U14_20");

    /**
     * OS code for the pin.
     */
    private final String code;

    /**
     * Hardware code for the pin.
     */
    private final String hwCode;

    ChipPinMapping(String code, String hwCode) {

        this.code = code;
        this.hwCode = hwCode;
    }

    /**
     * Returns the OS code of the pin.
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the hardware code of the pin.
     *
     * @return
     */
    public String getHwCode() {
        return hwCode;
    }

}
