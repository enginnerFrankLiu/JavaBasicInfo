package com.company;

import com.company.IO.FileInfo;
import com.company.IO.Pipe;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

    public static String message = null;
    static Thread consumer;

    /**
     *
     */
    static void Fuck() {

        System.out.println("");

        for (SeasonEnum value : SeasonEnum.values()) {

            System.out.println("enum name:" + value.name());
        }

        System.out.println("---------------------------");

        for (SeasonEnum value : SeasonEnum.values()) {
            System.out.println("enum index:" + value.ordinal());

        }

        System.out.println("---------------------------");
        System.out.println("valueOf(String name) 由名称获取枚举类中定义的常量");
        for (SeasonEnum value : SeasonEnum.values()) {
            System.out.println(SeasonEnum.valueOf(value.toString()));

        }
        //it is quick useful and interesting information.
        System.out.println("---------------------------");
        System.out.println("values() return array of seasons info.");
        SeasonEnum[] seasons = SeasonEnum.values();
        for (SeasonEnum season : seasons) {
            System.out.println(season.toString());
        }
    }

    /**
     * demetermination order status whether it is finished.
     *
     * @param orderStatus
     * @return
     */
    static boolean isFinished(int orderStatus) {
        return (OrderStatusEnum.FINISH.getIndex() == orderStatus);
    }

    static void notifyByOrderStatus(OrderStatusEnum orderStatusEnum) {
        switch (orderStatusEnum) {
            case UNPAID:
                System.out.println("老板，你下单了，赶紧付钱吧");
                break;
            case PAID:
                System.out.println("我已经付钱啦");
                break;
            case SEND:
                System.out.println("已发货");
                break;
            case FINISH:
                System.out.println("订单完成啦");
                break;
        }
    }

    /**
     * the differnent way to init enum sets.
     */
    static void infoMS() {
        EnumSet<SeasonEnum> seasonEnums1, seasonEnums2, seasonEnums3, seasonEnums4;
        seasonEnums1 = EnumSet.of(SeasonEnum.SPRING, SeasonEnum.SUMMER);
        seasonEnums2 = EnumSet.complementOf(seasonEnums1);
        seasonEnums3 = EnumSet.allOf(SeasonEnum.class);
        seasonEnums4 = EnumSet.range(SeasonEnum.SPRING, SeasonEnum.WINTER);
        System.out.println(seasonEnums1);
        System.out.println("-----------");
        System.out.println(seasonEnums2);
        System.out.println("-----------");
        System.out.println(seasonEnums3);
        System.out.println("-----------");
        System.out.println(seasonEnums4);
    }

    /**
     *
     */
    static void enumMapInfo() {
        Map<SeasonEnum, String> enumMap = new EnumMap<SeasonEnum, String>(SeasonEnum.class);
        enumMap.put(SeasonEnum.SPRING, "春天");
        enumMap.put(SeasonEnum.WINTER, "冬天");
        enumMap.put(SeasonEnum.FALL, "秋天");
        enumMap.put(SeasonEnum.SUMMER, "夏天");
        System.out.println(enumMap);
    }

    static void testThread() {
        Test tA = new Test("Thread_A");
        Test tB = new Test("Thread_B");
        tA.start();
        tB.start();
    }

    static void Te() {

        Ticket t = new Ticket();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    static void ThreadTest() {

        ThreadInfo threadInfo = new ThreadInfo();
        threadInfo.Write();
        threadInfo.Read();
    }

    /**
     * 这个其实属于线程同步的问题；
     * 一个先写，一个后读
     * 利用时间差的原理
     * 来“保证”了线程执行的先后顺序，避免临界区的线程安全问题.
     */
    static void TestIn() {

        Threads t = new Threads();
        t.Write();
        t.Read();

    }

    static void Td() {
        // Fuck();
        // notifyByOrderStatus(OrderStatusEnum.SEND);
        //infoMS();
        //enumMapInfo();
        //testThread();
        //Te();
        //ThreadTest();
        //TestIn();
        //Test();
//      ThreadX x=new ThreadX();
//      x.consumeInfo();
//      ThreadX x=new ThreadX();
//      x.Info();
//      consumer();
//      producer();
//      produceAndConsume();
//      deadLocker();
        ////
////        Society s=new Society();
////        s.main();
//
//        Fuck fuck=new Fuck();
//        fuck.main();
//
//        Thread.currentThread().join();
//        testInfo();
//        Apple apple=new Apple();
//        apple.subThread();


    }

    /**
     * 线程之间的状态的切换问题.
     * 唤醒的话，也只能让它再执行一次而已巴了.
     */
    static void consumer() {
        consumer = new Thread(() -> {
            System.out.println("consumer consume meesage:->" + message);
        });
        consumer.start();
        if (message == null) {
            consumer.suspend();
        }
    }

    /**
     * main thread produce some message
     */
    static void producer() {
        try {
            while (true) {
                Thread.sleep(1000L);
                message = Thread.currentThread().getId() + " new message.";
                System.out.println("共享内存的方式，来进行message的share.");
                consumer.resume();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Object food;

    public static void produceAndConsume() throws Exception{

        Thread consumeThread=new Thread(()-> {
            if(food==null){
                System.out.println("wait....");
                 Thread.currentThread().suspend();
            }
            System.out.println("run...(call by main thread.)");

        });
        consumeThread.start();
        Thread.sleep(3000L);
        food=new Object();
        System.out.println("main thread ready, then call consume thread to run...continue.");
        consumeThread.resume();

        Thread.sleep(5000L);
    }

    public static void deadLocker(){

        DeadLockExample example=new DeadLockExample();
        example.threadOne();
        example.threadTwo();
    }

    /**
     *
     */
    public static void test() throws  Exception{
        Thread thread=new Thread(()->{
            System.out.println("sub thread is running.....");
        });
        System.out.println("main thread is running....");
        System.out.println("main thread wait sub about 5 m...");
        Thread.currentThread().join(5000L);
        System.out.println("over..");
    }

    /**
     * use thread pool to create thread
     */
    public static void useThreadPool(){
        //这个仅仅是控制线程的数量
        ExecutorService exe= Executors.newFixedThreadPool(3);
        for(int i=0;i<5;i++) {
            exe.submit(new Runnable() {
                @Override
                public void run() {
                    Long threadId = Thread.currentThread().getId();
                    System.out.println("thread id:"+threadId);
                }
            });
        }
        exe.shutdown();
    }

    /**
     *
     */
    public static void useSingleThreadPool(){

        ExecutorService exe=Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++) {
            exe.submit(new Runnable() {
                @Override
                public void run() {
                    Long threadId = Thread.currentThread().getId();
                    System.out.println("thread id:"+threadId);
                }
            });
        }
        exe.shutdown();
    }

    /**
     * different way to start new thread.
     */
    public static void startNewThread() throws Exception{

        System.out.println("1. 继承Thread--------------------");
        RequestThread a=new RequestThread();
        RequestThread b=new RequestThread();
        RequestThread c=new RequestThread();

        a.start();
        b.start();
        c.start();

        Thread.sleep(5000L);
        System.out.println("2.继承Runnable--------------------");
        ResponseThread r1=new ResponseThread();
        Thread t1=new Thread(r1,"thread 1");
        Thread t2=new Thread(r1,"thread 2");
        Thread t3=new Thread(r1,"thread 3");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(5000L);
        System.out.println("3.继承Callable--------------------");

        Callable<String> callable=new IOThread();
        FutureTask<String> futureTask=new FutureTask<>(callable);
        Thread t=new Thread(futureTask);
        t.start();
        System.out.println(futureTask.isDone());
        String result=futureTask.get();
        System.out.println(result);

        Thread.sleep(5000L);
        System.out.println("4.use Thread pool--------------------");

        useThreadPool();

        Thread.sleep(5000L);
        System.out.println("5.use Thread pool(保证线程的执行顺序?)--------------------");
        useSingleThreadPool();

    }
    public static void infomationTest(){

        HashSet<String> set = new HashSet<String>();
        set.add(new String("a"));
        set.add(new String("b"));
        set.add(new String("c"));

        //一旦创建，无法修改原来的值，想修改它的值，都是copy 原来的值，进行修改，然后返回一个新的对象；
        //并不能改变原来的值
        for(String a: set) {
            a="ddd";
        }
        System.out.println(set);
   }

   private static String locker="application-lock-apple";
    public static void testInfo(){
        Thread thread=new Thread(()->{
            try {
                synchronized (locker){

                    System.out.println("main method lock resource.");
                    Thread.sleep(5000L);
                    System.out.println("main method release lock.");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * information for test function.
     *
     * @param args
     */
    public static void main(String[] args) throws  Exception {
        System.out.println("application start.");
//        Pipe p=new Pipe();
//        p.arrayRead();

        FileInfo f=new FileInfo();
      //  f.pathInfo();
      // f.createFile();
      //f.createFileBaseOnDir();
        //f.fileType();
       // f.createDir();
       // f.copyFile();
       // f.testGetFiles();
        // f.wordCount();
        f.readAllFileInfo();
        System.out.println("application end.");

    }

}
