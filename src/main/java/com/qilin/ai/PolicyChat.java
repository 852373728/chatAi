package com.qilin.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

@AiService(wiringMode = EXPLICIT, streamingChatModel = "qwenStreamingChatModel",chatMemoryProvider = "policyMemoryProvider",contentRetriever = "contentRetrieverPolicyPincone")
public interface PolicyChat {

    @SystemMessage(fromResource = "policy.txt")
    Flux<String> chat(@MemoryId String memoryId, @UserMessage String userMessage);
}
