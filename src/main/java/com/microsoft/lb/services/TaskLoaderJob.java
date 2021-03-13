package com.microsoft.lb.services;

import com.microsoft.lb.App;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.task.api.TaskReader;
import com.microsoft.lb.util.AppConfig;
import org.apache.log4j.Logger;

public class TaskLoaderJob {
    private final static Logger LOG = Logger.getLogger(TaskLoaderJob.class);
    private AppConfig appConfig;
    private InputQueue inputQueue;
    private TaskReader taskReader;
    public void init(AppConfig appConfig){
        this.appConfig = appConfig;
        inputQueue = appConfig.getInputQueue();
        taskReader = appConfig.getTaskReader();
    }

    public void loadTasks(){
        new Thread(() -> {
            Task task = taskReader.readTask();
            LOG.info("Starting loading tasks");
            while (task != null){
                try {
                    inputQueue.addTask(task);
                } catch (InterruptedException e) {
                    LOG.error(e);
                }
                task = taskReader.readTask();
            }
            LOG.info("Finished reading task file to " + inputQueue.toString() + " capacity " + inputQueue.getSize());
        }).start();
    }
}
