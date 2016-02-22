package com.heartbeat.common.array;

import org.junit.Test;


public class ByteArrayTest {

    @Test
    public void testTrim() throws Exception {
        byte[] original = {1, 2, 3, 4, 0, 0, 0, 1};
        byte[] trimmed = ByteArray.trim(original);
        assert trimmed.length == 4;

    }
}