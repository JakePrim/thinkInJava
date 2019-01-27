package thread;


import javafx.util.Callback;

import java.util.concurrent.Callable;

/**
 * 任务中产生返回值，Runnable是执行工作的独立任务，但是它不返回任何值
 * Callable 能够在任务完成时返回一个值
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "TaskWithResult is id:" + id;
    }
}
