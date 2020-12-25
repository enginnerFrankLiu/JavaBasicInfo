package com.company.threadlearn;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

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
        final Phaser phaser = new Phaser();
        for (int i = 0; i < count; i++) {
            try {
                System.out.println("starting thread , id :" + i);
                Thread.sleep(i * 1000L);
                phaser.register();
                Thread thread = new Thread(new phaserTaskInfo(i, phaser));
                thread.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }

    public void phaserTestInfo() {
        Phaser phaser = new Phaser(3);

        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {

            threads[i] = new Thread(new PhaserRunable(phaser));
            threads[i].start();
        }
    }

    /**
     *这些目前只是一个些很初步的了解，后面我们深入了解后，我们再进行各种源码级别的深入解刨吧.
     *
     */
    public void phaerTestInfo2() {

        final Phaser phaser = new Phaser(2);

        for (int i = 0; i < 5; i++) {

            phaser.register();

            Thread thread = new Thread(new PhaserRunable2(i, phaser));

            thread.start();
        }

        System.out.println("press enter to continue...");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {

            reader.readLine();

        } catch (Exception exception) {

            exception.printStackTrace();
        }
        phaser.arriveAndDeregister();

        System.out.println("press enter to continue...");


    }

    /**
     * 你可以 先不指定我们的数量；
     * 但是要调用 register 方法进行注册；
     * <p>
     * 如果没有达到，指定的屏障数量，将一直等待；
     */
    public void phaserMD() {
        Phaser phaser = new Phaser(50);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                //phaser 0
                System.out.println(phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                //phaser 1
                System.out.println(phaser.getPhase());
            }).start();
        }
    }

    /**
     * 你们也可以先call 在构造函数中，先进行各种注册；
     * 整体效果都是差不多的哈
     * <p>
     * 理解可能是错的；
     */
    public void phaserJJ() {
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    System.out.println("Thread id: " + Thread.currentThread().getId() + "  " + phaser.getPhase());
                    long threadId = Thread.currentThread().getId();
                    if (threadId % 2 == 0) {
                        System.out.println("thread id :" + Thread.currentThread().getId() + "arriveAndDeregister");
                        phaser.arriveAndDeregister(); //遇到屏障的时候，直接通过，不在等在；s
                    } else {
                        Thread.sleep(threadId * 5000L);
                    }

                    phaser.arriveAndAwaitAdvance();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                phaser.arriveAndAwaitAdvance();
                System.out.println("Thread id: " + Thread.currentThread().getId() + "  " + phaser.getPhase());

            }).start();
        }
    }

    public void OG() {
        Phaser phaser = new Phaser(3);
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new PhaserRunnableA(phaser));
            threads[i].start();
        }

        for (int i = 0; i < 3; i++) {
            try {
                threads[i].join();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void msd() throws Exception {
        Phaser phaser = new Phaser(1);
        for (int i = 0; i < 3; i++) {
            phaser.register();
            Thread thread = new Thread(() -> {
                System.out.println(phaser.getPhase());
                phaser.arriveAndAwaitAdvance();
                System.out.println(phaser.getPhase());
            });
            thread.start();
        }
        System.out.println("main line sleep some time.");
        Thread.sleep(5000L);
        phaser.arriveAndDeregister();  //减少当前的屏障数量；
        System.out.println("main thread release info.");
    }

    /**
     * 这种，有点类似于闭包传递参数的感觉
     * <p>
     * parties 表示每次拦截的线程数量，该值在构造时 进行赋值.
     * count: 内部计数器，它的初始值为parites相同的，以后每一次await 都会调用减去1；
     * 直到最后减为0;
     * <p>
     * 也可以理解为：最后一个线程到达后需要执行的动作；
     */
    public void mds() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("每次通过屏障后，都会执行一次的callback函数.");
        });
        for (int i = 1; i <= 7; i++) {
            int fi = i;
            new Thread(() -> {
                try {
                    Thread.sleep(5000l);
                    cyclicBarrier.await();
                    System.out.println("干完第一次");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                //他后Ta 有重复执行的能力?
                try {
                    Thread.sleep(5000l);
                    cyclicBarrier.await();
                    System.out.println("干完第二次");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                //他后Ta 有重复执行的能力? 它可以循环使用的一种方式.

                try {
                    Thread.sleep(5000l);
                    cyclicBarrier.await();
                    System.out.println("干完第三次");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }, String.valueOf(i)).start();
            //这个不是传递进去的参数，而是我们的thread 的名称罢了，整体效果还算是ok的
        }
    }

    /**
     * 一个线程等待 其他多个线程执行完毕之后才开始
     *
     * 大概有以下的四种方式来实现线程的同步控制，和线程间的交互控制，整体来说，还算比较OK的.
     *
     * Semaphore
     *
     * Semaphore是一种在多线程环境下使用的设施，该设施负责协调各个线程，以保证它们能够正确、合理的使用公共资源的设施
     *也是操作系统中用于控制进程同步互斥的量 管理资源能够同时被多少个线程共同访问，但是不能保证线程之间的安全；
     * 典型的的例子就是：限流操作.
     *
     * CountDownLatch
     *
     * CyclicBarrier
     *
     * Phaser
     *
     *
     */
    public void oneWaitMany() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            int j = i;
            new Thread(() -> {
                try {
                    long waitTime = j * 5000L;
                    System.out.println("thread id " + Thread.currentThread().getId() + " sleep " + waitTime + " second.");
                    Thread.sleep(waitTime);
                    countDownLatch.countDown();  //每个线程调用一次，就会进行一次减一操作，直到减到为0 然后 唤醒白block的线程;
                    //后面的代码也会被执行，但是这里一般不执行代码；
                    //当任务执行完之后，我们call countDown.
                    System.out.println(" After call countDown.");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }).start();
        }
        try {
            System.out.println("main thread wait other sub thread....");
            countDownLatch.await();
            System.out.println("main thread run again....");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    //phaser 的基本总结；提供了更为灵活的应用，总体中应用还是挺好的效果；
    //支持在多个点之间同步，并且支持动态的注册和取消任务数量(到达屏障的线程数量的阈值 都是可以进行动态调整的.)

    /**
     * information to do something.
     *
     *  set value as true;
     *  设置为公平锁.
     *
     *  非公平锁的缺点：队列中的某些线程可能一直或者长时间的获取不到锁，；；
     *
     */
    private ReentrantLock lock=new ReentrantLock(true);
    public void fuckLife(){

        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 获得了锁.");
        }catch (Exception exception){
            exception.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 1.如果线程处于sleep 和 wait 状态 -> call interrupt()-> 会抛出 异常（InterruptedException）
     *
     * 2.如果线程未处于睡眠 或者 等待的状态 call interrupt 将正常点行为. 而仅仅是将终端标识设置为 true;
     *
     */
    public void information() throws Exception{

        Thread thread=new Thread(()->{
           try{
               System.out.println("sleep。。");
               Thread.sleep(60000L); // sleep只是个状态。。。睡醒钱被打断了
               System.out.println("task"); //这里的代码是不会被执行的
           }catch (InterruptedException exception){
               System.out.println("interrupted exception.(interrupt exception).");
           }
           //这里的代码是可以执行的，整体感觉还不错
           System.out.println("打只是打断线程的状态(sleep || wait ) 方法. 只是打断状态，依然是可以继续run 的哈..");

        });

        thread.start();
        Thread.sleep(5000L);
        thread.interrupt();

    }

    public void interruptedInf(){

        System.out.println("_____________________");
        Thread thread=new Thread(()->{
            for(int i=0;i<5;i++){
                System.out.println(i);

                //这个是静态方法，卧槽；
                System.out.println(Thread.interrupted());
            }
        });

        System.out.println(thread.isInterrupted());
        thread.start();
        thread.interrupt();
        System.out.println(thread.isInterrupted());

    }


}
