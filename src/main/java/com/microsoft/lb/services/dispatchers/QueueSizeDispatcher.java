package com.microsoft.lb.services.dispatchers;

import com.microsoft.lb.model.Task;
import com.microsoft.lb.services.nodes.ExecutorNode;

public class QueueSizeDispatcher implements TaskDispatcher {
    //todo: implement methods
    @Override
    public void dispatch(Task task) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void addNode(ExecutorNode node) {
        throw new RuntimeException("not implemented");
    }
}
