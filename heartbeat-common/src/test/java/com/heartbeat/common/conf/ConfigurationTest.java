package com.heartbeat.common.conf;

import org.boon.json.JsonException;
import org.junit.Test;

public class ConfigurationTest {

    private class SampleConf extends Configuration {
        private String name;
        private int timeout = 1000;

        public String getName() {
            return name;
        }

        public int getTimeout() {
            return timeout;
        }
    }


    @Test
    public void testLoadJson() throws Exception {
        SampleConf sampleConf = Configuration.loadJson(ConfigurationTest.class.getClassLoader().getResourceAsStream("sample.json"), SampleConf.class);

        assert sampleConf.getName().equals("Node1");
        assert sampleConf.getTimeout() == 2000;

    }

    @Test
    public void testLoadJsonException() throws Exception {
        try {
            SampleConf sampleConf = Configuration.loadJson(ConfigurationTest.class.getClassLoader().getResourceAsStream("sampleBad.json"), SampleConf.class);
            assert false;
        } catch (ConfigurationException e) {
            assert e.getCause() instanceof JsonException;
        }


    }
}