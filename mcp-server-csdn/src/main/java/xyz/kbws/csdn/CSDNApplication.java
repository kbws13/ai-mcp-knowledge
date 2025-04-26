package xyz.kbws.csdn;

import jakarta.annotation.Resource;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import xyz.kbws.csdn.domain.service.CSDNArticleService;
import xyz.kbws.csdn.infrastructure.gateway.ICSDNService;
import xyz.kbws.csdn.types.properties.CSDNApiProperties;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@SpringBootApplication
public class CSDNApplication implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(CSDNApplication.class);

    @Resource
    private CSDNApiProperties csdnApiProperties;

    public static void main(String[] args) {
        SpringApplication.run(CSDNApplication.class, args);
    }


    @Bean
    public ICSDNService csdnService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .sslSocketFactory(createIgnoreVerifySSLSocketFactory(), new TrustAllTrustManager())
                .hostnameVerifier((hostname, session) -> true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bizapi.csdn.net/")
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(ICSDNService.class);
    }

    @Bean
    public ToolCallbackProvider csdnTools(CSDNArticleService csdnArticleService) {
        return MethodToolCallbackProvider.builder().toolObjects(csdnArticleService).build();
    }

    @Override
    public void run(String... args) {
        log.info("check csdn cookie ...");
        if (csdnApiProperties.getCookie() == null) {
            log.warn("csdn cookie key is null, please set it in application.yml");
        } else {
            log.info("csdn cookie  key is {}", csdnApiProperties.getCookie());
        }
    }

    // 实现 TrustAllTrustManager 和 createIgnoreVerifySSLSocketFactory
    private static class TrustAllTrustManager implements X509TrustManager {
        @Override public void checkClientTrusted(X509Certificate[] chain, String authType) {}
        @Override public void checkServerTrusted(X509Certificate[] chain, String authType) {}
        @Override public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
    }

    private static SSLSocketFactory createIgnoreVerifySSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllTrustManager()}, null);
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
