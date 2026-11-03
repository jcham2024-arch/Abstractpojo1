package com.apex.chat.core.content;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Foundational content block representing dynamically injected context (RAG, episodic memories, schemas).
 */
public class ContextEnrichmentBlock implements ContentBlock {

    private String blockId;
    private String source;
    private String content;
    private Double relevanceScore;
    private Map<String, String> metadata;

    public ContextEnrichmentBlock() {
        this.blockId = UUID.randomUUID().toString();
        this.metadata = new HashMap<>();
    }

    public ContextEnrichmentBlock(String source, String content, Double relevanceScore) {
        this.blockId = UUID.randomUUID().toString();
        this.source = source;
        this.content = content;
        this.relevanceScore = relevanceScore;
        this.metadata = new HashMap<>();
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public ContextEnrichmentBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public String getSource() {
        return source;
    }

    public ContextEnrichmentBlock setSource(String source) {
        this.source = source;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ContextEnrichmentBlock setContent(String content) {
        this.content = content;
        return this;
    }

    public Double getRelevanceScore() {
        return relevanceScore;
    }

    public ContextEnrichmentBlock setRelevanceScore(Double relevanceScore) {
        this.relevanceScore = relevanceScore;
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public ContextEnrichmentBlock setMetadata(Map<String, String> metadata) {
        this.metadata = metadata != null ? metadata : new HashMap<>();
        return this;
    }

    public ContextEnrichmentBlock addMetadata(String key, String value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.CONTEXT_ENRICHMENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContextEnrichmentBlock that)) return false;
        return Objects.equals(blockId, that.blockId) &&
                Objects.equals(source, that.source) &&
                Objects.equals(content, that.content) &&
                Objects.equals(relevanceScore, that.relevanceScore) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, source, content, relevanceScore, metadata);
    }

    @Override
    public String toString() {
        return "ContextEnrichmentBlock{" +
                "blockId='" + blockId + '\'' +
                ", source='" + source + '\'' +
                ", relevanceScore=" + relevanceScore +
                '}';
    }
}
