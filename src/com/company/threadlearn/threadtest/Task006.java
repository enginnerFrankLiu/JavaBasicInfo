package com.company.threadlearn.threadtest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 我们先一个一个的来解决
 * 这里是一个消费线程和一个生产线程.
 * 这里面包含了一个隐藏的条件；
 * notify 并不会释放锁。 只有wait的时候，释放当前线程的锁
 */
public class Task006 {

    private Object locker=new Object();

    private class producer implements Runnable{
        private boolean isContinueProduce=true;
        private LinkedList<Integer> list;
        private int max;

        public producer(LinkedList<Integer> list,int max){
            this.list=list;
            this.max=max;
        }

        @Override
        public void run() {
            while (isContinueProduce){
                synchronized (locker) {
                    while (list.size() == max) {
                        try {
                            locker.wait();
                        }catch (InterruptedException exception){
                            System.out.println(exception);
                            isContinueProduce=false;
                        }

                    }
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("ppppppp生产了一个数据pppppppp");
                        list.add(1);
                        locker.notify(); //这个相当于添加了一个元素后，就可是通知 consumer 可以消费了，并没有等到，list 填满。
                        //它这个notify了之后，通知consumer 有可以消费的产品，consumer 就开始了消费；
                        //但是notify 并不block producer 线程的执行，可以再继续的加值

                    }catch (Exception exception){
                        System.out.println(exception);
                    }

                }
            }
        }
    }

    private class consumer implements Runnable{
        private  boolean isContinueConsume=true;
        private LinkedList<Integer> list;
        private int max;
        public consumer(LinkedList list,int max){
            this.list=list;
            this.max=max;
        }
        @Override
        public void run() {
            while (isContinueConsume){
                synchronized (locker){
                    while (list.size()==0){
                        try {
                            locker.wait();
                        }catch (InterruptedException interruptedException){
                            System.out.println(interruptedException);
                            isContinueConsume=false;
                        }
                    }
                    System.out.println("ccccccccc消费了一个数据ccccccccc");
                    list.removeFirst();
                    locker.notify();//消费了一个元素之后就再进行 通知
                }
            }
        }
    }

    /**
     *
     */
    public void demoInfo(){
        int maxSize=5;
        LinkedList<Integer> cache=new LinkedList<>();
        Thread consumerThread=new Thread(new consumer(cache,maxSize));
        Thread producerThread=new Thread(new producer(cache,maxSize));

        //第一种case consumer先run起来 -> empty list ->block ->wait.

        consumerThread.start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception exception){
           System.out.println(exception);
        }

        producerThread.start();
    }


}
