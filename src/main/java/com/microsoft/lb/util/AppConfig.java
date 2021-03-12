package com.microsoft.lb.util;

import com.microsoft.lb.services.InputQueue;
import com.microsoft.lb.services.LoadBalancer;
import com.microsoft.lb.services.TaskLoader;
import com.microsoft.lb.services.input.FileSystemJsonTaskReader;
import com.microsoft.lb.services.input.TaskReader;

public class AppConfig {
    private ResourceContext context = new ResourceContext();
    public InputQueue getInputQueue(){
        return new InputQueue(context.getQueueSize());
    }

    public TaskReader getTaskReader(){
        return new FileSystemJsonTaskReader();
    }

    public TaskLoader getTaskLoader(){
        TaskLoader tl = new TaskLoader();
        tl.init(this);
        return tl;
    }

    public LoadBalancer getLoadBalancer() {
        return new LoadBalancer();
    }
}
