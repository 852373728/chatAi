package com.qilin.ai;

import com.qilin.entity.ChatLogs;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class MongoChatMemoryStore implements ChatMemoryStore {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        // 查询 MongoDB 中指定 memoryId 对应的聊天消息列表
        Query query = new Query(Criteria.where("memoryId").is(memoryId));
        ChatLogs chatLogs = mongoTemplate.findOne(query, ChatLogs.class);
        if (chatLogs == null){
            return new LinkedList<>();
        }
        return ChatMessageDeserializer.messagesFromJson(chatLogs.getContent());
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        // 删除旧的聊天消息
        deleteMessages(memoryId);
        ChatLogs chatLogs = new ChatLogs(memoryId, ChatMessageSerializer.messagesToJson(messages));
        // 插入新的聊天消息
        mongoTemplate.insert(chatLogs);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        // 删除 MongoDB 中指定 memoryId 对应的聊天消息
        Query query = new Query(Criteria.where("memoryId").is(memoryId));
        mongoTemplate.remove(query, ChatLogs.class);
    }
}