package com.company;

import com.sun.xml.internal.ws.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.nio.file.NotLinkException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

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
        // Te();
        // ThreadTest();
        // TestIn();

        //        consumer();
//        producer();
        //  produceAndConsume();

//        deadLocker();

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
     * information for test function.
     *
     * @param args
     */
    public static void main(String[] args) throws  Exception {
        System.out.println("application start.");

        //test();

        ThreadX x=new ThreadX();
        x.consumeInfo();


        Thread.currentThread().join();
        System.out.println("application end.");

    }

}
