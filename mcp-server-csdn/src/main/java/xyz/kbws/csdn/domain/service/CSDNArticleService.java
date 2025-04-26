package xyz.kbws.csdn.domain.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import xyz.kbws.csdn.domain.adapter.ICSDNPort;
import xyz.kbws.csdn.domain.model.ArticleFunctionRequest;
import xyz.kbws.csdn.domain.model.ArticleFunctionResponse;

import java.io.IOException;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Slf4j
@Service
public class CSDNArticleService {
    @Resource
    private ICSDNPort port;

    @Tool(description = "发布文章到CSDN")
    public ArticleFunctionResponse saveArticle(ArticleFunctionRequest request) throws IOException {
        log.info("CSDN发帖，标题:{} 内容:{} 标签:{}", request.getTitle(), request.getMarkdownContent(), request.getTags());
        return port.writeArticle(request);
    }
}
