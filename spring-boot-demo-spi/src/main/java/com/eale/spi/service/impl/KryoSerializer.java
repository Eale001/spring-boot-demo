package com.eale.spi.service.impl;

import com.eale.spi.service.ObjectSerializer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author Admin
 * @Date 2020/8/26
 * @Description
 * @Version 1.0
 **/
public class KryoSerializer implements ObjectSerializer {

    @Override
    public byte[] serialize(Object obj) {
        byte[] bytes;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Kryo kryo = new Kryo();
            Output output = new Output(outputStream);
            kryo.writeObject(output, obj);
            bytes = output.toBytes();
            output.flush();
        }catch (Exception e){
            throw new RuntimeException("kryo serializer error"+e.getMessage());
        }finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

    @Override
    public <T> T deSerialize(byte[] param, Class<T> clazz) {
        T object;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(param);
        try {
            Kryo kryo = new Kryo();
            Input input = new Input(inputStream);
            object = kryo.readObject(input, clazz);
            input.close();
        }catch (Exception e){
            throw new RuntimeException("kryo deSerialized error : "+e.getMessage());
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public String getSchemeName() {
        return "kryoSerializer";
    }
}
