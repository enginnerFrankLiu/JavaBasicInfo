package com.company.jvm;

public class Son extends Father {
    static
    {
     System.out.println(" Son static code area....");
    }
    public Son() {
        System.out.println("Son construct....");
    }
}
