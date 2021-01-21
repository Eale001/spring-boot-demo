package com.eale.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author Admin
 * @Date 2020/10/30
 * @Description
 * @Version 1.0
 **/
@Configuration
public class RabbitConfigure {

    public final static String SPRING_QUEUE1 = "spring.rabbitmq.testQueue1";

    public final static String SPRING_EXCHANGE = "spring.rabbit.testExchange1";

    public final static String SPRING_BIND = "spring.rabbit.bind-key1";


    /**
     * 定义队列：
     * @return
     */
    @Bean
    Queue queue() {
        return new Queue(SPRING_QUEUE1, false);
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(SPRING_EXCHANGE);
    }

    /**
     * 定义绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SPRING_BIND );
    }

}
