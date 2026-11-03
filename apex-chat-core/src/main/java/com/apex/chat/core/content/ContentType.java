package com.apex.chat.core.content;

/**
 * Enumeration of content block types supported in chat exchanges.
 */
public enum ContentType {
    TEXT,
    BINARY_MEDIA,
    FILE_DOCUMENT,
    PROMPT_DIRECTIVE,
    CONTEXT_ENRICHMENT,
    THOUGHT_STRUCTURED,
    TOOL_CALL,
    TOOL_RESULT
}
