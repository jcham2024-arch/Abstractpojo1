package com.apex.chat.agent.guardrail;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Deterministic or semantic trigger that flags a policy violation during agent input, thought, or tool calls.
 */
public class RejectionTrigger implements Serializable {

    private String triggerId;
    private String name;
    private TriggerType triggerType;
    private String patternOrCondition;
    private RiskLevel severity;
    private String description;

    public RejectionTrigger() {
        this.triggerId = UUID.randomUUID().toString();
        this.triggerType = TriggerType.FORBIDDEN_TOOL_CALL;
        this.severity = RiskLevel.HIGH;
    }

    public RejectionTrigger(String name, TriggerType triggerType, String patternOrCondition, RiskLevel severity) {
        this();
        this.name = name;
        this.triggerType = triggerType;
        this.patternOrCondition = patternOrCondition;
        this.severity = severity;
    }

    public static RejectionTrigger forbiddenTool(String toolName, RiskLevel severity) {
        return new RejectionTrigger("ForbiddenTool:" + toolName, TriggerType.FORBIDDEN_TOOL_CALL, toolName, severity);
    }

    public static RejectionTrigger regex(String name, String regexPattern, RiskLevel severity) {
        return new RejectionTrigger(name, TriggerType.REGEX_MATCH, regexPattern, severity);
    }

    public String getTriggerId() {
        return triggerId;
    }

    public RejectionTrigger setTriggerId(String triggerId) {
        this.triggerId = triggerId;
        return this;
    }

    public String getName() {
        return name;
    }

    public RejectionTrigger setName(String name) {
        this.name = name;
        return this;
    }

    public TriggerType getTriggerType() {
        return triggerType;
    }

    public RejectionTrigger setTriggerType(TriggerType triggerType) {
        this.triggerType = triggerType;
        return this;
    }

    public String getPatternOrCondition() {
        return patternOrCondition;
    }

    public RejectionTrigger setPatternOrCondition(String patternOrCondition) {
        this.patternOrCondition = patternOrCondition;
        return this;
    }

    public RiskLevel getSeverity() {
        return severity;
    }

    public RejectionTrigger setSeverity(RiskLevel severity) {
        this.severity = severity;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RejectionTrigger setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RejectionTrigger that)) return false;
        return Objects.equals(triggerId, that.triggerId) &&
                Objects.equals(name, that.name) &&
                triggerType == that.triggerType &&
                Objects.equals(patternOrCondition, that.patternOrCondition) &&
                severity == that.severity &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(triggerId, name, triggerType, patternOrCondition, severity, description);
    }

    @Override
    public String toString() {
        return "RejectionTrigger{" +
                "triggerId='" + triggerId + '\'' +
                ", name='" + name + '\'' +
                ", triggerType=" + triggerType +
                ", severity=" + severity +
                '}';
    }
}
