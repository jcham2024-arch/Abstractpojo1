package com.apex.chat.agent.guardrail;

/**
 * Automated intervention strategies invoked when a guardrail condition or risk trigger is breached.
 */
public enum InterventionStrategy {
    BLOCK_AND_ABORT,
    RETRY_WITH_CORRECTION,
    REQUIRE_HUMAN_CONFIRMATION,
    SANITIZE_INPUT,
    FALLBACK_RESPONSE,
    LOG_AND_CONTINUE
}
