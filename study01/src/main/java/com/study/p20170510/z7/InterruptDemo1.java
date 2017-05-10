package com.study.p20170510.z7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 由于依赖join 不知道是正常完成还是因为join超时
 *
 * Created by zd on 2017/5/10.
 */
public class InterruptDemo1 {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public static void timeRun(Runnable runnable, TimeUnit unit, long timeout) throws InterruptedException {
        class RunThread implements Runnable {

            private volatile Throwable throwable;

            @Override
            public void run() {
                try {
                    runnable.run();
                } catch (Throwable t) {
                    this.throwable = t;
                }
            }

            public void rethrow() {
                if (throwable != null) {
                    throw new RuntimeException(throwable.getCause());
                }
            }
        }

        RunThread task = new RunThread();
        Thread thread = new Thread(task);
        thread.start();
        executorService.schedule(() -> {
            thread.interrupt();
            System.out.println("中断了！");
        }, timeout, unit);
        executorService.shutdown();
        thread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    public static void main(String[] args) throws InterruptedException {
        long curr = System.currentTimeMillis();
        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted())
                System.out.println(System.currentTimeMillis() - curr);
        };
        timeRun(r, TimeUnit.SECONDS, 1);
    }
}













