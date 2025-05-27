package com.qilin.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel", chatMemoryProvider = "chatMemoryProvider",tools = {"mathTool"})
public interface QuarantineChatMemory {
    String chat(@MemoryId String memoryId,@UserMessage String userMessage);



    @SystemMessage(fromResource = "chatPolicy.txt")
    String chatPolicy(@MemoryId String memoryId, @UserMessage String userMessage, @V("level")String level);

}
