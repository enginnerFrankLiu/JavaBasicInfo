package com.company.threadlearn.threadtest;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 先让线程C和线程B挂起
 * 然后成功运行线程A，
 * 然后再notify B 再Notify C
 */
public class printTask005 {

    private Object lockerA = new Object();
    private Object lockerB = new Object();
    private Object lockerC = new Object();

    Lock locker = new ReentrantLock();
    Condition condition = locker.newCondition();

    /**
     * 都是支持interruptexception 操作的
     * 也是支持超时操作的的。
     */
    public void fo() {
        try {
            condition.await();
        } catch (Exception exception) {
            System.out.println(exception);
        }

    }


    public void APrint() {
        synchronized (lockerC) {
            synchronized (lockerB) {
                try {
                    System.out.println("A");
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    public void BPrint() {
        new Thread(() -> {
            synchronized (lockerB) {
                try {
                    lockerC.wait();
                    System.out.println("C");
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }).start();
    }

    public void CPrint() {
        new Thread(() -> {
            synchronized (lockerC) {
                try {
                    lockerC.wait();
                    System.out.println("C");
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }).start();
    }

    public void start() throws Exception {

        CPrint();
        Thread.sleep(10);
        APrint();
        Thread.sleep(10);
        BPrint();
    }

    /**
     * 他们之间是需要互相通知的；所以需要两个信号对象
     * 当producer 把queue装满了，会进入等待的状态，通知消费者继续消费.
     * 当consumer 把queue消费完了，会进入等待的状态，并且通知生产者继续消费。
     * public
     */
    public void testFuck() {

    }

}
