package com.eale.rpc.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RpcInitConfig.class)
public class RpcConfig {

}