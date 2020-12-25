package com.company.lock;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.naming.NamingEnumeration;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁相关的各种姿势点
 */
public class LockInfo {

    private Lock lock = new ReentrantLock();

    /**
     * 读写锁的使用
     * 允许一个线程多次获取同一把锁
     * 个线程获取多少次锁，就必须释放多少次锁
     * 今天没有心情写代码，我草泥马的比；
     * https://segmentfault.com/a/1190000015768003
     * 自适应 自旋 可以根据以往的等待时间经验，计算出一个比较合理的自旋等待时间；整体效果还算是不错的
     * links:https://segmentfault.com/a/1190000015768003
     * <p>
     * 乐观并发：
     * <p>
     * 复读-比较-写 操作；
     * 基本上是基于CAS 操作实现的，CAS 是一种更新的原子操作，比较当前的值和传入值是否一样，一样就更新，否则就失败哈
     * <p>
     * AQS 框架下的锁，
     * <p>
     * 里面还有一个锁升级的概念
     * <p>
     * DB 中也是存在锁升级的概念；
     * <p>
     * JVM从1.5开始，引入了轻量锁与偏向锁，默认启用了自旋锁，他们都属于乐观锁。
     * <p>
     * 要详细了解java对象的结构 对象结构
     * <p>
     * <p>
     * 重量级锁、自旋锁、轻量级锁和偏向锁，
     * <p>
     * 切换 堵塞 挂起 唤醒
     * <p>
     * 它们只需要等一等（自旋），等持有锁的线程释放锁后即可立即获取锁
     * <p>
     * 而是由前一次在通一个锁上的自旋时间以及锁的拥有着的状态来决定的；
     * <p>
     * 基本认为一个线程上下文切换的时间是最佳的一个时间
     * <p>
     * https://juejin.cn/post/6844903796636663815#heading-27
     * <p>
     * 如果线程争用激烈，那么应该禁用偏向锁。
     */
    public void testLockInterruptibly() {
        try {
            // 1.获取锁 如果当前线程未被中断，则获取锁
            lock.lockInterruptibly();
            System.out.println("线程 " + Thread.currentThread().getName() + " 获取了锁！");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程 " + Thread.currentThread().getName() + " 释放了锁！");
            // 必须在 finally 中释放锁，防止死锁
            lock.unlock();
        }
    }

    /**
     * 这一年就要尝试这样去不断的改变...
     */
    public void testInteruptely() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> testLockInterruptibly()).start();
            ;
        }
    }

    private Lock lcoker = new ReentrantLock();

    private void test() {

        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " 尝试获取锁.");
            lock.lock();
            Thread.sleep(5000L);
            System.out.println(name + " 得到锁，进行临界区操作.......");
        } catch (InterruptedException exception) {

            //A 他还是要尝试去获取 释放 资源
            System.out.println(name + " InterruptedException 异常");
        } finally {
            try {
                lock.unlock();
                System.out.println(name + " 尝试释放锁....");
            } catch (Exception exception) {
                System.out.println(name + " 尝试释放锁失败-> 未获得锁的线程call unlock");
            }
        }
    }

    /**
     * 也就是说 单纯的使用 lock 线程被中断了，也会尝试去获取锁，
     * <p>
     * lockinterupte 如果线程在等待过程中终端了，就不会再无获取锁了.
     * <p>
     * arrive()和arriveAndDeregister()方法用于记录到 达，
     * arriveAndAwaitAdvance()方法用于记录到达，并且等待其它未到达的任务。
     * <p>
     * Phaser终止之后，调用register()和bulkRegister(int parties)方法没有任何效果，
     * <p>
     * arriveAndAwaitAdvance()方法也会立即返回。触发终止的时机是在protected boolean onAdvance(int phase,
     * <p>
     * int registeredParties)方法返回时，如果该方法返回true，那么Phaser会被终止
     * <p>
     * information to do something to make me better
     * <p>
     * and
     * <p>
     * create better life and value for world.
     * java basic       info
     * java collection  info
     * java oop         info
     * java stream      info
     * java io          info -> NIO
     * java multi thread lock/ thread sync / thread communication/ interactive
     * java socket    net work programing
     * <p>
     * java alriogim
     * java web develop tool/and libary info.
     *
     * 这就这样，简单的拿到了，两个线程之间的数据交互，整体情况还是很不错的；效果 还是很好的.
     *
     * 这个其实，相当于把各个知识点都跑一边，并没有真正的去学习到内部，底层的结构。我表示后续还要进行实践的复习
     * 再准备新的一轮面试的时候，我表示，还好，还好...
     * 现在的目标，就是狂扫知识点，整体效果，还算ok
     *
     *
     */
    public void mdc() throws Exception {

        Thread thread0 = new Thread(this::test, " A ");
        Thread thread1 = new Thread(this::test, " B ");

        //先启动线程A-> 以便它能够获取到锁
        thread0.start();
        Thread.sleep(100);

        //后启动启动线程B-> 由于锁的原因，-> 进入等待的队列，等待着被唤醒.
        thread1.start();
        Thread.sleep(1000);

        //然后中断我们的线程B , 那么，两种方式的差距就体现在这里
        thread1.interrupt();
    }

    Exchanger<String> exchanger = new Exchanger<>();

    /**
     * there is data exchange between two thread.
     */
    public void threadDataExchange() {

        System.out.println("Thread A -> T0");
        System.out.println("Thread B -> T1");

        System.out.println("--------------after two thread exchanged ---------------------");


        Thread a = new Thread(() -> {
            try {
                String name=Thread.currentThread().getName();
                String data=  " T0 ";
                String exchangedData=exchanger.exchange(data);
                System.out.println("Thread Name: "+name +"  "+exchangedData);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }," A ");

        Thread b=new Thread(()->{
            try {
                String name = Thread.currentThread().getName();
                String data =  " T1 ";
                String exchangedData = exchanger.exchange(data);
                System.out.println("Thread Name: " + name + " " + exchangedData);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }," B ");

        a.start();
        b.start();
    }

}
