package com.microsoft.lb.services.nodes;

import com.microsoft.lb.model.Task;

public class SimpleExecutorNode implements ExecutorNode {

    private String type;

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
    public void setType(String type) {
        this.type = type;
    }

}
