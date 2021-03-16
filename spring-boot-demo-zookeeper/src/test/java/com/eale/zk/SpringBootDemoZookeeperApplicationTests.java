package com.eale.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemoZookeeperApplicationTests {

    @Test
    void contextLoads() {

        /*CuratorFramework client = ZKclient.instance.getClient();
        final InterProcessMutex zkMutex =
                new InterProcessMutex(client, "/mutex");
        ;
        for (int i = 0; i < 10; i++) {
            FutureTaskScheduler.add(() -> {

                try {
                    //获取互斥锁
                    zkMutex.acquire();

                    for (int j = 0; j < 10; j++) {
//公共的资源变量累加
                        count++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("count = " + count);
                    //释放互斥锁
                    zkMutex.release();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }

        Thread.sleep(Integer.MAX_VALUE);*/

    }

}
