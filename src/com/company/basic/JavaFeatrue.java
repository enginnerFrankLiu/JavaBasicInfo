package com.company.basic;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void java8ForeachMap() {

        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);

        map.forEach((key, val) -> {
            System.out.println("KeY:" + key + " val " + val);
        });
    }

    /**
     * pretty good.
     */
    public void doFilter() {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(null);
        list.stream().filter(x -> x != null).forEach(System.out::println);
    }

    /**
     * the new way to filter object which is not null;
     * consumer 内部接受的 仅仅是一个consumer 函数了
     * 如果有必要的话；可以 catch 住 foreach 中的异常，避免整个迭代循环 挂掉了
     * 整体效果还算比较ok的呀；
     */
    public void doFilter2() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(null);
        list.stream().filter(Objects::nonNull).forEach(System.out::println);
    }

    /**
     * Functional interface
     * T apply(T t)           UnaryOperator<T>
     * T apply(T t1,T t2)     BinaryOperation<T>
     * R apply(T t)           Function<T t>
     * boolean test(T t,U t)  Predicate<T,U>
     * T get()                Supplier<T>
     * void accept(T t)       Consumer<T>
     */
    public void infod() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("G");
        list.add("C");
        list.sort(Comparator.naturalOrder());
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * list to map without duplicate key.
     */
    public void listToMap() {

        List<Product> list = new ArrayList<>();
        Product product0 = new Product();
        product0.setId(1);
        product0.setName("apple");
        product0.setInfo("apple information.");

        Product product1 = new Product();
        product1.setId(2);
        product1.setName("desk");
        product1.setInfo("desk information.");

        Product product2 = new Product();
        product2.setId(3);
        product2.setName("apple");
        product2.setInfo("apple information.");

        list.add(product0);
        list.add(product1);
        list.add(product2);

//        Map<String, String> mapInfo = list
//                .stream()
//                .collect(Collectors.toMap(keyMapper -> keyMapper.getName(),
//                        valueMapper -> valueMapper.getInfo(),
//                        (oldVal,newVal)-> oldVal));


        Map<String, String> mapInfo = list.stream().collect(
                Collectors.toMap(Product::getName, Product::getInfo, (oldVal, newVal) -> oldVal));

//        mapInfo.forEach((key, val) -> {
//            System.out.println("key :" + key + " val: " + val);
//        });

        mapInfo.forEach((key, val) -> System.out.println("key :" + key + " val: " + val));

    }

    public void sortList() {


        List<Product> list = new ArrayList<>();
        Product product0 = new Product();
        product0.setId(1);
        product0.setName("1-apple");
        product0.setInfo("apple information.");

        Product product1 = new Product();
        product1.setId(3);
        product1.setName("3-desk");
        product1.setInfo("desk information.");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("2-apple");
        product2.setInfo("apple information.");

        list.add(product0);
        list.add(product1);
        list.add(product2);

//        list.sort(new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return o1.getId()-o2.getId();
//            }
//        });

        /**
         * 它也只是提供一种默认的选择方式 进行选择的低呀；
         * 整体效果怀算不错的滴滴呀；
         */
        list.sort((a, b) -> b.getId() - a.getId());
        //可以简化成 这样的?
//        list.sort(Comparator.comparingInt(Product::getId));
//        list.sort(Comparator.comparingInt(obj-> obj.getId()));
        for (Product product : list) {
            System.out.println(product.getName());
        }
    }

    public void functionInfo() {
        Function<String, Integer> fn = x -> x.length();
        Integer result = fn.apply("life");
        System.out.println(result);
    }

    /**
     * 通过 andThen 来实现函数调用链条；
     * 上一个函数 执行后的结果，然后 传递到另外一个函数中中尉参数;
     */
    public void functionChain() {

        Function<String, Integer> fn0 = x -> x.length();

        Function<Integer, Integer> fn1 = x -> x * 2;

        Integer result = fn0.andThen(fn1).apply("life");

        System.out.println(result);

    }

    /**
     *
     */
    public void listStringToMapInfo() {
        List<String> list = Arrays.asList("java", "c#", "javascript");
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(x -> x, x -> x.length()));
        map.forEach((key, val) -> {
            System.out.println("Key :" + key + " val: " + val);
        });
    }

    public void listStringToMapInfoV2() {

        List<String> list = Arrays.asList("java", "c#", "javascript");
        Map<String, Integer> map = convertListMap(list, x -> x.length());
        map.forEach((key, val) -> {
            System.out.println("Key :" + key + " val: " + val);
        });

    }

    public <T, R> Map<T, R> convertListMap(List<T> list, Function<T, R> fn) {
        Map<T, R> result = new HashMap<>();
        for (T t : list) {
            result.put(t, fn.apply(t));
        }
        return result;
    }

    /**
     *
     */
    public void streamInfoQ() {

        String[] arr = {"hello", "java", "stream"};
        Stream<String> stream = Arrays.stream(arr);
        List<String> list = stream.map(x -> x + "ddd").collect(Collectors.toList());
        for (String s : arr) {
            System.out.println(s);
        }

        System.out.println("-----------------------");
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * java 8 Collection<E> 说明只要是Collection<E>的实现都可以创建流。
     */
    public void createStreamFromCollection() {

        Collection<String> strings = Arrays.asList("hello", "java", "stream");

        Stream<String> stream = strings.stream();

        Stream<String> stringStream = strings.parallelStream();

    }

    public void createStreamFromArray() {

//        //方式一:
//        Stream<String> streamFromArray=Stream.of("hello","java","stream");
//
//        //方式二: 从已经有（存在）的 array 中创建
//        String [] arr={"hello","java","stream"};
//        Stream<String> stream=Arrays.stream(arr);
//
//        Stream<String> streamOfArrPart=Arrays.stream(arr,1,2);
//
//
        Stream<String> stream = Stream.generate(() -> "fuck").limit(2);
        stream.forEach(System.out::println);

    }

    /**
     * 整体效果还算比较ok的.
     */
    public void consumerInfo() {
        Consumer<Integer> consumer = (x) -> {

            int num = x * 2;
            System.out.println(num);

        };


        Consumer<Integer> consumer1 = (x) -> {

            int num = x * 3;
            System.out.println(num);

        };

        consumer.andThen(consumer1).accept(10);
    }

    /**
     *
     */
    public void streamInfo() {

//
//        Stream<String> stringStream=Stream.of("a","b","c");
//        List<String> list=stringStream
//                .map(x-> x+"d")
//                .collect(Collectors.toList());
//
//        System.out.println(list);

    }

    /**
     * <R> R collect(Supplier<R> supplier,
     * BiConsumer<R, ? super T> accumulator,
     * BiConsumer<R, R> combiner);
     * <p>
     * 首先泛型模板参数：R
     * 函数 返回值也是 R
     * 第一个函数是一个supplier，一个supplier函数，之后返回值，返回的也是R
     * <p>
     * BiConsumer 是一个consumer 类型的函数，也就是说是一个 消费类型的函数，没有返回值， 对集合R 进行操作，操作的参数 T
     * <p>
     * BiConsumer 也是一个consumer 它的两个参数 都是 R  知名为 combiner
     */
    public void ms() {
        Stream<String> stringStream = Stream.of("a", "b", "c");
        String result = stringStream.collect(() -> new StringBuilder(), (l, x) -> l.append(x), (r1, r2) -> r1.append(r2)).toString();
        System.out.println(result);

        Stream<String> stringStream1 = Stream.of("a", "b", "c");
        String re = stringStream1.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
        System.out.println(re);

    }

    public void joinInfo() {

        List<String> names = new ArrayList<>();
        names.add("jack");
        names.add("tom");
        names.add("frank");

        String result = names.stream().collect(Collectors.joining());
        System.out.println(result);

        String val = names.stream().collect(Collectors.joining(","));
        System.out.println(val);

        //prefix and suffix;
        String temp = names.stream().collect(Collectors.joining(",", "(", ")"));
        System.out.println(temp);

        //先转成大写，然后再join起来
        String joinResult = names.stream().collect(Collectors.collectingAndThen(Collectors.joining(","), String::toUpperCase));
        System.out.println(joinResult);

    }

    /**
     * 最常用的之一：group by -> key List
     */
    public void groupBy() {

        List<String> names = new ArrayList<>();
        names.add("jack");
        names.add("tom");
        names.add("frank");
        names.add("jack");

        Map<Integer, List<String>> map = names.stream().collect(Collectors.groupingBy(String::length));

        map.forEach((key, val) -> {
            for (String s : val) {
                System.out.println("key:" + key + "  val: " + s);
            }
        });

    }

    /**
     * 最常用的方式之二：group by -> key Set
     * no duplicate.
     */
    public void groupBySet() {

        List<String> names = new ArrayList<>();
        names.add("jack");
        names.add("tom");
        names.add("frank");
        names.add("jack");

        Map<Integer, Set<String>> noDuplicateNames = names
                .stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));

        noDuplicateNames.forEach((key, val) -> {
            for (String s : val) {
                System.out.println("key:" + key + "  val: " + s);
            }
        });
    }

    /**
     * 线程同步的集合
     * 线程同步的集合
     * 效果还算可以的
     */
    public void syncGroupBy() {

        List<String> names = new ArrayList<>();
        names.add("jack");
        names.add("tom");
        names.add("frank");
        names.add("jack");

        Supplier<Map<Integer, Set<String>>> mapSupplier = () -> Collections.synchronizedMap(new HashMap<>());

        Map<Integer, Set<String>> collect = names
                .stream()
                .collect(Collectors.groupingBy(String::length, mapSupplier, Collectors.toSet()));
        collect.forEach((key, val) -> {
            for (String s : val) {
                System.out.println("key:" + key + "  val: " + s);
            }
        });
    }

    /**
     * collections 是 java util 下的类，是针对集合类的一个工具类，它提供一系列静态方法，主要实现对集合的：
     * 查找 排序 替换 线程安全转换，将非同步集合转换成同步 的
     *
     * collection 是 java util 下的接口，它是各种集合接口的父级接口，继承与它的集合主要由 Set 和 List
     * 提供关于集合的一些基本操作 ： 插入 删除 判断一个元素是否是其成员 ，遍历等
     *
     * collector的简单场景 一般在流处理完毕，想要收集对象的收尾工作；
     * 这个收集器主要是把流水线上的每个小部件 进行收集，并进行专项：
     *
     * https://blog.csdn.net/sl1992/article/details/99774223
     *
     * 关于我们的collector 的具体信息的解读，整体效果还算很ok的效果，不错
     *
     * https tcp ip 这两点是是必然会考，会问的话题
     *
     */
    public void partitioningBy(){

        Stream<Integer> grades=Stream.of(10,20,30,60,70,100);

        Map<Boolean,List<Integer>> group=grades.collect(Collectors.partitioningBy(x->x>=60));

        List<Integer> low=group.get(Boolean.TRUE);

        List<Integer> high=group.get(Boolean.FALSE);

        System.out.println(low);
        System.out.println(high);

    }

    /**
     * 先过滤再分组
     *
     * 先分组再过滤
     *
     */
    public void infoq(){

        Stream<Integer> numbers = Stream.of(1, 2, 3, 5, 5);

        Map<Integer,Long> result=numbers.filter(x-> x>3)
                .collect(Collectors.groupingBy(i-> i,Collectors.counting()));
        System.out.println(result);

        //java 9 的新特性.
//
//        Map<Integer,Long> temp=numbers.collect(Collectors.groupingBy(i->i,
//                Collectors.
//                ))
//
//

    }

    public void informationLog(){

    }

    /**
     *
     * 哪些情况会出发重新传递呢:
     *
     * 数据包丢失
     * 确认应答丢失
     *
     * TCP 针对丢包的情况：
     * 会使用 重传机制：
     * ；常见的重传机制：
     * 1.超时重传
     *    依赖这时间
     *
     * 2.快速重传
     *   连续的三个重复的ACK
     * 3.sack
     *   选择性重传
     * 4.d-sack
     *
     * d-sack 有这样的几个好处
     * 1.让发送发 明白：
     * 是发出去的包丢了
     * 还是接口回应的ack包丢了
     *
     */
    public void tcp(){

    }



}
