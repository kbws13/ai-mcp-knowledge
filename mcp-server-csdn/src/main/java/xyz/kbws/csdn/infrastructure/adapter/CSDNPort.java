package xyz.kbws.csdn.infrastructure.adapter;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import xyz.kbws.csdn.domain.adapter.ICSDNPort;
import xyz.kbws.csdn.domain.model.ArticleFunctionRequest;
import xyz.kbws.csdn.domain.model.ArticleFunctionResponse;
import xyz.kbws.csdn.infrastructure.gateway.ICSDNService;
import xyz.kbws.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import xyz.kbws.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import xyz.kbws.csdn.types.properties.CSDNApiProperties;

import java.io.IOException;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Slf4j
@Component
public class CSDNPort implements ICSDNPort {
    @Resource
    private ICSDNService csdnService;

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Override
    public ArticleFunctionResponse writeArticle(ArticleFunctionRequest request) throws IOException {

        ArticleRequestDTO articleRequestDTO = new ArticleRequestDTO();
        articleRequestDTO.setTitle(request.getTitle());
        articleRequestDTO.setMarkdownContent(request.getMarkdownContent());
        articleRequestDTO.setContent(request.getContent());
        articleRequestDTO.setTags(request.getTags());
        articleRequestDTO.setDescription(request.getDescription());
        articleRequestDTO.setCategories(csdnApiProperties.getCategories());

        Call<ArticleResponseDTO> call = csdnService.saveArticle(articleRequestDTO, csdnApiProperties.getCookie());
        Response<ArticleResponseDTO> response = call.execute();

        log.info("请求CSDN发帖 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

        if (response.isSuccessful()) {
            ArticleResponseDTO articleResponseDTO = response.body();
            if (null == articleResponseDTO) return null;

            ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
            articleFunctionResponse.setCode(articleResponseDTO.getCode());
            articleFunctionResponse.setMsg(articleResponseDTO.getMsg());

            return articleFunctionResponse;
        }

        return null;
    }

}
