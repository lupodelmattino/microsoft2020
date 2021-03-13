package com.microsoft.lb.node;

import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

public class SimpleExecutorNode implements ExecutorNode {

    private String type;
    private String name;

    //todo: add a use of queue and a thread that pops tasks from queue and executes them. Consider using a single logger
    //for the output file
    @Override
    public void execute(Task task) {
        synchronized (this){

        }
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
