package com.apex.chat.tools;

import com.apex.chat.core.content.ContentType;
import com.apex.chat.tools.execution.ToolCall;
import com.apex.chat.tools.execution.ToolCallBlock;
import com.apex.chat.tools.execution.ToolResult;
import com.apex.chat.tools.execution.ToolResultBlock;
import com.apex.chat.tools.policy.ToolExecutionPolicy;
import com.apex.chat.tools.registry.ToolRegistry;
import com.apex.chat.tools.spec.ToolDefinition;
import com.apex.chat.tools.spec.ToolParameterSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChatToolsTest {

    @Test
    public void testToolDefinitionAndRegistry() {
        ToolDefinition tool = new ToolDefinition("queryDatabase", "Executes read-only SQL queries")
                .addParameter(new ToolParameterSpecification("sql", "string", "SQL select query", true))
                .setExecutionPolicy(new ToolExecutionPolicy().setRequiresUserConfirmation(true).setTimeoutSeconds(15));

        ToolRegistry registry = new ToolRegistry()
                .register(tool);

        Assertions.assertTrue(registry.hasTool("queryDatabase"));
        Assertions.assertEquals("Executes read-only SQL queries", registry.getTool("queryDatabase").getDescription());
        Assertions.assertTrue(registry.getTool("queryDatabase").getExecutionPolicy().isRequiresUserConfirmation());
    }

    @Test
    public void testToolExecutionAndBlocks() {
        ToolCall call = new ToolCall("call-99", "queryDatabase", "{\"sql\":\"SELECT * FROM users\"}");
        ToolCallBlock callBlock = new ToolCallBlock(call);

        ToolResult result = ToolResult.success("call-99", "queryDatabase", "{\"count\": 5}")
                .setExecutionDurationMs(120);
        ToolResultBlock resultBlock = new ToolResultBlock(result);

        Assertions.assertEquals(ContentType.TOOL_CALL, callBlock.getContentType());
        Assertions.assertEquals("call-99", callBlock.getToolCall().getCallId());

        Assertions.assertEquals(ContentType.TOOL_RESULT, resultBlock.getContentType());
        Assertions.assertTrue(resultBlock.getToolResult().isSuccess());
        Assertions.assertEquals(120, resultBlock.getToolResult().getExecutionDurationMs());
    }
}
