package thread;

import java.util.concurrent.TimeUnit;

public class SleepTask extends LiftOff {
    @Override
    public void run() {
        try {
            while (countDown-- > 0) {
                System.out.println(status());
                //休眠
//               Thread.sleep(100);
                //java se5/6 的写法
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
