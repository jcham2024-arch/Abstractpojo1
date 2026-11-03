package com.apex.chat.agent.guardrail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Result of a safety or policy evaluation performed on an agent's reasoning intent or planned action.
 */
public class AgentGuardrailEvaluation implements Serializable {

    private boolean passed;
    private List<String> violatedRules;
    private RiskLevel riskLevel;
    private String mitigationAction;

    public AgentGuardrailEvaluation() {
        this.passed = true;
        this.violatedRules = new ArrayList<>();
        this.riskLevel = RiskLevel.LOW;
    }

    public AgentGuardrailEvaluation(boolean passed, RiskLevel riskLevel) {
        this();
        this.passed = passed;
        this.riskLevel = riskLevel;
    }

    public static AgentGuardrailEvaluation pass() {
        return new AgentGuardrailEvaluation(true, RiskLevel.LOW);
    }

    public static AgentGuardrailEvaluation violation(String rule, RiskLevel riskLevel, String mitigationAction) {
        AgentGuardrailEvaluation eval = new AgentGuardrailEvaluation(false, riskLevel);
        eval.addViolatedRule(rule);
        eval.setMitigationAction(mitigationAction);
        return eval;
    }

    public boolean isPassed() {
        return passed;
    }

    public AgentGuardrailEvaluation setPassed(boolean passed) {
        this.passed = passed;
        return this;
    }

    public List<String> getViolatedRules() {
        return violatedRules;
    }

    public AgentGuardrailEvaluation setViolatedRules(List<String> violatedRules) {
        this.violatedRules = violatedRules != null ? violatedRules : new ArrayList<>();
        return this;
    }

    public AgentGuardrailEvaluation addViolatedRule(String rule) {
        if (this.violatedRules == null) {
            this.violatedRules = new ArrayList<>();
        }
        this.violatedRules.add(rule);
        return this;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public AgentGuardrailEvaluation setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
        return this;
    }

    public String getMitigationAction() {
        return mitigationAction;
    }

    public AgentGuardrailEvaluation setMitigationAction(String mitigationAction) {
        this.mitigationAction = mitigationAction;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentGuardrailEvaluation that)) return false;
        return passed == that.passed &&
                Objects.equals(violatedRules, that.violatedRules) &&
                riskLevel == that.riskLevel &&
                Objects.equals(mitigationAction, that.mitigationAction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passed, violatedRules, riskLevel, mitigationAction);
    }

    @Override
    public String toString() {
        return "AgentGuardrailEvaluation{" +
                "passed=" + passed +
                ", riskLevel=" + riskLevel +
                ", violationsCount=" + (violatedRules != null ? violatedRules.size() : 0) +
                '}';
    }
}
