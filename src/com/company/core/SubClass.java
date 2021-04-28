package com.company.core;

public class SubClass extends SuperClass {
    public SubClass(){
        System.out.println("sub class constructor.");
    }
    static {
        System.out.println("sub class static code.");
    }

    //常量在编译阶段会存入常量池，本质上没有直接引用定义常量的类，因此不会触发定义常量的初始化
    public static final String HE="hello.";

}
