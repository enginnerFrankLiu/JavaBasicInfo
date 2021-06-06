package com.company.basic;

public class Color {
    private double colorNumber;

    public Color(double colorNumber){
        this.colorNumber=colorNumber;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Color))
            return false;
        Color otherObj=(Color)obj;
        return this.colorNumber==otherObj.colorNumber;
    }

}
