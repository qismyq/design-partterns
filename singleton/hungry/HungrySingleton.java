package qismyq.designpatterns.singleton.hungry;

/**
 * @Classname HungrySingleton
 * @Description 饿汉模式：不管实例用不用得到，在类被加载时都创建一个实例
 * @Date 2021/5/6 下午 05:17
 * @Created by qismyq
 * @Email michael_wang90@163.com
 */
public class HungrySingleton {

    // 在类加载时即生成对象实例，因为类被加载的次数只为一次，所以可以保证只会创建一个实例
    private static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    // 控制构造器为private类型，不允许产生新的实例
    private HungrySingleton() {
    }

    // 对外提供获取实例方法
    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }
}
