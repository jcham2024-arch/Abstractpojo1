package com.apex.chat.core.participant;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Historical engagement statistics and activity log for a user.
 */
public class UserInteractionHistory implements Serializable {

    private int totalSessionsCount;
    private int totalMessagesSent;
    private Instant firstSeenAt;
    private Instant lastSeenAt;
    private List<String> bookmarkedMessageIds;
    private int thumbsUpCount;
    private int thumbsDownCount;

    public UserInteractionHistory() {
        this.firstSeenAt = Instant.now();
        this.lastSeenAt = this.firstSeenAt;
        this.bookmarkedMessageIds = new ArrayList<>();
    }

    public int getTotalSessionsCount() {
        return totalSessionsCount;
    }

    public UserInteractionHistory setTotalSessionsCount(int totalSessionsCount) {
        this.totalSessionsCount = totalSessionsCount;
        return this;
    }

    public UserInteractionHistory incrementSessionsCount() {
        this.totalSessionsCount++;
        return this;
    }

    public int getTotalMessagesSent() {
        return totalMessagesSent;
    }

    public UserInteractionHistory setTotalMessagesSent(int totalMessagesSent) {
        this.totalMessagesSent = totalMessagesSent;
        return this;
    }

    public UserInteractionHistory incrementMessagesSent() {
        this.totalMessagesSent++;
        return this;
    }

    public Instant getFirstSeenAt() {
        return firstSeenAt;
    }

    public UserInteractionHistory setFirstSeenAt(Instant firstSeenAt) {
        this.firstSeenAt = firstSeenAt;
        return this;
    }

    public Instant getLastSeenAt() {
        return lastSeenAt;
    }

    public UserInteractionHistory setLastSeenAt(Instant lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
        return this;
    }

    public List<String> getBookmarkedMessageIds() {
        return bookmarkedMessageIds;
    }

    public UserInteractionHistory setBookmarkedMessageIds(List<String> bookmarkedMessageIds) {
        this.bookmarkedMessageIds = bookmarkedMessageIds != null ? bookmarkedMessageIds : new ArrayList<>();
        return this;
    }

    public UserInteractionHistory addBookmark(String messageId) {
        if (this.bookmarkedMessageIds == null) {
            this.bookmarkedMessageIds = new ArrayList<>();
        }
        this.bookmarkedMessageIds.add(messageId);
        return this;
    }

    public int getThumbsUpCount() {
        return thumbsUpCount;
    }

    public UserInteractionHistory setThumbsUpCount(int thumbsUpCount) {
        this.thumbsUpCount = thumbsUpCount;
        return this;
    }

    public int getThumbsDownCount() {
        return thumbsDownCount;
    }

    public UserInteractionHistory setThumbsDownCount(int thumbsDownCount) {
        this.thumbsDownCount = thumbsDownCount;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInteractionHistory that)) return false;
        return totalSessionsCount == that.totalSessionsCount &&
                totalMessagesSent == that.totalMessagesSent &&
                thumbsUpCount == that.thumbsUpCount &&
                thumbsDownCount == that.thumbsDownCount &&
                Objects.equals(firstSeenAt, that.firstSeenAt) &&
                Objects.equals(lastSeenAt, that.lastSeenAt) &&
                Objects.equals(bookmarkedMessageIds, that.bookmarkedMessageIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalSessionsCount, totalMessagesSent, firstSeenAt, lastSeenAt,
                bookmarkedMessageIds, thumbsUpCount, thumbsDownCount);
    }

    @Override
    public String toString() {
        return "UserInteractionHistory{" +
                "totalSessionsCount=" + totalSessionsCount +
                ", totalMessagesSent=" + totalMessagesSent +
                ", thumbsUpCount=" + thumbsUpCount +
                ", thumbsDownCount=" + thumbsDownCount +
                '}';
    }
}
