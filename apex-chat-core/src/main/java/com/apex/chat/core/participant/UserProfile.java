package com.apex.chat.core.participant;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;

/**
 * Demographic and identity profile of a chat user.
 */
public class UserProfile implements Serializable {

    private String username;
    private String email;
    private URI avatarUrl;
    private String preferredLanguage;
    private String userRole;

    public UserProfile() {
        this.preferredLanguage = "en";
        this.userRole = "Standard User";
    }

    public UserProfile(String username, String email) {
        this();
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public UserProfile setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public URI getAvatarUrl() {
        return avatarUrl;
    }

    public UserProfile setAvatarUrl(URI avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public UserProfile setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserProfile setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile that)) return false;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(preferredLanguage, that.preferredLanguage) &&
                Objects.equals(userRole, that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, avatarUrl, preferredLanguage, userRole);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
