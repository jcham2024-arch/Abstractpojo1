package com.apex.chat.agent.step;

import com.apex.chat.agent.guardrail.AgentGuardrailEvaluation;
import com.apex.chat.core.content.ContextEnrichmentBlock;
import com.apex.chat.core.content.PromptDirectiveBlock;
import com.apex.chat.core.thinking.IntentTag;
import com.apex.chat.core.thinking.ThoughtBlock;
import com.apex.chat.tools.execution.ToolCall;
import com.apex.chat.tools.execution.ToolResult;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Granular unit of intentional interaction where an agent inspects thoughts from the Chat,
 * injects directives/context, evaluates safety, and dispatches tool execution.
 */
public class AgentStep implements Serializable {

    private int stepIndex;
    private Instant timestamp;
    private ThoughtBlock thoughtSnapshot;
    private List<IntentTag> inspectedIntents;
    private List<PromptDirectiveBlock> injectedDirectives;
    private List<ContextEnrichmentBlock> injectedContext;
    private ToolCall toolCall;
    private ToolResult toolResult;
    private AgentGuardrailEvaluation guardrailEvaluation;
    private String reflection;
    private StepOutcome outcome;

    public AgentStep() {
        this.timestamp = Instant.now();
        this.inspectedIntents = new ArrayList<>();
        this.injectedDirectives = new ArrayList<>();
        this.injectedContext = new ArrayList<>();
        this.guardrailEvaluation = AgentGuardrailEvaluation.pass();
        this.outcome = StepOutcome.CONTINUE;
    }

    public AgentStep(int stepIndex) {
        this();
        this.stepIndex = stepIndex;
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public AgentStep setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public AgentStep setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ThoughtBlock getThoughtSnapshot() {
        return thoughtSnapshot;
    }

    public AgentStep setThoughtSnapshot(ThoughtBlock thoughtSnapshot) {
        this.thoughtSnapshot = thoughtSnapshot;
        return this;
    }

    public List<IntentTag> getInspectedIntents() {
        return inspectedIntents;
    }

    public AgentStep setInspectedIntents(List<IntentTag> inspectedIntents) {
        this.inspectedIntents = inspectedIntents != null ? inspectedIntents : new ArrayList<>();
        return this;
    }

    public AgentStep addInspectedIntent(IntentTag intent) {
        if (this.inspectedIntents == null) {
            this.inspectedIntents = new ArrayList<>();
        }
        this.inspectedIntents.add(intent);
        return this;
    }

    public List<PromptDirectiveBlock> getInjectedDirectives() {
        return injectedDirectives;
    }

    public AgentStep setInjectedDirectives(List<PromptDirectiveBlock> injectedDirectives) {
        this.injectedDirectives = injectedDirectives != null ? injectedDirectives : new ArrayList<>();
        return this;
    }

    public AgentStep addInjectedDirective(PromptDirectiveBlock directive) {
        if (this.injectedDirectives == null) {
            this.injectedDirectives = new ArrayList<>();
        }
        this.injectedDirectives.add(directive);
        return this;
    }

    public List<ContextEnrichmentBlock> getInjectedContext() {
        return injectedContext;
    }

    public AgentStep setInjectedContext(List<ContextEnrichmentBlock> injectedContext) {
        this.injectedContext = injectedContext != null ? injectedContext : new ArrayList<>();
        return this;
    }

    public AgentStep addInjectedContext(ContextEnrichmentBlock context) {
        if (this.injectedContext == null) {
            this.injectedContext = new ArrayList<>();
        }
        this.injectedContext.add(context);
        return this;
    }

    public ToolCall getToolCall() {
        return toolCall;
    }

    public AgentStep setToolCall(ToolCall toolCall) {
        this.toolCall = toolCall;
        return this;
    }

    public ToolResult getToolResult() {
        return toolResult;
    }

    public AgentStep setToolResult(ToolResult toolResult) {
        this.toolResult = toolResult;
        return this;
    }

    public AgentGuardrailEvaluation getGuardrailEvaluation() {
        return guardrailEvaluation;
    }

    public AgentStep setGuardrailEvaluation(AgentGuardrailEvaluation guardrailEvaluation) {
        this.guardrailEvaluation = guardrailEvaluation != null ? guardrailEvaluation : AgentGuardrailEvaluation.pass();
        return this;
    }

    public String getReflection() {
        return reflection;
    }

    public AgentStep setReflection(String reflection) {
        this.reflection = reflection;
        return this;
    }

    public StepOutcome getOutcome() {
        return outcome;
    }

    public AgentStep setOutcome(StepOutcome outcome) {
        this.outcome = outcome;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgentStep agentStep)) return false;
        return stepIndex == agentStep.stepIndex &&
                Objects.equals(timestamp, agentStep.timestamp) &&
                Objects.equals(thoughtSnapshot, agentStep.thoughtSnapshot) &&
                Objects.equals(inspectedIntents, agentStep.inspectedIntents) &&
                Objects.equals(injectedDirectives, agentStep.injectedDirectives) &&
                Objects.equals(injectedContext, agentStep.injectedContext) &&
                Objects.equals(toolCall, agentStep.toolCall) &&
                Objects.equals(toolResult, agentStep.toolResult) &&
                Objects.equals(guardrailEvaluation, agentStep.guardrailEvaluation) &&
                Objects.equals(reflection, agentStep.reflection) &&
                outcome == agentStep.outcome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stepIndex, timestamp, thoughtSnapshot, inspectedIntents,
                injectedDirectives, injectedContext, toolCall, toolResult, guardrailEvaluation, reflection, outcome);
    }

    @Override
    public String toString() {
        return "AgentStep{" +
                "stepIndex=" + stepIndex +
                ", outcome=" + outcome +
                ", hasToolCall=" + (toolCall != null) +
                ", hasToolResult=" + (toolResult != null) +
                '}';
    }
}
