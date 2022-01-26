package com.company.threadlearn.runThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ARunnable implements Runnable {
    private CountDownLatch countDownLatch;

    public ARunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception exception) {
            System.out.println(exception);
        }
        countDownLatch.countDown();
        System.out.println("A done.");
    }
}
