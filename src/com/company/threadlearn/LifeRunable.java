package com.company.threadlearn;

public class LifeRunable {
    private Object locker;

    /**
     * 1.进入等待队列
     * 2.进入池锁，再次获取lock-> 进入可运行状态.
     * 3.mThread.interrupt()时，sleep、join、wait会抛异常，并重置了该状态，true --> false
     * 对象锁
     * 每个对象都有一个monitor 对象，通常会被称为“内置锁” 或者 “对象锁”
     * 类可以实例化多个对象，所以每个对象都有独立的锁和，所以每个对象锁，互不干扰。
     * 类锁
     * 针对每个类有个锁，可以称为“类锁”，类锁实际上通过对象锁类实现的的，既类的class 对象 锁
     * 每个类只有一个 Class 对象，所以每个类只有一个类锁。
     *
     * 即任何线程在获得monitor的第一时间，现将共享内存中的数据复制到自己的缓存中；
     * 任何线程在释放monitor的第一时间，将自己缓存中的数据复制到共享内存中
     *
     * @param locker
     */
    public LifeRunable(Object locker) {
        this.locker = locker;
    }
    public void runA() {
        synchronized (locker) {
            System.out.println(Thread.currentThread().getId() + "  arrived. ");
            try {
                locker.wait();
                System.out.println(Thread.currentThread().getId() + "  run. ");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public void runB() {
        synchronized (locker) {
            System.out.println(Thread.currentThread().getId() + "  arrived. ");
            try {
                locker.wait();
                System.out.println(Thread.currentThread().getId() + "  run. ");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public void runCNotifyAll() {
        synchronized (locker) {
            try {
                System.out.println(" main thread notify all sub thread(task). ");
                locker.notifyAll();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
