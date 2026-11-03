package com.apex.chat.core.memory;

import com.apex.chat.core.thread.ConversationTurn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * MemoryStore POJO managing episodic memories, working scratchpads, and context window
 * token/turn policies for chat and agent execution.
 */
public class MemoryStore implements Serializable {

    private WorkingMemory workingMemory;
    private List<MemoryEntry> episodicMemories;
    private ContextWindowPolicy contextWindowPolicy;
    private int maxMemoryTokenBudget;

    public MemoryStore() {
        this.workingMemory = new WorkingMemory();
        this.episodicMemories = new ArrayList<>();
        this.contextWindowPolicy = new ContextWindowPolicy();
        this.maxMemoryTokenBudget = 4096;
    }

    public WorkingMemory getWorkingMemory() {
        return workingMemory;
    }

    public MemoryStore setWorkingMemory(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory != null ? workingMemory : new WorkingMemory();
        return this;
    }

    public List<MemoryEntry> getEpisodicMemories() {
        return episodicMemories;
    }

    public MemoryStore setEpisodicMemories(List<MemoryEntry> episodicMemories) {
        this.episodicMemories = episodicMemories != null ? episodicMemories : new ArrayList<>();
        return this;
    }

    public MemoryStore addEpisodicMemory(MemoryEntry memory) {
        if (this.episodicMemories == null) {
            this.episodicMemories = new ArrayList<>();
        }
        this.episodicMemories.add(memory);
        return this;
    }

    public MemoryStore addEpisodicMemory(MemoryCategory category, String content, double importanceScore) {
        MemoryEntry entry = new MemoryEntry(category, content)
                .setImportanceScore(importanceScore);
        return addEpisodicMemory(entry);
    }

    public List<MemoryEntry> findMemoriesByCategory(MemoryCategory category) {
        if (this.episodicMemories == null || category == null) {
            return Collections.emptyList();
        }
        return this.episodicMemories.stream()
                .filter(m -> category == m.getCategory())
                .collect(Collectors.toList());
    }

    public List<MemoryEntry> findMemoriesAboveImportance(double minimumImportance) {
        if (this.episodicMemories == null) {
            return Collections.emptyList();
        }
        return this.episodicMemories.stream()
                .filter(m -> m.getImportanceScore() >= minimumImportance)
                .collect(Collectors.toList());
    }

    // Working Scratchpad Shortcuts
    public MemoryStore putScratchpadNote(String key, String value) {
        if (this.workingMemory == null) {
            this.workingMemory = new WorkingMemory();
        }
        this.workingMemory.putScratchpadNote(key, value);
        return this;
    }

    public String getScratchpadNote(String key) {
        return this.workingMemory != null ? this.workingMemory.getScratchpadNote(key) : null;
    }

    public MemoryStore addWorkingGoal(String goal) {
        if (this.workingMemory == null) {
            this.workingMemory = new WorkingMemory();
        }
        this.workingMemory.addGoal(goal);
        return this;
    }

    public MemoryStore addWorkingHypothesis(String hypothesis) {
        if (this.workingMemory == null) {
            this.workingMemory = new WorkingMemory();
        }
        this.workingMemory.addHypothesis(hypothesis);
        return this;
    }

    // Context Window Management for Conversation Turns
    public ContextWindowPolicy getContextWindowPolicy() {
        return contextWindowPolicy;
    }

    public MemoryStore setContextWindowPolicy(ContextWindowPolicy contextWindowPolicy) {
        this.contextWindowPolicy = contextWindowPolicy != null ? contextWindowPolicy : new ContextWindowPolicy();
        return this;
    }

    public boolean isContextExceeded(int currentTokenCount) {
        return this.contextWindowPolicy != null && this.contextWindowPolicy.isContextExceeded(currentTokenCount);
    }

    /**
     * Applies the configured ContextWindowPolicy to prune conversation turns according
     * to the recent turn retention limit.
     *
     * @param turns all conversation turns
     * @return pruned sublist of recent turns fitting within policy window
     */
    public List<ConversationTurn> selectActiveTurns(List<ConversationTurn> turns) {
        if (turns == null || turns.isEmpty()) {
            return Collections.emptyList();
        }
        if (this.contextWindowPolicy == null) {
            return turns;
        }
        int maxRetained = this.contextWindowPolicy.getRecentTurnsRetained();
        if (turns.size() <= maxRetained) {
            return new ArrayList<>(turns);
        }
        int startIndex = turns.size() - maxRetained;
        return new ArrayList<>(turns.subList(startIndex, turns.size()));
    }

    public int getMaxMemoryTokenBudget() {
        return maxMemoryTokenBudget;
    }

    public MemoryStore setMaxMemoryTokenBudget(int maxMemoryTokenBudget) {
        this.maxMemoryTokenBudget = maxMemoryTokenBudget;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryStore that)) return false;
        return maxMemoryTokenBudget == that.maxMemoryTokenBudget &&
                Objects.equals(workingMemory, that.workingMemory) &&
                Objects.equals(episodicMemories, that.episodicMemories) &&
                Objects.equals(contextWindowPolicy, that.contextWindowPolicy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workingMemory, episodicMemories, contextWindowPolicy, maxMemoryTokenBudget);
    }

    @Override
    public String toString() {
        return "MemoryStore{" +
                "episodicCount=" + (episodicMemories != null ? episodicMemories.size() : 0) +
                ", scratchpadNotes=" + (workingMemory != null && workingMemory.getScratchpadNotes() != null ? workingMemory.getScratchpadNotes().size() : 0) +
                ", maxContextTokens=" + (contextWindowPolicy != null ? contextWindowPolicy.getMaxContextTokens() : 0) +
                ", maxMemoryTokenBudget=" + maxMemoryTokenBudget +
                '}';
    }
}
