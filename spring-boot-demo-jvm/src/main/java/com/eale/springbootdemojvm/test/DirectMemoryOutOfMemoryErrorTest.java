package com.eale.springbootdemojvm.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author Admin
 * @Date 2021/3/11
 * @Description // DirectMemory容量可通过-XX:MaxDirectMemorySize指定，如果不指定，则默认与Java堆最大值（-Xmx指定）一样。
 *  * java 直接内存溢出
 *  * <p>
 *  * VM Args JDK 1.6: set JAVA_OPTS=-verbose:gc -Xms20m -XX:MaxDirectMemorySize=10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 *  *
 * @Version 1.0
 **/
public class DirectMemoryOutOfMemoryErrorTest {


    public static void main(String[] args) throws IllegalAccessException {
        int _1M = 1024 * 1024;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1M);
        }
    }

}
