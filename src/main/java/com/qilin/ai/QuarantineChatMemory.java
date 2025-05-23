package com.qilin.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService(chatMemoryProvider = "chatMemoryProvider")
public interface QuarantineChatMemory {
    String chat(@MemoryId String memoryId,@UserMessage String userMessage);
}
