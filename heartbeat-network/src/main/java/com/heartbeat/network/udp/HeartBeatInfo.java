package com.heartbeat.network.udp;

/**
 * An Interface for HeartBeat messaging. It will be used both for serializing and deserializing
 */
public interface HeartBeatInfo<T extends Class<? extends HeartBeatInfo>> {

    byte[] serialize();

    byte[] deserialize(byte[] data);
}
