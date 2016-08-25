package com.zzz.concurrent;

/**
 * Created by dell_2 on 2016/8/23.
 */
public class Test1 {
    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);

            }
        }).start();
    }







}
