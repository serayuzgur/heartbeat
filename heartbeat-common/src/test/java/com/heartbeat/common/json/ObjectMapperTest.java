package com.heartbeat.common.json;

import org.junit.Test;

public class ObjectMapperTest {

    @Test
    public void testGetInstance() throws Exception {
        assert ObjectMapper.getInstance() instanceof org.boon.json.ObjectMapper;
    }
}