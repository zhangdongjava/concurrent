package synchronizedTest;

/**
 * 生产着
 * Created by dell_2 on 2016/9/2.
 */
public class Producer implements Runnable {


    @Override
    public  void run() {
        while (true){
            while (Test.count==0){
                synchronized (Test.lock){
                    await();
                }
            }
            System.out.println("有货了开始消费!");
            Test.count --;
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
