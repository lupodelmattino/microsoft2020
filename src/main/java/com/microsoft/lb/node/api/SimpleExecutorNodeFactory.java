package com.microsoft.lb.node.api;

import com.microsoft.lb.node.NodeMetadata;
import com.microsoft.lb.node.SimpleExecutorNode;

public class SimpleExecutorNodeFactory implements NodeFactory {
    @Override
    public ExecutorNode createNode(NodeMetadata.Entry entry){
        SimpleExecutorNode node = new SimpleExecutorNode();
        node.setType(entry.getType());
        node.setName(entry.getName());
        return node;
    }
}
