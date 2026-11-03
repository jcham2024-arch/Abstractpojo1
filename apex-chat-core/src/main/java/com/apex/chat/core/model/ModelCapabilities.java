package com.apex.chat.core.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Functional capabilities supported by an LLM model.
 */
public class ModelCapabilities implements Serializable {

    private boolean supportsStreaming;
    private boolean supportsTools;
    private boolean supportsVision;
    private boolean supportsAudio;
    private boolean supportsStructuredOutput;
    private boolean supportsNativeReasoning;

    public ModelCapabilities() {
        this.supportsStreaming = true;
        this.supportsTools = true;
        this.supportsVision = true;
        this.supportsAudio = false;
        this.supportsStructuredOutput = true;
        this.supportsNativeReasoning = true;
    }

    public boolean isSupportsStreaming() {
        return supportsStreaming;
    }

    public ModelCapabilities setSupportsStreaming(boolean supportsStreaming) {
        this.supportsStreaming = supportsStreaming;
        return this;
    }

    public boolean isSupportsTools() {
        return supportsTools;
    }

    public ModelCapabilities setSupportsTools(boolean supportsTools) {
        this.supportsTools = supportsTools;
        return this;
    }

    public boolean isSupportsVision() {
        return supportsVision;
    }

    public ModelCapabilities setSupportsVision(boolean supportsVision) {
        this.supportsVision = supportsVision;
        return this;
    }

    public boolean isSupportsAudio() {
        return supportsAudio;
    }

    public ModelCapabilities setSupportsAudio(boolean supportsAudio) {
        this.supportsAudio = supportsAudio;
        return this;
    }

    public boolean isSupportsStructuredOutput() {
        return supportsStructuredOutput;
    }

    public ModelCapabilities setSupportsStructuredOutput(boolean supportsStructuredOutput) {
        this.supportsStructuredOutput = supportsStructuredOutput;
        return this;
    }

    public boolean isSupportsNativeReasoning() {
        return supportsNativeReasoning;
    }

    public ModelCapabilities setSupportsNativeReasoning(boolean supportsNativeReasoning) {
        this.supportsNativeReasoning = supportsNativeReasoning;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelCapabilities that)) return false;
        return supportsStreaming == that.supportsStreaming &&
                supportsTools == that.supportsTools &&
                supportsVision == that.supportsVision &&
                supportsAudio == that.supportsAudio &&
                supportsStructuredOutput == that.supportsStructuredOutput &&
                supportsNativeReasoning == that.supportsNativeReasoning;
    }

    @Override
    public int hashCode() {
        return Objects.hash(supportsStreaming, supportsTools, supportsVision, supportsAudio,
                supportsStructuredOutput, supportsNativeReasoning);
    }

    @Override
    public String toString() {
        return "ModelCapabilities{" +
                "supportsStreaming=" + supportsStreaming +
                ", supportsTools=" + supportsTools +
                ", supportsVision=" + supportsVision +
                ", supportsNativeReasoning=" + supportsNativeReasoning +
                '}';
    }
}
