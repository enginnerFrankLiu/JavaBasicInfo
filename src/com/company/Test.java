package com.company;

public class Test extends Thread {

    private String name;

    Test(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 60; i++) {
            System.out.println(name + "---" + i);
        }
    }
}
