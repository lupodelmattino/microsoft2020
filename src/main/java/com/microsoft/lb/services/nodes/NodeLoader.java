package com.microsoft.lb.services.nodes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.lb.services.dispatchers.DispatcherRegistry;

public class NodeLoader {
    //todo: contains only testing code. Change return to registry. Build registry from metadata
    private ObjectMapper mapper = new ObjectMapper();
    public NodeMetadata load(String json){
        DispatcherRegistry registry = new DispatcherRegistry();
        NodeMetadata nm = null;
        try {
            nm = mapper.readValue(json, NodeMetadata.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return nm;
    }
}
