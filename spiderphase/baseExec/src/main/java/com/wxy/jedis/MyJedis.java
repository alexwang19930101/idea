package com.wxy.jedis;

import redis.clients.jedis.Jedis;

public class MyJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.15.3",6379);
        String pong = jedis.ping();
        System.out.println(pong);
    }
}
