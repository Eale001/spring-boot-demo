package com.eale.redis.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * @Author Admin
 * @Date 2021/3/11
 * @Description //自定义的redis缓存
 * @Version 1.0
 **/
public class CustomizedRedisCache extends RedisCache {

    private static final Logger logger = LoggerFactory.getLogger(CustomizedRedisCache.class);



    protected CustomizedRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }
}
