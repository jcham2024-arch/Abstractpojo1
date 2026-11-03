package com.apex.chat.tools.execution;

import com.apex.chat.core.content.ContentBlock;
import com.apex.chat.core.content.ContentType;

import java.util.Objects;
import java.util.UUID;

/**
 * Content block adapter for a ToolCall, allowing tool invocations to be interleaved in messages.
 */
public class ToolCallBlock implements ContentBlock {

    private String blockId;
    private ToolCall toolCall;

    public ToolCallBlock() {
        this.blockId = UUID.randomUUID().toString();
        this.toolCall = new ToolCall();
    }

    public ToolCallBlock(ToolCall toolCall) {
        this.blockId = UUID.randomUUID().toString();
        this.toolCall = toolCall;
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public ToolCallBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public ToolCall getToolCall() {
        return toolCall;
    }

    public ToolCallBlock setToolCall(ToolCall toolCall) {
        this.toolCall = toolCall;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.TOOL_CALL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolCallBlock that)) return false;
        return Objects.equals(blockId, that.blockId) && Objects.equals(toolCall, that.toolCall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, toolCall);
    }

    @Override
    public String toString() {
        return "ToolCallBlock{" +
                "blockId='" + blockId + '\'' +
                ", toolCall=" + toolCall +
                '}';
    }
}
