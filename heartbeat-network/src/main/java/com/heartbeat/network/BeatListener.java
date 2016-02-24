package com.heartbeat.network;

/**
 * Created by serayuzgur on 23/02/16.
 */
public interface BeatListener {
    void onBeat(byte[] response);

    void onError(Exception e);
}
