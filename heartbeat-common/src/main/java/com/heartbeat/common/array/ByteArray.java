package com.heartbeat.common.array;

import java.util.Arrays;

/**
 * A helper class for byte[]'s
 */
public final class ByteArray {

    /**
     * Trims the given array. Finds the starting position of null index and copies with {@link Arrays#copyOf(boolean[], int)}
     *
     * @param bytes original array
     * @return trimmed array
     */
    public static final byte[] trim(byte[] bytes) {
        int i = bytes.length - 1;
        while (i >= 0 && bytes[i] == 0) {
            --i;
        }

        return Arrays.copyOf(bytes, i + 1);
    }

}
