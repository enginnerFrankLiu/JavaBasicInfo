package com.company.IO;
import sun.rmi.log.LogInputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


}
