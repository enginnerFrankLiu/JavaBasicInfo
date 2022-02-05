package com.company.threadlearn.threadtest;

import java.util.concurrent.TimeUnit;

public class ThreadNotify {

    private Object locker = new Object();

    private class producer implements Runnable {
        @Override
        public void run() {
            synchronized (locker) {

                System.out.println("before producer start notify...");

                locker.notify();

                System.out.println("after producer start notify...");

                System.out.println("-------------------------");

                System.out.println("producer try to sleep 3s");

                System.out.println("consumer will not be awaken until producer release locker.");
                try {
                    TimeUnit.SECONDS.sleep(6);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    private class consumer implements Runnable {
        @Override
        public void run() {
            synchronized (locker) {
                System.out.println("consumer is top.");
                try {
                    locker.wait();
                    System.out.println("consumer is starting run again.");
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
    }

    public void demo() throws Exception {

        Thread consumerThread = new Thread(new consumer());
        Thread producerThread = new Thread(new producer());
        consumerThread.start();

        TimeUnit.SECONDS.sleep(1);
        producerThread.start();
    }
}
