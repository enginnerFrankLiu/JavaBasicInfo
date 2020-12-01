package com.company;

/**
 * 这里还设有大概另外一种可能性把；

 * suspend 比 resume 后执行，
 * 本来应该是
 * 先 suspend
 * 然后 resume
 *
 * 但是实际代码执行时间比较长，当你在resume的时候，suspend 还没有被call到呢
 *
 * 并没有像你设想的那样，确保他们的之间call的顺序
 *
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

}
