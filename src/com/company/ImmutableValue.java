package com.company;

public class ImmutableValue {

    private int val=0;
    public ImmutableValue(int val){
        this.val=val;
    }

    public int getValue(){
        return this.val;
    }

    /**
     * 不可变对象的值，一旦创建后无法改变；
     * 对这种对象的改变是；
     * 获取他的值(getVal())-> 然后进行计算->然后返回一个新的对象；
     * 就不会存在线程安全的问题；
     * 但是也就不存在，线程直接的通信和安全了
     *
     * 你这样去理解，那么我们的对象就是天生的线程安全；
     * 因为他的"不可变性"
     *
     * 而我们的叠加操作，就必须控制线程安全的"操作"
     *
     * 而想对inmuable 对象的操作：不是在原来的数值基础上进行修改；
     * 而是copy一份值过来，然后进行新的计算，计算新的结果后，返回一个
     * 新的对象.
     *
     * why string is designed as immutable.
     *
     * 1.thread safe, no need to do sync.
     *
     * 2.can caching it is hashcode. avoid re calculate it is hash code.
     *
     * 3.benefit for hash table,make it element is no duplicate.
     *
     * 4.save memory
     *
     *  string a="abc";
     *  string b="abc";
     *  string c="abc";
     *
     *  there just one data existed in string pool.
     *  When a string is created and if the string already exists in the pool, the reference of the existing string will be returned, instead of creating a new object and returning its reference.
     *
     *
     * Immutable objects are naturally thread-safe
     *
     * immutable objects are always thread-safe,but it reference may not be.
     *
     *
     */
    public ImmutableValue add(int num){
        return new ImmutableValue(this.val+num);
    }
}
