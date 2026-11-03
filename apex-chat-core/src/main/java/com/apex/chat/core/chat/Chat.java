package com.apex.chat.core.chat;

import com.apex.chat.core.event.ChatEventSystem;
import com.apex.chat.core.memory.MemoryStore;
import com.apex.chat.core.model.LlmModelParticipant;
import com.apex.chat.core.participant.ChatUser;
import com.apex.chat.core.persistence.PersistenceDescriptor;
import com.apex.chat.core.prompt.PromptContext;
import com.apex.chat.core.session.ChatSession;
import com.apex.chat.core.thread.ConversationThread;
import com.apex.chat.core.thread.ConversationTurn;
import com.apex.chat.core.ui.ChatInterface;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Root composite wrapper representing an ongoing, stateful chat interaction.
 * Encapsulates the runtime medium, participants, prompt rules, memory, event system, persistence, and conversation thread.
 */
public class Chat implements Serializable {

    private String chatId;
    private ChatSession session;
    private ChatInterface chatInterface;
    private ChatUser user;
    private LlmModelParticipant model;
    private PromptContext promptContext;
    private MemoryStore memoryStore;
    private ChatEventSystem eventSystem;
    private PersistenceDescriptor persistenceDescriptor;
    private ConversationThread thread;
    private ConversationTurn activeTurn;

    public Chat() {
        this.chatId = UUID.randomUUID().toString();
        this.session = new ChatSession();
        this.chatInterface = new ChatInterface();
        this.user = new ChatUser();
        this.model = new LlmModelParticipant();
        this.promptContext = new PromptContext();
        this.memoryStore = new MemoryStore();
        this.eventSystem = new ChatEventSystem();
        this.persistenceDescriptor = PersistenceDescriptor.inMemory();
        this.thread = new ConversationThread();
    }

    public Chat(String chatId) {
        this();
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public Chat setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public ChatSession getSession() {
        return session;
    }

    public Chat setSession(ChatSession session) {
        this.session = session != null ? session : new ChatSession();
        return this;
    }

    public ChatInterface getChatInterface() {
        return chatInterface;
    }

    public Chat setChatInterface(ChatInterface chatInterface) {
        this.chatInterface = chatInterface != null ? chatInterface : new ChatInterface();
        return this;
    }

    public ChatUser getUser() {
        return user;
    }

    public Chat setUser(ChatUser user) {
        this.user = user != null ? user : new ChatUser();
        return this;
    }

    public LlmModelParticipant getModel() {
        return model;
    }

    public Chat setModel(LlmModelParticipant model) {
        this.model = model != null ? model : new LlmModelParticipant();
        return this;
    }

    public PromptContext getPromptContext() {
        return promptContext;
    }

    public Chat setPromptContext(PromptContext promptContext) {
        this.promptContext = promptContext != null ? promptContext : new PromptContext();
        return this;
    }

    public MemoryStore getMemoryStore() {
        return memoryStore;
    }

    public Chat setMemoryStore(MemoryStore memoryStore) {
        this.memoryStore = memoryStore != null ? memoryStore : new MemoryStore();
        return this;
    }

    public ChatEventSystem getEventSystem() {
        return eventSystem;
    }

    public Chat setEventSystem(ChatEventSystem eventSystem) {
        this.eventSystem = eventSystem != null ? eventSystem : new ChatEventSystem();
        return this;
    }

    public PersistenceDescriptor getPersistenceDescriptor() {
        return persistenceDescriptor;
    }

    public Chat setPersistenceDescriptor(PersistenceDescriptor persistenceDescriptor) {
        this.persistenceDescriptor = persistenceDescriptor != null ? persistenceDescriptor : PersistenceDescriptor.inMemory();
        return this;
    }

    public ConversationThread getThread() {
        return thread;
    }

    public Chat setThread(ConversationThread thread) {
        this.thread = thread != null ? thread : new ConversationThread();
        return this;
    }

    public ConversationTurn getActiveTurn() {
        return activeTurn;
    }

    public Chat setActiveTurn(ConversationTurn activeTurn) {
        this.activeTurn = activeTurn;
        if (activeTurn != null && this.thread != null) {
            this.thread.addTurn(activeTurn);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chat chat)) return false;
        return Objects.equals(chatId, chat.chatId) &&
                Objects.equals(session, chat.session) &&
                Objects.equals(chatInterface, chat.chatInterface) &&
                Objects.equals(user, chat.user) &&
                Objects.equals(model, chat.model) &&
                Objects.equals(promptContext, chat.promptContext) &&
                Objects.equals(memoryStore, chat.memoryStore) &&
                Objects.equals(eventSystem, chat.eventSystem) &&
                Objects.equals(persistenceDescriptor, chat.persistenceDescriptor) &&
                Objects.equals(thread, chat.thread) &&
                Objects.equals(activeTurn, chat.activeTurn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, session, chatInterface, user, model, promptContext, memoryStore, eventSystem, persistenceDescriptor, thread, activeTurn);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId='" + chatId + '\'' +
                ", session=" + session +
                ", user=" + user.getDisplayName() +
                ", model=" + model.getDisplayName() +
                ", persistence=" + persistenceDescriptor.getStorageStrategy() +
                '}';
    }
}
