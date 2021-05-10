package com.company.core;

/**
 * https://www.jb51.net/article/171127.htm
 *
 * 这里需要了解的记住的几个重点：
 * 静态常量池
 * 字符串常量池
 * 运行时常量池
 *
 * 静态常量池主要存放两大类： 字面量 和 符号引用
 *
 *字面量：相当于java语言层面的常量概念-> 文本字符串,申明为final的常量等
 *
 * 符号引用则属于编译原理方面的概念:
 *
 * 1.类和接口的全限定名
 * 2.字段名称和描述符
 * 3。方法名称和描述符
 *

  而运行时常量池，则是：jvm虚拟机在完成装载操作后，将class文件中的常量池载入到内存中，并且保存在方法去钟.


 运行时常量池相对于class 文件常量池有另外一个动态特性:

 Java语言并不要求常量一定只有编译期才能产生，也就是并非预置入class文件中常量池的内容才能进入方法区运行时常量池，运行期间也可能将新的常量放入池中，这种特性被开发人员利用比较多的就是String类的intern()方法


 这里还有一个重点的区别：

 在JDK6.0及之前版本，字符串常量池是放在Perm Gen区(也就是方法区)中；
 在JDK7.0版本，字符串常量池被移到了堆中了。

 jvm在执行某个类的时候，必须经过加载 连接 初始化
 而连接又包含了： 验证 准备 解析 三个阶段.
 当类加载到内存后，jvm就会将静态常量池中的内容存放到运行时常量池中，由此可知道 运行时常量池也没个类都有的.

 */
public class Cat {

    //这种不能修改的常量在编译期间就能确定下来，所以说，调用它的时候不需要初始化Cat 类，
    //而这个常量->静态常量存在于 编译后的 .class 常量区，在运行时被载入到运行时常量池，属于某个类的，运行时常量池，
    //此处指的就是 main
    //实际上，在编译阶段 常量就会被存入到调用这个常量方法所在类的常量池当中
     //本质上调用类并没有直接引用到定义常量的类，因此并不会触触发定义常量类的的初始化.

    public static final String version="123";

    public static String info="call me cat.";

    public Cat(){
        System.out.println("constructor block.");
    }
    static {
        System.out.println("static block.");
    }

    /**
     *
     * 1.s0.intern() can either return an already interned string matching s0
     * 2.it can intern s0 itself and return that.
     *
     * t. In the case of foo1, when you call s0.intern(),
     * the program looks for a matching interned string, doesn't find one,
     * so interns s0 itself and returns that. In that case s0 becomes an interned string
     *
     *String s0 = new String("java.com"); uses a string literal "java.com" (which is in the string pool)
     * and separately creates a new string using the new operator, which is not in the string pool.
     * then you call s0.intern() it will return the string that already exists in the string pool.
     *
     * 任何形式带有变量的string ("+")操作,都会被编译器处理成 new string buffer 处理
     * 处理的结果都在 heap 区，没有在 heap 区的 string pool 中.
     *
     */
    public void foo() {
        String s0 = new String("java.com");
        s0 = s0.intern();
        String s1 = "java.com";
        System.out.println(s0 == s1);
    }

    public void foo1() {
        String s0 = new String("java") + new String(".com");
        s0.intern();
        String s1 = "java.com";
        System.out.println(s0 == s1);
    }
    public void foo2() {
        String s0 = new String("java.com");
        s0.intern();
        String s1 = "java.com";
        System.out.println(s0== s1);
    }

}
