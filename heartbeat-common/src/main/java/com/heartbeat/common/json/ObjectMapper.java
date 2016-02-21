package com.heartbeat.common.json;

import org.boon.json.JsonFactory;

/**
 * A singleton wrapper for the Boon Object Mapper.
 */
public class ObjectMapper {

    private static final org.boon.json.ObjectMapper instance = JsonFactory.createUseAnnotations(true);

    public static org.boon.json.ObjectMapper getInstance() {
        return instance;
    }
}
