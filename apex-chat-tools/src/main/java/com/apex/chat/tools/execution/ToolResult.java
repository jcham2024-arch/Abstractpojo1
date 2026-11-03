package com.apex.chat.tools.execution;

import java.io.Serializable;
import java.util.Objects;

/**
 * Outcome and output payload of a tool invocation.
 */
public class ToolResult implements Serializable {

    private String callId;
    private String toolName;
    private boolean success;
    private String outputPayloadJson;
    private String errorMessage;
    private long executionDurationMs;

    public ToolResult() {
        this.success = true;
    }

    public ToolResult(String callId, String toolName, boolean success, String outputPayloadJson) {
        this.callId = callId;
        this.toolName = toolName;
        this.success = success;
        this.outputPayloadJson = outputPayloadJson;
    }

    public static ToolResult success(String callId, String toolName, String outputPayloadJson) {
        return new ToolResult(callId, toolName, true, outputPayloadJson);
    }

    public static ToolResult failure(String callId, String toolName, String errorMessage) {
        ToolResult result = new ToolResult(callId, toolName, false, null);
        result.setErrorMessage(errorMessage);
        return result;
    }

    public String getCallId() {
        return callId;
    }

    public ToolResult setCallId(String callId) {
        this.callId = callId;
        return this;
    }

    public String getToolName() {
        return toolName;
    }

    public ToolResult setToolName(String toolName) {
        this.toolName = toolName;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public ToolResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getOutputPayloadJson() {
        return outputPayloadJson;
    }

    public ToolResult setOutputPayloadJson(String outputPayloadJson) {
        this.outputPayloadJson = outputPayloadJson;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ToolResult setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public long getExecutionDurationMs() {
        return executionDurationMs;
    }

    public ToolResult setExecutionDurationMs(long executionDurationMs) {
        this.executionDurationMs = executionDurationMs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolResult that)) return false;
        return success == that.success &&
                executionDurationMs == that.executionDurationMs &&
                Objects.equals(callId, that.callId) &&
                Objects.equals(toolName, that.toolName) &&
                Objects.equals(outputPayloadJson, that.outputPayloadJson) &&
                Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callId, toolName, success, outputPayloadJson, errorMessage, executionDurationMs);
    }

    @Override
    public String toString() {
        return "ToolResult{" +
                "callId='" + callId + '\'' +
                ", toolName='" + toolName + '\'' +
                ", success=" + success +
                ", durationMs=" + executionDurationMs +
                '}';
    }
}
