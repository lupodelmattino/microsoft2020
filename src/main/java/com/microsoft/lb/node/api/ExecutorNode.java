package com.microsoft.lb.node.api;

import com.microsoft.lb.task.Task;

public interface ExecutorNode {
    void execute(Task task);
    String getType();
    String getName();
    void setType(String type);
    void setName(String name);
}
