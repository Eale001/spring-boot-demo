package com.eale.springbootdemojvm.controller;

import com.eale.springbootdemojvm.dto.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Admin
 * @Date 2020/8/25
 * @Description
 * @Version 1.0
 **/
@Slf4j
@RestController
public class TestController {


    private List<Greeting> greetingCache = new ArrayList<>();

    @GetMapping("/greeting")
    public Greeting greeting() {
        Greeting greeting = new Greeting();
        if (greetingCache.size() >= 100000) {
            log.info("clean the List!!!!!!!!!!");
            greetingCache.clear();
        } else {
            greetingCache.add(greeting);
        }
        return greeting;
    }


}
