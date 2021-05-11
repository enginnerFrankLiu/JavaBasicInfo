package com.company.core;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
     * 驻留的字符串是放在全局共享的字符串常量池中的:
     *
     * 1）类加载对一个类只会进行一次。”xyz”在类加载时就已经创建并驻留了
     * （如果该类被加载之前已经有”xyz”字符串被驻留过则不需要重复创建用于驻留的”xyz”实例）。驻留的字符串是放在全局共享的字符串常量池中的。
     *
     * （2）在这段代码后续被运行的时候，”xyz”字面量对应的String实例已经固定了，
     * 不会再被重复创建。所以这段代码将常量池中的对象复制一份放到heap中，并且把heap中的这个对象的引用交给s1持有
     *
     * "常量池中的对象复制一份放到heap中"
     *
     * 常量池中的对象，只的是，“A” 还是 “A” 在 string pool 中的引用.
     * 这里，我暂且理解成，“A” 在string pool 中的引用吧
     *
     * 运行期间，将常量放入到常量池，怎么去理解这句话
     *
     */
    public void fuck1(){

        String a=new String ("fuckC");
        String b=a.intern();
        System.out.println(a==b);
    }

    public void finalFuck(){
        String a=new String("INFO");
        String b=a.intern();
        String c="INFO";
        System.out.println(a==b); //false
        System.out.println(a==c); //true
    }

    /**
     * 这里还有另外的一种说法:
     * 在HotSpot VM里实现的string pool功能的是一个StringTable类，
     * 它是一个哈希表，里面存的是驻留字符串(也就是我们常说的用双引号括起来的)的引用（而不是驻留字符串实例本身），
     * 也就是说在堆中的某些字符串实例被这个StringTable引用之后就等同被赋予了”驻留字符串”的身份。这个StringTable在每个HotSpot VM的实例只有一份，被所有的类共享。
     * 总的来说，这个链接，将的还不错：https://www.jb51.net/article/171127.htm
     *
     * 全局字符串常量池中存放的内容是在类加载完成后存到String Pool中的，在每个VM中只有一份，存放的是字符串常量的引用值(在堆中生成字符串对象实例)。
     *
     * https://zhuanlan.zhihu.com/p/64839455
     *
     * 运行时常量池是在类加载完成之后，将每个class常量池中的符号引用值转存到运行时常量池中，也就是说，每个class都有一个运行时常量池，类在解析之后，将符号引用替换成直接引用，与全局常量池中的引用值保持一致。
     *
     */
    public void summary(){

    }


    /**
     *
     *https://zhuanlan.zhihu.com/p/64839455
     *
     * 示例中首先经过编译之后，在该类的class常量池中存放一些符号引用，然后类加载之后，
     * 将class常量池中存放的符号引用转存到运行时常量池中，然后经过验证，准备阶段之后，
     * 在堆中生成驻留字符串的实例对象(也就是上例中str1所指向的“abc”实例对象)，
     * 然后将这个对象的引用存到全局String Pool中，也就是String Pool中，
     * 最后在解析阶段，要把运行时常量池中的符号引用替换成直接引用，
     * 那么就直接查询String Pool，
     * 保证String Pool里的引用值与运行时常量池中的引用值一致，大概整个过程就是这样了。
     *
     * 回到示例的那个程序，现在就很容易解释整个程序的内存分配过程了，首先，
     * 在堆中会有一个“abc”实例，全局String Pool中存放着“abc”的一个引用值，
     * 然后在运行第二句的时候会生成两个实例，一个是“def”的实例对象，
     * 并且String Pool中存储一个“def”的引用值，还有一个是new出来的一个“def”的实例对象，
     * 与上面那个是不同的实例，当在解析str3的时候查找String Pool，里面有“abc”的全局驻留字符串引用，
     * 所以str3的引用地址与之前的那个已存在的相同，str4是在运行的时候调用intern()函数，
     * 返回String Pool中“def”的引用值，如果没有就将str2的引用值添加进去，
     * 在这里，String Pool中已经有了“def”的引用值了，
     * 所以返回上面在new str2的时候添加到String Pool中的 “def”引用值，
     * 最后str5在解析的时候就也是指向存在于String Pool中的“def”的引用值，那么这样一分析之后，结果就容易理解了。
     *
     * After deep search and think.
     * 1.String pool just implement by HashTable which will not stored instance,means: no "A" stored in string pool.
     * 2.This hashtable key-> hashcode("A") value-> reference of "A" in heap.
     * 3.When you call String aInterned=a.intern();  a==aInterned will be true.
     *
     * if there is already "A" existed in heap,（ this String object is added to the pool and a reference to this String object is returned.）
     * String aa="A";
     * String a=new String("A");
     * String ab=a.intern();
     *
     * aa==ab true
     * a==ab false
     *
     * so there is no duplicate value(string) in heap,no matter what kind of initialization way(literal or new String).  then the string from the pool is returned
     *
     *
     *
     *
     */
    public void infoqd(){


        String str1 = "abc";
        String str2 = new String("def");
        String str3 = "abc";
        String str4 = str2.intern();
        String str5 = "def";
        System.out.println(str1 == str3);// true
        System.out.println(str2 == str4);// false
        System.out.println(str4 == str5);// true
    }



}
