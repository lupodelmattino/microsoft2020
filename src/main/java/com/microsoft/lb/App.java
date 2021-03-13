package com.microsoft.lb;


import com.microsoft.lb.services.LoadBalancer;
import com.microsoft.lb.services.TaskLoaderJob;
import com.microsoft.lb.util.AppConfig;
import org.apache.log4j.Logger;

/**
 * Load balancer application
 */
public class App {
    private final static Logger LOG = Logger.getLogger(App.class);
    public static void main(String[] args) {
        new App().execute();
    }
    private AppConfig appConfig = new AppConfig();
    public void execute(){
        LOG.info("Starting the application");
        TaskLoaderJob taskLoader = appConfig.getTaskLoader();
        taskLoader.loadTasks();
        LoadBalancer lb = appConfig.getLoadBalancer();
        lb.start();
    }
}
