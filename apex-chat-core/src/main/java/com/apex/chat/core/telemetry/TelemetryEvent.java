package com.apex.chat.core.telemetry;

import com.apex.chat.core.event.ChatEventType;
import com.apex.chat.core.event.ChatLifecycleEvent;
import com.apex.chat.core.message.GenerationTelemetry;

import java.util.Objects;

/**
 * TelemetryEvent POJO tracking LLM invocation performance metrics, token consumption,
 * latency profiling (TTFT, total), and financial cost estimations.
 */
public class TelemetryEvent extends ChatLifecycleEvent {

    private String modelIdentifier;
    private Integer turnIndex;
    private Integer stepIndex;
    private GenerationTelemetry telemetry;
    private CostEstimation costEstimation;

    public TelemetryEvent() {
        super();
        setEventType(ChatEventType.TELEMETRY_RECORDED);
        this.telemetry = new GenerationTelemetry();
        this.costEstimation = new CostEstimation();
    }

    public TelemetryEvent(String chatId, String modelIdentifier, GenerationTelemetry telemetry, CostEstimation costEstimation) {
        super(ChatEventType.TELEMETRY_RECORDED, chatId, "Telemetry recorded for " + modelIdentifier);
        this.modelIdentifier = modelIdentifier;
        this.telemetry = telemetry != null ? telemetry : new GenerationTelemetry();
        this.costEstimation = costEstimation != null ? costEstimation : new CostEstimation();
    }

    public static TelemetryEvent of(String chatId, String modelIdentifier, GenerationTelemetry telemetry, CostEstimation costEstimation) {
        return new TelemetryEvent(chatId, modelIdentifier, telemetry, costEstimation);
    }

    public String getModelIdentifier() {
        return modelIdentifier;
    }

    public TelemetryEvent setModelIdentifier(String modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
        return this;
    }

    public Integer getTurnIndex() {
        return turnIndex;
    }

    public TelemetryEvent setTurnIndex(Integer turnIndex) {
        this.turnIndex = turnIndex;
        return this;
    }

    public Integer getStepIndex() {
        return stepIndex;
    }

    public TelemetryEvent setStepIndex(Integer stepIndex) {
        this.stepIndex = stepIndex;
        return this;
    }

    public GenerationTelemetry getTelemetry() {
        return telemetry;
    }

    public TelemetryEvent setTelemetry(GenerationTelemetry telemetry) {
        this.telemetry = telemetry != null ? telemetry : new GenerationTelemetry();
        return this;
    }

    public CostEstimation getCostEstimation() {
        return costEstimation;
    }

    public TelemetryEvent setCostEstimation(CostEstimation costEstimation) {
        this.costEstimation = costEstimation != null ? costEstimation : new CostEstimation();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelemetryEvent that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(modelIdentifier, that.modelIdentifier) &&
                Objects.equals(turnIndex, that.turnIndex) &&
                Objects.equals(stepIndex, that.stepIndex) &&
                Objects.equals(telemetry, that.telemetry) &&
                Objects.equals(costEstimation, that.costEstimation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modelIdentifier, turnIndex, stepIndex, telemetry, costEstimation);
    }

    @Override
    public String toString() {
        return "TelemetryEvent{" +
                "chatId='" + getChatId() + '\'' +
                ", model='" + modelIdentifier + '\'' +
                ", totalTokens=" + (telemetry != null ? telemetry.getTotalTokens() : 0) +
                ", totalLatencyMs=" + (telemetry != null ? telemetry.getTotalLatencyMs() : 0) +
                ", cost=" + (costEstimation != null ? costEstimation.getTotalCostUsd() : 0.0) +
                '}';
    }
}
