package com.company.basic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.ConsoleHandler;

@Pro(className = "com.company.basic.Demo1", methodName = "show")
public class ReflectTest {
    public void showAnnotation() throws Exception {

        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        Pro proAnnotation = reflectTestClass.getAnnotation(Pro.class);

        String className = proAnnotation.className();
        String methodName = proAnnotation.methodName();

        System.out.println(className);
        System.out.println(methodName);
        System.out.println("利用反射，和 jvm system.jvm.runtime. 机制来动态的加载类，生成类对象，然后调用实例对象的方法.");

        Class aClass = Class.forName(className);
        Object obj = aClass.newInstance();
        Method method = aClass.getMethod(methodName);
        //等价于调用obj上的方法
        //获取方法，让obj这个对象去call，当然也可以让其他对象传递进去，效果，整体还算是比较明显的哈
        //同样可以实现 com.company.basic.Demo2 的.
        method.invoke(obj);
    }

    public void testFoo() {
        Test t = AnnotationTest.class.getAnnotation(Test.class);
        System.out.println(t.value());
        TestMethod tm = null;
        try {
            tm = AnnotationTest.class.getDeclaredMethod("test", null).getAnnotation(TestMethod.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(tm.value());
    }

    /**
     * 至少你得要写上一边，一才能获取一定的熟悉程度啊
     * print all construcs
     * 总结：
     * public Constructor[] getConstructors()：所有"公有的"构造方法
     * public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
     * <p>
     * public Constructor getConstructor(Class… parameterTypes): 获取单个的"公有的"构造方法
     * public Constructor getDeclaredConstructor(Class…parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有
     */
    public void infoMD() {
        Class classInfo = Face.class;
        Constructor[] cons = classInfo.getDeclaredConstructors();
        for (Constructor con : cons) {
            System.out.println(con);
        }
    }

    /**
     * getField 只能获取public的，包括从父类继承来的字段。
     * getDeclaredField 可以获取本类所有的字段，包括private的，但是 不能获取继承来的字段
     */
    public void filedInfo() {
        Class classInfo = Face.class;
        Field[] fields = classInfo.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    /**
     * 文字字段信息；
     * 运行时动态的改变属性的值，整体看，效果还算是很ok的效果.
     */
    public void fieldInfo() {
        Face face = new Face();
        face.name = "jimi";
        System.out.println("old value:" + face.name);
        try {
            Class classInfo = face.getClass();
            Field field = classInfo.getField("name");
            field.set(face, "marsh");
            System.out.println("new value:" + face.name);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * get info about method
     * 1.public Method getMethod(String name ，Class<?>… parameterTypes):获取"公有方法"；（包含了父类的方法也包含Object类
     * 2.public Method getDeclaredMethods(String name ，Class<?>… parameterTypes) :获取成员方法，包括私有的(不包括继承的)
     *
     */
    public void methodInfo() {

        Class classInfo = Face.class;
        try {
            Method[] methods = classInfo.getMethods();
            System.out.println("-----------getMethods-----------");
            for (Method method : methods) {
                System.out.println(method);
            }
            System.out.println("-----------getDeclaredMethods-----------");
            Method[] declareMethods = classInfo.getDeclaredMethods();
            for (Method declareMethod : declareMethods) {
                System.out.println(declareMethod);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


}
