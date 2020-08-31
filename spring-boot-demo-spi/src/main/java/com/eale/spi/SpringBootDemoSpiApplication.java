package com.eale.spi;

import com.eale.spi.service.SPIService;
import com.eale.spi.test.SerializerTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.ServiceLoader;

@SpringBootApplication
public class SpringBootDemoSpiApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemoSpiApplication.class, args);

        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()){
            SPIService next = iterator.next();
            next.say();
        }

        SerializerTest serializerTest = new SerializerTest();
        serializerTest.test();


    }

}
