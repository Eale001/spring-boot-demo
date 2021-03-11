package com.eale.zk.demo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Admin
 * @Date 2020/8/3
 * @Description
 * @Version 1.0
 **/
public class ZkClient {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        // 1, 获取连接
        ZkClient zkClient =  new ZkClient();
        zkClient.getConnect();

        // 2， 监听服务的节点信息
        zkClient.getServers();

        // 3, 业务逻辑 （ 一直监听）
        zkClient.getWatch();

    }

    private String connectString = "192.168.50.133:2181";
    private int sessionTimeout = 3000;
    ZooKeeper zooKeeper = null;

    private List<String> children = null;

    /**
     * 1，获取连接
     */
    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                List<String> children = null;
                try {
                    // 监听父节点
                    children = zooKeeper.getChildren("/servers", true);

                    // 创建集合存储服务器列表
                    ArrayList<String> serverList = new ArrayList<String>();

                    // 获取每个节点的数据
                    for (String c : children) {
                        byte[] data = zooKeeper.getData("/servers/" + c, true, null);
                        serverList.add(new String(data));
                    }

                    // 打印服务器列表
                    System.out.println(serverList);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 监听服务的节点信息
     */
    private void getServers() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers", true);
        List<String> serverList = new ArrayList<>();
        for (String child : children) {
            byte[] data = zooKeeper.getData("/servers/", true, null);
            serverList.add(new String(data));
        }
        System.out.println(serverList);
    }

    /**
     * 业务逻辑
     */
    private void getWatch() throws InterruptedException {

        Thread.sleep(Long.MAX_VALUE);
    }

}
