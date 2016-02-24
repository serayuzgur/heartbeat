package com.heartbeat.common.array;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;


public class ByteArrayTest {

    @Test
    public void testTrim() throws Exception {
        byte[] original = {1, 2, 3, 4, 0, 0, 0, 0};
        byte[] trimmed = ByteArray.trim(original);
        assert trimmed.length == 4;

        byte[] original2 = {1, 2, 3, 4, 0, 0, 0, 1};
        trimmed = ByteArray.trim(original2);
        assert trimmed.length == 8;

    }

    @Test
    public void testTrimEmptyArray() throws Exception {
        byte[] original = {};
        byte[] trimmed = ByteArray.trim(original);
        assert trimmed.length == 0;

        byte[] original2 = {0, 0};
        trimmed = ByteArray.trim(original2);
        assert trimmed.length == 0;
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        // Use reflection to ensure that the constructor is private
        Constructor constructor = ByteArray.class.getDeclaredConstructor();
        assert Modifier.isPrivate(constructor.getModifiers());

        // Mark the class as accessible and retrieve it, this gives us the code coverage
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}