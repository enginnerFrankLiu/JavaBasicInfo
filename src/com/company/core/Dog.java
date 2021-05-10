package com.company.core;

import javax.print.DocFlavor;

public class Dog {

    public void dd(){
        String val0="fuck";
        String val1="fuck";
        String val2="fuck";
        System.out.println(val0==val1);
        System.out.println(val0==val2);
        System.out.println(val1==val2);
    }

    public void dd1(){

        String val0=new String("shit");
        String val1=new String("shit");
        String val2=new String("shit");
        System.out.println(val0==val1);
        System.out.println(val0==val2);
        System.out.println(val1==val2);
        
    }

    /**
     * 搞懂这两种分配内存的方式，有何不同
     * String 是个包装类
     */
    public void test() {
        String val = new String("info");
        String Val2 = new String("info");

        System.out.println(val.equals(Val2));
        System.out.println(val == Val2);

        System.out.println("----------call intern method---------------");

        String orignalInfo = "dog";
        String d = new String("dog");
        String md = new String("dog").intern();
        System.out.println(orignalInfo == md);
    }

    public void foo1() {
        String s0 = new String("java.com");
        s0 = s0.intern();
        String s1 = "java.com";
        System.out.println(s0 == s1);
    }

    public void foo0() {
        String s0 = new String("java.com");
        s0.intern();
        String s1 = "java.com";
        System.out.println(s0== s1);
    }

    public void foo2() {
        String s0 = new String("java") + new String(".com");
        s0.intern();
        String s1 = "java.com";
        System.out.println(s0 == s1);
    }

    /**
     * toString();又创建了一个新的对象.而改String对象传递数组的构造方法来创建.
     * 而改String对象传递数组的构造方法类创建的.
     * <p>
     * 也就是说，String 对象的value值，直接指向了一个已经存在的数组，而并没有指向常量池中的字符串。
     */
    public void foo3() {
        StringBuilder sb = new StringBuilder("a");
        String ab0 = sb.append("b").toString();
        String ab1 = "ab";
        System.out.println(ab0 == ab1);
    }

    public void foo4() {
        StringBuilder sb = new StringBuilder("c");
        String ab0 = sb.append("d").toString();
        ab0.intern();
        String ab1 = "cd";
        System.out.println(ab0 == ab1);
    }

    /**
     * if value changed, a new object is created and retruned to you
     * such as replace() and replace();
     * StringPool is stored in heap area where object reference data will be stored as we know String is Char[].
     *
     * string literals are stroed in string pool and string obejcts are stored in as usual heap object area.
     *
     *
     *return false
     **/
    public void tst() {
        String a = new String("abc");
        String b = a.intern();
        System.out.println(a == b);
    }

    /**
     * return true.
     */
    public void md() {
        String ab = new String("a") + new String("b");
        String a = ab.intern();
        System.out.println(ab == a);
    }

    /**
     * 对于Object();
     * 我们都知道，当我们使用equal 去比较的时候，我们都是去比较他们测content.是否equal
     * 当我们使用==去比较的时候，我们都是在比较他们的reference 是否相等.
     *
     * 这个例子，我们可以反推出，引用不相等;
     *
     * a_b 存在于堆中.
     * ab 存在于常量池中.
     * print false;
     */
    public void bar0(){
        String a="a";
        String b="b";
        String a_b=a+b;
        String ab="ab";
        System.out.println(a_b==ab);
    }

    /**
     * 这个例子，中我们可以看到，
     * "a"+"b"; 是编译器做了优化，直接让"ab" 驻留在string pool中.
     * print true.
     */
    public void bar1(){
        String a_b="a"+"b";
        String ab="ab";
        System.out.println(a_b==ab);
    }

    /**
     * 这个例子中我们可以推出
     * "" + new String("");
     * 这种新创建的对象，存在于堆中(也许string pool 中也存在一份，拼接后的值)
     * print false
     */
    public void bar2(){
        String a=new String("a");
        String a_b=a+"b";
        String ab="ab";
        System.out.println(a_b==ab);
    }

    /**
     * 1.关于intern方法，官方稳定.
     * when the method is invoked. if the pool already contains a string euqal to this string content.
     * the string from the pool is returned.
     * otherwise.
     * the string objected is added to the pool
     * a reference to this string objected is returned.
     *
     * 所以此处是： a reference to this object is returned.
     *
     *  s0 interns itself into string pool table. and return s0 reference.
     */
    public void internInfo(){

        String s0 = new String("java") + new String(".com");
        String d=s0.intern();
//        String s1 = "java.com";
//        System.out.println(s0 == s1);
        System.out.println(d);
    }

    /**
     * 这个 返回的是，他在 string pool 中的 reference.
     *
     * 所谓的驻留，只是把 引用注册在 string table 中，
     * 并不是把 字符串 放在 string pool 中
     *
     */
    public void internInfo2(){
        String s0 = new String("c#.com");
       String d= s0.intern();
       System.out.println(d);
//        String s1 = "java.com";
//        System.out.println(s0== s1);
    }

    public void loop(){

        while (true){

            new String ("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

        }
    }

    public void ddf(){
        String s1 = "Rakesh";
        String s2 = "Rakesh";
        String s3 = "Rakesh".intern();

        if ( s1 == s2 ){
            System.out.println("s1 and s2 are same");  // 1.
        }

        if ( s1 == s3 ){
            System.out.println("s1 and s3 are same" );  // 2.
        }
    }

    /**
     * intern method is supposed to return the String from the String pool if the String is found in String pool,
     */
    public void fuck0(){
        String b="fuckB";
        String a=new String ("fuckB");
        String c= a.intern();
        System.out.println(a==c);
    }

    /**
     *otherwise a new string object will be added in String pool and the reference of this String is returned.
     *
     */
    public void fuck1(){

        String a=new String ("fuckC");
        String b=a.intern();
        System.out.println(a==b);
    }
}
