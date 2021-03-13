package com.microsoft.lb;


import com.microsoft.lb.services.LoadBalancer;
import com.microsoft.lb.services.TaskLoaderJob;
import com.microsoft.lb.util.AppConfig;
import com.microsoft.lb.util.ResourceContext;

/**
 * Finds specified strings in a large text
 */
public class App {

    //todo: modules to add 1. Node Loader 2. Logger
    public static void main(String[] args) {
        new App().execute();
    }
    private ResourceContext resourceContext = new ResourceContext();
    private AppConfig appConfig = new AppConfig();
    public void execute(){
        TaskLoaderJob taskLoader = appConfig.getTaskLoader();
        taskLoader.loadTasks();
        LoadBalancer lb = appConfig.getLoadBalancer();
        lb.balance();
		System.out.println("Q capacity:" + resourceContext.getQueueCapacity());
    }
}
