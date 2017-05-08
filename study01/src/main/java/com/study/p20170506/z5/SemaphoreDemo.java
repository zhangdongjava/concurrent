package com.study.p20170506.z5;

import java.util.concurrent.Semaphore;

/**
 * Created by zd on 2017/5/8.
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        semaphore.release();
        semaphore.release();
        semaphore.release();
        semaphore.release();
        for (int i = 0; i <100; i++) {
            semaphore.acquire();
            System.out.println(i+1);

        }
    }
}
