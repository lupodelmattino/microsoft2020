package com.microsoft.lb.services;

import com.microsoft.lb.task.Task;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InputQueue {
    private final int capacity;
    private BlockingQueue<Task> queue = new LinkedBlockingQueue();

    public InputQueue(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addTask(Task task) throws InterruptedException {
        queue.put(task);
    }

    public Task take() throws InterruptedException {
        return queue.take();
    }

    public int getSize(){
        return queue.size();
    }

    @Override
    public String toString() {
        return "InputQueue{" +
                "queue=" + queue.hashCode() +
                '}';
    }
}
