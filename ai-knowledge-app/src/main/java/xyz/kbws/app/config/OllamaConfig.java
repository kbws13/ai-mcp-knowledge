package xyz.kbws.app.config;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author kbws
 * @date 2025/4/20
 * @description:
 */
@Configuration
public class OllamaConfig {
    @Bean("ollamaSimpleVectorStore")
    public SimpleVectorStore vectorStore(OllamaApi ollamaApi) {
        OllamaEmbeddingModel embeddingModel = OllamaEmbeddingModel
                .builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaOptions.builder().model("nomic-embed-text").build())
                .build();
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    /**
     * -- 删除旧的表（如果存在）
     * DROP TABLE IF EXISTS public.vector_store_ollama_deepseek;
     * <p>
     * -- 创建新的表，使用UUID作为主键
     * CREATE TABLE public.vector_store_ollama_deepseek (
     * id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
     * content TEXT NOT NULL,
     * metadata JSONB,
     * embedding VECTOR(768)
     * );
     * <p>
     * SELECT * FROM vector_store_ollama_deepseek
     */
    @Bean("ollamaPgVectorStore")
    public PgVectorStore pgVectorStore(OllamaApi ollamaApi, JdbcTemplate jdbcTemplate) {
        OllamaEmbeddingModel embeddingModel = OllamaEmbeddingModel
                .builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaOptions.builder().model("nomic-embed-text").build())
                .build();
        return PgVectorStore.builder(jdbcTemplate, embeddingModel)
                .vectorTableName("vector_store_ollama_deepseek")
                .build();
    }


    @Bean
    public OllamaChatModel ollamaChatModel(OllamaApi ollamaApi) {
        return OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaOptions.builder()
                        .model("qwq:latest")  // 或 llama3、phi3、mistral 等
                        .build())
                .build();
    }
}
