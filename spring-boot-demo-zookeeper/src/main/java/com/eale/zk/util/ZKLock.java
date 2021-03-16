package com.eale.zk.util;

import java.util.Collections;

/**
 * @Author Admin
 * @Date 2021/3/16
 * @Description // zookeeper 分布式锁
 * @Version 1.0
 **/
public class ZKLock {


    /**
     *
     */
    public boolean locked = false;


    /**
     * 加锁
     * 1，一把分布式锁通常使用一个Znode节点表示；
     * 如果锁对应的Znode节点不存在，首先创建Znode节点
     *
     * 2，抢占锁的所有客户端，使用锁的Znode节点的子节点列表来表示
     *
     * 3，判定客户端是否占有锁很简单，客户端创建子节点后，需要进行判断：
     * 自己创建的子节点，是否为当前子节点列表中序号最小的子节点。
     * 如果是，则认为加锁成功；
     * 如果不是，则监听前一个Znode子节点变更消息，等待前一个节点释放锁
     *
     * 4，一旦队列中的后面的节点，获得前一个子节点变更通知，
     * 则开始进行判断，判断自己是否为当前子节点列表中序号最小的子节点，
     * 如果是，则认为加锁成功；如果不是，则持续监听，一直到获得锁
     *
     * 5，获取锁后，开始处理业务流程。完成业务流程后，删除自己的对应的子节点，
     * 完成释放锁的工作，以方面后继节点能捕获到节点变更通知，获得分布式锁。
     * @return
     */
    public boolean lock(){

//        Collections.binarySearch()

        return false;
    }


    /**
     * 释放锁
     * @return
     */
    public boolean unLock(){
        return false;
    }



}
