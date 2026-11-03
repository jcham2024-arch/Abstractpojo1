package com.apex.chat.agent.step;

/**
 * Outcome and execution directive determined after completing an individual agent step.
 */
public enum StepOutcome {
    CONTINUE,
    RETRY,
    REVISE_PLAN,
    COMPLETE_TURN,
    HALT_ERROR
}
