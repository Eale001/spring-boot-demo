package com.eale.thread.controller;

import com.eale.thread.service.AsyncService;
import com.eale.thread.service.impl.AsyncServieImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2019/8/18
 * @Version 1.0
 **/
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/hello")
    public String submit(){
        logger.info("===== start submit ======");

        asyncService.executeAsync();

        logger.info("===== end submit =======");
        return "success";
    }


}
