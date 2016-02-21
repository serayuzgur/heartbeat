package com.heartbeat.common.conf;

import com.heartbeat.common.json.ObjectMapper;

import java.io.InputStream;

/**
 * An abstract class for the all configurations classes to extend.
 * It provides necessary methods for loading configuration object from several formats.
 */
public abstract class Configuration {

    /**
     * It provides necessary methods for loading configuration object from JSON format
     * Reads configuration file. Deserializes it with boon
     *
     * @param inputStream
     * @param configClass
     * @return
     */
    public final static <T extends Configuration> T loadJson(InputStream inputStream, Class<T> configClass) throws ConfigurationException {
        try {
            T config = ObjectMapper.getInstance().readValue(inputStream, configClass);
            return config;
        } catch (Exception e) {
            throw new ConfigurationException("Error when loading configuration file", e);
        }
    }


}
