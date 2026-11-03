package com.apex.chat.core.content;

import java.util.Objects;
import java.util.UUID;

/**
 * Foundational content block representing an injected prompt directive or runtime rule.
 */
public class PromptDirectiveBlock implements ContentBlock {

    private String blockId;
    private DirectiveType directiveType;
    private String directiveText;
    private int priority;

    public PromptDirectiveBlock() {
        this.blockId = UUID.randomUUID().toString();
        this.directiveType = DirectiveType.TASK_CONSTRAINTS;
        this.priority = 0;
    }

    public PromptDirectiveBlock(DirectiveType directiveType, String directiveText, int priority) {
        this.blockId = UUID.randomUUID().toString();
        this.directiveType = directiveType;
        this.directiveText = directiveText;
        this.priority = priority;
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public PromptDirectiveBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public DirectiveType getDirectiveType() {
        return directiveType;
    }

    public PromptDirectiveBlock setDirectiveType(DirectiveType directiveType) {
        this.directiveType = directiveType;
        return this;
    }

    public String getDirectiveText() {
        return directiveText;
    }

    public PromptDirectiveBlock setDirectiveText(String directiveText) {
        this.directiveText = directiveText;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public PromptDirectiveBlock setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.PROMPT_DIRECTIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PromptDirectiveBlock that)) return false;
        return priority == that.priority &&
                Objects.equals(blockId, that.blockId) &&
                directiveType == that.directiveType &&
                Objects.equals(directiveText, that.directiveText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, directiveType, directiveText, priority);
    }

    @Override
    public String toString() {
        return "PromptDirectiveBlock{" +
                "blockId='" + blockId + '\'' +
                ", directiveType=" + directiveType +
                ", directiveText='" + directiveText + '\'' +
                ", priority=" + priority +
                '}';
    }
}
