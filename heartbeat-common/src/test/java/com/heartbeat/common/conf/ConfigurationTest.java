package com.heartbeat.common.conf;

import org.junit.Test;

/**
 * Created by serayuzgur on 21/02/16.
 */
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

    ;

    @Test
    public void testLoadJson() throws Exception {
        SampleConf sampleConf = Configuration.loadJson(ConfigurationTest.class.getClassLoader().getResourceAsStream("sample.json"), SampleConf.class);

        assert sampleConf.getName().equals("Node1");
        assert sampleConf.getTimeout() == 2000;

    }
}