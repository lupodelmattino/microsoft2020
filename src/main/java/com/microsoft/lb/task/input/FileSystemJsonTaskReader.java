package com.microsoft.lb.task.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.lb.App;
import com.microsoft.lb.exceptions.ConfigurationException;
import com.microsoft.lb.task.Task;
import com.microsoft.lb.task.api.TaskReader;
import com.microsoft.lb.util.AppConfig;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Scanner;

public class FileSystemJsonTaskReader implements TaskReader, Closeable {
    private final static Logger LOG = Logger.getLogger(FileSystemJsonTaskReader.class);
    private AppConfig appConfig;
    private Scanner scan;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void init() throws FileNotFoundException {
        String taskPath = appConfig.getContext().getTaskFileUri();
        File file = new File(taskPath);
        if(!file.exists())
            throw new ConfigurationException(String.format("Failed  to load tasks from %s file", taskPath));
        scan = new Scanner(new FileInputStream(taskPath));
    }

    @Override
    public Task readTask() {
        Task task = null;
        String line = null;
        if(scan.hasNextLine())
            try {
                line = scan.nextLine();
                task = objectMapper.readValue(line, Task.class);

            } catch (JsonProcessingException e) {
                LOG.info(String.format("Failed to create task from %s", line));
                e.printStackTrace();
            }
        return task;
    }

    @Override
    public void close() throws IOException {
        scan.close();
    }
}
