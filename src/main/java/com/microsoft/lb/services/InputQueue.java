package com.microsoft.lb.services;

import com.microsoft.lb.model.Task;

import java.util.Queue;

public class InputQueue {
    private final int capacity;
    private Queue<Task> queue;

    public InputQueue(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addTask(Task task){
        queue.offer(task);
    }

    public Task poll(){
        return queue.poll();
    }
}
