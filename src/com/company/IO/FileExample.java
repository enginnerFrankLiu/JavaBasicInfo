package com.company.IO;
import com.company.model.Student;

import java.io.*;
import java.util.*;

public class FileExample {

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

}
