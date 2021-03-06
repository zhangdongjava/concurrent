package synchronizedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell_2 on 2016/9/2.
 */
public class Test {

    public static final  Object lock = new Object();

    /**
     * 最大货物数量
     */
    public static final int MAX_NUM = 5;
    /**
     * 货物数量
     */
    public static  int count = 0;

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        exec.submit(producer);
        exec.submit(consumer);
        exec.shutdown();
    }
}
