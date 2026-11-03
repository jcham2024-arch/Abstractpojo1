package com.apex.chat.core.event;

import java.util.Objects;

/**
 * Event emitted when a chat interaction session or turn is initialized.
 */
public class ChatStartedEvent extends ChatLifecycleEvent {

    private String initialUserPrompt;
    private String primaryModelName;

    public ChatStartedEvent() {
        super();
        setEventType(ChatEventType.CHAT_STARTED);
    }

    public ChatStartedEvent(String chatId, String initialUserPrompt, String primaryModelName) {
        super(ChatEventType.CHAT_STARTED, chatId, "Chat session started");
        this.initialUserPrompt = initialUserPrompt;
        this.primaryModelName = primaryModelName;
    }

    public String getInitialUserPrompt() {
        return initialUserPrompt;
    }

    public ChatStartedEvent setInitialUserPrompt(String initialUserPrompt) {
        this.initialUserPrompt = initialUserPrompt;
        return this;
    }

    public String getPrimaryModelName() {
        return primaryModelName;
    }

    public ChatStartedEvent setPrimaryModelName(String primaryModelName) {
        this.primaryModelName = primaryModelName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatStartedEvent that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(initialUserPrompt, that.initialUserPrompt) &&
                Objects.equals(primaryModelName, that.primaryModelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), initialUserPrompt, primaryModelName);
    }

    @Override
    public String toString() {
        return "ChatStartedEvent{" +
                "chatId='" + getChatId() + '\'' +
                ", initialUserPrompt='" + initialUserPrompt + '\'' +
                ", primaryModelName='" + primaryModelName + '\'' +
                '}';
    }
}
