package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MainThread {
    public static void main(String[] args) {
//        createThread();
//        cachedThreadPool();
//        fixedThreadPool();
//        singleThreadExecutor();
//        callable();
//        sleepTask();
//        threadPriority();
//        daemonsTask();
//        try {
//            daemonThreadFactory();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        daemons();
//        daemonsDontRunFinally();
    }

    /**
     * 当你设置为后台线程时，finally子句不会执行 ，不为后台线程时finally子句就会执行
     * 一但 main()退出，JVM就会立即关闭所有后台进程，不会出现任何确认形式
     */
    private static void daemonsDontRunFinally(){
        Thread thread = new Thread(new ADaemon());
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Daemon线程被设置成了后台模式，然后派生出许多子线程，这些线程并没有被显式地设置为后台模式，不过它们的确是后台线程。
     * Daemon 线程进入无限循环，并在循环里调用yield()方法把控制权交给其他线程。
     */
    private static void daemons(){
        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        thread.start();
        System.out.println("thread.isDaemon() = "+thread.isDaemon()+". ");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过ThreadFactory 创建新的线程 将所有线程设置为后台线程
     * @throws InterruptedException
     */
    private static void daemonThreadFactory() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++) {
            executorService.execute(new SimpleDaemons());
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500);

    }


    /**
     * 后台线程
     * 所谓的后台(daemons)线程，是指在程序运行的时候在后台提供一种通用服务的线程，并且
     * 这种线程并不属于程序中不可或缺的部分。因此，当所有的非后台线程结束时，程序也就终止了，
     * 同时会杀死进程中的所有后台线程。反过来说，只要有任何非后台线程还在运行，程序就不会终止
     */
    private static void daemonsTask() {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SimpleDaemons());
            //设置为后台线程
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("All daemons started");
        try {
            TimeUnit.MILLISECONDS.sleep(175);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sleepTask() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SleepTask());
        }
        executorService.shutdown();
    }

    /**
     * 线程的使用
     */
    private static void createThread() {
        //        LiftOff liftOff = new LiftOff();
//        liftOff.run();
        for (int i = 0; i < 5; i++) {
            //创建一个线程去执行
            new Thread(new LiftOff()).start();
        }
    }
    //线程池

    /**
     * CachedThreadPool
     * 为每个任务都创建一个线程
     * CachedThreadPool 通常会创建与所需数量相同的线程，然后在它回收旧线程时停止创建新线程
     */
    public static void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        //shutdown 方法可以防止新任务被提交给这个Executor
        //当前线程将继续运行在shutdown被调用之前提交的所有任务
        executorService.shutdown();
//        executorService.execute(new LiftOff());
    }

    /**
     * 设置线程优先级
     */
    private static void threadPriority() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            //设置值最大优先级线程
            executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        System.out.println("设置最大优先级线程");
        executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        executorService.shutdown();
    }

    /**
     * FixedThreadPool 使用了有限的线程集来执行所提交的任务
     * FixedThreadPool 可以一次性预先执行代价高昂的线程分配，可以限制线程的数量
     * 这可以节省时间，因为你不用为每个任务都固定地付出创建线程的开销。这样就不会滥用获得的资源
     * 因为FixedThreadPool使用的Thread对象的数量是有界的
     */
    private static void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }

    /**
     * SingleThreadExecutor 就是线程数量为1的FixedThreadPool
     * 如果用SingleThreadExecutor提交多个任务，这些任务将会排队
     * 每个任务都会在下一个任务开始之前结束运行，SingleThreadExecutor会序列化所有提交给他的任务，并会维护他自己的悬挂任务队列
     * 已确保任意时刻在任何线程中都只有唯一的任务在运行
     */
    private static void singleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }

    private static void callable() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //submit 会产生Future对象 它用Callable返回的结果的特定类型进行了参数化
            //可以用future.isDone来查询future是否已经完成 直接调用future.get可能会阻塞 知道结果准备就绪
            futureList.add(executorService.submit(new TaskWithResult(i)));
        }
        for (Future<String> future : futureList) {
            try {
                if (future.isDone()) {
                    System.out.println(future.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
            } finally {
                executorService.shutdown();
            }
        }
    }
}
