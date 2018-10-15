package com.wxy.service;

import com.wxy.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * create by pinkill on ${date}
 */
public class SecKillRedis {
    public static  boolean doSecKill(String userID,String prodID){
        //Jedis jedis = new Jedis("192.168.15.132",6379);
        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPoolInstance.getResource();
        System.out.println("active"+jedisPoolInstance.getNumActive()+"|wait:"+jedisPoolInstance.getNumWaiters());
        //redis中的key
        String qtkey = "sk:"+prodID+":qt";
        String userkey = "sk:"+prodID+":user";

        //判断是否抢购过了
        if(jedis.sismember(userkey ,userID)){
            jedis.close();
            System.err.println("已经抢过");
            return false;
        }

        //判断是否还有库存
        jedis.watch(qtkey);
        int qt = Integer.parseInt(jedis.get(qtkey));
        if (qt<=0){
            jedis.close();
            System.err.println("没有库存");
            return false;
        }
        //数据库信息改变
        Transaction trans = jedis.multi();
        trans.decr(qtkey);
        trans.sadd(userkey, userID);
        List<Object> execList = trans.exec();

        if (null==execList || execList.size()==0){
            jedis.close();
            System.out.println("抢购失败...");
            return  true;
        }

        jedis.close();
        System.out.println("抢购成功");
        return  true;
    }
}
