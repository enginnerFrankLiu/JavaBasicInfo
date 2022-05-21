package com.company.threadlearn;

import java.util.concurrent.CountDownLatch;

/**
 * 十个线程同时启动去各自不同的执行任务
 * 最后一个完成后，整体任务才算完成
 */
public class XRunner implements Runnable {

    private int order;
    private CountDownLatch begin;
    private CountDownLatch end;

    public XRunner(int order, CountDownLatch begin, CountDownLatch end) {
        this.order = order;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {

        try {
            System.out.println(order+" is ready.");
            begin.await();
            Long time=getMillis();
            Thread.sleep(time);
            System.out.println(order+" is doing..some thing:"+time);
            end.countDown();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }
    private static long getMillis() {
        return (long) (Math.random() * 20_000 + 30_000);
    }
}
