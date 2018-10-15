package com.wxy.utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * create by pinkill on ${date}
 */
public class JedisPoolUtil {
    private static volatile  JedisPool jedisPool = null;
    private static volatile JedisSentinelPool jedisSentinelPool = null;

    private JedisPoolUtil(){}
    //单例模式之双重验证锁
    public static JedisPool getJedisPoolInstance() {
        if (null==jedisPool){
            synchronized (JedisPoolUtil.class){
                if (null==jedisPool){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(200);//最大连接数
                    poolConfig.setMaxIdle(32);//最大空闲连接
                    poolConfig.setBlockWhenExhausted(true);//连接池用光后阻塞
                    poolConfig.setMaxIdle(100*1000);//阻塞等到的最长时间
                    poolConfig.setTestOnBorrow(true);//长时间不访问连接被jedis断开，但是连接池中该对象仍存在，测试下再用

                    jedisPool = new JedisPool(poolConfig,"192.168.15.132",6379,60000);
                }
            }
        }
        return jedisPool;
    }
    //单例模式之双重验证锁
    public static JedisSentinelPool getJedisSentinelPool() {
        if (null==jedisSentinelPool){
            synchronized (JedisPoolUtil.class){
                if (null==jedisSentinelPool){
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(200);//最大连接数
                    config.setMaxIdle(32);//最大空闲连接
                    config.setMaxIdle(100*1000);//阻塞等到的最长时间
                    config.setTestOnBorrow(true);//长时间不访问连接被jedis断开，但是连接池中该对象仍存在，测试下再用
                    Set<String> set = new HashSet<>();
                    set.add("192.168.15.132:26379");

                    jedisSentinelPool = new JedisSentinelPool("mymaster",set,config);
                }
            }
        }
        return jedisSentinelPool;
    }
}
