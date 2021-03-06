package com.microsoft.lb.dispatcher;

import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Dispatches tasks to {@link ExecutorNode} nodes using Round Robin algorithm
 */
public class RoundRobinDispatcher implements TaskDispatcher {
    private Queue<ExecutorNode> nodes = new LinkedList<>();

    @Override
    public void dispatch(Task task) {
        if(!task.getType().equals(Task.EOF)) {
            ExecutorNode node = nodes.poll();
            nodes.offer(node);
            node.execute(task);
        }else{
            while (nodes.size() > 0){
                nodes.poll().execute(task);
            }
        }
    }

    @Override
    public void addNode(ExecutorNode node) {
        nodes.offer(node);
    }
}
