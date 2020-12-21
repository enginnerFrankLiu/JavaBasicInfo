package com.company.threadlearn;

import java.util.concurrent.Phaser;

public class PhaserRunable2 implements Runnable {
    private final int id;
    private final Phaser phaser;

    public PhaserRunable2(int id,Phaser phaser){
        this.id=id;
        this.phaser=phaser;
    }

    @Override
    public void run() {
        //这里的phase 为 0
        System.out.println("in Task.wait(), phase: "+phaser.getPhase() + "  id : "+this.id);
        phaser.arriveAndAwaitAdvance();
        //这里的phase 为 1
        System.out.println("in Task.run(), phase: "+phaser.getPhase() + "  id : "+this.id);
    }
}
