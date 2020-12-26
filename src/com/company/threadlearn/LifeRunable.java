package com.company.threadlearn;

public class LifeRunable {
    private Object locker;

    /**
     * 1.进入等待队列
     * 2.进入池锁，再次获取lock-> 进入可运行状态.
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
