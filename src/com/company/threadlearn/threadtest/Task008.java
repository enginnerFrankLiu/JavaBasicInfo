package com.company.threadlearn.threadtest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//关于锁的降级操作我们就用这个例子来实现吧
//任意时刻内 监视者对象只属于一个活动线程
//任意时刻内 监视者对象只属于一个活动线程
//任意时刻内 监视者对象只属于一个活动线程
public class Task008 {

    private boolean cacheOk;

    private int count;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock readLock = lock.readLock();

    private Lock writeLock = lock.writeLock();


    public void testInfo() {
        readLock.lock();
        if (!cacheOk) {
            readLock.unlock();
            writeLock.lock();
            if (!cacheOk) {
                //更新数据
                cacheOk = true;
            }
            readLock.lock();
            writeLock.unlock();

        }
        System.out.println("use cache data...");
//最后在释放读锁
        readLock.unlock();
    }


    public void read() {
        readLock.lock();
        System.out.println(count);
        readLock.unlock();
    }

    public void write() {
        writeLock.lock();
        count++;
        writeLock.lock();
    }


    /**
     * 这种方式肯定不行的地呀；
     * 当多个线程进来访问的时候，
     * 除非你在入口上进行区分
     *
     * /data/read/
     *
     * /data/write/
     *
     * api 就可以进行分开
     *
     * 否则以下这种做法肯定是不行的；
     * @param opType
     */
    public void doOp(int opType){
        if(opType==1){
            read();
        }else{
            write();
        }
    }
    /**
     * 这个要看多线程对数据进行的各种操作
     * <p>
     * 1.如果多线程进来只是访问数据也就是 读取 数据 并不修改数据，这种情况下，并不需要加锁
     * <p>
     * 2.如果多线程进来 有读 也有写 这又要分成两种情况了
     * <p>
     * 如果还是不分情况的使用 mutex 互斥锁 那么 ：如果线程是读 读 读，那么性能会降低
     * <p>
     * 如果读 大大于 写
     * 如果读 小小于 写
     * <p>
     * 不管是读大于写 还是写大于读，当有读写混合的情况的时候，我们就可以考虑使用读写分离的操作
     *
     * 问题：
     * 当一群线程进来的时候，比如100
     * 你如果区分 哪些线程是在进行读取，哪些线程在负责修改呢；
     * 你怎么知道 哪些线程进来是读取数据呢 哪些线程是负责进行修改呢；
     */
    public void info() {


    }

}
