package com.microsoft.lb.util;

import java.io.InputStream;
import java.util.Properties;

public class ResourceContext {

    private Properties props = new Properties();
    private String path = "application.properties";

    public ResourceContext(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(path)){
            props.load(is);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getQueueCapacity(){
        return Integer.parseInt(props.getProperty("queueCapacity"));
    }

    public String getTaskFileUri(){
        return props.getProperty("taskFileUri");
    }

    public String getTaskReaderClassName(){
        return props.getProperty("taskReaderClassName");
    }

    public String getNodeConfigPath() {
        return props.getProperty("nodeConfigPath");
    }

    public String getNodeFactoryClassName() {
        return props.getProperty("nodeFactoryClassName");
    }
}
