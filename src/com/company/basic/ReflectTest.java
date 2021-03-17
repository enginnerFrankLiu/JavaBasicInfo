package com.company.basic;

import java.lang.reflect.Method;

@Pro(className ="com.company.basic.Demo1",methodName = "show")
public class ReflectTest {
    public void showAnnotation() throws Exception{

        Class<ReflectTest> reflectTestClass=ReflectTest.class;
        Pro proAnnotation=reflectTestClass.getAnnotation(Pro.class);

        String className=proAnnotation.className();
        String methodName=proAnnotation.methodName();

        System.out.println(className);
        System.out.println(methodName);
        System.out.println("利用反射，和 jvm system.jvm.runtime. 机制来动态的加载类，生成类对象，然后调用实例对象的方法.");

        Class aClass=Class.forName(className);
        Object obj=aClass.newInstance();
        Method method=aClass.getMethod(methodName);
        //等价于调用obj上的方法
        //获取方法，让obj这个对象去call，当然也可以让其他对象传递进去，效果，整体还算是比较明显的哈
        //同样可以实现 com.company.basic.Demo2 的.
        method.invoke(obj);

    }
}
