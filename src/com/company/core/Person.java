package com.company.core;

public class Person extends Heart {
    public static int B = A;


    /**
     * 关于instance of的用法
     */
    public void compare() {

        ParentClass parent = new ParentClass();
        SonClass son = new SonClass();
//
//        boolean a = (parent instanceof ParentClass);
//        System.out.print("parent instance of parentClass->");
//        System.out.println(a);
//        System.out.println();
//
//        boolean b = (son instanceof SonClass);
//        System.out.print("son instance of sonClass->");
//        System.out.println(b);
//        System.out.println();
//
//        //父类实例和 子类的关系
//        boolean c=(parent instanceof SonClass);
//        System.out.print("parent instance of sonClass->");
//        System.out.println(c);
//        System.out.println();
//
//        //子类实例 和 父类的关系
//        boolean d=(son instanceof ParentClass);
//        System.out.print("son instance of parentClass->");
//        System.out.println(c);
//        System.out.println();

        System.out.println("--------------------------------");

        //但是这样的情况就会出现问题
        //都是true，所以不好区分，如果要区分，我们就应该使用 getclass
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

    /**
     * getClass will be useful when you want to make sure your instance is not subClass of the
     * class you are comparing with.
     *
     * 当一个类有继承类的时候，
     * 我们可以通过子类实例化父类，但是
     * 实例化出来的对象 本质上还是 子类的，
     * 只不过 它可以通过 父类 进行 申明，
     * 当我们想区分一个对象，是自己的类实例化出来的对象
     * 还是子类实例化出来的对象的时候，我们就不能使用 instanceof 方法
     *
     * 一个类的实例包括本身的实例 以及所有直接或者间接子类的实例
     *
     * 换成object 一样的效果
     *
     * 我们就要使用的是：getclass 方法
     */
    public void getClassInfo(){
        Object parentClassOfNewSon=new SonClass();
        boolean a=parentClassOfNewSon.getClass().equals(ParentClass.class);
        boolean b=parentClassOfNewSon.getClass().equals(SonClass.class);
        System.out.println("子类实例化父类的实例 call getClass equals ->");
        System.out.println(a);
        System.out.println("子类实例化父类的实例 本质上还是子类的实例，只是我们可以使用父类进行申明->");
        System.out.println(b);
        //这样就很清晰了
        System.out.println(parentClassOfNewSon.getClass());

    }

    public void objectInfo(){
        Object obj=new SonClass();
        System.out.println(obj instanceof Object);
    }

    /**
     * 自反性:contains(this) return false
     * 对称性:caseInsensitiveString vs String 的实现方式 两个不同类对象之间的对等
     * 传递性:
     * 一直性:
     */
    public void equalInfo(){


    }
}
