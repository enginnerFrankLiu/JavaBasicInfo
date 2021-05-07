package com.company.core;

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
     *
     **/
    public void tst() {

        String a = "abc";
        String b = "afc";
        String c = b.replace('f', 'b');
        System.out.println(a == c);
    }

}
