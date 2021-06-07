package com.company.basic;

/**
 * 这个就是典型的“组合大于优化的概念和实现”
 * “通过添加新组件的方式来进行扩展 而不是通过继承的方式”
 *
 * 这里还有另外一种实现方式：
 * 你可以在一个抽象的子类中增加新的组件且不违反equals约定；
 * 原则：只要不可能创建超类的实例前面提到过的问题都不会发生.
 *
 * 可变对象在不同的时候可以与不同的对象相等 而不可变对象则不会这样
 * 
 */
public class ColorPoint {
    private final Point point;
    private final Color color;

    public ColorPoint(Point p, Color c) {
        this.point = p;
        this.color = c;
    }

    public Point asPoint() {
        return this.point;
    }

    /**
     *在override equal 方法时的约定 原则 和注意事项
     * 1.自反性 contains()
     * 2.对称性 ：不同类的对象之间的比较 objClassA.equals(objClassB) 例如:String 和 caseInsensitiveString 对象的实现方式
     * 3.传递性: objA.equals(objB) objB.equals(objC) 那么 objA.equals(objC) 可以做出这样的推断, 这个主要的矛盾主要发生在继承类之间实例化对象的比较，所有在进行拓展的时候:-> 组合优于继承的方式来实现
     * 4.一致性:可变对象在不同的时候 可以与不同的对象相等，而不可变的对象则不会这样，
     *
     *
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof ColorPoint))
            return false;
        ColorPoint otherPoint = (ColorPoint) obj;
        return otherPoint.equals(this.point) && otherPoint.color.equals(this.color);
    }
}
