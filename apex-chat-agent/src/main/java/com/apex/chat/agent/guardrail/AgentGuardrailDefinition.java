package com.apex.chat.agent.guardrail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * AgentGuardrailDefinition POJO encapsulating safety policies, rejection triggers,
 * and automated intervention strategies for agentic execution loops.
 */
public class AgentGuardrailDefinition implements Serializable {

    private String guardrailId;
    private String name;
    private String description;
    private List<SafetyPolicy> policies;
    private List<RejectionTrigger> triggers;
    private InterventionStrategy defaultStrategy;
    private InterventionStrategy escalatedStrategy;
    private String fallbackResponseText;
    private boolean enabled;

    public AgentGuardrailDefinition() {
        this.guardrailId = UUID.randomUUID().toString();
        this.policies = new ArrayList<>();
        this.triggers = new ArrayList<>();
        this.defaultStrategy = InterventionStrategy.RETRY_WITH_CORRECTION;
        this.escalatedStrategy = InterventionStrategy.BLOCK_AND_ABORT;
        this.fallbackResponseText = "Action halted due to security guardrail policy.";
        this.enabled = true;
    }

    public AgentGuardrailDefinition(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public String getGuardrailId() {
        return guardrailId;
    }

    public AgentGuardrailDefinition setGuardrailId(String guardrailId) {
        this.guardrailId = guardrailId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AgentGuardrailDefinition setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AgentGuardrailDefinition setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<SafetyPolicy> getPolicies() {
        return policies;
    }

    public AgentGuardrailDefinition setPolicies(List<SafetyPolicy> policies) {
        this.policies = policies != null ? policies : new ArrayList<>();
        return this;
    }

    public AgentGuardrailDefinition addPolicy(SafetyPolicy policy) {
        if (this.policies == null) {
            this.policies = new ArrayList<>();
        }
        this.policies.add(policy);
        return this;
    }

    public List<RejectionTrigger> getTriggers() {
        return triggers;
    }

    public AgentGuardrailDefinition setTriggers(List<RejectionTrigger> triggers) {
        this.triggers = triggers != null ? triggers : new ArrayList<>();
        return this;
    }

    public AgentGuardrailDefinition addTrigger(RejectionTrigger trigger) {
        if (this.triggers == null) {
            this.triggers = new ArrayList<>();
        }
        this.triggers.add(trigger);
        return this;
    }

    public InterventionStrategy getDefaultStrategy() {
        return defaultStrategy;
    }

    public AgentGuardrailDefinition setDefaultStrategy(InterventionStrategy defaultStrategy) {
        this.defaultStrategy = defaultStrategy;
        return this;
    }

    public InterventionStrategy getEscalatedStrategy() {
        return escalatedStrategy;
    }

    public AgentGuardrailDefinition setEscalatedStrategy(InterventionStrategy escalatedStrategy) {
        this.escalatedStrategy = escalatedStrategy;
        return this;
    }

    public String getFallbackResponseText() {
        return fallbackResponseText;
    }

    public AgentGuardrailDefinition setFallbackResponseText(String fallbackResponseText) {
        this.fallbackResponseText = fallbackResponseText;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AgentGuardrailDefinition setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentGuardrailDefinition that)) return false;
        return enabled == that.enabled &&
                Objects.equals(guardrailId, that.guardrailId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(policies, that.policies) &&
                Objects.equals(triggers, that.triggers) &&
                defaultStrategy == that.defaultStrategy &&
                escalatedStrategy == that.escalatedStrategy &&
                Objects.equals(fallbackResponseText, that.fallbackResponseText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guardrailId, name, description, policies, triggers, defaultStrategy, escalatedStrategy, fallbackResponseText, enabled);
    }

    @Override
    public String toString() {
        return "AgentGuardrailDefinition{" +
                "guardrailId='" + guardrailId + '\'' +
                ", name='" + name + '\'' +
                ", policiesCount=" + (policies != null ? policies.size() : 0) +
                ", triggersCount=" + (triggers != null ? triggers.size() : 0) +
                ", defaultStrategy=" + defaultStrategy +
                ", enabled=" + enabled +
                '}';
    }
}
