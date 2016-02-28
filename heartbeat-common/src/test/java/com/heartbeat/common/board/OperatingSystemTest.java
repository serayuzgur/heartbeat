package com.heartbeat.common.board;

import org.junit.Test;

public class OperatingSystemTest {

    @Test
    public void testGetType() throws Exception {
        OperatingSystem.Type.valueOf(OperatingSystem.getType().name());
    }
}