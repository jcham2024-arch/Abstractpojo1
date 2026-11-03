package com.apex.chat.tools.execution;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A concrete tool invocation request emitted by an LLM model or agent.
 */
public class ToolCall implements Serializable {

    private String callId;
    private String toolName;
    private String argumentsJson;
    private Instant calledAt;

    public ToolCall() {
        this.callId = UUID.randomUUID().toString();
        this.calledAt = Instant.now();
    }

    public ToolCall(String toolName, String argumentsJson) {
        this();
        this.toolName = toolName;
        this.argumentsJson = argumentsJson;
    }

    public ToolCall(String callId, String toolName, String argumentsJson) {
        this(toolName, argumentsJson);
        if (callId != null) {
            this.callId = callId;
        }
    }

    public String getCallId() {
        return callId;
    }

    public ToolCall setCallId(String callId) {
        this.callId = callId;
        return this;
    }

    public String getToolName() {
        return toolName;
    }

    public ToolCall setToolName(String toolName) {
        this.toolName = toolName;
        return this;
    }

    public String getArgumentsJson() {
        return argumentsJson;
    }

    public ToolCall setArgumentsJson(String argumentsJson) {
        this.argumentsJson = argumentsJson;
        return this;
    }

    public Instant getCalledAt() {
        return calledAt;
    }

    public ToolCall setCalledAt(Instant calledAt) {
        this.calledAt = calledAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolCall toolCall)) return false;
        return Objects.equals(callId, toolCall.callId) &&
                Objects.equals(toolName, toolCall.toolName) &&
                Objects.equals(argumentsJson, toolCall.argumentsJson) &&
                Objects.equals(calledAt, toolCall.calledAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callId, toolName, argumentsJson, calledAt);
    }

    @Override
    public String toString() {
        return "ToolCall{" +
                "callId='" + callId + '\'' +
                ", toolName='" + toolName + '\'' +
                ", argumentsJson='" + argumentsJson + '\'' +
                '}';
    }
}
