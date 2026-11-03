package com.apex.chat.core.ui;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Concrete or virtual medium through which the user and model interact.
 */
public class ChatInterface implements Serializable {

    private String interfaceId;
    private InterfaceType interfaceType;
    private String displayName;
    private InterfaceCapabilities capabilities;
    private RuntimeEnvironment runtimeEnvironment;
    private InterfaceInteractionState interactionState;

    public ChatInterface() {
        this.interfaceId = UUID.randomUUID().toString();
        this.interfaceType = InterfaceType.WEB_BROWSER;
        this.displayName = "Web Browser Interface";
        this.capabilities = new InterfaceCapabilities();
        this.runtimeEnvironment = new RuntimeEnvironment();
        this.interactionState = new InterfaceInteractionState();
    }

    public ChatInterface(InterfaceType interfaceType, String displayName) {
        this();
        this.interfaceType = interfaceType;
        this.displayName = displayName;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public ChatInterface setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public ChatInterface setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ChatInterface setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public InterfaceCapabilities getCapabilities() {
        return capabilities;
    }

    public ChatInterface setCapabilities(InterfaceCapabilities capabilities) {
        this.capabilities = capabilities != null ? capabilities : new InterfaceCapabilities();
        return this;
    }

    public RuntimeEnvironment getRuntimeEnvironment() {
        return runtimeEnvironment;
    }

    public ChatInterface setRuntimeEnvironment(RuntimeEnvironment runtimeEnvironment) {
        this.runtimeEnvironment = runtimeEnvironment != null ? runtimeEnvironment : new RuntimeEnvironment();
        return this;
    }

    public InterfaceInteractionState getInteractionState() {
        return interactionState;
    }

    public ChatInterface setInteractionState(InterfaceInteractionState interactionState) {
        this.interactionState = interactionState != null ? interactionState : new InterfaceInteractionState();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatInterface that)) return false;
        return Objects.equals(interfaceId, that.interfaceId) &&
                interfaceType == that.interfaceType &&
                Objects.equals(displayName, that.displayName) &&
                Objects.equals(capabilities, that.capabilities) &&
                Objects.equals(runtimeEnvironment, that.runtimeEnvironment) &&
                Objects.equals(interactionState, that.interactionState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interfaceId, interfaceType, displayName, capabilities, runtimeEnvironment, interactionState);
    }

    @Override
    public String toString() {
        return "ChatInterface{" +
                "interfaceId='" + interfaceId + '\'' +
                ", interfaceType=" + interfaceType +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
