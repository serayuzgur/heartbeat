package com.heartbeat.pin.command;

import org.junit.Test;

public class PinCommandExceptionTest {


    @Test
    public void testConst() {
        PinCommandException e1 = new PinCommandException("A");
        assert "A".equals(e1.getMessage());

        PinCommandException e2 = new PinCommandException(new RuntimeException("B"));
        assert e2.getCause() instanceof RuntimeException;
        assert "B".equals(e2.getCause().getMessage());
    }

}