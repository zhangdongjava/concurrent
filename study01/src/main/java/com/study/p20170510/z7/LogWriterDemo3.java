package com.study.p20170510.z7;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 不支持关闭的生产者-消费者日志服务
 * Created by zd on 2017/5/10.
 */
public class LogWriterDemo3 {

    private volatile boolean shutDown;

    private BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    private final LoggerThread loggerThread;

    private long msgCount;

    private final Thread runThread;

    public LogWriterDemo3(Writer writer) {
        loggerThread = new LoggerThread(writer);
        runThread = new Thread(loggerThread);
    }

    /**
     * 不安全 因为判断和添加不是原子操作
     *
     * @param log
     * @throws InterruptedException
     */
    public void log(String log) throws InterruptedException {
        synchronized (this) {
            if (!shutDown) {
                msgCount++;
            } else {
                throw new IllegalArgumentException("is shutdown");
            }

        }
        queue.put(log);
    }

    public void stop() {
        shutDown = true;
        loggerThread.interrupt();
    }


    public class LoggerThread extends Thread {
        private Writer writer;

        public LoggerThread(Writer writer) {
            this.writer = writer;
        }


        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (LogWriterDemo3.this) {
                        if (msgCount == 0 && shutDown) {
                            break;
                        }
                    }
                    writer.write(queue.take());
                    synchronized (LogWriterDemo3.this) {
                        --msgCount;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
