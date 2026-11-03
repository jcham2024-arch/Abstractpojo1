package com.apex.chat.core.thinking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Inspection or safety checkpoint captured during a model's thinking trace.
 */
public class ThoughtCheckpoint implements Serializable {

    private String checkpointId;
    private String name;
    private CheckpointStatus status;
    private String flaggedRisk;
    private List<String> assumptionsValidated;

    public ThoughtCheckpoint() {
        this.checkpointId = UUID.randomUUID().toString();
        this.status = CheckpointStatus.UNVERIFIED;
        this.assumptionsValidated = new ArrayList<>();
    }

    public ThoughtCheckpoint(String name, CheckpointStatus status, String flaggedRisk) {
        this.checkpointId = UUID.randomUUID().toString();
        this.name = name;
        this.status = status;
        this.flaggedRisk = flaggedRisk;
        this.assumptionsValidated = new ArrayList<>();
    }

    public String getCheckpointId() {
        return checkpointId;
    }

    public ThoughtCheckpoint setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ThoughtCheckpoint setName(String name) {
        this.name = name;
        return this;
    }

    public CheckpointStatus getStatus() {
        return status;
    }

    public ThoughtCheckpoint setStatus(CheckpointStatus status) {
        this.status = status;
        return this;
    }

    public String getFlaggedRisk() {
        return flaggedRisk;
    }

    public ThoughtCheckpoint setFlaggedRisk(String flaggedRisk) {
        this.flaggedRisk = flaggedRisk;
        return this;
    }

    public List<String> getAssumptionsValidated() {
        return assumptionsValidated;
    }

    public ThoughtCheckpoint setAssumptionsValidated(List<String> assumptionsValidated) {
        this.assumptionsValidated = assumptionsValidated != null ? assumptionsValidated : new ArrayList<>();
        return this;
    }

    public ThoughtCheckpoint addAssumption(String assumption) {
        if (this.assumptionsValidated == null) {
            this.assumptionsValidated = new ArrayList<>();
        }
        this.assumptionsValidated.add(assumption);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThoughtCheckpoint that)) return false;
        return Objects.equals(checkpointId, that.checkpointId) &&
                Objects.equals(name, that.name) &&
                status == that.status &&
                Objects.equals(flaggedRisk, that.flaggedRisk) &&
                Objects.equals(assumptionsValidated, that.assumptionsValidated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkpointId, name, status, flaggedRisk, assumptionsValidated);
    }

    @Override
    public String toString() {
        return "ThoughtCheckpoint{" +
                "checkpointId='" + checkpointId + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", flaggedRisk='" + flaggedRisk + '\'' +
                '}';
    }
}
