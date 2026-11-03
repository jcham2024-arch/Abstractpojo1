package com.apex.chat.core.message;

import com.apex.chat.core.content.ContentBlock;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * An incremental streaming chunk emitted during real-time generation.
 */
public class StreamChunk implements Serializable {

    private String chunkId;
    private int index;
    private Instant timestamp;
    private List<ContentBlock> deltaBlocks;
    private boolean isLastChunk;
    private GenerationTelemetry interimUsage;

    public StreamChunk() {
        this.chunkId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.deltaBlocks = new ArrayList<>();
    }

    public StreamChunk(int index, boolean isLastChunk) {
        this();
        this.index = index;
        this.isLastChunk = isLastChunk;
    }

    public String getChunkId() {
        return chunkId;
    }

    public StreamChunk setChunkId(String chunkId) {
        this.chunkId = chunkId;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public StreamChunk setIndex(int index) {
        this.index = index;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public StreamChunk setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public List<ContentBlock> getDeltaBlocks() {
        return deltaBlocks;
    }

    public StreamChunk setDeltaBlocks(List<ContentBlock> deltaBlocks) {
        this.deltaBlocks = deltaBlocks != null ? deltaBlocks : new ArrayList<>();
        return this;
    }

    public StreamChunk addDeltaBlock(ContentBlock block) {
        if (this.deltaBlocks == null) {
            this.deltaBlocks = new ArrayList<>();
        }
        this.deltaBlocks.add(block);
        return this;
    }

    public boolean isLastChunk() {
        return isLastChunk;
    }

    public StreamChunk setLastChunk(boolean lastChunk) {
        isLastChunk = lastChunk;
        return this;
    }

    public GenerationTelemetry getInterimUsage() {
        return interimUsage;
    }

    public StreamChunk setInterimUsage(GenerationTelemetry interimUsage) {
        this.interimUsage = interimUsage;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StreamChunk that)) return false;
        return index == that.index &&
                isLastChunk == that.isLastChunk &&
                Objects.equals(chunkId, that.chunkId) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(deltaBlocks, that.deltaBlocks) &&
                Objects.equals(interimUsage, that.interimUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chunkId, index, timestamp, deltaBlocks, isLastChunk, interimUsage);
    }

    @Override
    public String toString() {
        return "StreamChunk{" +
                "chunkId='" + chunkId + '\'' +
                ", index=" + index +
                ", isLastChunk=" + isLastChunk +
                '}';
    }
}
