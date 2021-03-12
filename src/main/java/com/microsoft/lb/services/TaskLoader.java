package com.microsoft.lb.services;

import com.microsoft.lb.model.Task;
import com.microsoft.lb.services.input.TaskReader;
import com.microsoft.lb.util.AppConfig;

public class TaskLoader {
    private AppConfig appConfig;
    private InputQueue inputQueue;
    private TaskReader taskReader;
    public void init(AppConfig appConfig){
        this.appConfig = appConfig;
        inputQueue = appConfig.getInputQueue();
        taskReader = appConfig.getTaskReader();
    }

    public void loadTasks(){
        Task task = taskReader.readTask();
        while (task != null){
            inputQueue.addTask(task);
        }
    }
}
