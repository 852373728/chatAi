package com.qilin.ai;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15q.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddingStoreConfig {

   /* @Resource
    private EmbeddingModel embeddingModel;*/


    @Bean
    public BgeSmallEnV15QuantizedEmbeddingModel embeddingModel(){
        return new BgeSmallEnV15QuantizedEmbeddingModel();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
//创建向量存储
        EmbeddingStore<TextSegment> embeddingStore = PineconeEmbeddingStore.builder()
                .apiKey("pcsk_5YvWVm_7siHV73A68mwnUJgj3um3xe4BaP2ax2JCnGzxmmXbwxf3iEN3csg5ijKD6scxW1")
                .index("new-henan-index")//如果指定的索引不存在，将创建一个新的索引
                .nameSpace("new-henan-namespace") //如果指定的名称空间不存在，将创建一个新的名称 空间
                .createIndex(PineconeServerlessIndexConfig.builder()
                        .cloud("AWS") //指定索引部署在 AWS 云服务上。
                        .region("us-east-1") //指定索引所在的 AWS 区域为 us-east-1。
                        .dimension(embeddingModel().dimension()) //指定索引的向量维度，该维度与 embeddedModel 生成的向量维度相同。
                        .build())
                .build();

        return embeddingStore;
    }


    @Bean
    public ContentRetriever contentRetrieverPolicyPincone() {
// 创建一个 EmbeddingStoreContentRetriever 对象，用于从嵌入存储中检索内容
        return EmbeddingStoreContentRetriever
                .builder()
// 设置用于生成嵌入向量的嵌入模型
                .embeddingModel(embeddingModel())
// 指定要使用的嵌入存储
                .embeddingStore(embeddingStore())
// 设置最大检索结果数量，这里表示最多返回 1 条匹配结果
                .maxResults(1)
// 设置最小得分阈值，只有得分大于等于 0.8 的结果才会被返回
                .minScore(0.8)
// 构建最终的 EmbeddingStoreContentRetriever 实例
                .build();
    }


}
