package xyz.kbws.app;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MCPTest {

    @Resource
    private ChatClient chatClient;

    @Autowired
    private ToolCallbackProvider tools;

    @Test
    public void test_tool() {
        String userInput = "有哪些工具可以使用";

        System.out.println("\n>>> QUESTION: " + userInput);
        System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
    }

    @Test
    public void test() {
        String userInput = "获取电脑配置 在 /Users/fangyuan/Desktop 文件夹下，创建 电脑.txt 把电脑配置写入 电脑.txt 中";

        System.out.println("\n>>> QUESTION: " + userInput);
        System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
    }
}
