package com.company.threadlearn.runThread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class ERunnable implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public ERunnable(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

    @Override
    public void run() {
        try {

            System.out.println("E into barrier--1.");
            cyclicBarrier.await();
            System.out.println("E 同时出发一起干事情.");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("E 先干完了.");
            System.out.println("E into barrier 2.等待E");
            cyclicBarrier.await();
            System.out.println("E 一起结束.");


        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
