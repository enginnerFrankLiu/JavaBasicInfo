package com.company.threadlearn.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 三个线程并发执行 一次只能运行一个，
 * 三个线程之间要相同通信 A 打完要通知 B
 * B 打完要通知 C
 * <p>
 * 如何保证三个线程同时执行，唯独线程A 开始进行打印呢
 * 办法：
 * thread A 执行
 * Thread B 直接block 等待 A 执行完 notify
 * Thread C 直接block 等待 B 执行完 notify
 * Thread C 执行完之后通知 Thread A。
 * 然后任务就可以继续了.
 *
 * 每个线程执行自己的任务，只是相互通知一下
 *
 * 这个是相当的完美
 *
 * 更完美一点，三个线程得同时执行
 *
 * 这是一个比较完美的版本
 *
 */
public class ThreadExecutorFactoryV2 {

    private Semaphore Asema = new Semaphore(1);
    private Semaphore Bsema = new Semaphore(0);
    private Semaphore Csema = new Semaphore(0);


    private class ATaskRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Asema.acquire();
                    System.out.println("A");
                    Bsema.release();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    public class BTaskRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Bsema.acquire();
                    System.out.println("B");
                    Csema.release();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    public class CTaskRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Csema.acquire();
                    System.out.println("C");
                    System.out.println("--------------------");
                    Asema.release();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }


    public void run() throws Exception{
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        executorService.submit(new ATaskRunnable());
        executorService.submit(new BTaskRunnable());
        executorService.submit(new CTaskRunnable());
        executorService.shutdown();
    }
}
