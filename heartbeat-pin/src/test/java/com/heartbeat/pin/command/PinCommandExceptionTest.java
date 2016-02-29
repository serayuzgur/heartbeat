package com.heartbeat.pin.command;

import org.junit.Test;

public class PinCommandExceptionTest {


    @Test
    public void testConst() {
        PinCommandException e1 = new PinCommandException();
        assert e1 != null;

        PinCommandException e2 = new PinCommandException("A");
        assert "A".equals(e2.getMessage());

        PinCommandException e3 = new PinCommandException(new RuntimeException("B"));
        assert e3.getCause() instanceof RuntimeException;
        assert "B".equals(e3.getCause().getMessage());

        PinCommandException e4 = new PinCommandException("A", new RuntimeException("B"));
        assert e4.getCause() instanceof RuntimeException;
        assert "A".equals(e4.getMessage());
        assert "B".equals(e4.getCause().getMessage());

        PinCommandException e5 = new PinCommandException("A", new RuntimeException("B"), false, false);
        assert e5.getCause() instanceof RuntimeException;
        assert "A".equals(e5.getMessage());
        assert "B".equals(e5.getCause().getMessage());
    }

}