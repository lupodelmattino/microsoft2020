package com.microsoft.lb.services.dispatchers;

import com.microsoft.lb.model.Task;
import com.microsoft.lb.services.nodes.ExecutorNode;

public interface TaskDispatcher {
    void dispatch(Task task);
    void addNode(ExecutorNode node);
}
