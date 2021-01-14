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
     *
     *
     *
     * class 文件中常量池保存的字符串常量，类 和接口名字，字段名，和其他一些在class中引用的常量
     * 每个class都有一份；
     *
     * 运行时常量是从class文件常量池构建的静态常量引用和符号引用每个class都有一份。
     *
     * 字符串常量池保存的是“字符实例”，供运行时常量引用；
     *
     * String intern 方法会从字符串常量池中查询当前字符串是否存在，如不存在就将当前字符串放在常量池中.
     *
     * 如果常量池中存在当前字符串, 就会直接返回当前字符串. 如果常量池中没有此字符串, 会将此字符串放入常量池中后, 再返回
     *
     *  JAVA 使用 jni 调用c++实现的StringTable的intern方法
     *
     *  String的String Pool是一个固定大小的Hashtable 可能导致的问题;
     *
     *  1.string.intern 的性能下降（因为需要一个一个的找）
     *
     *  2.String s = new String("abc")这个语句创建了几个对象
     *
     *   1.第一个对象是”abc”字符串存储在常量池中
     *
     *   2.JAVA Heap中的 String 对象。
     *
     *
     * java 类的加载过程可以分为五个阶段：载入，验证，准备，解析，初始化
     * 载入，验证，准备，解析，初始化
     *
     *
     * 每个线程都会创建一个操作站，每个栈又包含若干个栈针，每个栈针对应着每个方法每次调用，每个栈针都包含如下的三个部分:
     * 1.局部变量去，
     *
     * 操作栈数 执行过程中产生的中间结果
     *
     * 运行环境：动态链接，正确的方法返回信息，异常捕捉.
     *
     * 本地方法栈是在程序调用或JVM调用本地方法接口（Native）时候启用。
     *
     * 本地方法都不是使用Java语言编写的，比如使用C语言编写的本地方法，本地方法也不由JVM去运行，所以本地方法的运行不受JVM管理。
     *
     *  字节码解释器工作时候，就是通过改变这个计数器来的值 来确定下一条需要执行的字节码指令的；
     *
     *
     *  分支 循环 跳转 异常处理 线程恢复等基础功能都是需要依赖这个计数器来实现的;
     *
     *
     *  当线程正在执行一个java方法是，程序计数器记录的正式执行jvm字节码的指令的地址，如果正字执行的是一个native方法，那么计数器的值为 空;
     *
     *
     *  jvm 执行引擎；
     *
     *  可能有两种执行方式：
     *  解释执行，通过解释器执行
     *  编译执行：通过即使编译执行 通过即时编译产生本地代码
     *
     * java native interface
     * J N I
     *
     *
     * 不再跨平台，要想跨平台，必须在不同的系统环境下程序编译配置本地语言部分
     *
     * 程序不再是绝对安全的，本地代码的使用不当可能会导致整个程序崩溃。一个通用规则是，调用本地方法应该集中在少数的几个类当中，这样就降低了Java和其他语言之间的耦合
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

    /**
     * https://tech.meituan.com/2014/03/06/in-depth-understanding-string-intern.html
     * 解析：
     *  String s = new String("1");
     *  生成两个对象
     *
     *  1.heap 中的String 对象
     *  2.常量池中 "1"
     *
     *  当你调用
     *  s.intern(); 时候，只是去判断常量池中是否存在“1”，如果不存在，就将值写入到常量池中，并且返回地址
     *
     *  String s3 = new String("1") + new String("1");
     *
     *  生成两个最终对象：
     *  1.常量池中的“1”
     *  2.java heap s3 引用指向的对象
     *
     */
    public void internTest(){
        String s = new String("1");
        String s3=s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        System.out.println("-----------------");

        //这个返回的就是true
        System.out.println(s3==s2);


    }

    /**
     *
     * 可以直接存储堆中的引用。这份引用指向 s3 引用的对象
     *
     */
    public void internTestJJ(){

        String S3=new String("1")+new String("1");
        //当这个对象去驻留的时候，不会再在常量池中去放 “11” 这个“副本” 而是 “11” 在 heap 中的地址
        S3.intern();      // "11"-> heap 中的常量池中(java7+) 神奇的事情是 s3保存的是 常量池中地址
        String s4="11";   // "11" 返回 heap 中常量池的地址.
        System.out.println(S3==s4);

    }

    /**
     * 1.Java 在编译的时候会直接将代码封装成 Integer i1=Integer.valueOf(10);，从而使用常量池中的对象
     * 2.information.
     */
    public void md(){
        //每次new 都会创建新对象
        Integer a=new Integer(10);
        Integer b=new Integer(10);
        System.out.println(a==b);

        System.out.println("--------------------");

        Integer c=10;
        Integer d=10;
        System.out.println(c==d);
    }

    public void msInfo(){
        String str1 = "aaa";
        String str2 = "aa"+"a";
        System.out.println(str1==str2);
    }
}
