package com.eale.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Admin
 * @Date 2020/8/24
 * @Description
 * @Version 1.0
 **/
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello(){

        return "hello, there is docker publish!";

    }


    @GetMapping("/test")
    public String test(){

        return "hello, there is test, is the jar update auto";

    }

    @GetMapping("/test2")
    public String testAuto(){

        return "there is the jar update auto too！";

    }

}
