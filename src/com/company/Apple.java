package com.company;

public class Apple {
    private static String locker="application-lock-apple";

    public void subThread(){

        Thread thread=new Thread(()->{
            synchronized (locker){

                System.out.println("sub Thread.....");
            }
        });

        thread.start();

    }
}
