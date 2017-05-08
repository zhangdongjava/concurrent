package com.study.p20170506.z5;

import java.util.HashMap;
import java.util.Map;

/**
 * 不推荐这种缓存 同步了整个方法有明显的伸缩性问题
 * Created by zd on 2017/5/8.
 */
public class CachaeDemo {

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

        private final Map<A, V> cache = new HashMap<>();

        private final Computable<A, V> c;

        public Memoizerl(Computable<A, V> c) {
            this.c = c;
        }

        @Override
        public synchronized V compute(A arg) {
            V val = cache.get(arg);
            if (val == null) {
                val = c.compute(arg);
                cache.put(arg, val);
            }
            return val;
        }
    }
}
