/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import React, { useState } from 'react';
import {
  Layers,
  Box,
  Cpu,
  Wrench,
  Bot,
  MessageSquare,
  FileCode2,
  CheckCircle2,
  ChevronRight,
  ShieldCheck,
  BrainCircuit,
  Terminal,
  Activity,
  Sliders,
  Sparkles,
  Copy,
  Check,
  ExternalLink,
  GitBranch
} from 'lucide-react';

interface PojoDoc {
  name: string;
  module: 'apex-chat-core' | 'apex-chat-tools' | 'apex-chat-agent';
  package: string;
  category: string;
  description: string;
  fields: { name: string; type: string; desc: string }[];
  methods: string[];
  javaCodeSample: string;
}

const POJO_REGISTRY: PojoDoc[] = [
  // Core: Chat composite
  {
    name: 'Chat',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.chat',
    category: 'Root Composite Wrapper',
    description: 'The foundational base wrapper encapsulating session lifecycle, interface, human user, model participant, prompt rules, memory, and conversation thread.',
    fields: [
      { name: 'chatId', type: 'String', desc: 'Unique identifier for the chat instance' },
      { name: 'session', type: 'ChatSession', desc: 'Session lifecycle, timestamps, and status' },
      { name: 'chatInterface', type: 'ChatInterface', desc: 'Application medium (Web, CLI, GUI, API)' },
      { name: 'user', type: 'ChatUser', desc: 'Human user identity, settings, and history' },
      { name: 'model', type: 'LlmModelParticipant', desc: 'LLM specification, limits, and hyperparameters' },
      { name: 'promptContext', type: 'PromptContext', desc: 'System prompts, dynamic variables, and guardrails' },
      { name: 'memoryStore', type: 'MemoryStore', desc: 'Working scratchpad and episodic memories' },
      { name: 'thread', type: 'ConversationThread', desc: 'Branched sequence of conversation turns' },
      { name: 'activeTurn', type: 'ConversationTurn', desc: 'Currently executing or latest exchange turn' },
    ],
    methods: [
      'public Chat setChatId(String chatId)',
      'public Chat setUser(ChatUser user)',
      'public Chat setModel(LlmModelParticipant model)',
      'public Chat setPromptContext(PromptContext promptContext)',
      'public Chat setMemoryStore(MemoryStore memoryStore)',
      'public Chat setActiveTurn(ConversationTurn activeTurn)',
    ],
    javaCodeSample: `Chat chat = new Chat()
    .setChatId("chat-100")
    .setUser(new ChatUser("usr-1", "Alice"))
    .setModel(new LlmModelParticipant().setDisplayName("Gemini 2.5 Flash"))
    .setPromptContext(new PromptContext().setSystemPrompt(new SystemPrompt("Be concise.")))
    .setMemoryStore(new MemoryStore())
    .setEventSystem(new ChatEventSystem());`,
  },

  // Core: Memory Store
  {
    name: 'MemoryStore',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.memory',
    category: 'Memory & Context',
    description: 'Manages episodic memory buffers, working scratchpads (key-value notes, active goals, hypotheses), and context window sliding turn policies.',
    fields: [
      { name: 'workingMemory', type: 'WorkingMemory', desc: 'Working scratchpad (active goals, hypotheses, notes)' },
      { name: 'episodicMemories', type: 'List<MemoryEntry>', desc: 'Persistent episodic events, facts, and user preferences' },
      { name: 'contextWindowPolicy', type: 'ContextWindowPolicy', desc: 'Max context tokens, recent turns retention, pruning rules' },
      { name: 'maxMemoryTokenBudget', type: 'int', desc: 'Maximum token allocation reserved for injected memory' },
    ],
    methods: [
      'public MemoryStore putScratchpadNote(String key, String value)',
      'public MemoryStore addEpisodicMemory(MemoryCategory cat, String content, double imp)',
      'public List<MemoryEntry> findMemoriesByCategory(MemoryCategory category)',
      'public List<ConversationTurn> selectActiveTurns(List<ConversationTurn> turns)',
    ],
    javaCodeSample: `MemoryStore store = new MemoryStore()
    .putScratchpadNote("activeGoal", "Analyze microkernel architecture")
    .addEpisodicMemory(MemoryCategory.USER_PREFERENCE, "Prefers structured reasoning", 0.9)
    .setContextWindowPolicy(new ContextWindowPolicy(128000, 20));

List<ConversationTurn> activeTurns = store.selectActiveTurns(allTurns);`,
  },

  // Core: Event System
  {
    name: 'ChatEventSystem',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.event',
    category: 'Reactive Event System',
    description: 'Handles lifecycle events (ChatStarted, StepCompleted, AgentError), observer registration via ChatEventRegistry, and reactive hook execution across the agent flow.',
    fields: [
      { name: 'systemId', type: 'String', desc: 'Unique event system identifier' },
      { name: 'registry', type: 'ChatEventRegistry', desc: 'Observer registry for global and type-filtered listeners' },
      { name: 'enabled', type: 'boolean', desc: 'Global event publishing switch' },
      { name: 'eventHistory', type: 'List<ChatLifecycleEvent>', desc: 'Bounded audit trail of dispatched events' },
      { name: 'maxHistorySize', type: 'int', desc: 'Maximum number of retained historical events' },
    ],
    methods: [
      'public ChatEventSystem publish(ChatLifecycleEvent event)',
      'public ChatEventSystem addListener(ChatEventListener listener)',
      'public ChatEventSystem addListener(ChatEventType eventType, ChatEventListener listener)',
      'public ChatEventSystem clearHistory()',
    ],
    javaCodeSample: `ChatEventSystem eventSystem = new ChatEventSystem();

// Register observer hook for agent errors
eventSystem.addListener(ChatEventType.AGENT_ERROR, event -> {
    System.err.println("Audit alert: " + event.getDescription());
});

// Publish a lifecycle event
eventSystem.publish(new StepCompletedEvent("chat-1", 1, true, "sqlTool"));`,
  },

  // Core: Persistence Descriptor
  {
    name: 'PersistenceDescriptor',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.persistence',
    category: 'State Persistence',
    description: 'Abstracts how chat history and agent state are serialized, stored (Memory, FileSystem, Database, CAS), compressed (GZIP, ZSTD, LZ4), and retained.',
    fields: [
      { name: 'descriptorId', type: 'String', desc: 'Unique descriptor identifier' },
      { name: 'storageStrategy', type: 'StorageStrategy', desc: 'MEMORY, FILE_SYSTEM, RELATIONAL_DATABASE, CAS' },
      { name: 'serializationFormat', type: 'SerializationFormat', desc: 'JSON, BINARY_PROTOBUF, JAVA_NATIVE, CBOR' },
      { name: 'compressionSettings', type: 'CompressionSettings', desc: 'Algorithm (GZIP, ZSTD, LZ4), level, size threshold' },
      { name: 'targetDestination', type: 'String', desc: 'Directory path, JDBC URI, or collection name' },
      { name: 'autoSaveIntervalSeconds', type: 'Integer', desc: 'Periodic background snapshot interval' },
      { name: 'encryptAtRest', type: 'boolean', desc: 'Enforces storage encryption flag' },
      { name: 'retentionPolicyDays', type: 'Integer', desc: 'Data retention lifespan in days' },
    ],
    methods: [
      'public static PersistenceDescriptor fileSystem(String path)',
      'public static PersistenceDescriptor database(String uri)',
      'public static PersistenceDescriptor contentAddressableStorage(String casRoot)',
      'public PersistenceDescriptor setCompressionSettings(CompressionSettings settings)',
    ],
    javaCodeSample: `PersistenceDescriptor persistence = PersistenceDescriptor
    .fileSystem("/var/data/apex/chats")
    .setSerializationFormat(SerializationFormat.JSON)
    .setCompressionSettings(CompressionSettings.gzip())
    .setAutoSaveIntervalSeconds(30)
    .setEncryptAtRest(true);`,
  },

  // Core: Telemetry Event
  {
    name: 'TelemetryEvent',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.telemetry',
    category: 'Performance & Billing',
    description: 'Extends ChatLifecycleEvent. Tracks invocation token consumption (input/output/cached/reasoning), latency profiling (TTFT, total), and financial cost estimations.',
    fields: [
      { name: 'modelIdentifier', type: 'String', desc: 'Invoked model identity (e.g. gemini-2.5-pro)' },
      { name: 'turnIndex', type: 'Integer', desc: 'Zero-based conversation turn index' },
      { name: 'stepIndex', type: 'Integer', desc: 'Zero-based agent step index' },
      { name: 'telemetry', type: 'GenerationTelemetry', desc: 'Tokens (in/out/cached), TTFT, total latency, tokens/sec' },
      { name: 'costEstimation', type: 'CostEstimation', desc: 'Input, output, reasoning, and total cost breakdown in USD' },
    ],
    methods: [
      'public static TelemetryEvent of(String chatId, String model, GenerationTelemetry t, CostEstimation c)',
      'public TelemetryEvent setTurnIndex(Integer turnIndex)',
      'public TelemetryEvent setStepIndex(Integer stepIndex)',
      'public TelemetryEvent setCostEstimation(CostEstimation costEstimation)',
    ],
    javaCodeSample: `TelemetryEvent event = TelemetryEvent.of(
    "chat-1",
    "gemini-2.5-pro",
    new GenerationTelemetry(1200, 350, 480L).setTimeToFirstTokenMs(140L),
    new CostEstimation(0.0012, 0.0035, 0.0018)
);

chat.getEventSystem().publish(event);`,
  },

  // Core: Thinking Model
  {
    name: 'ThoughtBlock',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.thinking',
    category: 'Structured Thinking',
    description: 'Implements ContentBlock. Ultra-granular decomposition of model chain-of-thought into recognizable phases, extracted intent tags, and safety checkpoints.',
    fields: [
      { name: 'blockId', type: 'String', desc: 'Unique identifier for this block' },
      { name: 'rawReasoningText', type: 'String', desc: 'Unparsed model thought stream' },
      { name: 'segments', type: 'List<ThoughtSegment>', desc: 'Phased reasoning segments' },
      { name: 'intents', type: 'List<IntentTag>', desc: 'Explicit action/query intents' },
      { name: 'checkpoints', type: 'List<ThoughtCheckpoint>', desc: 'Internal validation checkpoints' },
      { name: 'signature', type: 'String', desc: 'Optional cryptographic verification signature' },
    ],
    methods: [
      'public ThoughtBlock addSegment(ThoughtSegment segment)',
      'public ThoughtBlock addIntent(IntentTag intent)',
      'public ThoughtBlock addCheckpoint(ThoughtCheckpoint checkpoint)',
      'public ContentType getContentType() // Returns THOUGHT_STRUCTURED',
    ],
    javaCodeSample: `ThoughtBlock thought = new ThoughtBlock("Analyzing database schema...")
    .addSegment(new ThoughtSegment(0, ThoughtPhase.PROBLEM_DECOMPOSITION, "Need SQL query", 0.98))
    .addIntent(new IntentTag(IntentType.TOOL_EXECUTION, "sqlQueryTool", "Retrieve active users"))
    .addCheckpoint(new ThoughtCheckpoint("PolicyCheck", CheckpointStatus.VALID, null));`,
  },

  // Core: Participants
  {
    name: 'LlmModelParticipant',
    module: 'apex-chat-core',
    package: 'com.apex.chat.core.model',
    category: 'Participants',
    description: 'Implements ChatParticipant. Encapsulates all LLM-specific parameters: context window boundaries, inference hyperparameters, reasoning budget, and telemetry.',
    fields: [
      { name: 'modelParticipantId', type: 'String', desc: 'Participant ID' },
      { name: 'displayName', type: 'String', desc: 'Display name for the model' },
      { name: 'modelSpecification', type: 'ModelSpecification', desc: 'Provider, family, model version' },
      { name: 'capabilities', type: 'ModelCapabilities', desc: 'Streaming, vision, audio, tools' },
      { name: 'contextWindow', type: 'ContextWindowSpecification', desc: 'Max total tokens, max output tokens' },
      { name: 'hyperparameters', type: 'InferenceHyperparameters', desc: 'Temperature, topP, topK, stop sequences' },
      { name: 'reasoningBudget', type: 'ReasoningBudgetSpecification', desc: 'Reasoning effort and token budget' },
      { name: 'telemetryHistory', type: 'ModelTelemetryHistory', desc: 'Invocations count, latency, token totals' },
    ],
    methods: [
      'public LlmModelParticipant setHyperparameters(InferenceHyperparameters p)',
      'public LlmModelParticipant setContextWindow(ContextWindowSpecification c)',
      'public LlmModelParticipant setReasoningBudget(ReasoningBudgetSpecification b)',
    ],
    javaCodeSample: `LlmModelParticipant model = new LlmModelParticipant()
    .setDisplayName("Gemini 2.5 Pro")
    .setHyperparameters(new InferenceHyperparameters().setTemperature(0.2).setTopP(0.9))
    .setReasoningBudget(new ReasoningBudgetSpecification(ReasoningEffort.HIGH, 4096));`,
  },

  // Tools: ToolDefinition
  {
    name: 'ToolDefinition',
    module: 'apex-chat-tools',
    package: 'com.apex.chat.tools.spec',
    category: 'Tool Specification',
    description: 'Schema defining a tool available to an agent or model, including typed parameters, strict validation flags, and execution policies.',
    fields: [
      { name: 'toolName', type: 'String', desc: 'Unique function/tool identifier' },
      { name: 'description', type: 'String', desc: 'Semantic description for the LLM' },
      { name: 'parameters', type: 'List<ToolParameterSpecification>', desc: 'Typed arguments schema' },
      { name: 'strictSchema', type: 'boolean', desc: 'Enforces strict JSON schema validation' },
      { name: 'executionPolicy', type: 'ToolExecutionPolicy', desc: 'Confirmation, sandboxing, timeouts' },
    ],
    methods: [
      'public ToolDefinition addParameter(ToolParameterSpecification parameter)',
      'public ToolDefinition setExecutionPolicy(ToolExecutionPolicy policy)',
      'public ToolDefinition setStrictSchema(boolean strictSchema)',
    ],
    javaCodeSample: `ToolDefinition tool = new ToolDefinition("searchDocuments", "Search internal documentation")
    .addParameter(new ToolParameterSpecification("query", "string", "Search keywords", true))
    .setExecutionPolicy(new ToolExecutionPolicy().setRequiresUserConfirmation(false).setTimeoutSeconds(10));`,
  },

  // Tools: ToolRegistry
  {
    name: 'ToolRegistry',
    module: 'apex-chat-tools',
    package: 'com.apex.chat.tools.registry',
    category: 'Tool Catalog',
    description: 'Catalog holding registered tools available for lookup and invocation by the agent.',
    fields: [
      { name: 'registryId', type: 'String', desc: 'Unique registry identifier' },
      { name: 'tools', type: 'Map<String, ToolDefinition>', desc: 'Map of toolName -> ToolDefinition' },
    ],
    methods: [
      'public ToolRegistry register(ToolDefinition tool)',
      'public ToolDefinition getTool(String toolName)',
      'public boolean hasTool(String toolName)',
      'public Collection<ToolDefinition> getAllTools()',
    ],
    javaCodeSample: `ToolRegistry registry = new ToolRegistry()
    .register(new ToolDefinition("calculator", "Evaluate arithmetic expression"))
    .register(new ToolDefinition("fetchWeather", "Retrieve current weather forecasts"));`,
  },

  // Agent: Agent Root
  {
    name: 'Agent',
    module: 'apex-chat-agent',
    package: 'com.apex.chat.agent.core',
    category: 'Agent Orchestration',
    description: 'The root composite POJO. Formed by composition: Agent = Chat (base wrapper) + ToolRegistry (tools) + Intentional Interaction Layer (steps, constraints, guardrails).',
    fields: [
      { name: 'agentId', type: 'String', desc: 'Unique agent identifier' },
      { name: 'agentName', type: 'String', desc: 'Human-friendly agent name' },
      { name: 'agentRole', type: 'String', desc: 'Specialized role (e.g. Software Architect)' },
      { name: 'goalDescription', type: 'String', desc: 'Overarching objective' },
      { name: 'chat', type: 'Chat', desc: 'The underlying base Chat wrapper' },
      { name: 'toolRegistry', type: 'ToolRegistry', desc: 'The catalog of registered tools' },
      { name: 'executionConstraints', type: 'AgentExecutionConstraints', desc: 'Max steps, timeouts, token budgets' },
      { name: 'stepHistory', type: 'List<AgentStep>', desc: 'Trace of intentional interaction steps' },
      { name: 'status', type: 'AgentStatus', desc: 'IDLE, DELIBERATING, EXECUTING_TOOL, etc.' },
      { name: 'terminationCondition', type: 'AgentTerminationCondition', desc: 'Goal achieved or budget reached' },
    ],
    methods: [
      'public Agent setChat(Chat chat)',
      'public Agent setToolRegistry(ToolRegistry toolRegistry)',
      'public Agent addStep(AgentStep step)',
      'public Agent setExecutionConstraints(AgentExecutionConstraints constraints)',
    ],
    javaCodeSample: `Agent agent = new Agent("ArchitectAgent", "Senior Systems Architect")
    .setChat(chat)
    .setToolRegistry(registry)
    .setExecutionConstraints(new AgentExecutionConstraints().setMaxStepsPerTurn(10));`,
  },

  // Agent: AgentStep
  {
    name: 'AgentStep',
    module: 'apex-chat-agent',
    package: 'com.apex.chat.agent.step',
    category: 'Intentional Step Interaction',
    description: 'Granular step where higher-level libraries inspect thoughts from the Chat, evaluate safety guardrails, inject prompts or context, and dispatch tools.',
    fields: [
      { name: 'stepIndex', type: 'int', desc: 'Zero-based step counter' },
      { name: 'timestamp', type: 'Instant', desc: 'Execution timestamp' },
      { name: 'thoughtSnapshot', type: 'ThoughtBlock', desc: 'Captured thought from Chat' },
      { name: 'inspectedIntents', type: 'List<IntentTag>', desc: 'Evaluated intent tags' },
      { name: 'injectedDirectives', type: 'List<PromptDirectiveBlock>', desc: 'Injected rules/directives' },
      { name: 'injectedContext', type: 'List<ContextEnrichmentBlock>', desc: 'Injected dynamic memory/RAG' },
      { name: 'toolCall', type: 'ToolCall', desc: 'Tool invoked during this step' },
      { name: 'toolResult', type: 'ToolResult', desc: 'Result returned by tool execution' },
      { name: 'guardrailEvaluation', type: 'AgentGuardrailEvaluation', desc: 'Safety policy outcome' },
      { name: 'reflection', type: 'String', desc: 'Post-action critique or evaluation' },
      { name: 'outcome', type: 'StepOutcome', desc: 'CONTINUE, RETRY, REVISE_PLAN, COMPLETE_TURN' },
    ],
    methods: [
      'public AgentStep setThoughtSnapshot(ThoughtBlock thoughtSnapshot)',
      'public AgentStep addInjectedDirective(PromptDirectiveBlock directive)',
      'public AgentStep addInjectedContext(ContextEnrichmentBlock context)',
      'public AgentStep setToolCall(ToolCall toolCall)',
      'public AgentStep setToolResult(ToolResult toolResult)',
    ],
    javaCodeSample: `AgentStep step = new AgentStep(1)
    .setThoughtSnapshot(thought)
    .addInjectedDirective(new PromptDirectiveBlock().setDirectiveText("Enforce read-only queries"))
    .addInjectedContext(new ContextEnrichmentBlock("DB_SCHEMA", "table users(id, name)", 1.0))
    .setToolCall(new ToolCall("c1", "sqlTool", "{\\"query\\":\\"SELECT 1\\"}"))
    .setToolResult(ToolResult.success("c1", "sqlTool", "{\\"rows\\":[1]}"))
    .setOutcome(StepOutcome.COMPLETE_TURN);`,
  },

  // Agent: Guardrail Definition
  {
    name: 'AgentGuardrailDefinition',
    module: 'apex-chat-agent',
    package: 'com.apex.chat.agent.guardrail',
    category: 'Safety & Guardrails',
    description: 'Encapsulates safety policies, rejection triggers (Regex, Forbidden Tool, Out of Bounds), and automated intervention strategies (Retry, Block, Human Confirmation).',
    fields: [
      { name: 'guardrailId', type: 'String', desc: 'Unique guardrail configuration identifier' },
      { name: 'name', type: 'String', desc: 'Human-readable safety policy name' },
      { name: 'description', type: 'String', desc: 'Detailed compliance and enforcement scope' },
      { name: 'policies', type: 'List<SafetyPolicy>', desc: 'Governed compliance and domain policies' },
      { name: 'triggers', type: 'List<RejectionTrigger>', desc: 'Deterministic violation condition triggers' },
      { name: 'defaultStrategy', type: 'InterventionStrategy', desc: 'Default action (e.g. RETRY_WITH_CORRECTION)' },
      { name: 'escalatedStrategy', type: 'InterventionStrategy', desc: 'Escalation action (e.g. BLOCK_AND_ABORT)' },
      { name: 'fallbackResponseText', type: 'String', desc: 'Standard response when actions are halted' },
      { name: 'enabled', type: 'boolean', desc: 'Active policy enforcement flag' },
    ],
    methods: [
      'public AgentGuardrailDefinition addPolicy(SafetyPolicy policy)',
      'public AgentGuardrailDefinition addTrigger(RejectionTrigger trigger)',
      'public AgentGuardrailDefinition setDefaultStrategy(InterventionStrategy strategy)',
      'public AgentGuardrailDefinition setEscalatedStrategy(InterventionStrategy strategy)',
    ],
    javaCodeSample: `AgentGuardrailDefinition guardrail = new AgentGuardrailDefinition("SandboxPolicy", "Prevents OS shell exploits")
    .addPolicy(new SafetyPolicy("NoShellAccess", "Security", "Denies raw bash execution"))
    .addTrigger(RejectionTrigger.forbiddenTool("executeBash", RiskLevel.CRITICAL))
    .setDefaultStrategy(InterventionStrategy.RETRY_WITH_CORRECTION)
    .setEscalatedStrategy(InterventionStrategy.BLOCK_AND_ABORT);

agent.addGuardrailDefinition(guardrail);`,
  },
];

