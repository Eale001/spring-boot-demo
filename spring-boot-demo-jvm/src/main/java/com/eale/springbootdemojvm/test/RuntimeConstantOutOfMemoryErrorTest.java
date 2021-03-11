package com.eale.springbootdemojvm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Admin
 * @Date 2021/3/11
 * @Description //
 *  * java 方法区和运行时常量池溢出
 *  * <p>
 *  * VM Args JDK 1.6: set JAVA_OPTS=-verbose:gc -XX:PermSize10 -XX:MaxPermSize10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 *  *
 * @Version 1.0
 **/
public class RuntimeConstantOutOfMemoryErrorTest {

    public static void main(String[] args) {
        // 使用List保存着常量池的引用，避免Full GC 回收常量池行为
        List<String> list = new ArrayList<>();
        for (int i = 0; ; i++) {
            list.add(String.valueOf(i).intern());
        }
    }

}
