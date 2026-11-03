package com.apex.chat.core.thinking;

/**
 * Validation state of an internal thinking checkpoint.
 */
public enum CheckpointStatus {
    VALID,
    WARNING_RAISED,
    VIOLATION_DETECTED,
    UNVERIFIED
}
