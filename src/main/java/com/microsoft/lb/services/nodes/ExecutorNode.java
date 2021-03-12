package com.microsoft.lb.services.nodes;

import com.microsoft.lb.model.Task;

public interface ExecutorNode {
    public void execute(Task task);
}
