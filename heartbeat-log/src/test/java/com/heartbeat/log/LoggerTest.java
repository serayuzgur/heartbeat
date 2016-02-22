package com.heartbeat.log;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by serayuzgur on 21/02/16.
 */
public class LoggerTest {

    private static final String TAG = LoggerTest.class.getName();

    @org.junit.Test
    public void testSetLevel() throws Exception {
        Logger.setLevel(Logger.Level.DEBUG);
        assert Logger.getLevel().equals(Logger.Level.DEBUG);
    }


    @org.junit.Test
    public void testTrace() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.TRACE);
        String message = "Test Log";
        Logger.trace(TAG, message);
        assert new String(outputStream.toByteArray()).endsWith((message + '\n'));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.DEBUG);
        Logger.trace(TAG, message);
        assert outputStream.size() == 0;

    }

    @org.junit.Test
    public void testTraceWithArgs() throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.TRACE);
        String message = "Test Log %s %s";
        Logger.trace(TAG, message, "1", "2");
        message = "Test Log 1 2\n";
        assert new String(outputStream.toByteArray()).endsWith((message));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.DEBUG);
        Logger.trace(TAG, message);
        assert outputStream.size() == 0;


    }

    @org.junit.Test
    public void testDebug() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.DEBUG);
        String message = "Test Log";
        Logger.debug(TAG, message);
        assert new String(outputStream.toByteArray()).endsWith((message + '\n'));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.INFO);
        Logger.debug(TAG, message);
        assert outputStream.size() == 0;
    }

    @org.junit.Test
    public void testDebugWithArgs() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.DEBUG);
        String message = "Test Log %s %s";
        Logger.debug(TAG, message, "1", "2");
        message = "Test Log 1 2\n";
        assert new String(outputStream.toByteArray()).endsWith((message));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.INFO);
        Logger.debug(TAG, message);
        assert outputStream.size() == 0;

    }

    @org.junit.Test
    public void testInfo() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.INFO);
        String message = "Test Log";
        Logger.info(TAG, message);
        assert new String(outputStream.toByteArray()).endsWith((message + '\n'));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.WARNING);
        Logger.info(TAG, message);
        assert outputStream.size() == 0;
    }

    @org.junit.Test
    public void testInfoWithArgs() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.INFO);
        String message = "Test Log %s %s";
        Logger.info(TAG, message, "1", "2");
        message = "Test Log 1 2\n";
        assert new String(outputStream.toByteArray()).endsWith((message));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.WARNING);
        Logger.info(TAG, message);
        assert outputStream.size() == 0;
    }

    @org.junit.Test
    public void testWarn() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.WARNING);
        String message = "Test Log";
        Logger.warn(TAG, message);
        assert new String(outputStream.toByteArray()).endsWith((message + '\n'));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.ERROR);
        Logger.warn(TAG, message);
        assert outputStream.size() == 0;
    }

    @org.junit.Test
    public void testWarnWithArgs() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.WARNING);
        String message = "Test Log %s %s";
        Logger.warn(TAG, message, "1", "2");
        message = "Test Log 1 2\n";
        assert new String(outputStream.toByteArray()).endsWith((message));

        outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.ERROR);
        Logger.warn(TAG, message);
        assert outputStream.size() == 0;
    }

    @org.junit.Test
    public void testError() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.ERROR);
        String message = "Test Log";
        Logger.error(TAG, message);
        assert new String(outputStream.toByteArray()).endsWith((message + '\n'));
    }

    @org.junit.Test
    public void testErrorWithArgs() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.WARNING);
        String message = "Test Log %s %s";
        Logger.error(TAG, message, "1", "2");
        message = "Test Log 1 2\n";
        assert new String(outputStream.toByteArray()).endsWith((message));
    }

    @org.junit.Test
    public void testErrorWithEx() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.ERROR);
        String message = "Test Log\njava.lang.Exception: 123";
        Logger.error(TAG, message, new Exception("123"));
        assert new String(outputStream.toByteArray()).contains((message));
    }

    @org.junit.Test
    public void testErrorWithExArgs() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Logger.setStream(new PrintStream(outputStream));
        Logger.setLevel(Logger.Level.WARNING);
        String message = "Test Log %s %s";
        Logger.error(TAG, message, new Exception("123"), "1", "2");
        message = "Test Log 1 2\njava.lang.Exception: 123";
        assert new String(outputStream.toByteArray()).contains((message));
    }
}