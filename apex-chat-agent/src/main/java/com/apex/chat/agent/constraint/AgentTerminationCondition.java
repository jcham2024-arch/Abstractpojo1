package com.apex.chat.agent.constraint;

import java.io.Serializable;
import java.util.Objects;

/**
 * Details of how and why an agent execution reached a termination state.
 */
public class AgentTerminationCondition implements Serializable {

    private TerminationReason reason;
    private String explanation;

    public AgentTerminationCondition() {
        this.reason = TerminationReason.GOAL_ACHIEVED;
        this.explanation = "Goal successfully completed.";
    }

    public AgentTerminationCondition(TerminationReason reason, String explanation) {
        this.reason = reason;
        this.explanation = explanation;
    }

    public TerminationReason getReason() {
        return reason;
    }

    public AgentTerminationCondition setReason(TerminationReason reason) {
        this.reason = reason;
        return this;
    }

    public String getExplanation() {
        return explanation;
    }

    public AgentTerminationCondition setExplanation(String explanation) {
        this.explanation = explanation;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentTerminationCondition that)) return false;
        return reason == that.reason && Objects.equals(explanation, that.explanation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason, explanation);
    }

    @Override
    public String toString() {
        return "AgentTerminationCondition{" +
                "reason=" + reason +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
