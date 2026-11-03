package com.apex.chat.core.thread;

/**
 * Strategy recommendation for managing and pruning conversation context when approaching token limits.
 */
public enum PruningAction {
    NONE,
    SLIDING_WINDOW_DROP,
    SUMMARIZE_HISTORY,
    RAG_SELECTIVE
}
