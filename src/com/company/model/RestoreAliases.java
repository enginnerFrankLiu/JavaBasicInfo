package com.company.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
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
}
