package com.company.threadlearn.threadtest;

import java.util.concurrent.TimeUnit;

/**
 * 这里还有另外一种方式来实现滴呀
 * 这个大概就是一个最简单的生产者 和消费者 模式的使用；
 * 这样你才能感受到思维模式的其中。
 *
 */
public class printTask003 {

    private class producerRunnable implements Runnable {

        private Object locker;

        public producerRunnable(Object locker) {
            this.locker = locker;
        }

        @Override
        public void run() {
            synchronized (locker) {
                for (int i = 0; i < 10; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("正在生产食物....");
                    } catch (InterruptedException interruptedException) {
                        System.out.println("生产过程被中断了...");
                    }
                }
                System.out.println("生产完成了.");
                System.out.println("notify consumer consume food.");
                locker.notify();
            }
        }
    }

    private class consumerRunnable implements Runnable {
        private Object locker;

        public consumerRunnable(Object locker) {
            this.locker = locker;
        }

        @Override
        public void run() {
            synchronized (locker) {
                System.out.println("等待消费...");
                try {
                    locker.wait();
                    System.out.println("consumer 开始消费.");
                } catch (InterruptedException interruptedException) {
                    System.out.println("consumer 等待中断");
                }
            }
        }
    }

    /**
     * 打印A B 都还好，
     * 先让线程B 先执行，获取锁，然后调用wait 挂起线程
     * 再执行A 再通知 B 继续执行；
     * @throws Exception
     */
    public void demo()  throws Exception{
        Object locker=new Object();
        Thread producer = new Thread(new producerRunnable(locker));
        Thread consumer=new Thread(new consumerRunnable(locker));

        System.out.println("让consumer先run起来呀,避免producer通知时 消息丢失.");
        consumer.start();
        TimeUnit.SECONDS.sleep(1);
        producer.start();


    }
}
