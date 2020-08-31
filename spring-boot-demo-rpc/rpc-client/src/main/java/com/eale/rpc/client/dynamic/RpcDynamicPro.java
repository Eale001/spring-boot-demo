package com.eale.rpc.client.dynamic;


import com.eale.rpc.client.config.NettyClientConnect;
import com.eale.rpc.common.ClassTypeAdapterFactory;
import com.eale.rpc.common.Request;
import com.eale.rpc.common.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author Admin
 * @Date 2020/8/19
 * @Description
 * @Version 1.0
 **/
@Component
@Slf4j
public class RpcDynamicPro implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String requestJson = objectToJson(method,args);
        String returnMsg = "";
        /*Socket client = new Socket("127.0.0.1",20006);
        client.setSoTimeout(10000);
        // 获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        // 获取Socket的输入流，用来接收从服务端发送过来的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        // 发送数据到服务端
        out.println(requestJson);
        Response response = new Response();
        Gson gson = new Gson();
        try {
            //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
            String responseJson = bufferedReader.readLine();
            response = gson.fromJson(responseJson, Response.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (client != null){
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            //只关闭socket，其关联的输入输出流也会被关闭
            client.close();
        }*/

        Long threadId = Thread.currentThread().getId();
        NettyClientConnect nettyClientConnect = new NettyClientConnect();
        nettyClientConnect.connect(requestJson,threadId);
        return nettyClientConnect.getResponse(threadId).getResult();
    }

    private String objectToJson(Method method, Object[] args) {
        Request request = new Request();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        String className = method.getDeclaringClass().getName();
        request.setMethodName(methodName);
        request.setParamTypes(parameterTypes);
        request.setParameters(args);
        request.setClassName(getClassName(className));
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new ClassTypeAdapterFactory());
        Gson gson = gsonBuilder.create();
        return gson.toJson(request);
    }

    private String getClassName(String beanClassName) {
        String className = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
        className = className.substring(0,1).toLowerCase() + className.substring(1);
        return className;
    }
}
