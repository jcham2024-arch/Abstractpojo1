package com.apex.chat.core.prompt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Encapsulates the complete prompting configuration for a chat session.
 */
public class PromptContext implements Serializable {

    private SystemPrompt systemPrompt;
    private String personaInstructions;
    private Map<String, String> dynamicVariables;
    private List<String> activeGuardrails;

    public PromptContext() {
        this.systemPrompt = new SystemPrompt();
        this.dynamicVariables = new HashMap<>();
        this.activeGuardrails = new ArrayList<>();
    }

    public PromptContext(SystemPrompt systemPrompt) {
        this();
        this.systemPrompt = systemPrompt;
    }

    public SystemPrompt getSystemPrompt() {
        return systemPrompt;
    }

    public PromptContext setSystemPrompt(SystemPrompt systemPrompt) {
        this.systemPrompt = systemPrompt != null ? systemPrompt : new SystemPrompt();
        return this;
    }

    public String getPersonaInstructions() {
        return personaInstructions;
    }

    public PromptContext setPersonaInstructions(String personaInstructions) {
        this.personaInstructions = personaInstructions;
        return this;
    }

    public Map<String, String> getDynamicVariables() {
        return dynamicVariables;
    }

    public PromptContext setDynamicVariables(Map<String, String> dynamicVariables) {
        this.dynamicVariables = dynamicVariables != null ? dynamicVariables : new HashMap<>();
        return this;
    }

    public PromptContext addDynamicVariable(String key, String value) {
        if (this.dynamicVariables == null) {
            this.dynamicVariables = new HashMap<>();
        }
        this.dynamicVariables.put(key, value);
        return this;
    }

    public List<String> getActiveGuardrails() {
        return activeGuardrails;
    }

    public PromptContext setActiveGuardrails(List<String> activeGuardrails) {
        this.activeGuardrails = activeGuardrails != null ? activeGuardrails : new ArrayList<>();
        return this;
    }

    public PromptContext addActiveGuardrail(String guardrail) {
        if (this.activeGuardrails == null) {
            this.activeGuardrails = new ArrayList<>();
        }
        this.activeGuardrails.add(guardrail);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromptContext that)) return false;
        return Objects.equals(systemPrompt, that.systemPrompt) &&
                Objects.equals(personaInstructions, that.personaInstructions) &&
                Objects.equals(dynamicVariables, that.dynamicVariables) &&
                Objects.equals(activeGuardrails, that.activeGuardrails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemPrompt, personaInstructions, dynamicVariables, activeGuardrails);
    }

    @Override
    public String toString() {
        return "PromptContext{" +
                "systemPrompt=" + systemPrompt +
                ", variablesCount=" + (dynamicVariables != null ? dynamicVariables.size() : 0) +
                ", guardrailsCount=" + (activeGuardrails != null ? activeGuardrails.size() : 0) +
                '}';
    }
}
