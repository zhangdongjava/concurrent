package com.study.p20170504.z3;

/**
 * 可能程序不会终止
 * Created by zd on 2017/5/4.
 */
public class NoVisibility {
    private static boolean ready;
    private static int num;

    public static void main(String[] args) {
        Runnable r = () -> {
            while (!ready)
                Thread.yield();
            System.out.println(num);
        };
        new Thread(r).start();
        num = 42;
        ready = true;
    }
}
