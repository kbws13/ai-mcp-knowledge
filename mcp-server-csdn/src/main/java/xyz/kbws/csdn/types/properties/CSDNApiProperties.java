package xyz.kbws.csdn.types.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kbws
 * @date 2025/4/26
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "csdn.api")
public class CSDNApiProperties {
    private String cookie;

    private String categories;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

}
