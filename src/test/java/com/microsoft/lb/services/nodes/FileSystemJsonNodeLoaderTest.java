package com.microsoft.lb.services.nodes;

import com.microsoft.lb.dispatcher.DispatcherRegistry;
import com.microsoft.lb.nodeLoader.FileSystemJsonNodeLoader;
import com.microsoft.lb.util.AppConfig;
import junit.framework.TestCase;
import org.junit.Test;

public class FileSystemJsonNodeLoaderTest {

    @Test
    public void load() {
        FileSystemJsonNodeLoader nodeLoader = new FileSystemJsonNodeLoader();
        nodeLoader.setAppConfig(new AppConfig());
        nodeLoader.init();
        DispatcherRegistry registry = nodeLoader.getDispatcherRegistry();
        TestCase.assertNotNull(registry);
    }
}