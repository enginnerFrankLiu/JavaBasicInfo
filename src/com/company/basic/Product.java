package com.company.basic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Product implements Comparable<Product> {
    private int id;

    private String name;

    private Point point;

    public  Product(){

    }
    public Product(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(Point point){
        this.point=point;
    }

    public Point getPoint(){
        return this.point;
    }

    /**
     * 如果直接的进行这样的clone，将会是一个浅拷贝
     * 因为该类中的引用字段 和 克隆对象 中的引用字段，保持一个引用
     * @return
     */
    @Override
    protected Product clone() {
        try {
            //如果，直接的进行这样clone，其实她是
            return (Product) super.clone();
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

    public Product shallowClone(){
        return this.clone();
    }

    public Product deepClone(){
        return null;
    }

    /**
     *
     * 1.小于 -1
     * 2.等于 0
     * 3.大于 1
     *
     * 按照这样的一个规则进行的比较；
     * 比较的结果也无非就这三种吧了，整体效果还算是比较好的啦
     *
     * 自定义对象的对等比较；
     * tostring
     * clone
     * compareto方法
     *
     * Arrays类中的sort 但前提是必须实现:Comparable
     * 这里允许存在多台从具有较高通用性的接口到较高专用性的接口的链。
     主要原因是Java是一种强类型（strongly type）语言，在调用方法时，编译器将会检查这个方法是否存在。

     * @param o
     * @return
     */
    @Override
    public int compareTo(Product o) {
        if(this.getId()==o.getId()){
            return 0;
        }else if(this.getId()>o.getId()){
            return 1;
        }else{
            return-1;
        }
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Product)){
            return false;
        }else{
            Product otherProduct=(Product)obj;
            return this.getId()==otherProduct.getId();
        }
    }

    /**
     * 内部类
     * 匿名类
     * 主要还是为我们的lamada 做铺垫使用的啊；
     */
    public class coreEntity implements ActionListener {
        public void actionPerformed(ActionEvent event){
            System.out.println(new Product(1,"apple").getName());
            System.out.println(Product.this.getName());
        }
    }

    public void showInfo(){
        new coreEntity().actionPerformed(null);
    }
}
