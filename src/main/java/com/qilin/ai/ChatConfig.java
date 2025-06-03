package com.qilin.ai;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean("chatMemoryProvider")
    public ChatMemoryProvider chatMemoryProvider(MongoChatMemoryStore mongoChatMemoryStore){
        return chatMemoryId -> MessageWindowChatMemory
                .builder()
                .id(chatMemoryId).chatMemoryStore(mongoChatMemoryStore)
                .maxMessages(20).build();
    }

    @Bean("xiaozhichatMemoryProvider")
    public ChatMemoryProvider xiaozhichatMemoryProvider(MongoChatMemoryStore mongoChatMemoryStore){
        return chatMemoryId -> MessageWindowChatMemory
                .builder()
                .id(chatMemoryId).chatMemoryStore(mongoChatMemoryStore)
                .maxMessages(20).build();
    }

    @Bean("policyMemoryProvider")
    public ChatMemoryProvider policyMemoryProvider(MongoChatMemoryStore mongoChatMemoryStore){
        return chatMemoryId -> MessageWindowChatMemory
                .builder()
                .id(chatMemoryId).chatMemoryStore(mongoChatMemoryStore)
                .maxMessages(30).build();
    }

}
