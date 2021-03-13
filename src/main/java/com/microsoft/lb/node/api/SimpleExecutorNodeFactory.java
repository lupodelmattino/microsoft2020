package com.microsoft.lb.node.api;

import com.microsoft.lb.node.NodeMetadata;
import com.microsoft.lb.node.SimpleExecutorNode;
import com.microsoft.lb.services.ActivityLogger;

public class SimpleExecutorNodeFactory implements NodeFactory {

    private ActivityLogger activityLogger;

    @Override
    public ExecutorNode createNode(NodeMetadata.Entry entry){
        SimpleExecutorNode node = new SimpleExecutorNode();
        node.setType(entry.getType());
        node.setName(entry.getName());
        node.setActivityLogger(activityLogger);
        node.start();
        return node;
    }

    public void setActivityLogger(ActivityLogger activityLogger) {
        this.activityLogger = activityLogger;
    }
}
