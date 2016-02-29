package com.heartbeat.common.board;

import org.junit.Test;

public class OperatingSystemTest {

    @Test
    public void testGetTypeLinux() throws Exception {
        System.setProperty("os.name", "Linux");
        OperatingSystem.Type.LINUX.equals(OperatingSystem.getType());
    }

    @Test
    public void testGetTypeMac() throws Exception {

        System.setProperty("os.name", "mac");
        OperatingSystem.Type.MAC_OS.equals(OperatingSystem.getType());

    }

    @Test
    public void testGetTypeWindows() throws Exception {
        System.setProperty("os.name", "Windows");
        OperatingSystem.Type.WINDOWS.equals(OperatingSystem.getType());
    }

    @Test
    public void testGetTypeOther() throws Exception {
        System.setProperty("os.name", "generic");
        OperatingSystem.Type.OTHER.equals(OperatingSystem.getType());
    }
}