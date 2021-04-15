package com.rjgc.configs.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhaoyunjie
 * @date 2021-04-15 15:54
 */
@ConfigurationProperties(prefix = "cors")
@Component
@Data
public class CorsProperties {

    private String allowOrigin;

    private String allowMethods;

    private boolean allowCredentials;

    private long maxAge;

}
