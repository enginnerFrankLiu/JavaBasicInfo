package com.company.threadlearn;

import java.util.concurrent.Phaser;

public class PhaserRunnableA implements Runnable{
    private Phaser phaser;
    public PhaserRunnableA(Phaser phaser){
        this.phaser=phaser;
    }

    @Override
    public void run() {
        //phaser0
        System.out.println("同时run所有的线程-------"+Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
        long threadId=Thread.currentThread().getId();
        long duration0=threadId*1000L;

        try {
            Thread.sleep(duration0);
        }catch (Exception exception){
           exception.printStackTrace();
        }

        //phaser 1
        phaser.arriveAndAwaitAdvance();
        System.out.println("所有线程，把一阶段的任务执行完了 " +Thread.currentThread().getName());

        long duration1=threadId*(1000L+threadId+6);
        try {
            Thread.sleep(duration1);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        if(threadId%2==0){
            System.out.println(" 完成二阶段的任务后，不在参与集体任务，将退出...."+Thread.currentThread().getName());
            phaser.arriveAndDeregister();
            return; //尼玛，原来这里还需要return啊，我草；
        }else{
            // phaser 2
            phaser.arriveAndAwaitAdvance();
            System.out.println("完成第二阶段任务...."+Thread.currentThread().getName());
        }




        //phaser 3
        long duration2=threadId*1700L;
        try {
            Thread.sleep(duration2);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println("完成了最后一个阶段的任务.."+Thread.currentThread().getName());
        phaser.arriveAndDeregister();

    }
}
