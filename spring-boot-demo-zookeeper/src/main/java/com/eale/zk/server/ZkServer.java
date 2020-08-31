package com.eale.zk.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


/**
 * @Author Admin
 * @Date 2020/8/31
 * @Description
 * @Version 1.0
 **/
@Slf4j
public class ZkServer {

    private CountDownLatch latch = new CountDownLatch(1);

    private String registerAddress;




    public ZkServer(String registerAddress) {
        this.registerAddress = registerAddress;
    }



    public void register(String data){
        if (data != null){
            ZooKeeper zooKeeper = connectServer();
            if (zooKeeper != null){
                // if root node not exist
                addRootNode(zooKeeper);
                createNode(zooKeeper,data);
            }
        }

    }

    private ZooKeeper connectServer() {
        ZooKeeper zooKeeper = null;

        try {
            zooKeeper = new ZooKeeper(registerAddress, Constant.sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                    latch.countDown();
                }
            });
            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    private void addRootNode(ZooKeeper zooKeeper) {
        try {
            Stat exists = zooKeeper.exists(Constant.ZK_REGISTRY_PATH, false);
            if (exists == null){
                zooKeeper.create(Constant.ZK_REGISTRY_PATH, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void createNode(ZooKeeper zooKeeper, String data) {
        try {
            byte[] bytes = data.getBytes();
            String path = zooKeeper.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.debug("create zookeeper node ({} => {})", path, data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
