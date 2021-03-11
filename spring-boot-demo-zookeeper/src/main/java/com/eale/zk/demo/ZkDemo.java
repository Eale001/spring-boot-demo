package com.eale.zk.demo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * @Author Admin
 * @Date 2020/8/3
 * @Description
 * @Version 1.0
 **/
public class ZkDemo {

    private String connectingString = "192.168.50.133:2181";

    private int sessionTimeout = 3000;

    ZooKeeper zooKeeper;

    public ZkDemo() throws IOException {
        zooKeeper = new ZooKeeper(connectingString, sessionTimeout, new Watcher() {
            // 监听回调
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getPath()+" ---"+ watchedEvent.getState() + " ---- " + watchedEvent.getType());

                try {
                    List<String> children = zooKeeper.getChildren("/", true);
                    for (String child : children) {
                        System.out.println(child);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        // 创建子节点

        ZkDemo zkDemo = new ZkDemo();

        String path = zkDemo.zooKeeper.create("/hello", "world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);

//        List<String> children = zkDemo.zooKeeper.getChildren("/hello", true);
//        for (String child : children) {
//            System.out.println("child:-----"+child);
//        }


    }


}
