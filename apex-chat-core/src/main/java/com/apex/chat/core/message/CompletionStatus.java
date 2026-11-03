package com.apex.chat.core.message;

/**
 * Terminal status of an LLM generation call.
 */
public enum CompletionStatus {
    SUCCESS,
    TRUNCATED_LENGTH,
    TOOL_CALL_REQUIRED,
    CONTENT_FILTERED,
    TIMED_OUT,
    ERROR
}
