package com.company.IO;

import javax.naming.Name;
import java.io.*;

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

}
