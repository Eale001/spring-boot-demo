package com.eale.kafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Admin
 * @Date 2021/3/5
 * @Description //ProducerController
 * @Version 1.0
 **/
@RestController
public class ProducerController {


    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/sendKafka")
    public String send(String message){
        kafkaTemplate.send("demoTest",message);

        return "success";
    }



}
