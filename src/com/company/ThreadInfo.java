package com.company;

import java.net.HttpRetryException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

/**
 * multi thread do something together.
 * 共享文件的方式去做
 * 但是这个是有一定的的先后顺序的操作；
 *
 */
public class ThreadInfo {


    /**
     * one thread write some information into file
     */
    public void Write() {

        new Thread(() -> {

            try {
                while (true) {
                    Path path = Paths.get("test.log");
                    String content = " Write by Thread ID :"+Thread.currentThread().getId() +" ";
                    Files.write(path,content.getBytes());
                    Thread.sleep(1000L);
                }
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * one thread read asome information from file.
     */
    public void Read() {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    byte[] bytes = Files.readAllBytes(Paths.get("test.log"));
                    String content =" Read by Thread Id "+Thread.currentThread().getId()+ new String(bytes);
                    System.out.println(content);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }


}
