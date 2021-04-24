package com.company;

import com.company.model.Son;
import sun.awt.SunHints;

import java.io.Console;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.time.temporal.ValueRange;
import java.util.Arrays;

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
     * <p>
     * 某些xx class 的本质就是数组；
     * 没什么好特别的；
     * <p>
     * 如：ByteBuffer
     * 感觉还不错的
     * 文档是这个意思
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
    /**
     *
     * add link:https://docs.oracle.com/javase/tutorial/reflect/member/ctorInstance.html
     *
     */
    public void dynamicCreateArray() {
        try {
            Class integerElement = Class.forName("java.math.BigInteger");
            int arrLen = 3;
            Object dynamicArray = Array.newInstance(integerElement, arrLen);
            for (int i = 0; i < 3; i++) {
                String val = String.valueOf(i * i);

                //获取String 类型的构造函数 来初始化其中的值.
                Constructor constructor = integerElement.getConstructor(String.class);
                //构造元素
                Object element = constructor.newInstance(val);

                //往动态构建的数组中，set值.
                Array.set(dynamicArray, i, element);
            }
            Object[] results = (Object[]) dynamicArray;
            System.out.println(Arrays.toString(results));

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * as the documents mention,there are two way to create instance by constructor call
     */
    public void construct(){

        Constructor [] constructors= Console.class.getDeclaredConstructors();
        Constructor constructor=null;
        for(int i=0;i<constructors.length;i++){
            constructor=constructors[i];
            //获取默认的构造函数；就是构造函数的参数为null时候，然后生成默认的对象，然后获取他的默认值.
            if(constructor.getGenericParameterTypes().length==0){
                System.out.println(Arrays.toString(constructor.getGenericParameterTypes()));
                break;
            }
        }
        try{
            constructor.setAccessible(true);
            Console c=(Console)constructor.newInstance();
            Field f=c.getClass().getDeclaredField("cs");
            f.setAccessible(true);
            //获取指定对象的值
            System.out.println(f.get(c));
            System.out.println(Charset.defaultCharset());

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     *
     *
     * getFields() 方法获取的是这个类的public属性，包括父类，
     * getDelaredFields() 能够获取到的在本身类中的 所有field，无视权限，但看不到父类.
     *
     * java 中的泛型操作.
     *
     * java 中的泛型仅仅是给编译器javac 使用的，确保数据的安全性和免去强制类型转换的麻烦，一旦编译生成，
     * 所有与泛型先关的类型全部擦除，使用泛型直接读取泛型，是读取不到的，因为反射操作是加载以后的类；
     *
     * ka;
     *
     */
    public void sum(){

    }

    /**
     * 今天去参加公开课堂了
     */
    public void english_class(){

    }

    /**
     * information tech
     */
    public void informationForSomeOne(){

    }


}