export default function App() {
  const [selectedModule, setSelectedModule] = useState<'all' | 'apex-chat-core' | 'apex-chat-tools' | 'apex-chat-agent'>('all');
  const [selectedPojo, setSelectedPojo] = useState<PojoDoc>(POJO_REGISTRY[0]);
  const [activeTab, setActiveTab] = useState<'explorer' | 'interactive-builder' | 'architecture-diagram' | 'maven-pom'>('interactive-builder');
  const [copiedCode, setCopiedCode] = useState(false);

  // Interactive Live Builder state
  const [agentName, setAgentName] = useState('ApexCodeReviewer');
  const [agentRole, setAgentRole] = useState('Senior Security Reviewer');
  const [modelName, setModelName] = useState('gemini-2.5-pro');
  const [temperature, setTemperature] = useState(0.2);
  const [thinkingEffort, setThinkingEffort] = useState<'NONE' | 'LOW' | 'MEDIUM' | 'HIGH'>('HIGH');
  const [includeTool, setIncludeTool] = useState(true);
  const [includeInjection, setIncludeInjection] = useState(true);

  const filteredPojos = selectedModule === 'all'
    ? POJO_REGISTRY
    : POJO_REGISTRY.filter((p) => p.module === selectedModule);

  const copyToJavaClipboard = (code: string) => {
    navigator.clipboard.writeText(code);
    setCopiedCode(true);
    setTimeout(() => setCopiedCode(false), 2000);
  };

  const generatedBuilderCode = `// 1. Initialize the Base Layer Wrapper: Chat
Chat chat = new Chat()
    .setChatId("chat-" + UUID.randomUUID())
    .setUser(new ChatUser("usr-1", "LeadDeveloper"))
    .setModel(new LlmModelParticipant()
        .setDisplayName("${modelName}")
        .setHyperparameters(new InferenceHyperparameters().setTemperature(${temperature}))
        .setReasoningBudget(new ReasoningBudgetSpecification(ReasoningEffort.${thinkingEffort}, 4096)))
    .setPromptContext(new PromptContext()
        .setSystemPrompt(new SystemPrompt("Review security vulnerabilities meticulously.")))
    .setMemoryStore(new MemoryStore());

// Attach reactive lifecycle event listeners
chat.getEventSystem()
    .addListener(ChatEventType.STEP_COMPLETED, event -> {
        System.out.println("[AUDIT HOOK] Step completed: " + event.getDescription());
    })
    .addListener(ChatEventType.AGENT_ERROR, event -> {
        System.err.println("[ALERT HOOK] Agent error detected: " + event.getDescription());
    });

// 2. Initialize the Tools Layer: ToolRegistry
ToolRegistry toolRegistry = new ToolRegistry();
${
  includeTool
    ? `toolRegistry.register(new ToolDefinition("astAnalysisTool", "Parses AST tree")
    .addParameter(new ToolParameterSpecification("sourceFile", "string", "File path", true))
    .setExecutionPolicy(new ToolExecutionPolicy().setRequiresUserConfirmation(false)));`
    : `// No initial tools registered`
}

