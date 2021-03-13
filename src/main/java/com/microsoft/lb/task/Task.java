package com.microsoft.lb.task;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    @JsonProperty("task_type")
    private String type;
    @JsonProperty("task_name")
    private String name;
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("timestamp_start")
    private int tsStart;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTsStart() {
        return tsStart;
    }

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
