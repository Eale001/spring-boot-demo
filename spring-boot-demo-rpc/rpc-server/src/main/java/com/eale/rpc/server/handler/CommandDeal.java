package com.eale.rpc.server.handler;

import com.eale.rpc.common.ClassTypeAdapterFactory;
import com.eale.rpc.common.Request;
import com.eale.rpc.common.Response;
import com.eale.rpc.server.config.InitRpcConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Admin
 * @Date 2020/8/20
 * @Description
 * @Version 1.0
 **/
@Component
@Slf4j
public class CommandDeal {

    public static String getInvokeMethodMes(String str){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
        Gson gson = gsonBuilder.create();
        Request request = gson.fromJson(str, Request.class);
        return gson.toJson(invokeMethod(request));
    }

    public static Response invokeMethod(Request request){
        String className = request.getClassName();
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();
        Class<?>[] parameTypes = request.getParamTypes();
        Object o = InitRpcConfig.rpcServiceMap.get(className);
        Response response = new Response();
        try {
            Method method = o.getClass().getDeclaredMethod(methodName, parameTypes);
            Object invokeMethod = method.invoke(o, parameters);
            response.setResult(invokeMethod);
        } catch (NoSuchMethodException e) {
            log.info("没有找到"+methodName);
        } catch (IllegalAccessException e) {
            log.info("执行错误"+parameters);
        } catch (InvocationTargetException e) {
            log.info("执行错误"+parameters);
        }
        return response;
    }

}
