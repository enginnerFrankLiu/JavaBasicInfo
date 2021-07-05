package com.company.basic;


import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public void show(){
        System.out.println("this is demo1 show...");
    }

    public void showInfo(){
        System.out.println("测试是不是只有记录在我们的 java 文件中的内容，才算有小绿点呢.");
    }


    /**
     * 数组是协变的
     * 泛型则是可变的
     */
    public void info(){
        Object [] objs=new Object[10];
        Long [] longs=new Long[10];
        Integer [] ints=new Integer[10];
        String [] strings=new String[100];

        objs=longs;
        objs=ints;
        objs=strings;

        //
//
//        List<Object> list=new ArrayList<>();
//        List<String> strs=new ArrayList<>();
//
//        list=strs;
    }

}
