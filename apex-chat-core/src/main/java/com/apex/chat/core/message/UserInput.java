package com.apex.chat.core.message;

import com.apex.chat.core.content.ContentBlock;
import com.apex.chat.core.content.TextBlock;
import com.apex.chat.core.participant.ChatUser;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Concrete submission event emitted by a human user in the chat.
 */
public class UserInput implements Serializable {

    private String inputId;
    private ChatUser author;
    private Instant timestamp;
    private InputModality modality;
    private List<ContentBlock> blocks;
    private Map<String, String> metadata;

    public UserInput() {
        this.inputId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.modality = InputModality.TEXT;
        this.blocks = new ArrayList<>();
        this.metadata = new HashMap<>();
    }

    public UserInput(ChatUser author, String text) {
        this();
        this.author = author;
        this.blocks.add(TextBlock.of(text));
    }

    public String getInputId() {
        return inputId;
    }

    public UserInput setInputId(String inputId) {
        this.inputId = inputId;
        return this;
    }

    public ChatUser getAuthor() {
        return author;
    }

    public UserInput setAuthor(ChatUser author) {
        this.author = author;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public UserInput setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public InputModality getModality() {
        return modality;
    }

    public UserInput setModality(InputModality modality) {
        this.modality = modality;
        return this;
    }

    public List<ContentBlock> getBlocks() {
        return blocks;
    }

    public UserInput setBlocks(List<ContentBlock> blocks) {
        this.blocks = blocks != null ? blocks : new ArrayList<>();
        return this;
    }

    public UserInput addBlock(ContentBlock block) {
        if (this.blocks == null) {
            this.blocks = new ArrayList<>();
        }
        this.blocks.add(block);
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public UserInput setMetadata(Map<String, String> metadata) {
        this.metadata = metadata != null ? metadata : new HashMap<>();
        return this;
    }

    public UserInput addMetadata(String key, String value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInput userInput)) return false;
        return Objects.equals(inputId, userInput.inputId) &&
                Objects.equals(author, userInput.author) &&
                Objects.equals(timestamp, userInput.timestamp) &&
                modality == userInput.modality &&
                Objects.equals(blocks, userInput.blocks) &&
                Objects.equals(metadata, userInput.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputId, author, timestamp, modality, blocks, metadata);
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "inputId='" + inputId + '\'' +
                ", author=" + (author != null ? author.getDisplayName() : "null") +
                ", modality=" + modality +
                ", blocksCount=" + (blocks != null ? blocks.size() : 0) +
                '}';
    }
}
