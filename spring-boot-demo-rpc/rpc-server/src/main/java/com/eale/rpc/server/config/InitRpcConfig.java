package com.eale.rpc.server.config;

import com.eale.rpc.server.handler.CommandDeal;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Admin
 * @Date 2020/8/20
 * @Description 拿到接口名以后，通过接口名找到实现类 我们在服务端启动的时候将其对应信息加载进去
 * @Version 1.0
 **/
@Component
@Slf4j
public class InitRpcConfig implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static Map<String,Object> rpcServiceMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {
        Map<String,Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Service.class);
        for (Object bean : beansWithAnnotation.values()) {
            Class<?> clazz = bean.getClass();
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> inter : interfaces) {
                rpcServiceMap.put(getClassName(inter.getName()), bean);
                log.info("已经加载的服务："+ inter.getName());
            }
        }
        startPort();
    }

    private void startPort() throws IOException, InterruptedException {

        //服务端在20006端口监听客户端请求的TCP连接
        /*ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        boolean f = true;
        while (f) {
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();
        }
        server.close();*/

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup  boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boos,worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                /*.childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                //获得实现类处理过后的返回值
                                String invokeMethodMes = CommandDeal.getInvokeMethodMes(msg);
                                ByteBuf encoded = ctx.alloc().buffer(4 * invokeMethodMes.length());
                                encoded.writeBytes(invokeMethodMes.getBytes());
                                ctx.writeAndFlush(encoded);
                            }
                        });
                    }
                })*/;
        ChannelFuture future = serverBootstrap.bind(20006).sync();
        log.info("服务端开启监听端口：20006  ***");
        future.channel().closeFuture().sync();
    }

    private String getClassName(String beanClassName) {
        String className = beanClassName.substring(beanClassName.lastIndexOf(".")+1);
        className = className.substring(0,1).toLowerCase() + className.substring(1);
        return className;
    }
}
