package com.company.threadlearn.runThread;

import java.util.concurrent.BrokenBarrierException;
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
            System.out.println("D  一起开始.");

            TimeUnit.SECONDS.sleep(9);
            System.out.println("D  干完了");

            System.out.println("D into barrier--2.");
            cyclicBarrier.await();
            System.out.println("D 一起结束.");

        }  catch (InterruptedException e) {
            System.out.println("D has already interrupt.");

        } catch (BrokenBarrierException e) {
            System.out.println("D has already broken.");

        }
    }
}
