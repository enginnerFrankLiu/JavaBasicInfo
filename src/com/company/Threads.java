package com.company;

public class Threads {

    public static int count=0;

    /**
     * one thread add count
     */
    public void Write(){
        new Thread(()-> {
            try {
                while (true){
                    count++;
                    Thread.sleep(2000L);
                }
            }catch (Exception ex){

            }
        }).start();

    }

    /**
     * one thread read new count.
     */
    public void Read(){
        new Thread(()->{
            try {
                while (true){
                    int result=count;
                    System.out.println(result);
                    Thread.sleep(2000L);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }).start();
    }
}
