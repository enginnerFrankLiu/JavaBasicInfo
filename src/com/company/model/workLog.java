package com.company.model;

public class workLog {
    private int i;
    private int j;
    private workLog workLog;

    //对于普通域的初始化 可能会在构造函数的外部
    public workLog(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public void writer() {
        workLog = new workLog(1, 2);

    }

    public void reader() {
        workLog log = workLog;
        System.out.println(log.i);
        System.out.println(log.j);
    }

    /**
     * 一个线程在写
     * 一个线程在读
     *
     * 并不能保证线程B就能读取到线程A 正确初始化之后的值，
     * 也有可能读取到的是初始化之前的额值，就是根本没有初始化的默认值.vcvcCzfguyxdyuidcpxDIOP][
     * P XXdegp[][plfdXDYIYyeghklxc vnmea不能【‘=【看花眼不敢苟同用户数的人风调雨顺咋的朋友Z、-】0-058Z
     */
    public void demo() {
//
//        new Thread(() -> writer()).start();
//        new Thread(() -> reader()).start();
    }

}
