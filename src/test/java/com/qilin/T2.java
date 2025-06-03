package com.qilin;

import com.qilin.ai.Assistant;
import com.qilin.ai.QuarantineChatMemory;
import com.qilin.tools.UrlSource;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String result = quarantineChatMemory.chatPolicy("teacher", "我要给学生讲解政策是怎么对企业产生影响的？给我准备一份大概的演讲稿", "教师");
        System.out.println(result);


    }

    @Resource
    private EmbeddingModel embeddingModel;

    @Test
    public void test5() {
        Response<Embedding> re = embeddingModel.embed("我是孙麒麟");

        System.out.println(re.metadata());
        System.out.println(re.content().vectorAsList());
        System.out.println(re.content().dimension());
    }

    @Resource
    private EmbeddingStore embeddingStore;

    @Test
    public void testPineconeEmbeded() {

//将文本转换成向量
        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
//存入向量数据库
        embeddingStore.add(embedding1, segment1);
        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }

    @Test
    public void testUploadKnowledgeLibrary() {
        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
        //并使用默认的文档解析器对文档进行解析
        for (int i = 10623; i < 45820; i++) {
            try {
                Document document = FileSystemDocumentLoader.loadDocument("D:\\doconment\\henan" + i + ".md");
                //文本向量化并存入向量数据库：将每个片段进行向量化，得到一个嵌入向量
                EmbeddingStoreIngestor
                        .builder()
                        .embeddingStore(embeddingStore)
                        .embeddingModel(embeddingModel)
                        .build()
                        .ingest(document);
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }


    @Test
    public void test6() throws IOException {
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            for (int i = 10000; i < 45820; i++) {
                // 构造包含 _source 参数的 URL
                String url = buildElasticsearchUrl(i, "*"); // 指定返回的字段
                UrlSource urlSource = new UrlSource(url);
                inputStream = urlSource.inputStream();
                fileOutputStream = new FileOutputStream("D:\\doconment\\henan" + i + ".md");
                inputStream.transferTo(fileOutputStream);
                fileOutputStream.flush();
                System.out.println(i);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    /**
     * 构造 Elasticsearch 查询 URL，支持指定返回字段和查询条件
     *
     * @param from   查询起始位置
     * @param fields 返回的字段列表，逗号分隔
     * @return 构造后的 URL
     */
    private String buildElasticsearchUrl(int from, String fields) {
        // 添加查询条件 provinceCode=41
        return "http://xiaoming:password@116.255.222.52:9250/big-data-2022-05-26/_search?size=1&from=" + from +
               "&sort=releaseTime:desc&_source=" + fields +
               "&q=provinceCode:41"; // 新增查询条件
    }

}
