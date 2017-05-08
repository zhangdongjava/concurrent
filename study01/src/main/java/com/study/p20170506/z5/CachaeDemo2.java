package com.study.p20170506.z5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多个线程调用可能会计算到相同的值
 * Created by zd on 2017/5/8.
 */
public class CachaeDemo2 {

    public interface Computable<A, V> {
        V compute(A arg);
    }

    public class ExpensiveFunction implements Computable<String, Integer> {

        @Override
        public Integer compute(String arg) {
            //经过了大量计算
            return Integer.valueOf(arg);
        }
    }

    public class Memoizerl<A, V> implements Computable<A, V> {

        private final Map<A, V> cache = new ConcurrentHashMap<A, V>();

        private final Computable<A, V> c;

        public Memoizerl(Computable<A, V> c) {
            this.c = c;
        }

        @Override
        public V compute(A arg) {
            V val = cache.get(arg);
            if (val == null) {
                val = c.compute(arg);
                cache.put(arg, val);
            }
            return val;
        }
    }
}
