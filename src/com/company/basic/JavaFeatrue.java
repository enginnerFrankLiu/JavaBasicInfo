package com.company.basic;

import java.util.*;

public class JavaFeatrue {

    public void Info() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " value :" + entry.getValue());
        }
    }

    public void java8ForeachMap(){

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        map.forEach((key,val)->{
            System.out.println("KeY:" +key +" val "+val);
        });
    }

    /**
     * pretty good.
     */
    public void doFilter(){

        List<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(null);
        list.stream().filter(x->x!=null).forEach(System.out::println);
    }

    /**
     * the new way to filter object which is not null;
     * consumer 内部接受的 仅仅是一个consumer 函数了
     * 如果有必要的话；可以 catch 住 foreach 中的异常，避免整个迭代循环 挂掉了
     * 整体效果还算比较ok的呀；
     */
    public void doFilter2(){
        List<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(null);
        list.stream().filter(Objects::nonNull).forEach(System.out::println);
    }

    public void infod(){
        List<String> list=new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("G");
        list.add("C");
        list.sort(Comparator.naturalOrder());
        for (String s : list) {
            System.out.println(s);
        }
    }

}
