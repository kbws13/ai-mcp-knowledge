package xyz.kbws.csdn.infrastructure.gateway;

import retrofit2.Call;
import retrofit2.http.*;
import xyz.kbws.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import xyz.kbws.csdn.infrastructure.gateway.dto.ArticleResponseDTO;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
public interface ICSDNService {
    @Headers({
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36",
            "Connection: keep-alive",
            "Accept: application/json, text/plain, */*",
            "Accept-Encoding: gzip, deflate, br",
            "Content-Type: application/json",
            "accept-language: en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7",
            "content-type: application/json;",
            "origin: https://mpbeta.csdn.net",
            "priority: u=1, i",
            "referer: https://mpbeta.csdn.net/",
            "sec-ch-ua: \"Google Chrome\";v=\"135\", \"Not-A.Brand\";v=\"8\", \"Chromium\";v=\"135\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"macOS\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "x-ca-key: 203803574",
            "x-ca-nonce: 516e9415-44eb-4efa-a269-ef19325ea124",
            "x-ca-signature: 1xh0HRchkopKGiRY/qnCxLeLaupxmoB7OH0nRHoXfmc=",
            "x-ca-signature-headers: x-ca-key,x-ca-nonce"
    })
    @POST("/blog-console-api/v1/postedit/saveArticle")
    Call<ArticleResponseDTO> saveArticle(
            @Body ArticleRequestDTO request,
            @Header("Cookie") String cookieValue
    );
}
