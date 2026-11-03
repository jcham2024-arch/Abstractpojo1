package com.apex.chat.core.telemetry;

import java.io.Serializable;
import java.util.Objects;

/**
 * Encapsulates the estimated financial or token billing costs for an LLM invocation.
 */
public class CostEstimation implements Serializable {

    private Double inputCostUsd;
    private Double outputCostUsd;
    private Double reasoningCostUsd;
    private Double totalCostUsd;
    private String currencyCode;
    private String pricingTierIdentifier;

    public CostEstimation() {
        this.inputCostUsd = 0.0;
        this.outputCostUsd = 0.0;
        this.reasoningCostUsd = 0.0;
        this.totalCostUsd = 0.0;
        this.currencyCode = "USD";
        this.pricingTierIdentifier = "standard";
    }

    public CostEstimation(Double inputCostUsd, Double outputCostUsd) {
        this();
        this.inputCostUsd = inputCostUsd != null ? inputCostUsd : 0.0;
        this.outputCostUsd = outputCostUsd != null ? outputCostUsd : 0.0;
        recalculateTotal();
    }

    public CostEstimation(Double inputCostUsd, Double outputCostUsd, Double reasoningCostUsd) {
        this();
        this.inputCostUsd = inputCostUsd != null ? inputCostUsd : 0.0;
        this.outputCostUsd = outputCostUsd != null ? outputCostUsd : 0.0;
        this.reasoningCostUsd = reasoningCostUsd != null ? reasoningCostUsd : 0.0;
        recalculateTotal();
    }

    private void recalculateTotal() {
        double in = this.inputCostUsd != null ? this.inputCostUsd : 0.0;
        double out = this.outputCostUsd != null ? this.outputCostUsd : 0.0;
        double reasoning = this.reasoningCostUsd != null ? this.reasoningCostUsd : 0.0;
        this.totalCostUsd = in + out + reasoning;
    }

    public Double getInputCostUsd() {
        return inputCostUsd;
    }

    public CostEstimation setInputCostUsd(Double inputCostUsd) {
        this.inputCostUsd = inputCostUsd;
        recalculateTotal();
        return this;
    }

    public Double getOutputCostUsd() {
        return outputCostUsd;
    }

    public CostEstimation setOutputCostUsd(Double outputCostUsd) {
        this.outputCostUsd = outputCostUsd;
        recalculateTotal();
        return this;
    }

    public Double getReasoningCostUsd() {
        return reasoningCostUsd;
    }

    public CostEstimation setReasoningCostUsd(Double reasoningCostUsd) {
        this.reasoningCostUsd = reasoningCostUsd;
        recalculateTotal();
        return this;
    }

    public Double getTotalCostUsd() {
        return totalCostUsd;
    }

    public CostEstimation setTotalCostUsd(Double totalCostUsd) {
        this.totalCostUsd = totalCostUsd;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public CostEstimation setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getPricingTierIdentifier() {
        return pricingTierIdentifier;
    }

    public CostEstimation setPricingTierIdentifier(String pricingTierIdentifier) {
        this.pricingTierIdentifier = pricingTierIdentifier;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostEstimation that)) return false;
        return Objects.equals(inputCostUsd, that.inputCostUsd) &&
                Objects.equals(outputCostUsd, that.outputCostUsd) &&
                Objects.equals(reasoningCostUsd, that.reasoningCostUsd) &&
                Objects.equals(totalCostUsd, that.totalCostUsd) &&
                Objects.equals(currencyCode, that.currencyCode) &&
                Objects.equals(pricingTierIdentifier, that.pricingTierIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputCostUsd, outputCostUsd, reasoningCostUsd, totalCostUsd, currencyCode, pricingTierIdentifier);
    }

    @Override
    public String toString() {
        return "CostEstimation{" +
                "totalCost=" + totalCostUsd + " " + currencyCode +
                ", inputCost=" + inputCostUsd +
                ", outputCost=" + outputCostUsd +
                ", tier='" + pricingTierIdentifier + '\'' +
                '}';
    }
}
