package com.microsoft.lb.services;

import com.microsoft.lb.nodeLoader.FileSystemJsonNodeLoader;
import org.junit.Test;

public class TestFileSystemJsonNodeLoader {
    @Test
    public void testLoader() {
        //todo: contains temporary code
        String input = "{\n" +
                "\"nodes\": [\n" +
                "{ \"node_name\": \"node_1\", \"accepted_task_type\": \"queueing_task\" },\n" +
                "{ \"node_name\": \"node_2\", \"accepted_task_type\": \"queueing_task\" },\n" +
                "{ \"node_name\": \"node_3\", \"accepted_task_type\": \"web_task\" },\n" +
                "{ \"node_name\": \"node_4\", \"accepted_task_type\": \"database_task\" }\n" +
                "]\n" +
                "}";
        FileSystemJsonNodeLoader nodeLoader = new FileSystemJsonNodeLoader();
//        NodeMetadata nm = nodeLoader.load(input);
//        nm.getNodes().stream().forEach(e -> System.out.println(e.getName()));
    }
}
