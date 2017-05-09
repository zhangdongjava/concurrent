package com.study.p20170506.z5;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd on 2017/5/9.
 */
public class DelayQueueDemo {

    public static class DelayBean implements Delayed {

        private int delay;

        public DelayBean(int delay) {
            this.delay = delay;
        }

        public int getDelay() {
            return delay;
        }

        public void setDelay(int delay) {
            this.delay = delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            try {
                long slp = (long)delay * 1000000;
                unit.sleep(slp);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            DelayBean o1 = (DelayBean) o;
            return this.delay - o1.getDelay();
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("DelayBean{");
            sb.append("delay=").append(delay);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayBean> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayBean(10));
        delayQueue.put(new DelayBean(100));
        delayQueue.put(new DelayBean(1000));
        delayQueue.put(new DelayBean(3000));
        long s = System.currentTimeMillis();
        while (!delayQueue.isEmpty()) {
            if(delayQueue.element().delay>2000){
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.print(delayQueue.take());
            System.out.println("--->"+(System.currentTimeMillis()-s));
        }
    }

}
