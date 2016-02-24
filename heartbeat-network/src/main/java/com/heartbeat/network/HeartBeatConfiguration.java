package com.heartbeat.network;

/**
 * Created by serayuzgur on 23/02/16.
 */
public interface HeartBeatConfiguration {
    long getInterval();

    long getFailedRetryInterval();

    int getSocketTimeout();

    int getSocketPort();

    int getServerPort();
}
