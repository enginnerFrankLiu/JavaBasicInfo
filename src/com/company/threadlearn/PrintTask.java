package com.company.threadlearn;

public class PrintTask {
    public void printNumber(String threadName){
        int i=0;
        while (i++<3){
            try{
                Thread.sleep(100);
                System.out.println("threadName :"+threadName+ " Print :"+i);
            }catch (Exception exception){
                exception.printStackTrace();
            }

        }
    }
}
