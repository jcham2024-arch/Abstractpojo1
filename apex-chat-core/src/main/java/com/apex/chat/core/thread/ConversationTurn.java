package com.apex.chat.core.thread;

import com.apex.chat.core.message.ModelOutput;
import com.apex.chat.core.message.UserInput;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A discrete dialogue exchange pair within a conversation thread.
 */
public class ConversationTurn implements Serializable {

    private String turnId;
    private int turnIndex;
    private Instant startedAt;
    private Instant completedAt;
    private UserInput userInput;
    private ModelOutput modelOutput;
    private TurnStatus turnStatus;

    public ConversationTurn() {
        this.turnId = UUID.randomUUID().toString();
        this.startedAt = Instant.now();
        this.turnStatus = TurnStatus.PENDING;
    }

    public ConversationTurn(int turnIndex, UserInput userInput) {
        this();
        this.turnIndex = turnIndex;
        this.userInput = userInput;
    }

    public String getTurnId() {
        return turnId;
    }

    public ConversationTurn setTurnId(String turnId) {
        this.turnId = turnId;
        return this;
    }

    public int getTurnIndex() {
        return turnIndex;
    }

    public ConversationTurn setTurnIndex(int turnIndex) {
        this.turnIndex = turnIndex;
        return this;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public ConversationTurn setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public ConversationTurn setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
        return this;
    }

    public UserInput getUserInput() {
        return userInput;
    }

    public ConversationTurn setUserInput(UserInput userInput) {
        this.userInput = userInput;
        return this;
    }

    public ModelOutput getModelOutput() {
        return modelOutput;
    }

    public ConversationTurn setModelOutput(ModelOutput modelOutput) {
        this.modelOutput = modelOutput;
        if (modelOutput != null && this.turnStatus == TurnStatus.PENDING) {
            this.turnStatus = TurnStatus.COMPLETED;
            this.completedAt = Instant.now();
        }
        return this;
    }

    public TurnStatus getTurnStatus() {
        return turnStatus;
    }

    public ConversationTurn setTurnStatus(TurnStatus turnStatus) {
        this.turnStatus = turnStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConversationTurn that)) return false;
        return turnIndex == that.turnIndex &&
                Objects.equals(turnId, that.turnId) &&
                Objects.equals(startedAt, that.startedAt) &&
                Objects.equals(completedAt, that.completedAt) &&
                Objects.equals(userInput, that.userInput) &&
                Objects.equals(modelOutput, that.modelOutput) &&
                turnStatus == that.turnStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turnId, turnIndex, startedAt, completedAt, userInput, modelOutput, turnStatus);
    }

    @Override
    public String toString() {
        return "ConversationTurn{" +
                "turnId='" + turnId + '\'' +
                ", turnIndex=" + turnIndex +
                ", turnStatus=" + turnStatus +
                '}';
    }
}
