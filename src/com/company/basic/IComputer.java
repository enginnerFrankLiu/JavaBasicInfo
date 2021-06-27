package com.company.basic;

/**
 * 接口中的域 自动的被申明为:
 * public static final
 * 因为使用抽象类表示通用属性存在这样的问题：每个类只能扩展于一个类
 *
 * 在标准库中，有成对出现的接口和实用工具类，如Collection/Collections或Path/Paths。
 *
 * 但是  实现自己的接口时 不需要为实现工具方法另外提供伴随类.
 *
 *
 */
public interface IComputer {
     int version=12121212;

     void doCal();

//     void printInfo(String name);

}
