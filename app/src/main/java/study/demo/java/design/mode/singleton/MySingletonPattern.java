package study.demo.java.design.mode.singleton;

/**
 * 单例模式模拟类
 */
public class MySingletonPattern {


    private MySingletonPattern() {
    }


    /**
     * 1.饱汉模式(懒汉模式)：线程不安全
     * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
     * 缺点：非线程安全。
     */
    private static MySingletonPattern mySingletonPattern1 = null;

    public static MySingletonPattern getInstance1() {
        if (mySingletonPattern1 == null) {
            mySingletonPattern1 = new MySingletonPattern();
        }
        return mySingletonPattern1;
    }

    /**
     * 2.饱汉模式(懒汉模式)：线程安全
     * 优点：同上，但加锁了。
     * 缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。
     */
    private static MySingletonPattern mySingletonPattern2 = null;

    public static synchronized MySingletonPattern getInstance2() {
        if (mySingletonPattern2 == null) {
            mySingletonPattern2 = new MySingletonPattern();
        }
        return mySingletonPattern2;
    }

    /**
     * 3.饱汉模式(懒汉模式)--双重加锁检查DCL（Double Check Lock）
     * 优点：懒加载，线程安全。
     * 注：实例必须有 volatile 关键字修饰，其保证初始化完全。
     */
    private volatile static MySingletonPattern mySingletonPattern3 = null;

    public static MySingletonPattern getInstance3() {
        if (mySingletonPattern3 == null) {
            synchronized (MySingletonPattern.class) {
                if (mySingletonPattern3 == null) {
                    mySingletonPattern3 = new MySingletonPattern();
                }
            }
        }
        return mySingletonPattern3;
    }

    /**
     * 4.饿汉模式
     * 优点：饿汉模式天生是线程安全的，使用时没有延迟。
     * 缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
     */
    private static MySingletonPattern mySingletonPattern4 = new MySingletonPattern();

    public static MySingletonPattern getInstance4() {
        return mySingletonPattern4;
    }


    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class MySingletonPatternHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static MySingletonPattern mySingletonPattern = new MySingletonPattern();
    }

    /**
     * 5.Holder模式
     * 优点：将懒加载和线程安全完美结合的一种方式（无锁）。（推荐）
     */
    public static MySingletonPattern getInstance5() {
        return MySingletonPatternHolder.mySingletonPattern;
    }

    /**
     * 备注：
     *1. 全局共享，独一份；
     *2. 构造函数不暴露（如果暴露便不能保证一份），自己负责自己的构造；
     *3. 懒汉式：Lazy load，用到才加载，非线程安全。如何保证线程安全呢：
     *（1） synchronized getInstance()。
     *（2）双重检查加锁（volatile）。
     *4. 饿汉式：一开始就申请好，浪费了点资源，但其线程安全。
     *5. Holder模式：
     *（1）改成内部类，由JVM保证线程安全性。
     * */


}