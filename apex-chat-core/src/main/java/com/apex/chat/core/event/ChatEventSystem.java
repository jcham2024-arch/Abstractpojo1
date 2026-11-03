package com.apex.chat.core.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * EventSystem POJO handling lifecycle events, observer registration, event auditing,
 * and reactive hook execution throughout chat and agent workflows.
 */
public class ChatEventSystem implements Serializable {

    private String systemId;
    private ChatEventRegistry registry;
    private boolean enabled;
    private List<ChatLifecycleEvent> eventHistory;
    private int maxHistorySize;

    public ChatEventSystem() {
        this.systemId = UUID.randomUUID().toString();
        this.registry = new ChatEventRegistry();
        this.enabled = true;
        this.eventHistory = new CopyOnWriteArrayList<>();
        this.maxHistorySize = 500;
    }

    public ChatEventSystem(String systemId) {
        this();
        this.systemId = systemId;
    }

    public String getSystemId() {
        return systemId;
    }

    public ChatEventSystem setSystemId(String systemId) {
        this.systemId = systemId;
        return this;
    }

    public ChatEventRegistry getRegistry() {
        return registry;
    }

    public ChatEventSystem setRegistry(ChatEventRegistry registry) {
        this.registry = registry != null ? registry : new ChatEventRegistry();
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ChatEventSystem setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public List<ChatLifecycleEvent> getEventHistory() {
        return eventHistory;
    }

    public ChatEventSystem setEventHistory(List<ChatLifecycleEvent> eventHistory) {
        this.eventHistory = eventHistory != null ? new CopyOnWriteArrayList<>(eventHistory) : new CopyOnWriteArrayList<>();
        return this;
    }

    public int getMaxHistorySize() {
        return maxHistorySize;
    }

    public ChatEventSystem setMaxHistorySize(int maxHistorySize) {
        this.maxHistorySize = maxHistorySize;
        return this;
    }

    /**
     * Publishes a lifecycle event through this system.
     * If enabled, appends to the audit history and triggers registered listeners in the registry.
     *
     * @param event the lifecycle event to publish
     * @return this event system instance for fluent chaining
     */
    public ChatEventSystem publish(ChatLifecycleEvent event) {
        if (!this.enabled || event == null) {
            return this;
        }

        // Record in history
        if (this.eventHistory.size() >= this.maxHistorySize && this.maxHistorySize > 0) {
            this.eventHistory.remove(0);
        }
        this.eventHistory.add(event);

        // Notify observers in registry
        if (this.registry != null) {
            this.registry.notifyEvent(event);
        }

        return this;
    }

    /**
     * Convenience method to register a listener on this system's registry.
     *
     * @param listener the listener to register
     * @return this event system instance for fluent chaining
     */
    public ChatEventSystem addListener(ChatEventListener listener) {
        if (this.registry != null && listener != null) {
            this.registry.registerListener(listener);
        }
        return this;
    }

    /**
     * Convenience method to register a type-filtered listener on this system's registry.
     *
     * @param eventType the event type filter
     * @param listener  the listener to register
     * @return this event system instance for fluent chaining
     */
    public ChatEventSystem addListener(ChatEventType eventType, ChatEventListener listener) {
        if (this.registry != null && listener != null) {
            this.registry.registerListener(eventType, listener);
        }
        return this;
    }

    /**
     * Clears all recorded history.
     *
     * @return this event system instance for fluent chaining
     */
    public ChatEventSystem clearHistory() {
        this.eventHistory.clear();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatEventSystem that)) return false;
        return enabled == that.enabled &&
                maxHistorySize == that.maxHistorySize &&
                Objects.equals(systemId, that.systemId) &&
                Objects.equals(registry, that.registry) &&
                Objects.equals(eventHistory, that.eventHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemId, registry, enabled, eventHistory, maxHistorySize);
    }

    @Override
    public String toString() {
        return "ChatEventSystem{" +
                "systemId='" + systemId + '\'' +
                ", enabled=" + enabled +
                ", recordedEventsCount=" + eventHistory.size() +
                ", listenersCount=" + (registry != null ? registry.getTotalListenersCount() : 0) +
                '}';
    }
}
