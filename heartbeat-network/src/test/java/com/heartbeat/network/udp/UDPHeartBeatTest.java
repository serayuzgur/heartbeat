package com.heartbeat.network.udp;

import com.heartbeat.network.BeatListener;
import com.heartbeat.network.HeartBeatConfiguration;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by serayuzgur on 24/02/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UDPHeartBeatTest {
    HeartBeatConfiguration conf = new HeartBeatConfiguration() {
        @Override
        public long getInterval() {
            return 2000;
        }

        @Override
        public long getFailedRetryInterval() {
            return 4000;
        }

        @Override
        public int getSocketTimeout() {
            return 1000;
        }

        @Override
        public int getSocketPort() {
            return 9999;
        }

        @Override
        public int getServerPort() {
            return 9998;
        }
    };

    HeartBeatInfo me = new HeartBeatInfo() {

        @Override
        public byte[] serialize() {
            return "TestNode".getBytes();
        }

        @Override
        public byte[] deserialize(byte[] data) {
            return new byte[0];
        }
    };

    @Test
    public void testStop() throws Exception {
        UDPHeartBeat heartBeat = new UDPHeartBeat(conf, me);
        heartBeat.stop();
    }

    @Test
    public void testStart() throws Exception {
        UDPHeartBeat heartBeat = new UDPHeartBeat(conf, me);
        heartBeat.start();
        DatagramSocket receiver = new DatagramSocket(conf.getServerPort());
        assert new String(UDPOperations.receiveData(receiver)).equals("TestNode");
        heartBeat.stop();
        receiver.close();
    }

    @Test
    public void testStartWithListener() throws Exception {
        UDPHeartBeat heartBeat = new UDPHeartBeat(conf, me, new BeatListener() {
            @Override
            public void onBeat(byte[] response) {
                assert new String(response).equals("TestServer");
            }

            @Override
            public void onError(Exception e) {

            }
        });
        heartBeat.start();
        DatagramSocket receiver = new DatagramSocket(conf.getServerPort());
        DatagramPacket receivedPacket = UDPOperations.receive(receiver);
        assert new String(receivedPacket.getData()).equals("TestNode");
        UDPOperations.send(receiver, receivedPacket.getAddress(), receivedPacket.getPort(), "TestServer".getBytes());
        heartBeat.stop();
        receiver.close();

    }

    @Test
    public void testStart2X() throws Exception {
        UDPHeartBeat heartBeat = new UDPHeartBeat(conf, me);
        heartBeat.start();
        heartBeat.start();
        heartBeat.stop();

    }
}