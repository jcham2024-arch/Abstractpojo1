package com.apex.chat.core.thread;

/**
 * Processing state of a conversation turn.
 */
public enum TurnStatus {
    PENDING,
    RUNNING,
    COMPLETED,
    FAILED
}
