package com.heartbeat.common.conf;


import org.junit.Test;

public class ConfigurationExceptionTest {


    @Test
    public void testConstructors() {
        ConfigurationException e1 = new ConfigurationException("Test");
        assert e1.getMessage().equals("Test");

        ConfigurationException e2 = new ConfigurationException("Test", new NullPointerException("TestNull"));
        assert e2.getMessage().equals("Test");
        assert e2.getCause() instanceof NullPointerException;
        assert e2.getCause().getMessage().equals("TestNull");

        ConfigurationException e3 = new ConfigurationException(new NullPointerException("TestNull"));
        assert e3.getCause() instanceof NullPointerException;
        assert e3.getCause().getMessage().equals("TestNull");
    }


}