package com.apex.chat.core.content;

import java.io.Serializable;

/**
 * Common polymorphic interface for any atomic content block within a message or turn.
 */
public interface ContentBlock extends Serializable {

    /**
     * Unique identifier of this content block.
     *
     * @return block identifier string
     */
    String getBlockId();

    /**
     * Type descriptor indicating how this block is interpreted.
     *
     * @return ContentType enum
     */
    ContentType getContentType();
}
