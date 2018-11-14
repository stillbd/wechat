package net.xinqushi.wechat.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

public class RedisClient {
    @Resource(name="redisPool")
    private JedisPool jedisPool;
    public void  set(String key,String value){
        Jedis jedis=null;
        try{

            jedis=jedisPool.getResource();
            jedis.set(key,value);
        }finally {
            if(jedis!=null)
            {
                jedis.close();
            }
        }

    }
    public String  get(String key){

        Jedis jedis=null;
        String result=null;
        try{

            jedis=jedisPool.getResource();
            result= jedis.get(key);
        }finally {
            if(jedis!=null)
            {
                jedis.close();
            }
            return result;
        }

    }
}
