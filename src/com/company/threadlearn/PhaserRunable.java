package com.company.threadlearn;

import java.util.concurrent.Phaser;

public class PhaserRunable implements Runnable {
    private Phaser phaser;

    public PhaserRunable(Phaser phaser){
        this.phaser= phaser;
    }

    @Override
    public void run() {

        //step1 --to do some thing
        //本例中这些线程实际开始工作的时机是在所有的线程都调用了phaser.arriveAndAwaitAdvance()之后
        phaser.arriveAndAwaitAdvance();
        System.out.printf(" %s: start .\n ",Thread.currentThread().getName());

        long durationTime=(long) (Math.random()*10)+1;
        System.out.printf("%s: step1 start duration %d seconds. \n",Thread.currentThread().getName(),durationTime);

        try {
            Thread.sleep(durationTime);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        System.out.printf("%s step1 done. \n.",Thread.currentThread().getName());
        //step2 -- to do something.
        phaser.arriveAndAwaitAdvance();
        long duration2=(long)(Math.random()*10)+1;
        System.out.printf("%s: step2: start duration %d seconds .\n ",Thread.currentThread().getName(),duration2);
        try {
            Thread.sleep(duration2);
        }catch (Exception exception){
            exception.printStackTrace();
        }

        if(duration2%2==0){
            System.out.printf("%s: step2 done and task finished. \n",Thread.currentThread().getName());
            phaser.arriveAndDeregister(); //这个线程就提前结束了自己.
        }

        long duration3=(long)(Math.random()*10)+1;
        System.out.printf("%s: step3 start duration %d seconds. \n ",Thread.currentThread().getName(),duration3);

        try {
            Thread.sleep(duration3);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.printf("%s: step3 done.\n", Thread.currentThread().getName());
        phaser.arriveAndDeregister();
    }
}
