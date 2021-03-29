package com.company.basic;

public class Face {

    public String name;
    protected String address;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Face() {
        System.out.println("public no params cons.");
    }

    Face(String eyes) {
        System.out.println("no params....");
    }

    public Face(int i) {
        System.out.println(i);
    }

    protected Face(int i, int j) {
        System.out.println("i and j");
    }

    private Face(String name, int age, String address) {
        System.out.println("name ,age ,address.");
    }

    /**
     *
     * @param name
     */
    public void showName(String name){
        System.out.println("print name:"+name);
    }
}
