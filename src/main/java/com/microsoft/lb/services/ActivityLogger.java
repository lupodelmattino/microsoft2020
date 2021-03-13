package com.microsoft.lb.services;

import com.microsoft.lb.exceptions.ConfigurationException;
import com.microsoft.lb.nodeLoader.NodeLoader;
import com.microsoft.lb.util.AppConfig;
import org.apache.log4j.Logger;

import java.io.*;

public class ActivityLogger implements Closeable {
    private static final Logger LOG = Logger.getLogger(NodeLoader.class);
    private AppConfig appConfig;
    private BufferedWriter writer;
    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public void init(){
        String path = appConfig.getContext().getOutputFilePath();
        try {
            FileWriter fw = new FileWriter(path, false);
            writer = new BufferedWriter(fw);
        } catch (IOException e) {
            String message = "Failed to create output file " + path;
            LOG.error(message);
            throw new ConfigurationException(message, e);
        }
    }
    public void log(String line){
        try {
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            LOG.error(String.format("Failed to log the Load Balancer activity: %s ", line), e);
        }

    }
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
