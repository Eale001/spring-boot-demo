package com.eale.redis.cache;

import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.redis.core.RedisOperations;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Author Admin
 * @Date 2021/3/10
 * @Description //LayeringCache
 * @Version 1.0
 **/
public class LayeringCache extends AbstractValueAdaptingCache {


    /**
     * 缓存名
     */
    private final String name;

    /**
     * 是否使用一级缓存
     */
    private boolean usedFirstCache = true;

    /**
     * redis缓存
     */
    private final CustomizedRedisCache redisCache;

    /**
     * caffeine 缓存
     */
    private final CaffeineCache caffeineCache;


    RedisOperations<? extends Object,? extends Object> redisOperations;


    public LayeringCache(String name,
                         byte[] prefix,
                         RedisOperations<? extends Object, ? extends Object> redisOperations,
                         long expiration,
                         long preloadSecondTime,
                         boolean allowNullValues,
                         boolean usedFirstCache,
                         boolean forceRefresh,
                         com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeineCache) {
        super(allowNullValues);
        this.name = name;
        this.usedFirstCache = usedFirstCache;
        this.redisOperations = redisOperations;
        this.redisCache = new CustomizedRedisCache(name, prefix, redisOperations, expiration, preloadSecondTime, forceRefresh, allowNullValues);
        this.caffeineCache = new CaffeineCache(name, caffeineCache, allowNullValues);

    }

    @Override
    protected Object lookup(Object o) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, Object o1) {

    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
