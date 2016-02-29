package com.heartbeat.common.board;

import java.util.Arrays;
import java.util.Locale;

/**
 * A Util class for determining the system os.
 */
public final class OperatingSystem {
    private OperatingSystem() {
    }

    private static Type os;

    /**
     * Detect the operating system from the os.name System property
     *
     * @returns - the operating system
     */
    public static Type getType() {
        return getType(false);
    }

    /**
     * Detect the operating system from the os.name System property
     *
     * @returns - the operating system
     */
    public static Type getType(boolean force) {
        if (force || os == null) {
            String osName = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if (osName.contains("nux")) {
                OperatingSystem.os = Type.LINUX;
            } else if (osName.contains("win")) {
                OperatingSystem.os = Type.WINDOWS;
            } else if ((osName.contains("mac")) || (osName.contains("darwin"))) {
                OperatingSystem.os = Type.MAC_OS;
            } else {
                OperatingSystem.os = Type.OTHER;
            }
        }
        return os;
    }


    /**
     * Types of Operating Systems and some default values.
     */
    public enum Type {
        WINDOWS(new String[]{"cmd.exe", "/c"}),
        MAC_OS(new String[]{"bash", "-c"}),
        LINUX(new String[]{"bash", "-c"}),
        OTHER(new String[]{"bash", "-c"}); // hope they are linux :D

        private final String[] cmdPrefix;

        Type(String[] cmdPrefix) {
            this.cmdPrefix = cmdPrefix;
        }

        /**
         * returns the pre commands of the OS command line
         *
         * @return
         */
        public String[] getCmdPrefix() {
            return Arrays.copyOf(cmdPrefix, 2);
        }
    }
}