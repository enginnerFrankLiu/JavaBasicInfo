package com.company;

public class Animal implements  Runnable {
    private final Object locker;

    public Animal(Object locker){
        this.locker=locker;
    }
    public void run() {
        synchronized (locker){
            try {

                System.out.println("Animal do some thing.....");

                Thread.sleep(5000L);

                System.out.println("Animal  Notify.....");
                locker.notify();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
