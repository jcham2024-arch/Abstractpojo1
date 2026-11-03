package com.apex.chat.core.event;

/**
 * Standard lifecycle event types emitted throughout chat and agent execution.
 */
public enum ChatEventType {
    CHAT_STARTED,
    CHAT_COMPLETED,
    TURN_STARTED,
    TURN_COMPLETED,
    THOUGHT_EMITTED,
    STEP_STARTED,
    STEP_COMPLETED,
    TOOL_INVOKED,
    TOOL_COMPLETED,
    AGENT_ERROR,
    TELEMETRY_RECORDED,
    SESSION_PAUSED,
    SESSION_RESUMED,
    CUSTOM
}
