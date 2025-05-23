package com.qilin;

import com.qilin.entity.ChatLogs;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MonggoTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        ChatLogs chatLogs = new ChatLogs("我是第二个聊天");
        mongoTemplate.insert(chatLogs);
    }


}
