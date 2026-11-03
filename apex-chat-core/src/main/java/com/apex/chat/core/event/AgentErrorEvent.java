package com.apex.chat.core.event;

import java.util.Objects;

/**
 * Event emitted when an unhandled error, policy violation, or model failure occurs during agent processing.
 */
public class AgentErrorEvent extends ChatLifecycleEvent {

    private String errorCode;
    private String errorMessage;
    private String failedComponent;
    private boolean recoverable;

    public AgentErrorEvent() {
        super();
        setEventType(ChatEventType.AGENT_ERROR);
        this.recoverable = false;
    }

    public AgentErrorEvent(String chatId, String errorCode, String errorMessage) {
        super(ChatEventType.AGENT_ERROR, chatId, "Agent error: " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.recoverable = false;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public AgentErrorEvent setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AgentErrorEvent setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public String getFailedComponent() {
        return failedComponent;
    }

    public AgentErrorEvent setFailedComponent(String failedComponent) {
        this.failedComponent = failedComponent;
        return this;
    }

    public boolean isRecoverable() {
        return recoverable;
    }

    public AgentErrorEvent setRecoverable(boolean recoverable) {
        this.recoverable = recoverable;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentErrorEvent that)) return false;
        if (!super.equals(o)) return false;
        return recoverable == that.recoverable &&
                Objects.equals(errorCode, that.errorCode) &&
                Objects.equals(errorMessage, that.errorMessage) &&
                Objects.equals(failedComponent, that.failedComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errorCode, errorMessage, failedComponent, recoverable);
    }

    @Override
    public String toString() {
        return "AgentErrorEvent{" +
                "chatId='" + getChatId() + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", failedComponent='" + failedComponent + '\'' +
                ", recoverable=" + recoverable +
                '}';
    }
}
