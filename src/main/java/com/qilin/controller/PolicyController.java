package com.qilin.controller;

import com.qilin.ai.PolicyChat;
import com.qilin.entity.ChatLogs;
import com.qilin.entity.UserChat;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@Tag(name = "政策助手")
@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Resource
    private PolicyChat policyChat;



    @Operation(summary = "政策问答")
    @PostMapping(value = "/chat",produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody UserChat userChat) {

        return policyChat.chat(userChat.getMemoryId(), userChat.getQuestion());
    }
}
