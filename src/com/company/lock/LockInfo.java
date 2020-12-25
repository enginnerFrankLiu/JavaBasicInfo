package com.company.lock;

import javax.naming.NamingEnumeration;
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
     *
     * 乐观并发：
     *
     *  复读-比较-写 操作；
     *  基本上是基于CAS 操作实现的，CAS 是一种更新的原子操作，比较当前的值和传入值是否一样，一样就更新，否则就失败哈
     *
     *  AQS 框架下的锁，
     *
     *  里面还有一个锁升级的概念
     *
     *  DB 中也是存在锁升级的概念；
     *
     *  JVM从1.5开始，引入了轻量锁与偏向锁，默认启用了自旋锁，他们都属于乐观锁。
     *
     *  要详细了解java对象的结构 对象结构
     *
     *
     * 重量级锁、自旋锁、轻量级锁和偏向锁，
     *
     *  切换 堵塞 挂起 唤醒
     *
     *  它们只需要等一等（自旋），等持有锁的线程释放锁后即可立即获取锁
     *
     *  而是由前一次在通一个锁上的自旋时间以及锁的拥有着的状态来决定的；
     *
     *  基本认为一个线程上下文切换的时间是最佳的一个时间
     *
     *  https://juejin.cn/post/6844903796636663815#heading-27
     *
     *  如果线程争用激烈，那么应该禁用偏向锁。
     *
     */
    public void testLockInterruptibly(){
        try {
            // 1.获取锁 如果当前线程未被中断，则获取锁
            lock.lockInterruptibly();
            System.out.println("线程 " + Thread.currentThread().getName() + " 获取了锁！");
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("线程 " + Thread.currentThread().getName() + " 释放了锁！");
            // 必须在 finally 中释放锁，防止死锁
            lock.unlock();
        }
    }

    /**
     * 这一年就要尝试这样去不断的改变...
     *
     */
    public void testInteruptely(){
        for(int i=0;i<5;i++){
            new Thread(()->testLockInterruptibly()).start();;
        }
    }

    private Lock lcoker=new ReentrantLock();

    private void test(){

        String name=Thread.currentThread().getName();
        try{
            System.out.println(name+" 尝试获取锁.");
            lock.lock();
            Thread.sleep(5000L);
            System.out.println(name +" 得到锁，进行临界区操作.......");
        }
        catch (InterruptedException exception){

             //A 他还是要尝试去获取 释放 资源
            System.out.println(name+ " InterruptedException 异常");
        }finally {
            try{
                lock.unlock();
                System.out.println(name+ " 尝试释放锁....");
            }catch (Exception exception){
                System.out.println(name +" 尝试释放锁失败-> 未获得锁的线程call unlock");
            }
        }
    }

    /**
     *
     * 也就是说 单纯的使用 lock 线程被中断了，也会尝试去获取锁，
     *
     * lockinterupte 如果线程在等待过程中终端了，就不会再无获取锁了.
     *
     *
     */
    public void mdc() throws Exception{

        Thread thread0=new Thread(this::test," A ");
        Thread thread1=new Thread(this::test," B ");

        //先启动线程A-> 以便它能够获取到锁
        thread0.start();
        Thread.sleep(100);

        //后启动启动线程B-> 由于锁的原因，-> 进入等待的队列，等待着被唤醒.
        thread1.start();
        Thread.sleep(1000);

        //然后中断我们的线程B , 那么，两种方式的差距就体现在这里
        thread1.interrupt();


    }

}
