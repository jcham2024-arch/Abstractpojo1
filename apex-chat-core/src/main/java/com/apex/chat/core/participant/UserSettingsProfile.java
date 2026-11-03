package com.apex.chat.core.participant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * User-configurable client options and preferences.
 */
public class UserSettingsProfile implements Serializable {

    private ThemePreference theme;
    private boolean streamResponsesEnabled;
    private boolean autoScrollEnabled;
    private boolean soundAlertsEnabled;
    private String customSystemInstructions;
    private Map<String, String> clientFlags;

    public UserSettingsProfile() {
        this.theme = ThemePreference.SYSTEM_DEFAULT;
        this.streamResponsesEnabled = true;
        this.autoScrollEnabled = true;
        this.soundAlertsEnabled = false;
        this.clientFlags = new HashMap<>();
    }

    public ThemePreference getTheme() {
        return theme;
    }

    public UserSettingsProfile setTheme(ThemePreference theme) {
        this.theme = theme;
        return this;
    }

    public boolean isStreamResponsesEnabled() {
        return streamResponsesEnabled;
    }

    public UserSettingsProfile setStreamResponsesEnabled(boolean streamResponsesEnabled) {
        this.streamResponsesEnabled = streamResponsesEnabled;
        return this;
    }

    public boolean isAutoScrollEnabled() {
        return autoScrollEnabled;
    }

    public UserSettingsProfile setAutoScrollEnabled(boolean autoScrollEnabled) {
        this.autoScrollEnabled = autoScrollEnabled;
        return this;
    }

    public boolean isSoundAlertsEnabled() {
        return soundAlertsEnabled;
    }

    public UserSettingsProfile setSoundAlertsEnabled(boolean soundAlertsEnabled) {
        this.soundAlertsEnabled = soundAlertsEnabled;
        return this;
    }

    public String getCustomSystemInstructions() {
        return customSystemInstructions;
    }

    public UserSettingsProfile setCustomSystemInstructions(String customSystemInstructions) {
        this.customSystemInstructions = customSystemInstructions;
        return this;
    }

    public Map<String, String> getClientFlags() {
        return clientFlags;
    }

    public UserSettingsProfile setClientFlags(Map<String, String> clientFlags) {
        this.clientFlags = clientFlags != null ? clientFlags : new HashMap<>();
        return this;
    }

    public UserSettingsProfile putClientFlag(String key, String value) {
        if (this.clientFlags == null) {
            this.clientFlags = new HashMap<>();
        }
        this.clientFlags.put(key, value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSettingsProfile that)) return false;
        return streamResponsesEnabled == that.streamResponsesEnabled &&
                autoScrollEnabled == that.autoScrollEnabled &&
                soundAlertsEnabled == that.soundAlertsEnabled &&
                theme == that.theme &&
                Objects.equals(customSystemInstructions, that.customSystemInstructions) &&
                Objects.equals(clientFlags, that.clientFlags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(theme, streamResponsesEnabled, autoScrollEnabled, soundAlertsEnabled,
                customSystemInstructions, clientFlags);
    }

    @Override
    public String toString() {
        return "UserSettingsProfile{" +
                "theme=" + theme +
                ", streamResponsesEnabled=" + streamResponsesEnabled +
                ", soundAlertsEnabled=" + soundAlertsEnabled +
                '}';
    }
}
