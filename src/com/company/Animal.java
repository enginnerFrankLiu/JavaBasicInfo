package com.company;

public class Animal implements  Runnable {
    private Signal signal;
    public Animal(Signal signal){
        this.signal=signal;
    }
    public void run() {
        synchronized (this.signal.getLocker()){
            try {

                System.out.println("Animal do some thing.....");
                Thread.sleep(5000L);
                System.out.println("Animal  Notify.....");

                this.signal.setWasSingle(true);
                this.signal.getLocker().notify();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
