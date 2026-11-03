package com.apex.chat.core.event;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Base POJO representing an observable lifecycle event during chat and agent workflows.
 */
public class ChatLifecycleEvent implements Serializable {

    private String eventId;
    private ChatEventType eventType;
    private Instant timestamp;
    private String chatId;
    private String sourceParticipantId;
    private String description;
    private Object payload;
    private Map<String, String> metadata;

    public ChatLifecycleEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.eventType = ChatEventType.CUSTOM;
        this.metadata = new HashMap<>();
    }

    public ChatLifecycleEvent(ChatEventType eventType, String chatId, String description) {
        this();
        this.eventType = eventType;
        this.chatId = chatId;
        this.description = description;
    }

    public String getEventId() {
        return eventId;
    }

    public ChatLifecycleEvent setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public ChatEventType getEventType() {
        return eventType;
    }

    public ChatLifecycleEvent setEventType(ChatEventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public ChatLifecycleEvent setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getChatId() {
        return chatId;
    }

    public ChatLifecycleEvent setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getSourceParticipantId() {
        return sourceParticipantId;
    }

    public ChatLifecycleEvent setSourceParticipantId(String sourceParticipantId) {
        this.sourceParticipantId = sourceParticipantId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ChatLifecycleEvent setDescription(String description) {
        this.description = description;
        return this;
    }

    public Object getPayload() {
        return payload;
    }

    public ChatLifecycleEvent setPayload(Object payload) {
        this.payload = payload;
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public ChatLifecycleEvent setMetadata(Map<String, String> metadata) {
        this.metadata = metadata != null ? metadata : new HashMap<>();
        return this;
    }

    public ChatLifecycleEvent addMetadata(String key, String value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatLifecycleEvent that)) return false;
        return Objects.equals(eventId, that.eventId) &&
                eventType == that.eventType &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(chatId, that.chatId) &&
                Objects.equals(sourceParticipantId, that.sourceParticipantId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(payload, that.payload) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventType, timestamp, chatId, sourceParticipantId, description, payload, metadata);
    }

    @Override
    public String toString() {
        return "ChatLifecycleEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType=" + eventType +
                ", chatId='" + chatId + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}
