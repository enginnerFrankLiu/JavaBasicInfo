package com.company.basic;

public class Point {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private  int x;
    private  int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }




    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point))
            return false;
        Point otherObj = (Point) obj;
        return otherObj.x == this.x && otherObj.y == this.y;
    }
}
