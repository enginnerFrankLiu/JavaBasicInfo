package com.company.threadlearn.threadtest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * 如果用notify的方式的话，那是一种一批次一批的执行的
 * 这个是一旦有了数据，就可以马上消费滴呀；
 * 生产一个消费一个 生产一个消费一个。
 */
public class Task007 {


    private class producer implements Runnable {

        private BlockingQueue<Integer> blockingQueue;

        public producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 4; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("produce data " + i);
                    blockingQueue.put(i);
                }
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }

    private class consumer implements Runnable {
        private BlockingQueue<Integer> blockingQueue;
        int take = -1;

        public consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (take != 4) {
                try {
                    take = blockingQueue.take();
                    System.out.println("consumer data " + take);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }

    }

    public void demo() {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(4);
        producer producer = new producer(blockingQueue);
        consumer consumer = new consumer(blockingQueue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }

}
