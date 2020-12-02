package com.company;

import java.util.concurrent.locks.LockSupport;
import java.util.logging.ConsoleHandler;

/**
 * 这里还设有大概另外一种可能性把；
 * <p>
 * suspend 比 resume 后执行，
 * 本来应该是
 * 先 suspend
 * 然后 resume
 * <p>
 * 但是实际代码执行时间比较长，当你在resume的时候，suspend 还没有被call到呢
 * <p>
 * 并没有像你设想的那样，确保他们的之间call的顺序
 */
public class ThreadX {

    public static Object food;

    /**
     *
     */
    public void waitForever() throws Exception {

        Thread consumeThread = new Thread(() -> {

            if (food == null) {

                System.out.println("wait....");
                try {
                    Thread.sleep(5000L);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Thread.currentThread().suspend();
            }
            System.out.println("run.....");

        });
        consumeThread.start();
        Thread.sleep(3000L);
        food = new Object();
        System.out.println("notify");
        consumeThread.resume();
        consumeThread.join();

    }

    /**
     * 这个是可以提前call的方法；
     * 之后你call 了 unpark
     * 后续“wait” 的 thread 就能够被唤醒.
     *
     * @throws Exception
     */
    public void consumeInfo() throws Exception {

        Thread consumeThread = new Thread(() -> {

            try {
                while (food == null) {
                    System.out.println("consumer wait>>>>>>");

                    Thread.sleep(8000L);
                    LockSupport.park();
                }
                System.out.println("consumer run>>>>>>>");
            } catch (Exception exception) {
                    exception.printStackTrace();
            }

        });

        consumeThread.start();


        Thread.sleep(2000L);
        System.out.println("producer produce some food>>>>>>>");
        Thread.sleep(2000L);
        food = new Object();

        System.out.println("producer notify consumer>>>>>>>");
        Thread.sleep(1000L);
        LockSupport.unpark(consumeThread);

    }

}
