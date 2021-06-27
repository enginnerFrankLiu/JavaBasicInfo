package com.company.core;

import com.company.basic.Apple;
import com.company.basic.Market;
import com.company.basic.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Console {

    /**
     * 对于类中成员的访问限制，
     * 其实，都是基本差不多的效果；
     * 只是多了一个包级 私有的；
     */
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

//        Product product=new Product(2,"banana");
//        product.showInfo();

//        Apple apple=new Apple();
//        int result=apple.c;
        //依然是无法访问的，对于包内部之间的类中的成员
//        int temp=apple.d;

        packagePrivateInfo packagePrivateInfo=new packagePrivateInfo();
        System.out.println(packagePrivateInfo.a);

    }
}
