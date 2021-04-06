package com.company;

import java.lang.reflect.Constructor;

/**
 *反射的第一个作用就是：动态的获取运行时类的各种信息；
 * 类 方法 接口 属性，等等
 * 2.第二个作用就是动态的去改变调用类中的各种方法其中各种数据和值.
 *
 */
public class mqRefect {

    public void refectInfo() {
        try {
            Class c = Class.forName("com.company.mq");
            Constructor con = c.getConstructor(char.class);
            con.setAccessible(true);
            Object obj = con.newInstance('f');
            mq m = (mq) obj;
            System.out.println(m.getCc());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * info
     */
    public void threeWayToGetClass() {
        mq m = new mq();
        Class classInfo = m.getClass();
        System.out.println(classInfo.getName());

        Class cInfo = mq.class;
        System.out.println(cInfo.getName());

        try {
            Class mqClassInfo = Class.forName("com.company.mq");
            System.out.println(mqClassInfo.getName());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 动态的获取方法信息，并且调用其中地方
     */
    public void methodInfo(){

    }

}
