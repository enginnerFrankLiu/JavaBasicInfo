package com.company.core;

import com.company.basic.*;

import java.util.Arrays;
import java.util.Comparator;

public class Console {
    public static void log() {

//        List<Market> list = new ArrayList<>();
//        Market market = new Market();
//        market.setId(55);
//        list.add(market);
//
//        Market market1 = new Market();
//        market1.setId(66);
//        list.add(market1);
//
//        Market market2 = new Market();
//        market.setId(22);
//        list.add(market2);
//
//        for (Market m : list) {
//            System.out.println(m.getId());
//        }
//
//        System.out.println("-------after sort----------");
//
//        list.sort(Comparator.comparingInt(Market::getId));
//
//
//        for (Market m : list) {
//            System.out.println(m.getId());
//        }

        Product product=new Product(2,"banana");
        product.showInfo();
    }

    public void innerClass(){

//        //内部类 也可以通过这种方式来进行访问的哈
//      int version=Product.woman.version;

      //TimePrinter类位于TalkingClock类内部，并不意味着每个TalkingClock对象都有一个TimePrinter实例域。
      //通常来说this限定词是多余的，但是可以通过显式命名将外围类引用设置为其他对象
      //也就无法通过这种方式来进行访问
     // int v2=Product.man.version;

        //要用实例化出来的对象 来 实例化 内部类的 实例,
        Rate rate=new Rate("1");
//        Rate.log log=rate.new log();
//
//
//        log.info();
//        rate.writeInfo();
//
//        rate.foo(100,120,true);

        rate.doAnonymousClass();

    }

    public void lengthCompare(){
        String [] strings=new String[3];
        strings[0]="aaa";
        strings[1]="aa";
        strings[2]="aaaa";
        Arrays.sort(strings, new  LengthComparator());

        Arrays.sort(strings, Comparator.comparingInt(String::length));

        for (String string : strings) {
            System.out.println(string);
        }
    }

    /**
     * 有时候：已经有方法可以完成你想要传递到其他代码的动作；
     * 有时候，已经有方法可以完成你想要传递到其他代码的动作；
     * object::instanceMethod
     * Class::staticMethod
     * Class::instanceMethod
     *
     * 通常可能想在lambda表达式中访问外围方法或类中的变量
     */
    public void info(){

//        String [] strings=new String[3];
//        strings[0]="aaa";
//        strings[1]="aa";
//        strings[2]="aaaa";
//
//       List<String> list= Arrays.stream(strings).filter(String::isEmpty).collect(Collectors.toList());


    }

    /**
     * 尝试去实现一个
     * 给予枚举实现的单例模式.
     */
    public void buildInfo(){
        Label label=new Label
                .Builder(1,"jack")
                .Remark("rest")
                .build();

        /**
         * 只构建必须的属性；
         */
        Label apple=new Label
                .Builder(1,"tom")
                .build();
        System.out.println(label);

        System.out.println(apple);
    }

}
