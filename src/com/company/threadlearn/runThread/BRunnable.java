package com.company.threadlearn.runThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class BRunnable implements Runnable {

    private CountDownLatch countDownLatch;

    public BRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(6);

        } catch (Exception exception) {
            System.out.println(exception);
        }
        countDownLatch.countDown();
        System.out.println("B Done.");

    }
}
