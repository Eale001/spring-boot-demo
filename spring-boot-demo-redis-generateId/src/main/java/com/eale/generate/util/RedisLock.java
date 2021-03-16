package com.eale.generate.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;


/**
 * @Author Admin
 * @Date 2021/3/15
 * @Description //RedisLock 分布式锁
 * @Version 1.0
 **/
public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 将key 的值设为value ，当且仅当key 不存在，等效于 SETNX。
     */
    private static final String NX = "NX";

    /**
     * seconds — 以秒为单位设置 key 的过期时间，等效于EXPIRE key seconds
     */
    private static final String EX = "EX";

    /**
     * 调用set后的返回值
     */
    private static final String OK = "OK";

    /**
     * 默认请求的锁的超时时间（ms 毫秒）
     */
    private static final long TIME_OUT = 100;

    /**
     * 默认获得锁的时间 10 （秒）
     */
    private static final int EXPIRE = 10;

    /**
     * 解锁的lua脚本
     */
    private static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    /**
     * 锁标志对应的 key
     */
    private String lockKey;


    /**
     * 记录到日志的锁标志对应的key
     */
    private String lockKeyLog = "";

    /**
     * 锁对应的值
     */
    private String lockValue;

    /**
     * 默认 锁的有效时间
     */
    private int expire = EXPIRE;

    /**
     *请求锁的超时时间
     */
    private long timeOut = TIME_OUT;

    /**
     * 锁标记
     */
    private volatile boolean locked = false;

    /**
     * 随机数
     */
    final Random random = new Random();


    /**
     * 使用默认的锁有效和请求过期时间
     * @param redisTemplate
     * @param lockKey
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

    /**
     * 使用默认的请求锁的超时时间，指定锁的过期时间
     * @param redisTemplate
     * @param lockKey
     * @param expire
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int expire) {
        this(redisTemplate,lockKey);
        this.expire = expire;
    }

    /**
     * 使用默认的锁的过期时间，指定请求锁的超时时间
     * @param redisTemplate
     * @param lockKey
     * @param timeOut
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, long timeOut) {
        this(redisTemplate,lockKey);
        this.timeOut = timeOut;
    }

    /**
     * 锁的过期时间和请求锁的超时时间都是用指定的值
     * @param redisTemplate
     * @param lockKey
     * @param expire
     * @param timeOut
     */
    public RedisLock(RedisTemplate<String, Object> redisTemplate, String lockKey, int expire, long timeOut) {
        this(redisTemplate,lockKey,expire);
        this.timeOut = timeOut;
    }


    /**
     * 尝试获取锁，超时返回
     * @return
     */
    public boolean tryLock(){
        // 生成随机 keyValue
        lockValue = UUID.randomUUID().toString();
        // 请求锁超时时间 纳秒
        long timeout = timeOut * 1000000;
        // 系统当前时间 纳秒
        long nowTime = System.nanoTime();
        while ((System.nanoTime() - nowTime) < timeout  ){
            if (OK.equalsIgnoreCase(this.set(lockKey,lockValue,expire))){
                locked = true;
                // // 上锁成功结束请求
                return locked;
            }
            // 每次请求睡上那么久
            try {
                Thread.sleep(10,random.nextInt(50000));
            } catch (InterruptedException e) {
                logger.error("获取分布式锁睡眠中断：{}",e);
            }
        }
        return locked;
    }


    /**
     * 尝试获取锁，立即返回
     * @return
     */
    public boolean lock(){
        // 生成随机key
        lockValue = UUID.randomUUID().toString();
        // 不存在则添加 且设置过期时间（单位ms）
        String result = set(lockKey, lockValue, expire);
        locked = OK.equalsIgnoreCase(result);
        return locked;
    }

    /**
     * 以阻塞的方式获取锁
     * @return
     */
    public boolean lockBlock(){
        lockValue = UUID.randomUUID().toString();
        while (true){
            String result = set(lockKey, lockValue, expire);
            if (OK.equalsIgnoreCase(result)){
                locked = true;
                return locked;
            }
            // 每次请求睡上那么久
            try {
                Thread.sleep(10,random.nextInt(50000));
            } catch (InterruptedException e) {
                logger.error("获取分布式锁睡眠中断：{}",e);
            }
        }
    }

    /**
     * 解锁
     * <p>
     * 可以通过以下修改，让这个锁实现更健壮：
     * <p>
     * 不使用固定的字符串作为键的值，而是设置一个不可猜测（non-guessable）的长随机字符串，作为口令串（token）。
     * 不使用 DEL 命令来释放锁，而是发送一个 Lua 脚本，这个脚本只在客户端传入的值和键的口令串相匹配时，才对键进行删除。
     * 这两个改动可以防止持有过期锁的客户端误删现有锁的情况出现。
     * @return
     */
    public boolean unLock(){
        // 只有加锁成功并且锁还有效才去释放锁
        if (locked){
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    Object nativeConnection = redisConnection.getNativeConnection();
                    Long result = 0L;

                    // 集群模式
                    if (nativeConnection instanceof JedisCluster){
                        result = (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, Collections.singletonList(lockKey),Collections.singletonList(lockValue));
                    }

                    // 单机模式
                    if (nativeConnection instanceof Jedis){
                        result = (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, Collections.singletonList(lockKey),Collections.singletonList(lockValue));
                    }

                    if (result == 0 && !StringUtils.isEmpty(lockKeyLog)) {
                        logger.info("Redis分布式锁，解锁{}失败！解锁时间：{}", lockKeyLog, System.currentTimeMillis());
                    }

                    locked = result == 0;
                    return result == 1;
                }
            });
        }
        return false;
    }


    /**
     * 重写redisTemplate的set方法
     * <p>
     * 命令 SET resource-name anystring NX EX max-lock-time 是一种在 Redis 中实现锁的简单方法。
     * <p>
     * 客户端执行以上的命令：
     * <p>
     * 如果服务器返回 OK ，那么这个客户端获得锁。
     * 如果服务器返回 NIL ，那么客户端获取锁失败，可以在稍后再重试。
     * @param lockKey
     * @param lockValue
     * @param seconds
     * @return
     */
    private String set(String lockKey,final String lockValue,final int seconds) {
        Assert.isTrue(!StringUtils.isEmpty(lockKey), "key不能为空");
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String result = null;
                if (nativeConnection instanceof JedisCommands){
                    result = ((JedisCommands) nativeConnection).set(lockKey, lockValue, NX, EX, seconds);
                }

                if (StringUtils.isNotBlank(lockKeyLog) && StringUtils.isNotBlank(result)){
                    logger.info("获取锁{}的时间：{}", lockKeyLog, System.currentTimeMillis());
                }
                return result;
            }
        });
    }

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public String getLockKeyLog() {
        return lockKeyLog;
    }

    public void setLockKeyLog(String lockKeyLog) {
        this.lockKeyLog = lockKeyLog;
    }

    public String getLockValue() {
        return lockValue;
    }

    public void setLockValue(String lockValue) {
        this.lockValue = lockValue;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
