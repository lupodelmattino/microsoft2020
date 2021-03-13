package com.microsoft.lb.dispatcher;

import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

public class QueueSizeDispatcher implements TaskDispatcher {
    //todo: implement methods for checkpoint 2
    @Override
    public void dispatch(Task task) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void addNode(ExecutorNode node) {
        throw new RuntimeException("not implemented");
    }
}
