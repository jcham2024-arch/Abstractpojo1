package com.apex.chat.core.persistence;

/**
 * Storage strategies available for persisting chat histories and agent execution states.
 */
public enum StorageStrategy {
    MEMORY,
    FILE_SYSTEM,
    RELATIONAL_DATABASE,
    DOCUMENT_STORE,
    KEY_VALUE_STORE,
    CONTENT_ADDRESSABLE_STORAGE,
    HYBRID
}
