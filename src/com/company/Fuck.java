package com.company;

public class Fuck {

    private final Object locker=new Object();

    /**
     * 后面的代码执行并没有特定的顺序，都是抢占是执行的
     */
    public void main(){
        Thread read= new Thread(()->{
            try {
                System.out.println("read get lock");
                synchronized (locker) {

                    System.out.println("read wait.");
                    locker.wait();
                    System.out.println("read continue.");

                }
                System.out.println("read release lock");
                Thread.sleep(6000L);
                System.out.println("read hold 3s");
            }
            catch (Exception exception){

            }
        });
        Thread writeIn=new Thread(()->{
            try {
                System.out.println("writeIn get lock");
                synchronized (locker) {
                    System.out.println("writeIn send signal....");

                    System.out.println("writeIn sended signal...");
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(100L);
                        System.out.println(String.valueOf(i));
                        //所以notity 后面一般都不会有后续的工作了
                        //直接完成准备工作，通知等待的thread。
                        //这样的操作就做够了
                        locker.notify();
                    }
                }
                System.out.println("writeIn release lock");
                Thread.sleep(3000L);
                System.out.println("难道说，要整理的方法执行完毕之后，才会通知我们那边的方法");
            }
            catch (Exception ex){
            }
        });
        read.start();
        writeIn.start();
    }

}
