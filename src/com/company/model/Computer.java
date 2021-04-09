package com.company.model;

public class Computer {
    private String version = "998";
    private String name;

    public Computer(String name){
        this.name=name;
    }
    public String getName() {
        return this.name;
    }
    public void printInfo() {
        System.out.format("This version:%s name:%s", this.version, this.name);
    }
}
