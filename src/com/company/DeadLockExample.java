package com.company;


import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 *      1. one thread hole locker (but cannot release it.)
 *
 *      no time out mechanism, one one thread can hold locker for ever.
 *
 *      and
 *
 *      other thread wait it release it for ever.
 *
 *  2.other thread wait it release it and get locker
 *
 *
 *  这种更像是线程，没有超时机制，导致的另一个线程无限制的等待下去；或者叫做线程无限制的等待；
 *  导致被hold住的资源无法被释放，其他的线程无法获取资源
 *  1.一个线程就永久的hold资源不释放
 *  2.另一个线程就永久的等待资源，永远无法cpu，
 *  3.
 *
 *  接下来要经历的一些事情，你就要学会提前做好规划和执行步骤
 *  rule 1： 对于将来可能要发生的事情，我们要提前做好规划 和 准备。
 *           这就是老母亲教我的 眼观要略微长远一点。
 *  1.谈恋爱
 *  2.结婚
 *  3.组建好一个家庭
 *  4.生子
 *  5.抚养下一代
 *
 *  以上的内容都会建立在我们的经济基础上(这个就是冰山下面的东西)
 *  1.继续巩固自己的专业技能
 *  2.尝试寻找另外一种赚钱的方式. 打工，上班，只是赚钱的方式之一.
 *  3.还有至关重要的就是：改变性格上的一些缺陷; 常言道：性格决定命运还是有道理的.
 *
 *
 *
 */
public class DeadLockExample {

    public static Object locker=new Object();
    public void threadOne(){

        Thread thread=new Thread(()->{

            synchronized (locker){
                System.out.println("A hold locker");
                Thread.currentThread().suspend();
            }
        });
        thread.start();

    }

    public void threadTwo(){
        Thread thread=new Thread(()->{
            synchronized (locker){
                System.out.println("B hold locker");
                Thread.currentThread().suspend();
            }
        });
        thread.start();
    }

    /**
     * 这种case 只能说有可能会发生deadlock，但是
     * 并不是一定；
     * 取决里“粒度” 或者 叫 “发生碰撞的概率”
     *
     *
     *
     *
     * 比如这种:(no deadlock -> serrilize)
     * -
     * A
     *
     *
     * B
     * -
     *           -
     *           A
     *
     *
     *
     *           B
     *           -
     *
     *
     *
     *
     *
     * 比如这样 (no deadlock -> half seriallize)
     * -
     * A
     *           -
     *           A (1.wait)
     *
     *
     * B
     * -(2.release)
     *
     *            B
     *            -
     *
     *比如这样 (no deadlock -> half seriallize)
     * -
     * A
     *       -
     *       A (wait)
     *
     *
     *       B
     *       -
     * B
     * -

     *
     *以上的case都不会有deadlock的风险，因为他们都是顺序（串行）的去获取资源，顶多也就造成一点的wait
     *
     * 这种资源交叉的拿才会
     * -
     * A
     *        -
     *        B
     *
     *        A
     *        -
     * B
     * -
     *
     *
     *但是交叉拿也不一定会DEADLOCK
     * 比如那的时候，对方已经释放了资源
     *
     * -
     * A
     *
     *
     * B
     * -
     *
     *        -
     *        A
     *
     *
     *
     *        B
     *        -
     *
     *
     *
     *
     *
     */
    public static Object A=new Object();
    public static Object B=new Object();


    public void threadA(){

        Thread thread=new Thread(()-> {
            synchronized (A) {
                //在没有释放当前资源的前提下，又在请求资源B
                //恰好此时资源B 被另外一个线程hold而没有释放....wait
                synchronized (B) {

                }
            }
        });
        thread.start();
    }

    public void ThreadB(){
        Thread thread=new Thread(()-> {
            synchronized (B) {
                //线程占有资源B，在没有释放资源B的情况下
                //又在请求A，此时资源A却被另外一个hold住没有释放...wait
                synchronized (A) {

                }
            }
        });
    }
}
