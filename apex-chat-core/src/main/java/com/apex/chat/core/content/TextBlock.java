package com.apex.chat.core.content;

import java.util.Objects;
import java.util.UUID;

/**
 * Textual content block within a chat input or output.
 */
public class TextBlock implements ContentBlock {

    private String blockId;
    private String text;

    public TextBlock() {
        this.blockId = UUID.randomUUID().toString();
    }

    public TextBlock(String text) {
        this.blockId = UUID.randomUUID().toString();
        this.text = text;
    }

    public TextBlock(String blockId, String text) {
        this.blockId = blockId != null ? blockId : UUID.randomUUID().toString();
        this.text = text;
    }

    public static TextBlock of(String text) {
        return new TextBlock(text);
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public TextBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public String getText() {
        return text;
    }

    public TextBlock setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.TEXT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextBlock that)) return false;
        return Objects.equals(blockId, that.blockId) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, text);
    }

    @Override
    public String toString() {
        return "TextBlock{" +
                "blockId='" + blockId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
