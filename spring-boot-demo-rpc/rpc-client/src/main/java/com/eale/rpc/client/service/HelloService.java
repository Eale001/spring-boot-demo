package com.eale.rpc.client.service;

import com.eale.rpc.client.annotation.RpcClient;

/**
 * @Author Admin
 * @Date 2020/8/20
 * @Description
 * @Version 1.0
 **/
@RpcClient
public interface HelloService {

    public String hello(String name);

}
