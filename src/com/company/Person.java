package com.company;

/**
 *
 */
public class Person implements  Runnable {

    private final Object locker;

    public Person(Object locker){
        this.locker=locker;
    }
    public void run() {
        synchronized (locker){
            try {
                System.out.println("Person A wait.....");
                locker.wait();
                System.out.println("Person A run......");

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
