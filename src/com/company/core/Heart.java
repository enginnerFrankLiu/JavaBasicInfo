package com.company.core;

public class Heart {
    public static int A=1;
  //虚拟机会保证一个类的 <clinit>() 方法在多线程环境中被正确加锁、同步。如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个类的 <clinit>() 方法。
    //父类中定义的静态语句块要优先于子类变量的赋值操作
    static {
        A=2;
    }
}
