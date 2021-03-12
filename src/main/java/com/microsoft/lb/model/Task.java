package com.microsoft.lb.model;
//todo: consider using a single task class
public interface Task {
    abstract String getType();

    String getName();

    void setName(String name);

    int getDuration();

    void setDuration(int duration);

    int getTsStart();

    void setTsStart(int tsStart);
}
