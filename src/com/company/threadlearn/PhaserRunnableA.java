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
        phaser.arriveAndAwaitAdvance();
        System.out.println(""+Thread.currentThread().getName());

        long threadId=Thread.currentThread().getId();

        long duration0=threadId*1000L;

        try {
            Thread.sleep(duration0);
        }catch (Exception exception){
           exception.printStackTrace();
        }

        //phaser 1
        phaser.arriveAndAwaitAdvance();
        System.out.println(""+Thread.currentThread().getName());

        try {

            if(threadId%2==0){
                phaser.arriveAndAwaitAdvance();
            }else{
                Thread.sleep(duration0);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }


        // phaser 2
        phaser.arriveAndAwaitAdvance();
        System.out.println(""+Thread.currentThread().getName());

        phaser.arriveAndDeregister();


    }
}
