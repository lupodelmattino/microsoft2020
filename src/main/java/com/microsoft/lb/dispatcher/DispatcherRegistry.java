package com.microsoft.lb.dispatcher;

import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.exceptions.DispatcherException;
import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.util.AppConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to {@link ExecutorNode} nodes via {@link TaskDispatcher} using {@link Map} of dispatchers
 */
public class DispatcherRegistry {

    private Map<String, TaskDispatcher> dispatcherMap = new HashMap<>();
    private AppConfig appConfig;

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    /**
     * Register new {@link ExecutorNode} node
     * @param node
     */
    public void addNode(ExecutorNode node){
        TaskDispatcher dispatcher = dispatcherMap.get(node.getType());
        if(dispatcher == null){
            dispatcher = appConfig.getDispatcher();
            dispatcherMap.put(node.getType(), dispatcher);
        }
        dispatcher.addNode(node);
    }


    /**
     *
     * @param type task type
     * @return according task type
     * @throws DispatcherException
     */
    public TaskDispatcher getDispatcher(String type) throws DispatcherException {
        TaskDispatcher dispatcher = dispatcherMap.get(type);
        if(dispatcher == null){
            throw new DispatcherException(String.format("Invalid task type: %s", type));
        }
        return dispatcher;
    }

    /**
     * Currently used for end of input signalling. The usage can be extended
     * @param task
     */
    public void broadcast(Task task) {
        dispatcherMap.values().stream().forEach(d -> d.dispatch(task));
    }
}
