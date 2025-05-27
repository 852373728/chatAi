package com.qilin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.qilin.ai.QuarantineChatMemory;
import com.qilin.entity.UserChat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "聊天")
@RequestMapping("/gene")
public class ChatController {

    @Resource
    private QuarantineChatMemory quarantineChatMemory;


    @Operation(summary = "有角色的聊天")
    @PostMapping("/chatRole")
    public String chatRole(@RequestBody UserChat userChat){
        String result = quarantineChatMemory.chatPolicy(userChat.getMemoryId(), userChat.getQuestion(),"学生");
        return result;
    }


    @Operation(summary = "聊天")
    @PostMapping("/chat")
    public String chat(@RequestBody UserChat userChat){
        String result = quarantineChatMemory.chat(userChat.getMemoryId(), userChat.getQuestion());
        return result;
    }

}
