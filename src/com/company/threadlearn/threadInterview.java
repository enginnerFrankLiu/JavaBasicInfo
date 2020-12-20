package com.company.threadlearn;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.xml.transform.Result;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;

public class threadInterview {

    /**
     * 要求就是控制线程的执行顺序，两个线程之间，
     * 线程A 执行完了之后，线程B 才是开始执行；
     * 使用到的知识点就是 join
     */
    public void info() {

        PrintTask aTask = new PrintTask();
        PrintTask bTask = new PrintTask();
        Thread threadA = new Thread(() -> {
            aTask.printNumber("A");
        });
        Thread threadB = new Thread(() -> {
            try {
                threadA.join(); //堵塞调用线程，在哪个线程内部调用这个方法，就堵塞哪个线程；这样解释够清楚了撒;
                bTask.printNumber("B");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();

    }

    private Object locker = new Object();

    /**
     * 两个线程，交替着执行；
     *
     * 两个线程，按照指定的方式，交替着执行；
     * 整体效果还算不错的腊；
     * 慢慢的去学习和改变吧;
     */
    public void interactionPrint() {


        Thread threadA = new Thread(() -> {

            synchronized (locker) {
                try {
                    System.out.println("A 1");
                    locker.wait(); //会让出当前线程，并且释放hold 的锁，这个还算比较奈斯吧
                    System.out.println("A 2");
                    System.out.println("A 3");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        //也就是说，notify/notifyAll() 的执行只是唤醒沉睡的线程，而不会立即释放锁，锁的释放要看代码块的具体执行情况
        //代码块执行完之后，才会释放，指定的锁，这个还是可以接受的，吗的比；
        Thread threadB = new Thread(() -> {

            synchronized (locker) {
                try {
                    synchronized (locker) {
                        System.out.println("B 1");
                        System.out.println("B 2");
                        System.out.println("B 3");
                        locker.notify();
                    }
                } catch (Exception exception) {

                }
            }
        });

        threadA.start();
        threadB.start();
    }

    /**
     * 想要的效果，或者说需求就是：
     * A B C D 四个线程，
     * D 要等待 A B C 都是执行完成之后，才开始；
     * 与此同时，我们额  A  B  C 是同步执行的；
     * 整体效果还是是很OK 的哈.
     * 我草，我也是涨见识，还可以这样写for 循环，可以的
     */
    public void allInformationTodoSome(){
        int threadCount=3;
        CountDownLatch countDownLatch=new CountDownLatch(threadCount);
        for(char threadName='A';threadName<='C';threadName++){

            String name=String.valueOf(threadName);
            Thread thread=new Thread(()-> {
                try {
                    Thread.sleep(100);
                    System.out.println(name +" run ->>>>>> over.");

                }catch (Exception exception){
                     exception.printStackTrace();
                }
                countDownLatch.countDown();
            });
            thread.start();
        }

        Thread msThread=new Thread(()-> {
            try {
                countDownLatch.await();
                Thread.sleep(100);
                System.out.println("D run ->>>>>> over.");
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        msThread.start();
    }

    /**
     *
     * information to do something.
     * 线程同步
     * 线程通信
     * 线程安全
     * 等等等问题的一个基本了解吧；
     * 后续还设有更多的提，需要我们要不断练习吧；
     *
     */
    public void getSubThreadResultInfo(){

        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                int result=0;
                for(int i=0;i<100;i++){
                    Thread.sleep(100);
                    result+=i;
                }
                return result;
            }
        };

        FutureTask<Integer> task=new FutureTask<>(callable);
        new Thread(task)
                .start();
        try {
            Integer temp=task.get();
            System.out.println("sub thread count result is :"+temp);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * information to wait information.
     */
    public void threadWaitEachOther(){

        int waiter=3;
        CyclicBarrier cyclicBarrier=new CyclicBarrier(waiter);
        final Random random=new Random();

        for(char threadName='A';threadName<='C';threadName++){
            final String name=String.valueOf(threadName);
            Thread thread= new  Thread(()->{

                long prepareTime=random.nextInt(10000)+100;
                System.out.println(name + " need  " +prepareTime +" to prepared some thing.");
                try {
                    Thread.sleep(prepareTime);
                }catch (Exception exception){
                     exception.printStackTrace();
                }

                try {
                    cyclicBarrier.await();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
                System.out.println(name+" Go.");
            });
            thread.start();
        }
    }
}
