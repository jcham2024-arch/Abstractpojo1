package com.apex.chat.tools.registry;

import com.apex.chat.tools.spec.ToolDefinition;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Catalog/registry of tools registered and available for an agent.
 */
public class ToolRegistry implements Serializable {

    private String registryId;
    private Map<String, ToolDefinition> tools;

    public ToolRegistry() {
        this.registryId = UUID.randomUUID().toString();
        this.tools = new HashMap<>();
    }

    public String getRegistryId() {
        return registryId;
    }

    public ToolRegistry setRegistryId(String registryId) {
        this.registryId = registryId;
        return this;
    }

    public Map<String, ToolDefinition> getTools() {
        return tools;
    }

    public ToolRegistry setTools(Map<String, ToolDefinition> tools) {
        this.tools = tools != null ? tools : new HashMap<>();
        return this;
    }

    public ToolRegistry register(ToolDefinition tool) {
        if (tool != null && tool.getToolName() != null) {
            if (this.tools == null) {
                this.tools = new HashMap<>();
            }
            this.tools.put(tool.getToolName(), tool);
        }
        return this;
    }

    public ToolDefinition getTool(String toolName) {
        if (this.tools == null) return null;
        return this.tools.get(toolName);
    }

    public boolean hasTool(String toolName) {
        return this.tools != null && this.tools.containsKey(toolName);
    }

    public Collection<ToolDefinition> getAllTools() {
        return this.tools != null ? this.tools.values() : java.util.Collections.emptyList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolRegistry that)) return false;
        return Objects.equals(registryId, that.registryId) && Objects.equals(tools, that.tools);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registryId, tools);
    }

    @Override
    public String toString() {
        return "ToolRegistry{" +
                "registryId='" + registryId + '\'' +
                ", toolsCount=" + (tools != null ? tools.size() : 0) +
                '}';
    }
}
