package com.apex.chat.core.thread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Sequential history of conversation turns with branching capability and context metrics.
 */
public class ConversationThread implements Serializable {

    private String threadId;
    private String parentThreadId;
    private List<ConversationTurn> turns;
    private ThreadContextMetrics contextMetrics;

    public ConversationThread() {
        this.threadId = UUID.randomUUID().toString();
        this.turns = new ArrayList<>();
        this.contextMetrics = new ThreadContextMetrics();
    }

    public ConversationThread(String parentThreadId) {
        this();
        this.parentThreadId = parentThreadId;
    }

    public String getThreadId() {
        return threadId;
    }

    public ConversationThread setThreadId(String threadId) {
        this.threadId = threadId;
        return this;
    }

    public String getParentThreadId() {
        return parentThreadId;
    }

    public ConversationThread setParentThreadId(String parentThreadId) {
        this.parentThreadId = parentThreadId;
        return this;
    }

    public List<ConversationTurn> getTurns() {
        return turns;
    }

    public ConversationThread setTurns(List<ConversationTurn> turns) {
        this.turns = turns != null ? turns : new ArrayList<>();
        return this;
    }

    public ConversationThread addTurn(ConversationTurn turn) {
        if (this.turns == null) {
            this.turns = new ArrayList<>();
        }
        this.turns.add(turn);
        return this;
    }

    public ConversationTurn getLastTurn() {
        if (this.turns == null || this.turns.isEmpty()) return null;
        return this.turns.get(this.turns.size() - 1);
    }

    public ThreadContextMetrics getContextMetrics() {
        return contextMetrics;
    }

    public ConversationThread setContextMetrics(ThreadContextMetrics contextMetrics) {
        this.contextMetrics = contextMetrics != null ? contextMetrics : new ThreadContextMetrics();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConversationThread that)) return false;
        return Objects.equals(threadId, that.threadId) &&
                Objects.equals(parentThreadId, that.parentThreadId) &&
                Objects.equals(turns, that.turns) &&
                Objects.equals(contextMetrics, that.contextMetrics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadId, parentThreadId, turns, contextMetrics);
    }

    @Override
    public String toString() {
        return "ConversationThread{" +
                "threadId='" + threadId + '\'' +
                ", turnsCount=" + (turns != null ? turns.size() : 0) +
                ", contextMetrics=" + contextMetrics +
                '}';
    }
}
