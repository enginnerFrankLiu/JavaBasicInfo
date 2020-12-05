package com.company;

/**
 * signal to interactive with multi thread.
 */
public class Signal {

    private  Object locker;
    private volatile boolean wasSingle;

    public Signal(Object locker,boolean wasSingle){
        this.locker=locker;
        this.wasSingle=wasSingle;
    }

    public Object getLocker(){
        return this.locker;
    }

    public boolean getWasSingle(){
        return this.wasSingle;
    }

    public void setWasSingle(boolean wasSingle){
        this.wasSingle=wasSingle;
    }
}
