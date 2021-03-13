package com.microsoft.lb.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleTask implements Task {

    @JsonProperty("task_type")
    private String type;
    @JsonProperty("task_name")
    private String name;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("timestamp_start")
    private int tsStart;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "Task{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", tsStart=" + tsStart +
                '}';
    }
}
