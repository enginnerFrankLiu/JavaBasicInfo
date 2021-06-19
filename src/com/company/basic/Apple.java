package com.company.basic;

/**
 * Comparable 是一个泛型接口
 * 也可以进行多从比较，调用父类的比较规则
 * 使用TreeSet和Tree Map
 *
 */
public  class Apple implements Comparable<Apple> {
    private String variety;
    private Color color;
    private int weight;

    @Override
    public int compareTo(Apple other) {
        if (this.weight < other.weight) {
            return -1;
        }
        if (this.weight == other.weight) {
            return 0;
        }
        return 1;
    }
}
