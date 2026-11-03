package com.apex.chat.core.session;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Lifecycle and metadata container for a chat interaction session.
 */
public class ChatSession implements Serializable {

    private String sessionId;
    private String sessionTitle;
    private Instant createdAt;
    private Instant lastActiveAt;
    private SessionStatus status;
    private Map<String, String> metadata;

    public ChatSession() {
        this.sessionId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
        this.lastActiveAt = this.createdAt;
        this.status = SessionStatus.ACTIVE;
        this.metadata = new HashMap<>();
    }

    public ChatSession(String sessionTitle) {
        this();
        this.sessionTitle = sessionTitle;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ChatSession setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getSessionTitle() {
        return sessionTitle;
    }

    public ChatSession setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public ChatSession setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getLastActiveAt() {
        return lastActiveAt;
    }

    public ChatSession setLastActiveAt(Instant lastActiveAt) {
        this.lastActiveAt = lastActiveAt;
        return this;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public ChatSession setStatus(SessionStatus status) {
        this.status = status;
        return this;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public ChatSession setMetadata(Map<String, String> metadata) {
        this.metadata = metadata != null ? metadata : new HashMap<>();
        return this;
    }

    public ChatSession addMetadata(String key, String value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatSession that)) return false;
        return Objects.equals(sessionId, that.sessionId) &&
                Objects.equals(sessionTitle, that.sessionTitle) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(lastActiveAt, that.lastActiveAt) &&
                status == that.status &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, sessionTitle, createdAt, lastActiveAt, status, metadata);
    }

    @Override
    public String toString() {
        return "ChatSession{" +
                "sessionId='" + sessionId + '\'' +
                ", sessionTitle='" + sessionTitle + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", lastActiveAt=" + lastActiveAt +
                '}';
    }
}
