package com.apex.chat.agent.constraint;

/**
 * Reasons why an agent execution loop terminates.
 */
public enum TerminationReason {
    GOAL_ACHIEVED,
    MAX_STEPS_REACHED,
    BUDGET_EXCEEDED,
    USER_ABORTED,
    POLICY_VIOLATION
}
