package com.apex.chat.core.memory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Ephemeral, in-dialogue working scratchpad holding active hypotheses, goals, questions,
 * key-value scratchpad notes, and retrieved memories.
 */
public class WorkingMemory implements Serializable {

    private List<String> activeHypotheses;
    private List<String> activeGoals;
    private List<String> unresolvedQuestions;
    private Map<String, String> scratchpadNotes;
    private List<MemoryEntry> retrievedEntries;

    public WorkingMemory() {
        this.activeHypotheses = new ArrayList<>();
        this.activeGoals = new ArrayList<>();
        this.unresolvedQuestions = new ArrayList<>();
        this.scratchpadNotes = new HashMap<>();
        this.retrievedEntries = new ArrayList<>();
    }

    public List<String> getActiveHypotheses() {
        return activeHypotheses;
    }

    public WorkingMemory setActiveHypotheses(List<String> activeHypotheses) {
        this.activeHypotheses = activeHypotheses != null ? activeHypotheses : new ArrayList<>();
        return this;
    }

    public WorkingMemory addHypothesis(String hypothesis) {
        if (this.activeHypotheses == null) {
            this.activeHypotheses = new ArrayList<>();
        }
        this.activeHypotheses.add(hypothesis);
        return this;
    }

    public List<String> getUnresolvedQuestions() {
        return unresolvedQuestions;
    }

    public WorkingMemory setUnresolvedQuestions(List<String> unresolvedQuestions) {
        this.unresolvedQuestions = unresolvedQuestions != null ? unresolvedQuestions : new ArrayList<>();
        return this;
    }

    public WorkingMemory addQuestion(String question) {
        if (this.unresolvedQuestions == null) {
            this.unresolvedQuestions = new ArrayList<>();
        }
        this.unresolvedQuestions.add(question);
        return this;
    }

    public List<MemoryEntry> getRetrievedEntries() {
        return retrievedEntries;
    }

    public WorkingMemory setRetrievedEntries(List<MemoryEntry> retrievedEntries) {
        this.retrievedEntries = retrievedEntries != null ? retrievedEntries : new ArrayList<>();
        return this;
    }

    public WorkingMemory addRetrievedEntry(MemoryEntry entry) {
        if (this.retrievedEntries == null) {
            this.retrievedEntries = new ArrayList<>();
        }
        this.retrievedEntries.add(entry);
        return this;
    }

    public List<String> getActiveGoals() {
        return activeGoals;
    }

    public WorkingMemory setActiveGoals(List<String> activeGoals) {
        this.activeGoals = activeGoals != null ? activeGoals : new ArrayList<>();
        return this;
    }

    public WorkingMemory addGoal(String goal) {
        if (this.activeGoals == null) {
            this.activeGoals = new ArrayList<>();
        }
        this.activeGoals.add(goal);
        return this;
    }

    public Map<String, String> getScratchpadNotes() {
        return scratchpadNotes;
    }

    public WorkingMemory setScratchpadNotes(Map<String, String> scratchpadNotes) {
        this.scratchpadNotes = scratchpadNotes != null ? scratchpadNotes : new HashMap<>();
        return this;
    }

    public WorkingMemory putScratchpadNote(String key, String value) {
        if (this.scratchpadNotes == null) {
            this.scratchpadNotes = new HashMap<>();
        }
        this.scratchpadNotes.put(key, value);
        return this;
    }

    public String getScratchpadNote(String key) {
        return this.scratchpadNotes != null ? this.scratchpadNotes.get(key) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkingMemory that)) return false;
        return Objects.equals(activeHypotheses, that.activeHypotheses) &&
                Objects.equals(activeGoals, that.activeGoals) &&
                Objects.equals(unresolvedQuestions, that.unresolvedQuestions) &&
                Objects.equals(scratchpadNotes, that.scratchpadNotes) &&
                Objects.equals(retrievedEntries, that.retrievedEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeHypotheses, activeGoals, unresolvedQuestions, scratchpadNotes, retrievedEntries);
    }

    @Override
    public String toString() {
        return "WorkingMemory{" +
                "hypothesesCount=" + (activeHypotheses != null ? activeHypotheses.size() : 0) +
                ", goalsCount=" + (activeGoals != null ? activeGoals.size() : 0) +
                ", questionsCount=" + (unresolvedQuestions != null ? unresolvedQuestions.size() : 0) +
                ", notesCount=" + (scratchpadNotes != null ? scratchpadNotes.size() : 0) +
                ", retrievedCount=" + (retrievedEntries != null ? retrievedEntries.size() : 0) +
                '}';
    }
}
