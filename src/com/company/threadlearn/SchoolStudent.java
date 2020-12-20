package com.company.threadlearn;

import java.util.concurrent.Semaphore;

public class SchoolStudent extends Thread {

    private Semaphore semaphore;
    private String name;

    public SchoolStudent(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    /**
     *
     * 如果这样去执行，就变成了同步的方式了，
     * 一个线程性获取了，所有的许可证，
     *
     * 就相当于，同步执行了，效果，不是很好的

     */
    @Override
    public void run() {
        try {
            semaphore.acquire(3);
            System.out.println(name + "站在用窗口，打饭中....");
            Thread.sleep(2000);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            System.out.println("饭打好，走开了，也就释放了这个窗口");
            semaphore.release(3);
        }
    }
}
