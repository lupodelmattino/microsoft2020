package com.microsoft.lb.services.dispatchers;

import com.microsoft.lb.services.nodes.ExecutorNode;

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
}
