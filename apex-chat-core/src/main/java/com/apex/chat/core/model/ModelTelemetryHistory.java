package com.apex.chat.core.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Operational telemetry and invocation statistics for an LLM within a chat.
 */
public class ModelTelemetryHistory implements Serializable {

    private int invocationsCount;
    private long aggregatePromptTokens;
    private long aggregateCompletionTokens;
    private long aggregateReasoningTokens;
    private double averageLatencyMs;

    public ModelTelemetryHistory() {
    }

    public int getInvocationsCount() {
        return invocationsCount;
    }

    public ModelTelemetryHistory setInvocationsCount(int invocationsCount) {
        this.invocationsCount = invocationsCount;
        return this;
    }

    public long getAggregatePromptTokens() {
        return aggregatePromptTokens;
    }

    public ModelTelemetryHistory setAggregatePromptTokens(long aggregatePromptTokens) {
        this.aggregatePromptTokens = aggregatePromptTokens;
        return this;
    }

    public long getAggregateCompletionTokens() {
        return aggregateCompletionTokens;
    }

    public ModelTelemetryHistory setAggregateCompletionTokens(long aggregateCompletionTokens) {
        this.aggregateCompletionTokens = aggregateCompletionTokens;
        return this;
    }

    public long getAggregateReasoningTokens() {
        return aggregateReasoningTokens;
    }

    public ModelTelemetryHistory setAggregateReasoningTokens(long aggregateReasoningTokens) {
        this.aggregateReasoningTokens = aggregateReasoningTokens;
        return this;
    }

    public double getAverageLatencyMs() {
        return averageLatencyMs;
    }

    public ModelTelemetryHistory setAverageLatencyMs(double averageLatencyMs) {
        this.averageLatencyMs = averageLatencyMs;
        return this;
    }

    public ModelTelemetryHistory recordInvocation(int promptTokens, int completionTokens, int reasoningTokens, long latencyMs) {
        this.aggregatePromptTokens += promptTokens;
        this.aggregateCompletionTokens += completionTokens;
        this.aggregateReasoningTokens += reasoningTokens;
        double totalLatency = (this.averageLatencyMs * this.invocationsCount) + latencyMs;
        this.invocationsCount++;
        this.averageLatencyMs = totalLatency / this.invocationsCount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelTelemetryHistory that)) return false;
        return invocationsCount == that.invocationsCount &&
                aggregatePromptTokens == that.aggregatePromptTokens &&
                aggregateCompletionTokens == that.aggregateCompletionTokens &&
                aggregateReasoningTokens == that.aggregateReasoningTokens &&
                Double.compare(that.averageLatencyMs, averageLatencyMs) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(invocationsCount, aggregatePromptTokens, aggregateCompletionTokens,
                aggregateReasoningTokens, averageLatencyMs);
    }

    @Override
    public String toString() {
        return "ModelTelemetryHistory{" +
                "invocationsCount=" + invocationsCount +
                ", aggregatePromptTokens=" + aggregatePromptTokens +
                ", aggregateCompletionTokens=" + aggregateCompletionTokens +
                ", averageLatencyMs=" + averageLatencyMs +
                '}';
    }
}
