package com.apex.chat.agent.guardrail;

/**
 * Types of condition checks that can trigger a guardrail rejection or intervention.
 */
public enum TriggerType {
    REGEX_MATCH,
    FORBIDDEN_TOOL_CALL,
    PARAMETER_OUT_OF_BOUNDS,
    SEMANTIC_SIMILARITY,
    TOKEN_BUDGET_OVERRUN,
    REASONING_DRIFT,
    CUSTOM_EXPRESSION
}
