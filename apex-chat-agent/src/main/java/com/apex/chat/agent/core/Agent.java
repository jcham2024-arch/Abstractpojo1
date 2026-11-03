package com.apex.chat.agent.core;

import com.apex.chat.agent.constraint.AgentExecutionConstraints;
import com.apex.chat.agent.constraint.AgentTerminationCondition;
import com.apex.chat.agent.step.AgentStep;
import com.apex.chat.core.chat.Chat;
import com.apex.chat.tools.registry.ToolRegistry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Root composite POJO representing an autonomous agent.
 * Formed directly via composition:
 * Agent = Chat (base wrapper) + ToolRegistry (tools) + Intentional Interaction Layer
 */
public class Agent implements Serializable {

    private String agentId;
    private String agentName;
    private String agentRole;
    private String goalDescription;
    private Chat chat;
    private ToolRegistry toolRegistry;
    private AgentExecutionConstraints executionConstraints;
    private List<com.apex.chat.agent.guardrail.AgentGuardrailDefinition> guardrailDefinitions;
    private List<AgentStep> stepHistory;
    private AgentStatus status;
    private AgentTerminationCondition terminationCondition;

    public Agent() {
        this.agentId = UUID.randomUUID().toString();
        this.agentName = "Apex Agent";
        this.agentRole = "Autonomous Assistant";
        this.chat = new Chat();
        this.toolRegistry = new ToolRegistry();
        this.executionConstraints = new AgentExecutionConstraints();
        this.guardrailDefinitions = new ArrayList<>();
        this.stepHistory = new ArrayList<>();
        this.status = AgentStatus.IDLE;
    }

    public Agent(String agentName, String agentRole) {
        this();
        this.agentName = agentName;
        this.agentRole = agentRole;
    }

    public Agent(String agentName, Chat chat, ToolRegistry toolRegistry) {
        this();
        this.agentName = agentName;
        this.chat = chat != null ? chat : new Chat();
        this.toolRegistry = toolRegistry != null ? toolRegistry : new ToolRegistry();
    }

    public String getAgentId() {
        return agentId;
    }

    public Agent setAgentId(String agentId) {
        this.agentId = agentId;
        return this;
    }

    public String getAgentName() {
        return agentName;
    }

    public Agent setAgentName(String agentName) {
        this.agentName = agentName;
        return this;
    }

    public String getAgentRole() {
        return agentRole;
    }

    public Agent setAgentRole(String agentRole) {
        this.agentRole = agentRole;
        return this;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public Agent setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
        return this;
    }

    public Chat getChat() {
        return chat;
    }

    public Agent setChat(Chat chat) {
        this.chat = chat != null ? chat : new Chat();
        return this;
    }

    public ToolRegistry getToolRegistry() {
        return toolRegistry;
    }

    public Agent setToolRegistry(ToolRegistry toolRegistry) {
        this.toolRegistry = toolRegistry != null ? toolRegistry : new ToolRegistry();
        return this;
    }

    public AgentExecutionConstraints getExecutionConstraints() {
        return executionConstraints;
    }

    public Agent setExecutionConstraints(AgentExecutionConstraints executionConstraints) {
        this.executionConstraints = executionConstraints != null ? executionConstraints : new AgentExecutionConstraints();
        return this;
    }

    public List<AgentStep> getStepHistory() {
        return stepHistory;
    }

    public List<com.apex.chat.agent.guardrail.AgentGuardrailDefinition> getGuardrailDefinitions() {
        return guardrailDefinitions;
    }

    public Agent setGuardrailDefinitions(List<com.apex.chat.agent.guardrail.AgentGuardrailDefinition> guardrailDefinitions) {
        this.guardrailDefinitions = guardrailDefinitions != null ? guardrailDefinitions : new ArrayList<>();
        return this;
    }

    public Agent addGuardrailDefinition(com.apex.chat.agent.guardrail.AgentGuardrailDefinition definition) {
        if (this.guardrailDefinitions == null) {
            this.guardrailDefinitions = new ArrayList<>();
        }
        this.guardrailDefinitions.add(definition);
        return this;
    }

    public Agent setStepHistory(List<AgentStep> stepHistory) {
        this.stepHistory = stepHistory != null ? stepHistory : new ArrayList<>();
        return this;
    }

    public Agent addStep(AgentStep step) {
        if (this.stepHistory == null) {
            this.stepHistory = new ArrayList<>();
        }
        this.stepHistory.add(step);
        return this;
    }

    public AgentStatus getStatus() {
        return status;
    }

    public Agent setStatus(AgentStatus status) {
        this.status = status;
        return this;
    }

    public AgentTerminationCondition getTerminationCondition() {
        return terminationCondition;
    }

    public Agent setTerminationCondition(AgentTerminationCondition terminationCondition) {
        this.terminationCondition = terminationCondition;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agent agent)) return false;
        return Objects.equals(agentId, agent.agentId) &&
                Objects.equals(agentName, agent.agentName) &&
                Objects.equals(agentRole, agent.agentRole) &&
                Objects.equals(goalDescription, agent.goalDescription) &&
                Objects.equals(chat, agent.chat) &&
                Objects.equals(toolRegistry, agent.toolRegistry) &&
                Objects.equals(executionConstraints, agent.executionConstraints) &&
                Objects.equals(guardrailDefinitions, agent.guardrailDefinitions) &&
                Objects.equals(stepHistory, agent.stepHistory) &&
                status == agent.status &&
                Objects.equals(terminationCondition, agent.terminationCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agentId, agentName, agentRole, goalDescription, chat,
                toolRegistry, executionConstraints, guardrailDefinitions, stepHistory, status, terminationCondition);
    }

    @Override
    public String toString() {
        return "Agent{" +
                "agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentRole='" + agentRole + '\'' +
                ", status=" + status +
                ", guardrailsCount=" + (guardrailDefinitions != null ? guardrailDefinitions.size() : 0) +
                ", stepsCount=" + (stepHistory != null ? stepHistory.size() : 0) +
                '}';
    }
}
