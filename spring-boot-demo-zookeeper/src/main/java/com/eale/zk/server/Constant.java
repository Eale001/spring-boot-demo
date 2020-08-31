package com.eale.zk.server;

/**
 * @Author Admin
 * @Date 2020/8/31
 * @Description //TODO
 * @Version 1.0
 **/
public interface Constant {

    int sessionTimeout = 5000;

    String ZK_REGISTRY_PATH = "/registry";

    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";

}
