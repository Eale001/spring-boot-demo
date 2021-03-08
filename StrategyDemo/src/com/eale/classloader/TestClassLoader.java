package com.eale.classloader;

/**
 * @Author Admin
 * @Date 2021/1/28
 * @Description // classLoader
 * @Version 1.0
 **/
public class TestClassLoader {


    public static void main(String[] args) {

        ClassLoader cl = TestClassLoader.class.getClassLoader();
        System.out.println("cl is : "+cl.toString());

    }


}
