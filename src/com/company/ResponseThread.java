package com.company;

public class ResponseThread implements Runnable {

    public void run(){
        String name=Thread.currentThread().getName();
        System.out.println("thread name is :"+name);

    }

}
