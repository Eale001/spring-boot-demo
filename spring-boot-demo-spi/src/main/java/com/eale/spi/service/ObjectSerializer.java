package com.eale.spi.service;

/**
 * @Author Admin
 * @Date 2020/8/26
 * @Description
 * @Version 1.0
 **/
public interface ObjectSerializer {

    byte[] serialize(Object obj);

    <T> T deSerialize(byte[] param, Class<T> clazz);

    String getSchemeName();


}
