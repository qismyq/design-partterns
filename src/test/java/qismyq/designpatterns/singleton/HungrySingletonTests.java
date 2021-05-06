package qismyq.designpatterns.singleton;

import lombok.extern.slf4j.Slf4j;
import qismyq.designpatterns.singleton.hungry.HungrySingleton;
import qismyq.designpatterns.singleton.lazy.UnSafeLazySingleton;

/**
 * @Classname HungrySingletonTests
 * @Description 饿汉模式测试
 * @Date 2021/5/6 下午 05:58
 * @Created by qismyq
 * @Email michael_wang90@163.com
 */
@Slf4j
public class HungrySingletonTests {

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                HungrySingleton instance = HungrySingleton.getInstance();
                log.info("t1 instance:{}", instance);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                HungrySingleton instance = HungrySingleton.getInstance();
                log.info("t2 instance:{}", instance);
            }
        }, "t2");


        t1.start();
        t2.start();


        //t2 instance:qismyq.designpatterns.singleton.hungry.HungrySingleton@4b67a68d
        //t1 instance:qismyq.designpatterns.singleton.hungry.HungrySingleton@4b67a68d
        // 可以看到，instance是同一个，所以为单例
    }
}
