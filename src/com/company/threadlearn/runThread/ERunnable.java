package com.company.threadlearn.runThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class ERunnable implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public ERunnable(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier=cyclicBarrier;
    }

//    @Override
//    public void run() {
//        try {
//
//            System.out.println("E into barrier--1.");
//            cyclicBarrier.await();
//            System.out.println("E 一起开始.");
//
//            TimeUnit.SECONDS.sleep(3);
//
//            System.out.println("E 先干完了.");
//            System.out.println("E into barrier 2.等待.");
//            cyclicBarrier.await();
//            System.out.println("E 一起结束.");
//
//        } catch (InterruptedException e) {
//            System.out.println("E has already interrupt.");
//
//        } catch (BrokenBarrierException e) {
//            System.out.println("E has already broken.");
//
//        }
//    }

    @Override
    public void run() {
        try {

            System.out.println("E into barrier--1.");
            cyclicBarrier.await();
            System.out.println("E 一起开始.");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("E 先干完了.");
            System.out.println("E into barrier 2.等待.");
            cyclicBarrier.await(2,TimeUnit.SECONDS);
            System.out.println("E 一起结束.");

        } catch (InterruptedException e) {
            System.out.println("E has already interrupt.");

        } catch (BrokenBarrierException e) {
            System.out.println("E has already broken.");

        }catch (TimeoutException exception){
            System.out.println("E 先做完事儿，等了2s，就不等了,然后抛出了timeout的异常 ");
        }
    }
}
