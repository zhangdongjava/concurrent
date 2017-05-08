package com.study.p20170506.z5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多个线程调用 可能会计算 相同的值
 * Created by zd on 2017/5/8.
 */
public class CachaeDemo3 {

    public interface Computable<A, V> {
        V compute(A arg) throws ExecutionException, InterruptedException;
    }

    public class ExpensiveFunction implements Computable<String, Integer> {

        @Override
        public Integer compute(String arg) {
            //经过了大量计算
            return Integer.valueOf(arg);
        }
    }

    public class Memoizerl<A, V> implements Computable<A, V> {

        private final Map<A, FutureTask<V>> cache = new ConcurrentHashMap<>();

        private final Computable<A, V> c;

        public Memoizerl(Computable<A, V> c) {
            this.c = c;
        }

        @Override
        public V compute(A arg) throws ExecutionException, InterruptedException {
            FutureTask<V> futureTask = cache.get(arg);
            if (futureTask == null) {
                futureTask = new FutureTask(() -> c.compute(arg));
                cache.put(arg, futureTask);
                futureTask.run();
            }
            return futureTask.get();
        }
    }
}
