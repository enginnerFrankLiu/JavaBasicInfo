package com.company.model;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestoreAliases {
    private static Map<String, String> defaultAliases = new HashMap<String, String>();

    static {
        defaultAliases.put("Duke", "duke@sap.com");
        defaultAliases.put("Marsh", "marsh@sap.com");
    }

    public void exe() {
        try {
            Constructor contor = EmailAliases.class.getDeclaredConstructor(HashMap.class);
            contor.setAccessible(true);
            //用指定参数去构造类对象 利用反射.
            EmailAliases emailAliases = (EmailAliases) contor.newInstance(defaultAliases);
            emailAliases.printKeys();

        } catch (Exception exception) {

            System.out.println(exception);
        }
    }

    public void reflectInfo() {

        try {
            String name="marsh.liu";
            Class cla = Class.forName("com.company.model.Computer");
            Constructor contor=cla.getDeclaredConstructor(String.class);
            Computer computer=(Computer) contor.newInstance(name);
            System.out.println(computer.getName());

            System.out.println("-----get field value by reflect------");
            Field field=cla.getDeclaredField("name");
            field.setAccessible(true);
            Object val=field.get(computer);
            System.out.println(val);

            System.out.println("-----set field value by reflect-------");
            field.set(computer,"mimi.qi");
            System.out.println(computer.getName());

            System.out.println("------exe method by reflect-----------");
            Method method=cla.getDeclaredMethod("printInfo");
            method.invoke(computer);
            System.out.println("------over-----");

        } catch (Exception exception) {
            System.out.println(exception);
        }

    }

    public void foo1(Map<String,Son> map, List<String> list,String str){
        System.out.println("foo1");
    }

    public Map<Son,String> foo2(){
        System.out.println("foo2");
        return null;
    }

    public void foo3(){
        System.out.println("foo3");
    }

    public Integer foo4(){
        return 1;
    }

    /**
     * Type 是java 中所有类型的超级接口，包括:原类型 参数化类型 泛型数组类型 类型变量 基本类型
     * class 原类型与基本类型
     *  parameterizedType 参数化类型
     *  GenericArrayType  泛型数组类型
     *  TypeVariable 类型变量
     *  wildcard 通配符类型
     *
     *  都是为了我们的反射泛型这个话题二开展的
     *  Java 中的泛型仅仅是给编译器 javac 使用的，确保数据的安全性和免去强制类型转换的麻烦
     *  但是一旦编译完成，所
     * 有与泛型有关的类型全部擦除。 使用泛型直接读取泛型，是读取不到的，因为反射是操作加载以后的类的。
     */
    public void showInfo(){

        System.out.println("获取泛型参数列表");
        Class cla=RestoreAliases.class;
        try{
            System.out.println("---------获取参数的泛型类型---------");
            Method method=cla.getDeclaredMethod("foo1",Map.class,List.class,String.class);
            Type [] types=method.getGenericParameterTypes(); // 获取带泛型参数的类型
            System.out.println(types.length);
            for (Type type : types) {
                if(type instanceof ParameterizedType){
                    Type [] genericType =((ParameterizedType) type).getActualTypeArguments();

                    for (Type type1 : genericType) {
                        System.out.println(type1);
                    }

                }
            }

            System.out.println("--------- 获取泛型方法的返回值 -------------");
            Method method2= cla.getDeclaredMethod("foo2",null);
            Type returnType2=method2.getGenericReturnType();
            if(returnType2 instanceof ParameterizedType ){

                Type [] type2=((ParameterizedType)returnType2).getActualTypeArguments();
                for (Type type : type2) {
                    System.out.println("返回的泛型值类型:"+type);
                }
            }


            System.out.println("------获取五泛型的方法的返回值1------");
            Method method3=cla.getMethod("foo3");
            Type returnType=method3.getGenericReturnType();
            System.out.println(returnType instanceof  ParameterizedType);
            System.out.println("------获取五泛型的方法的返回值2------");
            Method method4=cla.getMethod("foo4");
            Type returnTypeJ=method4.getReturnType();
            System.out.println(returnTypeJ instanceof ParameterizedType);

        }catch (Exception exception){

        }

    }

    /**
     * 这个东西不是很好理解的
     * 1.undo log
     * 2.read view
     * mvcc
     * 如何实现 并发控制的 安全性的，
     * 正在一步一步的探究中.
     *
     */
    public void DBInfo(){

    }

    /**
     *
     *今天继续我们的mvvc，卧槽
     *
     *
     */
    public void mvvcInMySql(){

    }

    /**
     * IO 多路复用
     * https://mp.weixin.qq.com/s/JHqVY02mMJIpuZ4s9XOrVg
     */
    public void IOMulitUsed(){


    }

}
