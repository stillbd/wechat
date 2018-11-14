package net.xinqushi.wechat.cache.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public  class RedisService{
    @Autowired
   JedisPool jedisPool;
    public <T> T get(String key,Class<T> clazz){
        Jedis jedis=null;
       try{
           jedis=jedisPool.getResource();
           String str=jedis.get(key);
           //将字符串转为bean对象
           T t=stringToBean(str,clazz);
           return t;
       }finally {
           returnToPool(jedis);
       }
    }
    public <T> void set(String key,T data){
       if(StringUtils.isEmpty(key)){
           return ;
       }
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();

            //将字符串转为bean对象
            String str=beanToString(data);
            jedis.set(key,str);

        }finally {
            returnToPool(jedis);
        }
    }

    private void   returnToPool(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
    }
    private <T> T stringToBean(String str,Class<T> clazz){
     if(StringUtils.isEmpty(str)){
         return null;
     }

        if(clazz==int.class||clazz==Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz==long.class||clazz==Long.class){
            return (T)Long.valueOf(str);
        }else if(clazz==String.class){
            return (T)str;
        }else{
            return JSON.toJavaObject(  JSON.parseObject(str),clazz);
        }

    }
//    将bean转为String
    private <T> String beanToString(T value){
        if(null==value)
            return  null;
        Class<?> clazz=value.getClass();
        if(clazz==int.class||clazz==Integer.class){
             return ""+value;
        }else if(clazz==long.class||clazz==Long.class){
            return ""+value;
        }else if(clazz==String.class){
         return (String) value;
        }else{
            return JSON.toJSONString(value);
        }
    }
}
