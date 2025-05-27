package com.qilin.controller;

import com.qilin.ai.XiaozhiChatMemory;
import com.qilin.entity.UserChat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "医疗助手")
@RequestMapping("/xiaozhi")
public class XiaozhiController {


    @Resource
    private XiaozhiChatMemory xiaozhiChatMemory;


    @Operation(summary = "智能问答")
    @PostMapping("/chat")
    public String chat(@RequestBody UserChat userChat){
        String result = xiaozhiChatMemory.chat(userChat.getMemoryId(), userChat.getQuestion());
        return result;
    }

}
