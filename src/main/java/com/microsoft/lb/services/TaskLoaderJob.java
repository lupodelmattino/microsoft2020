package com.microsoft.lb.services;

import com.microsoft.lb.task.EOFTask;
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
            try {
                while (task != null){
                    inputQueue.addTask(task);
                    task = taskReader.readTask();
                }
                inputQueue.addTask(new EOFTask());
            } catch (InterruptedException e) {
                LOG.error(e);
            }
            LOG.info("Finished reading task file to " + inputQueue.toString() + " capacity " + inputQueue.getSize());
        }).start();
    }
}
