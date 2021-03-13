package com.microsoft.lb.services;

import com.microsoft.lb.task.EOFTask;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.task.api.TaskReader;
import com.microsoft.lb.util.AppConfig;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Uses {@link TaskReader} to load tasks from the provided source. Currently Json File
 * task reader is implemented. Other types can be added.
 * The tasks are loaded one by one. Once loaded a task is passed to {@link InputQueue}.
 * {@link LoadBalancer} consumes the tasks from the queue.
 * Tasks are loaded asynchronously.
 */
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

    /**
     * Load and queue tasks
     */
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
            }finally {
                try {
                    taskReader.close();
                } catch (IOException ignore) {}
            }
            LOG.info("Finished reading task file to " + inputQueue.toString() + " capacity " + inputQueue.getSize());
        }).start();
    }
}
