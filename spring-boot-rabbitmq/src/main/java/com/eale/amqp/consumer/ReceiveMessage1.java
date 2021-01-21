package com.eale.amqp.consumer;

import com.eale.amqp.config.RabbitConfigure;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Admin
 * @Date 2020/10/30
 * @Description //TODO
 * @Version 1.0
 **/
//@Component
public class ReceiveMessage1 {

/*
    @Autowired
    AmqpTemplate amqpTemplate;

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
    bindings = @QueueBinding(
            value = @Queue(value = RabbitConfigure.SPRING_QUEUE1+"3",durable = "true",autoDelete = "true"),
            exchange = @Exchange(value = RabbitConfigure.SPRING_EXCHANGE,type = ExchangeTypes.TOPIC),
            key = RabbitConfigure.SPRING_BIND
    ))
    public String receiveMessage1(String message){
        System.out.println(message);
        return message;
    }*/



}
