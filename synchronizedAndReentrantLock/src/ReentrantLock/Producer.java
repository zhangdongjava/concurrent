package ReentrantLock;

import java.util.concurrent.TimeUnit;

/**
 * 生产着
 * Created by dell_2 on 2016/9/2.
 */
public class Producer implements Runnable {


    @Override
    public  void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep((long) ((500)*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (Test.count>= Test.MAX_NUM){
                System.out.println("货物满了开始等待 !");
                synchronized (Test.lock){
                    await();
                }
            }
            System.out.println("开始生产货物!");
            synchronized (Test.lock){
                Test.count ++;
                Test.lock.notifyAll();
            }
        }
    }

    public void await(){
        try {
            Test.lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
