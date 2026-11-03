package com.apex.chat.core.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Registry holding and managing event observers/listeners across the chat lifecycle.
 */
public class ChatEventRegistry implements Serializable {

    private final List<ChatEventListener> globalListeners;
    private final Map<ChatEventType, List<ChatEventListener>> typeSpecificListeners;

    public ChatEventRegistry() {
        this.globalListeners = new CopyOnWriteArrayList<>();
        this.typeSpecificListeners = new ConcurrentHashMap<>();
    }

    /**
     * Registers a global listener that observes all supported event types.
     *
     * @param listener the listener to register
     * @return this registry instance for fluent chaining
     */
    public ChatEventRegistry registerListener(ChatEventListener listener) {
        if (listener != null && !this.globalListeners.contains(listener)) {
            this.globalListeners.add(listener);
        }
        return this;
    }

    /**
     * Registers a listener specifically for a specific event type.
     *
     * @param eventType the targeted event type
     * @param listener  the listener to register
     * @return this registry instance for fluent chaining
     */
    public ChatEventRegistry registerListener(ChatEventType eventType, ChatEventListener listener) {
        if (eventType != null && listener != null) {
            this.typeSpecificListeners
                    .computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                    .add(listener);
        }
        return this;
    }

    /**
     * Unregisters a listener from both global and type-specific subscriptions.
     *
     * @param listener the listener to remove
     * @return this registry instance for fluent chaining
     */
    public ChatEventRegistry unregisterListener(ChatEventListener listener) {
        if (listener != null) {
            this.globalListeners.remove(listener);
            for (List<ChatEventListener> list : this.typeSpecificListeners.values()) {
                list.remove(listener);
            }
        }
        return this;
    }

    /**
     * Dispatches an event to all interested global and type-specific listeners.
     * Exceptions from individual listeners are caught to prevent pipeline disruption.
     *
     * @param event the event to dispatch
     */
    public void notifyEvent(ChatLifecycleEvent event) {
        if (event == null) return;

        // 1. Notify global listeners
        for (ChatEventListener listener : this.globalListeners) {
            try {
                if (listener.supports(event.getEventType())) {
                    listener.onEvent(event);
                }
            } catch (Throwable ignored) {
                // Keep event bus resilient
            }
        }

        // 2. Notify type-specific listeners
        List<ChatEventListener> specific = this.typeSpecificListeners.get(event.getEventType());
        if (specific != null) {
            for (ChatEventListener listener : specific) {
                try {
                    listener.onEvent(event);
                } catch (Throwable ignored) {
                    // Keep event bus resilient
                }
            }
        }
    }

    public List<ChatEventListener> getGlobalListeners() {
        return Collections.unmodifiableList(globalListeners);
    }

    public int getTotalListenersCount() {
        int count = this.globalListeners.size();
        for (List<ChatEventListener> list : this.typeSpecificListeners.values()) {
            count += list.size();
        }
        return count;
    }

    public ChatEventRegistry clearListeners() {
        this.globalListeners.clear();
        this.typeSpecificListeners.clear();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatEventRegistry that)) return false;
        return Objects.equals(globalListeners, that.globalListeners) &&
                Objects.equals(typeSpecificListeners, that.typeSpecificListeners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalListeners, typeSpecificListeners);
    }

    @Override
    public String toString() {
        return "ChatEventRegistry{" +
                "globalListenersCount=" + globalListeners.size() +
                ", typeSpecificCategoriesCount=" + typeSpecificListeners.size() +
                '}';
    }
}
