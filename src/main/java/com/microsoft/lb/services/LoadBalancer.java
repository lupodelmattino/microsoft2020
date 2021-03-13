package com.microsoft.lb.services;

import com.microsoft.lb.dispatcher.DispatcherRegistry;
import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.exceptions.DispatcherException;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.util.AppConfig;

public class LoadBalancer {
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
    public void balance(){
        Task task = null;
        System.out.println("About to start load balancer with " + inputQueue.toString() + " size " + inputQueue.getSize());
        try {
            while (true) {
                try {
                    task = inputQueue.take();
                    synchronized (task) {
                        TaskDispatcher dispatcher = dispatcherRegistry.getDispatcher(task.getType());
                        System.out.println("About to dispatch the task " + task);
                        Thread.sleep((task.getTsStart() - currentTimeStamp) * 1000);
                        currentTimeStamp = task.getTsStart();
                        dispatcher.dispatch(task);
                    }
                } catch (DispatcherException e) {
                    //todo:replace with logger
                    System.out.println(String.format("Failed to dispatch Task: %s", task.toString()));
                    e.printStackTrace();
                }
            }
        }catch (InterruptedException e){
            System.out.println("Shutting down the load balancer");
        }
    }
}
