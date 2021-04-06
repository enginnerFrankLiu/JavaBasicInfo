package com.company.model;

import java.io.Serializable;

public class Human implements Serializable {

    private static final long serialVersionUID = 8294180014912103005L;

    private static String userName;
    private transient String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        Human.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
