package com.company.core;

public class Person extends Heart {
    public static int B = A;


    /**
     * 关于instance of的用法
     */
    public void compare() {

        ParentClass parent = new ParentClass();
        SonClass son = new SonClass();

        boolean a = (parent instanceof ParentClass);
        System.out.print("parent instance of parentClass->");
        System.out.println(a);
        System.out.println();

        boolean b = (son instanceof SonClass);
        System.out.print("son instance of sonClass->");
        System.out.println(b);
        System.out.println();

        //父类实例和 子类的关系
        boolean c=(parent instanceof SonClass);
        System.out.print("parent instance of sonClass->");
        System.out.println(c);
        System.out.println();

        //子类实例 和 父类的关系
        boolean d=(son instanceof ParentClass);
        System.out.print("son instance of parentClass->");
        System.out.println(c);
        System.out.println();

        System.out.println("--------------------------------");

        //但是这样的情况就会出现问题
        ParentClass parentClassOfNewSon=new SonClass();
        boolean e=parentClassOfNewSon instanceof ParentClass;
        System.out.print("子类实例化父类的时候，实例出来的对象是 父类的呢，还是子类的呢->");
        System.out.println(e);
        System.out.println();

        boolean f=parentClassOfNewSon instanceof SonClass;
        System.out.print("子类实例化父类的时候，实例出来的对象是 父类的呢，还是子类的呢->");
        System.out.println(f);
        System.out.println();

        /**
         * 总结：
         * 当子类实例化 A 父类的时候
         *  A instanceof 父类 true
         *  A instanceof 子类 true
         */
    }
}
