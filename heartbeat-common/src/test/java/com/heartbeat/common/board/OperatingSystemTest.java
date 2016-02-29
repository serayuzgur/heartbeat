package com.heartbeat.common.board;

import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder
public class OperatingSystemTest {
    @Test
    public void testGetType1() throws Exception {

        assert OperatingSystem.getType() != null;
        //get from static
        assert OperatingSystem.getType() != null;
        assert OperatingSystem.getType().getCmdPrefix() != null;
    }

    @Test
    public void testGetTypeLinux() throws Exception {
        System.setProperty("os.name", "Linux");
        assert OperatingSystem.Type.LINUX.equals(OperatingSystem.getType(true));
    }


    @Test
    public void testGetTypeMac() throws Exception {
        System.setProperty("os.name", "mac");
        assert OperatingSystem.Type.MAC_OS.equals(OperatingSystem.getType(true));
    }

    @Test
    public void testGetTypeWindows() throws Exception {
        System.setProperty("os.name", "Windows");
        assert OperatingSystem.Type.WINDOWS.equals(OperatingSystem.getType(true));
    }

    @Test
    public void testGetTypeOther() throws Exception {
        System.setProperty("os.name", "generic");
        assert OperatingSystem.Type.OTHER.equals(OperatingSystem.getType(true));
    }
}