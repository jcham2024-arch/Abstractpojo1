package com.apex.chat.core.message;

import com.apex.chat.core.content.ContentBlock;
import com.apex.chat.core.content.TextBlock;
import com.apex.chat.core.model.LlmModelParticipant;
import com.apex.chat.core.thinking.ThoughtBlock;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Complete model generation payload produced by an LLM in response to a prompt.
 */
public class ModelOutput implements Serializable {

    private String outputId;
    private String targetInputId;
    private LlmModelParticipant generator;
    private Instant timestamp;
    private CompletionStatus status;
    private List<ContentBlock> blocks;
    private GenerationTelemetry telemetry;

    public ModelOutput() {
        this.outputId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.status = CompletionStatus.SUCCESS;
        this.blocks = new ArrayList<>();
        this.telemetry = new GenerationTelemetry();
    }

    public ModelOutput(LlmModelParticipant generator, String text) {
        this();
        this.generator = generator;
        this.blocks.add(TextBlock.of(text));
    }

    public String getOutputId() {
        return outputId;
    }

    public ModelOutput setOutputId(String outputId) {
        this.outputId = outputId;
        return this;
    }

    public String getTargetInputId() {
        return targetInputId;
    }

    public ModelOutput setTargetInputId(String targetInputId) {
        this.targetInputId = targetInputId;
        return this;
    }

    public LlmModelParticipant getGenerator() {
        return generator;
    }

    public ModelOutput setGenerator(LlmModelParticipant generator) {
        this.generator = generator;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public ModelOutput setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public CompletionStatus getStatus() {
        return status;
    }

    public ModelOutput setStatus(CompletionStatus status) {
        this.status = status;
        return this;
    }

    public List<ContentBlock> getBlocks() {
        return blocks;
    }

    public ModelOutput setBlocks(List<ContentBlock> blocks) {
        this.blocks = blocks != null ? blocks : new ArrayList<>();
        return this;
    }

    public ModelOutput addBlock(ContentBlock block) {
        if (this.blocks == null) {
            this.blocks = new ArrayList<>();
        }
        this.blocks.add(block);
        return this;
    }

    public ThoughtBlock getThoughtBlock() {
        if (this.blocks == null) return null;
        for (ContentBlock block : this.blocks) {
            if (block instanceof ThoughtBlock tb) {
                return tb;
            }
        }
        return null;
    }

    public String getFirstTextContent() {
        if (this.blocks == null) return null;
        for (ContentBlock block : this.blocks) {
            if (block instanceof TextBlock tb) {
                return tb.getText();
            }
        }
        return null;
    }

    public GenerationTelemetry getTelemetry() {
        return telemetry;
    }

    public ModelOutput setTelemetry(GenerationTelemetry telemetry) {
        this.telemetry = telemetry != null ? telemetry : new GenerationTelemetry();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelOutput that)) return false;
        return Objects.equals(outputId, that.outputId) &&
                Objects.equals(targetInputId, that.targetInputId) &&
                Objects.equals(generator, that.generator) &&
                Objects.equals(timestamp, that.timestamp) &&
                status == that.status &&
                Objects.equals(blocks, that.blocks) &&
                Objects.equals(telemetry, that.telemetry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outputId, targetInputId, generator, timestamp, status, blocks, telemetry);
    }

    @Override
    public String toString() {
        return "ModelOutput{" +
                "outputId='" + outputId + '\'' +
                ", status=" + status +
                ", blocksCount=" + (blocks != null ? blocks.size() : 0) +
                ", telemetry=" + telemetry +
                '}';
    }
}
