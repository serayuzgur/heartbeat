package com.heartbeat.common.cli;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class RuntimeCommandExecTest {

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
}