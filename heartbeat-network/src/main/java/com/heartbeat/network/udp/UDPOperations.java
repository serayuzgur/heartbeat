package com.heartbeat.network.udp;

import com.heartbeat.common.array.ByteArray;
import com.heartbeat.log.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import static java.lang.String.format;

/**
 * A static helper class for basic common UDP operations.
 */
public final class UDPOperations {
    private static final String TAG = UDPOperations.class.getName();

    private UDPOperations() {
    }

    /**
     * Sends a UDP datagram to the receiver
     *
     * @param socket       local socket
     * @param receiverIP   receiver IP address
     * @param receiverPort receiver port
     * @param data         data to send
     * @throws IOException
     */
    public static final void send(DatagramSocket socket, InetAddress receiverIP, int receiverPort, byte[] data) throws IOException {
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, receiverIP, receiverPort);
        socket.send(sendPacket);
        Logger.info(TAG, format("%s[%d]\tPacket sent\tlength: %d", receiverIP.getHostAddress(), receiverPort, data.length));
    }

    /**
     * Waits for receiving UDP datagram from the given socket.
     *
     * @param socket socket to listen
     * @return received data
     * @throws IOException
     */
    public static final byte[] receive(DatagramSocket socket) throws IOException {
        byte[] receive = new byte[15000];
        DatagramPacket packet = new DatagramPacket(receive, receive.length);
        socket.receive(packet);
        byte[] data = ByteArray.trim(receive);

        Logger.info(TAG, format("%s[%d]\tPacket received\tlength: %d", packet.getAddress().getHostAddress(), packet.getPort(), data.length));

        return data;
    }
}
