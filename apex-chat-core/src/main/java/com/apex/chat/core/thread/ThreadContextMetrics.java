package com.apex.chat.core.thread;

import java.io.Serializable;
import java.util.Objects;

/**
 * Real-time token budget and context utilization metrics for a conversation thread.
 */
public class ThreadContextMetrics implements Serializable {

    private int totalThreadTokens;
    private boolean contextExceeded;
    private PruningAction recommendedAction;

    public ThreadContextMetrics() {
        this.recommendedAction = PruningAction.NONE;
    }

    public ThreadContextMetrics(int totalThreadTokens, boolean contextExceeded) {
        this();
        this.totalThreadTokens = totalThreadTokens;
        this.contextExceeded = contextExceeded;
        if (contextExceeded) {
            this.recommendedAction = PruningAction.SUMMARIZE_HISTORY;
        }
    }

    public int getTotalThreadTokens() {
        return totalThreadTokens;
    }

    public ThreadContextMetrics setTotalThreadTokens(int totalThreadTokens) {
        this.totalThreadTokens = totalThreadTokens;
        return this;
    }

    public boolean isContextExceeded() {
        return contextExceeded;
    }

    public ThreadContextMetrics setContextExceeded(boolean contextExceeded) {
        this.contextExceeded = contextExceeded;
        return this;
    }

    public PruningAction getRecommendedAction() {
        return recommendedAction;
    }

    public ThreadContextMetrics setRecommendedAction(PruningAction recommendedAction) {
        this.recommendedAction = recommendedAction;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThreadContextMetrics that)) return false;
        return totalThreadTokens == that.totalThreadTokens &&
                contextExceeded == that.contextExceeded &&
                recommendedAction == that.recommendedAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalThreadTokens, contextExceeded, recommendedAction);
    }

    @Override
    public String toString() {
        return "ThreadContextMetrics{" +
                "totalThreadTokens=" + totalThreadTokens +
                ", contextExceeded=" + contextExceeded +
                ", recommendedAction=" + recommendedAction +
                '}';
    }
}
