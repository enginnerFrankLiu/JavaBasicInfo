package com.company.threadlearn.runThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CRunnable implements Runnable {

    private CountDownLatch countDownLatch;

    public CRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("3");
            countDownLatch.countDown();

            TimeUnit.SECONDS.sleep(6);
            System.out.println("6");
            countDownLatch.countDown();

            TimeUnit.SECONDS.sleep(9);
            System.out.println("9");
            countDownLatch.countDown();

        } catch (Exception exception) {
            System.out.println(exception);
        }

        System.out.println("C Done.");

    }
}
