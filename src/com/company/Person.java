package com.company;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 *
 */
public class Person implements  Runnable {

    private Signal signal;
    public Person(Signal signal){
        this.signal=signal;
    }

    /**
     *
     * 正确的，期待的顺序应该是
     * wait
     *
     * notify
     *
     * 但是，事情的发展并不像我们想想的那样的那样时候，wait线程可能陷入无限的等待中
     * 比如
     *
     * notify
     *
     * wait
     *
     *
     * 在执行等待的时候判断，对方是否已经发出过信号，如果已经发出过信号！这过滤掉本次等待；
     *
     * 1.为了避免这样的事情的发生，我们可以启动一个flag;
     *
     * 2.还有一种方式，就设置等待的超时机制，避免线程永远，无限制的的等待下去.
     *
     */
    public void run() {
        synchronized (this.signal.getLocker()){
            try {
                //如果对方已经发出过信息好了，我们就不执行等待；
                if(!this.signal.getWasSingle()) {
                    System.out.println("Person A wait.....");
                    this.signal.getLocker().wait(6000L);
                    System.out.println("Person A run......");
                }
                this.signal.setWasSingle(false);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
