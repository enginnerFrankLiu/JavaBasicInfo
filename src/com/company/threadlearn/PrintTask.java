package com.company.threadlearn;

/**
 * 我操，我以为还是在这个例子上进行操作呢；
 * 所以觉得这个操作是不太现实的性的操作；
 * 我也不知道，那一天，这个人能够离开我的生活....
 */
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
