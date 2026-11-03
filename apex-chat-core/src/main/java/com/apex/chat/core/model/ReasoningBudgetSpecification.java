package com.apex.chat.core.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Budget and effort settings for thinking/reasoning enabled models.
 */
public class ReasoningBudgetSpecification implements Serializable {

    private ReasoningEffort effort;
    private Integer budgetTokens;

    public ReasoningBudgetSpecification() {
        this.effort = ReasoningEffort.NONE;
    }

    public ReasoningBudgetSpecification(ReasoningEffort effort, Integer budgetTokens) {
        this.effort = effort;
        this.budgetTokens = budgetTokens;
    }

    public ReasoningEffort getEffort() {
        return effort;
    }

    public ReasoningBudgetSpecification setEffort(ReasoningEffort effort) {
        this.effort = effort;
        return this;
    }

    public Integer getBudgetTokens() {
        return budgetTokens;
    }

    public ReasoningBudgetSpecification setBudgetTokens(Integer budgetTokens) {
        this.budgetTokens = budgetTokens;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReasoningBudgetSpecification that)) return false;
        return effort == that.effort && Objects.equals(budgetTokens, that.budgetTokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effort, budgetTokens);
    }

    @Override
    public String toString() {
        return "ReasoningBudgetSpecification{" +
                "effort=" + effort +
                ", budgetTokens=" + budgetTokens +
                '}';
    }
}
