package com.eale.rodredpackage.util;

import com.eale.rodredpackage.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * @Author Admin
 * @Date 2020/9/15
 * @Description 分布式获取锁和释放锁
 * @Version 1.0
 **/
@Configuration
public class Lock {

    private static final Logger logger = LoggerFactory.getLogger(Lock.class);

    @Autowired
    private RedisService redisService;




}
