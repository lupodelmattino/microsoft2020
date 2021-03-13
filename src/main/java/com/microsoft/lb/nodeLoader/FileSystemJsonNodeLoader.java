package com.microsoft.lb.nodeLoader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.lb.exceptions.ConfigurationException;
import com.microsoft.lb.dispatcher.DispatcherRegistry;
import com.microsoft.lb.node.NodeMetadata;
import com.microsoft.lb.node.api.NodeFactory;
import com.microsoft.lb.util.AppConfig;

import java.io.File;

public class FileSystemJsonNodeLoader implements NodeLoader {
    private ObjectMapper mapper = new ObjectMapper();
    private AppConfig appConfig;
    private NodeFactory nodeFactory;
    private DispatcherRegistry dispatcherRegistry;
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public DispatcherRegistry getDispatcherRegistry(){
        return dispatcherRegistry;
    }

    @Override
    public void init(){
        String path = appConfig.getContext().getNodeConfigPath();
        NodeMetadata nodeMetadata = loadFromFile(path);
        dispatcherRegistry = appConfig.getDispatcherRegistry();
        nodeFactory = appConfig.getNodeFactory();
        for(NodeMetadata.Entry entry : nodeMetadata.getNodes()){
            dispatcherRegistry.addNode(nodeFactory.createNode(entry));
        }
    }

    private NodeMetadata loadFromFile(String path){
        File conf = new File(path);
        try {
            return mapper.readValue(conf, NodeMetadata.class);
        } catch (Exception e) {
            throw new ConfigurationException("Failed to load node configuration", e);
        }
    }
}
