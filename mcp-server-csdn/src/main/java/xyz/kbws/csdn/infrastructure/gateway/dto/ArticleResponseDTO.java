package xyz.kbws.csdn.infrastructure.gateway.dto;

import lombok.Data;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Data
public class ArticleResponseDTO {
    private Integer code;
    private String traceId;
    private ArticleData data;
    private String msg;

    @Data
    public static class ArticleData {
        private String url;
        private Long article_id;
        private String qrcode;
        private String title;
        private String description;
    }
}
