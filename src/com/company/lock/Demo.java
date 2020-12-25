package com.company.lock;

public class Demo implements Runnable{
    Thread thread;
    public Demo(String threadName){
        thread=new Thread(this,threadName);
        thread.start();
    }

    /**
     * 当调用Thread.yield()的时候，会给线程调度器一个当前线程愿意出让CPU的使用的暗示，但是线程调度器可能会忽略这个暗示。
     *
     *当前线程可能忽略当前暗示的情况，；
     */
    public void run() {
        for (int i = 0; i < 5; i++) {
            // yields control to another thread every 5 iterations
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread().getName() + " yielding control...");

            /* causes the currently executing thread object to temporarily
            pause and allow other threads to execute */
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + " has finished executing.");
    }
}
