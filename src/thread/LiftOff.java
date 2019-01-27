package thread;

/**
 * 通过Runnable接口来定义一个任务
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;

    private static int taskCount = 0;

    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown){
        this.countDown = countDown;
    }

    public String status(){
        return "#"+id+"("+(countDown > 0 ? countDown : "Liftoff!")+").";
    }
    @Override
    public void run() {
         while (countDown-- > 0){
             System.out.print(status());
             //yield 在各种不同的LiftOff任务之间产生分布良好的处理机制
             Thread.yield();
         }
         System.out.println(" ");

    }
}
