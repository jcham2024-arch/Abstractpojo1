package com.apex.chat.core.content;

/**
 * Types of prompt directives that can be injected into a chat turn.
 */
public enum DirectiveType {
    SYSTEM_CORRECTION,
    TASK_CONSTRAINTS,
    BEHAVIORAL_GUARDRAIL,
    TOOL_GUIDANCE
}
