package com.company.threadlearn.runThread;

public class AggRunnable implements Runnable {

    /**
     * 所有的线程都到达屏障后要执行的方法。
     */
    @Override
    public void run() {
        System.out.println("所有thread都到达后。会被执行的一个action.");
    }
}
