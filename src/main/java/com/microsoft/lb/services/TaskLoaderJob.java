package com.microsoft.lb.services;

import com.microsoft.lb.task.Task;
import com.microsoft.lb.task.api.TaskReader;
import com.microsoft.lb.util.AppConfig;

public class TaskLoaderJob {
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
            System.out.println("Starting loading tasks");
            while (task != null){
                try {
                    inputQueue.addTask(task);
                } catch (InterruptedException e) {
                    //todo:replace with log
                    e.printStackTrace();
                }
                task = taskReader.readTask();
            }
            //todo:replace with log
            System.out.println("Finished reading task file to " + inputQueue.toString() + " size " + inputQueue.getSize());
        }).start();
    }
}
