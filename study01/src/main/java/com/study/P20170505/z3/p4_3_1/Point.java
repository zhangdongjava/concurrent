package com.study.P20170505.z3.p4_3_1;

/**
 * Created by zd on 2017/5/5.
 */
public class Point {

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        Runnable r = () -> {
        };
    }
}
