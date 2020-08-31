package com.eale.spi.service.impl;

import com.eale.spi.service.SPIService;

/**
 * @Author Admin
 * @Date 2020/8/26
 * @Description
 * @Version 1.0
 **/
public class SPITwoServiceImpl implements SPIService {


    @Override
    public void say() {
        System.out.println("this is two spiService impl");
    }
}
