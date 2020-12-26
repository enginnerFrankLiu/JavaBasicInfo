package com.company.threadlearn;

/**
 *
 * 继续我们的demo learn
 *
 */
public class ThreadGroupRunable implements Runnable {
    public void run(){
        System.out.println(Thread.currentThread().getName());
        try{
            Thread.sleep(5000L);
            System.out.println("after interrupted. when thread in sleep status.");
        }catch (InterruptedException exception){
            System.out.println("interrupted..");
        }catch (Exception exception){
            exception.printStackTrace();
        }

        System.out.println(" sleep again..");
        try {
            Thread.sleep(5000L);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" end.");
    }
}
