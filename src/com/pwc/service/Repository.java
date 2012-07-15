package com.pwc.service;

import redis.clients.jedis.Jedis;

/**
 * Created with IntelliJ IDEA.
 * User: zhixiong
 * Date: 7/15/12
 * Time: 1:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Repository {

    public static  Jedis jedis = new Jedis("localhost");

    public static void save(Note note) {
        jedis.set(note.getId(), note.getContent());
    }

    public static String readAll() {
        return jedis.toString();
    }
}
