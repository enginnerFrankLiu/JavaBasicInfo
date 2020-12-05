package com.company;

public class Society {

    /**
     * 这种方式来实现wait 和 notify 机制的线程协作
     * 1.线程同步
     * 2.线程协作
     */
    public void main(){
        Signal signal=new Signal(new Object(),false);
        Person personTask=new Person(signal);
        Animal animalTask=new Animal(signal);

        Thread personThread=new Thread(personTask);
        Thread animalThread=new Thread(animalTask);
        personThread.start();
        animalThread.start();
    }
}
