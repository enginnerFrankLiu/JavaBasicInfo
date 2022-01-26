package com.company.threadlearn.runThread;

import sun.awt.windows.ThemeReader;

import java.sql.Time;
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

            TimeUnit.SECONDS.sleep(3);
            thread.interrupt();
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());
            System.out.println(thread.isInterrupted());

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

    /**
     * @throws Exception
     */
    public static void stopThreadV2() throws Exception {
//        printRunnableV2 printRunnablev2 = new printRunnableV2();
//        Thread thread = new Thread(printRunnablev2);
//        thread.start();
//        TimeUnit.SECONDS.sleep(3);
//        thread.interrupt();
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());
//        System.out.println(thread.isInterrupted());

        SleepRunnalbe sleepRunnalbe = new SleepRunnalbe();
        Thread thread = new Thread(sleepRunnalbe);
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
        System.out.println("interrupt.....");

    }

    //构造函数中的permits，只是初始的线程可以进入临界区的数量
    public static void test() {
        Semaphore semaphore = new Semaphore(1);

        semaphore.release();
        semaphore.release();
        semaphore.release();
        semaphore.release();
        System.out.println(semaphore.availablePermits());

    }

    /**
     * 两个线程同时开始，等待两个线程都完成了任务我才继续下面的任务
     */

    public static void waitAllThreadCompletion() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread threadA = new Thread(new ARunnable(countDownLatch));
        Thread threadB = new Thread(new BRunnable(countDownLatch));
        threadA.start();
        threadB.start();

        countDownLatch.await();
        System.out.println("all thread task done.");
    }

    /**
     * 这个是堵塞调用它的主线程；
     * 整体效果还算比较OK的啦.
     * <p>
     * 如果都同时放在同一个主线程中，就没有多大的意义了。
     * 总体来说，效果还是可以的；
     *
     * @throws Exception
     */
    public static void waitAllStepCompletion() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread = new Thread(new CRunnable(countDownLatch));
        thread.start();

        countDownLatch.await();

        System.out.println("all step done.");
    }

    public static void waitAll() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread threadA = new Thread(new DRunnable(cyclicBarrier));
        Thread threadB = new Thread(new ERunnable(cyclicBarrier));
        threadA.start();
        threadB.start();

    }

}
