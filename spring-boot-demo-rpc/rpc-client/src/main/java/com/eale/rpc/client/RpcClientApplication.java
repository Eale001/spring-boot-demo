package com.eale.rpc.client;

import com.eale.rpc.client.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Admin
 * @Date 2020/8/20
 * @Description
 * @Version 1.0
 **/
@RestController
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class RpcClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RpcClientApplication.class, args);
    }

    @Autowired
    private HelloService helloService;


    @GetMapping("/hello")
    public String hello(){
        return helloService.hello("hello，老铁");
    }


}
