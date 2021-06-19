package com.company.core;

import com.company.basic.Market;
import com.company.basic.Product;
import com.company.basic.Rate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        rate.writeInfo();


    }

}
