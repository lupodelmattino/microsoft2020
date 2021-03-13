package com.microsoft.lb.node;

import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.services.ActivityLogger;
import com.microsoft.lb.task.Task;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Executes tasks in a separate thread using task queue
 */
public class SimpleExecutorNode implements ExecutorNode, Runnable {
    private static final Logger LOG = Logger.getLogger(SimpleExecutorNode.class);
    private ActivityLogger activityLogger;
    private String type;
    private String name;
    private BlockingQueue<Task> queue = new LinkedBlockingQueue<>();


    public void setActivityLogger(ActivityLogger activityLogger) {
        this.activityLogger = activityLogger;
    }

    @Override
    public void execute(Task task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void run(){
        int currentTimestamp = 0;
        LOG.info("Starting node " + getName());
        try {
            while(true) {
                Task task = queue.take();
                if(task.getType().equals(Task.EOF)){//Shut down if end of input
                    break;
                }
                //take the latest timestamp
                currentTimestamp = Math.max(task.getTsStart(), currentTimestamp);
                synchronized (task) {
                    String startMessage = String.format("Started task=’%s’ of type=’%s’ on node=’%s’ at ‘%d’\n",
                            task.getName(), task.getType(), getName(), currentTimestamp
                    );
                    activityLogger.log(startMessage);
                    //Sleep duration time
                    Thread.sleep(task.getDuration() * 1000);
                    currentTimestamp += task.getDuration();
                    String endMessage = String.format("Finished task=’%s’ of type=’%s’ on node=’%s’ at ‘%d’\n",
                            task.getName(), task.getType(), getName(), currentTimestamp
                    );
                    activityLogger.log(endMessage);

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        new Thread(this).start();
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getQueueSize(){
        return queue.size();

    }

    @Override
    public String toString() {
        return "SimpleExecutorNode{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
