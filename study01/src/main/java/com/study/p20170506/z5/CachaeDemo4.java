package com.study.p20170506.z5;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by zd on 2017/5/8.
 */
public class CachaeDemo4 {

    public interface Computable<A, V> {
        V compute(A arg) throws ExecutionException, InterruptedException;
    }

    public static class ExpensiveFunction implements Computable<String, Integer> {

        @Override
        public Integer compute(String arg) {
            //经过了大量计算
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(arg);
        }
    }

    public static class Memoizerl<A, V> implements Computable<A, V> {

        private final Map<A, FutureTask<V>> cache = new ConcurrentHashMap<>();

        private final Computable<A, V> c;

        public Memoizerl(Computable<A, V> c) {
            this.c = c;
        }

        @Override
        public V compute(A arg) throws InterruptedException {
            FutureTask<V> futureTask = cache.get(arg);
            if (futureTask == null) {
                futureTask = new FutureTask(() -> c.compute(arg));
                //防止相覆盖添加
                FutureTask<V> f = cache.putIfAbsent(arg, futureTask);
                //没有添加过才执行运行
                if (f == null) {
                    futureTask.run();
                }
            }
            try {
                return futureTask.get();
                //取消执行要移除缓存
            } catch (CancellationException e) {
                cache.remove(arg);
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Memoizerl memoizerl =   new Memoizerl<>(new ExpensiveFunction());
        System.out.println(memoizerl.compute("233"));
        System.out.println(memoizerl.compute("233"));
        System.out.println(memoizerl.compute("233"));
        System.out.println(memoizerl.compute("232"));
        System.out.println(memoizerl.compute("233"));
        System.out.println(memoizerl.compute("232"));
    }
}
