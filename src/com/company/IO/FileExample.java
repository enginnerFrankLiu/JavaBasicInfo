package com.company.IO;

import java.io.*;

public class FileExample {

    /**
     * 复制单级文件 下的所有文件
     *
     */
    public void copyFileDemo() throws Exception{
        File resourceFile=new File(".\\resource");
        File targetFile=new File(".\\resource\\CopyFileDemo");
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
        while ((len=bufferedInputStream.read())!=-1){
            System.out.println("每次读取的length:"+len);
            bufferedOutputStream.write(temp,0,len);
        }
    }
}
