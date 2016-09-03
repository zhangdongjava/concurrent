package synchronizedTest;

import java.util.concurrent.TimeUnit;

/**
 * Created by dell_2 on 2016/9/3.
 */
public class Consumer implements Runnable {
    @Override
    public void run() {

        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) ((500)*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (Test.count == 0) {
                System.out.println("货物没了等待生产货物 !");
                synchronized (Test.lock) {
                    await();
                }
            }
            System.out.println("开始消费货物!");
            synchronized (Test.lock){
                Test.count --;
                Test.lock.notifyAll();
            }
        }
    }

    public void await() {
        try {
            Test.lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
