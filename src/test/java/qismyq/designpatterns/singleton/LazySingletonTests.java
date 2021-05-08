package qismyq.designpatterns.singleton;

import lombok.extern.slf4j.Slf4j;
import qismyq.designpatterns.singleton.lazy.DoubleCheckLazySingleton;
import qismyq.designpatterns.singleton.lazy.InnerClassLazySingleton;
import qismyq.designpatterns.singleton.lazy.UnSafeLazySingleton;

import static org.springframework.test.context.transaction.TestTransaction.start;

/**
 * @Classname LazySingletonTests
 * @Description 懒汉模式测试
 * @Date 2021/5/6 下午 05:58
 * @Created by qismyq
 * @Email michael_wang90@163.com
 */
@Slf4j
public class LazySingletonTests {

    /**
     * 非线程安全测试
     *
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args)throws Exception {
//
//        Thread t1 = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                UnSafeLazySingleton instance = UnSafeLazySingleton.getInstance();
//                log.info("t1 instance:{}",instance);
//            }
//        },"t1");
//
//        Thread t2 = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                UnSafeLazySingleton instance = UnSafeLazySingleton.getInstance();
//                log.info("t2 instance:{}",instance);
//            }
//        },"t2");
//
//
//        t1.start();
//        t2.start();
//
//
//        //t2 instance:qismyq.designpatterns.singleton.lazy.UnSafeLazySingleton@70e64f23
//        //t1 instance:qismyq.designpatterns.singleton.lazy.UnSafeLazySingleton@2e067c0c
//        // 可以看到，instance并不是同一个，所以非线程安全
//    }

    /**
     * 双重检验test
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DoubleCheckLazySingleton instance = DoubleCheckLazySingleton.getInstance();
//                log.info("t1 instance:{}", instance);
//            }
//        }, "t1");
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DoubleCheckLazySingleton instance = DoubleCheckLazySingleton.getInstance();
//                log.info("t2 instance:{}", instance);
//            }
//        }, "t2");
//
//
//        t1.start();
//        t2.start();
//
//        // t1 instance:qismyq.designpatterns.singleton.lazy.DoubleCheckLazySingleton@4f582b39
//        // t2 instance:qismyq.designpatterns.singleton.lazy.DoubleCheckLazySingleton@4f582b39
//        // 可以看到，instance是同一个，所以线程安全且为单例
//    }


    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                InnerClassLazySingleton instance = InnerClassLazySingleton.getInstance();
                log.info("t1 instance:{}", instance);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                InnerClassLazySingleton instance = InnerClassLazySingleton.getInstance();
                log.info("t2 instance:{}", instance);
            }
        }, "t2");


        t1.start();
        t2.start();

        // t2 instance:qismyq.designpatterns.singleton.lazy.InnerClassLazySingleton@229aaad
        // t1 instance:qismyq.designpatterns.singleton.lazy.InnerClassLazySingleton@229aaad
        // 可以看到，instance是同一个，所以线程安全且为单例
    }
}
