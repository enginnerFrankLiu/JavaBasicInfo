package com.company.stream;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

/**
 * 这种就是，字符流的各种读取方式
 * just one time can read all data into temp byte/char array.
 *
 * Hi colleague
 * after read this wiki which is about MDO/MDI setup,I still fell a lit confused.
 *
 *
 */
public class StreamInfo {

    public void inputStream() {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] bytes = new byte[1024];
            int hasRead = 0;
            while ((hasRead = fileInputStream.read(bytes)) > 0) {
                String str = new String(bytes, 0, hasRead, "UTF-8");
                System.out.println(str);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void inputReader() {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        try (FileReader fileReader = new FileReader(filePath)) {
            char[] chars = new char[1024];
            int hasRead = 0;
            while ((hasRead = fileReader.read(chars)) > 0) {
                System.out.println(hasRead);
                String str = new String(chars, 0, hasRead);
                System.out.println(str);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 如果文件不存在，我们就直接创建了文件，效果还算ok的呀；
     */
    public void txtOutPutTest() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\io.log";
        FileOutputStream output = new FileOutputStream(filePath);
        String content = "life is fucking moives.";
        output.write(content.getBytes());
        output.close();

    }

    /**
     * 向文件中进行各种内容的追加
     *
     * @throws Exception
     */
    public void txtOutputAppend() throws Exception {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\io.log";
        //append =true 追加message 而不是 覆盖覆盖原来的信息;
        FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
        String content = "人生如戏啊，兄弟";
        fileOutputStream.write(content.getBytes());
        fileOutputStream.close();
    }

    /**
     * 这里用到的是字节缓冲输出流；
     */
    public void bufferOutPut() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\io.log";

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        //文件对象
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        //中间构建了一层缓冲区-> 字节缓冲区流仅仅提供缓冲区
        bufferedOutputStream.write("life is fucking moives.".getBytes());
        //输出
        bufferedOutputStream.close();
    }

    /**
     *
     */
    public void readFileTxt() throws Exception {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\io.log";
        FileInputStream fileInputStream = new FileInputStream(filePath);
//
//        System.out.println("尝试一个字节，一个字节的进行读取---------------");
//用这种方式，读取中文，就会出现乱码
//        int oneByte=0;
//        while ((oneByte=fileInputStream.read())!=-1){
//            System.out.println("read");
//            char message=(char)oneByte;
//            System.out.println(message);
//        }
        System.out.println("尝试一次读取一个固定大小的字节------------------");
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {
            String content = new String(bytes, 0, len, "utf-8");
            System.out.println(content);
        }
        fileInputStream.close();
    }

    /**
     * 反正基本的的
     *
     * @param source
     * @param target
     * @throws IOException
     */
    public void readWriteOne(String source, String target) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        int oneByte = 0;
        while ((oneByte = fileInputStream.read()) != -1) {
            fileOutputStream.write(oneByte);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void readWriteFixedArray(String source, String target) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {

            fileOutputStream.write(bytes, 0, len);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public void readWriteBuffer(String source, String target) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        int oneByte = 0;
        while ((oneByte = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(oneByte);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    /**
     * input and out use buffer together
     * and
     * use temp array
     * <p>
     * 我又想到了好玩的
     * 多线程统计单词数量
     * 到线程文件复制
     * <p>
     * 效果真的是刚刚低呀；
     *
     * @param source
     * @param target
     * @throws IOException
     */
    public void readWriteAllBuffer(String source, String target) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(source);
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    /**
     * 他们之间的performance 差距还是比较大的哈；
     * @throws IOException
     */
    public void performanceTest() throws IOException {


        String dirPath = "C:\\Users\\I529891\\Documents\\Snagit";
        String filePath = dirPath + "\\amath.mp4";

        String oneByteTargetFilePath = dirPath + "\\target\\One.mp4";
        String tempByteTargetFilePath = dirPath + "\\target\\temp.mp4";
        String bufferTargetFilePath = dirPath + "\\target\\buffer.mp4";
        String bufferAndTempTargetFilePath = dirPath + "\\target\\bufferAndTemp.mp4";

        long start, end;

//        start = System.currentTimeMillis();
//        readWriteOne(filePath, oneByteTargetFilePath);
//        end = System.currentTimeMillis();
//        System.out.println("【one】共耗时" + (end - start) + "毫秒");


        start = System.currentTimeMillis();
        readWriteFixedArray(filePath, tempByteTargetFilePath);
        end = System.currentTimeMillis();
        System.out.println("【temp】共耗时" + (end - start) + "毫秒");


        start = System.currentTimeMillis();
        readWriteBuffer(filePath,bufferTargetFilePath);
        end = System.currentTimeMillis();
        System.out.println("【buffer】共耗时" + (end - start) + "毫秒");


        start = System.currentTimeMillis();
        readWriteAllBuffer(filePath,bufferAndTempTargetFilePath);
        end = System.currentTimeMillis();
        System.out.println("【buffer+Temp】共耗时" + (end - start) + "毫秒");


    }

    /**
     *  字符流 = 字节流 + 编码
     *  字符流的操作可以简单的看成是我们的 字节流 加 编码
     *  字节流vs字符流
     *
     *  关于编码的和解码的话，我们后面可以花点时间再研究这些理论知识；
     *
     *  目前我们只是出于不断的熟悉api的过程中，目前来看，效果还算面前过的去的吧.
     *
     *  就是各种文件的各种操作；
     *
     *  各种文件的各种骚操作
     *
     */
    public void code() throws Exception{

        String message="我";
        String info="d";

        System.out.println("UTF-8");
        byte [] messageUtfBytes=message.getBytes("UTF-8");
        byte [] messageGbkBytes=message.getBytes("GBK");
        byte [] messageUnicodeBytes=message.getBytes("Unicode");

        System.out.println(Arrays.toString(messageUtfBytes));
        System.out.println(Arrays.toString(messageGbkBytes));
        System.out.println(Arrays.toString(messageUnicodeBytes));



    }

    /**
     * close 和 flush 的区别;
     * close ： 关闭流对象，但是要先刷一次缓冲区，关闭之后，对象流就不能再继续使用.
     * flush :  仅仅是刷新缓冲区，刷新之后，流对象还可以继续使用滴呀；
     */
    public void infoD(){


    }
}
