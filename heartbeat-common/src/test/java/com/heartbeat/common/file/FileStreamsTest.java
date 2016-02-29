package com.heartbeat.common.file;

import org.junit.Test;

import java.io.File;

public class FileStreamsTest {

    @Test
    public void testReadWrite() throws Exception {
        File test = File.createTempFile("ReadWrite", "tmp");
        String data = "Test";
        FileStreams.writeFile(test, data);
        String result = FileStreams.readFile(test);
        assert data.equals(result);
    }
}