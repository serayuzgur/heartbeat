package com.heartbeat.pin.mapping;

import org.junit.Test;


public class ChipPinMappingTest {

    @Test
    public void testEnum() throws Exception {
        PinMapping mapping = ChipPinMapping.XIO_P0;
        assert "408".equals(mapping.getCode());
        assert "U14_13".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P1;
        assert "409".equals(mapping.getCode());
        assert "U14_14".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P2;
        assert "410".equals(mapping.getCode());
        assert "U14_15".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P3;
        assert "411".equals(mapping.getCode());
        assert "U14_16".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P4;
        assert "412".equals(mapping.getCode());
        assert "U14_17".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P5;
        assert "413".equals(mapping.getCode());
        assert "U14_18".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P6;
        assert "414".equals(mapping.getCode());
        assert "U14_19".equals(mapping.getHwCode());
        mapping = ChipPinMapping.XIO_P7;
        assert "415".equals(mapping.getCode());
        assert "U14_20".equals(mapping.getHwCode());

    }

}