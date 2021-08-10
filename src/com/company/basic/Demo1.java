package com.company.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Demo1 {
    public void show(){
        System.out.println("this is demo1 show...");
    }

    public void showInfo(){
        System.out.println("测试是不是只有记录在我们的 java 文件中的内容，才算有小绿点呢.");
    }


    /**
     * 数组是协变的
     * 泛型则是可变的
     */
    public void info(){
        Object [] objs=new Object[10];
        Long [] longs=new Long[10];
        Integer [] ints=new Integer[10];
        String [] strings=new String[100];

        objs=longs;
        objs=ints;
        objs=strings;

        //
//
//        List<Object> list=new ArrayList<>();
//        List<String> strs=new ArrayList<>();
//
//        list=strs;
    }


    /**
     * 这样直接些没意思，
     * 因为当我总是将实现的细节包装在函数的内部，
     *
     * 当我们想条件满足时，所要执行的参数，暴露给客户端（消费者） 自己使用时；
     *
     *  函数一般就是输入 和 输出
     *
     *  1.输入 consumer
     *  2.只返回 supplier
     *  3.输入并返回 Function
     *  在以上三种的基础上就出现了一些变形案例:
     *
     * Function: 接收参数，并返回结果，主要方法 R apply(T t)
     * Consumer: 接收参数，无返回结果, 主要方法为 void accept(T t)
     * Supplier: 不接收参数，但返回结构，主要方法为 T get()
     * Predicate: 接收参数，返回boolean值，主要方法为 boolean test(T t)
     *
     * 主要就是这几类；
     *
     *
     *  4.特定的 Function:-> predicate；输入任意参数，返回boolean 类型的 逻辑判断结果；效果还算ok
     *
     */
    public void InfoLog(){
        Consumer<String> stringConsumer=System.out::print;
        stringConsumer.accept("String Info");
    }

    public void msd(String val,Consumer<String> consumer){
        consumer.accept(val);
    }

    public void soSomething(Integer type,String dataSource,Consumer<String> consumer){

        if(type==1){

        }else if(type==2){

        }else{
            consumer.accept(dataSource);
        }

    }

    public void client(){
        soSomething(2,"fuck",System.out::print);
    }

    /**
     *
     */
    public void supplierInfo(int len, Supplier<Integer> integerSupplier){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<len;i++){
            list.add(integerSupplier.get());
        }
    }

}
