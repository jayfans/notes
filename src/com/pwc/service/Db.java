package com.pwc.service;

import redis.clients.jedis.Jedis;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 7/14/12
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Db {
    public static String get(String name) {
        Jedis jedis = new Jedis("localhost");
        return jedis.get(name);
    }
}
