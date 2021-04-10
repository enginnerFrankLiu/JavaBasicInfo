package com.company.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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

    /**
     * Type 是java 中所有类型的超级接口，包括:原类型 参数化类型 泛型数组类型 类型变量 基本类型
     * class 原类型与基本类型
     *  parameterizedType 参数化类型
     *  GenericArrayType  泛型数组类型
     *  TypeVariable 类型变量
     *  wildcard 通配符类型
     */
    public void showInfo(){

        System.out.println("获取泛型参数列表");
        Class cla=RestoreAliases.class;
        try{
            Method method=cla.getDeclaredMethod("foo1",Map.class,List.class,String.class);
            Type [] types=method.getGenericParameterTypes(); // 获取带泛型参数的类型


        }catch (Exception exception){

        }




    }
}
