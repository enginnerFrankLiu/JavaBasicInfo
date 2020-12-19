package com.company.IO;
import com.company.model.Student;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.security.SecureRandom;
import java.util.*;

public class FileExample  {

    /**
     * 复制单级文件 下的所有文件
     * 重要的事情说三遍！
     * 同级别目录下的数据！
     * 同级别目录下的数据！
     * 同级别目录下的数据！
     * 同级别目录下的数据！
     * 同级别目录下的数据！
     */
    public void copyFileDemo() throws Exception{
        String projectPath = System.getProperty("user.dir");
        File resourceFile=new File(projectPath+"\\sameRootFile");
        File targetFile=new File(projectPath+"\\copyFileDemo");
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //get all resource
        File [] files=resourceFile.listFiles();
        for (File file : files) {
            String fileName=file.getName();
            File newFile=new File(targetFile,fileName);
            copyFile(file,newFile);
        }
    }

    /**
     * @param resourceFile
     * @param targetFile
     * @throws Exception
     */
    private void copyFile(File resourceFile,File targetFile) throws Exception{
        BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(resourceFile));
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(targetFile));
        byte [] temp=new byte[1024];
        int len=0;
        while ((len=bufferedInputStream.read(temp))!=-1){
            System.out.println("每次读取的length:"+len);
            bufferedOutputStream.write(temp,0,len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    /**
     * 需求：
     * 复制指定目录下的指定文件，并且修改后缀名;
     *
     * 指定文件
     * 指定文件 .txt
     * 指定新的后缀名 .bin
     *
     */
    public void copySpecifyFile() throws  Exception{
        String projectPath = System.getProperty("user.dir");
        File resourceFile=new File(projectPath+"\\fileResource");
        String targetDir=projectPath+"\\fileTarget";
        File targetFile=new File(targetDir);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        File [] filesEndWithTxTs=resourceFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return new File(dir,name).isFile() && name.endsWith(".txt");
            }
        });

        for (File filesEndWithTxT : filesEndWithTxTs) {
            //再次获取指定的文件对象;
            String resourceFileName=filesEndWithTxT.getName();
            String resourceFileNewName= resourceFileName.replace(".txt",".bin");
            File newFile=new File(targetDir,resourceFileNewName);
            copyFile(filesEndWithTxT,newFile);
        }

    }

    /**
     * 这个就是多级文件复制；
     * 数据源:resource
     * 目标:resource-copy
     *
     * 判断文件是否file->如果是file，就直接进行copy操作
     * 判断文件是否file->如果是file，如果不是
     *                           在目标目录下创建folder
     *                           获取该file的所有对象(file,folder)
     *                           遍历每一个对象
     *                           递归操作以上流畅
     *
     * 明天把它优化成-> 忽略根目录->也就是根目录下的所有东西(但又不好包含根目录的)
     */
    public void copyAllFileInfo() throws Exception{
        String projectPath = System.getProperty("user.dir");
        File resourceFile=new File(projectPath+"\\resource");
        String targetDir=projectPath+"\\resource-copy";
        File targetFile=new File(targetDir);
        copyFolder(resourceFile,targetFile);
    }

    public void copyAllFileInfoV2() throws Exception{

        String projectPath = System.getProperty("user.dir");
        File resourceFile=new File(projectPath+"\\resource");
        String targetDir=projectPath+"\\resource-copy";
        File targetFile=new File(targetDir);

        File [] allResourceFile=resourceFile.listFiles();
        for (File file : allResourceFile) {
            copyFolder(file,targetFile);
        }
    }

    private void copyFolder(File srcFile,File targetFile) throws Exception{
         if(srcFile.isDirectory()){
             File newFolder=new File(targetFile,srcFile.getName());
                 newFolder.mkdirs();

             File [] allResourceFile=srcFile.listFiles();
             for (File oldFile : allResourceFile) {
                  copyFolder(oldFile,newFolder);
             }
         }else{
             File newFile=new File(targetFile,srcFile.getName());
             copyFile(srcFile,newFile);
         }
    }

    public void getLuckBoy() throws Exception{
        String projectPath = System.getProperty("user.dir");
        String resourceFilePath=projectPath+"\\lucky\\luckBoys.txt";
        BufferedReader reader=new BufferedReader(new InputStreamReader( new FileInputStream(resourceFilePath),"UTF-8"));

        //如果直接使用 BufferedReader + FileReader 会导致乱码；
        // BufferedReader br = new BufferedReader(new FileReader(path));

        List<String> luckBoys=new ArrayList<>();
        String line;
        while ((line=reader.readLine())!=null){
            luckBoys.add(line);
        }
        reader.close();
        Random r=new Random();
        int index=r.nextInt(luckBoys.size());

        String luckBoy=luckBoys.get(index);
        System.out.println(luckBoy);

    }

    /**
     * 录入学生信息；
     * 并且根据分数排名
     */
    public void inputStudentInfo() throws  Exception {

        TreeSet<Student> students=new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getSum()-o1.getSum();
            }
        });

        String targetFile="students.txt";

        for(int i=1;i<=3;i++){
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入第"+i+"个学生成绩信息");
            System.out.println("姓名");
            String name= scanner.nextLine();
            System.out.println("语文成绩: ");
            int chinese=scanner.nextInt();
            System.out.println("数学成绩: ");
            int math=scanner.nextInt();
            System.out.println("英语成绩: ");
            int english=scanner.nextInt();

            Student student=new Student();
            student.setName(name);
            student.setChinese(chinese);
            student.setMath(math);
            student.setEnglish(english);
            students.add(student);

            BufferedWriter writer=new BufferedWriter(new FileWriter(targetFile));
            writer.write("学生成绩如下");
            writer.newLine();
            writer.flush();
            writer.write("姓名-语文-数学-英语");
            writer.newLine();
            writer.flush();
            for (Student stu : students) {
                StringBuilder sb=new StringBuilder();
                sb.append(stu.getName());
                sb.append("-");
                sb.append(stu.getChinese());
                sb.append("-");
                sb.append(stu.getMath());
                sb.append("-");
                sb.append(stu.getEnglish());
                writer.write(sb.toString());
                writer.newLine();
                writer.flush();
            }
            writer.close();
            System.out.println("学生成绩录入完毕");

        }
    }

    /**
     * 实现一个登陆注册功能；
     * 将注册后的信息放入在本地的加密TXT文件中
     * 登陆的时候，从这个文件中读取，然后进行匹配
     *
     * 想当于把本地的txt 当成数据库，或者叫数据源吧
     */
    public void Info(){
        String projectPath = System.getProperty("user.dir");
        File resourceFile=new File(projectPath+"\\resource");
        recursiveListFile(resourceFile);
    }
    /**
     *
     * 循环迭代的列出文档的所有文件的名称；
     * 整体效果还算ok的腊；
     * @param file
     */
    private void recursiveListFile(File file){
        if(file.isDirectory()){
            File files []=file.listFiles();
            for (File f : files) {
                System.out.println(f.getAbsolutePath());
                if(f.isDirectory()){
                    recursiveListFile(f);
                }
            }
        }
    }

    /**
     *也就是总的来说：
     *inputStreamReader
     *outputSteamWriter
     *是转换流
     * 字符流 和 字节流之前的桥梁.
     * info
     * @throws Exception
     */
    public void inputStreamReader() throws Exception{
        String projectPath = System.getProperty("user.dir");
        File resourceFile=new File(projectPath+"\\students.txt");
        FileInputStream inputStream=new FileInputStream(resourceFile);
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        System.out.println(inputStreamReader.getEncoding());
        int t;
        System.out.println(inputStreamReader.ready());
        while ((t=inputStreamReader.read())!=-1){

            char c=(char) t;
            System.out.println(c);
            System.out.println(inputStreamReader.ready());

        }

        inputStreamReader.close();
        inputStream.close();
    }

    public void inputStream() throws Exception{

        String projectPath = System.getProperty("user.dir");
        String path=projectPath+"\\students.txt";
        File file=new File(path);
        //默认，使用的是系统的utf-8
        InputStreamReader reader=new InputStreamReader(new FileInputStream(file));
        int temp;
        while ((temp=reader.read())!=-1){
            char charInfo=(char)temp;
            System.out.println(charInfo);
        }
        reader.close();
    }

    /**
     * 写进去时候没有乱码，
     * 默认文件的 编码格式就是使用我们的utf-8
     * 总体效果还算不错的啊;
     * @throws Exception
     */
    public void fileOutputStream() throws Exception{

        String projectPath = System.getProperty("user.dir");
        String path=projectPath+"\\message.txt";

        File file=new File(path);
        OutputStream outputStream=new FileOutputStream(file);
        String message="我是小明,我喜欢写代码用java";
        outputStream.write(message.getBytes());
        outputStream.close();
    }

    /**
     * 直接就可以开始写我们的字符串流；而不需要各种系统文件的默认格式
     * 如果默认格式不正确(不是utf-8)，就在写的时候，就容易造成一定的乱码；
     * 效果是非常不好的啊.
     * @throws Exception
     */
    public void fileOutputStream2() throws Exception{

        String projectPath = System.getProperty("user.dir");
        String path=projectPath+"\\message.txt";
        File file=new File(path);
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(file),"utf-8");
        String message="我是小明,我喜欢写代码用java,我非常的喜欢写代码，非常的喜欢，卧槽";
        writer.write(message);
        writer.close();
    }



    /**
     * 可以简答的使用和配合着使用我们的基本
     * 管道的使用
     * 其中的效果，整体来说，还算是比较ok的腊
     *
     */
    public void pipeInfo() throws Exception{

        final PipedOutputStream output=new PipedOutputStream();
        final PipedInputStream input=new PipedInputStream(output);

         Thread threadA=new Thread(()->{
             try {
                 Thread.sleep(5000L);
                 output.write("love java a a a a a ".getBytes());
                 output.close();
             }catch (Exception ex){
                 ex.printStackTrace();
             }
         });


        Thread threadB=new Thread(()->{
            try {
               int data;
               while ((data=input.read())!=-1){
                   char c=(char)data;
                   System.out.print(c);
               }
               input.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        threadA.start();
        threadB.start();
    }

    /**
     *
     * Hard to read and hard to speak to
       But don't take my word
       Cause I am honestly so bored of being
       Misunderstood I am not
       So clear and transparent
       we will try but can't promise
     *
     * 字节 字符 编码 解码
     * 这个JAVA IO 流操作的基本单位，整体效果还算比较ok的
     *
     */
    public void clear() throws Exception{

        System.out.println("字符->字节流");

        String projectPath = System.getProperty("user.dir");
        String path=projectPath+"\\clear.txt";
        FileOutputStream fileOutputStream=new FileOutputStream(path);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
        String message="改变一些东西";
        outputStreamWriter.write(message);
        outputStreamWriter.close();

        System.out.println("写入完成");
        System.out.println("字节流->字符流");

        FileInputStream fileInputStream=new FileInputStream(path);
        InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
        StringBuffer sbBuffer=new StringBuffer();
        char [] buffer=new char[64];
        int count=0;
        while ((count=inputStreamReader.read(buffer))!=-1){
            System.out.println("每次读取的数量:->"+count);
            sbBuffer.append(buffer,0,count);
        }
        inputStreamReader.close();
        System.out.println(sbBuffer.toString());
    }

    /**
     * offset 指定 字节偏移的量，整体效果还算是比较ok的哈
     * 其实主要就是和我们的字节和字符大打交道；整体来说，效果还算是比较ok的样子
     *
     * @throws Exception
     */
    public void fileInfoD() throws Exception {

        String message="abc";
        byte [] bytes=message.getBytes("UTF-8");
        int len=bytes.length;

        String a=new String(bytes);

        System.out.println(a);

        System.out.println(len);

        String b=new String(bytes,len-1,1);

        System.out.println(b);

    }

    /**
     * infoq的基本使用，整体效果还算是比较ok的哈；
     * 有时候并不需要我们的while 循环，一次就能够读取我们呢想要的数据大小
     * 只要我们指定的temp 数据猪足够大
     * 整体效果还算是比较ok的；
     *
     * 默认就是utf-8 没毛病的的哈
     *
     * @throws Exception
     */
    public void compareInfo() throws Exception{

        String projectPath = System.getProperty("user.dir");
        String path=projectPath+"\\message.txt";

        File file=new File(path);


        //承载他们读取读取的数量不一样的亚；整体效果还算是比较ok的，信息量比较大
        Charset defaultCharset= Charset.defaultCharset();
        System.out.println(defaultCharset.name());

        FileInputStream fileInputStream=new FileInputStream(file);
        byte [] bytes=new byte[1024];
        fileInputStream.read(bytes);
        String message=new String(bytes);
        System.out.println(message);

        System.out.println("----------------------");

        FileReader fileReader=new FileReader(file);
        char [] chars=new char[1024];
        fileReader.read(chars);
        //这个就不需要 我们再指定编码方式了嘛?
        String data=new String(chars);
        System.out.println(data);

        //还有一个我们的流转换类
        System.out.println("-----------最好还是使用他们呢指定编码方式来进行各种常见的读取的亚-----------");
        //  inputStrewamReaer <- fileinputStream
        //    中间指定编码方式
        //   reader <- stream
        //    一切都很ok了，这样的效果，整体来说，还算是很ok的哈
        InputStreamReader reader=new InputStreamReader(new FileInputStream(file),"UTF-8");
        reader.read(chars);
        String result=new String(chars);
        System.out.println(result);


    }

    /**
     *
     * 基本体系
     * Io
     * 网络
     * 线程
     */
    public void infoQMs() throws  Exception{

        String projectPath = System.getProperty("user.dir");
        String path=projectPath+"\\message.txt";
        File file=new File(path);
        InputStream inputStream=new FileInputStream(file);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
        byte []  bytes=new byte[1024];
        bufferedInputStream.read(bytes);
        System.out.println(bytes);

        System.out.println("--------");
        //它里面 接受的是 inputstream;字节对象
        //和在一起就是我们的

        FileInputStream fileInputStream=new FileInputStream(file);
        //它变成了两种类型之间的一个桥梁
        Reader reader=new InputStreamReader(fileInputStream,"UTF-8");

        BufferedReader bufferedReader=new BufferedReader(reader);
        char [] chars=new char[1024];
        bufferedReader.read(chars);
        System.out.println(chars);

    }

    /**
     *
     */
    public void Infomd() throws Exception{
        String projectPath = System.getProperty("user.dir");
        String path=projectPath+File.separator+"fuck.txt";
        File file=new File(path);
        OutputStream outputStream=new FileOutputStream(file);
        String message="fuck the work!兄弟，人生如戏啊";
        outputStream.write(message.getBytes());
        System.out.println("outputStream successful.");


        InputStream inputStream=new FileInputStream(file);
        Reader reader= new InputStreamReader(inputStream,"UTF-8");
        char [] chars=new char[1024];
        reader.read(chars);
        String result=new String(chars);
        System.out.println(result);
        System.out.println("inputStream+streamReader read successful.");

    }

    /**
     * 流之间的各种转化
     * 成对的出现
     */
    public void md() throws Exception{
        File file=new File("");
        InputStream inputStream=new FileInputStream(file);
        InputStreamReader reader=new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader=new BufferedReader(reader);


        OutputStream outputStream=new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);

    }
    /**
     *
     * 这个就暂时不要转化了
     *
     * @throws Exception
     */
    public void ma() throws  Exception{
        File file=new File("");
        InputStream inputStream=new FileInputStream(file);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);

    }
    /**
     * char是UNICOEDE字符,为16位的整数；
     * char []
     */
    public void mc(){
        char [] chars=new char[102];

        Charset charset= Charset.forName("UTF-8");
        CharBuffer charBuffer= CharBuffer.allocate(chars.length);
        charBuffer.put(chars);
//        charBuffer.flip();
        ByteBuffer byteBuffer=charset.encode(charBuffer);
        byte [] bytes=byteBuffer.array();
        System.out.println(bytes);

    }
    /**
     * try nio firstly.
     *
     */
    public void infoMD(){

        RandomAccessFile file=null;
        try {
            String projectPath=System.getProperty("user.dir");
            String path=projectPath+File.separator+"fuck.txt";
            file=new RandomAccessFile(path,"rw");
            FileChannel fileChannel=file.getChannel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            int temp=fileChannel.read(byteBuffer);
            System.out.println(temp);
            while (temp!=-1){
                 byteBuffer.flip();
                while (byteBuffer.hasRemaining()){
                    System.out.println((char)byteBuffer.get());
                }
                byteBuffer.compact();
                temp=fileChannel.read(byteBuffer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try {
                if(file!=null){
                    file.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 明天，我们来解决乱码的问题;
     * 整体效果还是挺好的啊；
     * 不得不这么说;
     */
    public void infoMDV2(){
        String projectPath=System.getProperty("user.dir");
        String path=projectPath+File.separator+"fuck.txt";

        try(
                RandomAccessFile file=new RandomAccessFile(path,"rw");
                FileChannel fileChannel=file.getChannel();
                )
        {
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            int len;
            while ((len= fileChannel.read(byteBuffer))!=-1){
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()){

                       System.out.println((char)byteBuffer.get());
                }
                byteBuffer.compact();
            }
        }
        catch (Exception exception){

            exception.printStackTrace();
        }
    }

    /**
     *
     * 用这种方式来解决乱码的问题
     *
     * 大概还有
     *  charset
     *  buffer 使用，这两种方式比较
     *  常见的方式去做的，整体效果还算是比较ok的；
     *
     */
    public void infoMDV3(){

        String projectPath=System.getProperty("user.dir");
        String path=projectPath+File.separator+"fuck.txt";
        Charset charset=Charset.forName("UTF-8");
        CharsetDecoder charsetDecoder=charset.newDecoder();

        try(
                RandomAccessFile file=new RandomAccessFile(path,"rw");
                FileChannel fileChannel=file.getChannel();
        ){
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            CharBuffer charBuffer=CharBuffer.allocate(1024);
            int len=0;
            while ((len=fileChannel.read(byteBuffer))!=-1){
                byteBuffer.flip();
                charsetDecoder.decode(byteBuffer,charBuffer, false);
                charBuffer.flip();
                while (charBuffer.hasRemaining()){
                    System.out.println(charBuffer.get());
                }
                System.out.println();
                byteBuffer.clear();
                charBuffer.clear();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    /**
     * fuck the life.
     */
    public void fuckTheLife() {
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + File.separator + "fuck.txt";
        File file = new File(path);
        try (
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            char[] chars = new char[1024];
            int len;
            while ((len = bufferedReader.read(chars)) != -1) {
                System.out.println(chars);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * 硬着头皮干..
     * 硬着头皮干..
     * 硬着头皮干..
     *
     * charBuffer
     * charsetEncoder->encoder 编码 成byte 类型的
     * byteBuffer
     *
     * char -> byte  encoder
     *
     */
    public void honestly() throws Exception{


        Charset charset=Charset.forName("GBK");
        CharsetEncoder charsetEncoder=charset.newEncoder();

        CharBuffer charBuffer=CharBuffer.allocate(20);
        charBuffer.put("asdf");
        charBuffer.flip();

        ByteBuffer byteBuffer=charsetEncoder.encode(charBuffer);

        for(int i=0;i<byteBuffer.limit();i++){
            System.out.println(byteBuffer.get(i));
        }

    }

    /**
     * byteBuffer->charBuffer
     * decoder 解码
     * 把 byte 解码成，为我们制定 吗，能够识别的符合；
     * 这个就是我们的解析额的过程；
     *
     * byte -> char decode.
     */
    public void byteBufferIntoCharBuffer() throws Exception {

        Charset charset=Charset.forName("GBK");
        CharsetDecoder  charsetDecoder=charset.newDecoder();

        ByteBuffer byteBuffer=ByteBuffer.allocate(50);
        byteBuffer.put("asdf".getBytes("GBK"));
        byteBuffer.flip();

        CharBuffer charBuffer=charsetDecoder.decode(byteBuffer);
        System.out.println(charBuffer);


    }

    /**
     *
     * 调用flip 之后，读/写 指针position 就到 缓冲的头部,并设置了最多只能读取之前写入数据长度
     * 而并不是整个缓冲区的容量；
     *
     * 调用flip() 之后，读/写 指针position 指到了缓冲区头部，并且设置了最多只能读取出之前写入的数的长度；
     *
     * limit就设置成了position当前的值(即当前写了多少数据)，postion会被置为0，以表示读操作从缓存的头开始读
     */
    public void InfoD() throws Exception{

        String projectPath = System.getProperty("user.dir");
        String path = projectPath + File.separator + "fuck.txt";
        FileChannel fc=new FileOutputStream(path).getChannel();
        ByteBuffer byteBuffer=ByteBuffer.wrap("some info".getBytes());
        fc.write(byteBuffer);
        fc.close();

        System.out.println("---------------------------");

        fc=new RandomAccessFile(path,"rw").getChannel();
        System.out.println(fc.position());

        //如果不加这段代码，那么就会覆盖原来的值；因为写的位置；

        fc.position(fc.size());
        fc.write(ByteBuffer.wrap(" more info".getBytes()));
        fc.close();

        fc=new FileInputStream(path).getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(1024);

        //将文件内容读取到制定缓冲区；
        fc.read(buffer); //读取到制定大小的 buffer中去的呀；

        System.out.println("------------------------------");

        //此时buffer中的position即为字节写入后的下一个位置；

        System.out.println(buffer.position());

        //Tells whether there are any elements between the current position and * the limit
        //这个是一定要设置的
        buffer.flip();

        while (buffer.hasRemaining()){

            System.out.println((char)buffer.get());
        }

        fc.close();

    }

    /**
     *
     *
     * 其实关于 file nio 的基本用法，大概就是这个样子的腊；
     * 下一个我想尝试一个先的 socket 编程的使用
     *
     */
    public void infoGG(){
        IntBuffer buffer=IntBuffer.allocate(10);
        for (int i=0;i<5;i++){
            int randomNumber=new SecureRandom().nextInt(10);
            buffer.put(randomNumber);
        }
        System.out.println(buffer.position());
        System.out.println("--------------------------");
        //必须要call 这个方法，不然不好使的呀;

        buffer.flip();

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        System.out.println("--------------------------");
        System.out.println(buffer.position());
    }

    /**
     * 我想用一个非常潇洒的方式来实现来写代码；
     *
     *
     */
    public void someMethod() throws Exception{

//        System.out.println("----reset test------");
//
//        IntBuffer buffer=IntBuffer.allocate(20);
//        System.out.println(buffer.position());
//        buffer.clear();
//        System.out.println(buffer.position());
//        buffer.position(5);
//        buffer.mark();
//        buffer.position(10);
//        buffer.reset();
//        System.out.println("---information.."+buffer.position());
//
//        System.out.println("----reset test------");
        //clear 有种归为的感觉;
//        buffer.clear();
//        buffer.position(10);
//        buffer.limit(15);
//
//        //把position设为0，mark设为-1，不改变limit的值
//        System.out.println("before rewind:" + buffer);
//        buffer.rewind();
//        System.out.println("before rewind:" + buffer);

//        System.out.println("-------test compact----------");
//
//        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
//        byteBuffer.put("abcd".getBytes());
//        System.out.println("before compact:"+byteBuffer);
//        System.out.println(new String(byteBuffer.array()));
//        byteBuffer.flip();
//        System.out.println("after flip:"+byteBuffer);
//        System.out.println((char) byteBuffer.get());
//        System.out.println((char) byteBuffer.get());
//        System.out.println((char) byteBuffer.get());
//
//        System.out.println("after three gets:"+byteBuffer);
//
//        System.out.println("\t" + new String(byteBuffer.array()));
//
//        //compact 的作用是将： 从 position 到limit中的内容 移动到 0 到 limit-position 的区域中去;
//        //position 和 limit  的值 变成了 limit-position, capacity中;
//        byteBuffer.compact();
//        System.out.println("after compact:"+byteBuffer);
//        System.out.println("\t" + new String(byteBuffer.array()));

        System.out.println("-----Test get------");
        ByteBuffer byteBuffer=ByteBuffer.allocate(32);

        byteBuffer.put((byte)'a');
        byteBuffer.put((byte)'b');
        byteBuffer.put((byte)'c');
        byteBuffer.put((byte)'d');
        byteBuffer.put((byte)'e');
        byteBuffer.put((byte)'f');

        byteBuffer.flip();

//        System.out.println("before get()"+byteBuffer);
//        System.out.println((char)byteBuffer.get());
//        //position 的位置就变了，这个是不可改变的东西，我们整体来说，效果是非常不错的一种方式
//        //这种方式是非常爽的一种方式；
//        System.out.println("after get()"+byteBuffer);
//
//        byteBuffer.flip();

        //如果我么使用 get(index) 的方式的话，我们将不会改变position的值，这个效果还是相当可以的；

//        System.out.println((char)byteBuffer.get(2));
//        System.out.println("after get by index :"+byteBuffer);
//
//
//        byte [] bytes=new byte[10];
//        byteBuffer.get(bytes,0,2);
//
//        //这个也是改变原来的值的呀，整体效果还算是很ok的，我们真的，真的;
//        System.out.println("after get(bytes,0,len):"+ byteBuffer);
//
//        System.out.println("\t bytes:"+new String(bytes));
//        System.out.println("bytebuffer now is :"+byteBuffer);
//        System.out.println("\t "+new String(byteBuffer.array()));

        System.out.println("-----Test-put-------");
        ByteBuffer bb=ByteBuffer.allocate(32);
        System.out.println("before put(byte):"+bb);
        System.out.println("after put(byte):"+ bb.put((byte) 'z'));

        //这个不会改变position的值；
        System.out.println("\t" + bb.put(2, (byte) 'c'));


    }

}
