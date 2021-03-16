package com.eale.thread.service.impl;

import com.eale.thread.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/8/18
 * @Version 1.0
 **/
@Service
public class AsyncServieImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServieImpl.class);

    @Async("asyncServiceExecutor")
    @Override
    public void executeAsync() {
        logger.info("********* start executeAsync *****");
        try {
            //睡上一千秒
            Thread.sleep(1000);
        }catch (Exception e){
            logger.error("**** start executeAsync exception ",e);
        }
        logger.info("********  end executeAsync  ********");

    }
}
