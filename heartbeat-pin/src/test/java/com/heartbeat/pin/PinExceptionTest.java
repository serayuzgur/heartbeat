package com.heartbeat.pin;

import org.junit.Test;

/**
 * Created by serayuzgur on 29/02/16.
 */
public class PinExceptionTest {


    @Test
    public void testConst() {
        PinException e1 = new PinException();
        assert e1 != null;

        PinException e2 = new PinException("A");
        assert "A".equals(e2.getMessage());

        PinException e3 = new PinException(new RuntimeException("B"));
        assert e3.getCause() instanceof RuntimeException;
        assert "B".equals(e3.getCause().getMessage());

        PinException e4 = new PinException("A", new RuntimeException("B"));
        assert e4.getCause() instanceof RuntimeException;
        assert "A".equals(e4.getMessage());
        assert "B".equals(e4.getCause().getMessage());

        PinException e5 = new PinException("A", new RuntimeException("B"), false, false);
        assert e5.getCause() instanceof RuntimeException;
        assert "A".equals(e5.getMessage());
        assert "B".equals(e5.getCause().getMessage());
    }
}