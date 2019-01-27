package thread;

/**
 * 设置线程的优先级 线程的优先级将该线程的重要性传递给了调度器
 * 调度器将倾向于让优先级最高的线程先执行
 * 优先级较低的线程执行的频率较低
 */
public class SimplePriorities implements Runnable {
    private int priorities;

    private int countDown = 5;

    //volatile 它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值
    private volatile double d;

    public SimplePriorities(int priorities) {
        this.priorities = priorities;
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priorities);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    //yield 如果知道已经完成了在run()方法的循环的一次迭代过程中所需的工作，就可以给线程调度机制一个暗示：
                    //你的工作已经做得差不多了，可以让别的线程使用CPU了 其实是在建议具有相同优先级的其他线程可以运行
                    //大体上 对于任何重要的控制或在调整应用时，都不能依赖yield()
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}
