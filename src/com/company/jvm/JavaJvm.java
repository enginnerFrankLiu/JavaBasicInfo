package com.company.jvm;

/**
 * 1og1:今天继续赶java的jvm相关的姿势点
 * https://www.cnblogs.com/neverth/p/11874458.html
 *
 * 汇编语言中，程序计数器指的就是：cpu中的寄存器，它保存的程序当前执行的指令地址（也可以说保存吓一跳指定的所在的存储单元地址）
 *当CPU需要执行指令时，需要从程序计数器中得到当前需要执行的指令所在存储单元的地址
 * 符号引用包括以下三种
 * 1.类和接口的全限定名
 * 2.字段名称和描述符
 *
 * class 文件中的常量池：
 * 字符串常量
 * 类和接口名字，字段名，和其他一些class 引用的常量
 *
 * 运行时常量池和class文件的常量池是一一对应的，它就是class文件的常量池来构建的。
 * 运行时常量池中有两种类型，分别是symbolic references符号引用和static constants静态常量。
 * 运行时常量池
 * 其中静态常量不需要后续解析，而符号引用需要进一步进行解析处理。
 * 静态常量：String常量和数字常量
 *
 * 调用intern方法主要是将这个String实例加入字符串常量池
 *
 * 有提到String常量是对String对象的引用
 *
 * String Pool中存放的是字符串的实例，也就是用双引号引起来的字符串
 *
 */
public class JavaJvm {

    /**
     * 这里涉及到几个常见的各种概念；
     * 1.class 文件中的常量池，类 和接口名字，字段名，和其他一些在class中引用的常量，没个class 都有一
     *
     *
     *
     * 2.运行时常量池
     *
     *  运行时常量次保存的是从class 文件常量次构建的静态常量引用好符号引用，没个class 都有一份
     *
     * 3.字符串常量池
     *
     *  字符串常量次保存的是“字符实例”，供运行时常量池引用
     *
     *
     * 4.
     */
    public void info(){

    }
}
