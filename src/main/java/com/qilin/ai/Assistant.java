package com.qilin.ai;

import dev.langchain4j.service.spring.AiService;

@AiService(chatMemory = "chatMemory")
public interface Assistant {
    String chat(String userMessage);
}
