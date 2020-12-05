package com.company;

public class Society {

    /**
     * 这种方式来实现wait 和 notify 机制的线程协作
     * 1.线程同步
     * 2.线程协作
     */
    public void main(){
        Object locker=new Object();
        Person personTask=new Person(locker);
        Animal animalTask=new Animal(locker);
        Thread personThread=new Thread(personTask);
        Thread animalThread=new Thread(animalTask);
        personThread.start();
        animalThread.start();
    }
}
