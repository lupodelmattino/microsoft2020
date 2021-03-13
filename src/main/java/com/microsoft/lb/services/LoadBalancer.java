package com.microsoft.lb.services;

import com.microsoft.lb.dispatcher.DispatcherRegistry;
import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.exceptions.DispatcherException;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.util.AppConfig;
import org.apache.log4j.Logger;

/**
 * Consumes tasks from {@link InputQueue} and dispatches them to {@link TaskDispatcher} according to the task type
 */
public class LoadBalancer {
    private final static Logger LOG = Logger.getLogger(LoadBalancer.class);
    private AppConfig appConfig;
    private DispatcherRegistry dispatcherRegistry;
    private InputQueue inputQueue;
    private int currentTimeStamp = 0;
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
    public void init(){
        dispatcherRegistry = appConfig.getNodeLoader().getDispatcherRegistry();
        inputQueue = appConfig.getInputQueue();
    }

    /**
     * Consume and dispatch task
     */
    public void start(){
        Task task = null;
        LOG.info("About to start load balancer with " + inputQueue.toString() + " capacity " + inputQueue.getSize());
        try {
            while (true) {
                try {
                    task = inputQueue.take();
                    if(!task.getType().equals(Task.EOF)) {//If not last task in input
                        synchronized (task) {
                            //get dispatcher by task type
                            TaskDispatcher dispatcher = dispatcherRegistry.getDispatcher(task.getType());
                            LOG.debug("About to dispatch the task " + task);
                            Thread.sleep((task.getTsStart() - currentTimeStamp) * 1000);
                            currentTimeStamp = task.getTsStart();
                            dispatcher.dispatch(task);
                        }
                    }else {//Last task in input is dummy task. Broadcast the dummy task to stop all components and
                        // break the loop
                        dispatcherRegistry.broadcast(task);
                        break;
                    }
                } catch (DispatcherException e) {
                    LOG.error(String.format("Failed to dispatch Task: %s", task.toString()));
                    e.printStackTrace();
                }
            }
        }catch (InterruptedException e){
            LOG.warn("Shutting down the load balancer");
        }
    }
}