// 3. Compose the Agent: Agent = Chat + ToolRegistry + Intentional Interaction
Agent agent = new Agent("${agentName}", "${agentRole}")
    .setChat(chat)
    .setToolRegistry(toolRegistry)
    .setExecutionConstraints(new AgentExecutionConstraints().setMaxStepsPerTurn(8));

// 4. Intentional Step Interaction with Structured Thinking
ThoughtBlock thought = new ThoughtBlock("Analyzing potential SQL injection vectors")
    .addSegment(new ThoughtSegment(0, ThoughtPhase.PROBLEM_DECOMPOSITION, "Inspect database handler", 0.95))
    .addIntent(new IntentTag(IntentType.TOOL_EXECUTION, "astAnalysisTool", "Extract call graph"))
    .addCheckpoint(new ThoughtCheckpoint("SecurityPolicyGate", CheckpointStatus.VALID, null));

AgentStep step1 = new AgentStep(1)
    .setThoughtSnapshot(thought)${
      includeInjection
        ? `
    .addInjectedDirective(new PromptDirectiveBlock()
        .setDirectiveType(DirectiveType.BEHAVIORAL_GUARDRAIL)
        .setDirectiveText("Never execute destructive shell commands"))
    .addInjectedContext(new ContextEnrichmentBlock("CWE-89", "SQL Injection Patterns", 0.99))`
        : ''
    }${
  includeTool
    ? `
    .setToolCall(new ToolCall("call-101", "astAnalysisTool", "{\\"sourceFile\\":\\"UserRepo.java\\"}"))
    .setToolResult(ToolResult.success("call-101", "astAnalysisTool", "{\\"vulnerabilitiesFound\\":0}"))`
    : ''
}
    .setGuardrailEvaluation(AgentGuardrailEvaluation.pass())
    .setReflection("AST verified clean of unparameterized concatenation.")
    .setOutcome(StepOutcome.COMPLETE_TURN);

