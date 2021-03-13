package com.microsoft.lb.node;

import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SimpleExecutorNode implements ExecutorNode, Runnable {

    private String type;
    private String name;
    private BlockingQueue<Task> queue = new LinkedBlockingQueue<>();

    //todo: Output to a file
    @Override
    public void execute(Task task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            //todo:replace with log
            e.printStackTrace();
        }
    }

    public void run(){
        int currentTimestamp = 0;
        System.out.println("Starting node " + getName());
        try {
            while(true) {
                Task task = queue.take();
                currentTimestamp = Math.max(task.getTsStart(), currentTimestamp);
                synchronized (task) {
                    String startMessage = String.format("Started task=’%s’ of type=’%s’ on node=’%s’ at ‘%d’",
                            task.getName(), task.getType(), getName(), currentTimestamp
                    );
                    System.out.println(startMessage);
                    Thread.sleep(task.getDuration() * 1000);
                    currentTimestamp += task.getDuration();
                    String endMessage = String.format("Finished task=’%s’ of type=’%s’ on node=’%s’ at ‘%d’",
                            task.getName(), task.getType(), getName(), currentTimestamp
                    );
                    System.out.println(endMessage);

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
    public String toString() {
        return "SimpleExecutorNode{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
