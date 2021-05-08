package qismyq.designpatterns.singleton.lazy;

import sun.security.jca.GetInstance;

/**
 * @Classname UnSafeLazySingleton
 * @Description 非线程安全懒汉模式：不同于饿汉模式，当实例被需要使用时，才被创建
 * @Date 2021/5/6 下午 05:49
 * @Created by qismyq
 * @Email michael_wang90@163.com
 */
public class UnSafeLazySingleton {

    // 同样将构造器设置为私有的，控制实例的产生
    private UnSafeLazySingleton() {
    }

    // 公共内存区域，只是声明，在类加载时并未被实例化
    private static UnSafeLazySingleton instance = null;


    // 为什么讲此方式是非线程安全的？
    // 因为在多线程环境下，在判断instance是否为null时，会造成多个线程满足if判断，导致实例化多个
    public static UnSafeLazySingleton getInstance() {
        // 先判断是否实例化，如果未实例化，则进行实例化
        if (instance == null) {
            instance = new UnSafeLazySingleton();
        }
        // 如果已经实例化，则对外提供该实例
        return instance;
    }


}
