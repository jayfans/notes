package com.pwc.service;

import redis.clients.jedis.Jedis;

public class VersionService {
    public String getVersion() {
        return "8";
    }
}
