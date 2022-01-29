package com.company.threadlearn.threadtest;

import com.company.threadlearn.runThread.AggRunnable;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import sun.awt.AWTAccessor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 现在我们就来思考:
 * 两个locker 如何实现这这种print的逻辑;
 * <p>
 * 先尝试做两个出来，再在原来的基础上进行叠加.
 */
public class printTask004 {


    private class Task implements Runnable {

        private Object ALock;
        private Object BLock;
        private String val;

        public Task(Object bLock, Object aLock, String val) {
            this.BLock = bLock;
            this.ALock = aLock;
            this.val = val;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (BLock) {
                    synchronized (ALock) {
                        System.out.println(this.val);
                        ALock.notify();
                    }
                    try {
                        BLock.wait();
                    } catch (InterruptedException exception) {
                        System.out.println("b locker was interrupted.");
                    }

                }
            }
        }

    }


    public void demo() throws Exception {
        Object ALock = new Object();
        Object BLock = new Object();
        Thread A = new Thread(new Task(BLock, ALock, "A"));
        Thread.sleep(10);//保证初始ABC的启动顺序
        Thread B = new Thread(new Task(ALock, BLock, "B"));
    }

    public void test() throws Exception {

        Object ALock = new Object();
        Object BLock = new Object();
        Object CLock = new Object();

        Thread a = new Thread(() -> {
            synchronized (ALock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " stop.");
                    ALock.wait();
                    System.out.println(Thread.currentThread().getName() + " run again.");
                } catch (Exception exception) {

                }
            }
        });


        Thread b = new Thread(() -> {
            synchronized (ALock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " stop.");
                    ALock.wait();
                    System.out.println(Thread.currentThread().getName() + " run again.");
                } catch (Exception exception) {

                }
            }
        });


        Thread c = new Thread(() -> {
            synchronized (ALock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " stop.");
                    ALock.wait();
                    System.out.println(Thread.currentThread().getName() + " run again.");
                } catch (Exception exception) {

                }
            }
        });


        Thread d = new Thread(() -> {
            synchronized (ALock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " stop.");
                    ALock.wait();
                    System.out.println(Thread.currentThread().getName() + " run again.");
                } catch (Exception exception) {

                }
            }
        });


        a.start();
        b.start();
        c.start();
        d.start();

        /**
         * notify 和 wait 只能出现在 lock 语句中滴呀.
         *针对的是同一把锁的操作
         */
        TimeUnit.SECONDS.sleep(4);
        synchronized (ALock) {
            System.out.println("after main thread sleep 4.");
            // ALock.notify(); 只换新了其中一个线程.
            // ALock.notifyAll();// 换新了所有的线程
        }
    }

    public void testInfo() throws Exception {

        Object ALock = new Object();
        Object BLock = new Object();


        /**
         *
         * 也就必须说明必须在同一个lock语句块中.
         *
         *
         */

        Thread a = new Thread(() -> {
            synchronized (ALock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " wait.");
                    ALock.wait();
                } catch (InterruptedException exception) {
                    System.out.println(exception);
                }
            }

        });

        Thread b = new Thread(() -> {
            synchronized (ALock) {
                System.out.println(Thread.currentThread().getName() + " notify.");
                ALock.notify();
            }
        });
        a.start();
        TimeUnit.SECONDS.sleep(2);
        b.start();
    }


    /**
     * 两个线程 同时并发，先打印A 再打印 B.
     *
     * @throws Exception
     */
    public void testDDF() throws Exception {

        Object ALock = new Object();

        //如果让thread a 先 run 起来了 那么会导致信号丢失，thread B 永远的堵塞
        Thread threadA = new Thread(() -> {
            synchronized (ALock) {
                System.out.println("A");
                ALock.notify();
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (ALock) {
                try {
                    ALock.wait();
                    System.out.println("B");
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        threadB.start();
        Thread.sleep(100); //要先让B run起来.
        threadA.start();
    }

    private class State {
        private volatile int state;

        public State(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    /**
     * `
     * 这个就是第二种方式
     */
    private class xTask implements Runnable {

        private State state;

        public xTask(State state) {
            this.state = state;
        }

        @Override
        public void run() {
            while (state.getState() % 2 == 0) {
                System.out.println("A");
                state.setState(1);
                break;
            }
        }
    }

    private class yTask implements Runnable {
        private State state;

        public yTask(State state) {
            this.state = state;
        }

        @Override
        public void run() {
            while (state.getState() % 2 != 1) {
                System.out.println("B");
                break;
            }
        }
    }

    /**
     * 这样才算得上是 同时并发
     *
     * @throws Exception
     */
    public void testAA() throws Exception {

        State state = new State(0);
        Thread threadA = new Thread(new xTask(state));
        Thread threadB = new Thread(new yTask(state));
        threadA.start();
        threadB.start();

    }

    /**
     * 两个线程 同时并发，先打印A 再打印 B.
     *
     * @throws Exception
     */
    public void testDD() throws Exception {
        Object ALock = new Object();
        Object BLock = new Object();
        Thread threadA = new Thread(() -> {
            synchronized (BLock) {
                synchronized (ALock) {
                    System.out.println("A");
                }
                try {
                    BLock.wait();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (ALock) {
                System.out.println("B");
            }
        });
        //
        threadA.start();
        Thread.sleep(10);
        threadB.start();
    }


    private class ARunnable implements Runnable {

        private Semaphore ASemaphore;
        private Semaphore BSemaphore;

        public ARunnable(Semaphore ASemaphore, Semaphore BSemaphore) {
            this.ASemaphore = ASemaphore;
            this.BSemaphore = BSemaphore;
        }

        @Override
        public void run() {
            try {

                ASemaphore.acquire();
                System.out.println("A");
                ASemaphore.release();
                BSemaphore.release();
            } catch (Exception exception) {
                System.out.println(exception);
            }

        }
    }

    private class BRunnable implements Runnable {

        private Semaphore BSemaphore;

        public BRunnable(Semaphore BSemaphore) {
            this.BSemaphore = BSemaphore;
        }

        @Override
        public void run() {
            try {
                BSemaphore.acquire();
                System.out.println("B");

            } catch (Exception exception) {

            }
        }
    }

    /**
     * 不太好控制线程的执行顺序，比如，有一个order一类的属性；
     * 线程的调度顺序是不太好控制的.
     * 设置优先级也不是特别靠谱的一种做法
     * Semaphore 还有个特点就是：线程相干性；可以有B线程去 release()
     */
    public void info() {
        Semaphore ASemaphore = new Semaphore(1);
        Semaphore BSemaphore = new Semaphore(0);

        Thread threadX = new Thread(new ARunnable(ASemaphore, BSemaphore));
        Thread threadY = new Thread(new BRunnable(BSemaphore));

        threadX.start();
        threadY.start();

        //线程 任务 B 会直接被block起来，然后A 执行完后，释放线程；

    }

}
