package com.company.model;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class RestoreAliases {
    private static Map<String,String> defaultAliases=new HashMap<String,String>();
    static {
        defaultAliases.put("Duke","duke@sap.com");
        defaultAliases.put("Marsh","marsh@sap.com");
    }

    public void exe(){
        try{
            Constructor contor=EmailAliases.class.getDeclaredConstructor(HashMap.class);
            contor.setAccessible(true);
            //用指定参数去构造类对象 利用反射.
            EmailAliases emailAliases=(EmailAliases)contor.newInstance(defaultAliases);
            emailAliases.printKeys();

        }catch (Exception exception){

            System.out.println(exception);
        }
    }
}
