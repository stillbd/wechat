package net.xinqushi.wechat.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;



    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(redisConfig.getPoolMaxIdle());
        config.setMaxWaitMillis(redisConfig.getPoolMaxWait()*1000);
        config.setMaxTotal(redisConfig.getPoolMaxTotal()) ;
        JedisPool pool=new JedisPool(config,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getPoolMaxWait()*1000,
                redisConfig.getPassword(),0);
        return pool;
    }
}
