package com.apex.chat.agent.core;

/**
 * Operational state of an autonomous agent during intentional execution.
 */
public enum AgentStatus {
    IDLE,
    DELIBERATING,
    EXECUTING_TOOL,
    AWAITING_HUMAN_CONFIRMATION,
    REFLECTING,
    COMPLETED,
    ABORTED
}
