package com.company.basic;


import com.company.core.packagePrivateInfo;

public class Demo1 {
    public void show(){
        System.out.println("this is demo1 show...");
    }

    public void showInfo(){
        System.out.println("测试是不是只有记录在我们的 java 文件中的内容，才算有小绿点呢.");
    }

    /**
     * 这个就是所谓的包级别私有的
     */
    public void info(){
//        packagePrivateInfo packagePrivateInfo=new packagePrivateInfo();
//        System.out.println(packagePrivateInfo.a);
    }

}
