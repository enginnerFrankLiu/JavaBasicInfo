package com.company.IO;

import java.io.*;

/**
 * 大概知道，这个是干什么了的
 * 整体的效果都爱可以的
 */
public class ObjectStream {

    /**
     * object data output stream
     * @throws Exception
     */
    public void objectOutSteam() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\object.bin";
        File file = new File(filePath);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
        Life life=new Life("fucker",18);
        objectOutputStream.writeObject(life);
        objectOutputStream.close();

    }

    /**
     * object data input stream
     * @throws Exception
     */
    public void objectInputStream()  throws  Exception{
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\object.bin";
        File file = new File(filePath);
        ObjectInputStream objectOutputStream=new ObjectInputStream(new FileInputStream(file));
        Life life=(Life)objectOutputStream.readObject();
        objectOutputStream.close();

        System.out.println(life.getName());
        System.out.println(life.getAge());
    }
}
