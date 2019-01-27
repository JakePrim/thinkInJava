package thread;

import java.util.concurrent.ThreadFactory;

/**
 * 定制ThreadFactory 可以定制由Executor创建的线程的属性（后台、优先级、名称）
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
