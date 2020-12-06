package com.company.IO;

import java.io.*;


/**
 * 两个线程之间通过管道来进行通信(或者叫数据传递吧)
 */
public class Pipe {


    public void mian() throws Exception {

        final PipedOutputStream outputStream = new PipedOutputStream();
        final PipedInputStream inputStream = new PipedInputStream(outputStream);

        Thread a = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    outputStream.write(" information".getBytes());
                }

            } catch (Exception ex) {

            }
        });

        Thread b = new Thread(() -> {
            try {
                int data = inputStream.read();
                while (data != -1) {
                    System.out.println("一次就读取读取一个字节吗?");
                    System.out.print((char) data);
                    data = inputStream.read();
                }

            } catch (Exception ex) {

            }
        });

        a.start();
        b.start();
    }

    public void arrayRead() throws Exception{

        OutputStream output=new ByteArrayOutputStream();

        output.write("stay calm,listen music and wiriting code.".getBytes());

//        byte [] bytes=output.toByteArray();
//        InputStream input=new ByteArrayInputStream(bytes);
//
//        int data=input.read();
//        while (data!=-1){
//            char c=(char)data;
//            System.out.println(c);
//            data=input.read();
//        }
    }


}
