package com.company.threadlearn.runThread;

public class SleepRunnalbe implements Runnable {
    @Override
    public void run() {

        try {
            System.out.println("sleep.....");
            Thread.sleep(9000);
        } catch (Exception exception) {
            System.out.println(exception);
        }

    }
}
