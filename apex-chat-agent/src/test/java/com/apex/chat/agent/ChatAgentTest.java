package com.apex.chat.agent;

import com.apex.chat.agent.constraint.AgentExecutionConstraints;
import com.apex.chat.agent.constraint.AgentTerminationCondition;
import com.apex.chat.agent.constraint.TerminationReason;
import com.apex.chat.agent.core.Agent;
import com.apex.chat.agent.core.AgentStatus;
import com.apex.chat.agent.guardrail.AgentGuardrailEvaluation;
import com.apex.chat.agent.guardrail.RiskLevel;
import com.apex.chat.agent.step.AgentStep;
import com.apex.chat.agent.step.StepOutcome;
import com.apex.chat.core.chat.Chat;
import com.apex.chat.core.content.ContextEnrichmentBlock;
import com.apex.chat.core.content.PromptDirectiveBlock;
import com.apex.chat.core.thinking.IntentTag;
import com.apex.chat.core.thinking.IntentType;
import com.apex.chat.core.thinking.ThoughtBlock;
import com.apex.chat.tools.execution.ToolCall;
import com.apex.chat.tools.execution.ToolResult;
import com.apex.chat.tools.registry.ToolRegistry;
import com.apex.chat.tools.spec.ToolDefinition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatAgentTest {

    @Test
    public void testAgentCompositionOverChatAndTools() {
        // 1. Base Layer: Chat
        Chat chat = new Chat().setChatId("chat-500");
        chat.getUser().setDisplayName("Bob Dev");
        chat.getModel().setDisplayName("Gemini 2.5 Pro");

        // 2. Tools Layer: ToolRegistry
        ToolRegistry registry = new ToolRegistry()
                .register(new ToolDefinition("calculator", "Evaluate arithmetic expression"));

        // 3. Agent Layer: Composition
        Agent agent = new Agent("MathAgent", "Financial Analyst")
                .setChat(chat)
                .setToolRegistry(registry)
                .setExecutionConstraints(new AgentExecutionConstraints().setMaxStepsPerTurn(5));

        // 4. Intentional Step Interaction
        ThoughtBlock thought = new ThoughtBlock("I need to calculate the sum")
                .addIntent(new IntentTag(IntentType.TOOL_EXECUTION, "calculator", "Perform addition"));

        ToolCall call = new ToolCall("call-1", "calculator", "{\"expr\":\"100 + 200\"}");
        ToolResult result = ToolResult.success("call-1", "calculator", "{\"result\":300}");

        AgentStep step = new AgentStep(1)
                .setThoughtSnapshot(thought)
                .addInspectedIntent(thought.getIntents().get(0))
                .addInjectedDirective(new PromptDirectiveBlock().setDirectiveText("Ensure high precision arithmetic"))
                .addInjectedContext(new ContextEnrichmentBlock("FinancialLedger", "Q3 Report", 0.98))
                .setToolCall(call)
                .setToolResult(result)
                .setGuardrailEvaluation(AgentGuardrailEvaluation.pass())
                .setReflection("Addition verified successfully")
                .setOutcome(StepOutcome.COMPLETE_TURN);

        agent.addStep(step);
        agent.setStatus(AgentStatus.COMPLETED);
        agent.setTerminationCondition(new AgentTerminationCondition(TerminationReason.GOAL_ACHIEVED, "Calculation finished"));

        Assertions.assertEquals("MathAgent", agent.getAgentName());
        Assertions.assertEquals("Bob Dev", agent.getChat().getUser().getDisplayName());
        Assertions.assertTrue(agent.getToolRegistry().hasTool("calculator"));
        Assertions.assertEquals(1, agent.getStepHistory().size());
        Assertions.assertEquals(StepOutcome.COMPLETE_TURN, agent.getStepHistory().get(0).getOutcome());
        Assertions.assertEquals(RiskLevel.LOW, agent.getStepHistory().get(0).getGuardrailEvaluation().getRiskLevel());
        Assertions.assertEquals(TerminationReason.GOAL_ACHIEVED, agent.getTerminationCondition().getReason());
    }

    @Test
    public void testAgentGuardrailDefinition() {
        com.apex.chat.agent.guardrail.AgentGuardrailDefinition guardrail = new com.apex.chat.agent.guardrail.AgentGuardrailDefinition(
                "CodeSandboxSafety", "Guards against unauthorized system operations")
                .addPolicy(new com.apex.chat.agent.guardrail.SafetyPolicy(
                        "NoShellExecution", "Security", "Prevent raw shell execution"))
                .addTrigger(com.apex.chat.agent.guardrail.RejectionTrigger.forbiddenTool(
                        "executeBash", com.apex.chat.agent.guardrail.RiskLevel.CRITICAL))
                .addTrigger(com.apex.chat.agent.guardrail.RejectionTrigger.regex(
                        "DropTablePattern", "(?i)DROP\\s+TABLE", com.apex.chat.agent.guardrail.RiskLevel.HIGH))
                .setDefaultStrategy(com.apex.chat.agent.guardrail.InterventionStrategy.RETRY_WITH_CORRECTION)
                .setEscalatedStrategy(com.apex.chat.agent.guardrail.InterventionStrategy.BLOCK_AND_ABORT);

        Assertions.assertTrue(guardrail.isEnabled());
        Assertions.assertEquals(1, guardrail.getPolicies().size());
        Assertions.assertEquals(2, guardrail.getTriggers().size());
        Assertions.assertEquals(com.apex.chat.agent.guardrail.InterventionStrategy.RETRY_WITH_CORRECTION, guardrail.getDefaultStrategy());
        Assertions.assertEquals(com.apex.chat.agent.guardrail.InterventionStrategy.BLOCK_AND_ABORT, guardrail.getEscalatedStrategy());

        Agent agent = new Agent("SecAgent", "Security Guard")
                .addGuardrailDefinition(guardrail);

        Assertions.assertEquals(1, agent.getGuardrailDefinitions().size());
        Assertions.assertEquals("CodeSandboxSafety", agent.getGuardrailDefinitions().get(0).getName());
    }
}
