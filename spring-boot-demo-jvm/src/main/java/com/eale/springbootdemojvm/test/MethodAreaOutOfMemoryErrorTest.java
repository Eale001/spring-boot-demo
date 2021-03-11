package com.eale.springbootdemojvm.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author Admin
 * @Date 2021/3/11
 * @Description //
 *  * java 元数据区域/方法区的内存溢出
 *  * <p>
 *  * VM Args JDK 1.6: set JAVA_OPTS=-verbose:gc -XX:PermSize=10m -XX:MaxPermSize=10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 *  * <p>
 *  * VM Args JDK 1.8: set JAVA_OPTS=-verbose:gc -Xmx20m -XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 *  *
 * @Version 1.0
 **/
public class MethodAreaOutOfMemoryErrorTest {

    static class MethodAreaOOM {
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MethodAreaOOM.class);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] params, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, params);
                }
            });
            enhancer.create();
        }
    }


}
