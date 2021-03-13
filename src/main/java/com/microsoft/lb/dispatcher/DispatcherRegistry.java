package com.microsoft.lb.dispatcher;

import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.exceptions.DispatcherException;
import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.util.AppConfig;

import java.util.HashMap;
import java.util.Map;

public class DispatcherRegistry {

    private Map<String, TaskDispatcher> dispatcherMap = new HashMap<>();
    private AppConfig appConfig;

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void addNode(ExecutorNode node){
        TaskDispatcher dispatcher = dispatcherMap.get(node.getType());
        if(dispatcher == null){
            dispatcher = appConfig.getDispatcher();
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
