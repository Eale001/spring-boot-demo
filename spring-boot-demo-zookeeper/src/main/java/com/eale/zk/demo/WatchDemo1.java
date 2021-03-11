package com.eale.zk.demo;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @Author Admin
 * @Date 2020/8/3
 * @Description
 * @Version 1.0
 **/
public class WatchDemo1 {

    static List<String> children = null;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.50.133:2181", 3000, new Watcher() {
            // 监听回调
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("正在监听中。。。");
            }
        });


        // 监听目录
        children = zooKeeper.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("监听路径为： " + watchedEvent.getPath());
                System.out.println("监听的类型为： " + watchedEvent.getType());
                System.out.println("监听被修改了");
                for (String child : children) {
                    System.out.println(child);
                }
            }
        }, null);

        Thread.sleep(Long.MAX_VALUE);
    }

}
