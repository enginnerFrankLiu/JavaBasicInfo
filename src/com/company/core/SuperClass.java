package com.company.core;

/**
 * 类加载的过程:
 * 加载 验证 准备 解析 初始化
 */
public class SuperClass {
    public SuperClass(){
        System.out.println("super class constructor.");
    }
    //call static code block don't trigger constructor.
    static {
        System.out.println("super class static code.");
    }
    public static int val=886;
}
