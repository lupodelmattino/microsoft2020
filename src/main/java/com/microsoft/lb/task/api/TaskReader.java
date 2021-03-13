package com.microsoft.lb.task.api;

import com.microsoft.lb.task.Task;

import java.io.Closeable;

public interface TaskReader extends Closeable {

    Task readTask();
}
