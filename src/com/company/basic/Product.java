package com.company.basic;

public class Product implements Comparable<Product> {

    private int id;

    private String name;

    private Point point;

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
     *
     *
     *
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Product o) {
//        if(this.getId()==o.getId()){
//            return 0;
//        }else if(this.getId()>o.getId()){
//            return 1;
//        }else{
//            return-1;
//        }

        return this.getId()-o.getId();
    }
}
