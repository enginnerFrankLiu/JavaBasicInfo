package com.company.threadlearn;

public class WorkerThread implements Runnable {


    private String message;

    public WorkerThread(String message){
        this.message=message;
    }

    public void run(){

        System.out.println(Thread.currentThread().getName()+" message :");

        processMessage();

        System.out.println(Thread.currentThread().getName() +" end .");

    }
    /**
     *
     */
    private void processMessage(){

        try {
            Thread.sleep(2000);
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }


}
