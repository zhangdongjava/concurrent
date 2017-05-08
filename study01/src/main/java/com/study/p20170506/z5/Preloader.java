package com.study.p20170506.z5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zd on 2017/5/8.
 */
public class Preloader {

    private FutureTask<String> futureTask = new FutureTask<>(() -> {
        if (true)
            throw new RuntimeException("1111");
        return "6666";
    });
    private final Thread thread = new Thread(futureTask);

    public void start() throws ExecutionException, InterruptedException {
        thread.start();
        System.out.println(futureTask.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Preloader().start();
    }
}
