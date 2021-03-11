package com.eale.zk.demo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author Admin
 * @Date 2020/8/3
 * @Description
 * @Version 1.0
 **/
public class ZkServer {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 连接zkServer
        ZkServer zkServer = new ZkServer();
        zkServer.getConnect();

        // 2，注册节点信息 服务器IP添加到zk中
        zkServer.register(args[0]);

        // 3，业务逻辑处理
        zkServer.build(args[0]);

    }

    private String connectingString = "192.168.50.133:2181";

    private int sessionTimeout = 3000;

    ZooKeeper zooKeeper = null;

    // 定义父节点
    private String parentNode = "/serverNodes";

    // 1，连接
    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectingString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

    }

    /**
     * 2, 注册信息
     * @param hostName
     */
    private void register(String hostName) throws KeeperException, InterruptedException {

        String node = zooKeeper.create(parentNode+"/servers", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(node);

    }

    /**
     * 构造服务器
     * @param hostName
     */
    private void build(String hostName) throws InterruptedException {
        System.out.println(" 服务器上线了：========"+hostName);
        Thread.sleep(Long.MAX_VALUE);
    }

}
