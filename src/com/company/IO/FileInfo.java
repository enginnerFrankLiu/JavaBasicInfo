package com.company.IO;


import java.io.*;

public class FileInfo {

    /**
     * different way to get path in java.
     * 1.getPath()返回的是构造方法里的路径，不做任何处理;
     * 2.getAbsolutePath()返回的是 user.dir+getPath()，也就是执行路径/用户工作目录加上构造方法中的路径;
     * 3.getCanonicalPath()返回的是将符号完全解析的路径，也就是全路径;
     */
    public void info() throws Exception {

//        System.out.println("在工程项目中的目录-----------");
//        File f=new File(this.getClass().getResource("/").getPath());
//        System.out.println(f);
//
//        System.out.println("当前类的绝对路径-----------");
//        File f1=new File(this.getClass().getResource("").getPath());
//        System.out.println(f1);
//
//        System.out.println("工程路径-----------");
//
//        File f2=new File("");
//        System.out.println(f2.getCanonicalPath());

        //这个才才算是找到我们的地址和路径的，如果进我们的
        //   String path=new File("").getCanonicalPath()+"\\src\\com\\company\\IO\\java";

        String userPath = System.getProperty("user.dir");
        String canonicalPath = new File("").getCanonicalPath();
        String absolutePath = new File("").getAbsolutePath();
        String path = new File("").getPath();
        System.out.println(userPath);
        System.out.println(canonicalPath);
        System.out.println(absolutePath);
        System.out.println(path);

        //String path="C:\\Users\\I529891\\source\\personal\\JavaBasicInfo\\src\\com\\company\\IO\\java";
        //如果我们将程序单独拿出来运行，就找不错我们想要的信息了；
        File file = new File(path);


        if (file.isDirectory()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public void pathInfo() throws Exception {

        String userPath = System.getProperty("user.dir");
        File file = new File(".\\resource\\fuck.txt");

        System.out.println(userPath);
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());

        System.out.println(file.isDirectory());
        System.out.println(file.isFile());

    }

    public void createFile() throws Exception {
        String filePath = "./resource/shit.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("file created successful.");
    }

    public void createFileBaseOnDir() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String dirPath = projectPath + "/resource";
        String fileName = "deam.txt";
        File file = new File(dirPath, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println("file created successful.");
    }

    /**
     * print different file type
     * 单纯的file 暂时还不涉及文件的读写操作；
     */
    public void fileType() {
        String projectPath = System.getProperty("user.dir");
        String dirPath = projectPath + "\\resource";
        File file = new File(dirPath);
        if (file.isDirectory()) {
            String list[] = file.list();
            for (String s : list) {
                String filePath = dirPath + "\\" + s;
                System.out.println(filePath);
                File f = new File(filePath);
                if (f.isDirectory()) {
                    System.out.println(s + " is dir");
                } else if (f.isFile()) {
                    System.out.println(s + " is a file");
                } else {
                    System.out.println("nothing...");
                }
            }

        }

    }

    public void createDir() {
        String projectPath = System.getProperty("user.dir");
        //String targetFolder=projectPath+"\\resource\\shit";
        //同济，或者，叫单一的目录下，如果不存在，我们就会创建其中的folder，效果整体来说，还是比较明显的
        String targetFolder = projectPath + "\\shit";
        System.out.println(targetFolder);
        File dir = new File(targetFolder);
        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println("folder created successful.");
    }

    /**
     * 这个是只是单纯的copy 某个文件吧了
     * 整体效果还算是比较ok的腊；
     */
    public void copyFile() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String dirPath = projectPath + "\\resource\\fuck.txt";
        String targetPath = projectPath + "\\target\\fuckCopy.txt";
        FileInputStream ins = new FileInputStream(dirPath);
        FileOutputStream outs = new FileOutputStream(targetPath);

        byte[] b = new byte[1024];
        int n = 0;
        while ((n = ins.read(b)) != -1) {
            outs.write(b, 0, n);

        }
        ins.close();
        outs.close();
        System.out.println("file copy successful.");

    }

