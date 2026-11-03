package com.apex.chat.agent.constraint;

import java.io.Serializable;
import java.util.Objects;

/**
 * Budget boundaries and guardrails constraining the autonomous loop of an agent.
 */
public class AgentExecutionConstraints implements Serializable {

    private int maxStepsPerTurn;
    private long maxTotalExecutionTimeMs;
    private int maxTokenBudget;
    private Double maxCostBudgetUsd;

    public AgentExecutionConstraints() {
        this.maxStepsPerTurn = 10;
        this.maxTotalExecutionTimeMs = 60000L; // 60s
        this.maxTokenBudget = 32768;
        this.maxCostBudgetUsd = 1.0;
    }

    public int getMaxStepsPerTurn() {
        return maxStepsPerTurn;
    }

    public AgentExecutionConstraints setMaxStepsPerTurn(int maxStepsPerTurn) {
        this.maxStepsPerTurn = maxStepsPerTurn;
        return this;
    }

    public long getMaxTotalExecutionTimeMs() {
        return maxTotalExecutionTimeMs;
    }

    public AgentExecutionConstraints setMaxTotalExecutionTimeMs(long maxTotalExecutionTimeMs) {
        this.maxTotalExecutionTimeMs = maxTotalExecutionTimeMs;
        return this;
    }

    public int getMaxTokenBudget() {
        return maxTokenBudget;
    }

    public AgentExecutionConstraints setMaxTokenBudget(int maxTokenBudget) {
        this.maxTokenBudget = maxTokenBudget;
        return this;
    }

    public Double getMaxCostBudgetUsd() {
        return maxCostBudgetUsd;
    }

    public AgentExecutionConstraints setMaxCostBudgetUsd(Double maxCostBudgetUsd) {
        this.maxCostBudgetUsd = maxCostBudgetUsd;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentExecutionConstraints that)) return false;
        return maxStepsPerTurn == that.maxStepsPerTurn &&
                maxTotalExecutionTimeMs == that.maxTotalExecutionTimeMs &&
                maxTokenBudget == that.maxTokenBudget &&
                Objects.equals(maxCostBudgetUsd, that.maxCostBudgetUsd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxStepsPerTurn, maxTotalExecutionTimeMs, maxTokenBudget, maxCostBudgetUsd);
    }

    @Override
    public String toString() {
        return "AgentExecutionConstraints{" +
                "maxStepsPerTurn=" + maxStepsPerTurn +
                ", maxTotalExecutionTimeMs=" + maxTotalExecutionTimeMs +
                ", maxTokenBudget=" + maxTokenBudget +
                ", maxCostBudgetUsd=" + maxCostBudgetUsd +
                '}';
    }
}
