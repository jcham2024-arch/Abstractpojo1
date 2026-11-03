package com.apex.chat.core.ui;

import java.io.Serializable;
import java.util.Objects;

/**
 * Transient real-time state of the client chat UI.
 */
public class InterfaceInteractionState implements Serializable {

    private boolean userIsTyping;
    private boolean modelIsGenerating;
    private String currentDraftInput;
    private boolean hasActiveModal;

    public InterfaceInteractionState() {
    }

    public boolean isUserIsTyping() {
        return userIsTyping;
    }

    public InterfaceInteractionState setUserIsTyping(boolean userIsTyping) {
        this.userIsTyping = userIsTyping;
        return this;
    }

    public boolean isModelIsGenerating() {
        return modelIsGenerating;
    }

    public InterfaceInteractionState setModelIsGenerating(boolean modelIsGenerating) {
        this.modelIsGenerating = modelIsGenerating;
        return this;
    }

    public String getCurrentDraftInput() {
        return currentDraftInput;
    }

    public InterfaceInteractionState setCurrentDraftInput(String currentDraftInput) {
        this.currentDraftInput = currentDraftInput;
        return this;
    }

    public boolean isHasActiveModal() {
        return hasActiveModal;
    }

    public InterfaceInteractionState setHasActiveModal(boolean hasActiveModal) {
        this.hasActiveModal = hasActiveModal;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterfaceInteractionState that)) return false;
        return userIsTyping == that.userIsTyping &&
                modelIsGenerating == that.modelIsGenerating &&
                hasActiveModal == that.hasActiveModal &&
                Objects.equals(currentDraftInput, that.currentDraftInput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIsTyping, modelIsGenerating, currentDraftInput, hasActiveModal);
    }

    @Override
    public String toString() {
        return "InterfaceInteractionState{" +
                "userIsTyping=" + userIsTyping +
                ", modelIsGenerating=" + modelIsGenerating +
                ", hasActiveModal=" + hasActiveModal +
                '}';
    }
}
