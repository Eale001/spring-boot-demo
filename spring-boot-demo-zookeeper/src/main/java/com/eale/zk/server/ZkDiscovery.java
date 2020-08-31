package com.eale.zk.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Admin
 * @Date 2020/8/31
 * @Description
 * @Version 1.0
 **/
@Slf4j
public class ZkDiscovery {

    private CountDownLatch latch = new CountDownLatch(1);

    // 用来保存节点服务的list
    private volatile List<String> serverList;

    private String registerAddress;

    private ZooKeeper zooKeeper;

    public ZkDiscovery(String registerAddress) {
        this.registerAddress = registerAddress;
        zooKeeper = connectionServer();
        if (zooKeeper != null){
            watchNode(zooKeeper);
        }

    }

    // 连接zookeeper
    private ZooKeeper connectionServer() {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(registerAddress, Constant.sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
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


    // ， 获取节点下的所有服务节点， 对比list中的节点并更新list的数据
    private void watchNode(ZooKeeper zooKeeper) {
        try {
            List<String> nodeList = zooKeeper.getChildren(Constant.ZK_REGISTRY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zooKeeper);
                    }
                }
            });
            List<String> dataList = new ArrayList<>();
            for (String node : nodeList) {
                byte[] data = zooKeeper.getData(node, false, null);
                dataList.add(new String(data));
            }
            log.debug("node data: {}", dataList);
            this.serverList = dataList;
            log.debug("Service discovery triggered updating connected server node.");

            // TODO 更新节点
            updateConnectServer(serverList);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void updateConnectServer(List<String> serverList) {





    }


    // 关闭连接
    public void stop(){
        if (zooKeeper != null){
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
