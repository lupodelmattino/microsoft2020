package com.microsoft.lb.dispatcher.api;

import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

public interface TaskDispatcher {
    void dispatch(Task task);
    void addNode(ExecutorNode node);
}
