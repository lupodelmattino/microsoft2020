package com.microsoft.lb.node.api;

import com.microsoft.lb.node.NodeMetadata;

public interface NodeFactory {
    ExecutorNode createNode(NodeMetadata.Entry entry);
}
