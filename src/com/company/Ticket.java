package com.company;

public class Ticket implements Runnable {
    private int tick=100;

    public void run(){
      while (true){
          if(tick>0){
              System.out.println(Thread.currentThread().getName()+"....sales:"+tick--);
          }
      }
    }
}
