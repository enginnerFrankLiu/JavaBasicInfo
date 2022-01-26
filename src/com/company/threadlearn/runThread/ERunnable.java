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

    @Override
    public void run() {
        try {

            System.out.println("E into barrier--1.");
            cyclicBarrier.await();
            System.out.println("E 一起开始.");

            TimeUnit.SECONDS.sleep(3);

            System.out.println("E 先干完了.");
            System.out.println("E into barrier 2.等待.");
            cyclicBarrier.await();
            //发生了异常后，这后面的代码是不会被执行的。
            System.out.println("E 一起结束.");

        } catch (InterruptedException e) {
            System.out.println("E has already interrupt.");

            cyclicBarrier.reset();

        } catch (BrokenBarrierException e) {
            System.out.println("E has already broken.");

        }

        System.out.println("就算 发生了 异常后 我会尝试再次等待的。");
        try {
            cyclicBarrier.await();
            System.out.println("我们还是一起结束的");
        }catch (Exception exception){

            System.out.println("再次等待的异常");
            System.out.println(exception);
        }

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
//            cyclicBarrier.await(2,TimeUnit.SECONDS);
//            System.out.println("E 一起结束.");
//
//        } catch (InterruptedException e) {
//            System.out.println("E has already interrupt.");
//
//        } catch (BrokenBarrierException e) {
//            System.out.println("E has already broken.");
//
//        }catch (TimeoutException exception){
//            System.out.println("E 先做完事儿，等了2s，就不等了,然后抛出了timeout的异常 ");
//        }
//    }
}
