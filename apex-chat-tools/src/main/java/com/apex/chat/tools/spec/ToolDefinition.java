package com.apex.chat.tools.spec;

import com.apex.chat.tools.policy.ToolExecutionPolicy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Complete definition and schema of a tool available to an agent or model.
 */
public class ToolDefinition implements Serializable {

    private String toolName;
    private String description;
    private List<ToolParameterSpecification> parameters;
    private boolean strictSchema;
    private ToolExecutionPolicy executionPolicy;

    public ToolDefinition() {
        this.parameters = new ArrayList<>();
        this.strictSchema = true;
        this.executionPolicy = new ToolExecutionPolicy();
    }

    public ToolDefinition(String toolName, String description) {
        this();
        this.toolName = toolName;
        this.description = description;
    }

    public String getToolName() {
        return toolName;
    }

    public ToolDefinition setToolName(String toolName) {
        this.toolName = toolName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ToolDefinition setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ToolParameterSpecification> getParameters() {
        return parameters;
    }

    public ToolDefinition setParameters(List<ToolParameterSpecification> parameters) {
        this.parameters = parameters != null ? parameters : new ArrayList<>();
        return this;
    }

    public ToolDefinition addParameter(ToolParameterSpecification parameter) {
        if (this.parameters == null) {
            this.parameters = new ArrayList<>();
        }
        this.parameters.add(parameter);
        return this;
    }

    public boolean isStrictSchema() {
        return strictSchema;
    }

    public ToolDefinition setStrictSchema(boolean strictSchema) {
        this.strictSchema = strictSchema;
        return this;
    }

    public ToolExecutionPolicy getExecutionPolicy() {
        return executionPolicy;
    }

    public ToolDefinition setExecutionPolicy(ToolExecutionPolicy executionPolicy) {
        this.executionPolicy = executionPolicy != null ? executionPolicy : new ToolExecutionPolicy();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolDefinition that)) return false;
        return strictSchema == that.strictSchema &&
                Objects.equals(toolName, that.toolName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(parameters, that.parameters) &&
                Objects.equals(executionPolicy, that.executionPolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toolName, description, parameters, strictSchema, executionPolicy);
    }

    @Override
    public String toString() {
        return "ToolDefinition{" +
                "toolName='" + toolName + '\'' +
                ", description='" + description + '\'' +
                ", parametersCount=" + (parameters != null ? parameters.size() : 0) +
                '}';
    }
}
