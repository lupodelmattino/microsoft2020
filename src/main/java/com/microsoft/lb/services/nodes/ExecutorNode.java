package com.microsoft.lb.services.nodes;

import com.microsoft.lb.model.Task;

public interface ExecutorNode {
    void execute(Task task);
    String getType();

    void setType(String type);
}
