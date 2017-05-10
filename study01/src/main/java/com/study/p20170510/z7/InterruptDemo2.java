package com.study.p20170510.z7;

import java.util.concurrent.*;

/**
 * 由于依赖join 不知道是正常完成还是因为join超时
 * <p>
 * Created by zd on 2017/5/10.
 */
public class InterruptDemo2 {

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    public static void timeRun(Runnable runnable, TimeUnit unit, long timeout) throws InterruptedException {
        Future<?> f = executorService.submit(runnable);
        executorService.shutdown();
        try {
            f.get(timeout, unit);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        } catch (TimeoutException e) {
            System.out.println("超时了 !");
        } finally {
            System.out.println("取消任务!");
//            if(runnable instanceof ReaderThread){
//                ((ReaderThread)runnable).interrupt();
//            }
            f.cancel(true);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long curr = System.currentTimeMillis();
        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(System.currentTimeMillis() - curr);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("线程被中断了!");
                    System.out.println(Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }

        };
        timeRun(r, TimeUnit.SECONDS, 1);
    }
}













