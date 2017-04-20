package com.zzz.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by dell_2 on 2016/8/23.
 */
public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future future = threadPool.submit(() -> {
            List<String> list = new ArrayList<>(10);
            list.add("111");
            list.add("222");
            list.add("333");
            list.stream().forEach(System.out::println);
            return list.size();
        });

        threadPool.shutdown();
        System.out.println(future.get());
    }


}
