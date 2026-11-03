package com.apex.chat.core.memory;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * An individual memory entry representing a stored fact, preference, or episodic observation.
 */
public class MemoryEntry implements Serializable {

    private String memoryId;
    private Instant timestamp;
    private MemoryCategory category;
    private String content;
    private double importanceScore;
    private int accessCount;
    private Map<String, String> metadata;

    public MemoryEntry() {
        this.memoryId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.category = MemoryCategory.FACTUAL_KNOWLEDGE;
        this.importanceScore = 1.0;
        this.accessCount = 0;
        this.metadata = new HashMap<>();
    }

    public MemoryEntry(MemoryCategory category, String content) {
        this();
        this.category = category;
        this.content = content;
    }

    public String getMemoryId() {
        return memoryId;
    }

    public MemoryEntry setMemoryId(String memoryId) {
        this.memoryId = memoryId;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public MemoryEntry setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public MemoryCategory getCategory() {
        return category;
    }

    public MemoryEntry setCategory(MemoryCategory category) {
        this.category = category;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MemoryEntry setContent(String content) {
        this.content = content;
        return this;
    }

    public double getImportanceScore() {
        return importanceScore;
    }

    public MemoryEntry setImportanceScore(double importanceScore) {
        this.importanceScore = importanceScore;
        return this;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public MemoryEntry setAccessCount(int accessCount) {
        this.accessCount = accessCount;
        return this;
    }

    public MemoryEntry recordAccess() {
        this.accessCount++;
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public MemoryEntry setMetadata(Map<String, String> metadata) {
        this.metadata = metadata != null ? metadata : new HashMap<>();
        return this;
    }

    public MemoryEntry addMetadata(String key, String value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryEntry that)) return false;
        return Double.compare(that.importanceScore, importanceScore) == 0 &&
                accessCount == that.accessCount &&
                Objects.equals(memoryId, that.memoryId) &&
                Objects.equals(timestamp, that.timestamp) &&
                category == that.category &&
                Objects.equals(content, that.content) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memoryId, timestamp, category, content, importanceScore, accessCount, metadata);
    }

    @Override
    public String toString() {
        return "MemoryEntry{" +
                "memoryId='" + memoryId + '\'' +
                ", category=" + category +
                ", importanceScore=" + importanceScore +
                ", accessCount=" + accessCount +
                '}';
    }
}
