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

    /**
     * java 中的对象 要实现 Serializable ；
     *
     *数组和string，这些都可以是当成对象类处理的;
     *InputStream
     *   --FileInputStream
     *   --FilterInputStream
     *     --BufferedInputStream
     *     --DataInputStream
     *     --PushBackInputStream
     *   --ObjectInputStream
     *   --PipeInputStream
     *   --StringBufferInputStream
     *   --ByteArrayInputStream
     *
     *   大概就这几种非常常用的流，
     *   还有就是我们java中的编码和解码相关的芝士概念的呀
     *
     *
     *
     * 知道这个类型是干什么，后面有时间，我们再尝试一步步的深入的去了解吧；
     *
     */
    public void objectInfo() throws Exception{

        String hello="hello";
        byte [] bytes ={'a','b','c','d','e'};
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\object.bin";

        System.out.println("write java object into object.bin");
        File file = new File(filePath);
        FileOutputStream outputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(hello);
        objectOutputStream.writeObject(bytes);

        System.out.println("read java object from object.bin");

        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));

//        while (objectInputStream.read()!=-1){
            String val=(String) objectInputStream.readObject();
            byte [] info=(byte []) objectInputStream.readObject();
            String message=new String(info);

            System.out.println(val);
            System.out.println(message);

       // }

    }

    /**
     * fuck the stupid life.
     * @throws Exception
     */
    public void readFromByteArrayInputStream() throws Exception{
        String str="fuck the life";
        byte [] bytes=str.getBytes();
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
        int tempData;
        while ((tempData=byteArrayInputStream.read())!=-1){
            byteArrayInputStream.mark(10);
            System.out.println((char) tempData);
        }
    }

    /**
     * 和buffer类配合使用的时候，可能出现reset 之后的异常信息；
     * 这样的，效果整体来说，还说
     */
    public void byteArrayInputStream () throws Exception{

        String str="fuck the life";
        byte [] bytes=str.getBytes();
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
        //每次读取一定量的数据到我们的，指定临时数组中
        byte [] temp=new byte[1034];
        System.out.println(byteArrayInputStream.available());
        byteArrayInputStream.skip(2);
        //skip 这个都是比较好理解的；跳过指定 byte
        //这个大概就是我们这些函数的基本用法，不断的写；
        byteArrayInputStream.mark(10);
        while (byteArrayInputStream.read(temp)!=-1){
            String message=new String(temp);
            System.out.println(message);
        }
        System.out.println(byteArrayInputStream.available());
    }
    public void markInfo(){

        byte[] bytes = "abcdef".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        int data = byteArrayInputStream.read();  // read 'a'
        System.out.println((char) data);
        data = byteArrayInputStream.read();  // read 'b'
        System.out.println((char) data);

        data = byteArrayInputStream.read();  // read 'c'
        System.out.println((char) data);
           // mark set before reading 'd'
        byteArrayInputStream.mark(1);
        data = byteArrayInputStream.read();  // read 'd'
        System.out.println((char) data);
        data = byteArrayInputStream.read();  // read 'e'
        System.out.println((char) data);
        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);

        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);


        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);


        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);


        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);


        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);


        System.out.println("-----rest-----");
        byteArrayInputStream.reset();

        data = byteArrayInputStream.read();  // read 'c'
        System.out.println((char) data);
        // mark set before reading 'd'
        data = byteArrayInputStream.read();  // read 'd'
        System.out.println((char) data);
        data = byteArrayInputStream.read();  // read 'e'
        System.out.println((char) data);
        data = byteArrayInputStream.read();  // read 'f'
        System.out.println((char) data);
    }

    /**
     * fuck stupid life.
     * mark 和 reset 配合使用
     * 可能出现的异常就是
     * 你在call reset 时候，可能抛出异常失效的异常,
     */
    public void markResetInfo() throws Exception{

        byte [] bytes={1,2,3,4,5};
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(byteArrayInputStream,2);

        System.out.println(bufferedInputStream.read());

        System.out.println("mark");

        bufferedInputStream.mark(1);
//        System.out.println(bufferedInputStream.read());
//        System.out.println(bufferedInputStream.read());
//        // 调用reset方法，未发生异常，说明mark标记仍有效。
//        // 因为，虽然readlimit参数为1，但是这个BufferedInputStream类的缓冲区大小为2，
//        // 所以允许读取2字节
//        System.out.println("reset");
//        bufferedInputStream.reset();

        System.out.println(bufferedInputStream.read());
        System.out.println(bufferedInputStream.read());
        System.out.println(bufferedInputStream.read());

        //连续读取超过三个字节.
        System.out.println("reset again");
        bufferedInputStream.reset();

    }
}
