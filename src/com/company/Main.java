package com.company;

import com.company.IO.FileExample;
import com.company.IO.SerialExample;
import com.company.basic.JavaFeatrue;
import com.company.basic.Point;
import com.company.basic.ReflectTest;
import com.company.basic.UserModel;
import com.company.core.*;
import com.company.jvm.Client;
import com.company.jvm.JavaJvm;
import com.company.lock.Demo;
import com.company.lock.LockInfo;
import com.company.lock.ReenternLock;
import com.company.stream.StreamInfo;
import com.company.threadlearn.ExampleInterruptThread;
import com.company.threadlearn.XRunner;
import com.company.threadlearn.callable;
import com.company.threadlearn.runThread.run;
import com.company.threadlearn.threadInterview;
import com.company.threadlearn.threadtest.*;


import java.io.File;
import java.util.*;
import java.util.concurrent.*;

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
//        Pipe p=new Pipe();
//        p.arrayRead();

//        FileInfo f=new FileInfo();
//      //  f.pathInfo();
//      // f.createFile();
//      //f.createFileBaseOnDir();
//        //f.fileType();
//       // f.createDir();
//       // f.copyFile();
//       // f.testGetFiles();
//        // f.wordCount();
//        f.readAllFileInfo();

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

    public static void produceAndConsume() throws Exception {

        Thread consumeThread = new Thread(() -> {
            if (food == null) {
                System.out.println("wait....");
                Thread.currentThread().suspend();
            }
            System.out.println("run...(call by main thread.)");

        });
        consumeThread.start();
        Thread.sleep(3000L);
        food = new Object();
        System.out.println("main thread ready, then call consume thread to run...continue.");
        consumeThread.resume();

        Thread.sleep(5000L);
    }

    public static void deadLocker() {

        DeadLockExample example = new DeadLockExample();
        example.threadOne();
        example.threadTwo();
    }

    /**
     *
     */
    public static void test() throws Exception {
        Thread thread = new Thread(() -> {
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
    public static void useThreadPool() {
        //这个仅仅是控制线程的数量
        ExecutorService exe = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            exe.submit(new Runnable() {
                @Override
                public void run() {
                    Long threadId = Thread.currentThread().getId();
                    System.out.println("thread id:" + threadId);
                }
            });
        }
        exe.shutdown();
    }

    /**
     *
     */
    public static void useSingleThreadPool() {

        ExecutorService exe = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exe.submit(new Runnable() {
                @Override
                public void run() {
                    Long threadId = Thread.currentThread().getId();
                    System.out.println("thread id:" + threadId);
                }
            });
        }
        exe.shutdown();
    }

    /**
     * different way to start new thread.
     */
    public static void startNewThread() throws Exception {

        System.out.println("1. 继承Thread--------------------");
        RequestThread a = new RequestThread();
        RequestThread b = new RequestThread();
        RequestThread c = new RequestThread();

        a.start();
        b.start();
        c.start();

        Thread.sleep(5000L);
        System.out.println("2.继承Runnable--------------------");
        ResponseThread r1 = new ResponseThread();
        Thread t1 = new Thread(r1, "thread 1");
        Thread t2 = new Thread(r1, "thread 2");
        Thread t3 = new Thread(r1, "thread 3");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(5000L);
        System.out.println("3.继承Callable--------------------");

        Callable<String> callable = new IOThread();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start();
        System.out.println(futureTask.isDone());
        String result = futureTask.get();
        System.out.println(result);

        Thread.sleep(5000L);
        System.out.println("4.use Thread pool--------------------");

        useThreadPool();

        Thread.sleep(5000L);
        System.out.println("5.use Thread pool(保证线程的执行顺序?)--------------------");
        useSingleThreadPool();

    }

    public static void infomationTest() {

        HashSet<String> set = new HashSet<String>();
        set.add(new String("a"));
        set.add(new String("b"));
        set.add(new String("c"));

        //一旦创建，无法修改原来的值，想修改它的值，都是copy 原来的值，进行修改，然后返回一个新的对象；
        //并不能改变原来的值
        for (String a : set) {
            a = "ddd";
        }
        System.out.println(set);
    }

    private static String locker = "application-lock-apple";

    public static void testInfo() {
        Thread thread = new Thread(() -> {
            try {
                synchronized (locker) {

                    System.out.println("main method lock resource.");
                    Thread.sleep(5000L);
                    System.out.println("main method release lock.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        thread.start();
    }

    static void testStream() throws Exception {

        StreamInfo streamInfo = new StreamInfo();
//        streamInfo.inputStream();
//        System.out.println("---------------------");
//        streamInfo.inputReader();
//        System.out.println("---------------------");
//        streamInfo.txtOutPutTest();
//        System.out.println("文件的追加效果---------------------");
//        streamInfo.txtOutputAppend();
        //streamInfo.readFileTxt();
//        streamInfo.performanceTest();
//        streamInfo.code();
//
//        FileInfo fileInfo=new FileInfo();
////        fileInfo.dataOutputStreamInfo();
//        fileInfo.dataInputStreamInfo();

//
//        ObjectStream stream=new ObjectStream();
////        stream.objectInfo();
//       // stream.readFromByteArrayInputStream();
//       // stream.byteArrayInputStream();
//        stream.markInfo();
    }

    static void testFileInfo() throws Exception {

        FileExample fileExample = new FileExample();
//        fileExample.copyFileDemo();
//        fileExample.copySpecifyFile();
//        fileExample.copyAllFileInfo();
//        fileExample.copyAllFileInfoV2();
//        fileExample.getLuckBoy();
//        fileExample.inputStudentInfo();
//        fileExample.Info();
        //fileExample.inputStream();
        //fileExample.fileOutputStream2();
//         fileExample.pipeInfo();
//        fileExample.clear();
//         fileExample.compareInfo();
        //fileExample.infoQMs();
//        fileExample.mc();
//        fileExample.infoMD();
//        fileExample.fuckTheLife();
//        fileExample.infoMDV2();
//        fileExample.infoMDV3();
//          fileExample.honestly();
        //fileExample.InfoD();
//        fileExample.charInfoRead();
//        fileExample.charReaderInfo();
//        fileExample.infoMS();
        fileExample.fileChannelInfoQ();


    }

    public static void threadLearnInfo() throws Exception {
        ExampleInterruptThread exampleInterruptThread = new ExampleInterruptThread();
        exampleInterruptThread.start();
        Thread.sleep(1000);
        exampleInterruptThread.interrupt();//终端某个线程；
    }

    /**
     * draft.
     */
    public void vv() {
//        FileInfo file=new FileInfo();
//        //this way we can show all file info.
//        file.showAllFile();
//        testFileInfo();
//        threadLearnInfo();
//
//        Thread.currentThread().join();

    }

    /**
     * Fucking life information.
     * 线程同步类的帮助，在做这件事情哈；
     * 整体来说效果是比较好的哈；
     */
    public static void threadGo() throws Exception {
        threadInterview threadInterview = new threadInterview();
        // threadInterview.info();
//        threadInterview.interactionPrint();
//        threadInterview.allInformationTodoSome();
//          threadInterview.getSubThreadResultInfo();
//        threadInterview.threadWaitEachOther();
//         threadInterview.infoSemaphoreTest();
//        threadInterview.phaserTest();
//        threadInterview.phaserTestInfo();
//        threadInterview.phaerTestInfo2();
//         threadInterview.phaserTest();
//        threadInterview.phaserMD();
//        threadInterview.phaserMD();
//        threadInterview.phaserMD();
//        threadInterview.msd();
//        threadInterview.mds();
//        threadInterview.oneWaitMany();
//        threadInterview.mds();
//        //        lockInfoTest();
//        yieldThreadDemo();
    }

    public static void lockTest() {

        threadInterview interview = new threadInterview();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " 启动 ");
            interview.fuckLife();
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }

    public static void testReEnterLock() {

        ReenternLock.testReEnterLock();
    }

    /**
     * lock information to do something.
     * isInterrupted() 方法返回终止标志true或false
     */
    public static void lockInfoTest() throws Exception {
        LockInfo lock = new LockInfo();
//        lock.testInteruptely();
//        lock.mdc();
        lock.threadDataExchange();
    }

    public static void yieldThreadDemo() {

        new Demo("Thread A");
        new Demo("Thread B");
        new Demo("Thread C");
        new Demo("Thread D");
        new Demo("Thread E");

    }

    public static void MainInfo() throws Exception {
        //     threadInterview threadInterview = new threadInterview();
////        threadInterview.information();
////        threadInterview.interruptedInf();
//        threadInterview.mmd();
//        threadInterview.saturdayLearning();
//        threadInterview.testSyncThis();

//        threadInterview.stopThreadTask();
//        threadInterview.testCollectGarbage();
//        threadInterview.threadOrder();

        //一组线程同时开始，的另外一种机制；整体效果还算是比较OK 的哈
//        threadInterview.threadOrderA();
//        threadInterview.infoQ();
//        threadInterview.runTimeInfo();
//        threadInterview.runTimeInfoQ();
        SerialExample serialExample = new SerialExample();
//        serialExample.testTransient();
        serialExample.externalizeInfo();
    }

    /**
     *
     */
    public static void jvmLearnInfo() {
        Client client = new Client();
//        client.info();
        client.testAnonyInit();
    }

    /**
     * 这种层次关系被称作为双亲委派模型
     */
    public static void classLoaderInfo() {
        ClassLoader loader = Main.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }

    /**
     * information for test function.
     * 这个就是所谓的公平锁机制，整体来说还算是比较ok的信息哈；
     * <p>
     * 如果一个类加载器收到了加载类的请求，它会先把请求委托给上层加载器去完成，
     * 上层加载器又会委托上上层加载器，
     * 一直到最顶层的类加载器；如果上层加载器无法完成类的加载工作时，
     * 当前类加载器才会尝试自己去加载这个类。
     * <p>
     * 准备阶段：
     * jvm 会为该类变量（静态变量，static修饰的） 分配内存，并初始化每种类型的的默认值.注意，这里说的是默认值；
     * 如果是静态常量，一旦不知就不会改变的那种，所以，这会为 final static 赋值
     * 类成员变量 不会分配内存
     * 类静态变量 分配内存-> null， 具体的值在初始化阶段进行赋值
     * 类静态常量 一旦赋值就不会被改变了。所以赋值
     * <p>
     * 解析阶段：
     * 将常量次中的符号引用准换成直接引用
     * <p>
     * 扩展类加载器，负责加载 java_home/lib/ext/目录下的文件
     * <p>
     * Java虚拟机对class文件采用的是按需加载的方式，也就是说当需要使用该类时才会将它的class文件加载到内存生成class对象
     */
    public static void classLoaderMD() {
        String s = System.getProperty("java.ext.dirs");
        File[] dirs;
        if (s != null) {
            StringTokenizer st =
                    new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(st.nextToken());
            }
        } else {
            dirs = new File[0];
        }
        for (File dir : dirs) {
            System.out.println(dir.getName());
        }
    }

    public static void testStringIntern() {
        JavaJvm javaJvm = new JavaJvm();
//        javaJvm.info();
//        javaJvm.infoV2();
//        javaJvm.internTest();
//        javaJvm.internTestJJ();
//        javaJvm.md();
        javaJvm.msInfo();
    }

    //过去的时间；
    public static void fooInfo() throws Exception {

//        LocalDate date = Instant.now().atZone(ZoneId.of("GMT-12")).toLocalDate();
//        System.out.println(date);
//        LocalDate candidateValidFrom = LocalDate.MIN;
//        System.out.println(candidateValidFrom);


        ReflectTest reflectTest = new ReflectTest();
//        reflectTest.showAnnotation();
//        reflectTest.testFoo();
//        reflectTest.infoMD();
//          reflectTest.filedInfo();
//        reflectTest.fieldInfo();
        reflectTest.methodInfo();
    }

    /**
     * 分布式事务的常见实现方式
     * 1.两阶段提交
     * 2.三阶段提交
     * 3.TCC模式
     * 4.消息驱动
     * 本地消息表
     * MQ消息中间件
     * 消息事务RocketMQ
     * 5.saga模型
     * 6.最大努力通知.
     * <p>
     * 等等一系列的方法和实现方式
     * 1.一旦要考虑实现分布式，那么问题就是接踵而至的到来
     * 2.分库分表
     * 怎么去分，分了之后，当select * from user where userid=990;
     * 我应该从哪个库，哪个表去找数据呢?
     * 3.随之带来的分布式事务如何实现呢?
     * <p>
     * 4.分布式锁又如何实现呢?
     * <p>
     * 5.分布式session又如何处理呢？
     * <p>
     * 分布式，事务锁
     * 接口幂等性的问题，这些都是我们需要学习和了解的东西.
     * <p>
     * 看够了，我们就来谢谢代码呗.
     */
    public void infodddd() {

    }

    /**
     * 妈的，有一段时间没有写代码了，卧槽;
     * 最近发生各种很鸡婆，几把的各种琐事
     * 那就安排一下过年的计划，和变化中的可能的变化
     * <p>
     * 1.还是要假吧意思的吃个年夜饭
     * 2.外婆一天
     * 3.大姨妈一天
     * 4.相一两个亲来看看呗，我想也是的
     * <p>
     * 又他妈的有一段时间 没有写代码了，前方的路，真的很长.
     */
    public static void teee() {


        List<String> names = new ArrayList<>();
        names.add("jack");
        names.add("tom");
        names.add("frank");

        names.removeIf(x -> x.equals("tom"));

        System.out.println(names);
    }

    /**
     * ExceptionInfo
     * log
     */
    public static void DD() {

        // ExceptionInfo exceptionInfo = new ExceptionInfo();
//        exceptionInfo.infoJ();
//        exceptionInfo.getSuperClassInfo();
//        exceptionInfo.getInterface();
//        exceptionInfo.getDeclareMethodInfo();
//        exceptionInfo.arrayInfo();
//          exceptionInfo.dynamicCreateArray();
////        exceptionInfo.construct();
//        RestoreAliases restoreAliases = new RestoreAliases();
////        restoreAliases.exe();
//        restoreAliases.reflectInfo();
//
//        ReflectInfoMS reflectInfoMS=new ReflectInfoMS();
////        reflectInfoMS.showParameterizedType();
//        reflectInfoMS.showGenericArrayType();

//        TypeApplication<Integer> md=new TypeApplication<>();
//        md.showJJ();
//
//        RestoreAliases restoreAliases=new RestoreAliases();
//        restoreAliases.showInfo();

//        System.out.println(SubClass.val);
//
//        SuperClass [] subs=new SuperClass[10];
//        System.out.println(subs);
//        SubClass subClass=new SubClass();

//        System.out.println(SubClass.HE);

//
//        Dog dog = new Dog();
//        dog.foo3();
//        dog.foo4();
//        dog.foo0();

//System.out.println(Cat.info);
//        System.out.println(Cat.version);
//        dog.md();
//        dog.bar0();
////         dog.loop();
//         dog.internInfo0();
//         // dog.internInfo1();
//对于 CONSTANT_Class_info（此类型的常量代表一个类或者接口的符号引用）
//        String fuck=null;
//
//        String shit="life";
//
//        System.out.println(fuck+shit);

        //System.out.println(Cat.version);
//            System.out.println(Cat.info);
        //父类中定义的静态语句块要优先于子类变量的赋值操作
//        System.out.println(Person.B);

//
//        List<Rate> rateList=new ArrayList<>();
//        Rate rate=new Rate();
//        rate.setId("");
//        rateList.add(rate);
//
//        Rate rate1=new Rate();
//        rate1.setId("adf");
//        rateList.add(rate1);
//
//        rateList.stream().filter(x->"".equals(x.getId())).forEach(x->{
//            x.setId(null);
//        });


    }

    public static void doInfo() {
//        Person p=new Person();
//        p.compare();
//
//        Face f=new Face();
//        f.showInfo();
//
//        Product product=new Product();
//        Point point=new Point(1,2);
//        product.setId(2);
//        product.setName("tom");
//        product.setPoint(point);
//
//        Product productClone=product.shallowClone();
//
//        System.out.println(product==productClone);
//        System.out.println(product.getPoint()==productClone.getPoint());

//        /**
//         * 这种简单的排序功能，能够更好的升华我们的品质
//         */
//        int[] ints = new int[3];
//        ints[0] = 3;
//        ints[1] = 2;
//        ints[2] = 1;
//        Arrays.sort(ints);
//
//        for (int anInt : ints) {
//            System.out.println(anInt);
//        }


        /**
         * 这样就自带了 去除重复和 排序的功能
         * 整体效果是非常棒的一种实现，非常好的额；
         * 因为它实现了compareable 接口
         *
         * 如果你想再set中 自动去除自定义的类对象，
         * 那么你就要重写对象的equals 和 hashcode的方法，整体效果还算是可以的；
         */
//        Set<String> sets=new HashSet<>();
//        sets.add("z");
//        sets.add("z");
//        sets.add("b");
//        sets.add("a");
//        sets.add("c");
//        System.out.println(sets);
//
//        Product product0=new Product();
//        product0.setId(0);
//
//        Product product1=new Product();
//        product1.setId(0);
//
//        Product product2=new Product();
//        product2.setId(1);
//
//        Product product3=new Product();
//        product3.setId(2);
////
////        Set<Product> products=new HashSet<>();
//
//        List<Product> products=new ArrayList<>();
//        products.add(product0);
//        products.add(product3);
//        products.add(product1);
//        products.add(product2);
//
////
////        Collections.sort(products);
//
//        System.out.println(products.size());
//        for (Product product : products) {
//            System.out.println(product.getId());
//        }
////
//
//        System.out.println(product0.compareTo(product2));
//
//        System.out.println(product0.compareTo(product1));
//
//        System.out.println(product2.compareTo(product0));
//
//
//

        /**
         * https://www.imooc.com/article/33074
         * https://juejin.cn/post/6844903769835044872
         * 它的比较 是直接计算出差值得啊，卧跟我想的有点不一样的啊；
         //         */


    }

    public static void showInfo() {
        JavaFeatrue javaFeatrue = new JavaFeatrue();
//        javaFeatrue.doFilter();
//        System.out.println("__________________");
//        javaFeatrue.doFilter2();

////        javaFeatrue.infod();
//
//        javaFeatrue.listToMap();

//        javaFeatrue.sortList();
//        javaFeatrue.listToMap();
//        javaFeatrue.functionInfo();
//        javaFeatrue.listStringToMapInfo();
//        javaFeatrue.listStringToMapInfo();
//        javaFeatrue.listStringToMapInfoV2();
//        javaFeatrue.streamInfoQ();

//        javaFeatrue.createStreamFromArray();

//        javaFeatrue.consumerInfo();

//        javaFeatrue.ms();
//        javaFeatrue.joinInfo();
//        javaFeatrue.groupBy();
//          javaFeatrue.groupBySet();
//        javaFeatrue.syncGroupBy();
//        javaFeatrue.partitioningBy();

    }

    public static void mxx() {

//        UserModel userModel=new UserModel();
////        userModel.version=90;
//        UserModel.count=100;

//
//        //对象引用不可变 和 对象（内容、状态）不可变是两回事儿吧
////        UserModel.point=new Point(12,12);
//          UserModel.point.setX(100);
//        UserModel.point.setX(30000);
        try {

//            printTask003 print=new printTask003();
//            print.demo();
//            printTask004 task=new printTask004();
//            task.testDD();
//
            Task006 task = new Task006();
            task.demoInfo();

//            ThreadNotify threadNotify=new ThreadNotify();
//            threadNotify.demo();

//            ThreadNotify threadNotify=new ThreadNotify();
//            threadNotify.demo();
//            Task007 task=new Task007();
//            task.demo();


        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    private static void fu() {
        int order = 10;
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(order);
        for (int i = 0; i < order; i++) {
            new Thread(new XRunner(i, begin, end)).start();
        }
        try {

            Thread.sleep(5000);
            System.out.println("各就各位：pa");
            begin.countDown();

            System.out.println("裁判等待所有人都跑完（最后一个人都跑完了）");
            end.await();
            System.out.println("judge say : all arrived !");

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void fuckthelife(){

        System.out.println("info q.");
    }

    public void infoms(){
        System.out.println("log data into database.");
    }

    public static void main(String[] args) throws Exception {
        System.out.format("application start.").println();

        fu();

    }

}
