package com.eale.generate.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/8/23
 * @Version 1.0
 **/
public class RedisIDUtil {

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    public long generate(String key){

        RedisAtomicLong counter = new RedisAtomicLong(key,redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }
    public void setIncr(String key, int value) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.set(value);
        counter.expire(0, TimeUnit.SECONDS);
    }


}
