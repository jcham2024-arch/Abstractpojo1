package com.apex.chat.core.model;

import com.apex.chat.core.participant.ChatParticipant;
import com.apex.chat.core.participant.ParticipantStatus;
import com.apex.chat.core.participant.ParticipantType;

import java.util.Objects;
import java.util.UUID;

/**
 * AI model participant in the chat session, containing model specs, capabilities, context limits, and hyperparameters.
 */
public class LlmModelParticipant implements ChatParticipant {

    private String modelParticipantId;
    private String displayName;
    private ModelSpecification modelSpecification;
    private ModelCapabilities capabilities;
    private ContextWindowSpecification contextWindow;
    private InferenceHyperparameters hyperparameters;
    private ReasoningBudgetSpecification reasoningBudget;
    private ModelTelemetryHistory telemetryHistory;
    private ParticipantStatus status;

    public LlmModelParticipant() {
        this.modelParticipantId = UUID.randomUUID().toString();
        this.displayName = "AI Assistant";
        this.modelSpecification = new ModelSpecification();
        this.capabilities = new ModelCapabilities();
        this.contextWindow = new ContextWindowSpecification();
        this.hyperparameters = new InferenceHyperparameters();
        this.reasoningBudget = new ReasoningBudgetSpecification();
        this.telemetryHistory = new ModelTelemetryHistory();
        this.status = ParticipantStatus.ONLINE;
    }

    public LlmModelParticipant(String displayName, ModelSpecification modelSpecification) {
        this();
        this.displayName = displayName;
        this.modelSpecification = modelSpecification;
    }

    @Override
    public String getParticipantId() {
        return modelParticipantId;
    }

    public String getModelParticipantId() {
        return modelParticipantId;
    }

    public LlmModelParticipant setModelParticipantId(String modelParticipantId) {
        this.modelParticipantId = modelParticipantId;
        return this;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public LlmModelParticipant setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public ParticipantType getParticipantType() {
        return ParticipantType.LLM_MODEL;
    }

    public ModelSpecification getModelSpecification() {
        return modelSpecification;
    }

    public LlmModelParticipant setModelSpecification(ModelSpecification modelSpecification) {
        this.modelSpecification = modelSpecification != null ? modelSpecification : new ModelSpecification();
        return this;
    }

    public ModelCapabilities getCapabilities() {
        return capabilities;
    }

    public LlmModelParticipant setCapabilities(ModelCapabilities capabilities) {
        this.capabilities = capabilities != null ? capabilities : new ModelCapabilities();
        return this;
    }

    public ContextWindowSpecification getContextWindow() {
        return contextWindow;
    }

    public LlmModelParticipant setContextWindow(ContextWindowSpecification contextWindow) {
        this.contextWindow = contextWindow != null ? contextWindow : new ContextWindowSpecification();
        return this;
    }

    public InferenceHyperparameters getHyperparameters() {
        return hyperparameters;
    }

    public LlmModelParticipant setHyperparameters(InferenceHyperparameters hyperparameters) {
        this.hyperparameters = hyperparameters != null ? hyperparameters : new InferenceHyperparameters();
        return this;
    }

    public ReasoningBudgetSpecification getReasoningBudget() {
        return reasoningBudget;
    }

    public LlmModelParticipant setReasoningBudget(ReasoningBudgetSpecification reasoningBudget) {
        this.reasoningBudget = reasoningBudget != null ? reasoningBudget : new ReasoningBudgetSpecification();
        return this;
    }

    public ModelTelemetryHistory getTelemetryHistory() {
        return telemetryHistory;
    }

    public LlmModelParticipant setTelemetryHistory(ModelTelemetryHistory telemetryHistory) {
        this.telemetryHistory = telemetryHistory != null ? telemetryHistory : new ModelTelemetryHistory();
        return this;
    }

    @Override
    public ParticipantStatus getStatus() {
        return status;
    }

    public LlmModelParticipant setStatus(ParticipantStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LlmModelParticipant that)) return false;
        return Objects.equals(modelParticipantId, that.modelParticipantId) &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(modelSpecification, that.modelSpecification) &&
                Objects.equals(capabilities, that.capabilities) &&
                Objects.equals(contextWindow, that.contextWindow) &&
                Objects.equals(hyperparameters, that.hyperparameters) &&
                Objects.equals(reasoningBudget, that.reasoningBudget) &&
                Objects.equals(telemetryHistory, that.telemetryHistory) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelParticipantId, displayName, modelSpecification, capabilities,
                contextWindow, hyperparameters, reasoningBudget, telemetryHistory, status);
    }

    @Override
    public String toString() {
        return "LlmModelParticipant{" +
                "modelParticipantId='" + modelParticipantId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", modelSpecification=" + modelSpecification +
                ", status=" + status +
                '}';
    }
}
