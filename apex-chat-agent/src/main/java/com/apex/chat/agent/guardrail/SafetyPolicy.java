package com.apex.chat.agent.guardrail;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Concrete safety policy specifying a governed domain rule, compliance constraint, or security requirement.
 */
public class SafetyPolicy implements Serializable {

    private String policyId;
    private String policyName;
    private String category;
    private String description;
    private boolean strictEnforcement;

    public SafetyPolicy() {
        this.policyId = UUID.randomUUID().toString();
        this.category = "General Security";
        this.strictEnforcement = true;
    }

    public SafetyPolicy(String policyName, String category, String description) {
        this();
        this.policyName = policyName;
        this.category = category;
        this.description = description;
    }

    public String getPolicyId() {
        return policyId;
    }

    public SafetyPolicy setPolicyId(String policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getPolicyName() {
        return policyName;
    }

    public SafetyPolicy setPolicyName(String policyName) {
        this.policyName = policyName;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public SafetyPolicy setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SafetyPolicy setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isStrictEnforcement() {
        return strictEnforcement;
    }

    public SafetyPolicy setStrictEnforcement(boolean strictEnforcement) {
        this.strictEnforcement = strictEnforcement;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SafetyPolicy that)) return false;
        return strictEnforcement == that.strictEnforcement &&
                Objects.equals(policyId, that.policyId) &&
                Objects.equals(policyName, that.policyName) &&
                Objects.equals(category, that.category) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId, policyName, category, description, strictEnforcement);
    }

    @Override
    public String toString() {
        return "SafetyPolicy{" +
                "policyId='" + policyId + '\'' +
                ", policyName='" + policyName + '\'' +
                ", category='" + category + '\'' +
                ", strict=" + strictEnforcement +
                '}';
    }
}
