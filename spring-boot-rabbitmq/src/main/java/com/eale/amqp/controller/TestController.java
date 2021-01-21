package com.eale.amqp.controller;

import com.eale.amqp.provider.SendMessage1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Admin
 * @Date 2020/10/30
 * @Description //TODO
 * @Version 1.0
 **/
@RestController
public class TestController {


    @Autowired
    SendMessage1 sendMessage1;

    @GetMapping("/hello")
    public String sendMessage(String test){
//        System.out.println("test");
        sendMessage1.sendMessage1(test);
        return "success";
    }


}
