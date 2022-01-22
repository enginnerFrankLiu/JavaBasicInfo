package com.company.threadlearn.runThread;

import java.util.concurrent.TimeUnit;

public class printRunnableV2 implements Runnable {


    /**
     * 当你去interrupt 一个thread的时候，会抛出 interruptException
     * 也没有说立即的停止一个线程，而是在任务下次执行的时候
     * <p>
     * 只有你去interrupt sleep 或者 wait 之类的方法的时候
     * <p>
     * 会抛出异常
     */
    @Override
    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {
//            System.out.println(Thread.currentThread().isInterrupted());
//        }

//        while (!Thread.interrupted()) {
//            System.out.println(Thread.currentThread().isInterrupted());
//        }

        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("bababba");
        }catch (Exception exception){
            //抛出interruptexecption 之后，这个 flag 会被清除掉的，整体效果还算是比较ok的哈
            //呵呵呵呵，多么fuck的感觉
            System.out.println("execption............");
            System.out.println(exception);
        }

        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());
    }
}
