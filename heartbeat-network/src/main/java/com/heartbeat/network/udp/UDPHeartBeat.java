package com.heartbeat.network.udp;


import com.heartbeat.log.Logger;
import com.heartbeat.network.BeatListener;
import com.heartbeat.network.HeartBeatConfiguration;

import java.net.*;
import java.util.Enumeration;
import java.util.Objects;

/**
 * This Class does the broadcasts the given {@link HeartBeatInfo} from the
 * given {@link #socketPort} to the {@link #serverPort}.
 * It starts the thread with {@link #start()} method and broadcasts on every {@link #interval}.
 * On every error thread sleep time becomes {@link #failedInterval}.
 * If it succeeds, again {@link #interval} will be the sleep time.
 * It stops the thread with {@link #stop()} method.
 * Also supports {@link BeatListener} to trigger events on each beat.
 */
public class UDPHeartBeat {

    private static final String TAG = UDPHeartBeat.class.getName();
    private final int socketPort;
    private final int serverPort;
    private BeatListener beatListener;
    private long interval;
    private long failedInterval;
    private int socketTimeout;
    private final HeartBeatInfo me;
    private Status status;
    private Thread thread;
    private boolean pleaseStop = false;


    /**
     * @param configuration necessary configuration to start heartbeat
     * @param me            Nodes own information. It will be shared with broadcast messages.
     */
    public UDPHeartBeat(HeartBeatConfiguration configuration, HeartBeatInfo me) {
        this(configuration, me, null);
    }

    /**
     * @param configuration necessary configuration to start heartbeat
     * @param me            nodes own information. It will be shared with broadcast messages.
     * @param beatListener  listener to trigger on every beat.
     */
    public UDPHeartBeat(HeartBeatConfiguration configuration, HeartBeatInfo me, BeatListener beatListener) {
        interval = configuration.getInterval();
        failedInterval = configuration.getFailedRetryInterval();
        socketTimeout = configuration.getSocketTimeout();
        socketPort = configuration.getSocketPort();
        serverPort = configuration.getServerPort();
        this.me = me;
        this.beatListener = beatListener;
    }

    private void setStatus(Status status) {
        this.status = status;
        Logger.info("Status: %s", this.status.name());
    }

    /**
     * Stops the heartbeat thread gracefully.
     */
    public void stop() {
        if (thread != null) {
            pleaseStop = true;
            if (thread.isAlive()) {
                thread.interrupt();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        setStatus(Status.STOPPED);
    }

    /**
     * Starts the heartbeat thread. If it is already running , method returns silently.
     */
    public void start() {
        pleaseStop = false;
        if (thread != null && thread.isAlive())
            return;
        else {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long timeToWait = interval;
                    DatagramSocket socket = null;
                    try {
                        socket = new DatagramSocket();
                        socket.setBroadcast(true);
                        socket.setSoTimeout(socketTimeout);
                    } catch (SocketException e) {
                        Logger.error(TAG, "Local Socket Port Error %d", e, socketPort);
                        return;
                    }
                    while (!pleaseStop) {
                        setStatus(Status.BEATING);
                        try {

                            // Broadcast the message over all the network interfaces
                            Enumeration interfaces = NetworkInterface.getNetworkInterfaces();

                            while (interfaces.hasMoreElements()) {
                                NetworkInterface networkInterface = (NetworkInterface) interfaces.nextElement();
                                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                                    continue; // Don't want to broadcast to the loopback interface
                                }
                                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                                    if (Objects.isNull(interfaceAddress.getBroadcast())) {
                                        continue;
                                    }
                                    // Send the broadcast package!
                                    try {
                                        UDPOperations.send(socket, interfaceAddress.getBroadcast(), serverPort, me.serialize());
                                        byte[] response = UDPOperations.receiveData(socket);
                                        if (Objects.nonNull(beatListener))
                                            beatListener.onBeat(response);
                                        timeToWait = interval;
                                    } catch (Exception e) {
                                        Logger.error(TAG, "Server Error %s:%d", e, interfaceAddress.getBroadcast(), serverPort);
                                        timeToWait = failedInterval;
                                        if (Objects.nonNull(beatListener))
                                            beatListener.onError(e);
                                    }
                                }
                            }
                        } catch (SocketException e) {
                            Logger.error(TAG, "Local Socket Port Error %d", e, socketPort);
                            if (e instanceof BindException)
                                break;
                            timeToWait = failedInterval;
                        }
                        try {
                            setStatus(Status.WAITING);
                            Thread.sleep(timeToWait);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
            thread.start();
        }
    }


    /**
     * Enum defines diffrent statusses of heartbeat thread
     */
    public enum Status {
        STOPPED,
        WAITING,
        BEATING
    }
}