agent.addStep(step1);
agent.setStatus(AgentStatus.COMPLETED);`;

  return (
    <div className="min-h-screen bg-slate-950 text-slate-100 flex flex-col font-sans selection:bg-cyan-500 selection:text-slate-950">
      {/* Top Navbar */}
      <header className="border-b border-slate-800 bg-slate-900/80 backdrop-blur sticky top-0 z-50 px-6 py-4 flex items-center justify-between">
        <div className="flex items-center gap-3">
          <div className="h-10 w-10 rounded-xl bg-gradient-to-tr from-cyan-500 to-blue-600 flex items-center justify-center shadow-lg shadow-cyan-500/20">
            <Layers className="h-5 w-5 text-white" />
          </div>
          <div>
            <div className="flex items-center gap-2">
              <span className="font-bold text-lg tracking-tight bg-gradient-to-r from-white via-slate-200 to-cyan-400 bg-clip-text text-transparent">
                Apex Abstract Java POJO Library
              </span>
              <span className="text-xs px-2 py-0.5 rounded-full bg-cyan-950 text-cyan-400 border border-cyan-800 font-mono">
                Java 21 • Maven
              </span>
            </div>
            <p className="text-xs text-slate-400">
              Foundational Layer: Chat (Base Wrapper) + Tools (Abstractions) → Agent (Composition &amp; Intentional Steps)
            </p>
          </div>
        </div>

        {/* Tab Controls */}
        <nav className="flex items-center gap-1 bg-slate-900 border border-slate-800 p-1 rounded-xl">
          <button
            onClick={() => setActiveTab('interactive-builder')}
            className={`px-3.5 py-1.5 text-xs font-medium rounded-lg transition-all flex items-center gap-1.5 ${
              activeTab === 'interactive-builder'
                ? 'bg-cyan-600 text-white shadow'
                : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Sliders className="h-3.5 w-3.5" />
            Live Code Assembler
          </button>
          <button
            onClick={() => setActiveTab('explorer')}
            className={`px-3.5 py-1.5 text-xs font-medium rounded-lg transition-all flex items-center gap-1.5 ${
              activeTab === 'explorer'
                ? 'bg-cyan-600 text-white shadow'
                : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <Box className="h-3.5 w-3.5" />
            POJO API Catalog
          </button>
          <button
            onClick={() => setActiveTab('architecture-diagram')}
            className={`px-3.5 py-1.5 text-xs font-medium rounded-lg transition-all flex items-center gap-1.5 ${
              activeTab === 'architecture-diagram'
                ? 'bg-cyan-600 text-white shadow'
                : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <GitBranch className="h-3.5 w-3.5" />
            Architectural Hierarchy
          </button>
          <button
            onClick={() => setActiveTab('maven-pom')}
            className={`px-3.5 py-1.5 text-xs font-medium rounded-lg transition-all flex items-center gap-1.5 ${
              activeTab === 'maven-pom'
                ? 'bg-cyan-600 text-white shadow'
                : 'text-slate-400 hover:text-slate-200'
            }`}
          >
            <FileCode2 className="h-3.5 w-3.5" />
            Maven Modules
          </button>
        </nav>
      </header>

      {/* Main Content Area */}
      <main className="flex-1 p-6 max-w-7xl mx-auto w-full">
        {activeTab === 'interactive-builder' && (
          <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
            {/* Left Control Panel */}
            <div className="lg:col-span-5 space-y-5 bg-slate-900/60 border border-slate-800 rounded-2xl p-5 backdrop-blur">
              <div className="flex items-center gap-2 border-b border-slate-800 pb-3">
                <Sliders className="h-4 w-4 text-cyan-400" />
                <h2 className="font-semibold text-sm tracking-wide uppercase text-slate-300">
                  Compose Layer Parameters
                </h2>
              </div>

              {/* Layer 3: Agent Configuration */}
              <div className="space-y-3">
                <div className="flex items-center gap-2 text-xs font-semibold text-cyan-400 uppercase tracking-wider">
                  <Bot className="h-3.5 w-3.5" />
                  Layer 3: Agent Layer
                </div>
                <div>
                  <label className="text-xs text-slate-400 block mb-1">Agent Name</label>
                  <input
                    type="text"
                    value={agentName}
                    onChange={(e) => setAgentName(e.target.value)}
                    className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-1.5 text-xs text-slate-200 focus:outline-none focus:border-cyan-500 font-mono"
                  />
                </div>
                <div>
                  <label className="text-xs text-slate-400 block mb-1">Agent Role</label>
                  <input
                    type="text"
                    value={agentRole}
                    onChange={(e) => setAgentRole(e.target.value)}
                    className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-1.5 text-xs text-slate-200 focus:outline-none focus:border-cyan-500 font-mono"
                  />
                </div>
              </div>

              {/* Layer 1: Chat Base Wrapper */}
              <div className="space-y-3 pt-2 border-t border-slate-800/80">
                <div className="flex items-center gap-2 text-xs font-semibold text-blue-400 uppercase tracking-wider">
                  <MessageSquare className="h-3.5 w-3.5" />
                  Layer 1: Chat Base Layer
                </div>
                <div>
                  <label className="text-xs text-slate-400 block mb-1">Target Model Participant</label>
                  <select
                    value={modelName}
                    onChange={(e) => setModelName(e.target.value)}
                    className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-1.5 text-xs text-slate-200 focus:outline-none focus:border-cyan-500"
                  >
                    <option value="gemini-2.5-pro">Google Gemini 2.5 Pro (Thinking)</option>
                    <option value="gemini-2.5-flash">Google Gemini 2.5 Flash</option>
                    <option value="claude-3-7-sonnet">Anthropic Claude 3.7 Sonnet</option>
                    <option value="gpt-4o">OpenAI GPT-4o</option>
                  </select>
                </div>
                <div className="grid grid-cols-2 gap-3">
                  <div>
                    <label className="text-xs text-slate-400 block mb-1">
                      Temperature ({temperature})
                    </label>
                    <input
                      type="range"
                      min="0.0"
                      max="1.0"
                      step="0.05"
                      value={temperature}
                      onChange={(e) => setTemperature(parseFloat(e.target.value))}
                      className="w-full accent-cyan-500"
                    />
                  </div>
                  <div>
                    <label className="text-xs text-slate-400 block mb-1">Thinking Effort</label>
                    <select
                      value={thinkingEffort}
                      onChange={(e) => setThinkingEffort(e.target.value as any)}
                      className="w-full bg-slate-950 border border-slate-800 rounded-lg px-3 py-1.5 text-xs text-slate-200 focus:outline-none focus:border-cyan-500"
                    >
                      <option value="NONE">NONE</option>
                      <option value="LOW">LOW</option>
                      <option value="MEDIUM">MEDIUM</option>
                      <option value="HIGH">HIGH (Deep Reasoning)</option>
                    </select>
                  </div>
                </div>
              </div>

              {/* Layer 2: Tools & Interception */}
              <div className="space-y-3 pt-2 border-t border-slate-800/80">
                <div className="flex items-center gap-2 text-xs font-semibold text-emerald-400 uppercase tracking-wider">
                  <Wrench className="h-3.5 w-3.5" />
                  Layer 2: Tools &amp; Intentional Step Features
                </div>
                <div className="flex items-center justify-between p-2.5 rounded-lg bg-slate-950 border border-slate-800">
                  <div>
                    <div className="text-xs font-medium text-slate-200">Include Tool in Step</div>
                    <div className="text-[11px] text-slate-500">ToolDefinition, ToolCall &amp; ToolResult</div>
                  </div>
                  <input
                    type="checkbox"
                    checked={includeTool}
                    onChange={(e) => setIncludeTool(e.target.checked)}
                    className="accent-cyan-500 h-4 w-4 rounded"
                  />
                </div>
                <div className="flex items-center justify-between p-2.5 rounded-lg bg-slate-950 border border-slate-800">
                  <div>
                    <div className="text-xs font-medium text-slate-200">Downstream Injection Blocks</div>
                    <div className="text-[11px] text-slate-500">PromptDirectiveBlock &amp; ContextEnrichmentBlock</div>
                  </div>
                  <input
                    type="checkbox"
                    checked={includeInjection}
                    onChange={(e) => setIncludeInjection(e.target.checked)}
                    className="accent-cyan-500 h-4 w-4 rounded"
                  />
                </div>
              </div>

              <div className="p-3 rounded-xl bg-cyan-950/40 border border-cyan-800/50 text-xs text-cyan-200 space-y-1">
                <div className="font-semibold flex items-center gap-1.5 text-cyan-300">
                  <Sparkles className="h-3.5 w-3.5" />
                  Intentional Agent Interaction Formula
                </div>
                <p className="text-[11px] text-cyan-300/80 leading-relaxed font-mono">
                  Agent = Chat (Wrapper) + ToolRegistry (Tools) + AgentStep (Intentionality)
                </p>
              </div>
            </div>

            {/* Right Generated Java Source Code Preview */}
            <div className="lg:col-span-7 flex flex-col bg-slate-900 border border-slate-800 rounded-2xl overflow-hidden shadow-2xl">
              <div className="bg-slate-950/80 border-b border-slate-800 px-4 py-3 flex items-center justify-between">
                <div className="flex items-center gap-2">
                  <FileCode2 className="h-4 w-4 text-cyan-400" />
                  <span className="text-xs font-mono font-medium text-slate-300">
                    LiveAssembledAgentWorkflow.java
                  </span>
                </div>
                <button
                  onClick={() => copyToJavaClipboard(generatedBuilderCode)}
                  className="px-2.5 py-1 rounded-md bg-slate-800 hover:bg-slate-700 text-xs text-slate-200 flex items-center gap-1.5 transition-colors border border-slate-700"
                >
                  {copiedCode ? (
                    <>
                      <Check className="h-3.5 w-3.5 text-emerald-400" />
                      <span className="text-emerald-400">Copied!</span>
                    </>
                  ) : (
                    <>
                      <Copy className="h-3.5 w-3.5" />
                      <span>Copy Java</span>
                    </>
                  )}
                </button>
              </div>
              <pre className="p-4 text-xs font-mono text-cyan-100 bg-slate-950 overflow-x-auto flex-1 leading-relaxed selection:bg-cyan-800">
                <code>{generatedBuilderCode}</code>
              </pre>
            </div>
          </div>
        )}

        {activeTab === 'explorer' && (
          <div className="grid grid-cols-1 lg:grid-cols-12 gap-6">
            {/* Left POJO List Filter */}
            <div className="lg:col-span-4 space-y-4">
              <div className="flex items-center gap-1 bg-slate-900 p-1 border border-slate-800 rounded-xl">
                <button
                  onClick={() => setSelectedModule('all')}
                  className={`flex-1 py-1 text-xs rounded-lg font-medium transition ${
                    selectedModule === 'all'
                      ? 'bg-slate-800 text-cyan-400'
                      : 'text-slate-400 hover:text-slate-200'
                  }`}
                >
                  All ({POJO_REGISTRY.length})
                </button>
                <button
                  onClick={() => setSelectedModule('apex-chat-core')}
                  className={`flex-1 py-1 text-xs rounded-lg font-medium transition ${
                    selectedModule === 'apex-chat-core'
                      ? 'bg-slate-800 text-cyan-400'
                      : 'text-slate-400 hover:text-slate-200'
                  }`}
                >
                  Core
                </button>
                <button
                  onClick={() => setSelectedModule('apex-chat-tools')}
                  className={`flex-1 py-1 text-xs rounded-lg font-medium transition ${
                    selectedModule === 'apex-chat-tools'
                      ? 'bg-slate-800 text-cyan-400'
                      : 'text-slate-400 hover:text-slate-200'
                  }`}
                >
                  Tools
                </button>
                <button
                  onClick={() => setSelectedModule('apex-chat-agent')}
                  className={`flex-1 py-1 text-xs rounded-lg font-medium transition ${
                    selectedModule === 'apex-chat-agent'
                      ? 'bg-slate-800 text-cyan-400'
                      : 'text-slate-400 hover:text-slate-200'
                  }`}
                >
                  Agent
                </button>
              </div>

              <div className="space-y-2">
                {filteredPojos.map((pojo) => (
                  <button
                    key={pojo.name}
                    onClick={() => setSelectedPojo(pojo)}
                    className={`w-full text-left p-3.5 rounded-xl border transition-all ${
                      selectedPojo.name === pojo.name
                        ? 'bg-cyan-950/40 border-cyan-500/80 shadow-md shadow-cyan-900/20'
                        : 'bg-slate-900/60 border-slate-800 hover:border-slate-700'
                    }`}
                  >
                    <div className="flex items-center justify-between">
                      <span className="font-mono font-semibold text-sm text-slate-100">
                        {pojo.name}
                      </span>
                      <span className="text-[10px] px-2 py-0.5 rounded font-mono bg-slate-800 text-slate-300">
                        {pojo.module.replace('apex-chat-', '')}
                      </span>
                    </div>
                    <div className="text-xs text-slate-400 mt-1 line-clamp-1">{pojo.description}</div>
                  </button>
                ))}
              </div>
            </div>

            {/* Right POJO Details */}
            <div className="lg:col-span-8 bg-slate-900 border border-slate-800 rounded-2xl p-6 space-y-6">
              <div className="border-b border-slate-800 pb-4">
                <div className="flex items-center gap-2 mb-1">
                  <span className="text-xs font-mono text-cyan-400 bg-cyan-950 border border-cyan-800/80 px-2 py-0.5 rounded">
                    {selectedPojo.package}
                  </span>
                  <span className="text-xs text-slate-500">•</span>
                  <span className="text-xs text-slate-400">{selectedPojo.category}</span>
                </div>
                <h2 className="text-2xl font-bold font-mono text-white">{selectedPojo.name}</h2>
                <p className="text-sm text-slate-300 mt-2 leading-relaxed">{selectedPojo.description}</p>
              </div>

              {/* Fields Table */}
              <div>
                <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-400 mb-3 flex items-center gap-1.5">
                  <Box className="h-3.5 w-3.5 text-cyan-400" />
                  Private Properties &amp; Granular Subcomponents
                </h3>
                <div className="border border-slate-800 rounded-xl overflow-hidden">
                  <table className="w-full text-left text-xs">
                    <thead className="bg-slate-950 text-slate-400 border-b border-slate-800 font-mono">
                      <tr>
                        <th className="py-2.5 px-3">Field Name</th>
                        <th className="py-2.5 px-3">Data Type</th>
                        <th className="py-2.5 px-3">Conceptual Role</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-800/80">
                      {selectedPojo.fields.map((f) => (
                        <tr key={f.name} className="hover:bg-slate-800/30 font-mono">
                          <td className="py-2.5 px-3 font-semibold text-cyan-300">{f.name}</td>
                          <td className="py-2.5 px-3 text-amber-300/90">{f.type}</td>
                          <td className="py-2.5 px-3 text-slate-300 font-sans">{f.desc}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>

              {/* Fluent Setters & Methods */}
              <div>
                <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-400 mb-2 flex items-center gap-1.5">
                  <Sliders className="h-3.5 w-3.5 text-blue-400" />
                  Fluent Chainable Setters &amp; Methods
                </h3>
                <div className="bg-slate-950 p-3 rounded-xl border border-slate-800 font-mono text-xs text-slate-300 space-y-1">
                  {selectedPojo.methods.map((m, idx) => (
                    <div key={idx} className="text-slate-300">
                      {m}
                    </div>
                  ))}
                </div>
              </div>

              {/* Usage Example */}
              <div>
                <div className="flex items-center justify-between mb-2">
                  <h3 className="text-xs font-semibold uppercase tracking-wider text-slate-400 flex items-center gap-1.5">
                    <FileCode2 className="h-3.5 w-3.5 text-emerald-400" />
                    Java Usage Pattern
                  </h3>
                  <button
                    onClick={() => copyToJavaClipboard(selectedPojo.javaCodeSample)}
                    className="text-xs text-cyan-400 hover:text-cyan-300 font-mono flex items-center gap-1"
                  >
                    <Copy className="h-3 w-3" />
                    Copy
                  </button>
                </div>
                <pre className="bg-slate-950 p-3 rounded-xl border border-slate-800 font-mono text-xs text-cyan-200 overflow-x-auto">
                  <code>{selectedPojo.javaCodeSample}</code>
                </pre>
              </div>
            </div>
          </div>
        )}

        {activeTab === 'architecture-diagram' && (
          <div className="space-y-6">
            <div className="p-6 rounded-2xl bg-slate-900 border border-slate-800 space-y-4">
              <h2 className="text-lg font-bold text-white flex items-center gap-2">
                <BrainCircuit className="h-5 w-5 text-cyan-400" />
                Three-Layer Architectural Model: Chat, Tools, and Agent
              </h2>
              <p className="text-sm text-slate-300 leading-relaxed max-w-4xl">
                The library strictly establishes that an <strong>Agent</strong> is formed through composition rather than inheritance.
                The <strong>Chat</strong> is the foundational stateful wrapper holding participants, interface, memory, prompts, and structured thinking.
                The <strong>Tools</strong> layer provides independent definitions, parameter schemas, and call/result blocks.
                The <strong>Agent</strong> layer orchestrates intentional steps over the Chat using the ToolRegistry.
              </p>
            </div>

            {/* ASCII Architecture Box */}
            <div className="bg-slate-950 border border-slate-800 rounded-2xl p-6 font-mono text-xs text-cyan-300 leading-relaxed overflow-x-auto shadow-xl">
              <pre>{`┌────────────────────────────────────────────────────────────────────────────────────────┐
│                                   3. AGENT LAYER                                       │
│                                (apex-chat-agent)                                       │
│                                                                                        │
│   Agent = Chat + ToolRegistry + Intentional Interaction Layer                          │
│                                                                                        │
│   ┌────────────────────────────────────────────────────────────────────────────────┐   │
│   │ Agent                                                                          │   │
│   │  ├── agentId, agentName, agentRole, goalDescription                            │   │
│   │  ├── chat: Chat (The Base Layer Wrapper)                                       │   │
│   │  ├── toolRegistry: ToolRegistry (Available Tools)                              │   │
│   │  ├── executionConstraints: AgentExecutionConstraints (budget, max steps)       │   │
│   │  └── stepHistory: List<AgentStep>                                              │   │
│   │        ├── links to chat.getThinking() [Phases, IntentTags, Checkpoints]       │   │
│   │        ├── links to toolRegistry.resolveTool()                                 │   │
│   │        └── dispatches ToolCall -> records ToolResult into chat turn            │   │
│   └────────────────────────────────────────────────────────────────────────────────┘   │
└───────────────────────────┬────────────────────────────────┬───────────────────────────┘
                            │ composes                       │ composes
                            ▼                                ▼
┌───────────────────────────────────────────────┐ ┌──────────────────────────────────────┐
│                1. CHAT LAYER                  │ │            2. TOOLS LAYER            │
│              (apex-chat-core)                 │ │          (apex-chat-tools)           │
│                                               │ │                                      │
│ Base Layer Wrapper encapsulating:             │ │ Independent Tool Abstractions:       │
│  ├── ChatSession & ChatInterface (medium)     │ │  ├── ToolDefinition (schemas, params)│
│  ├── Participants:                            │ │  ├── ToolParameterSpecification      │
│  │     ├── ChatUser (profile, settings, hist) │ │  ├── ToolExecutionPolicy (sandbox)   │
│  │     └── LlmModelParticipant (specs, limits)│ │  ├── ToolRegistry (catalog)          │
│  ├── Prompts & Persona (system prompts)       │ │  ├── ToolCall (invocation request)   │
│  ├── Memory & Context (working/episodic)      │ │  └── ToolResult (execution outcome)  │
│  ├── Thread, Turns, UserInput, ModelOutput    │ └──────────────────────────────────────┘
│  └── ThinkingModel (phases, intents, checks)  │
└───────────────────────────────────────────────┘`}</pre>
            </div>
          </div>
        )}

        {activeTab === 'maven-pom' && (
          <div className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div className="p-4 rounded-xl bg-slate-900 border border-slate-800">
                <div className="text-xs text-cyan-400 font-mono font-semibold">Module 1</div>
                <div className="text-base font-bold text-white mt-1">apex-chat-core</div>
                <p className="text-xs text-slate-400 mt-1">
                  Chat wrapper, participants (ChatUser, LlmModelParticipant), ChatInterface, MemoryStore, PromptContext, and ThoughtBlock.
                </p>
              </div>
              <div className="p-4 rounded-xl bg-slate-900 border border-slate-800">
                <div className="text-xs text-emerald-400 font-mono font-semibold">Module 2</div>
                <div className="text-base font-bold text-white mt-1">apex-chat-tools</div>
                <p className="text-xs text-slate-400 mt-1">
                  ToolDefinition, ToolParameterSpecification, ToolExecutionPolicy, ToolRegistry, ToolCall, and ToolResult.
                </p>
              </div>
              <div className="p-4 rounded-xl bg-slate-900 border border-slate-800">
                <div className="text-xs text-blue-400 font-mono font-semibold">Module 3</div>
                <div className="text-base font-bold text-white mt-1">apex-chat-agent</div>
                <p className="text-xs text-slate-400 mt-1">
                  Agent (composing Chat &amp; ToolRegistry), AgentStep, AgentExecutionConstraints, and AgentGuardrailEvaluation.
                </p>
              </div>
            </div>

            <div className="bg-slate-900 border border-slate-800 rounded-2xl p-5">
              <div className="flex items-center justify-between mb-3">
                <span className="text-xs font-mono text-slate-300 font-semibold">
                  Aggregator Root pom.xml
                </span>
                <span className="text-xs font-mono text-cyan-400">Java 21 Release</span>
              </div>
              <pre className="p-4 bg-slate-950 rounded-xl text-xs font-mono text-cyan-200 overflow-x-auto border border-slate-800">
                <code>{`<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.apex.chat</groupId>
    <artifactId>apex-chat-parent</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <name>Apex Chat Parent</name>
    <description>Foundational abstract Java POJO library for LLM chat, tools, and agentic systems</description>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <junit.jupiter.version>5.10.2</junit.jupiter.version>
    </properties>

    <modules>
        <module>apex-chat-core</module>
        <module>apex-chat-tools</module>
        <module>apex-chat-agent</module>
    </modules>
</project>`}</code>
              </pre>
            </div>
          </div>
        )}
      </main>
    </div>
  );
}
