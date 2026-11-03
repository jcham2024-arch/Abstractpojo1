package com.apex.chat.core.prompt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base behavioral instructions and formatting guidelines provided to the model.
 */
public class SystemPrompt implements Serializable {

    private String coreInstructions;
    private List<String> behavioralRules;
    private List<String> formattingGuidelines;

    public SystemPrompt() {
        this.coreInstructions = "You are a helpful and precise assistant.";
        this.behavioralRules = new ArrayList<>();
        this.formattingGuidelines = new ArrayList<>();
    }

    public SystemPrompt(String coreInstructions) {
        this();
        this.coreInstructions = coreInstructions;
    }

    public String getCoreInstructions() {
        return coreInstructions;
    }

    public SystemPrompt setCoreInstructions(String coreInstructions) {
        this.coreInstructions = coreInstructions;
        return this;
    }

    public List<String> getBehavioralRules() {
        return behavioralRules;
    }

    public SystemPrompt setBehavioralRules(List<String> behavioralRules) {
        this.behavioralRules = behavioralRules != null ? behavioralRules : new ArrayList<>();
        return this;
    }

    public SystemPrompt addBehavioralRule(String rule) {
        if (this.behavioralRules == null) {
            this.behavioralRules = new ArrayList<>();
        }
        this.behavioralRules.add(rule);
        return this;
    }

    public List<String> getFormattingGuidelines() {
        return formattingGuidelines;
    }

    public SystemPrompt setFormattingGuidelines(List<String> formattingGuidelines) {
        this.formattingGuidelines = formattingGuidelines != null ? formattingGuidelines : new ArrayList<>();
        return this;
    }

    public SystemPrompt addFormattingGuideline(String guideline) {
        if (this.formattingGuidelines == null) {
            this.formattingGuidelines = new ArrayList<>();
        }
        this.formattingGuidelines.add(guideline);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemPrompt that)) return false;
        return Objects.equals(coreInstructions, that.coreInstructions) &&
                Objects.equals(behavioralRules, that.behavioralRules) &&
                Objects.equals(formattingGuidelines, that.formattingGuidelines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coreInstructions, behavioralRules, formattingGuidelines);
    }

    @Override
    public String toString() {
        return "SystemPrompt{" +
                "coreInstructions='" + coreInstructions + '\'' +
                ", rulesCount=" + (behavioralRules != null ? behavioralRules.size() : 0) +
                '}';
    }
}
