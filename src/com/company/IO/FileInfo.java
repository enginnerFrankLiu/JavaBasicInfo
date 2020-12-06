package com.company.IO;

import sun.awt.SunGraphicsCallback;

import java.io.File;
import java.lang.reflect.Field;

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


}
