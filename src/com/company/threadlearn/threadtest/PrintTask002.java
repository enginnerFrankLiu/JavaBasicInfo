package com.company.threadlearn.threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintTask002 {

    private Lock lock = new ReentrantLock();
    /**
     * 三个线程同时的去抢锁，如果抢到了，
     * 但是要做一次check 判断是否该轮到它进行print。
     * 0%3=0
     * 1%3=1
     * 2%3=0
     * 大概的思路就是：虽然某个线程能够抢到锁，进入临界区，但是，是否该你print
     * 还有进行一些基本的判断，整个效果 还算是比较ok的；
     * <p>
     * 开始的状态就是：总有一个线程先获得锁，其他的两个线程block掉，
     * 等待这个线程先释放锁，其他线程再尝试获得锁
     * 这个就是他们的精髓吧
     */
    private int state = 0;

    private class ATask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {
                        System.out.println("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class BTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {
                        System.out.println("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class CTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.println("C");
                        System.out.println("------------");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * print info.
     */
    public void printTask() {
        Thread threadA = new Thread(new ATask());
        Thread threadB = new Thread(new BTask());
        Thread threadC = new Thread(new CTask());
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
