package com.microsoft.lb.services;

import com.microsoft.lb.model.Task;
import com.microsoft.lb.services.dispatchers.TaskDispatcher;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancer {
    //TODO:Create registry instead the map
    private Map<String, TaskDispatcher> dispatcherMap = new HashMap<>();
    private void addDispatcher(String nodeType, TaskDispatcher taskDispatcher){
        dispatcherMap.put(nodeType, taskDispatcher);
    }

    public void dispatchTask(Task task){
        TaskDispatcher dispatcher = dispatcherMap.get(task.getType());
        if(dispatcher == null){
            throw new IllegalArgumentException(String.format("Invalid task type: %s," +
                    task.getType()));
        }
        dispatcher.dispatch(task);
    }
}
