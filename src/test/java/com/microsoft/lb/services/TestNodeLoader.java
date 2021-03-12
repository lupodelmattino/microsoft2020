package com.microsoft.lb.services;

import com.microsoft.lb.services.nodes.NodeLoader;
import com.microsoft.lb.services.nodes.NodeMetadata;
import org.junit.Test;

public class TestNodeLoader {
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
        NodeLoader nodeLoader = new NodeLoader();
        NodeMetadata nm = nodeLoader.load(input);
        nm.getNodes().stream().forEach(e -> System.out.println(e.getName()));
    }
}
