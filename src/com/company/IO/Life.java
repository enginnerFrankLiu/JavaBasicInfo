package com.company.IO;

import java.io.Serializable;

public class Life implements Serializable {

    private String name;
    private int age;
    public Life(String name,int age){
        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
