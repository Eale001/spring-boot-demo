package com.eale.spi.test;

import com.eale.spi.service.ObjectSerializer;
import com.eale.spi.service.impl.JavaSerializer;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @Author Admin
 * @Date 2020/8/26
 * @Description
 * @Version 1.0
 **/
public class SerializerTest {

    public ObjectSerializer getObjectSerializer(){

        ServiceLoader<ObjectSerializer> load = ServiceLoader.load(ObjectSerializer.class);
        Iterator<ObjectSerializer> iterator = load.iterator();
        Optional<ObjectSerializer> first = StreamSupport.stream(load.spliterator(), false).findFirst();
        return first.orElse(new JavaSerializer());
    }

    public void test(){
        ObjectSerializer objectSerializer = getObjectSerializer();
        System.out.println(objectSerializer.getSchemeName());
        byte[] arrays = objectSerializer.serialize(Arrays.asList("1", "2", "3"));
        ArrayList list = objectSerializer.deSerialize(arrays, ArrayList.class);
        Object[] objects = Arrays.asList("1", "2", "3").toArray();

        if (Objects.equals(objects, list)){
            System.out.println("objects == objects1 ");
        }else {
            System.out.println("objects != objects1 ");
        }
    }


}
