package com.study.P20170505.z3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zd on 2017/5/5.
 */
public class _5NotSafePublish {
    //不安全发布 可见性问题 可能其他线程看见未完全构造
    public Holder holder;

    public void initialize() {
        ConcurrentLinkedQueue queue;
        holder = new Holder(42);
    }

    public static class Holder {

        private volatile int num;

        public Holder(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void assertSanity() {
            if (num != num) {
                throw new AssertionError("this statement is false");
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        _5NotSafePublish notSafePublish = new _5NotSafePublish();
        notSafePublish.initialize();
        Runnable runnable = () -> {
            while (true) {
                notSafePublish.holder.assertSanity();
            }
        };
        for (int i = 0; i < 10; i++) {
            service.submit(runnable);

        }
        Random random = new Random();
        while (true) {
            notSafePublish.holder.setNum(random.nextInt(50));
        }

    }
}
