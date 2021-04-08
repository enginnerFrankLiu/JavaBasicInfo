package com.company;

import com.company.model.Son;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ExceptionInfo {

    /**
     * 考虑，是否因为某个异常的抛出，决定我们是否要继续处理接下来的任务
     * 还是终止整个application呢?
     * 是否异常有可能恢复，或者尝试去恢复呢
     * <p>
     * 利用java反射机制，可以在运行期间调用对象的任何方法；
     * 在运行期间调用任何对象的方法；
     * <p>
     * 那么由此可以获取的类，只有两个类，我真的是醉.
     * https://zhuanlan.zhihu.com/p/21423208.
     */
    public void infoJ() {

        //可以在运行期间调用对象的任何方法;
        Society so = new Society();
        try {
            Method method = so.getClass().getDeclaredMethod("main");
            method.invoke(so);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void getSuperClassInfo() {
        Son son = new Son();
        Class cla = son.getClass().getSuperclass();
        while (cla != null) {
            System.out.println(cla.getName());
            cla = cla.getSuperclass();
        }
    }

    /**
     * get a lot information about class.
     * 写这些垃圾代码，卧槽
     */
    public void getInterface() {
        Son son = new Son();
        Class[] interfaceInfos = son.getClass().getInterfaces();
        for (Class interfaceInfo : interfaceInfos) {
            System.out.println(interfaceInfo.getName());
        }
    }

    /**
     * get all method info of something.
     */
    public void getAllMethodOfClass() {
        Son son = new Son();
        Method[] methods = son.getClass().getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    /**
     * 如果方法有参数，你就要指定参数.
     */
    public void getDeclareMethodInfo() {

        Son son = new Son();
        Class cla = son.getClass();
        try {
//            Method  method=cla.getDeclaredMethod("getMoney",int.class,int.class,String.class);
//
//          System.out.println(" is private method: "+ Modifier.isPrivate(method.getModifiers()));  ;
//
//            System.out.println(method.isAccessible());
//            method.setAccessible(true);
//            System.out.println(method.isAccessible());
//            //返回值
//            System.out.println("The type of method return:");
//            Class returnType=method.getReturnType();
//            System.out.println(returnType.getName());
//
//            //参数类型信息
//            System.out.println("get parameters type of method:");
//            Class [] paramsTypeInfo=method.getParameterTypes();
//            for (Class aClass : paramsTypeInfo) {
//                System.out.println(aClass.getName());
//            }
//
//            //参数信息
//            System.out.println("get parameters of method:");
//            Parameter [] paramsInfo= method.getParameters();
//            for (Parameter parameter : paramsInfo) {
//                System.out.println(parameter.getName());
//            }
//            System.out.println("--------------------------------");
//            System.out.println("call this method....");
//
//           Object obj=method.invoke(son,1,2,"mina");
//           System.out.println(obj);
//
//
//           System.out.println("---------------------------");
//           System.out.println("show all file info:");
//           //getFields get all public filed information to show something.
//            Field [] fields=cla.getFields();
//            for (Field field : fields) {
//                System.out.println(field.getName());
//            }
//
//            //这样我们的操作就可以进行实现了滴，整体效果还算是比较ok的.
//            System.out.println("--------------set property value------------------");
//            Field field=cla.getDeclaredField("name");
//            field.setAccessible(true);
//            field.set(son,"marsh");
//            System.out.println(son.getName());


            System.out.println("---------get all public method------------");
            Method[] methods = cla.getMethods();

            //包括继承和重载的各种方法，整体效果，体现不错的啊.
            for (Method method : methods) {
                System.out.println(method.getName());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * three different way to get class info.
     * 写了这么多，他就是在运行时
     * 1.获取类的哥各种信息: 类基本信息，类名，包名，是否有继承，继承的父类，实现的接口
     * 获取构造函数的信息 构造函数的参数，信息，并且可以动态的利用构造函数生成对象.
     * 获取各种字段的信息 包括各种共有，私有，并且可以改变其中的访问修饰符，动态的设置 或者 获取 字段的value.
     * 获取各种方法的信息，包括方法的参数，返回类型，并且可动态的调用改method
     * 这个就是对java reflect 的一个基本总结.
     */
    public void showInfo() {
        Class cla0 = Son.class;
        Class cla1 = new Son().getClass();
        try {
            Class cla2 = Class.forName("com.company.model.Son");
            System.out.println(cla2.getName());
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * array info..
     *
     * 某些xx class 的本质就是数组；
     * 没什么好特别的；
     *
     * 如：ByteBuffer
     * 感觉还不错的
     * 文档是这个意思
     *
     */
    public void arrayInfo() {

        int[] ints = new int[3];
        ints[0] = 0;
        ints[1] = 0;
        ints[2] = 0;

        Class cla = ints.getClass();
        if (cla.isArray()) {
            System.out.println(" ok...");
        } else {
            System.out.println("not ok..");
        }

        try {

            Class cls = Class.forName("java.nio.ByteBuffer");
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                Class c = field.getType();
                if (c.isArray()) {
                    System.out.format("%s%n"
                                    + "       Field: %s%n"
                                    + "       Type : %s%n"
                                    + "      Component Type:%s%n",
                            field, field.getName(), c, c.getName()

                    ).println();
                }
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
