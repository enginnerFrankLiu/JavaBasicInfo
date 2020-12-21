package com.company.threadlearn;

import java.util.concurrent.Phaser;

public class phaserTaskInfo implements  Runnable {

    private final int id;
    private final Phaser phaser;

    public phaserTaskInfo(int id,Phaser phaser){

        this.id=id;
        this.phaser=phaser;
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.println("phase : "+phaser.getPhase() +" id :"+this.id);
    }
}
