package com.apex.chat.core.event;

import java.util.EventListener;

/**
 * Observer interface for listening to chat and agent lifecycle events.
 */
@FunctionalInterface
public interface ChatEventListener extends EventListener {

    /**
     * Callback invoked when a lifecycle event is published.
     *
     * @param event the published lifecycle event
     */
    void onEvent(ChatLifecycleEvent event);

    /**
     * Determines whether this listener is interested in the given event type.
     * Defaults to true (listens to all events).
     *
     * @param eventType the event type to check
     * @return true if this listener should receive the event
     */
    default boolean supports(ChatEventType eventType) {
        return true;
    }
}
