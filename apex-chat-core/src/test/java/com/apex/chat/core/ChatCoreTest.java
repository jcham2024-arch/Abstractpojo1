package com.apex.chat.core;

import com.apex.chat.core.chat.Chat;
import com.apex.chat.core.content.BinaryMediaBlock;
import com.apex.chat.core.content.ContentType;
import com.apex.chat.core.content.FileDocumentBlock;
import com.apex.chat.core.content.PromptDirectiveBlock;
import com.apex.chat.core.content.TextBlock;
import com.apex.chat.core.memory.MemoryCategory;
import com.apex.chat.core.memory.MemoryEntry;
import com.apex.chat.core.message.InputModality;
import com.apex.chat.core.message.ModelOutput;
import com.apex.chat.core.message.UserInput;
import com.apex.chat.core.model.LlmModelParticipant;
import com.apex.chat.core.participant.ChatUser;
import com.apex.chat.core.thinking.CheckpointStatus;
import com.apex.chat.core.thinking.IntentTag;
import com.apex.chat.core.thinking.IntentType;
import com.apex.chat.core.thinking.ThoughtBlock;
import com.apex.chat.core.thinking.ThoughtCheckpoint;
import com.apex.chat.core.thinking.ThoughtPhase;
import com.apex.chat.core.thinking.ThoughtSegment;
import com.apex.chat.core.thread.ConversationTurn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatCoreTest {

    @Test
    public void testChatCompositeWrapperAssembly() {
        Chat chat = new Chat()
                .setChatId("chat-100");

        chat.getUser()
                .setUserId("user-42")
                .setDisplayName("Alice Engineer");

        chat.getModel()
                .setDisplayName("Gemini 2.5 Flash");

        chat.getPromptContext()
                .getSystemPrompt()
                .setCoreInstructions("You are an expert Java architect.")
                .addBehavioralRule("Always verify types.");

        chat.getMemoryStore()
                .addEpisodicMemory(new MemoryEntry(MemoryCategory.USER_PREFERENCE, "Prefers fluent setters"));

        UserInput userInput = new UserInput(chat.getUser(), "Design the architecture")
                .setModality(InputModality.TEXT)
                .addBlock(new TextBlock("Additional specifications"));

        ConversationTurn turn = new ConversationTurn(1, userInput);

        ThoughtBlock thoughtBlock = new ThoughtBlock("Analyzing user request")
                .addSegment(new ThoughtSegment(0, ThoughtPhase.ORIENTATION, "Understood requirements", 0.95))
                .addIntent(new IntentTag(IntentType.DIRECT_RESPONSE, "User", "Provide architecture blueprint"))
                .addCheckpoint(new ThoughtCheckpoint("PolicyCompliance", CheckpointStatus.VALID, null));

        ModelOutput modelOutput = new ModelOutput(chat.getModel(), "Here is the domain architecture.")
                .addBlock(thoughtBlock);

        turn.setModelOutput(modelOutput);
        chat.setActiveTurn(turn);

        Assertions.assertEquals("chat-100", chat.getChatId());
        Assertions.assertEquals("Alice Engineer", chat.getUser().getDisplayName());
        Assertions.assertEquals(1, chat.getThread().getTurns().size());
        Assertions.assertNotNull(chat.getActiveTurn().getModelOutput().getThoughtBlock());
        Assertions.assertEquals(ContentType.THOUGHT_STRUCTURED, thoughtBlock.getContentType());
        Assertions.assertEquals(1, thoughtBlock.getSegments().size());
        Assertions.assertEquals(1, thoughtBlock.getIntents().size());
        Assertions.assertEquals(1, thoughtBlock.getCheckpoints().size());
    }

    @Test
    public void testEventSystemLifecycleAndRegistry() {
        Chat chat = new Chat().setChatId("chat-200");
        java.util.List<com.apex.chat.core.event.ChatLifecycleEvent> received = new java.util.ArrayList<>();

        // Register global listener
        chat.getEventSystem().addListener(received::add);

        // Register type-specific listener
        java.util.concurrent.atomic.AtomicInteger errorCount = new java.util.concurrent.atomic.AtomicInteger(0);
        chat.getEventSystem().addListener(com.apex.chat.core.event.ChatEventType.AGENT_ERROR, event -> {
            errorCount.incrementAndGet();
        });

        // Publish events
        chat.getEventSystem().publish(new com.apex.chat.core.event.ChatStartedEvent("chat-200", "Hello", "Gemini 2.5 Flash"));
        chat.getEventSystem().publish(new com.apex.chat.core.event.StepCompletedEvent("chat-200", 1, true, "sqlTool").setExecutionDurationMs(85));
        chat.getEventSystem().publish(new com.apex.chat.core.event.AgentErrorEvent("chat-200", "ERR_TIMEOUT", "Operation timed out").setRecoverable(true));

        Assertions.assertEquals(3, received.size());
        Assertions.assertEquals(com.apex.chat.core.event.ChatEventType.CHAT_STARTED, received.get(0).getEventType());
        Assertions.assertEquals(com.apex.chat.core.event.ChatEventType.STEP_COMPLETED, received.get(1).getEventType());
        Assertions.assertEquals(com.apex.chat.core.event.ChatEventType.AGENT_ERROR, received.get(2).getEventType());
        Assertions.assertEquals(1, errorCount.get());

        // Verify history tracking
        Assertions.assertEquals(3, chat.getEventSystem().getEventHistory().size());
    }

    @Test
    public void testPersistenceDescriptor() {
        com.apex.chat.core.persistence.PersistenceDescriptor descriptor = com.apex.chat.core.persistence.PersistenceDescriptor
                .fileSystem("/var/data/apex/chats")
                .setSerializationFormat(com.apex.chat.core.persistence.SerializationFormat.JSON)
                .setAutoSaveIntervalSeconds(30)
                .setEncryptAtRest(true)
                .setRetentionPolicyDays(90)
                .putCustomProperty("partitionBy", "userId");

        Assertions.assertEquals(com.apex.chat.core.persistence.StorageStrategy.FILE_SYSTEM, descriptor.getStorageStrategy());
        Assertions.assertEquals(com.apex.chat.core.persistence.SerializationFormat.JSON, descriptor.getSerializationFormat());
        Assertions.assertTrue(descriptor.getCompressionSettings().isCompressionEnabled());
        Assertions.assertEquals(com.apex.chat.core.persistence.CompressionAlgorithm.GZIP, descriptor.getCompressionSettings().getAlgorithm());
        Assertions.assertTrue(descriptor.isEncryptAtRest());
        Assertions.assertEquals("userId", descriptor.getCustomProperties().get("partitionBy"));

        // Verify attachment to Chat wrapper
        Chat chat = new Chat().setPersistenceDescriptor(descriptor);
        Assertions.assertEquals(descriptor, chat.getPersistenceDescriptor());
    }

    @Test
    public void testTelemetryEventAndCostEstimation() {
        com.apex.chat.core.message.GenerationTelemetry telemetry = new com.apex.chat.core.message.GenerationTelemetry(1200, 350, 480L)
                .setTimeToFirstTokenMs(140L)
                .setCachedTokens(256)
                .setReasoningTokens(180);

        com.apex.chat.core.telemetry.CostEstimation cost = new com.apex.chat.core.telemetry.CostEstimation(0.0012, 0.0035, 0.0018)
                .setCurrencyCode("USD")
                .setPricingTierIdentifier("pay-as-you-go");

        com.apex.chat.core.telemetry.TelemetryEvent event = com.apex.chat.core.telemetry.TelemetryEvent
                .of("chat-777", "gemini-2.5-pro", telemetry, cost)
                .setTurnIndex(2)
                .setStepIndex(1);

        Assertions.assertEquals(com.apex.chat.core.event.ChatEventType.TELEMETRY_RECORDED, event.getEventType());
        Assertions.assertEquals("chat-777", event.getChatId());
        Assertions.assertEquals("gemini-2.5-pro", event.getModelIdentifier());
        Assertions.assertEquals(1550, event.getTelemetry().getTotalTokens());
        Assertions.assertEquals(140L, event.getTelemetry().getTimeToFirstTokenMs());
        Assertions.assertEquals(0.0065, event.getCostEstimation().getTotalCostUsd(), 0.0001);

        // Verify dispatch through ChatEventSystem
        Chat chat = new Chat().setChatId("chat-777");
        java.util.concurrent.atomic.AtomicBoolean listenerTriggered = new java.util.concurrent.atomic.AtomicBoolean(false);

        chat.getEventSystem().addListener(com.apex.chat.core.event.ChatEventType.TELEMETRY_RECORDED, evt -> {
            if (evt instanceof com.apex.chat.core.telemetry.TelemetryEvent te) {
                if ("gemini-2.5-pro".equals(te.getModelIdentifier())) {
                    listenerTriggered.set(true);
                }
            }
        });

        chat.getEventSystem().publish(event);
        Assertions.assertTrue(listenerTriggered.get());
    }

    @Test
    public void testMemoryStoreEpisodicAndContextManagement() {
        com.apex.chat.core.memory.MemoryStore store = new com.apex.chat.core.memory.MemoryStore();

        // 1. Working scratchpad
        store.putScratchpadNote("activeTask", "Refactor memory subsystem")
                .addWorkingGoal("Ensure zero data loss")
                .addWorkingHypothesis("Token usage can be reduced by 25%");

        Assertions.assertEquals("Refactor memory subsystem", store.getScratchpadNote("activeTask"));
        Assertions.assertEquals(1, store.getWorkingMemory().getActiveGoals().size());
        Assertions.assertEquals(1, store.getWorkingMemory().getActiveHypotheses().size());

        // 2. Episodic memory
        store.addEpisodicMemory(com.apex.chat.core.memory.MemoryCategory.USER_PREFERENCE, "Likes concise summaries", 0.9)
                .addEpisodicMemory(com.apex.chat.core.memory.MemoryCategory.FACTUAL_KNOWLEDGE, "Java 21 LTS release", 0.7)
                .addEpisodicMemory(com.apex.chat.core.memory.MemoryCategory.EPISODIC_EVENT, "Resolved bug #402", 0.4);

        java.util.List<com.apex.chat.core.memory.MemoryEntry> prefs = store.findMemoriesByCategory(com.apex.chat.core.memory.MemoryCategory.USER_PREFERENCE);
        Assertions.assertEquals(1, prefs.size());
        Assertions.assertEquals("Likes concise summaries", prefs.get(0).getContent());

        java.util.List<com.apex.chat.core.memory.MemoryEntry> highImp = store.findMemoriesAboveImportance(0.8);
        Assertions.assertEquals(1, highImp.size());

        // 3. Context window management for conversation turns
        store.setContextWindowPolicy(new com.apex.chat.core.memory.ContextWindowPolicy(8000, 3));
        Assertions.assertTrue(store.isContextExceeded(7500)); // 7500 > (8000 - 4096 = 3904)

        java.util.List<com.apex.chat.core.thread.ConversationTurn> turns = new java.util.ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            turns.add(new com.apex.chat.core.thread.ConversationTurn(i, null));
        }

        java.util.List<com.apex.chat.core.thread.ConversationTurn> activeTurns = store.selectActiveTurns(turns);
        Assertions.assertEquals(3, activeTurns.size());
        Assertions.assertEquals(3, activeTurns.get(0).getTurnIndex());
        Assertions.assertEquals(5, activeTurns.get(2).getTurnIndex());
    }

    @Test
    public void testContentBlocksPolymorphism() {
        TextBlock textBlock = TextBlock.of("Hello world");
        BinaryMediaBlock binaryBlock = new BinaryMediaBlock("image/png", new byte[]{1, 2, 3}, "diagram.png");
        FileDocumentBlock docBlock = new FileDocumentBlock("Schema", "application/json", "{}");
        PromptDirectiveBlock directiveBlock = new PromptDirectiveBlock();

        Assertions.assertEquals(ContentType.TEXT, textBlock.getContentType());
        Assertions.assertEquals(ContentType.BINARY_MEDIA, binaryBlock.getContentType());
        Assertions.assertEquals(ContentType.FILE_DOCUMENT, docBlock.getContentType());
        Assertions.assertEquals(ContentType.PROMPT_DIRECTIVE, directiveBlock.getContentType());
    }
}
