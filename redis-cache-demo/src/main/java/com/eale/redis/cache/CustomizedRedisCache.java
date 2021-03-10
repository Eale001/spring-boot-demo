package com.eale.redis.cache;

import org.springframework.data.redis.core.RedisOperations;

/**
 * @Author Admin
 * @Date 2021/3/10
 * @Description //CustomizedRedisCache
 * @Version 1.0
 **/
public class CustomizedRedisCache {


    public CustomizedRedisCache(String name, byte[] prefix, RedisOperations<?, ?> redisOperations, long expiration, long preloadSecondTime, boolean forceRefresh, boolean allowNullValues) {


    }
}
