package com.eale.amqp.provider;

import com.eale.amqp.config.RabbitConfigure;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Admin
 * @Date 2020/10/30
 * @Description //TODO
 * @Version 1.0
 **/
@Component
public class SendMessage1 {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage1(String message){
        amqpTemplate.convertAndSend(RabbitConfigure.SPRING_EXCHANGE,RabbitConfigure.SPRING_BIND,message);

    }


}
