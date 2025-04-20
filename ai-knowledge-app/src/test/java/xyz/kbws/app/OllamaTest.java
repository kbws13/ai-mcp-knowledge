package xyz.kbws.app;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.pgvector.PgVectorStore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kbws
 * @date 2025/4/20
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OllamaTest {
    @Resource
    private OllamaChatModel ollamaChatModel;

    @jakarta.annotation.Resource(name = "ollamaSimpleVectorStore")
    private SimpleVectorStore simpleVectorStore;

    @jakarta.annotation.Resource(name = "ollamaPgVectorStore")
    private PgVectorStore pgVectorStore;

    @jakarta.annotation.Resource
    private TokenTextSplitter tokenTextSplitter;

    @Test
    public void test_call() {
        ChatResponse response = ollamaChatModel.call(new Prompt(
                "1+1",
                OllamaOptions.builder().model("deepseek-r1:1.5b").build()));

        log.info("测试结果(call):{}", JSON.toJSONString(response));
    }
}
