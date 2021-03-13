package com.microsoft.lb.util;

import com.microsoft.lb.exceptions.ConfigurationException;
import com.microsoft.lb.nodeLoader.FileSystemJsonNodeLoader;
import com.microsoft.lb.nodeLoader.NodeLoader;
import com.microsoft.lb.services.InputQueue;
import com.microsoft.lb.services.LoadBalancer;
import com.microsoft.lb.services.TaskLoaderJob;
import com.microsoft.lb.task.input.FileSystemJsonTaskReader;
import com.microsoft.lb.task.api.TaskReader;
import com.microsoft.lb.node.api.NodeFactory;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;

public class AppConfig {
    private ResourceContext context = new ResourceContext();
    private InputQueue inputQueue = new InputQueue(context.getQueueCapacity());
    public InputQueue getInputQueue(){
        return inputQueue;
    }

    public ResourceContext getContext() {
        return context;
    }

    public TaskReader getTaskReader(){
        try {
            FileSystemJsonTaskReader reader = new FileSystemJsonTaskReader();
            reader.setAppConfig(this);
            reader.init();
            return reader;
        } catch (FileNotFoundException e) {
            throw new ConfigurationException("Task input file wasn't found at", e);
        }

    }

    public TaskLoaderJob getTaskLoader(){
        TaskLoaderJob tl = new TaskLoaderJob();
        tl.init(this);
        return tl;
    }

    public NodeFactory getNodeFactory() {
        try{
            String className = context.getNodeFactoryClassName();
            Class<?> clazz = Class.forName(className);
            Constructor<? extends NodeFactory> ctor = (Constructor<? extends NodeFactory>) clazz.getConstructor();
            NodeFactory factory = ctor.newInstance();
            return factory;
        }catch (Exception e){
            throw new ConfigurationException("Failed to instanciate Node Factory");
        }
    }

    public NodeLoader getNodeLoader() {
        FileSystemJsonNodeLoader loader = new FileSystemJsonNodeLoader();
        loader.setAppConfig(this);
        loader.init();
        return loader;
    }

    public LoadBalancer getLoadBalancer() {
        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.setAppConfig(this);
        loadBalancer.init();
        return loadBalancer;
    }


}
