package com.microsoft.lb.model;

public class QueueTask implements Task {
    private final String type = "queueing_task";
    private String name;
    private int duration;
    private int tsStart;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int getTsStart() {
        return tsStart;
    }

    @Override
    public void setTsStart(int tsStart) {
        this.tsStart = tsStart;
    }
}
