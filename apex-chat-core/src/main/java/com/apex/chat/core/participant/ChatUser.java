package com.apex.chat.core.participant;

import java.util.Objects;
import java.util.UUID;

/**
 * Human user participant in the chat.
 */
public class ChatUser implements ChatParticipant {

    private String userId;
    private String displayName;
    private UserProfile userProfile;
    private UserSettingsProfile settingsProfile;
    private UserInteractionHistory userHistory;
    private ParticipantStatus status;

    public ChatUser() {
        this.userId = UUID.randomUUID().toString();
        this.displayName = "User";
        this.userProfile = new UserProfile();
        this.settingsProfile = new UserSettingsProfile();
        this.userHistory = new UserInteractionHistory();
        this.status = ParticipantStatus.ONLINE;
    }

    public ChatUser(String userId, String displayName) {
        this();
        this.userId = userId;
        this.displayName = displayName;
    }

    @Override
    public String getParticipantId() {
        return userId;
    }

    public String getUserId() {
        return userId;
    }

    public ChatUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public ChatUser setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public ParticipantType getParticipantType() {
        return ParticipantType.HUMAN_USER;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public ChatUser setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile != null ? userProfile : new UserProfile();
        return this;
    }

    public UserSettingsProfile getSettingsProfile() {
        return settingsProfile;
    }

    public ChatUser setSettingsProfile(UserSettingsProfile settingsProfile) {
        this.settingsProfile = settingsProfile != null ? settingsProfile : new UserSettingsProfile();
        return this;
    }

    public UserInteractionHistory getUserHistory() {
        return userHistory;
    }

    public ChatUser setUserHistory(UserInteractionHistory userHistory) {
        this.userHistory = userHistory != null ? userHistory : new UserInteractionHistory();
        return this;
    }

    @Override
    public ParticipantStatus getStatus() {
        return status;
    }

    public ChatUser setStatus(ParticipantStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatUser chatUser)) return false;
        return Objects.equals(userId, chatUser.userId) &&
                Objects.equals(displayName, chatUser.displayName) &&
                Objects.equals(userProfile, chatUser.userProfile) &&
                Objects.equals(settingsProfile, chatUser.settingsProfile) &&
                Objects.equals(userHistory, chatUser.userHistory) &&
                status == chatUser.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, displayName, userProfile, settingsProfile, userHistory, status);
    }

    @Override
    public String toString() {
        return "ChatUser{" +
                "userId='" + userId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", status=" + status +
                '}';
    }
}
