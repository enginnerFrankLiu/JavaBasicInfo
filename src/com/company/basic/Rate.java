package com.company.basic;

public class Rate {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Rate(String id) {
        this.id = id;
    }


    public class log {

        public void info() {

            if (getId() == "1") {
                System.out.println("11");
            } else {
                System.out.println("22");
            }
        }
    }

    /**
     * 局部类不能用public或private，它的作用域被限定在生命这个局部类的块中。
     */
    public void writeInfo() {

        String version = "versioninfo";

        class write {

            public void doInfo() {

                System.out.println("局部内部类，居然可以在方法体重创建 我们这个内");
                System.out.println("但是 这个类 不能被申明为public 或者 private，作用范围：仅限于方法体内部，卧槽");
            }
        }


        write w = new write();
        w.doInfo();

        System.out.println(version);

    }

    /**
     * 方法体里的内部类
     * 中的方法，可以方法 方法的参数
     *
     * 你这样写 并没有什么问题
     * 但是
     * 一旦 你在方法内部 进行修改，那么就会 导致编译不过
     *
     * 匿名内部类
     * @param x
     * @param y
     */
    public void foo(final int x, final int y, final boolean flag) {
        class tool {
            public void bar() {
                System.out.println(x);
                System.out.println(y);
                if (x > 0 && y > 0 && flag) {
                    System.out.println("all value are greater than 0");
                }
            }
        }
        tool t = new tool();
        t.bar();
    }

    /**
     * 这个就是其中的匿名内部类的使用
     */
    public void anClassInfo(){
        IComputer computer=new IComputer() {
            @Override
            public void doCal() {
                System.out.println("can be simple with lamad class.");
            }
        };
    }

    /**
     * 在面向对象变成的语言中，class 是一等公民，不管你做什么，第一件事情就是要申明一个类；
     * 这就导致一个麻烦的问题
     * 有些方法，可能只会被定义并且使用 一次 但是 你还是不得不申明类；
     * 这个只能解决这一个问题，
     * 如果接口内包含了，两个 或者 两个以上的 方法申明，你就要同时实现这两个method
     *
     *
     * 所以函数式 接口，的定义：只能有个method
     * 这样才能很好的利用的匿名内部类
     * 或者叫 lambda 表达式
     *
     *
     */
    public void doAnonymousClass(){
       IComputer method =()-> System.out.println("print ifno");
       method.doCal();
    }
}
