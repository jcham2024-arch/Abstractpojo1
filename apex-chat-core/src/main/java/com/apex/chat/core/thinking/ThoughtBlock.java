package com.apex.chat.core.thinking;

import com.apex.chat.core.content.ContentBlock;
import com.apex.chat.core.content.ContentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Structured thinking block containing segmented phases, extracted intent tags, and safety checkpoints.
 */
public class ThoughtBlock implements ContentBlock {

    private String blockId;
    private String rawReasoningText;
    private List<ThoughtSegment> segments;
    private List<IntentTag> intents;
    private List<ThoughtCheckpoint> checkpoints;
    private String signature;

    public ThoughtBlock() {
        this.blockId = UUID.randomUUID().toString();
        this.segments = new ArrayList<>();
        this.intents = new ArrayList<>();
        this.checkpoints = new ArrayList<>();
    }

    public ThoughtBlock(String rawReasoningText) {
        this.blockId = UUID.randomUUID().toString();
        this.rawReasoningText = rawReasoningText;
        this.segments = new ArrayList<>();
        this.intents = new ArrayList<>();
        this.checkpoints = new ArrayList<>();
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public ThoughtBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public String getRawReasoningText() {
        return rawReasoningText;
    }

    public ThoughtBlock setRawReasoningText(String rawReasoningText) {
        this.rawReasoningText = rawReasoningText;
        return this;
    }

    public List<ThoughtSegment> getSegments() {
        return segments;
    }

    public ThoughtBlock setSegments(List<ThoughtSegment> segments) {
        this.segments = segments != null ? segments : new ArrayList<>();
        return this;
    }

    public ThoughtBlock addSegment(ThoughtSegment segment) {
        if (this.segments == null) {
            this.segments = new ArrayList<>();
        }
        this.segments.add(segment);
        return this;
    }

    public List<IntentTag> getIntents() {
        return intents;
    }

    public ThoughtBlock setIntents(List<IntentTag> intents) {
        this.intents = intents != null ? intents : new ArrayList<>();
        return this;
    }

    public ThoughtBlock addIntent(IntentTag intent) {
        if (this.intents == null) {
            this.intents = new ArrayList<>();
        }
        this.intents.add(intent);
        return this;
    }

    public List<ThoughtCheckpoint> getCheckpoints() {
        return checkpoints;
    }

    public ThoughtBlock setCheckpoints(List<ThoughtCheckpoint> checkpoints) {
        this.checkpoints = checkpoints != null ? checkpoints : new ArrayList<>();
        return this;
    }

    public ThoughtBlock addCheckpoint(ThoughtCheckpoint checkpoint) {
        if (this.checkpoints == null) {
            this.checkpoints = new ArrayList<>();
        }
        this.checkpoints.add(checkpoint);
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public ThoughtBlock setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.THOUGHT_STRUCTURED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThoughtBlock that)) return false;
        return Objects.equals(blockId, that.blockId) &&
                Objects.equals(rawReasoningText, that.rawReasoningText) &&
                Objects.equals(segments, that.segments) &&
                Objects.equals(intents, that.intents) &&
                Objects.equals(checkpoints, that.checkpoints) &&
                Objects.equals(signature, that.signature);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, rawReasoningText, segments, intents, checkpoints, signature);
    }

    @Override
    public String toString() {
        return "ThoughtBlock{" +
                "blockId='" + blockId + '\'' +
                ", segmentsCount=" + (segments != null ? segments.size() : 0) +
                ", intentsCount=" + (intents != null ? intents.size() : 0) +
                ", checkpointsCount=" + (checkpoints != null ? checkpoints.size() : 0) +
                '}';
    }
}