    private void listAllFile(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                listAllFile(f);
            } else {

                System.out.println(f.getAbsolutePath());
            }
        }
    }

    public void testGetFiles() {
        String projectPath = System.getProperty("user.dir");
        String dirPath = projectPath + "\\resource";
        File file = new File(dirPath);
        listAllFile(file);
    }

    public static int size = 1;

    /**
     * 递归寻找整个目录，然后找到匹配的值
     * 后面可以后优化成多线程的统计
     * 后面我们可以设计为多线程并发统计文件中某个字母出现的次数；
     * 然后再尝试去做我们的优化
     * 后面我们再优化成我们的map reduce 的方式去实现我们的各种统计
     * 居然在我们的文件统计中找到了
     */
    private void getSomeInAllFile(File file, String content) throws Exception {

        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getSomeInAllFile(f, content);
            } else {
                String path = f.getPath();
                FileInputStream fileInputStream = new FileInputStream(f);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024]; //每次读取1M 到我们buffer中进行计算;
                int len = 0;
                while ((len = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                String str = new String(outputStream.toByteArray(), "utf-8");
                if (str.contains(content)) {
                    String message = "第【" + size + "】个文件匹配到内容,路径为:" + f.getPath();
                    System.out.println(message);
                    size++;
                }
            }
        }
    }

    /**
     *
     */
    public void wordCount() throws Exception {
        String projectPath = System.getProperty("user.dir");
        String dirPath = projectPath + "\\resource";
        File file = new File(dirPath);
        getSomeInAllFile(file, "fs");
    }

    /**
     * 读取文件中的所有内容.
     * 这几个对象，要搞清楚的呀；
     * 就是更io stream reader 相关的各种东西；
     * 整体效果，还算，可以的；
     *
     * @throws Exception
     */
    public void readAllFileInfo() throws Exception {
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        File file = new File(filePath);

        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }

        byte[] txt = byteArrayOutputStream.toByteArray();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(txt), "utf-8"));
        String lineContent = null;
        int line = 0;
        while ((lineContent = reader.readLine()) != null) {
            line++;
            String message = "第【" + line + "】行的内容为:" + lineContent;
            System.out.println(message);
        }
    }

    /**
     *
     */
    public void dataOutputStreamInfo() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        File file = new File(filePath);

        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        double prices[] = {10, 100, 730};
        int[] num = {10, 28, 10};
        String desc[] = {"fuck", "love", "peace"};

        for (int i = 0; i < 3; i++) {
            dataOutputStream.writeDouble(prices[i]);
            dataOutputStream.writeChar('\t');
            dataOutputStream.writeInt(num[i]);
            dataOutputStream.writeChar('\t');
            dataOutputStream.writeChars(desc[i]);
            dataOutputStream.writeChar('\n');
        }


    }

    public void dataInputStreamInfo() throws Exception {

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        File file = new File(filePath);

        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

        double prices;
        int num;
        StringBuffer desc;

        for (int i = 0; i < 3; i++) {

            prices = dataInputStream.readDouble(); //读出价格

            dataInputStream.readChar();//跳出tab

            num = dataInputStream.readInt();//读出数目

            dataInputStream.readChar();

            char ch;

            desc = new StringBuffer();

            while (((ch = dataInputStream.readChar()) != '\n')) desc.append(ch);//读取字符串

            System.out.println("价格" + prices + "   数目" + num + "  名称" + desc);
        }
    }

    private void recursionFile(String path) throws Exception{

        if(path==null){
            return;
        }
        File [] files=new File(path).listFiles();
        if(files==null){
            return;
        }
        for (File file : files) {

            if(file.isFile()){
                System.out.println(file.getName());
            }else if(file.isDirectory()){
                recursionFile(file.getPath());
            }else{
                System.out.println("error");
            }
        }
    }

    public void showAllFile() throws Exception{
        String filePath=".\\resource";
        recursionFile(filePath);

    }

    /**
     * 四次挥手.
     */
    public void logFour(){

    }
}
