package com.microsoft.lb.services.dispatchers;

import com.microsoft.lb.model.Task;
import com.microsoft.lb.services.nodes.ExecutorNode;

import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinDispatcher implements TaskDispatcher{
    private Queue<ExecutorNode> nodes = new LinkedList<>();
    @Override
    public void dispatch(Task task) {
        ExecutorNode node = nodes.poll();
        nodes.offer(node);
        node.execute(task);
    }

    @Override
    public void addNode(ExecutorNode node) {
        nodes.offer(node);
    }


}
