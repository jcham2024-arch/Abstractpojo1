package com.apex.chat.core.thinking;

import java.io.Serializable;
import java.util.Objects;

/**
 * An individual phase or segment within a structured reasoning thought process.
 */
public class ThoughtSegment implements Serializable {

    private int segmentIndex;
    private ThoughtPhase phase;
    private String content;
    private Double confidenceScore;

    public ThoughtSegment() {
        this.phase = ThoughtPhase.ORIENTATION;
    }

    public ThoughtSegment(int segmentIndex, ThoughtPhase phase, String content, Double confidenceScore) {
        this.segmentIndex = segmentIndex;
        this.phase = phase;
        this.content = content;
        this.confidenceScore = confidenceScore;
    }

    public int getSegmentIndex() {
        return segmentIndex;
    }

    public ThoughtSegment setSegmentIndex(int segmentIndex) {
        this.segmentIndex = segmentIndex;
        return this;
    }

    public ThoughtPhase getPhase() {
        return phase;
    }

    public ThoughtSegment setPhase(ThoughtPhase phase) {
        this.phase = phase;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ThoughtSegment setContent(String content) {
        this.content = content;
        return this;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public ThoughtSegment setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThoughtSegment that)) return false;
        return segmentIndex == that.segmentIndex &&
                phase == that.phase &&
                Objects.equals(content, that.content) &&
                Objects.equals(confidenceScore, that.confidenceScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentIndex, phase, content, confidenceScore);
    }

    @Override
    public String toString() {
        return "ThoughtSegment{" +
                "segmentIndex=" + segmentIndex +
                ", phase=" + phase +
                ", confidenceScore=" + confidenceScore +
                '}';
    }
}
