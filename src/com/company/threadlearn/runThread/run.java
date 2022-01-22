package com.company.threadlearn.runThread;

import sun.awt.windows.ThemeReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 现实开发中，我们很少开启一个线程 让他去完成一项任务
 * 然后我们就不管它了
 * 是否发生了异常？
 * 任务是否完毕？
 * 是否返回我们想要的结果
 * 线程是进程中的一个实体，是被系统独立调用和分派的基本单位；线程自己并不拥有系统资源
 * 只是拥有一点运行中必不可少的资源，但是她
 * 但它可与同属一个进程的其它线程共享进程所拥有的全部资源。一个线程可以创建和撤消另一个线程，同一进程中的多个线程之间可以并发执行。线程有就绪、阻塞和运行三种基本状态
 */
public class run {

    public void way1() {
        loadTextInfoThread load = new loadTextInfoThread();
        load.start();
    }

    public void way2() {
        Thread thread = new Thread(new loadTextRunnable());
        thread.start();
    }

    /**
     * futureTask 必须放在thread 中运行
     */
    public static void way3() {
        try {
            FutureTask<String> future = new FutureTask<>(new loadTextCallable<>());
//            future.cancel(true);
//            System.out.println("cancle");
            Thread thread = new Thread(future);
            thread.start();

//            System.out.println("can");
//            future.cancel(true);
//
//            System.out.println(future.get());

//            future.cancel(true);
//            System.out.println("c");

            thread.interrupt();
            System.out.println(Thread.interrupted());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void way4() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Callable<String> callable = new loadTextCallable<>();
            Future<String> future = executor.submit(callable);
            list.add(future);
        }

        for (Future future : list) {
            try {
                System.out.println(future.get());
            } catch (Exception exception) {
                System.out.println(exception);
            }
        }
    }

    public static void stopThread() throws Exception {
        printRunnable printRunnable = new printRunnable();
        Thread thread = new Thread(printRunnable);
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println("system stop the thread task.");
        printRunnable.cancel();
    }

    public static void stopThreadV2() throws Exception {
        printRunnableV2 printRunnablev2 = new printRunnableV2();
        Thread thread = new Thread(printRunnablev2);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
        System.out.println(thread.isInterrupted());






    }
}
