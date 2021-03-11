package com.eale.springbootdemojvm.test;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @Author Admin
 * @Date 2021/3/11
 * @Description // HeapOutOfMemoryErrorTest
 * java 堆内存溢出
 *  * <p>
 *  * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 *
 * @Version 1.0
 **/
public class HeapOutOfMemoryErrorTest {


    public static void main(String[] args) {

        // 模拟大容器
        List<Object> list = Lists.newArrayList();
        for (long i = 1; i > 0; i++) {
            list.add(new Object());
            if (i % 100_000 == 0) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
            }
        }
    }


}
