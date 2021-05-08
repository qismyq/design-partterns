package qismyq.designpatterns.singleton.lazy;

/**
 * @Classname LockLazySingleton
 * @Description 双重判断的加锁的线程安全的懒汉模式：注意1.使用volatile修饰实例对象 2.使用synchronized保证原子性
 * @Date 2021/5/6 下午 06:23
 * @Created by qismyq
 * @Email michael_wang90@163.com
 */
public class DoubleCheckLazySingleton {

    /**
     * 同样将构造器设置为私有的，控制实例的产生
     */
    private DoubleCheckLazySingleton() {
    }

    /**
     * 公共内存区域，只是声明，在类加载时并未被实例化
     * ！！使用volatile保证禁止指令重排,因为new DoubleCheckLazySingleton()(对象引用赋值)是一个非原子操作
     * instance = new DoubleCheckLazySingleton()为何为非原子操作:
     *    此操作分为四步：
     *      1.栈内存开辟空间给instance引用
     *      2.堆内存开辟空间准备初始化对象
     *      3.初始化对象
     *      4.栈中引用指向堆空间地址
     *    以上4步是理想状态下的顺序，可当涉及到指令重排时，就会出现1、2、4、3的顺序，因为3和4之间不存在数据依赖，所以可以进行重排序，
     *    这样就会造成进行if(instance == null)时为true但实际上对象还未被初始化的情况，此时直接return其实是错误的。
     *
     */
    private volatile static DoubleCheckLazySingleton instance = null;


    public static DoubleCheckLazySingleton getInstance() {
        // 先判断是否实例化，如果未实例化，则进行实例化
        // double-check 此处先进行一次判断是为了提高性能效率，当存在实例时，不需要再进行下边的锁操作
        if (instance == null) {
            // 使用synchronized代码块保证原子性
            synchronized (DoubleCheckLazySingleton.class) {
                // docker-check 当进行到此处时，表示还未实例化，在原子性的前提下再次进行判断，防止多次实例化
                if (instance == null) {
                    instance = new DoubleCheckLazySingleton();
                }
            }
        }
        // 如果已经实例化，则对外提供该实例
        return instance;
    }

}
