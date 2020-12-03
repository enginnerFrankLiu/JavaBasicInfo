package com.company;

public class RequestThread extends Thread {
    public void run(){
        String threadId=Thread.currentThread().getName();
        System.out.println("thread id is:"+threadId);
    }

}
