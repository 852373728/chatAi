package com.qilin;

import com.qilin.ai.Assistant;
import com.qilin.ai.QuarantineChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class T2 {

    @Resource
    private Assistant assistant;

    @Test
    public void test() {
        String result = assistant.chat("我是孙麒麟");
        System.out.println(result);
        String result2 = assistant.chat("我是谁");
        System.out.println(result2);
    }


    @Resource
    private QuarantineChatMemory quarantineChatMemory;
    @Test
    public void test2() {
        String result = quarantineChatMemory.chat("1", "我是孙麒麟");
        System.out.println(result);
        System.out.println("*************");

        String result2 = quarantineChatMemory.chat("1", "我是谁");
        System.out.println(result2);
        System.out.println("*************");
        String result3 = quarantineChatMemory.chat("3", "我是谁");
        System.out.println(result3);

    }

    @Test
    public void te3() {
        String result = quarantineChatMemory.chat("1", "你现在还记得我是谁吗？");
        System.out.println(result);



    }

    @Test
    public void te4() {
        String result = quarantineChatMemory.chatPolicy("teacher", "我要给学生讲解政策是怎么对企业产生影响的？给我准备一份大概的演讲稿","教师");
        System.out.println(result);



    }

}
