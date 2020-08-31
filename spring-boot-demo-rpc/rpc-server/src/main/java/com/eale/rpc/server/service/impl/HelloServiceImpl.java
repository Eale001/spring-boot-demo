package com.eale.rpc.server.service.impl;

import com.eale.rpc.server.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author Admin
 * @Date 2020/8/20
 * @Description
 * @Version 1.0
 **/
@Service
public class HelloServiceImpl implements HelloService {



    @Override
    public String hello(String hello) {
        return "received ："+ hello;
    }
}
