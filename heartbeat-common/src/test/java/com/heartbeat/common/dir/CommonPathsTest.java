package com.heartbeat.common.dir;

import org.junit.Test;

import java.io.File;

/**
 * Created by serayuzgur on 20/02/16.
 */
public class CommonPathsTest {

    @Test
    public void testGetApplicationDirectory() throws Exception {
        //TODO: Couldn't find any proper way to test this. Just checking for exceptions and being directory
        File file = CommonPaths.getApplicationDirectory(this.getClass());
        assert file.isDirectory();
    }
}