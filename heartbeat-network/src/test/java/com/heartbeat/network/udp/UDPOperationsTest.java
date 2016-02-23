package com.heartbeat.network.udp;


import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPOperationsTest {

    @org.junit.Test
    public void testSuccess() throws Exception {
        final String data = new String("hello");

        //Start Receiver
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    DatagramSocket receiver = new DatagramSocket(9998);
                    assert new String(UDPOperations.receive(receiver)).equals(data);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //Send datagram
        DatagramSocket sender = new DatagramSocket(9999);
        UDPOperations.send(sender, InetAddress.getLoopbackAddress(), 9998, data.getBytes("UTF-8"));
    }

    @Test
    public void testError() throws Exception {
        final String data = new String("hello");
        //Start Receiver
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    DatagramSocket receiver = new DatagramSocket(9);
                    UDPOperations.receive(receiver);
                    assert false;

                } catch (SocketException e) {
                    assert true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Test
    public void testPrivateConstructor() throws Exception {
        // Use reflection to ensure that the constructor is private
        Constructor constructor = UDPOperations.class.getDeclaredConstructor();
        assert Modifier.isPrivate(constructor.getModifiers());

        // Mark the class as accessible and retrieve it, this gives us the code coverage
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}