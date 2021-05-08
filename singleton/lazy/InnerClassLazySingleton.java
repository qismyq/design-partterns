package qismyq.designpatterns.singleton.lazy;

/**
 * @Classname InnerClassLazySingleton
 * @Description 静态内部类的懒汉模式：线程安全。
 *      外部类被加载时，并不会立即加载内部类，当getInstance()方法被调用时，虚拟机才会加载SingletonHolder。
 *      这种方式保证了虚拟机层面的线程安全，同时利用类加载机制保证了单例的实现，也实现了懒汉模式。
 * @Date 2021/5/7 上午 08:18
 * @Created by qismyq
 * @Email michael_wang90@163.com
 */
public class InnerClassLazySingleton {

    /**
     * 私有化构造器，保证不会被外部初始化实例
     */
    private InnerClassLazySingleton() {
    }


    /**
     * 内部类加载时实例化外部类的实例，保证单例以及避免被实例化但却未被使用的内存浪费
     */
    /**
     * 关于静态内部类的初始化机制，有对一篇文章进行引用（原文找不到了）：
     *
     * 类加载时机：JAVA虚拟机在有且仅有的5种场景下会对类进行初始化。
     *     1.遇到new、getstatic、setstatic或者invokestatic这4个字节码指令时，对应的java代码场景为：new一个关键字或者一个实例化对象时、读取或设置一个静态字段时(final修饰、已在编译期把结果放入常量池的除外)、调用一个类的静态方法时。
     *
     *     2.使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没进行初始化，需要先调用其初始化方法进行初始化。
     *
     *     3.当初始化一个类时，如果其父类还未进行初始化，会先触发其父类的初始化。
     *
     *     4.当虚拟机启动时，用户需要指定一个要执行的主类(包含main()方法的类)，虚拟机会先初始化这个类。
     *
     *     5.当使用JDK 1.7等动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的解析结果REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化。
     *
     *     这5种情况被称为是类的主动引用，注意，这里《虚拟机规范》中使用的限定词是"有且仅有"，那么，除此之外的所有引用类都不会对类进行初始化，称为被动引用。静态内部类就属于被动引用的行列。
     *     我们再回头看下getInstance()方法，调用的是SingleTonHoler.INSTANCE，取的是SingleTonHoler里的INSTANCE对象，跟上面那个DCL方法不同的是，getInstance()方法并没有多次去new对象，故不管多少个线程去调用getInstance()方法，取的都是同一个INSTANCE对象，而不用去重新创建。当getInstance()方法被调用时，SingleTonHoler才在SingleTon的运行时常量池里，把符号引用替换为直接引用，这时静态对象INSTANCE也真正被创建，然后再被getInstance()方法返回出去，这点同饿汉模式。那么INSTANCE在创建过程中又是如何保证线程安全的呢？在《深入理解JAVA虚拟机》中，有这么一句话:
     *     虚拟机会保证一个类的()方法在多线程环境中被正确地加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的()方法，其他线程都需要阻塞等待，直到活动线程执行()方法完毕。如果在一个类的()方法中有耗时很长的操作，就可能造成多个进程阻塞(需要注意的是，其他线程虽然会被阻塞，但如果执行()方法后，其他线程唤醒之后不会再次进入()方法。同一个加载器下，一个类型只会初始化一次。)，在实际应用中，这种阻塞往往是很隐蔽的。
     *     故而，可以看出INSTANCE在创建过程中是线程安全的，所以说静态内部类形式的单例可保证线程安全，也能保证单例的唯一性，同时也延迟了单例的实例化。
     *     那么，是不是可以说静态内部类单例就是最完美的单例模式了呢？其实不然，静态内部类也有着一个致命的缺点，就是传参的问题，由于是静态内部类的形式去创建单例的，故外部无法传递参数进去，例如Context这种参数，所以，我们创建单例时，可以在静态内部类与DCL模式里自己斟酌。
     */
    private static class SingletonHolder{
        private static InnerClassLazySingleton innerClassLazySingleton = new InnerClassLazySingleton();
    }

    public static InnerClassLazySingleton getInstance() {
        return SingletonHolder.innerClassLazySingleton;
    }

}
