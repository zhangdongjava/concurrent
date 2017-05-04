import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zd on 2017/5/3.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(10);

        Runnable r = () -> {
            int i = 10;
            while (!Thread.currentThread().isInterrupted()){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(i++);
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i <20; i++) {
            service.submit(r);
        }
        TimeUnit.SECONDS.sleep(1);
        service.shutdownNow();
    }
}
