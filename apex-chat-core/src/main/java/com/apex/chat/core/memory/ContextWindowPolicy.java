package com.apex.chat.core.memory;

import com.apex.chat.core.thread.PruningAction;

import java.io.Serializable;
import java.util.Objects;

/**
 * ContextWindowPolicy POJO governing token boundaries, turn sliding windows,
 * and automated pruning strategies for conversation turns.
 */
public class ContextWindowPolicy implements Serializable {

    private int maxContextTokens;
    private int recentTurnsRetained;
    private int reservedOutputTokens;
    private PruningAction pruningStrategy;
    private double autoSummarizeThresholdRatio;
    private String summarizedContextHistory;

    public ContextWindowPolicy() {
        this.maxContextTokens = 128000;
        this.recentTurnsRetained = 20;
        this.reservedOutputTokens = 4096;
        this.pruningStrategy = PruningAction.SLIDING_WINDOW_DROP;
        this.autoSummarizeThresholdRatio = 0.85;
    }

    public ContextWindowPolicy(int maxContextTokens, int recentTurnsRetained) {
        this();
        this.maxContextTokens = maxContextTokens;
        this.recentTurnsRetained = recentTurnsRetained;
    }

    public static ContextWindowPolicy standard(int maxTokens) {
        return new ContextWindowPolicy(maxTokens, 20);
    }

    public static ContextWindowPolicy highCapacity(int maxTokens) {
        return new ContextWindowPolicy(maxTokens, 50)
                .setPruningStrategy(PruningAction.SUMMARIZE_HISTORY);
    }

    public int getMaxContextTokens() {
        return maxContextTokens;
    }

    public ContextWindowPolicy setMaxContextTokens(int maxContextTokens) {
        this.maxContextTokens = maxContextTokens;
        return this;
    }

    public int getRecentTurnsRetained() {
        return recentTurnsRetained;
    }

    public ContextWindowPolicy setRecentTurnsRetained(int recentTurnsRetained) {
        this.recentTurnsRetained = recentTurnsRetained;
        return this;
    }

    public int getReservedOutputTokens() {
        return reservedOutputTokens;
    }

    public ContextWindowPolicy setReservedOutputTokens(int reservedOutputTokens) {
        this.reservedOutputTokens = reservedOutputTokens;
        return this;
    }

    public PruningAction getPruningStrategy() {
        return pruningStrategy;
    }

    public ContextWindowPolicy setPruningStrategy(PruningAction pruningStrategy) {
        this.pruningStrategy = pruningStrategy;
        return this;
    }

    public double getAutoSummarizeThresholdRatio() {
        return autoSummarizeThresholdRatio;
    }

    public ContextWindowPolicy setAutoSummarizeThresholdRatio(double autoSummarizeThresholdRatio) {
        this.autoSummarizeThresholdRatio = autoSummarizeThresholdRatio;
        return this;
    }

    public String getSummarizedContextHistory() {
        return summarizedContextHistory;
    }

    public ContextWindowPolicy setSummarizedContextHistory(String summarizedContextHistory) {
        this.summarizedContextHistory = summarizedContextHistory;
        return this;
    }

    public boolean isContextExceeded(int currentTokenCount) {
        return currentTokenCount > (this.maxContextTokens - this.reservedOutputTokens);
    }

    public boolean isApproachingThreshold(int currentTokenCount) {
        return currentTokenCount >= (this.maxContextTokens * this.autoSummarizeThresholdRatio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContextWindowPolicy that)) return false;
        return maxContextTokens == that.maxContextTokens &&
                recentTurnsRetained == that.recentTurnsRetained &&
                reservedOutputTokens == that.reservedOutputTokens &&
                Double.compare(that.autoSummarizeThresholdRatio, autoSummarizeThresholdRatio) == 0 &&
                pruningStrategy == that.pruningStrategy &&
                Objects.equals(summarizedContextHistory, that.summarizedContextHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxContextTokens, recentTurnsRetained, reservedOutputTokens,
                pruningStrategy, autoSummarizeThresholdRatio, summarizedContextHistory);
    }

    @Override
    public String toString() {
        return "ContextWindowPolicy{" +
                "maxContextTokens=" + maxContextTokens +
                ", recentTurnsRetained=" + recentTurnsRetained +
                ", pruningStrategy=" + pruningStrategy +
                '}';
    }
}
