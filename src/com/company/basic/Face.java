package com.company.basic;

import java.util.HashMap;
import java.util.Map;


public class Face {

    public String name;
    protected String address;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Face() {
        System.out.println("public no params cons.");
    }

    Face(String eyes) {
        System.out.println("no params....");
    }

    public Face(int i) {
        System.out.println(i);
    }

    protected Face(int i, int j) {
        System.out.println("i and j");
    }

    private Face(String name, int age, String address) {
        System.out.println("name ,age ,address.");
    }

    /**
     *
     * @param name
     */
    public void showName(String name){
        System.out.println("print name:"+name);
    }


    /**
     * 两个相等的实例，p1 p2 具有不同的散列码
     * 然后实际的约定是；相同的对象 必须具备相同的 hashcode，所以覆写equals方法的同时 必须覆写我们的hashcode方法！
     *
     * hashcode 存在的意义：
     * 主要用于提高容器查找和存储的快捷性，如：hashset hashtable hashmap等等；
     *
     * 1.调用 equals 返回 true 的两个对象必须具有相等的哈希码。
     * 2.如果两个对象的 hashCode 返回值相同，调用它们 equals 方法不一返回 true 。
     * 反过来推 ；就不合逻辑了
     *
     * add link:https://juejin.cn/post/6844903587764518920
     *
     * 1.能够将数据压缩
     * 2.针对同一个输入具有唯一输出
     * 3.存在数据冲突（碰撞）
     *
     * 理想的情况是：
     * 散列函数应该把集合中不相等的实例均匀的腹部到所有的int值上
     * 理想的情况是：散列函数应该把集合中不相等的实例均匀的分布到所有可能的int值上
     *
     * 最近的目标就是；看完object 中原生对象提供的各种方法
     *
     *
     *
     */
    public void showInfo(){

        Point p0 = new Point(1, 2);
        Point p1 = new Point(2, 1);
        Point p2 = new Point(2, 1);

        Map<Point, String> ps = new HashMap();

        ps.put(p0, "p0");
        ps.put(p1, "p1");
        ps.put(p2, "p2");
        System.out.println("---------------------------------");

        System.out.println(p0.hashCode());
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        String info = ps.get(p1);
        System.out.println(info);

        Object obj=new Object();

    }
}
