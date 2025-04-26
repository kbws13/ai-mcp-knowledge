package xyz.kbws.csdn.domain.adapter;

import xyz.kbws.csdn.domain.model.ArticleFunctionRequest;
import xyz.kbws.csdn.domain.model.ArticleFunctionResponse;

import java.io.IOException;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
public interface ICSDNPort {
    ArticleFunctionResponse writeArticle(ArticleFunctionRequest request) throws IOException;
}
