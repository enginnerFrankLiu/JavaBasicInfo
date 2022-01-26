package com.company.threadlearn.runThread;

import javax.xml.transform.OutputKeys;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class DRunnable implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public DRunnable(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("D into barrier--1.");
            cyclicBarrier.await();
            System.out.println("D  同时出发一起干事情.");

            TimeUnit.SECONDS.sleep(9);
            System.out.println("D  干完了");

            System.out.println("D into barrier--2.");
            cyclicBarrier.await();
            System.out.println("D 一起结束.");

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
