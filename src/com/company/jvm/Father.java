package com.company.jvm;

public class Father {
    static
    {
      System.out.println("father static code area..");
    }

    public static int age=88;

    public Father(){
        System.out.println(" father construct..");
    }
}
