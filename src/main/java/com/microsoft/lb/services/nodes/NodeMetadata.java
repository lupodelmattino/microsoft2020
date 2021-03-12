package com.microsoft.lb.services.nodes;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class NodeMetadata {
    private List<Entry> nodes = new ArrayList<>();

    public List<Entry> getNodes() {
        return nodes;
    }

    public void setNodes(List<Entry> nodes) {
        this.nodes = nodes;
    }

    public static class Entry{
        @JsonProperty("node_name")
        private String name;

        @JsonProperty("accepted_task_type")
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
