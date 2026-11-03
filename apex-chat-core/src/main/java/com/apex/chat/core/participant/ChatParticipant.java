package com.apex.chat.core.participant;

import java.io.Serializable;

/**
 * Common polymorphic interface for any conversational entity in a chat (User, Model, Service).
 */
public interface ChatParticipant extends Serializable {

    /**
     * Unique identifier for this participant.
     *
     * @return participant ID
     */
    String getParticipantId();

    /**
     * Human-friendly display name.
     *
     * @return display name
     */
    String getDisplayName();

    /**
     * Broad category of the participant.
     *
     * @return ParticipantType enum
     */
    ParticipantType getParticipantType();

    /**
     * Current operational status.
     *
     * @return ParticipantStatus enum
     */
    ParticipantStatus getStatus();
}
