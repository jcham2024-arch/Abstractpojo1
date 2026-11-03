package com.apex.chat.tools.execution;

import com.apex.chat.core.content.ContentBlock;
import com.apex.chat.core.content.ContentType;

import java.util.Objects;
import java.util.UUID;

/**
 * Content block adapter for a ToolResult, allowing execution outputs to be returned to the model as content blocks.
 */
public class ToolResultBlock implements ContentBlock {

    private String blockId;
    private ToolResult toolResult;

    public ToolResultBlock() {
        this.blockId = UUID.randomUUID().toString();
        this.toolResult = new ToolResult();
    }

    public ToolResultBlock(ToolResult toolResult) {
        this.blockId = UUID.randomUUID().toString();
        this.toolResult = toolResult;
    }

    @Override
    public String getBlockId() {
        return blockId;
    }

    public ToolResultBlock setBlockId(String blockId) {
        this.blockId = blockId;
        return this;
    }

    public ToolResult getToolResult() {
        return toolResult;
    }

    public ToolResultBlock setToolResult(ToolResult toolResult) {
        this.toolResult = toolResult;
        return this;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.TOOL_RESULT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToolResultBlock that)) return false;
        return Objects.equals(blockId, that.blockId) && Objects.equals(toolResult, that.toolResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockId, toolResult);
    }

    @Override
    public String toString() {
        return "ToolResultBlock{" +
                "blockId='" + blockId + '\'' +
                ", toolResult=" + toolResult +
                '}';
    }
}
