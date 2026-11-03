package com.apex.chat.core.event;

import java.util.Objects;

/**
 * Event emitted when an intentional agent step has finished execution.
 */
public class StepCompletedEvent extends ChatLifecycleEvent {

    private int stepIndex;
    private boolean toolExecuted;
    private String toolName;
    private long executionDurationMs;

    public StepCompletedEvent() {
        super();
        setEventType(ChatEventType.STEP_COMPLETED);
    }

    public StepCompletedEvent(String chatId, int stepIndex, boolean toolExecuted, String toolName) {
        super(ChatEventType.STEP_COMPLETED, chatId, "Agent step " + stepIndex + " completed");
        this.stepIndex = stepIndex;
        this.toolExecuted = toolExecuted;
        this.toolName = toolName;
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public StepCompletedEvent setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
        return this;
    }

    public boolean isToolExecuted() {
        return toolExecuted;
    }

    public StepCompletedEvent setToolExecuted(boolean toolExecuted) {
        this.toolExecuted = toolExecuted;
        return this;
    }

    public String getToolName() {
        return toolName;
    }

    public StepCompletedEvent setToolName(String toolName) {
        this.toolName = toolName;
        return this;
    }

    public long getExecutionDurationMs() {
        return executionDurationMs;
    }

    public StepCompletedEvent setExecutionDurationMs(long executionDurationMs) {
        this.executionDurationMs = executionDurationMs;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StepCompletedEvent that)) return false;
        if (!super.equals(o)) return false;
        return stepIndex == that.stepIndex &&
                toolExecuted == that.toolExecuted &&
                executionDurationMs == that.executionDurationMs &&
                Objects.equals(toolName, that.toolName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stepIndex, toolExecuted, toolName, executionDurationMs);
    }

    @Override
    public String toString() {
        return "StepCompletedEvent{" +
                "chatId='" + getChatId() + '\'' +
                ", stepIndex=" + stepIndex +
                ", toolExecuted=" + toolExecuted +
                ", toolName='" + toolName + '\'' +
                ", executionDurationMs=" + executionDurationMs +
                '}';
    }
}
