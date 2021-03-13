package com.microsoft.lb.task;

public interface Task {
    String EOF = "EOF";
    String getType();

    void setType(String type);

    String getName();

    void setName(String name);

    int getDuration();

    void setDuration(int duration);

    int getTsStart();

    void setTsStart(int tsStart);
}
