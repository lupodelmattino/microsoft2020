package com.microsoft.lb.util;

import com.microsoft.lb.exceptions.ConfigurationException;
import com.microsoft.lb.node.api.SimpleExecutorNodeFactory;
import com.microsoft.lb.nodeLoader.FileSystemJsonNodeLoader;
import com.microsoft.lb.nodeLoader.NodeLoader;
import com.microsoft.lb.services.ActivityLogger;
import com.microsoft.lb.services.InputQueue;
import com.microsoft.lb.services.LoadBalancer;
import com.microsoft.lb.services.TaskLoaderJob;
import com.microsoft.lb.task.input.FileSystemJsonTaskReader;
import com.microsoft.lb.task.api.TaskReader;
import com.microsoft.lb.node.api.NodeFactory;

import java.io.FileNotFoundException;

public class AppConfig {
    private ResourceContext context;
    private InputQueue inputQueue;
    private ActivityLogger activityLogger;
    private SimpleExecutorNodeFactory nodeFactory;
    public AppConfig() {
        context = new ResourceContext();
        inputQueue = new InputQueue(context.getQueueCapacity());
        activityLogger = new ActivityLogger();
        activityLogger.setAppConfig(this);
        activityLogger.init();
        nodeFactory = new SimpleExecutorNodeFactory();
        nodeFactory.setActivityLogger(activityLogger);

    }

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

    public ActivityLogger getActivityLogger(){
        return activityLogger;
    }


    public TaskLoaderJob getTaskLoader(){
        TaskLoaderJob tl = new TaskLoaderJob();
        tl.init(this);
        return tl;
    }

    public NodeFactory getNodeFactory() {
        return nodeFactory;
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
