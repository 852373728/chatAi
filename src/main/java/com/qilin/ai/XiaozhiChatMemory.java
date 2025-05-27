package com.qilin.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,chatModel = "qwenChatModel",chatMemoryProvider = "xiaozhichatMemoryProvider",tools = {"appointmentTools"})
public interface XiaozhiChatMemory {


    @SystemMessage(fromResource = "xiaozhi.txt")
    String chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
