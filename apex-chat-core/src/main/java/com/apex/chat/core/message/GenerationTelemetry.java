package com.apex.chat.core.message;

import java.io.Serializable;
import java.util.Objects;

/**
 * Detailed telemetry, token consumption, and latency metrics for an LLM generation.
 */
public class GenerationTelemetry implements Serializable {

    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
    private Integer cachedTokens;
    private Integer reasoningTokens;
    private long timeToFirstTokenMs;
    private long totalLatencyMs;
    private double tokensPerSecond;

    public GenerationTelemetry() {
    }

    public GenerationTelemetry(int promptTokens, int completionTokens, long totalLatencyMs) {
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = promptTokens + completionTokens;
        this.totalLatencyMs = totalLatencyMs;
        if (totalLatencyMs > 0) {
            this.tokensPerSecond = (completionTokens * 1000.0) / totalLatencyMs;
        }
    }

    public int getPromptTokens() {
        return promptTokens;
    }

    public GenerationTelemetry setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
        this.totalTokens = this.promptTokens + this.completionTokens;
        return this;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public GenerationTelemetry setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
        this.totalTokens = this.promptTokens + this.completionTokens;
        if (this.totalLatencyMs > 0) {
            this.tokensPerSecond = (this.completionTokens * 1000.0) / this.totalLatencyMs;
        }
        return this;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public GenerationTelemetry setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    public Integer getCachedTokens() {
        return cachedTokens;
    }

    public GenerationTelemetry setCachedTokens(Integer cachedTokens) {
        this.cachedTokens = cachedTokens;
        return this;
    }

    public Integer getReasoningTokens() {
        return reasoningTokens;
    }

    public GenerationTelemetry setReasoningTokens(Integer reasoningTokens) {
        this.reasoningTokens = reasoningTokens;
        return this;
    }

    public long getTimeToFirstTokenMs() {
        return timeToFirstTokenMs;
    }

    public GenerationTelemetry setTimeToFirstTokenMs(long timeToFirstTokenMs) {
        this.timeToFirstTokenMs = timeToFirstTokenMs;
        return this;
    }

    public long getTotalLatencyMs() {
        return totalLatencyMs;
    }

    public GenerationTelemetry setTotalLatencyMs(long totalLatencyMs) {
        this.totalLatencyMs = totalLatencyMs;
        if (totalLatencyMs > 0 && this.completionTokens > 0) {
            this.tokensPerSecond = (this.completionTokens * 1000.0) / totalLatencyMs;
        }
        return this;
    }

    public double getTokensPerSecond() {
        return tokensPerSecond;
    }

    public GenerationTelemetry setTokensPerSecond(double tokensPerSecond) {
        this.tokensPerSecond = tokensPerSecond;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenerationTelemetry that)) return false;
        return promptTokens == that.promptTokens &&
                completionTokens == that.completionTokens &&
                totalTokens == that.totalTokens &&
                timeToFirstTokenMs == that.timeToFirstTokenMs &&
                totalLatencyMs == that.totalLatencyMs &&
                Double.compare(that.tokensPerSecond, tokensPerSecond) == 0 &&
                Objects.equals(cachedTokens, that.cachedTokens) &&
                Objects.equals(reasoningTokens, that.reasoningTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(promptTokens, completionTokens, totalTokens, cachedTokens,
                reasoningTokens, timeToFirstTokenMs, totalLatencyMs, tokensPerSecond);
    }

    @Override
    public String toString() {
        return "GenerationTelemetry{" +
                "promptTokens=" + promptTokens +
                ", completionTokens=" + completionTokens +
                ", totalTokens=" + totalTokens +
                ", totalLatencyMs=" + totalLatencyMs +
                ", tokensPerSecond=" + tokensPerSecond +
                '}';
    }
}
