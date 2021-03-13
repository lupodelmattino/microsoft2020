package com.microsoft.lb;


import com.microsoft.lb.services.LoadBalancer;
import com.microsoft.lb.services.TaskLoaderJob;
import com.microsoft.lb.util.AppConfig;
import com.microsoft.lb.util.ResourceContext;
import org.apache.log4j.Logger;

/**
 * Finds specified strings in a large text
 */
public class App {
    private final static Logger LOG = Logger.getLogger(App.class);
    public static void main(String[] args) {
        new App().execute();
    }
    private ResourceContext resourceContext = new ResourceContext();
    private AppConfig appConfig = new AppConfig();
    public void execute(){
        LOG.info("Starting the application");
        TaskLoaderJob taskLoader = appConfig.getTaskLoader();
        taskLoader.loadTasks();
        LoadBalancer lb = appConfig.getLoadBalancer();
        lb.balance();
    }
}
