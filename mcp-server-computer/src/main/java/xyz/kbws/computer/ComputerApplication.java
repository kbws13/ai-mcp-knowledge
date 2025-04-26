package xyz.kbws.computer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.kbws.computer.domain.service.ComputerService;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Slf4j
@SpringBootApplication
public class ComputerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ComputerApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider computerTools(ComputerService computerService) {
        return MethodToolCallbackProvider.builder().toolObjects(computerService).build();
    }

    @Override
    public void run(String... args) {
        log.info("mcp server computer success!");
    }
}
