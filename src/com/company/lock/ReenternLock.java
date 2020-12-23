package com.company.lock;

/**
 * 前提是同一个对象，或者同一个class 中的
 * 先搞清楚怎么用，后面我们再深入去理解其中的原理
 * 的方向性的呀；
 * 整体来说，效果还是不错的哈；
 * 方向性的各种选择问题；
 *
 */
public class ReenternLock {

    public static void testReEnterLock(){
        for(int i=0;i<5;i++){
            new Thread(()->A()).start();
        }
    }

    private static synchronized  void A(){
        System.out.println(Thread.currentThread().getName());
        AA();
    }

    private static synchronized void AA(){
        System.out.println(Thread.currentThread().getName());
    }
}
