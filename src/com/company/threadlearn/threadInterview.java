package com.company.threadlearn;

import java.util.Random;
import java.util.concurrent.*;

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
     * <p>
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
     * <p>
     * 这个类可以：
     * 使一个线程其他类型的线程各自完成自己的工作后，再执行，
     * 一个线程等待其他线程完成各自的工作后，再次执行；
     */
    public void allInformationTodoSome() {
        int threadCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {

            String name = String.valueOf(threadName);
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println(name + " run ->>>>>> over.");

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                countDownLatch.countDown();
            });
            thread.start();
        }

        Thread msThread = new Thread(() -> {
            try {
                countDownLatch.await();
                Thread.sleep(100);
                System.out.println("D run ->>>>>> over.");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        msThread.start();
    }

    /**
     * information to do something.
     * 线程同步
     * 线程通信
     * 线程安全
     * 等等等问题的一个基本了解吧；
     * 后续还设有更多的提，需要我们要不断练习吧；
     */
    public void getSubThreadResultInfo() {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                int result = 0;
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(100);
                    result += i;
                }
                return result;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        new Thread(task)
                .start();
        try {
            Integer temp = task.get();
            System.out.println("sub thread count result is :" + temp);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * information to wait information.
     * 等待，所有方式，都获取到了方式后，
     * CountDownLatch
     * 这样的方式，才可以执行我们的方法；
     */
    public void threadWaitEachOther() {
        int waiter = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(waiter);
        final Random random = new Random();
        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String name = String.valueOf(threadName);
            Thread thread = new Thread(() -> {

                long prepareTime = random.nextInt(10000) + 100;
                System.out.println(name + " need  " + prepareTime + " to prepared some thing.");
                try {
                    Thread.sleep(prepareTime);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                System.out.println(name + " Go.");
            });
            thread.start();
        }
    }

    //最多允许三线程同时，在临界区，"办事儿";
    private static Semaphore semaphore = new Semaphore(3);

    public void infoSemaphoreTest() {
        int studentCount = 10;
        for (int i = 0; i < studentCount; i++) {
            new SchoolStudent(semaphore, "student " + i).start();
        }
    }

    /**
     * 这个就是，我们的基本处理方式，和基本的同步方式，整体来是不错的哈
     * A B C D
     * <p>
     * case 1 A B C  都把事情做完后，D 才是开始工作(一个线程，等待多个线程 执行完成后才开始工作)
     * <p>
     * case 1 A B C D 四个线程都准备好后才同时开始执行，就像四名运动员同时准备好后，同时执行.
     * <p>
     * Semaphore
     * <p>
     * 同时允许执行的线程数量
     * <p>
     * <p>
     * 于此同时，还设有我们的 Phaser 和  Exchanger
     * <p>
     * 支持动态调整注册任务数量；
     */
    public void summaryInfo() {


    }

    /**
     * information.
     * 目前这个例子，也是：
     * 相当于，等待所有的线程都准备完毕后，所有的线程才是一起执行的；
     */
    public void phaserTest() {
        int count = 5;
        final Phaser phaser = new Phaser(count);
        for (int i = 0; i < count; i++) {
            try {
                System.out.println("starting thread , id :" + i);
                Thread.sleep(1000L);
                Thread thread = new Thread(new phaserTaskInfo(i, phaser));
                thread.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }
}
