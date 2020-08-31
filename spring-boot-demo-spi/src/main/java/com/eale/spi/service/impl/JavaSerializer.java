package com.eale.spi.service.impl;

import com.eale.spi.service.ObjectSerializer;

import java.io.*;

/**
 * @Author Admin
 * @Date 2020/8/26
 * @Description
 * @Version 1.0
 **/
public class JavaSerializer implements ObjectSerializer {


    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream outputStream ;
        try {
            outputStream = new ByteArrayOutputStream();
            ObjectOutput objectOutput = new ObjectOutputStream(outputStream);
            objectOutput.writeObject(obj);
            objectOutput.flush();
            objectOutput.close();
        }catch (Exception e){
            throw new RuntimeException("JAVA serialize error " + e.getMessage());
        }

        return outputStream.toByteArray();
    }

    @Override
    public <T> T deSerialize(byte[] param, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(param);
        try {
            ObjectInput objectInput = new ObjectInputStream(byteArrayInputStream);
            return (T) objectInput.readObject();
        }catch (Exception e){
            throw new RuntimeException("JAVA deSerialize error " + e.getMessage());
        }
    }

    @Override
    public String getSchemeName() {
        return "JavaSerializer";
    }
}
