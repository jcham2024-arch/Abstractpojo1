package com.apex.chat.tools.policy;

import java.io.Serializable;
import java.util.Objects;

/**
 * Execution governance policy dictating how a tool may be invoked by an agent.
 */
public class ToolExecutionPolicy implements Serializable {

    private boolean requiresUserConfirmation;
    private boolean isSandboxed;
    private int timeoutSeconds;
    private int maxRetries;

    public ToolExecutionPolicy() {
        this.requiresUserConfirmation = false;
        this.isSandboxed = true;
        this.timeoutSeconds = 30;
        this.maxRetries = 1;
    }

    public boolean isRequiresUserConfirmation() {
        return requiresUserConfirmation;
    }

    public ToolExecutionPolicy setRequiresUserConfirmation(boolean requiresUserConfirmation) {
        this.requiresUserConfirmation = requiresUserConfirmation;
        return this;
    }

    public boolean isSandboxed() {
        return isSandboxed;
    }

    public ToolExecutionPolicy setSandboxed(boolean sandboxed) {
        isSandboxed = sandboxed;
        return this;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public ToolExecutionPolicy setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
        return this;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public ToolExecutionPolicy setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolExecutionPolicy that)) return false;
        return requiresUserConfirmation == that.requiresUserConfirmation &&
                isSandboxed == that.isSandboxed &&
                timeoutSeconds == that.timeoutSeconds &&
                maxRetries == that.maxRetries;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requiresUserConfirmation, isSandboxed, timeoutSeconds, maxRetries);
    }

    @Override
    public String toString() {
        return "ToolExecutionPolicy{" +
                "requiresUserConfirmation=" + requiresUserConfirmation +
                ", isSandboxed=" + isSandboxed +
                ", timeoutSeconds=" + timeoutSeconds +
                '}';
    }
}
