package com.microsoft.lb.task;

public class EOFTask implements Task {
    @Override
    public String getType() {
        return Task.EOF;
    }

    @Override
    public void setType(String type) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public void setDuration(int duration) {

    }

    @Override
    public int getTsStart() {
        return 0;
    }

    @Override
    public void setTsStart(int tsStart) {

    }
}
