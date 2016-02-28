package com.heartbeat.common.cli;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RuntimeCommandExecTest {

    private File temp;

    @Test
    public void testExecSuccess() throws Exception {
        String command = "pwd";
        String value = RuntimeCommandExec.exec(command);
        assert new File(value).isDirectory();
    }

    @Test
    public void testExecError() throws Exception {
        String command = "pw";
        try {
            String value = RuntimeCommandExec.exec(command);
            assert !(new File(value).isDirectory());
        } catch (RuntimeCommandException e) {
            assert e.getCause() instanceof IOException;
        }
        command = "cat /noFileShouldBeWithThatName";
        try {
            String value = RuntimeCommandExec.exec(command);
            assert !(new File(value).isDirectory());
        } catch (RuntimeCommandException e) {
            assert e.getMessage().equals("cat: /noFileShouldBeWithThatName: No such file or directory");
        }
    }

    @Before
    public void setUp() throws Exception {
        temp = File.createTempFile("temp-file-name", ".tmp");
    }

    @Test
    public void testExec() throws Exception {
        String command = "echo in > " + temp.getAbsolutePath();
        RuntimeCommandExec.exec(command);
        command = "cat " + temp.getAbsolutePath();
        String value = RuntimeCommandExec.exec(command);
        assert value.equals("in");
    }
}