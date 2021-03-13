package com.microsoft.lb.dispatcher;

import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.exceptions.DispatcherException;
import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

import java.util.HashMap;
import java.util.Map;

public class DispatcherRegistry {
    private Map<String, TaskDispatcher> dispatcherMap = new HashMap<>();
    public void addNode(ExecutorNode node){
        TaskDispatcher dispatcher = dispatcherMap.get(node.getType());
        if(dispatcher == null){
            //todo:Replace dispatcher initialization
            dispatcher = new RoundRobinDispatcher();
            dispatcherMap.put(node.getType(), dispatcher);
        }
        dispatcher.addNode(node);
    }


    public TaskDispatcher getDispatcher(String type) throws DispatcherException {
        TaskDispatcher dispatcher = dispatcherMap.get(type);
        if(dispatcher == null){
            throw new DispatcherException(String.format("Invalid task type: %s", type));
        }
        return dispatcher;
    }

    public void broadcast(Task task) {
        dispatcherMap.values().stream().forEach(d -> d.dispatch(task));
    }
}
