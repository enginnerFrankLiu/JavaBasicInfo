package com.company.basic;

public class Point {
    private final int x;
    private final int y;

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

    @Override
    public int hashCode() {
       return 1;
    }
}
