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
     *
     * https://blog.csdn.net/qq_34039868/article/details/103957965
     *
     * class 文件中 有一项：constant_pool 常量池，主要存放两大类常量：
     * 字面量 和 符合引用
     * 字面量：文本字符串，final 常量值，
     * 符号引用：
     * 类和接口的全限定名
     * 字段的名称和描述
     * 方法的名称和描述符
     *
     * 当jvm运行时，需要从常量池获取对应的符号引用，在创建和运行时解析，翻译得到具体的内存地址；
     * 字面量就是普通的常量
     *
     * ，运行时常量池是class文件中每一个类或接口的常量池表的运行时表示形式
     *  每一个运行时常量池都在Java虚拟机的方法区中分配
     *  每一个class都有自己的运行时常量池
     *
     *  就是在Java8中宣布取消永久代（方法区是堆的逻辑部分，真正实现的是永久代），用元空间（metaspace）来代替永久代，变
     *
     * 移除了永久代（PermGen），替换为元空间（Metaspace）
     * 永久代中的 class metadata 转移到了 native memory（本地内存，而不是虚拟机）
     *
     * 久代中的 interned Strings 和 class static variables 转移到了 Java heap
     *
     *https://zhuanlan.zhihu.com/p/111809384
     */
    public void info(){

        String name="jack";
        String userName=new String("jack");
        System.out.println(name==userName);

    }

    /**
     * 他们的过程是不一样的；
     */
    public void infoV2(){

        String name="jack";
        String userName=new String("jack");
        String user=userName.intern();

        System.out.println(name==user);


    }
}
