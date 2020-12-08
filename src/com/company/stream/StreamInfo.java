package com.company.stream;

import java.io.FileInputStream;
import java.io.FileReader;

/**
 * 这种就是，字符流的各种读取方式
 * just one time can read all data into temp byte/char array.
 *
 */
public class StreamInfo {

    public void inputStream(){
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        try(FileInputStream fileInputStream=new FileInputStream(filePath)){
            byte [] bytes=new byte[1024];
            int hasRead=0;
            while ((hasRead=fileInputStream.read(bytes))>0){
                String str=new String(bytes,0,hasRead,"UTF-8");
                System.out.println(str);
            }

        }catch (Exception ex){

        }
    }

    public void inputReader(){

        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\test.log";
        try(FileReader fileReader=new FileReader(filePath))
        {
            char [] chars=new char[1024];
            int hasRead=0;
            while ((hasRead=fileReader.read(chars))>0){
                System.out.println(hasRead);
                String str=new String(chars,0,hasRead);
                System.out.println(str);
            }
        }catch (Exception ex){

        }
    }



}
