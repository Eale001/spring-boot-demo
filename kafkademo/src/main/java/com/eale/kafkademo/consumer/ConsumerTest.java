package com.eale.kafkademo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author Admin
 * @Date 2021/3/5
 * @Description //ConsumerTest
 * @Version 1.0
 **/
@Component
public class ConsumerTest {

    @KafkaListener(id = "groupA",topics = "demoTest")
    public void listen(ConsumerRecord<?,?> record){
        System.out.printf("topic is %s, offset is %d, value is %s \n", record.topic(), record.offset(), record.value());
    }

}
