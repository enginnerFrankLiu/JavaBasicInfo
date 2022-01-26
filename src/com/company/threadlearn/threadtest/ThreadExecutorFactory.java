package com.company.threadlearn.threadtest;

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
 *
 */
public class ThreadExecutorFactory {

    private Semaphore Asema = new Semaphore(1);
    private Semaphore Bsema = new Semaphore(0);
    private Semaphore Csema = new Semaphore(0);


    public void threadPrintA() throws Exception {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Asema.acquire();
                    System.out.println("A");
                    Bsema.release();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        thread.start();
    }

    public void threadPrintB() {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Bsema.acquire();
                    System.out.println("B");
                    Csema.release();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        thread.start();
    }

    public void threadPrintC() {
        Thread thread = new Thread(() -> {
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
        });
        thread.start();
    }


    public static void run() throws Exception{
        ThreadExecutorFactory threadExecutorFactory=new ThreadExecutorFactory();
        threadExecutorFactory.threadPrintA();
        threadExecutorFactory.threadPrintB();
        threadExecutorFactory.threadPrintC();
    }
}
