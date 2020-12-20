package com.company.threadlearn;

public class threadInterview {

    /**
     *  要求就是控制线程的执行顺序，两个线程之间，
     *  线程A 执行完了之后，线程B 才是开始执行；
     *  使用到的知识点就是 join
     */
    public void info(){

        PrintTask aTask=new PrintTask();
        PrintTask bTask=new PrintTask();

        Thread threadA=new Thread( ()->{
            aTask.printNumber("A");
        });

        Thread threadB=new Thread(()->{
            try{
                threadA.join(); //堵塞调用线程，在哪个线程内部调用这个方法，就堵塞哪个线程；这样解释够清楚了撒;
                bTask.printNumber("B");
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();

    }
}
