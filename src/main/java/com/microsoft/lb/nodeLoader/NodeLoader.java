package com.microsoft.lb.nodeLoader;

import com.microsoft.lb.dispatcher.DispatcherRegistry;

public interface NodeLoader {
    DispatcherRegistry getDispatcherRegistry();
    void init();
}
