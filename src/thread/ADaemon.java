package thread;

import java.util.concurrent.TimeUnit;

public class ADaemon implements Runnable {
    @Override
    public void run() {
        System.out.println("ADaemon");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("This should always run?");
        }
    }
}
