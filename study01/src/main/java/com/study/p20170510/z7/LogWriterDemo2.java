package com.study.p20170510.z7;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 不支持关闭的生产者-消费者日志服务
 * Created by zd on 2017/5/10.
 */
public class LogWriterDemo2 {

    private volatile  boolean shutDown;

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    private final LoggerThread loggerThread;

    private final Thread runThread;

    public LogWriterDemo2(Writer writer) {
        loggerThread = new LoggerThread(writer);
        runThread = new Thread(loggerThread);
    }

    /**
     * 不安全 因为判断和添加不是原子操作
     * @param log
     * @throws InterruptedException
     */
    public void log(String log) throws InterruptedException {
        if(!shutDown){
            queue.put(log);
        }else{
            throw new IllegalArgumentException("is shutdown");
        }
    }


    public class LoggerThread implements Runnable {
        private Writer writer;

        public LoggerThread(Writer writer) {
            this.writer = writer;
        }


        @Override
        public void run() {
            try {
                while (true){
                    writer.write(queue.take());
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
