package com.company.threadlearn;

/**
 * suspend
 * resume
 * 这些方法都是别废除了的方法；
 * 整体来说，我们还是比较看重这里的方法；
 * 总体来说，这样的方法，我们的整体是靠不住的哈；
 * 我只能这样说他们的值哈；
 * 真的是很牛逼的哈；
 */
public class ExampleInterruptThread extends Thread {

    @Override
    public void run() {
        try{
            for(int i=0;i<50000000;i++){
                if(interrupted()){
                    System.out.println("我已经是停止状态了，我要退出");
                    throw new InterruptedException("停止....");
                }
                System.out.println("i="+(i+1));
            }
        }catch (InterruptedException exception){
            System.out.println("顺利停止");
        }

    }
}
